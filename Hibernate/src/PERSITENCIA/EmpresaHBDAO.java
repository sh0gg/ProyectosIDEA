package PERSITENCIA;

import POJOS.*;
import Utilidades.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmpresaHBDAO {

    public static int conectarHibernate() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        if (sesion != null) {

            sesion.close();
            return 0;
        } else {
            return -1;
        }
    }

    public static Proxecto buscarProxecto(int proxecto) {
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.get(Proxecto.class, proxecto);
        } catch (HibernateException e) {
            // Lanzamos un RuntimeException
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static void crearEmpregado(Empregado empregado) {
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            sesion.save(empregado);
            tx.commit();
            System.out.println("Se ha creado el empleado " + empregado.toString());
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error al crear empregado.");
        }
    }

    public static int añadirFuncionDep(int i, String funcion) {
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            Departamento d = sesion.get(Departamento.class, i);
            if (d == null) {
                return -1;
            }
            if (d.getFunciones().contains(funcion)) {
                return 0;
            }
            d.getFunciones().add(funcion);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            return 0;
        }
        return 1;
    }

    public static int eliminarFuncionDep(int i, String funcion) {
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            Departamento d = sesion.get(Departamento.class, i);
            if (d == null) {
                return -1;
            }
            if (!d.getFunciones().contains(funcion)) {
                return 0;
            }
            d.getFunciones().remove(funcion);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            return 0;
        }
        return 1;
    }

    public static int añadirFaseProxecto(int i, Fase fase) {
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            Proxecto p = sesion.get(Proxecto.class, i);
            if (p == null) {
                return -1;
            }
            if (p.getFases().contains(fase)) {
                return 0;
            }
            p.getFases().add(fase);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            return 0;
        }
        return 1;
    }

    public static int addOrUpdateTlf(String nss, String numero, String info) {
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            Empregado e = sesion.get(Empregado.class, nss);
            if (e == null) {
                return -1;
            }
            e.getTelefonos().put(numero, info);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            return 0;
        }
        return 1;
    }

    public static int deleteTlf(String nss, String numero) {
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            Empregado e = sesion.get(Empregado.class, nss);
            if (e == null) {
                return -1;
            }
            if (!e.getTelefonos().containsKey(numero)) {
                return 0;
            }
            e.getTelefonos().remove(numero);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            return 0;
        }
        return 1;
    }

    public static int insertFamiliar(String nss, Familiar familiar) {
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            Empregado e = sesion.get(Empregado.class, nss);
            if (e == null) {
                return -1;
            }
            if (e.getFamiliares().contains(familiar)) {
                return 0;
            }
            e.getFamiliares().add(familiar);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            return 0;
        }
        return 1;
    }

    public static List<Empregado> getEmpregadosLocalidade(String localidade) {
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.createQuery("FROM Empregado e WHERE e.enderezo.localidade = :loc", Empregado.class)
                    .setParameter("loc", localidade)
                    .list();
        } catch (HibernateException e) {
            System.out.println("Error al recuperar empleados por localidad: " + e.getMessage());
            return null;
        }
    }
}
