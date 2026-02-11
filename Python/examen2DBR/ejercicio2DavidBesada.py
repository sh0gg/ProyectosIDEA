class Cliente:
    def __init__(self, __dni, __nombre, __apellido):
        self.__dni = __dni
        self.__nombre = __nombre
        self.__apellido = __apellido

    def getNombre(self):
        return self.__nombre
    def getApellido(self):
        return self.__apellido

class Articulo:
    def __init__(self, __codigo, __denominacion, __precio):
        self.__codigo = __codigo
        self.__denominacion = __denominacion
        self.__precio = __precio

    def getPrecio(self):
        return self.__precio

    def getDenominacion(self):
        return self.__denominacion

class Factura:
    def __init__(self, __numero, __cliente, __lineas):
        self.__numero = __numero
        self.__cliente = __cliente
        self.__lineas = __lineas

    def getNumeroFactura(self):
        return self.__numero
    def getCliente(self):
        return self.__cliente
    def getLineas(self):
        return self.__lineas

    def mostrarFactura(self):
        print("Factura #" + str(self.getNumeroFactura()))
        print("Cliente " + str(self.getCliente().getNombre()) + " " + str(self.getCliente().getApellido()))
        print("Nombre de producto | Cantidad | Precio por articulo | Subtotal")
        total = 0
        for linea in self.getLineas():
            subtotal = linea.getSubtotal()
            total += subtotal
            print(linea.getArticulo().getDenominacion() + " | " + str(linea.getCantidad()) + " | " + str(linea.getArticulo().getPrecio()) + "€ | " + str(subtotal) + "€")

        print("Total: " + str(total) + "€")

class Linea:
    def __init__(self, __id, __articulo, __cantidad):
        self.__id = __id
        self.__articulo = __articulo
        self.__cantidad = __cantidad

    def getSubtotal(self):
        return self.__articulo.getPrecio() * self.__cantidad

    def getArticulo(self):
        return self.__articulo

    def getCantidad(self):
        return self.__cantidad



