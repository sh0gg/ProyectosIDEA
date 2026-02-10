class Cliente:
    def __init__(self, nombre):
        self.nombre = nombre
        self.cantidad = 0

    def depositar(self, monto):
        self.cantidad += monto

    def extraer(self, monto):
        self.cantidad -= monto

    def mostrar_total(self):
        print(self.nombre, "tiene", self.cantidad)


class Banco:
    def __init__(self):
        self.cliente1 = Cliente("Juan")
        self.cliente2 = Cliente("Ana")
        self.cliente3 = Cliente("Luis")

    def operar(self):
        self.cliente1.depositar(100)
        self.cliente2.depositar(200)
        self.cliente3.depositar(300)

    def deposito_total(self):
        total = (
            self.cliente1.cantidad +
            self.cliente2.cantidad +
            self.cliente3.cantidad
        )
        print("Total depositado:", total)


b = Banco()
b.operar()
b.deposito_total()
