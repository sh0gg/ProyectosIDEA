class CuentaCorriente:
    def __init__(self, nombre, apellido, direccion, telefono, nif, saldo):
        self.nombre = nombre
        self.apellido = apellido
        self.direccion = direccion
        self.telefono = telefono
        self.nif = nif
        self.saldo = saldo

    def getNombre(self):
        return self.nombre

    def setNombre(self, nombre):
        self.nombre = nombre

    def getApellido(self):
        return self.apellido

    def setApellido(self, apellido):
        self.apellido = apellido

    def getDireccion(self):
        return self.direccion

    def setDireccion(self, direccion):
        self.direccion = direccion

    def getTelefono(self):
        return self.telefono

    def setTelefono(self, telefono):
        self.telefono = telefono

    def getNif(self):
        return self.nif

    def setNif(self, nif):
        self.nif = nif

    def getSaldo(self):
        return self.saldo

    def setSaldo(self, saldo):
        self.saldo = saldo

    def retirarDinero(self, cantidadRetirada):
        self.saldo -= cantidadRetirada
        print(self.getNombre() + " ha retirado " + str(cantidadRetirada) + " euros")

    def ingresarDinero(self, cantidadIngresada):
        self.saldo += cantidadIngresada
        print(self.getNombre() + " ha ingresado " + str(cantidadIngresada) + " euros")

    def consultarCuenta(self):
        print("Datos de la cuenta: ")
        print("NIF: " + self.nif)
        print("Nombre: " + self.getNombre())
        print("Apellido: " + self.getApellido())
        print("Direccion: " + self.getDireccion())
        print("Telefono: " + self.getTelefono())
        print("Saldo: " + str(self.getSaldo()))

    def saldoNegativo(self):
        print("... Comprobando saldo ...")
        if self.saldo < 0:
            print("Saldo negativo")
        else:
            print("Saldo positivo")


cc = CuentaCorriente("Jesus","Vazquez", "Avenida de los Robles", "+34 666 66 66 66", "53612286E", 6000)

cc.consultarCuenta()

cc.retirarDinero(7000)

cc.saldoNegativo()

cc.ingresarDinero(2000)

cc.saldoNegativo()


