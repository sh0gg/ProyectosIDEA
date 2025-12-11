# ----- HERENCIA -----

class Animal:
    def hablar(self):
        print("Hace un ruido")

class Perro(Animal):   # Hereda de Animal
    def hablar(self):  # Sobreescribe el método
        print("Guau!")

class Gato(Animal):
    def hablar(self):
        print("Miau!")

p = Perro()
g = Gato()

p.hablar()
g.hablar()
