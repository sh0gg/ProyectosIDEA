class Agenda:
    def __init__(self):
        self.contactos = []

    def agregar(self):
        nombre = input("Nombre: ")
        telefono = input("Teléfono: ")
        email = input("Email: ")
        self.contactos.append([nombre, telefono, email])

    def listar(self):
        for c in self.contactos:
            print("Nombre:", c[0], "Tel:", c[1], "Email:", c[2])

    def buscar(self):
        nombre = input("Nombre a buscar: ")
        for c in self.contactos:
            if c[0] == nombre:
                print(c)
                return
        print("Contacto no encontrado")

    def menu(self):
        opcion = ""
        while opcion != "5":
            print("1. Añadir contacto")
            print("2. Lista de contactos")
            print("3. Buscar contacto")
            print("4. Editar contacto")
            print("5. Cerrar agenda")

            opcion = input("Opción: ")

            if opcion == "1":
                self.agregar()
            elif opcion == "2":
                self.listar()
            elif opcion == "3":
                self.buscar()
            elif opcion == "4":
                print("Editar no implementado")
            elif opcion == "5":
                print("Agenda cerrada")


a = Agenda()
a.menu()
