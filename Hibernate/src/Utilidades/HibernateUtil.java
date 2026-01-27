package Utilidades;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class con SessionFactory lista para Hibernate 5+
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static StandardServiceRegistry serviceRegistry;

    static {
        try {
            // Cargar configuración desde Config/hibernate.cfg.xml
            Configuration configuration = new Configuration().configure();

//       Registrar explícitamente todos los mappings .hbm.xml
//Necesrio en Hibernate 5, no en la version 4, Hibernate 5 ya no busca automáticamente
//todos los mappings en el classpath. 
            configuration.addResource("MAPEO/Curso.hbm.xml");
             configuration.addResource("MAPEO/Vehiculo.hbm.xml");
            configuration.addResource("MAPEO/Empregado.hbm.xml");
            configuration.addResource("MAPEO/Departamento.hbm.xml");
              configuration.addResource("MAPEO/Proxecto.hbm.xml");

            // Construir el service registry usando las propiedades de configuración
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
     * @return 
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Cierra el ServiceRegistry al finalizar la aplicación
     * usa para cerrar y limpiar todos los recursos de Hibernate cuando ya no los necesitas.
    */
    public static void shutdown() {
        if (serviceRegistry != null) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }
}
