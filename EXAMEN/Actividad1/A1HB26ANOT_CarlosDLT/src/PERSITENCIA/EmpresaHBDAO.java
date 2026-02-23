package PERSITENCIA;

import DTO.EmpregadoListadoDepartamentoDTO;
import DTO.EmpregadoProxectoDTO;
import DTO.EmpregadoSupervisorDTO;
import POJOS.Departamento;
import POJOS.Empregado;
import POJOS.Empregadofixo;
import POJOS.Familiar;
import POJOS.Habilidade;
import POJOS.Lugar;
import POJOS.Proxecto;
import POJOS.ProxectoFase;
import POJOS.Vehiculo;
import Utilidades.HibernateUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class EmpresaHBDAO {

    public static int conectarHibernateDAO() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        if (sesion != null) {

            sesion.close();
            return 0;
        } else {
            return -1;
        }
    }

    public static Proxecto buscarProxectoDAO(int proxecto) {
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.get(Proxecto.class, proxecto);
        } catch (HibernateException e) {
            // Lanzamos un RuntimeException 
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static void guardarEmpregadoDAO(Empregado empregado) {

        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            // Comprobar se xa existe
            Empregado existente = sesion.get(Empregado.class, empregado.getNss());
            if (existente != null) {
                throw new RuntimeException("Xa existe un empregado co NSS " + empregado.getNss());
            }
            // Gardar
            sesion.save(empregado);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Erro de Hibernate ao crear empregado", e);
        }
    }

    public static Empregado buscarEmpregadoDAO(String nss) {
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.get(Empregado.class, nss);
        } catch (HibernateException e) {
            throw new RuntimeException("Error al buscar empleado en BD", e);
        }
    }

    public static Departamento buscarDepartamentoDAO(int numDepartamento) {
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.get(Departamento.class, numDepartamento);
        } catch (HibernateException e) {
            throw new RuntimeException("Erro ao buscar departamento na BD", e);
        }
    }

    public static void guardarFuncionDeptDAO(int numDepartamento, String funcion) {

        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            // Buscar o departamento
            Departamento d = sesion.get(Departamento.class, numDepartamento);

            if (d == null) {
                throw new RuntimeException("O departamento " + numDepartamento + " non existe.");
            }
            // Se a función xa existe ? erro específico
            if (d.getFunciones().contains(funcion)) {
                throw new RuntimeException("A función '" + funcion + "' xa está asignada ao departamento " + numDepartamento + ".");
            }
            // Engadir a función
            d.getFunciones().add(funcion);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Erro de Hibernate ao gardar a función no departamento", e);

        }

    }

    public static void eliminarFuncionDeptDAO(int numDepartamento, String funcion) {

        Transaction tx = null;

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            Departamento d = sesion.get(Departamento.class, numDepartamento);
            if (d == null) {
                throw new RuntimeException(
                        "O departamento " + numDepartamento + " non existe."
                );
            }
            // La función no pertenece al departamento
            if (!d.getFunciones().contains(funcion)) {
                throw new RuntimeException(
                        "A función '" + funcion + "' non pertence ao departamento " + numDepartamento + "."
                );
            }

            // Eliminamos la función
            d.getFunciones().remove(funcion);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(
                    "Erro de Hibernate ao eliminar a función do departamento",
                    e
            );
        }
    }

    public static boolean engadirFaseProxectoDAO(int numProxecto, ProxectoFase fase) {
        boolean actualizado = false;
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            Proxecto p = sesion.get(Proxecto.class, numProxecto);
            if (p == null) {
                throw new RuntimeException("O proxecto " + numProxecto + " non existe.");
            }

            // ver si está 
            //sobrescrito en metodo equal y hascode
            if (p.getFases().contains(fase)) {
                for (ProxectoFase f : p.getFases()) {
                    if (f.getNomeFase().equals(fase.getNomeFase())) {
                        f.setEstado(fase.getEstado());
                        actualizado = true;
                        break; // salir del bucle, ya encontramos la fase
                    }
                }

            }
            if (!actualizado) {
                p.getFases().add(fase);
            }
            tx.commit();
            return actualizado;

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Erro de Hibernate ao gardar a fase do proxecto", e);
        }
    }

    public static Boolean engadirTelefonoDAO(String nss, String numero, String tipo) {

        Transaction tx = null;
        boolean actualizado = false;

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {

            tx = sesion.beginTransaction();

            Empregado e = sesion.get(Empregado.class, nss);

            if (e == null) {   //Si no existe devolvemos null
                return null;
            }

            Map<String, String> telefonos = e.getTelefonos();

            // Se existe -> actualizar
            if (telefonos.containsKey(numero)) {
                telefonos.put(numero, tipo);
                actualizado = true;
            } else {
                // Se non existe ->inserir
                telefonos.put(numero, tipo);
            }

            tx.commit();
            return actualizado;

        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Erro ao gardar o teléfono do empregado", ex);
        }
    }

    public static Boolean borrarTelefonoDAO(String nss, String numero) {

        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            Empregado e = sesion.get(Empregado.class, nss);
            // Se o empregado non existe -> devolvemos null
            if (e == null) {
                return null;
            }
            Map<String, String> telefonos = e.getTelefonos();
            // Se o teléfono non existe -> devolvemos false
            if (!telefonos.containsKey(numero)) {
                return false;
            }
            // Se existe -> borrámolo
            telefonos.remove(numero);
            tx.commit();
            return true; // borrado correcto

        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Erro ao borrar o teléfono do empregado", ex);
        }
    }

    public static Boolean engadirFamiliarDAO(String nssEmpregado, Familiar familiar) {
        Transaction tx = null;

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();

            // 1. Buscar empregado
            Empregado e = sesion.get(Empregado.class, nssEmpregado);
            if (e == null) {
                return null; // empregado non existe
            }

            // 2. Comprobar se xa existe un familiar co mesmo NSS
            for (Familiar f : e.getFamiliares()) {
                if (f.getNss().equals(familiar.getNss())) {
                    return false; // familiar duplicado
                }
            }

            // 3. Engadir familiar -> Hibernate xera o índice automaticamente
            e.getFamiliares().add(familiar);

            tx.commit();
            return true; // engadido correctamente

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Erro inesperado ao engadir o familiar", e);
        }
    }

    /*
    Puntos clave:  HQL trabaja con atributos Java, no columnas SQL
     enderezo.localidade -> componente embebido 
      Devuelve List<Empregado>
    Si se hace un FROM en HQL mejor usar un alias si vas a:
     Acceder a atributos y  Usar WHERE, SELECT, ORDER BY, etc.
     */
    public static List<Empregado> obterEmpregadosPorLocalidadeDAO(String localidade) {

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            /*
        La conslta devuelve una lista de objetos de una clase asociada: Empregado
             */
            String hql = """
            FROM Empregado e
            WHERE e.enderezo.localidade = :localidade
        """;

            return sesion.createQuery(hql, Empregado.class)
                    .setParameter("localidade", localidade)
                    .getResultList();   // query.list() en HB5 sigue, pero  a partir de la 6 lo iliminaron 
            /*
        por parte sería 
        Query<Empregado> query = sesion.createQuery(hql, Empregado.class);
        query.setParameter("localidade", localidade);
         return query.getResultList();
             */

        } catch (HibernateException e) {
            throw new RuntimeException("Erro de Hibernate ao consultar empregados por localidade", e);
        }
    }

    public static Familiar buscarFamiliarDAO(String nssEmpregado, String nssFamiliar) {

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {

            // Consulta HQL: solo trae el familiar solicitado
            String hql = "select f "
                    + "from Empregado e inner join e.familiares f "
                    + "where e.nss = :nssEmpregado and f.nss = :nssFamiliar";

            Query<Familiar> q = sesion.createQuery(hql, Familiar.class);
            q.setParameter("nssEmpregado", nssEmpregado);
            q.setParameter("nssFamiliar", nssFamiliar);

            List<Familiar> resultado = q.getResultList();

            if (resultado.isEmpty()) {
                return null; // no existe el familiar o el empleado
            }

            return resultado.get(0);

        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar el familiar", ex);
        }
    }

    public static Departamento buscarDepartamentoPorNombreDAO(String nomeDepartamento) {

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "FROM Departamento d WHERE d.nomeDepartamento = :nome";

            return sesion.createQuery(hql, Departamento.class)
                    .setParameter("nome", nomeDepartamento)
                    .uniqueResult();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o departamento", e);
        }
    }

    /*
 Códigos de retorno:
  0  ? Operación correcta
 -1  ? El empleado ya existe
 -2  ? El departamento no existe
 -9  ? Error inesperado (Hibernate)
     */
 /*
 Comentario:
 En asociaciones bidireccionales de Hibernate, **es necesario actualizar ambos lados**:
 1. `empregado.setDepartamento(departamento)` asegura que el empleado conoce su departamento.
 2. `departamento.getEmpregados().add(empregado)` asegura que el departamento conoce a su empleado.
 
 Si solo actualizamos un lado, la relación en memoria queda inconsistente y Hibernate puede:
   - No persistir correctamente la relación en la base de datos.
   - Dar comportamientos inesperados al navegar por la colección del departamento.
 
 Por eso, para mantener la **coherencia entre objetos Java y la base de datos**, siempre se deben sincronizar ambos lados de la asociación.
     */
    public static int crearEmpregadoConDepartamentoDAO(
            Empregado empregado, String nomeDepartamento) {
        Transaction tx = null;

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {

            tx = sesion.beginTransaction();

            // 1. Comprobar si existe el empleado
            if (sesion.get(Empregado.class, empregado.getNss()) != null) {
                return -1; // empleado ya existe
            }
//            String hql = "FROM Departamento d WHERE d.nomeDepartamento = :nome";
//            Departamento departamento=sesion.createQuery(hql, Departamento.class)
//                    .setParameter("nome", nomeDepartamento)
//                    .uniqueResult();
//            // 2. Buscar departamento
            //Es transistorio , no está asociado con la sessin actual si lo traigo en una funcion
            Departamento departamento = buscarDepartamentoPorNombreDAO(nomeDepartamento);

            if (departamento == null) {
                return -2; // departamento no existe
            }
            //Existe en la base de datos y hay que traerlo a a memoria para la cache de persistencia 
            departamento = sesion.get(Departamento.class, departamento.getNumDepartamento());
            // 3. Asociación bidireccional; importante en lso dos sentidos
            /*
     Este es el único cambio que Hibernate necesita para actualizar la base de datos, porque:
     El lado MANY (Empregado) contiene la clave foránea.Hibernate solo mira ese lado para generar el UPDATE
     Por tanto: Con esta línea, la relación queda correctamente persistida en la base de datos.       
             */
            empregado.setDepartamento(departamento);          // Lado "muchos" ? establecemos el departamento del empleado

            /*  Esta línea NO es necesaria para la base de datos, 
     pero SÍ es necesaria para mantener la coherencia en memoria.
     Si no la pones:
    empregado.getDepartamento() ? mostrará el nuevo departamento
    pero novoDepartamento.getEmpregados() ? NO incluirá al empleado
             */
            departamento.getEmpregados().add(empregado);     // Lado "uno" ? añadimos el empleado a la colección del departamento

            // 4. Persistencia
            /*
           empregado es transitaorio y por eso lo tenemos que persisteir
           Departameto lo hemos obtenido con get y ya está en el contexto de persistencia
           Si hacemos session.persist(departamento):
            Hibernate intenta insertarlo como nuevo registro, y si el departamento ya tiene un ID, puede dar error de clave primaria duplicada.
            Solo usaríamos persist() para nuevos objetos que queremos insertar.
             */
            sesion.save(empregado);  //equivalente a Save (solo Hibernate). Persist es de JPA

            tx.commit();

            return 0; // OK

        } catch (HibernateException e) {
            System.out.println("ee " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
            return -9; // error inesperado
        }
    }

    /*
 Para cambiar una relación Many-to-One bidireccional es obligatorio:
 - Eliminar el empleado del departamento antiguo
 - Añadirlo al nuevo departamento
 - Actualizar el atributo departamento del empleado

 Hibernate NO mantiene la coherencia de ambos lados automáticamente.
     */
    public static int cambiarDepartamentoEmpregadoDAO(
            String nssEmpregado, int numNovoDepartamento) {

        Transaction tx = null;

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {

            tx = sesion.beginTransaction();

            // 1. Buscar empleado
            Empregado empregado = sesion.get(Empregado.class, nssEmpregado);
            if (empregado == null) {
                return -1; // empleado no existe
            }

            // 2. Buscar nuevo departamento
            Departamento novoDepartamento
                    = sesion.get(Departamento.class, numNovoDepartamento);

            if (novoDepartamento == null) {
                return -2; // departamento no existe
            }

            // 3. Comprobar si ya pertenece a ese departamento
            Departamento deptActual = empregado.getDepartamento();
            if (deptActual != null && deptActual.equals(novoDepartamento)) {
                return -3; // ya está en ese departamento
            }

            // 4. Quitar del departamento actual (si lo tiene)
            if (deptActual != null) {
                deptActual.getEmpregados().remove(empregado);
            }

            // 5. Asociar al nuevo departamento (BIDIRECCIONAL)
            empregado.setDepartamento(novoDepartamento);        // lado MANY
            novoDepartamento.getEmpregados().add(empregado);   // lado ONE

            // 6. Commit (empregado es persistente)
            tx.commit();

            return 0; // OK

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            return -9; // error inesperado
        }
    }

    public static int cambiarDepartamentoEmpregadoHQL(
            String nssEmpregado, int numDepartamento) {

        Transaction tx = null;

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {

            tx = sesion.beginTransaction();

            String hql = """
            UPDATE Empregado e
            SET e.departamento.numDepartamento = :numDept
            WHERE e.nss = :nss
        """;

            int filas = sesion.createQuery(hql)
                    .setParameter("numDept", numDepartamento)
                    .setParameter("nss", nssEmpregado)
                    .executeUpdate();

            tx.commit();

            if (filas == 0) {
                return -1; // empleado o departamento no existe
            }

            return 0; // OK

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            return -9;
        }
    }

    /*
Al pasar la Session como argumento, evitas el coste de abrir y cerrar conexiones constantemente. 

     */
    public static Habilidade buscarHabilidadePorNome(String nome, Session session) {

        return session.createQuery("FROM Habilidade h WHERE h.nome = :n", Habilidade.class)
                .setParameter("n", nome)
                .uniqueResult();
    }

    /**
     * Inserta solo las habilidades que no existen.
     *
     * @param listaEntrada
     * @return Lista de objetos Habilidade que han sido insertados con éxito.
     */
    public static List<Habilidade> insertarHabilidadesDAO(List<Habilidade> listaEntrada) throws HibernateException {
        List<Habilidade> insertadas = new ArrayList<>();
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            for (Habilidade hab : listaEntrada) {
                // Comprobamos si ya existe en la BD usando la sesión actual
                Habilidade existente = buscarHabilidadePorNome(hab.getNome(), session);

                if (existente == null) {
                    session.save(hab); // SQL Server genera el ID aquí
                    insertadas.add(hab);
                }
            }

            tx.commit();
            return insertadas;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }

    }

    public static int borrarHabilidadeDAO(int idHab) throws HibernateException {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Habilidade h = session.get(Habilidade.class, idHab);
            if (h == null) {
                return -1; // non existe
            }
// 2. Quitamos la habilidad de cada empleado (esto limpia la tabla intermedia)
            for (Empregado emp : h.getEmpregados()) {
                emp.getHabilidades().remove(h);
            }
            session.delete(h); // borra tamén EMPREGADO_HABILIDADE
            tx.commit();
            return 0;

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

    public static Empregado buscarEmpregadoPorNSS(String nss, Session session) {
        return session.get(Empregado.class, nss);
    }

    /**
     * Asigna una lista de habilidades a un empleado existente
     *
     * @param nss
     * @param habilidades
     * @return -1 ? empleado no existe 0 ? operación correcta
     */
    public static int asignarHabilidadesEmpregadoDAO(
            String nss, List<Habilidade> habilidades) throws HibernateException {

        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Empregado emp = buscarEmpregadoPorNSS(nss, session);
            if (emp == null) {
                return -1; // empleado no existe
            }

            for (Habilidade h : habilidades) {
                // Recuperamos la habilidad gestionada
                Habilidade hab = session.get(Habilidade.class, h.getId());
                if (hab != null) {
                    emp.getHabilidades().add(hab); // LADO PROPIETARIO
                    hab.getEmpregados().add(emp);
                }
            }

            // session.update(emp); // Hibernate gestiona EMPREGADO_HABILIDADE
            tx.commit();
            return 0;

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

    public static int gardarOuActualizarVehiculoDAO(String nss, Vehiculo v) throws HibernateException {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // 1. Recuperar empregado
            Empregado emp = buscarEmpregadoPorNSS(nss, session);
            if (emp == null) {
                return -1; // empregado non existe
            }

            // 2. Comprobar matrícula única
            Vehiculo existeMat = session.createQuery(
                    "FROM Vehiculo v WHERE v.matricula = :m", Vehiculo.class)
                    .setParameter("m", v.getMatricula())
                    .uniqueResult();

            if (existeMat != null && !existeMat.getNss().equals(nss)) {
                return -2; // matrícula xa usada por outro empregado
            }

            // 3. Inserir ou actualizar
            Vehiculo vehEmp = emp.getVehiculo();

            if (vehEmp == null) {
                // Inserción
                //Vehiculo es el lado propietario , el que tiene la PK
                //En relaciones One-to-One con clave primaria compartida, basta con actualizar 
                //el lado propietario (Vehiculo), porque es el único que escribe la relación en la base de datos.
                //El otro lado es solo de navegación.
                v.setEmpregado(emp);
                v.setNss(emp.getNss());   // PK = FK
                //emp.setVehiculo(vehEmp);  //no hace falta  ponerlo funciona igual. Pero si luego se utiliza si que haría falta para que tenga constancia
                session.save(v);
            } else {
                // Actualización           
                vehEmp.setMatricula(v.getMatricula());
                vehEmp.setMarca(v.getMarca());
                vehEmp.setModelo(v.getModelo());
                vehEmp.setDataCompra(v.getDataCompra());
                //session.update(vehEmp); //no hace falta por que es persistente
            }

            tx.commit();
            return 0;

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

    public static int borrarVehiculoDAO(String nss) throws HibernateException {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // 1. Comprobar que o NSS existe como empregado
            Empregado emp = buscarEmpregadoPorNSS(nss, session);
            if (emp == null) {
                return -2; // NSS non válido ? non existe empregado
            }

            // 2. Recuperar o vehículo polo NSS (PK = FK)
            Vehiculo v = session.get(Vehiculo.class, nss);
            if (v == null) {
                return -1; // o empregado existe pero non ten vehículo
            }

            // 3. Romper a relación no lado inverso (Empregado)
            /* Se borras o vehículo sen limpar o empregado:
          Hibernate ve que emp.getVehiculo() apunta ao obxecto borrado
         Como tes cascade=ALL, intenta gardalo de novo
        E salta a excepción
             */
            emp.setVehiculo(null);
            // 4. Borrar o vehículo
            session.delete(v);

            tx.commit();
            return 0;

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

    public static List<EmpregadoProxectoDTO> listarEmpleadosProxectoDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql
                    = "SELECT new DTO.EmpregadoProxectoDTO("
                    + "   e.nss, e.nome, concat(e.apelido1, ' ', coalesce(e.apelido2, ''))"
                    + ", p.nomeProxecto, ep.horas"
                    + ") "
                    + "FROM Empregado e "
                    + "LEFT JOIN e.proxectos ep "
                    + "LEFT JOIN ep.proxecto p "
                    + "ORDER BY e.nss";

            return session.createQuery(hql, EmpregadoProxectoDTO.class).list(); // getResultList() es JPA e list() máis común en Hibernate puro
        }
    }

    public static List<Object[]> listarEmpleadosProxectoArrayDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select e.nss, e.nome, concat(e.apelido1, ' ', coalesce(e.apelido2, '')), p.nomeProxecto,ep.horas
            from Empregado e left join e.proxectos ep left join ep.proxecto p
            order by e.nss
             """;

            return session.createQuery(hql, Object[].class).list();
        }
    }

    public static boolean borrarProxectoPorNome(String nomeProxecto) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction tx = session.beginTransaction();

            // 1. Buscar o proxecto polo nome
            String hql = "FROM Proxecto p WHERE p.nomeProxecto = :nome";
            Proxecto proxecto = session.createQuery(hql, Proxecto.class)
                    .setParameter("nome", nomeProxecto)
                    .uniqueResult();

            // 2. Se non existe ? non borrar
            if (proxecto == null) {
                System.out.println("O proxecto '" + nomeProxecto + "' non existe.");
                return false;
            }

            // 3. Borrar o proxecto
            //cascade = CascadeType.ALL, orphanRemoval = true BORRA AUTOMATICAMENTE TODOS LOS PROXECTOS DE EMPREGADO PROYECTOS
            //? Borra o proxecto
            //Borra todas as fases (porque son composite-element)
            //Borra todas as filas de EMPREGADO_PROXECTO (porque son orfos)
            /*
        sin cascade = CascadeType.ALL, orphanRemoval = true
        ao borrar un proxecto Hibernate non eliminaría automaticamente as relacións en EMPREGADO_PROXECTO 
        for (EmpregadoProxecto ep : proxecto.getEmpregados()) { session.remove(ep); }
             */
            session.remove(proxecto);

            tx.commit();
            return true;
        }
    }

    public static String quitarSupervisorDAO(String nss) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String resultado;
            Transaction tx = session.beginTransaction();

            Empregado emp = session.get(Empregado.class, nss);

            // 0 ? empregado non existe
            if (emp == null) {
                return "non existe o empregado " + nss;
            }

            //  empregado existe pero NON ten supervisor
            if (emp.getSupervisor() == null) {
                return "o empregado " + nss + " existe sin supervisor ";
            }

            /// Caso 3: empregado ten supervisor ? construímos mensaxe correcta 
            Empregado sup = emp.getSupervisor();
            resultado = "O empregado " + nss + " ten como supervisor a " + sup.getNome()
                    + " " + sup.getApelido1() + (sup.getApelido2() != null ? " " + sup.getApelido2() : "")
                    + ". Supervisor desasignado correctamente.";

            // desasignar supervisor
            emp.setSupervisor(null);

            tx.commit();
            return resultado;
        }
    }

    public static EmpregadoSupervisorDTO consultarEmpregadoSupervisorHQLDAO(String nss) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            SELECT NEW DTO.EmpregadoSupervisorDTO(
                e.nome,
                CONCAT(e.apelido1, ' ', COALESCE(e.apelido2, '')),
                s.nome,
                CONCAT(s.apelido1, ' ', COALESCE(s.apelido2, ''))
            )
            FROM Empregado e
            LEFT JOIN e.supervisor s
            WHERE e.nss = :nss
            """;

            return session.createQuery(hql, EmpregadoSupervisorDTO.class)
                    .setParameter("nss", nss)
                    .uniqueResult();
        }
    }

    public static String consultarEmpregadoSupervisorDAO(String nss) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Empregado emp = session.get(Empregado.class, nss);

            // 1. Non existe
            if (emp == null) {
                return "Non existe o empregado co NSS " + nss;
            }

            // Datos do empregado
            String datosEmpregado = emp.getNome() + " " + emp.getApelido1()
                    + (emp.getApelido2() != null ? " " + emp.getApelido2() : "");

            Empregado sup = emp.getSupervisor();

            // 2. Existe pero non ten supervisor
            if (sup == null) {
                return "Empregado: " + datosEmpregado + "\nSupervisor: ";
            }

            // 3. Ten supervisor
            String datosSupervisor = sup.getNome() + " " + sup.getApelido1()
                    + (sup.getApelido2() != null ? " " + sup.getApelido2() : "");

            return "Empregado: " + datosEmpregado + "\nSupervisor: " + datosSupervisor;
        }
    }

    public static List<Empregado> consultarEmpregadosDeUNSupervisorDAO(String nssSupervisor) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Comprobar se o supervisor existe
            Empregado supervisor = session.get(Empregado.class, nssSupervisor);

            if (supervisor == null) {
                return null; // NON existe
            }

            String hql = """
            FROM Empregado e
            WHERE e.supervisor.nss = :nssSup
            """;

            return session.createQuery(hql, Empregado.class)
                    .setParameter("nssSup", nssSupervisor)
                    .getResultList();  //o list()
        }
    }

    public static List<EmpregadoListadoDepartamentoDTO> listarEmpleadosTiposDAO() {

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select new DTO.EmpleadoListadoDTO(
                e.nss,
                concat(e.nome, ' ',e.apelido1, ' ', coalesce(e.apelido2, '')),
               case
                     when type(e) = POJOS.Empregadofixo then 'Empregado Fixo'
                       when type(e) = POJOS.Empregadotemporal then 'Empregado Temporal'
                end,
               d.nomeDepartamento
            )
            from Empregado e inner  join e.deptodirector d
            order by e.nome
            """;

            return sesion.createQuery(hql, EmpregadoListadoDepartamentoDTO.class).list();
        }
    }

    /*
    0 ? inserción correcta
    -1 ? NSS ya existe
    -2 ? departamento no existe
    -4 ? error genérico
     */
    public static int insertarEmpregadoDAO(Empregado emp, String nomeDepto) {

        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            // 0. Comprobar si ya existe un NSS igual
            Empregado existente = session.get(Empregado.class, emp.getNss());
            if (existente != null) {
                return -1;   // NSS ya existe
            }

            // 1. Buscar el departamento al que pertenece
            String hql = "from Departamento d where d.nomeDepartamento = :nome";
            Departamento depto = session.createQuery(hql, Departamento.class)
                    .setParameter("nome", nomeDepto)
                    .uniqueResult();

            if (depto == null) {
                return -2;  // Departamento no existe
            }

            // 2. Asignar el departamento al que pertenece
            emp.setDepartamento(depto);

            // 3. Guardar (Hibernate detecta si es fixo o temporal)
            session.save(emp);

            tx.commit();
            return 0; // Éxito

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return -4; // Error genérico 
        }
    }
    
  /**
 * Inserta o modifica as horas extras dun empregado fixo.
 *
 * Devolve:
 *   0 ? horas extras insertadas (non existía esa data)
 *   1 ? horas extras actualizadas (xa existían)
 *  -1 ? non existe un empregado con ese NSS
 *  -2 ? o empregado existe pero non é fixo
 *  -3 ? erro na operación (excepción)
     * @param nss
     * @param fecha
     * @param horas
     * @return 
 */  
 public static int insertarModificarHorasExtras(String nss, LocalDate fecha, double horas) {

    Transaction tx = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        tx = session.beginTransaction();
        Empregado emp = session.get(Empregado.class, nss);        if (emp == null) {
            return -1; // No existe
        }
        if (!(emp instanceof Empregadofixo)) {
            return -2; // No es fijo       
        }
        Empregadofixo fijo = (Empregadofixo) emp;

        Map<LocalDate, Double> mapa = fijo.getHorasextras();
        //Para saber si es una insercción o actualización 
        boolean existe = mapa.containsKey(fecha);
        mapa.put(fecha, horas); // inserta o actualiza automáticamente
        session.update(fijo);
        tx.commit();
        return existe ? 1 : 0; // 1 = actualizado, 0 = insertado

    } catch (Exception e) {
           System.out.println("en la persi "+e.getMessage());
        if (tx != null) tx.rollback();
        return -3; //error
    }
}

 
public static int eliminarHorasExtras(String nss, LocalDate fecha) {
    Transaction tx = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        tx = session.beginTransaction();
        Empregadofixo emp = session.get(Empregadofixo.class, nss);
        if (emp == null) {
            return -1; // Empleado inexistente
        }
        Map<LocalDate, Double> mapa = emp.getHorasextras();
        if (!mapa.containsKey(fecha)) {
            return -2; // No hay horas en esa fecha
        }
        mapa.remove(fecha);
        tx.commit();
        return 1; // Eliminado correctamente
    }

    catch (Exception e) {

        if (tx != null) tx.rollback();
        return -3; // Error general
    }
}

public static double totalHorasExtrasMesDAO(String nss, int mes, int ano) {
    // Usamos try-with-resources para asegurar el cierre automático de la sesión
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        
        // 1. Verificamos la existencia del empleado y si es de tipo Fijo
        Empregado emp = session.get(Empregado.class, nss);

        if (emp == null) return -1.0; 
        if (!(emp instanceof Empregadofixo)) return -2.0;

        // 2. Consulta HQL sobre la colección Map 'horasextras'
        // Usamos key(h) para la fecha y value(h) para el valor de las horas
        String hql = """
            SELECT COALESCE(SUM(value(h)), 0.0)
            FROM Empregadofixo e JOIN e.horasextras h
            WHERE e.nss = :nss
              AND MONTH(key(h)) = :mes
              AND YEAR(key(h)) = :ano
        """;

        // 3. Ejecutamos la consulta
        Double total = session.createQuery(hql, Double.class)
                .setParameter("nss", nss)
                .setParameter("mes", mes)
                .setParameter("ano", ano)
                .uniqueResult();

        // Si no hay registros (null), devolvemos 0.0
        return (total != null) ? total : 0.0;

    } catch (Exception e) {
        // Al no usar transacción manual, el catch captura el error sin conflictos
        System.err.println("Erro técnico: " + e.getMessage());
        return -3.0; 
    }
}

public static List<String> obtenerNombresLugaresDAO(String nomeDepto) {
 
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        
        
        // 1. Buscamos el objeto Departamento
        Query q = session.createQuery("from Departamento where nomeDepartamento = :nome");
        q.setParameter("nome", nomeDepto);
        Departamento depto = (Departamento) q.uniqueResult();
        
        // Si no existe, devolvemos null
        if (depto == null) return null;

        // 2. Si existe, usamos su método getter
        
        List<String> nombres = new ArrayList<>();
        for (Lugar l : depto.getLugares()) {
            nombres.add(l.getLugar());
        }
        //con consulta hql sería
        /*
        String hql = "select l.lugar " + "from Lugar l " + "where l.departamento.nome = :nomeDpto";
        return session.createQuery(hql, String.class)
        .setParameter("nomeDpto", 
        nomeDpto) .list();
        */        
     
        return nombres; 
        
    } catch (Exception e) {
               return null;
    }
}

 public static int insertarDepartamentoCompleto(Departamento dpto, Set<String> lugares,
            Set<String> funciones, String nssDirector) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            //  Buscamos al director. Si no existe, retornamos -1
            Empregadofixo director = session.get(Empregadofixo.class, nssDirector);
            if (director == null) {
                return -1;
            }

            // Comprobación de nome único
            Query<Departamento> q = session.createQuery(
                    "from Departamento where nomeDepartamento = :nome",
                    Departamento.class);
            q.setParameter("nome", dpto.getNomeDepartamento());
            Departamento existente = q.uniqueResult();

            if (existente != null) {
                return -2; // xa existe un departamento co mesmo nome
            }
     // 1. Comprobar se o director xa dirixe outro departamento 
            Query<Departamento> q1 = session.createQuery("from Departamento where director.nss = :nss", Departamento.class);
            q1.setParameter("nss", nssDirector);
            Departamento existente1 = q1.uniqueResult();
            if (existente1 != null) {
                return -3; // o director xa é director noutro departamento 

            }
            tx = session.beginTransaction();
            /*
    O departamento é o propietario lóxico da relación co director, pero en Hibernate sempre se debe actualizar o lado que contén
    a clave foránea (FK). Neste caso, a FK NSSDirector está na táboa DEPARTAMENTO, polo que debemos asignar
    o director chamando a  dpto.setDirector(director).
    Non é necesario facer nada no outro sentido (deptodirector.setDepartamento),
    porque Empregadofixo NON ten unha FK nin mapea esta relación.
             */
            dpto.setDirector(director);
            director.setDeptodirector(dpto); //no es necesario para insertar en la BD, pero si para coherencia en la memoria

            //  Al ser Set en el POJO, addAll filtra duplicados automáticamente
            // es componente 
            if (funciones != null) {
                dpto.getFunciones().addAll(funciones);
            }

            // Los lugares se añaden al Set de la entidad
            if (lugares != null) {
                for (String l : lugares) {
                    Lugar novoLugar = new Lugar(l);
                    novoLugar.setDepartamento(dpto); //único lado que Hibernate usa para actualizar a base de datos é o lado propietario, é dicir:é o lado que contén a FK Num_departamento.
                    dpto.getLugares().add(novoLugar); // NON é necesario para insertar en la bd BD, pero SI para manter o modelo en memoria consistente.
                    //Se non o fas, dpto.getLugares() non mostrará o novo lugar ata recargar desde a BD.
                }
            }
            // 4. Guardamos (CascadeType.ALL de lugares se encarga de guardarlos)
            session.save(dpto);
            tx.commit();
            return 0; // Éxito 

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return -4; // Error 
        }
    }



}
