package MAIN;

import LOGICA.GestorEmpresaHB;
import POJOS.Departamento;
import POJOS.Empregado;
import POJOS.Enderezo;
import POJOS.Familiar;
import POJOS.Lugar;
import POJOS.ProxectoFase;
import POJOS.Vehiculo;
import Utilidades.HibernateUtil;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class Main {

    public static void main(String[] args) throws ParseException {
        GestorEmpresaHB.comprobarConexion();
//          GestorEmpresaHB.visualizarProxecto(1);
//        GestorEmpresaHB.buscarEmpregado("1111111");
//        crearEmpleado();
//         AltaFuncionDepartamento();
//         bajaFuncionDepartamento();
//         probarEngadirFase();
//          EngadirTelefonEmpregado();
//          BorrarTelefonEmpregado();
//            EngadirFamiliarEmpregado();
//            EmpregadoporLocalidad();
//        crearEmpleadoDept();
//        cambiarDeptEmpleado();
//        almacenarVehiculoEmpregado();
//    totalHorasExtrasMesEmpregado();
           obtenerNombresLugares();
          insertarDepartamento();
    }

    public static void crearEmpleado() {
        Enderezo enderezo = new Enderezo(
                "Calle Falsa", // rua
                123, // numeroCalle
                "1A", // piso
                "15001", // cp
                "Santiago", // localidade
                "A Coru�a" // provincia
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

    private static void AltaFuncionDepartamento() {
        int numDepartamento = 144;
        String funcion = "Xesti�n de persoal";
        GestorEmpresaHB.altaFuncionDept(numDepartamento, funcion);

    }

    private static void bajaFuncionDepartamento() {
        int numDepartamento = 1;
        String funcion = "Xesti�n de persoal";
        GestorEmpresaHB.eliminarFuncionDepartamento(numDepartamento, funcion);

    }

    private static void probarEngadirFase() {
        // Creamos unha fase
        ProxectoFase fase1 = new ProxectoFase();
        fase1.setNomeFase("An�lise");
        fase1.setEstado("En proceso");
        // Chamamos � l�xica
        GestorEmpresaHB.engadirFaseProxecto(1000, fase1);
        // Segunda fase para probar
        ProxectoFase fase2 = new ProxectoFase();
        fase2.setNomeFase("programaci�n");
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
        GestorEmpresaHB.crearFamiliar("1111111", new Familiar("1234567", "AnaBel", "V�quez", "L�pez",
                LocalDate.of(2010, 5, 12), "Filla", 'M'));
        //Empleado sin familiares
        GestorEmpresaHB.crearFamiliar("7777777", new Familiar("1456432", "Carmen", "Elez", "L�pez",
                LocalDate.of(1990, 9, 10), "Muller", 'M'));
        //nss no existente
        GestorEmpresaHB.crearFamiliar("1111111988", new Familiar("1234527", "Pili", "V�quez", "L�pez",
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
                "A Coru�a" // provincia
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

    private static void almacenarVehiculoEmpregado() {

        // Crear o veh�culo que queremos gardar ou actualizar
        Vehiculo v = new Vehiculo();
        v.setMatricula("1234ABC");
        v.setMarca("Toyota");
        v.setModelo("Corolla");
        v.setDataCompra(LocalDate.now());

        // Chamada � l�xica (o main non imprime nada)
        GestorEmpresaHB.gardarOuActualizarVehiculo("5000000", v);
    }

    private static void totalHorasExtrasMesEmpregado() {

        String nss = "1111111"; // 1341431 no hay registros para este 0010010 y si para este 1231231

        GestorEmpresaHB.totalHorasExtrasMesEmpregado(nss, 5, 2017);
    }



    private static void obtenerNombresLugares() {
        String nomeDepto1 = "NovoDepartamento";
        String nomeDepto2 = "CONTABILIDAD";
/*
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("from Departamento where nomeDepartamento = :nome");
        q.setParameter("nome", nomeDepto2);
        Departamento depto = (Departamento) q.uniqueResult();

        Set<Lugar> lugares = new HashSet<>();
        Lugar lugar1 = new Lugar();
        lugar1.setLugar("Berlin");
        Lugar lugar2 = new Lugar();
        lugar2.setLugar("Malaga");
        lugares.add(lugar1);
        lugares.add(lugar2);

        depto.setLugares(lugares);

 */
        GestorEmpresaHB.obtenerNombresLugares(nomeDepto1);
        GestorEmpresaHB.obtenerNombresLugares(nomeDepto2);


    }


    private static void insertarDepartamento() {

        Departamento nuevo = new Departamento();
        nuevo.setNomeDepartamento("NuevoDepartamento"); // PERSOAL
        String nssDirector = "0010010"; // 0010010 o 5555  2525252
        Set<String> funciones = new HashSet<>();
        funciones.add("NUEVA FUNCI�N 1");
        funciones.add("NUEVA FUNCI�N 2");
        Set<String> lugares = new HashSet<>();
        lugares.add("Berlin");

        GestorEmpresaHB.insertarDepartamentoCompleto(nuevo, lugares, funciones, nssDirector);
    }



}
