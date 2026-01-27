package PERSITENCIA;

import POJOS.Empregado;
import POJOS.Proxecto;
import Utilidades.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

}
