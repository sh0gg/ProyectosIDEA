package LOGICA;

import DTO.EmpregadoListadoDepartamentoDTO;
import DTO.EmpregadoProxectoDTO;
import DTO.EmpregadoSupervisorDTO;
import PERSITENCIA.EmpresaHBDAO;
import POJOS.Departamento;
import POJOS.Empregado;
import POJOS.Familiar;
import POJOS.Lugar;
import POJOS.Proxecto;
import POJOS.ProxectoFase;
import POJOS.Vehiculo;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;

public class GestorEmpresaHB {

    public static void comprobarConexion() {
        int resultado = EmpresaHBDAO.conectarHibernateDAO();

        if (resultado == 0) {
            System.out.println("Conexi�n correcta");

        } else {
            System.out.println("Error de conexi�n ");

        }
    }

    public static void visualizarProxecto(int proxecto) {
        try {
            Proxecto p = EmpresaHBDAO.buscarProxectoDAO(proxecto);

            if (p == null) {
                System.out.println("No existe el proyecto con c�digo " + proxecto);
            } else {
                System.out.println("Proyecto encontrado:");
                System.out.println("N�mero: " + p.getNumProxecto());
                System.out.println("Nombre: " + p.getNomeProxecto());
            }

        } catch (RuntimeException e) {
            System.out.println("Error de acceso a la base de datos: " + e.getMessage());
        }
    }

    /*
  NOTA   :  CAMBI� FECHADENACIMEINTO EN LOS POJOS Y EN EL FICHERO HBM.XML POR localdate
     */
    public static void altaEmpregado(Empregado empregado) {

        // Validaci�ns b�sicas
        if (empregado.getNss() == null || empregado.getNss().isEmpty()) {
            System.out.println("Erro: o NSS non pode estar baleiro");
            return;
        }
        if (empregado.getNome() == null || empregado.getNome().isEmpty()) {
            System.out.println("Erro: o nome non pode estar baleiro");
            return;
        }
        if (empregado.getApelido1() == null || empregado.getApelido1().isEmpty()) {
            System.out.println("Erro: o primeiro apelido non pode estar baleiro");
            return;
        }
        if (empregado.getDataNacemento() == null || empregado.getDataNacemento().isAfter(LocalDate.now())) {
            System.out.println("Erro: a data de nacemento � inv�lida");
            return;
        }
        if (empregado.getSexo() == null || (empregado.getSexo() != 'H' && empregado.getSexo() != 'M')) {
            System.out.println("Erro: o sexo debe ser 'H' ou 'M'");
            return;
        }

        // Chamada ao DAO
        try {
            EmpresaHBDAO.guardarEmpregadoDAO(empregado);
            System.out.println("Empregado creado correctamente.");
        } catch (RuntimeException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public static void buscarEmpregado(String nss) {
        try {
            Empregado e = EmpresaHBDAO.buscarEmpregadoDAO(nss);
            if (e != null) {
                System.out.println("Empleado encontrado: " + e.getNome() + " " + e.getApelido1());
            } else {
                System.out.println("Empleado no encontrado");
            }
        } catch (RuntimeException e) {
            System.out.println("Error al buscar empleado: " + e.getMessage());
        }
    }

    public static void altaFuncionDept(int numDepartamento, String funcion) {
        try {
            EmpresaHBDAO.guardarFuncionDeptDAO(numDepartamento, funcion);
            System.out.println("Funci�n engadida correctamente.");
        } catch (RuntimeException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public static void eliminarFuncionDepartamento(int numDepartamento, String funcion) {

        if (funcion == null || funcion.isBlank()) {
            System.out.println("Erro: a funci�n non pode estar baleira.");
            return;
        }

        try {
            EmpresaHBDAO.eliminarFuncionDeptDAO(numDepartamento, funcion);
            System.out.println("Funci�n eliminada correctamente do departamento.");

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void engadirFaseProxecto(int numProxecto, ProxectoFase fase) {
        try {
            boolean actualizado = EmpresaHBDAO.engadirFaseProxectoDAO(numProxecto, fase);
            if (actualizado) {
                System.out.println("Fase actualizada correctamente no proxecto " + numProxecto + ".");
            } else {
                System.out.println("Fase engadida correctamente ao proxecto " + numProxecto + ".");
            }
        } catch (RuntimeException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public static void engadirOuActualizarTelefono(String nss, String numero, String tipo) {

        try {
            Boolean actualizado = EmpresaHBDAO.engadirTelefonoDAO(nss, numero, tipo);
            if (actualizado == null) {
                System.out.println("O empregado co NSS " + nss + " non existe.");
                return;
            }

            if (actualizado) {
                System.out.println("Tel�fono " + numero + " do empregado " + nss + " actualizado correctamente.");
            } else {
                System.out.println("Tel�fono " + numero + " do empregado " + nss + "engadido correctamente.");
            }

        } catch (RuntimeException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public static void borrarTelefono(String nss, String numero) {
        try {
            Boolean borrado = EmpresaHBDAO.borrarTelefonoDAO(nss, numero);
            if (borrado == null) {
                System.out.println("O empregado co NSS " + nss + " non existe.");
                return;
            }
            if (!borrado) {
                System.out.println("O tel�fono " + numero + " non existe para o empregado " + nss + ".");
                return;
            }
            System.out.println("Tel�fono " + numero + " borrado correctamente do empregado " + nss + ".");

        } catch (RuntimeException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public static void crearFamiliar(String nssEmpregado, Familiar familiar) {
        Boolean resultado = EmpresaHBDAO.engadirFamiliarDAO(nssEmpregado, familiar);

        if (resultado == null) {
            System.out.println("ERROR: O empregado co NSS " + nssEmpregado + " non existe.");
        } else if (!resultado) {
            System.out.println("ERROR: Xa existe un familiar co NSS " + familiar.getNss() + " para el  empregado " + nssEmpregado);
        } else {
            System.out.println("Familiar " + familiar.getNss() + " engadido correctamente ao empregado " + nssEmpregado + ".");
        }
    }
    // M�todo que obtiene todos los empleados de una localidad y los imprime

    public static void mostrarEmpregadosPorLocalidade(String localidade) {
        if (localidade == null || localidade.isBlank()) {
            System.out.println("Localidade non v�lida.");
            return;
        }

        try {
            List<Empregado> empregados = EmpresaHBDAO.obterEmpregadosPorLocalidadeDAO(localidade);

            if (empregados.isEmpty()) {
                System.out.println("Non hai empregados na localidade: " + localidade);
            } else {
                System.out.println("Empregados en " + localidade + ":");
                for (Empregado e : empregados) {
                    String apelido2 = e.getApelido2() != null ? e.getApelido2() : "";
                    System.out.println(e.getNss() + " - " + e.getNome() + " " + e.getApelido1() + " " + apelido2);
                }
            }

        } catch (RuntimeException ex) {
            System.out.println("Erro ao consultar empregados: " + ex.getMessage());
        }
    }

    public static void crearEmpregadoConDepartamento(Empregado empregado, String nomeDepartamento) {

        int resultado = EmpresaHBDAO.crearEmpregadoConDepartamentoDAO(empregado, nomeDepartamento);

        switch (resultado) {
            case 0 -> System.out.println("Empleado creado correctamente en el departamento " + nomeDepartamento);
            case -1 -> System.out.println("ERROR: El empleado con NSS " + empregado.getNss() + " ya existe.");
            case -2 -> System.out.println("ERROR: El departamento " + nomeDepartamento + " no existe.");
            default -> System.out.println("ERROR inesperado al crear el empleado.");
        }
    }
    public static void cambiarDepartamentoEmpregado(
            String nssEmpregado, int numDepartamento) {

        int resultado = EmpresaHBDAO
                .cambiarDepartamentoEmpregadoHQL(nssEmpregado, numDepartamento);

        switch (resultado) {
            case 0 ->
                    System.out.println("Departamento do empregado actualizado correctamente.");
            case -1 ->
                    System.out.println("ERROR: O empregado non existe.");
            case -2 ->
                    System.out.println("ERROR: O departamento non existe.");
            case -3 ->
                    System.out.println("O empregado xa pertence a ese departamento.");
            default ->
                    System.out.println("Erro inesperado ao cambiar o departamento.");
        }
    }

    public static void gardarOuActualizarVehiculo(String nss, Vehiculo v) {

        try {
            int r = EmpresaHBDAO.gardarOuActualizarVehiculoDAO(nss, v);

            switch (r) {
                case 0 -> System.out.println("Veh�culo gardado/actualizado correctamente para o empregado " + nss + ".");

                case -1 -> System.out.println("Non existe ning�n empregado co NSS " + nss + ".");

                case -2 -> System.out.println("A matr�cula '" + v.getMatricula() + "' xa est� rexistrada noutro veh�culo.");

                default -> System.out.println("Produciuse un erro ao gardar/actualizar o veh�culo.");
            }

        } catch (HibernateException e) {
            System.out.println("Erro inesperado ao gardar ou actualizar o veh�culo.");
        }
    }

    public static void borrarVehiculo(String nss) {

        try {
            int r = EmpresaHBDAO.borrarVehiculoDAO(nss);

            switch (r) {
                case 0 -> System.out.println("Veh�culo eliminado correctamente para o empregado con nss" + nss);

                case -1 -> System.out.println("O empregado con nss" + nss + "non ten ning�n vehiculo asociado");

                case -2 -> System.out.println("Non existe ning�n empregado con nss" + nss);

            }

        } catch (HibernateException e) {
            System.out.println("Erro inesperado ao gardar ou actualizar o veh�culo.");
        }

    }

    public static void listarEmpleadosProxectoDAO() {

        List<EmpregadoProxectoDTO> empregadosProxecto = EmpresaHBDAO.listarEmpleadosProxectoDAO();

        for (EmpregadoProxectoDTO e : empregadosProxecto) {
            System.out.println(e);
        }

    }

    public static void listarEmpleadosProxectoArrayDAO() {

        List<Object[]> empregadosProxecto = EmpresaHBDAO.listarEmpleadosProxectoArrayDAO();

        for (Object e : empregadosProxecto) {
            System.out.println(e);
        }

    }

    public static void borrarProxecto(String nomeProxecto) {

        try {
            boolean r = EmpresaHBDAO.borrarProxectoPorNome(nomeProxecto);

            if (r)
                System.out.println("O proxecto" + nomeProxecto + "ha sido borrado correctamente");
            else
                System.out.println("No se ha podido borrar o proxecto" + nomeProxecto);


        } catch (HibernateException e) {
            System.out.println("Erro inesperado ao gardar ou actualizar o veh�culo.");
        }

    }


    public static void quitarSupervisor(String nss) {
        try {
            String msg = EmpresaHBDAO.quitarSupervisorDAO(nss);

            System.out.println(msg);


        } catch (HibernateException e) {
            System.out.println("Erro inesperado ao gardar ou actualizar o veh�culo.");
        }
    }


    public static void consultarEmpregadoSupervisorHQL(String nss) {
        try {
            EmpregadoSupervisorDTO empregadoSupervisor = EmpresaHBDAO.consultarEmpregadoSupervisorHQLDAO(nss);

            System.out.println(empregadoSupervisor);


        } catch (HibernateException e) {
            System.out.println("Erro inesperado ao gardar ou actualizar o veh�culo.");
        }
    }

    public static void consultarEmpregadoSupervisor(String nss) {
        try {
            String msg = EmpresaHBDAO.consultarEmpregadoSupervisorDAO(nss);

            System.out.println(msg);


        } catch (HibernateException e) {
            System.out.println("Erro inesperado ao gardar ou actualizar o veh�culo.");
        }
    }

    public static void consultarEmpregadosDeUNSupervisor(String nssSupervisor) {
        List<Empregado> empregadosDeUnSupervisor = EmpresaHBDAO.consultarEmpregadosDeUNSupervisorDAO(nssSupervisor);

        for (Empregado e : empregadosDeUnSupervisor) {
            System.out.println(e);
        }
    }

    public static void listarEmpleadosTipos() {
        List<EmpregadoListadoDepartamentoDTO> empregadosPorTipo = EmpresaHBDAO.listarEmpleadosTiposDAO();

        for (EmpregadoListadoDepartamentoDTO e : empregadosPorTipo) {
            System.out.println(e);
        }
    }

    public static void insertarEmpregado(Empregado emp, String nomeDepto) {
        try {
            int r = EmpresaHBDAO.insertarEmpregadoDAO(emp, nomeDepto);

            switch (r) {
                case 0 -> System.out.println("Empregado insertado correctamente");

                case -1 -> System.out.println("O empregado con nss" + emp.getNss() + " xa existe");

                case -2 -> System.out.println("O departamento " + nomeDepto + " non existe");

            }

        } catch (HibernateException e) {
            System.out.println("Erro inesperado ao inserir o empregado.");
        }

    }

    public static void insertarModificarHorasExtras(String nss, LocalDate fecha, double horas) {
        try {
            int r = EmpresaHBDAO.insertarModificarHorasExtras(nss, fecha, horas);

            switch (r) {
                case 0 -> System.out.println("Horas extras insertadas para o empregado con nss" + nss);

                case 1 -> System.out.println("Horas extras actualizadas para o empregado con nss" + nss);

                case -1 -> System.out.println("Non existe o empregado con nss" + nss);

                case -2 -> System.out.println("O empregado con nss " + nss + " non est� fixo");

                case -3 -> System.out.println("Error insertando o modificando horas extras");

            }

        } catch (HibernateException e) {
            System.out.println("Erro inesperado ao inserir o empregado.");
        }

    }

    public static void eliminarHorasExtras(String nss, LocalDate fecha) {
        try {
            int r = EmpresaHBDAO.eliminarHorasExtras(nss, fecha);

            switch (r) {

                case 1 -> System.out.println("Empregado eliminado correctamente");

                case -1 -> System.out.println("Non existe o empregado con nss" + nss);

                case -2 -> System.out.println("Non hai horas extras nesas fechas");

                case -3 -> System.out.println("Error general");

            }

        } catch (HibernateException e) {
            System.out.println("Erro inesperado ao inserir o empregado.");
        }

    }

    public static void totalHorasExtrasMesEmpregado(String nss, int mes, int ano) {
        try {
            double r = EmpresaHBDAO.totalHorasExtrasMesDAO(nss, mes, ano);

            if (r == -1.0) {
                System.out.println("Non existe o empregado con nss " + nss);
            } else if (r == -2.0) {
                System.out.println("O empregado con nss " + nss + " non est� fixo");
            } else if (r == 0.0) {
                System.out.println("Non hai rexistros para este empregado");
            } else if (r == -3.0) {
                System.out.println("Erro t�cnico");
            } else {

                System.out.println("Este empregado conta con " + r + " horas extras este mes");
            }


        } catch (HibernateException e) {
            System.out.println("Erro inesperado ao inserir o empregado.");
        }

    }

    public static void  obtenerNombresLugares(String nomeDepto) {
        List<String> lugares = EmpresaHBDAO.obtenerNombresLugaresDAO(nomeDepto);

        if (lugares == null) {
            System.out.println("Non existe ning�n departamento con ese nome");
        }

        lugares.forEach(l -> {System.out.println(l);});
    }

    public static void insertarDepartamentoCompleto(Departamento dpto, Set<String> lugares,
                                                    Set<String> funciones, String nssDirector) {
        try {
            int r = EmpresaHBDAO.insertarDepartamentoCompleto(dpto, lugares, funciones, nssDirector);

            switch (r) {

                case 0 -> System.out.println("Departamento insertado correctamente");

                case -1 -> System.out.println("Non existe o director con nss" + nssDirector);

                case -2 -> System.out.println("Xa existe un departamento con ese nome");

                case -3 -> System.out.println("O director xa � director noutro departamento");

                case -4 -> System.out.println("Error general");

            }

        } catch (HibernateException e) {
            System.out.println("Erro inesperado ao inserir o empregado.");
        }
    }
}




