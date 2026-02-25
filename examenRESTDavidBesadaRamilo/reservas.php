<?php
require_once __DIR__ . "/db_config.php";

header("Content-Type: application/json; charset=utf-8");

try {
    $pdo = new PDO($dsn, $user, $pass, [
        PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
        PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8mb4"
    ]);
} catch (Exception $e) {
    http_response_code(503);
    echo json_encode(["error" => "No se pudo conectar"]);
    exit;
}

function isInt($v): bool
{
    return is_string($v) && preg_match('/^\d+$/', $v);
}

function requireId($id)
{
    if ($id === null || !isInt((string)$id)) {
        http_response_code(422);
        echo json_encode(["error" => "El id de la habitacion es obligatorio."]);
    }

}

$method = $_SERVER["REQUEST_METHOD"];
$pathInfo = isset($_SERVER["PATH_INFO"]) ? trim($_SERVER["PATH_INFO"], "/") : "";
$parts = ($pathInfo === "") ? [] : explode("/", $pathInfo);
$resource = isset($parts[0]) ? $parts[0] : '';
$id = isset($parts[1]) ? $parts[1] : null;


// GET DE LAS HABITACIONES (paso previo a ver las reservas)
// RUTA http://localhost/reservas/reservas.php/habitaciones/
if ($resource === "habitaciones") {
    if ($method === "GET" && $id !== null) {
        $st = $pdo->prepare("SELECT * FROM habitacion");
        $st->execute();
        echo json_encode($st->fetchAll(PDO::FETCH_ASSOC));
        exit;
    } else if ($method === "GET" && $id !== null) {
        $st = $pdo->prepare("SELECT * FROM habitacion WHERE id = ?");
        $st->execute([$id]);
        if ($st->rowCount() === 0) {
            http_response_code(404);
            echo json_encode(["error" => "Esa habitacion no existe"]);
            exit;
        }
        echo json_encode($st->fetchAll(PDO::FETCH_ASSOC));
        exit;
    }
}

// GET / POST / DELETE DE LAS RESERVAS PARA UNA HABITACION
// RUTA http://localhost/reservas/reservas.php/reservas/
if ($resource === "reservas") {
    requireId($id);
    $idHabitacion = (int)$id;

    // METODO GET DE LAS RESERVAS
    if ($method === "GET") {

        // se comprueba que la habitacion existe
        $st = $pdo->prepare("SELECT * FROM habitacion WHERE id = ?");
        $st->execute([$id]);
        if ($st->rowCount() === 0) {
            http_response_code(404);
            echo json_encode(["error" => "Esa habitacion no existe"]);
            exit;
        }

        // si existe se buscan las reservas
        $st = $pdo->prepare("SELECT * FROM reserva WHERE idHabitacion = ?");
        $st->execute([$idHabitacion]);
        $reservasHabitacion = $st->fetchAll(PDO::FETCH_ASSOC);
        if (count($reservasHabitacion) === 0) {
            echo json_encode(["error" => "No se encontraron reservas"]);
            exit;
        }
        http_response_code(200);
        // y se devuelven
        echo json_encode($reservasHabitacion);
        exit;
    }


    // METODO PUT DE LAS RESERVAS
    if ($method === "POST") {
        requireId($id);

        // se comprueba que la habitacion existe
        $st = $pdo->prepare("SELECT * FROM habitacion WHERE id = ?");
        $st->execute([$id]);
        if ($st->rowCount() === 0) {
            http_response_code(404);
            echo json_encode(["error" => "Esa habitacion no existe"]);
            exit;
        }

        $stringDatosHabitacion = file_get_contents("php://input", true);
        parse_str($stringDatosHabitacion, $habitacion);

        // el id ya es obligatorio, no se comprueba, pero los otros lanzan un error de UNPROCESSED ENTITY en caso de no concretarse
        $idHabitacion = $habitacion["idHabitacion"];
        if ($habitacion["nombre"] == null || $habitacion["nombre"] == "") {
            http_response_code(422);
            echo json_encode(["error" => "Nombre de la reserva es obligatorio"]);
            exit;
        }
        $nombre = $habitacion["nombre"];
        if ($habitacion["dia"] == null || $habitacion["dia"] == "") {
            http_response_code(422);
            echo json_encode(["error" => "Dia de entrada es obligatorio"]);
            exit;
        }

        //
        // TODO: falta comprobar que los dias no coincidan con otra reserva (comentado en el examen, prefiero terminar antes el delete ("-.-))
        //

        $dia = $habitacion["dia"];
        if ($habitacion["numDias"] == null || $habitacion["numDias"] == "") {
            http_response_code(422);
            echo json_encode(["error" => "Numero de dias es obligatorio"]);
            exit;
        }
        $numDias = $habitacion["numDias"];

        $st = $pdo->prepare("INSERT INTO reserva (idHabitacion,nombre,dia,numDias) VALUES (?,?,?,?)");
        $st->execute([$idHabitacion, $nombre, $dia, $numDias]);

        http_response_code(201);
        echo json_encode(["idReserva" => $pdo->lastInsertId()]);
        exit;
    }

    // METODO DELETE para borrar las reservas
    if ($method === "DELETE") {
        requireId($id);

        // se comprueba que la reserva (eh, que no la habitacion, es el mismo metodo lo se) existe
        $st = $pdo->prepare("SELECT * FROM reserva WHERE codReserva = ?");
        $st->execute([$id]);
        if ($st->rowCount() === 0) {
            http_response_code(404);
            echo json_encode(["error" => "Esa reserva no existe"]);
            exit;
        }

        $st = $pdo->prepare("DELETE FROM reserva WHERE codReserva = ?");
        $st->execute([$id]);
        http_response_code(204);

    }
}

if ($resource !== "habitaciones" && $resource !== "reservas") {
    http_response_code(404);
}