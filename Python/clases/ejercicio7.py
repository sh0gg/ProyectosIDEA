class Cuenta:
    def __init__(self, titular, cantidad):
        self.titular = titular
        self.cantidad = cantidad

    def imprimir_datos(self):
        print("Titular:", self.titular)
        print("Cantidad:", self.cantidad)

class CajaAhorro(Cuenta):
    def __init__(self, titular, cantidad):
        super().__init__(titular, cantidad)

    def mostrar_informacion(self):
        print("=== Caja de Ahorro ===")
        self.imprimir_datos()

class PlazoFijo(Cuenta):
    def __init__(self, titular, cantidad, plazo, interes):
        super().__init__(titular, cantidad)
        self.plazo = plazo
        self.interes = interes

    def calcular_interes(self):
        return self.cantidad * self.interes / 100

    def mostrar_informacion(self):
        print("=== Plazo Fijo ===")
        print("Titular:", self.titular)
        print("Cantidad:", self.cantidad)
        print("Plazo:", self.plazo)
        print("Interés:", self.interes)
        print("Total de interés:", self.calcular_interes())

caja = CajaAhorro("Juan Pérez", 5000)
plazo = PlazoFijo("Ana Gómez", 10000, 12, 5)

caja.mostrar_informacion()
print()
plazo.mostrar_informacion()
