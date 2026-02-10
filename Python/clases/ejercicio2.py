class Persona:
    def __init__(self, nombre, edad):
        self.nombre = nombre
        self.edad = edad

    def mostrar_datos(self):
        print("Nombre:", self.nombre)
        print("Edad:", self.edad)

    def es_mayor(self):
        if self.edad >= 18:
            print(self.nombre, "es mayor de edad")
        else:
            print(self.nombre, "es menor de edad")


p = Persona("Ana", 20)
p.mostrar_datos()
p.es_mayor()
