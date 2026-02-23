package Main;

import LOGICA.GestorEmpresaHBConsultas;

public class Main {

    public static void main(String[] args) {
        // EJER 1 (CORRECTO)
        System.out.println("=========== EJER1 ===========");
        GestorEmpresaHBConsultas.visualizarProxectos();
        GestorEmpresaHBConsultas.visualizarProxectosArray();
        GestorEmpresaHBConsultas.visualizarProxectosDTO();

        //EJER 2 (CORRECTO FALTA APARTADO B Y C)
        System.out.println();
        System.out.println("======= EJER2 =============");
        GestorEmpresaHBConsultas.visualizarEmpregadosOrdenados();
        GestorEmpresaHBConsultas.visualizarListadoOrdenadoDTOLOGICA();
        GestorEmpresaHBConsultas.visualizarListadoOrdenadoDTOLOGICA();

        // EJER 3 (CORRECTO)
        System.out.println();
        System.out.println("======= EJER3 =============");
        GestorEmpresaHBConsultas.listarDepartamentosNumEmpleados();

        // EJER 4 (CORRECTO)
        System.out.println();
        System.out.println("======= EJER4 =============");
        System.out.println("APARTADO A)");
        GestorEmpresaHBConsultas.mostrarProyectosPorEmpleado();
        System.out.println();
        System.out.println("APARTADDO B)");
        GestorEmpresaHBConsultas.mostrarEmpregadosConMaisDunProxecto();

        // EJER 5 (CORRECTO)
        System.out.println();
        System.out.println("======= EJER5 =============");
        GestorEmpresaHBConsultas.mostrarEmpregadosSenTelefono();

        // EJER 6 (CORRECTO)
        System.out.println();
        System.out.println("======= EJER6 =============");
        System.out.println("MÉTODO CON NÚMERO DE DEPARTAMENTO COMO PARÁMETRO");
        consultaProxectosDepartNumDepart();
        System.out.println();
        System.out.println("MÉTODO CON NOMBRE DE DEPARTAMENTO COMO PARÁMETRO");
        consultaProxectosDepartNombreDepart();

        // EJER 7 (CORRECTO)
        System.out.println();
        System.out.println("======= EJER7 =============");
        System.out.println("APARTADO D)");
        GestorEmpresaHBConsultas.mostrarDirectores();
        System.out.println();
        System.out.println("APARTADDO E)");
        GestorEmpresaHBConsultas.mostrarTotalSalariosDirectores();
        System.out.println("APARTADO F)");
        mostrarDepartamentosConSalarioSuperior();

        // EJER 8 (CORRECTO)
        System.out.println();
        System.out.println("======= EJER8 =============");
        GestorEmpresaHBConsultas.mostrarDepartamentosConMaisEmpregados();

        // EJER 9 (CORRECTO)
        System.out.println();
        System.out.println("======= EJER9 =============");
        System.out.println("APARTADO A)");
        GestorEmpresaHBConsultas.mostrarEmpregadosFixosQueGananMais();
        System.out.println();
        System.out.println("APARTADDO B)");
        GestorEmpresaHBConsultas.visualizarEmpregadosQueSuperanDirectorMax();
        System.out.println("APARTADO C)");
        visualizarVaronsSupervisoresPorRango();
        System.out.println();
        System.out.println("APARTADO D)");
        subirSalarioPorProxectos();


    }

    private static void consultaProxectosDepartNumDepart() {
        int numDepart = 1;

        GestorEmpresaHBConsultas.ConsultaProxectosDepart(numDepart);
    }

    private static void consultaProxectosDepartNombreDepart() {
       String nombreDepart = "PERSOAL";

        GestorEmpresaHBConsultas.ConsultaProxectosDepart(nombreDepart);
    }

    private static void mostrarDepartamentosConSalarioSuperior() {
        double cantidad = 3000;

        GestorEmpresaHBConsultas.mostrarDepartamentosConSalarioSuperior(cantidad);
    }

    private static void visualizarVaronsSupervisoresPorRango() {
        float limInf = 2000;
        float limSup = 4000;

        GestorEmpresaHBConsultas.visualizarVaronsSupervisoresPorRango(limInf, limSup);
    }

    private static void subirSalarioPorProxectos() {
        float porcentaje = 10;

        GestorEmpresaHBConsultas.subirSalarioPorProxectos(porcentaje);
    }
}
