package http.REST.matricula;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Scanner;

public class MenuMatricula {
    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ApiClient api = new ApiClient("http://localhost/cursos/rest.php");

        while (true) {
            System.out.println("\n=== MATRÍCULA INSTITUTO (REST) ===");
            System.out.println("1) Crear curso (nombre+aforo) y meter alumnos hasta aforo o blanco");
            System.out.println("2) Mostrar alumnos de un curso (por nombre)");
            System.out.println("3) Añadir N alumnos a un curso si caben");
            System.out.println("4) Eliminar curso (y alumnos)");
            System.out.println("6) Ver historial");
            System.out.println("5) Salir");
            System.out.print("Opción: ");

            int op = Integer.parseInt(sc.nextLine().trim());

            try {
                switch (op) {
                    case 1 -> altaCursoYAlumnos(api);
                    case 2 -> listarAlumnosPorNombreCurso(api);
                    case 3 -> insertarNAlumnosSiCaben(api);
                    case 4 -> eliminarCurso(api);
                    case 5 -> { System.out.println("Fin."); return; }
                    case 6 -> verHistorial(api);
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // 1) Crear curso y meter alumnos hasta aforo o blanco
    static void altaCursoYAlumnos(ApiClient api) throws Exception {
        System.out.print("Nombre del curso/grupo (DAM1, DAM2, ASIR1...): ");
        String nombre = sc.nextLine().trim();
        System.out.print("Aforo máximo: ");
        int aforo = Integer.parseInt(sc.nextLine().trim());

        String form = "nombreCurso=" + ApiClient.enc(nombre) + "&aforoMax=" + aforo;
        ApiClient.Response r = api.postForm("/cursos", form);

        if (r.status != 201) {
            System.out.println("No se pudo crear curso. (" + r.status + ") " + r.body);
            return;
        }

        int idCurso = new JSONObject(r.body).getInt("idCurso");
        System.out.println("Curso creado con idCurso=" + idCurso);

        // Meter alumnos hasta aforo o blanco
        while (true) {
            ApiClient.Response info = api.get("/cursos/" + idCurso);
            if (info.status != 200) {
                System.out.println("Error consultando curso: (" + info.status + ") " + info.body);
                return;
            }

            JSONObject j = new JSONObject(info.body);
            int libres = j.getInt("plazasLibres");

            if (libres <= 0) {
                System.out.println("Curso completo.");
                break;
            }

            System.out.print("Nombre alumno (ENTER para terminar). Quedan " + libres + " plazas: ");
            String alumno = sc.nextLine().trim();
            if (alumno.isEmpty()) break;

            ApiClient.Response ra = api.postForm("/cursos/" + idCurso + "/alumnos",
                    "nombreAlumno=" + ApiClient.enc(alumno));

            if (ra.status == 201) System.out.println("Alumno añadido.");
            else System.out.println("No se pudo añadir (" + ra.status + "): " + ra.body);
        }
    }

    // 2) Preguntar un nombre de curso y mostrar sus alumnos
    static void listarAlumnosPorNombreCurso(ApiClient api) throws Exception {
        System.out.print("Nombre del curso: ");
        String nombre = sc.nextLine().trim();

        ApiClient.Response rc = api.get("/cursos?nombre=" + ApiClient.enc(nombre));
        if (rc.status != 200) {
            System.out.println("Curso no encontrado. (" + rc.status + ") " + rc.body);
            return;
        }

        int idCurso = new JSONObject(rc.body).getInt("idCurso");
        ApiClient.Response ra = api.get("/cursos/" + idCurso + "/alumnos");

        if (ra.status != 200) {
            System.out.println("Error listando alumnos. (" + ra.status + ") " + ra.body);
            return;
        }

        JSONArray arr = new JSONArray(ra.body);
        System.out.println("Alumnos de " + nombre + ":");
        if (arr.length() == 0) System.out.println("  (sin alumnos)");
        for (int i = 0; i < arr.length(); i++) {
            System.out.println(" - " + arr.getJSONObject(i).getString("nombreAlumno"));
        }
    }

    // 3) Preguntar nombre curso y nº alumnos. Si caben, pedir nombres e insertar. Si no, informar.
    static void insertarNAlumnosSiCaben(ApiClient api) throws Exception {
        System.out.print("Nombre del curso: ");
        String nombre = sc.nextLine().trim();

        ApiClient.Response rc = api.get("/cursos?nombre=" + ApiClient.enc(nombre));
        if (rc.status != 200) {
            System.out.println("Curso no encontrado. (" + rc.status + ") " + rc.body);
            return;
        }

        int idCurso = new JSONObject(rc.body).getInt("idCurso");

        ApiClient.Response info = api.get("/cursos/" + idCurso);
        if (info.status != 200) {
            System.out.println("Error consultando curso: (" + info.status + ") " + info.body);
            return;
        }

        JSONObject j = new JSONObject(info.body);
        int libres = j.getInt("plazasLibres");

        System.out.print("¿Cuántos alumnos quieres añadir?: ");
        int n = Integer.parseInt(sc.nextLine().trim());

        if (n > libres) {
            System.out.println("No caben. Plazas libres: " + libres);
            return;
        }

        for (int i = 1; i <= n; i++) {
            System.out.print("Nombre alumno " + i + ": ");
            String alumno = sc.nextLine().trim();

            ApiClient.Response ra = api.postForm("/cursos/" + idCurso + "/alumnos",
                    "nombreAlumno=" + ApiClient.enc(alumno));

            if (ra.status != 201) {
                System.out.println("Fallo añadiendo (" + ra.status + "): " + ra.body);
                return;
            }
        }

        System.out.println("Añadidos " + n + " alumnos correctamente.");
    }

    // 4) Eliminar curso y alumnos
    static void eliminarCurso(ApiClient api) throws Exception {
        System.out.print("Nombre del curso a eliminar: ");
        String nombre = sc.nextLine().trim();

        ApiClient.Response rc = api.get("/cursos?nombre=" + ApiClient.enc(nombre));
        if (rc.status != 200) {
            System.out.println("Curso no encontrado. (" + rc.status + ") " + rc.body);
            return;
        }

        int idCurso = new JSONObject(rc.body).getInt("idCurso");
        ApiClient.Response rd = api.delete("/cursos/" + idCurso);

        if (rd.status == 204) System.out.println("Curso eliminado (y alumnos en cascada).");
        else System.out.println("No se pudo eliminar (" + rd.status + "): " + rd.body);
    }

    static void verHistorial(ApiClient api) throws Exception {
    ApiClient.Response r = api.get("/historial");

    if (r.status != 200) {
        System.out.println("Error: " + r.body);
        return;
    }

    System.out.println("=== HISTORIAL ===");
    System.out.println(r.body);
}
}