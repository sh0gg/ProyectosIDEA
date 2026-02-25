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

function log_historial(PDO $pdo, string $metodo, string $accion, string $descripcion) {
  $st = $pdo->prepare("INSERT INTO historial (metodo, accion, descripcion) VALUES (?,?,?)");
  $st->execute([$metodo, $accion, $descripcion]);
}

function ocupacion(PDO $pdo, int $idCurso): int {
  $st = $pdo->prepare("SELECT COUNT(*) FROM alumnos WHERE idCurso=?");
  $st->execute([$idCurso]);
  return (int)$st->fetchColumn();
}

$method = $_SERVER["REQUEST_METHOD"];
$pathInfo = isset($_SERVER["PATH_INFO"]) ? trim($_SERVER["PATH_INFO"], "/") : "";
$parts = ($pathInfo === "") ? [] : explode("/", $pathInfo);

/* ================= HISTORIAL ================= */
if ($pathInfo === "historial" && $method === "GET") {
  $st = $pdo->query("SELECT * FROM historial ORDER BY fecha DESC");
  echo json_encode($st->fetchAll(PDO::FETCH_ASSOC));
  exit;
}

/* ================= CURSOS ================= */

if ($parts[0] === "cursos" && count($parts) === 1) {

  if ($method === "GET") {
    $st = $pdo->query("SELECT * FROM cursos ORDER BY nombreCurso");
    echo json_encode($st->fetchAll(PDO::FETCH_ASSOC));
    exit;
  }

  if ($method === "POST") {
    parse_str(file_get_contents("php://input"), $data);
    $nombre = trim($data["nombreCurso"] ?? "");
    $aforo = (int)($data["aforoMax"] ?? 0);

    if ($nombre === "" || $aforo <= 0) {
      http_response_code(422);
      echo json_encode(["error"=>"Datos inválidos"]);
      exit;
    }

    $st = $pdo->prepare("INSERT INTO cursos (nombreCurso, aforoMax) VALUES (?,?)");
    $st->execute([$nombre,$aforo]);

    log_historial($pdo, "POST", "CREAR_CURSO", "Curso: $nombre, Aforo: $aforo");

    http_response_code(201);
    echo json_encode(["idCurso"=>$pdo->lastInsertId()]);
    exit;
  }
}

/* ================= CURSO POR ID ================= */

if ($parts[0] === "cursos" && count($parts) === 2 && ctype_digit($parts[1])) {

  $id = (int)$parts[1];

  if ($method === "GET") {
    $st = $pdo->prepare("SELECT * FROM cursos WHERE idCurso=?");
    $st->execute([$id]);
    $curso = $st->fetch(PDO::FETCH_ASSOC);
    if (!$curso) { http_response_code(404); exit; }

    $ocup = ocupacion($pdo,$id);
    echo json_encode([
      "idCurso"=>$id,
      "nombreCurso"=>$curso["nombreCurso"],
      "aforoMax"=>$curso["aforoMax"],
      "ocupacion"=>$ocup,
      "plazasLibres"=>$curso["aforoMax"] - $ocup
    ]);
    exit;
  }

  if ($method === "DELETE") {
    $st = $pdo->prepare("DELETE FROM cursos WHERE idCurso=?");
    $st->execute([$id]);

    log_historial($pdo,"DELETE","BORRAR_CURSO","Curso ID: $id");

    http_response_code(204);
    exit;
  }
}

/* ================= ALUMNOS ================= */

if ($parts[0] === "cursos" && count($parts) === 3 && $parts[2] === "alumnos") {

  $idCurso = (int)$parts[1];

  if ($method === "GET") {
    $st = $pdo->prepare("SELECT * FROM alumnos WHERE idCurso=?");
    $st->execute([$idCurso]);
    echo json_encode($st->fetchAll(PDO::FETCH_ASSOC));
    exit;
  }

  if ($method === "POST") {
    parse_str(file_get_contents("php://input"), $data);
    $nombreAlumno = trim($data["nombreAlumno"] ?? "");

    if ($nombreAlumno === "") {
      http_response_code(422);
      echo json_encode(["error"=>"Nombre vacío"]);
      exit;
    }

    $ocup = ocupacion($pdo,$idCurso);

    $st = $pdo->prepare("SELECT aforoMax FROM cursos WHERE idCurso=?");
    $st->execute([$idCurso]);
    $aforo = (int)$st->fetchColumn();

    if ($ocup >= $aforo) {
      http_response_code(409);
      echo json_encode(["error"=>"Curso completo"]);
      exit;
    }

    $st = $pdo->prepare("INSERT INTO alumnos (idCurso,nombreAlumno) VALUES (?,?)");
    $st->execute([$idCurso,$nombreAlumno]);

    log_historial($pdo,"POST","CREAR_ALUMNO","Alumno: $nombreAlumno en Curso ID: $idCurso");

    http_response_code(201);
    echo json_encode(["idAlumno"=>$pdo->lastInsertId()]);
    exit;
  }
}

http_response_code(404);
echo json_encode(["error"=>"Ruta no encontrada"]);