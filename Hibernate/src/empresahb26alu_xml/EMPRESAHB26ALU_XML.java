/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package empresahb26alu_xml;

import LOGICA.GestorEmpresaHB;
import POJOS.Empregado;
import POJOS.Enderezo;
import POJOS.Familiar;
import POJOS.Fase;

import java.text.ParseException;
import java.time.LocalDate;


public class EMPRESAHB26ALU_XML {

    public static void main(String[] args) throws ParseException {
        GestorEmpresaHB.comprobarConexion();
        GestorEmpresaHB.visualizarProxecto(1);
        crearEmpleado();
        altaFuncionDepartamento();
        bajaFuncionDepartamento();
        probarEngadirFase();
        EngadirTelefonEmpregado();
        BorrarTelefonEmpregado();
        EngadirFamiliarEmpregado();
        EmpregadoporLocalidad();
        crearEmpleadoDept();
        cambiarDeptEmpleado();
    }

    public static void crearEmpleado() {
        Enderezo enderezo = new Enderezo(
                "Calle Falsa", // rua
                123, // numeroCalle
                "1A", // piso
                "15001", // cp
                "Santiago", // localidade
                "A Coruña" // provincia
        );

        // Creamos el empleado usando el constructor (asumiendo que tienes un constructor que acepte todos los campos)
        Empregado novoEmpregado = new Empregado(
                "00000067", // nss
                "Luis", // nome
                "Souto", // apelido1
                "Real", // apelido2
                LocalDate.of(2000, 1, 15), // dataNacemento
                'H' // sexo
        );
        novoEmpregado.setEnderezo(enderezo);
        GestorEmpresaHB.altaEmpregado(novoEmpregado);

    }

    private static void altaFuncionDepartamento() {
        int numDepartamento = 999;
        String funcion = "Mantenimiento";
        GestorEmpresaHB.añadirFuncionDepartamento(numDepartamento, funcion);

    }

    private static void bajaFuncionDepartamento() {
        int numDepartamento = 999;
        String funcion = "Mantenimiento";
        GestorEmpresaHB.eliminarFuncionDepartamento(numDepartamento, funcion);

    }

    private static void probarEngadirFase() {
        // Creamos unha fase
        Fase fase1 = new Fase();
        fase1.setNombre("Analise");
        fase1.setEstado("En proceso");
        // Chamamos ? l?xica
        GestorEmpresaHB.engadirFaseProxecto(1000, fase1);
        // Segunda fase para probar
        Fase fase2 = new Fase();
        fase2.setNombre("Programacion");
        fase2.setEstado("Rematada");
        GestorEmpresaHB.engadirFaseProxecto(2, fase2);

    }

    private static void EngadirTelefonEmpregado() {
        GestorEmpresaHB.engadirOuActualizarTelefono("1111111", "986454565", "FIJO CASA");
        GestorEmpresaHB.engadirOuActualizarTelefono("1111111", "647885588", "MOVIL TRABAJO");
        GestorEmpresaHB.engadirOuActualizarTelefono("11111119", "647885588", "MOVIL PARTICULAR");

    }

    private static void BorrarTelefonEmpregado() {
        GestorEmpresaHB.borrarTelefono("111111199", "986454565");
        GestorEmpresaHB.borrarTelefono("1111111", "986454565");

    }

    private static void EngadirFamiliarEmpregado() {
        // Empleado con familiares
        GestorEmpresaHB.crearFamiliar("1111111", new Familiar("1234567", "AnaBel", "Vázquez", "López",
                LocalDate.of(2010, 5, 12), "Filla", 'M'));
        //Empleado sin familiares
        GestorEmpresaHB.crearFamiliar("7777777", new Familiar("1456432", "Carmen", "Elez", "López",
                LocalDate.of(1990, 9, 10), "Muller", 'M'));
        //nss no existente
        GestorEmpresaHB.crearFamiliar("1111111988", new Familiar("1234527", "Pili", "Vázquez", "López",
                LocalDate.of(2010, 5, 12), "Filla", 'M'));

    }

    private static void EmpregadoporLocalidad() {
        // Probar empleados de la localidad "Vigo"
        GestorEmpresaHB.mostrarEmpregadosPorLocalidade("Vigo");

        // Probar localidad que no existe
        GestorEmpresaHB.mostrarEmpregadosPorLocalidade("Plasencia");
    }

    public static void crearEmpleadoDept() {
        Enderezo enderezo = new Enderezo(
                "Calle Sol", // rua
                1, // numeroCalle
                "A", // piso
                "15001", // cp
                "Santiago", // localidade
                "A Coru?a" // provincia
        );

        // Creamos el empleado usando el constructor (asumiendo que tienes un constructor que acepte todos los campos)
        Empregado novoEmpregado = new Empregado(
                "00000080", // nss
                "Ana", // nome
                "Souto", // apelido1
                "Santiago", // apelido2
                LocalDate.of(2000, 1, 15), // dataNacemento
                'M' // sexo
        );
        novoEmpregado.setEnderezo(enderezo);
        GestorEmpresaHB.crearEmpregadoConDepartamento(novoEmpregado, "PERSOAL");

    }

    public static void cambiarDeptEmpleado() {
        GestorEmpresaHB.cambiarDepartamentoEmpregado("00000067", 1);
    }


}


