package PERSITENCIA;

import DTO.DepartamentoMaxEmpregadosDTO;
import DTO.DirectorDepartamentoDTO;
import DTO.EmpregadoListadoDTO;
import DTO.ListadoProxectosDTO;
import POJOS.Empregado;
import POJOS.Proxecto;
import Utilidades.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmpresaHBConsultasDAO {

    //Ejercicio 1:  
    public static List<Proxecto> listarProxectosDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            from Proxecto p
            where p.lugar in ('Vigo', 'Santiago')
            order by p.numProxecto
        """;

            return session.createQuery(hql, Proxecto.class).list();
        }
    }

    /*
    VERSI�N 2 ? Persistencia devolviendo List<Object[]>
    DAO (SELECT parcial)
     */
    public static List<Object[]> listarProxectosArrayDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select p.numProxecto, p.nomeProxecto, p.lugar
            from Proxecto p
            where p.lugar in ('Vigo', 'Santiago')
            order by p.numProxecto
        """;

            return session.createQuery(hql, Object[].class).list();
        }
    }

    /*  VERSI�N 3 ? Persistencia devolviendo un DTO 
    DTO: ListadoProxectosDTO
    DAO (SELECT parcial) y  con constructor expression
     */
    public static List<ListadoProxectosDTO> listarProxectosDTODAO() {

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select new DTO.ListadoProxectosDTO(
                p.numProxecto,
                p.nomeProxecto,
                p.lugar
            )
            from Proxecto p
            where p.lugar in ('Vigo', 'Santiago')
            order by p.numProxecto
        """;

            return sesion.createQuery(hql, ListadoProxectosDTO.class).list();
        }
    }

    //EJERCICIO 2
    //vERSION  NO FUNCIONA ASI por que los telefonos se cargar�an fuera de la sesion y dar�a error
    public static List<Empregado> listarEmpregadosOrdenadosDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            /*
        esta hql no funcionar�a por que los telefonos nos los carga y al cerrrar la sesion al hacer 
        e.getTelefonos().size() en la logica, Pero telefonos es un Map lazy, as� que Hibernate intenta cargarlo?
         pero ya no hay sesi�n ? error.  */
//         String hql = """            
//            from Empregado e           
//            order by e.apelido1, e.apelido2, e.nome
//        """;
            //Utilizar join fetch esto ace que crge los telefonos antes de cerrar la sesion
            String hql = """
            select distinct e
            from Empregado e
            left join fetch e.telefonos
            left join fetch e.departamento
            order by e.apelido1, e.apelido2, e.nome
        """;

            return session.createQuery(hql, Empregado.class).list();
        }
    }

    public static List<EmpregadoListadoDTO> listarEmpregadosOrdenadosDTODAO() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
            select new DTO.EmpregadoListadoDTO(
                e.nss, concat(e.apelido1, ' ', coalesce(e.apelido2, ''), ', ', e.nome),
                e.departamento.nomeDepartamento, 
                case 
                    when type(e) = POJOS.Empregadofixo then 'fijo' 
                    else 'temporal' 
                end,
                size(e.telefonos)
            )
            from Empregado e inner join e.departamento d
            order by e.apelido1 asc, e.apelido2 asc, e.nome asc
            """;

            return session.createQuery(hql, EmpregadoListadoDTO.class).list();
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Object[]> listarEmpregadosOrdenadosObjectDAO() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Eliminamos el "new DTO.EmpregadoListadoDTO"
            String hql = """
            select 
                e.nss, concat(e.apelido1, ' ', coalesce(e.apelido2, ''), ', ', e.nome),
                e.departamento.nomeDepartamento, 
                case 
                    when type(e) = POJOS.Empregadofixo then 'fijo' 
                    else 'temporal' 
                end,
                size(e.telefonos)
            from Empregado e  inner join e.departamento d
            order by e.apelido1 asc, e.apelido2 asc, e.nome asc
            """;

            // Importante: Usar Object[].class
            return session.createQuery(hql, Object[].class).list();
        } catch (Exception e) {
            return null;
        }
    }

//Exercicio 3:  
    public static List<Object[]> listarDepartamentosNumEmpleadosDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
// size(d.empregados) devuelve 0 si no hay empleados
            String hql = """
            select d.numDepartamento, d.nomeDepartamento, size(d.empregados)  
            from Departamento d
            order by size(d.empregados) desc
        """;

            return session.createQuery(hql, Object[].class).list();
        }
    }

//Ejercico 4 -A-
//Necesitamos combinar coas colecci�ns porque son a forma
// en que Hibernate representa as relaci�ns entre entidades, e sen ese join 
// non teriamos acceso aos proxectos asociados a cada empregado.
// Usamos LEFT JOIN porque queremos que aparezan TODOS os empregados, 
// te�an ou non te�an proxectos asignados.
    public static List<Object[]> listarProyectosPorEmpleadoDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
    select e.nss, p.nomeProxecto
    from Empregado e
    left join e.proxectos ep
    left join ep.proxecto p
    order by e.nss
""";

            return session.createQuery(hql, Object[].class).list();
        }

    }

    //EJERCICIO 4 -B-
    public static List<Object[]> listarEmpregadosConMaisDunProxectoDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select e.nss, p.nomeProxecto
            from Empregado e
            inner join e.proxectos ep
            inner join ep.proxecto p
            where size(e.proxectos) > 1
            order by e.nss
        """;

            return session.createQuery(hql, Object[].class).list();
        }
    }
//EJERCICIO 5

    public static List<Empregado> empregadosSenTelefonoDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            from Empregado e
            where e.telefonos is empty
            order by e.nss
        """;

            return session.createQuery(hql, Empregado.class).list();
        }
    }
//EJERCCIO 6 

    /*
 
 falta a�adir a Proxecto.hbm.xml el mapeo del departamento que controla 
lo siguiente
  <!--Mapeo del departamento que controla los proyectos -->
    <many-to-one name="departamento"
                     class="POJOS.Departamento"
                     column="NumDepartControla"
                     not-null="true"/>
     */
    public static List<Proxecto> consultaProxectosDepartDAO(int numDept) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select p
            from Proxecto p
            where p.departamento.numDepartamento = :num
            order by p.nomeProxecto
        """;

            return session.createQuery(hql, Proxecto.class)
                    .setParameter("num", numDept)
                    .list();
        }
    }

    public static List<Proxecto> consultaProxectosDepartDAO(String nomeDept) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select p
            from Proxecto p
            where p.departamento.nomeDepartamento = :nome
            order by p.nomeProxecto
        """;

            return session.createQuery(hql, Proxecto.class)
                    .setParameter("nome", nomeDept)
                    .list();
        }
    }

    //EXERCICIO 7
    //D
    // M�TODO DAO: devolve unha lista de DTOs cos datos dos directores (DirectorDepartamentoDTO).
// S� trae os campos necesarios, NON trae entidades completas.
    //tambi�n se podr�a traer un List<Object[]>
/*  A consulta usa "select new DTO.DirectorDepartamentoDTO(...)" para que Hibernate
    cree directamente obxectos DTO, evitando traballar con Object[].
    Usamos:    left join d.director.supervisor s
       - Se traen todos os departamentos e os seus directores.
        - Se o director TEN supervisor ? rec�llense os seus datos.
        - Se o director NON ten supervisor ? NON se elimina a fila. Simplemente, os campos do supervisor ve�en como NULL.
    Por iso se usa LEFT JOIN e non INNER JOIN:
        - INNER JOIN eliminar�a os directores sen supervisor.
        - LEFT JOIN garante que todos os directores aparezan no listado.
     */
    public static List<DirectorDepartamentoDTO> listarDirectoresDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Consulta HQL que crea directamente o DTO.
            // S� se traen os campos necesarios, non entidades completas.
            String hql = """
            select new DTO.DirectorDepartamentoDTO(
                d.director.nss,
                concat(d.director.apelido1, ' ', d.director.apelido2, ', ', d.director.nome),
                concat(s.apelido1, ' ', s.apelido2, ', ', s.nome),
                d.director.salario,
                d.nomeDepartamento
            )
            from Departamento d
            left join d.director.supervisor s
            order by d.director.salario asc, d.director.nss asc
        """;

            // Hibernate devolve directamente unha lista de DTOs
            return session.createQuery(hql, DirectorDepartamentoDTO.class).list();
        }
    }
//E
/*   M�TODO: totalSalariosDirectoresDAO
    Este m�todo calcula a suma total dos salarios dos directores de departamento.
    --------------
    - Cada Departamento ten un director (Empregadofixo). A consulta HQL accede a d.director.salario.
    - Usamos a funci�n SUM() de HQL para sumar todos os salarios.
    - S� se trae un �nico dato: o total.
    - Se non hai directores, SUM devolver�a null, as� que devolvemos 0.0 para evitar problemas.
     */
    public static Double totalSalariosDirectoresDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select sum(d.director.salario)
            from Departamento d
        """;

            Double total = session.createQuery(hql, Double.class).uniqueResult();
            // Se SUM devolve null (non deber�a), devolvemos 0.0
            return total != null ? total : 0.0;
        }
    }

    /*F
    M�TODO: departamentosConSalarioSuperiorDAO
    Este m�todo devolve unha lista de departamentos nos queo total dos salarios dos seus empregados fixos supera unha
    cantidade indicada por par�metro.
    - Cada Departamento ten un SET de empregados fixos. Sumamos os salarios deses empregados fixos usando SUM().
    - Agrupamos por departamento para obter un total por cada un.
    - Usamos HAVING para filtrar s� os que superan a cantidade indicada.
    - A consulta � parametrizada: :cantidade
    - Ordenamos polo total de salario DESC (de maior a menor).    
     */
    public static List<Object[]> departamentosConSalarioSuperiorDAO(double cantidade) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select  d.numDepartamento, d.nomeDepartamento, count(e), sum(e.salario)     
            from Departamento d
            inner join d.empregados e                         
            where type(e) = POJOS.Empregadofixo        
            group by d.numDepartamento, d.nomeDepartamento
            having sum(e.salario) > :cantidade
            order by sum(e.salario) desc
        """;

            return session.createQuery(hql, Object[].class)
                    .setParameter("cantidade", cantidade)
                    .list();
        }
    }

//exercicio 8
/*    Consulta que obt�n os departamentos que te�en asignado  o maior n�mero de empregados fixos.
 Unimos cada departamento cos seus empregados mediante  d.empregados.
 Filtramos s� os empregados fixos usando:  type(e) = Empregadofixo.Isto garante que s� se contan empregados fixos.
Agrupamos por departamento e director para poder calcular o n�mero de empregados fixos por cada un.
A cl�usula HAVING compara o n�mero de empregados fixos de cada departamento co valor m�ximo existente.
Para iso utilizamos:    count(e) >= ALL (subconsulta)
 A subconsulta devolve o n�mero de empregados de cada departamento, e 
 ALL selecciona s� aqueles que te�en  o valor m�ximo.
     */
    public static List<DepartamentoMaxEmpregadosDTO> departamentosConMaisEmpregadosDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
            select new DTO.DepartamentoMaxEmpregadosDTO(
                d.nomeDepartamento,   count(e),
                d.director.apelido1 || ' ' || coalesce(d.director.apelido2,'') || ', ' || d.director.nome
            )
            from Departamento d  join d.empregados e
           group by d.nomeDepartamento, d.director.apelido1, d.director.apelido2, d.director.nome
            having count(e) >= ALL (
                select size(d2.empregados)
                from Departamento d2
                group by d2.numDepartamento
            )
        """;
            return session.createQuery(hql, DepartamentoMaxEmpregadosDTO.class).list();
        }
    }
//Exercico 9
//A
/*    M�todo que devolve os empregados fixos que te�en o salario m�is alto.    
   1) Ao consultar sobre 'Empregadofixo', Hibernate realiza automaticamente o INNER JOIN 
      coa superclase 'Empregado' para obter os datos com�ns (nome, apelidos, depto).
   2) A subconsulta '(select max(f2.salario) from Empregadofixo f2)' identifica 
      o valor salarial m�is alto de forma din�mica.
   3) O WHERE filtra para que s� se devolvan os empregados cuxo salario coincida 
      exactamente con ese m�ximo, permitindo listar varios se hai un empate.
     */
    public static List<Object[]> empregadosFixosQueGananMaisDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
           select f.nss, f.apelido1 || ' ' || coalesce(f.apelido2,'') || ', ' || f.nome,
           f.departamento.nomeDepartamento, f.salario
           from Empregadofixo f
            where f.salario = (select max(f2.salario) from Empregadofixo f2)
            """;

            return session.createQuery(hql, Object[].class).list();
        }
    }
//B

    /* M�todo que devolve os empregados fixos que ga�an m�is que o director mellor pagado.
   1) Ao consultar sobre 'Empregadofixo', Hibernate realiza automaticamente o JOIN 
      coa superclase 'Empregado' para obter os datos com�ns (nome, apelidos, depto).
   2) A subconsulta identifica o salario m�is alto exclusivamente entre os directores, 
      localizando os seus NSS na entidade 'Departamento' mediante a cl�usula 'IN'.
   3) O WHERE filtra os empregados fixos cuxo salario sexa estrictamente superior (>) 
      ao valor m�ximo obtido nesa subconsulta de directores.
     */
    public static List<Object[]> empregadosQueGananMaisQueDirectorMaxDAO() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select f.nss, f.apelido1 || ' ' || coalesce(f.apelido2,'') || ', ' || f.nome,
                   f.departamento.nomeDepartamento, f.salario
            from Empregadofixo f
            where f.salario > (
                select max(f2.salario) 
                from Empregadofixo f2 
                where f2.nss in (select d.director.nss from Departamento d)
            )
        """;

            return session.createQuery(hql, Object[].class).list();
        }
    }

    //C    
    /*
    M�todo que devolve os empregados fixos var�ns que:
        - son supervisores y te�en un salario comprendido entre dous l�mites
     1) Consultamos diretamente 'Empregadofixo'. Hibernate traz os dados de 'Empregado' automaticamente.
     2) 'f.supervisados is not empty': Verifica se o empregado tem pessoas a cargo (� supervisor).
     3) 'f.sexo = 'H'': Filtra apenas os var�es.
     4) 'between :limInf and :limSup': Filtra o intervalo de sal�rio.
       
     */
    public static List<Object[]> empregadosVaronsSupervisoresEntreSalariosDAO(double limInf, double limSup) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select  f.nss, f.apelido1 || ' ' || coalesce(f.apelido2,'') || ', ' || f.nome,
                f.departamento.nomeDepartamento, f.salario
            from Empregadofixo f
            where f.sexo = 'H'
              and f.supervisados is not empty
              and f.salario between :limInf and :limSup
        """;

            return session.createQuery(hql, Object[].class)
                    .setParameter("limInf", limInf)
                    .setParameter("limSup", limSup)
                    .list();
        }
    }
    //D

    /*    Sube o salario nun porcentaxe aos empregados fixos que te�en asignado o maior n�mero de proxectos.

    1)  Cada empregado pode ter varios proxectos asignados (relaci�n e.proxectos).
    2)  A subconsulta:        select max(size(e2.proxectos)) from Empregado e2
        devolve o n�mero m�ximo de proxectos asignados a un empregado.
    3)  Facemos JOIN con Empregadofixo porque o salario s� existe na subclase.
    4)  Actualizamos s� os empregados fixos que te�en ese n�mero m�ximo de proxectos.
    5)  O salario increm�ntase multiplicando por (1 + porcentaxe/100).
     */
 /*
    PriMero obtenemo el valimno de los proyectos asignados a los empleados
   group by e.nss: Agrupa os proxectos por cada empregado.
   count(p): Conta cantos proxectos ten cada un deses grupos.
   order by count(p) desc: Pon o n�mero m�is alto (o que m�is proxectos ten) na primeira posici�n da lista.
   setMaxResults(1): Hibernate engade un TOP 1 (en SQL Server) � consulta para que s� viaxe un �nico n�mero dende a base de datos: o m�is alto.
     */
    public static Integer obterMaximoProxectosDAO() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
             //ordena para despues coger el primero que ser� el m�ximo
            String hql = """
                    select count(p) from Empregado e 
                    inner join e.proxectos p 
                    group by e.nss 
                    order by count(p) desc 
                    """;

            // Executamos a consulta e qued�monos s� co primeiro resultado
            //Cando usas a funci�n de agregado count() en HQL, Hibernate segue a especificaci�n JPA. 
            //Segundo esta norma, calquera operaci�n de conta (count) debe devolver un Long por defecto.
            Long resultado = session.createQuery(hql, Long.class)
                    .setMaxResults(1) //selecciona s� o m�ximo,
                    .uniqueResult();

            return (resultado != null) ? resultado.intValue() : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    public static int subirSalarioEmpregadosConMaisProxectosDAO(double porcentaxe) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // 1. Obtemos o m�ximo n�mero de proxectos usando o el m�todo anterior
            Integer maxP = obterMaximoProxectosDAO();

            // Se ningu�n ten proxectos, non hai nada que actualizar
            if (maxP == 0) {
                return 0;
            }
            Transaction tx = session.beginTransaction();
            String hqlUpdate = """
                    update Empregadofixo f
                    set f.salario = f.salario * (1 + :porc / 100.0)
                    where size(f.proxectos) = :maxP
                    """;

            /* Utilizando Group By en una sola consulta 
           String hql = """
            update Empregadofixo f 
            set f.salario = f.salario * (1 + :porcentaxe / 100)
            where f.nss in (
                select e.nss from Empregado e 
                join e.empregadoProxectos ep
                group by e.nss
                having count(ep) >= all (
                    select count(ep2) from Empregado e2 
                    join e2.empregadoProxectos ep2 
                    group by e2.nss
                )
            )
        """; 
            
             */
            int afectados = session.createQuery(hqlUpdate)
                    .setParameter("porc", porcentaxe)
                    .setParameter("maxP", maxP)
                    .executeUpdate(); 

            tx.commit();
            return afectados;
        }
    }

}
