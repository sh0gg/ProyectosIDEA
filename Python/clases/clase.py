# ----- CLASES Y OBJETOS -----

class Persona:
    # Constructor: __init__
    # 'self' es equivalente a 'this' en Java
    def __init__(self, nombre, edad):
        self.nombre = nombre
        self.edad = edad

    # Método normal
    def saludar(self):
        print(f"Hola, soy {self.nombre}")

    # Otro método
    def es_mayor_edad(self):
        return self.edad >= 18

# Crear objetos
p1 = Persona("Ana", 30)
p2 = Persona("Luis", 15)

p1.saludar()                  # Llama al método
print(p1.es_mayor_edad())          # True
print(p2.es_mayor_edad())          # False
