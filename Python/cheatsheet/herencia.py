# ----- HERENCIA -----

class Animal:
    def hablar(self):
        return "Hace ruido"

class Perro(Animal):   # Hereda de Animal
    def hablar(self):  # Sobreescribe el método
        return "Guau!"

class Gato(Animal):
    def hablar(self):
        return "Miau!"


p = Perro()
g = Gato()


p.hablar()
g.hablar()

