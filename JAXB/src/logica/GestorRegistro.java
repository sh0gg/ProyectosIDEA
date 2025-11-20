package logica;

import clases.ClasesRegistro.*;
import persistencia.XMLJAXBUtils;
import jakarta.xml.bind.JAXBException;

import java.time.LocalDate;
import java.util.List;

/**
 * Clase de prueba para generar un XML de registros de personas
 * usando las clases JAXB del paquete ClasesRegistro.
 * <p>
 * Al ejecutarla, generará un archivo XML en la ruta indicada
 * con el contenido del objeto Registro creado en memoria.
 */

public class GestorRegistro {
    public static void main(String[] args) {
        try {

// 1. Crear el objeto Registro (raíz del XML)
            Registro registro = new Registro();

// Versión del registro (atributo version="1.0")
            registro.setVersion("1.0");

// Fecha de creación del registro (se adaptará a dd-M-yy)
// Ej: LocalDate.of(2025, 11, 17) -> "17-11-25"
            registro.setFechaCreacion(LocalDate.of(2025, 11, 17));

// 2. Establecer las categorías (se convertirán a texto separado por espacios)
// <Categorias>Estudios Trabajo Deporte</Categorias>
            registro.setCategorias(List.of("Estudios", "Trabajo", "Deporte"));

// 3. Crear un Trabajador con teléfonos
            Trabajador trabajador = new Trabajador();
            trabajador.setNombre("Carlos Alba");

// Se guardará como "12-3-99" gracias al LocalDateAdapterRegistro
            trabajador.setFechaNacimiento(LocalDate.of(1999, 3, 12));

// 3.1. Crear los teléfonos del trabajador
            Telefonos telefonos = new Telefonos();
            telefonos.addTelefono(new Telefono("movil", "111111111"));
            telefonos.addTelefono(new Telefono("fijo", "222222222"));

// Asignar el contacto al trabajador (en este caso, telefonos)
            trabajador.setContacto(telefonos);

// 3.2. Crear la empresa con puesto
// <Empresa puesto="Director">LaColmena</Empresa>
            Empresa empresa = new Empresa();
            empresa.setNombre("LaColmena");
            empresa.setPuesto("Director");
            trabajador.setEmpresa(empresa);

// 3.3. Salario del trabajador
            trabajador.setSalario(8000.0);

// 4. Crear un Estudiante con email
            Estudiante estudiante = new Estudiante();
            estudiante.setNombre("Ángel Rodríguez");
            estudiante.setFechaNacimiento(LocalDate.of(2001, 7, 21));

// 4.1. Email como contacto
            Email email = new Email("angel.rodriguez@example.com");
            estudiante.setContacto(email);

// 4.2. Universidad y carrera
            estudiante.setUniversidad("Santiago");
            estudiante.setCarrera("Informática");

// 5. Añadir las personas al registro
            registro.addPersona(trabajador);
            registro.addPersona(estudiante);

// 6. Guardar el registro en un XML usando XMLJAXBUtils
// Ajusta la ruta si quieres que vaya a otra carpeta
            String rutaSalida = "src/test/registrosPersonas.xml";
            XMLJAXBUtils.marshal(registro, rutaSalida);
            System.out.println("XML de registros de personas generado correctamente en: " + rutaSalida);
        } catch (JAXBException e) {
            System.err.println("Error al generar el XML de registros de personas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}