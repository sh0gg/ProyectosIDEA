from ejercicio2DavidBesada import Articulo, Cliente, Factura, Linea

televisor = Articulo("0001", "Televisor", 399)
grafica = Articulo("0002", "Tarjeta Grafica", 239)

rosa = Cliente("53612286E", "Rosa", "Gonzalez")

factura = Factura(1, rosa, [Linea(1, televisor, 2),Linea(2, grafica, 1)])

factura.mostrarFactura()