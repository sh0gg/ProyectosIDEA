package PERSISTENCIA;

// Autor: David Besada Ramilo (53612286E)

import POJOS.Pasteleria;
import POJOS.Pastelero;
import POJOS.Producto;
import POJOS.Tecnica;
import UTILIDADES.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Map;

public class HBPasteleriaDAO {

    public static int conectarHibernateDAO() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        if (sesion != null) {

            sesion.close();
            return 0;
        } else {
            return -1;
        }
    }

    public static int addUpdateTecnicaPastelero(Pastelero p, Tecnica t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        int resultado = -2;
        try {
            String id = p.getCodigo();
            p = session.get(Pastelero.class, id);
            if (p != null) {
                Map<String, String> tecnicasActuales = p.getTecnicas();
                if (tecnicasActuales.containsKey(t.getTecnica())) {
                    tecnicasActuales.replace(t.getTecnica(), t.getNivel());
                    p.setTecnicas(tecnicasActuales);
                    session.update(p);
                    resultado = 0;
                } else {
                    tecnicasActuales.put(t.getTecnica(), t.getNivel());
                    p.setTecnicas(tecnicasActuales);
                    session.update(p);
                    resultado = 1;
                }
                tx.commit();
            } else {
                resultado = -1; // no se encontró al pastelero
            }
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }

        return resultado;
    }

    public static Pastelero getPasteleroId(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Pastelero p = null;
        try {
            p = session.get(Pastelero.class, id);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }

        return p;
    }

    public static String getPasteleroPasteleria(String nombrePasteleria) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Pastelero p = null;
        Pasteleria past;
        try {
            past = session.createQuery("from Pasteleria past join fetch past.dueno where past.nome LIKE :n", Pasteleria.class).setParameter("n", nombrePasteleria).uniqueResult();
            if (past != null) {
                p = past.getDueno();
                if (p != null) {
                    tx.commit();
                } else  {
                    return "errPastelero";
                }
            } else {
                return "errPasteleria";
            }
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }

        if (p != null) {
            return p.getAlias();
        } else  {
            return null;
        }
    }

    public static String cambiarDueno(String alias, String nombrePasteleria) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String nuevoDueno = null;
        Pastelero p = null;
        Pasteleria past = null;
        try {
            p = session.createQuery("from Pastelero p where p.alias LIKE :a", Pastelero.class).setParameter("a", alias).uniqueResult();
            if (p != null) {
                past = session.createQuery("from Pasteleria past join fetch past.dueno where past.nome LIKE :n", Pasteleria.class).setParameter("n", nombrePasteleria).uniqueResult();
                past.setDueno(p);
                session.update(past);
            } else {
                nuevoDueno = "errPastelero";
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
        return nuevoDueno;
    }

    public static int borrarProducto(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        int resultado = -2;
        Producto prod = null;
        try {
            prod = session.createQuery("from Producto p where p.codigo LIKE :i", Producto.class).setParameter("i", id).uniqueResult();
            if (prod != null) {
                session.delete(prod);
                resultado = 0;
            } else {
                resultado = -1;
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
        return resultado;
    }
}