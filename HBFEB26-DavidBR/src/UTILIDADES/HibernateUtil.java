package UTILIDADES;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

// Autor: David Besada Ramilo (53612286E)

/**
 * Hibernate Utility class con SessionFactory lista para Hibernate 5+
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static StandardServiceRegistry serviceRegistry;

    static {
        try {
            // Cargar configuracion desde Config/hibernate.cfg.xml
            Configuration configuration = new Configuration().configure();

            
            configuration.addResource("MAPEO/Certificacion.hbm.xml");
            configuration.addResource("MAPEO/Pasteleria.hbm.xml");
            configuration.addResource("MAPEO/Pastelero.hbm.xml");
            configuration.addResource("MAPEO/Producto.hbm.xml");
           
          
            serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            // Construir la SessionFactory con los mappings cargados
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {
            System.err.println("Fallo al crear la SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Devuelve la SessionFactory de Hibernate
     *
     * @return
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Cierra el ServiceRegistry al finalizar la aplicaciÃ³n usa para cerrar y
     * limpiar todos los recursos de Hibernate cuando ya no los necesitas.
     */
    public static void shutdown() {
        if (serviceRegistry != null) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }
}
