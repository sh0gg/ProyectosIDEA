class Triangulo:
    def __init__(self, a, b, c):
        self.a = a
        self.b = b
        self.c = c

    def ladoMayor(self):
        lados = [self.a, self.b, self.c]
        lados.sort()
        return lados[-1]

    def tipoTriangulo(self):
        lados = [self.a, self.b, self.c]
        cantidad = {}
        for lado in lados:
            cantidad[lado] = cantidad.get(lado, 0) + 1
            # cantidad |= {lado: cantidad.get(lado, 0) + 1}


        if len(cantidad) == 1:
            return print("equilatero")
        elif len(cantidad) == 2:
            return print("isosceles")
        elif len(cantidad) == 3:
            return print("escaleno")



t = Triangulo(6,1,2)
print(t.ladoMayor())
t.tipoTriangulo()


