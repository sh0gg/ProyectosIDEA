class Alumno:
    def __init__(self, nombre, nota):
        self.nombre = nombre
        self.nota = nota

    def verNota(self):
        print(self.nota)

    def verNombre(self):
         print(self.nombre)

    def hasAprobado(self):
        if self.nota >= 5:
            print (self.nombre + " ha aprobado con un " + str(self.nota))
        else:
            print (self.nombre + " ha suspendido con un " + str(self.nota))

a = Alumno("Xabi", 7)

a.verNombre()
a.verNota()
a.hasAprobado()