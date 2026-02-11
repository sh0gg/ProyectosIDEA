class ShoppingCart:
    def __init__(self):
        self.items = []

    def addItem(self, item):
        self.items.append(item)

    def removeItem(self, nombre):
        for item in self.items:
            if item.getNombre() == nombre:
                self.items.remove(item)
                break

    def total(self):
        total = 0
        for item in self.items:
            total += item.getPrecio()
        print(str(total) + "€")

    def listar(self):
        for item in self.items:
            print(item.getNombre())


class Item:
    def __init__(self, id, nombre, precio):
        self.id = id
        self.nombre = nombre
        self.precio = precio

    def getPrecio(self):
        return self.precio

    def getNombre(self):
        return self.nombre

sc = ShoppingCart()

item1 = Item(1, "Televisor", 100)
item2 = Item(2, "Televisor", 200)
item3 = Item(3, "Microondas", 300)

sc.addItem(item1)
sc.addItem(item2)
sc.addItem(item3)

# total con dos televisores, esperamos un total de 600
sc.total()

# añadi un metodo listar para ver que hay en el carrito
sc.listar()

# quitamos una tele
sc.removeItem("Televisor")

# comprobamos el nuevo total, esperamos un total de 500, 100 euros menos

sc.total()
sc.listar()