package Utilidades;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static StandardServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration().configure(); // Cargar configuración
            configuration.addAnnotatedClass(POJOS.Empregado.class);
            configuration.addAnnotatedClass(POJOS.Departamento.class);
            configuration.addAnnotatedClass(POJOS.Proxecto.class);
            configuration.addAnnotatedClass(POJOS.EmpregadoProxecto.class);
            configuration.addAnnotatedClass(POJOS.Vehiculo.class);
            configuration.addAnnotatedClass(POJOS.Empregadofixo.class);
            configuration.addAnnotatedClass(POJOS.Empregadotemporal.class);
            configuration.addAnnotatedClass(POJOS.Habilidade.class);
            configuration.addAnnotatedClass(POJOS.Lugar.class);
            serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry); // Usar la misma configuración para construir la SessionFactory
        } catch (Throwable ex) {
            // Log the exception.
            System.err.println("Fallo al crear la conexión" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
