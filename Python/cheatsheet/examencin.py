# ----- Definición de la clase Alumno -----

class Alumno:
    def __init__(self, nombre, notas):
        """
        nombre: string con el nombre del alumno
        notas: diccionario {asignatura: nota}
        """
        self.nombre = nombre
        self.notas = notas

    def calcular_media(self):
        """Devuelve la nota media del alumno."""
        if len(self.notas) == 0:
            return 0
        suma = 0
        for nota in self.notas.values():
            suma += nota
        return suma / len(self.notas)

    def asignaturas_aprobadas(self):
        """Devuelve una lista de asignaturas con nota >= 5."""
        aprobadas = []
        for asignatura, nota in self.notas.items():
            if nota >= 5:
                aprobadas.append(asignatura)
        return aprobadas

    def asignaturas_suspensas(self):
        """Devuelve una lista de asignaturas con nota < 5."""
        suspensas = []
        for asignatura, nota in self.notas.items():
            if nota < 5:
                suspensas.append(asignatura)
        return suspensas

    def mostrar_resumen(self):
        """Imprime por pantalla un resumen completo del alumno."""
        print("===================================")
        print(f"Alumno: {self.nombre}")
        print("Notas por asignatura:")
        for asignatura, nota in self.notas.items():
            print(f"  - {asignatura}: {nota}")

        media = self.calcular_media()
        print(f"\nNota media: {media:.2f}")

        aprobadas = self.asignaturas_aprobadas()
        suspensas = self.asignaturas_suspensas()

        print("\nAsignaturas aprobadas:")
        if len(aprobadas) == 0:
            print("  Ninguna")
        else:
            for asignatura in aprobadas:
                print(f"  - {asignatura}")

        print("\nAsignaturas suspensas:")
        if len(suspensas) == 0:
            print("  Ninguna")
        else:
            for asignatura in suspensas:
                print(f"  - {asignatura}")
        print("===================================\n")


# ----- Programa principal (versión simple: un solo alumno) -----

def programa_un_alumno():
    nombre = input("Introduce el nombre del alumno: ")

    # Asignaturas separadas por comas
    linea_asignaturas = input("Introduce las asignaturas separadas por comas: ")
    # Convertimos la cadena en lista y limpiamos espacios
    asignaturas = [a.strip() for a in linea_asignaturas.split(",") if a.strip() != ""]

    notas = {}
    for asignatura in asignaturas:
        entrada = input(f"Introduce la nota de {asignatura}: ")
        nota = float(entrada)   # Convertimos a float
        notas[asignatura] = nota

    alumno = Alumno(nombre, notas)
    alumno.mostrar_resumen()


# ----- Programa principal (versión extra: varios alumnos) -----

def programa_varios_alumnos():
    alumnos = []  # lista de objetos Alumno

    while True:
        nombre = input("Introduce el nombre del alumno (o 'salir' para terminar): ")
        if nombre.lower() == "salir":
            break

        linea_asignaturas = input("Introduce las asignaturas separadas por comas: ")
        asignaturas = [a.strip() for a in linea_asignaturas.split(",") if a.strip() != ""]

        notas = {}
        for asignatura in asignaturas:
            entrada = input(f"Introduce la nota de {asignatura}: ")
            nota = float(entrada)
            notas[asignatura] = nota

        alumno = Alumno(nombre, notas)
        alumnos.append(alumno)
        alumno.mostrar_resumen()

    # Al final, mostrar resumen de todos los alumnos
    print("\nRESUMEN FINAL DE TODOS LOS ALUMNOS:")
    for alumno in alumnos:
        media = alumno.calcular_media()
        print(f"- {alumno.nombre}: media = {media:.2f}")


# Descomenta solo una de estas líneas según lo que quieras probar:

# programa_un_alumno()
# programa_varios_alumnos()
