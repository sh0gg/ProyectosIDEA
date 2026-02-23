
package LOGICA;
import DTO.DepartamentoMaxEmpregadosDTO;
import DTO.DirectorDepartamentoDTO;
import DTO.EmpregadoListadoDTO;
import DTO.ListadoProxectosDTO;
import PERSITENCIA.EmpresaHBConsultasDAO;
import POJOS.Empregado;
import POJOS.Empregadofixo;
import POJOS.Proxecto;
import java.util.List;

public class GestorEmpresaHBConsultas {
    //Ejercicio 1
    //Version A  devolviendo una lista de clase asociada
    public static void visualizarProxectos() {

    List<Proxecto> lista = EmpresaHBConsultasDAO.listarProxectosDAO();

    System.out.printf("%-10s %-25s %-15s%n", "N�mero", "Nome", "Lugar");

    for (Proxecto p : lista) {
        System.out.printf("%-10s %-25s %-15s%n",
                p.getNumProxecto(), p.getNomeProxecto(), p.getLugar());
    }
}
    //Version B
    public static void visualizarProxectosArray() {

    List<Object[]> lista = EmpresaHBConsultasDAO.listarProxectosArrayDAO();

    System.out.printf("%-10s %-25s %-15s%n", "N�mero", "Nome", "Lugar");

    for (Object[] fila : lista) {
        System.out.printf("%-10s %-25s %-15s%n",
                fila[0], fila[1], fila[2]);
    }
}
    //Versi�n C
 public static void visualizarProxectosDTO() {

    List<ListadoProxectosDTO> lista = EmpresaHBConsultasDAO.listarProxectosDTODAO();

    System.out.printf("%-10s %-25s %-15s%n", "N�mero", "Nome", "Lugar");

    for (ListadoProxectosDTO dto : lista) {
        System.out.printf("%-10s %-25s %-15s%n",
                dto.getNumero(), dto.getNome(), dto.getLugar());
    }
}
 //EJERCICIO 2
 public static void visualizarEmpregadosOrdenados() {

    List<Empregado> lista = EmpresaHBConsultasDAO.listarEmpregadosOrdenadosDAO();

    if (lista == null || lista.isEmpty()) {
        System.out.println("Non hai empregados.");
        return;
    }

    System.out.printf("%-10s %-30s %-15s %-15s %-15s%n",
            "NSS", "Nome Completo", "Departamento", "Tipo empregado", "num. tel�fonos");

    for (Empregado e : lista) {
        String nomeCompleto = e.getApelido1() + " " +
                              (e.getApelido2() != null ? e.getApelido2() + ", " : ", ") + e.getNome();
        
        String tipo = (e instanceof Empregadofixo) ? "fijo" : "temporal";
        int numTelf = e.getTelefonos() != null ? e.getTelefonos().size() : 0;
        System.out.printf("%-10s %-30s %-15s %-15s %-15s%n",
                e.getNss(), nomeCompleto, e.getDepartamento() != null ? e.getDepartamento().getNomeDepartamento() : "?",
                tipo, numTelf == 0 ? "ninguno" : numTelf);
    }
}


public static void visualizarListadoOrdenadoDTOLOGICA() {
    List<EmpregadoListadoDTO> lista = EmpresaHBConsultasDAO.listarEmpregadosOrdenadosDTODAO();
    if (lista == null || lista.isEmpty()) {
        System.out.println("No se encontraron empleados con departamento asignado.");
        return;
    }

    System.out.println("---------------------------------------------------------------------------------------------------");
    System.out.printf("%-10s %-30s %-20s %-15s %-15s\n", 
                      "NSS", "Nombre Completo", "Departamento", "Tipo empleado", "num. de tel�fonos");
    System.out.println("---------------------------------------------------------------------------------------------------");

    for (EmpregadoListadoDTO emp : lista) {
        // Como el departamento es obligatorio, lo mostramos siempre en may�sculas para que resalte
        System.out.printf("%-10s %-30s %-20s %-15s %-15s\n",
                emp.getNss(),
                emp.getNomeCompleto(),
                emp.getDepartamento().toUpperCase(), 
                emp.getTipoEmpregado(),
                (emp.getNumTelefonos() == 0) ? "ninguno" : emp.getNumTelefonos());
    }
}
public static void visualizarListadoOrdenadoObjectLOGICA() {
    List<Object[]> lista =EmpresaHBConsultasDAO.listarEmpregadosOrdenadosObjectDAO();

    if (lista == null || lista.isEmpty()) {
        System.out.println("Non hai datos para amosar.");
        return;
    }

    System.out.println("---------------------------------------------------------------------------------------------------");
    System.out.printf("%-10s %-35s %-20s %-12s %-10s\n", 
                      "NSS", "Nombre Completo", "Departamento", "Tipo", "Telef.");
    System.out.println("---------------------------------------------------------------------------------------------------");

    for (Object[] fila : lista) {
        // Casting manual de los objetos
        String nss = (String) fila[0];
        String nombre = (String) fila[1];
        String depto = (String) fila[2];
        String tipo = (String) fila[3];
        int numTlf = (int) fila[4];

        System.out.printf("%-10s %-35s %-20s %-12s %-10s\n",
                nss, nombre,  depto.toUpperCase(), tipo,  (numTlf == 0) ? "ninguno" : numTlf);
    }
}

//EJERCCIO 3

public static void listarDepartamentosNumEmpleados() {

    List<Object[]> lista = EmpresaHBConsultasDAO.listarDepartamentosNumEmpleadosDAO();
       System.out.printf("%-10s %-35s %-20s\n",
            "NUMERO", "NOME DEPARTAMENTO", "NUM EMPREGADOS");

    System.out.println("-----------------------------------------------------------------------");

    // Filas
    for (Object[] fila : lista) {
        int num = (int) fila[0];
        String nome = (String) fila[1];
        Integer numEmp = (Integer) fila[2]; // size() devolve Integer

        System.out.printf("%-10d %-35s %-20d\n", num, nome, numEmp);
    }
}
//EJERCICIO 4
public static void mostrarProyectosPorEmpleado() {
    List<Object[]> lista = EmpresaHBConsultasDAO.listarProyectosPorEmpleadoDAO();

    for (Object[] fila : lista) {
        String nss = (String) fila[0];
        String proxecto = (String) fila[1];

        if (proxecto == null) {
            System.out.println("NSS:" + nss + "  no tiene proxectos");
        } else {
            System.out.println("NSS:" + nss + "  " + proxecto);
        }
    }
}
public static void mostrarEmpregadosConMaisDunProxecto() {

    List<Object[]> lista = EmpresaHBConsultasDAO.listarEmpregadosConMaisDunProxectoDAO();

    for (Object[] fila : lista) {
        String nss = (String) fila[0];
        String proxecto = (String) fila[1];

        System.out.println("NSS:" + nss + "  " + proxecto);
    }
}
//Logica
public static void mostrarEmpregadosSenTelefono() {

    List<Empregado> lista = EmpresaHBConsultasDAO.empregadosSenTelefonoDAO();

    for (Empregado e : lista) {
        System.out.println("NSS: " + e.getNss() + "  " + e.getNome() + " " + e.getApelido1());
    }
}
//Ejercicio 6
public static void ConsultaProxectosDepart(int numDept) {

    List<Proxecto> lista = EmpresaHBConsultasDAO.consultaProxectosDepartDAO(numDept);

    if (lista.isEmpty()) {
        System.out.println("O departamento " + numDept + " non controla proxectos.");
        return;
    }

    System.out.println("Proxectos do departamento " + numDept + ":");
    lista.forEach(p -> System.out.println(" - " + p.getNomeProxecto()));
}
public static void ConsultaProxectosDepart(String nomeDept) {

    List<Proxecto> lista = EmpresaHBConsultasDAO.consultaProxectosDepartDAO(nomeDept);

    if (lista.isEmpty()) {
        System.out.println("O departamento '" + nomeDept + "' non controla proxectos.");
        return;
    }

    System.out.println("Proxectos do departamento '" + nomeDept + "':");
    lista.forEach(p -> System.out.println(" - " + p.getNomeProxecto()));
}
/*eXERCICO 7
D
    M�TODO mostrarDirectores:  Recibe a lista de DTOs xerada polo DAO e imprime a informaci�n
    nun formato tabulado.
    Se o supervisor � NULL (porque o LEFT JOIN non atopou supervisor),
    m�strase "(sen supervisor)".
*/
public static void mostrarDirectores() {
    List<DirectorDepartamentoDTO> lista = EmpresaHBConsultasDAO.listarDirectoresDAO();

    System.out.printf("%-12s %-30s %-30s %-10s %-20s%n",
            "NSS", "Director", "Supervisor", "Salario", "Departamento");
    for (DirectorDepartamentoDTO dto : lista) {
        String supervisor = dto.getNomeCompletoSupervisor();
        if (supervisor == null) supervisor = "";
        System.out.printf("%-12s %-30s %-30s %-10.2f %-20s%n",
                dto.getNssDirector(), dto.getNomeCompletoDirector(), supervisor,
                dto.getSalario(), dto.getNomeDepartamento());
    }
}

public static void mostrarTotalSalariosDirectores() {

    Double total = EmpresaHBConsultasDAO.totalSalariosDirectoresDAO();
    System.out.println("Total sueldo de los directores " + total);
}

public static void mostrarDepartamentosConSalarioSuperior(double cantidade) {

    List<Object[]> lista = EmpresaHBConsultasDAO.departamentosConSalarioSuperiorDAO(cantidade);
    System.out.printf("%-10s %-20s %-25s %-25s%n",
            "Numero", "Nombre", "num de empleados fijos", "total sueldo empleados fijos");

    for (Object[] fila : lista) {
        Integer num = (Integer) fila[0];
        String nome = (String) fila[1];
        Long numEmp = (Long) fila[2];
        Double total = (Double) fila[3];
        System.out.printf("%-10d %-20s %-25d %-25.2f%n", num, nome, numEmp, total);
    }
}
public static void mostrarDepartamentosConMaisEmpregados() {

    List<DepartamentoMaxEmpregadosDTO> lista =
            EmpresaHBConsultasDAO.departamentosConMaisEmpregadosDAO();

    System.out.printf("%-15s %-20s %-30s%n",
            "Departamento", "NumeroEmpleados", "Director");
    System.out.println("-----------------------------------------------------------");

    for (DepartamentoMaxEmpregadosDTO d : lista) {
        System.out.printf("%-15s %-20d %-30s%n",
        d.getNomeDepartamento(),  d.getNumeroEmpregados(),  d.getDirector());
    }
}
//exercico 9
//A

  public static void mostrarEmpregadosFixosQueGananMais() {

    List<Object[]> lista = EmpresaHBConsultasDAO.empregadosFixosQueGananMaisDAO();

    System.out.printf("%-12s %-30s %-20s %-10s%n",
            "NSSEmpleado", "Nombre", "Departamento", "Salario");
    System.out.println("--------------------------------------------------------------------------");

    for (Object[] fila : lista) {
        System.out.printf("%-12s %-30s %-20s %-10.2f%n",
                fila[0], fila[1], fila[2], fila[3]);
    }
}

//b
  public static void visualizarEmpregadosQueSuperanDirectorMax() {
    List<Object[]> resultados = EmpresaHBConsultasDAO.empregadosQueGananMaisQueDirectorMaxDAO();
    if (resultados.isEmpty()) {
        System.out.println("Non se atoparon empregados que ga�en m�is que o director que m�is ga�a.");
        return;
    }

    System.out.printf("%-15s %-35s %-20s %-10s%n", "NSSEmpleado", "Nombre", "Departamento", "Salario");
    System.out.println("------------------------------------------------------------------------------------------");

    for (Object[] fila : resultados) {
        System.out.printf("%-15s %-35s %-20s %-10.1f%n", 
                          fila[0], fila[1], fila[2], fila[3]);
    }
}
  //C
  public static void visualizarVaronsSupervisoresPorRango(float limInf, float limSup) {
  
    List<Object[]> resultados = EmpresaHBConsultasDAO.empregadosVaronsSupervisoresEntreSalariosDAO(limInf, limSup);

    if (resultados.isEmpty()) {
        System.out.printf("Non se atoparon var�ns supervisores con salario entre %.2f e %.2f.%n", limInf, limSup);
        return;
    }

    System.out.println("\nLISTADO DE VAR�NS SUPERVISORES (Rango: " + limInf + " - " + limSup + ")");
    System.out.printf("%-15s %-35s %-20s %-10s%n", "NSS", "Nome Completo", "Departamento", "Salario");
    System.out.println("------------------------------------------------------------------------------------------");

    for (Object[] fila : resultados) {
        System.out.printf("%-15s %-35s %-20s %-10.1f%n", 
                          fila[0], // NSS
                          fila[1], // Nome (concatenado no HQL)
                          fila[2], // Nome Departamento
                          fila[3]);// Salario
    }
}
  //D
  public static void subirSalarioPorProxectos(float porcentaxe) {
    int actualizados = EmpresaHBConsultasDAO.subirSalarioEmpregadosConMaisProxectosDAO(porcentaxe);
    
    if (actualizados > 0) {
        System.out.printf("Subida do %.1f%% aplicada con �xito a %d empregado(s).%n", porcentaxe, actualizados);
    } else if (actualizados == 0) {
        System.out.println("Non se atoparon empregados para actualizar.");
    } else {
        System.out.println("Houbo un erro na operaci�n.");
    }
}
  
}
