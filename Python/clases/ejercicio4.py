class Calculadora:
    def __init__(self):
        self.a = int(input("Ingrese primer número: "))
        self.b = int(input("Ingrese segundo número: "))

    def suma(self):
        print("Suma:", self.a + self.b)

    def resta(self):
        print("Resta:", self.a - self.b)

    def multiplicacion(self):
        print("Multiplicación:", self.a * self.b)

    def division(self):
        if self.b != 0:
            print("División:", self.a / self.b)
        else:
            print("No se puede dividir por cero")


c = Calculadora()
c.suma()
c.resta()
c.multiplicacion()
c.division()
