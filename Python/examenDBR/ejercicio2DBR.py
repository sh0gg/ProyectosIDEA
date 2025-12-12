contactos = {
    "Marta J.": "664 34 43 43",
    "Alfredo H.": "665 34 43 43",
    "Martin M.": "665 36 43 43",
    "María E.": "663 34 43 43",
    "David B.": "662 34 40 43",
    "Juan S.C.": "665 32 43 43",
    "Roberto C.": "667 34 43 41",
    "Raquel T.": "663 34 42 41",
    "Gabriel B.": "669 12 43 43"
}

def mostrarmenu():
    print("Opciones:")
    print("1. Añadir/Modificar.")
    print("2. Buscar.")
    print("3. Borrar.")
    print("4. Listar.")
    print("5. Salir.")

def salir():
    print("¡Volviendo al menú principal!")
    print("")

def añadirmodificar():
    print("¿Que contacto quieres añadir/modificar?")
    nombre = input("Nombre: ")
    if nombre in contactos:
        print(f"El contacto {nombre} existe en tu agenda con el número {contactos[nombre]}")
        respuestaAccion = input("¿Quieres modificar su número? (S/N) ")
        if respuestaAccion == "S":
            nuevoNumero = input(f"El nuevo número de {nombre} será: ")
            contactos[nombre] = nuevoNumero;
            print(f"Se ha guardado el nuevo numero para el contacto {nombre}.")
            salir()
        else:
            salir()
    elif nombre not in contactos:
        print(f"No tengo registrado un contacto {nombre} en tu agenda.")
        respuestaAccion = input("¿Quieres crearlo? (S/N) ")
        if respuestaAccion == "S":
            nuevoNumero = input(f"El número de {nombre} será: ")
            contactos[nombre] = nuevoNumero;
            print(f"Se ha guardado el contacto {nombre} en tu agenda con el número {nuevoNumero}")
            salir()
        else:
            salir()

def buscar():
    print("¡Vamos a buscar!")
    cad = input("¿Qué quieres que busque en tus contactos? (Pon Mar para ver más resultados) ")
    numContactosEncontrados = 0
    cad = cad.upper()
    for contacto in contactos:
        contactoAux = contacto.upper()
        if contactoAux[0:len(cad)] == cad:
            numContactosEncontrados += 1
            print(f"Encontré el contacto {contacto} con el número {contactos[contacto]}")
    if numContactosEncontrados > 0:
        print(f"Encontramos {numContactosEncontrados} contactos.")
        salir()
    else:
        print("¡No hay contactos en tu agenda con esa cadena!")
        salir()

def borrar():
    print("¿Que contacto quieres borrar?")
    nombre = input("Nombre: ")
    if nombre in contactos:
        print(f"El contacto {nombre} existe en tu agenda con el número {contactos[nombre]}")
        respuestaAccion = input("¿Seguro que quieres borrarlo? (S/N) ")
        if respuestaAccion == "S":
            del contactos[nombre]
            print(f"Se ha borrado el contacto {nombre}.")
            salir()
        else:
            salir()
    elif nombre not in contactos:
        print(f"No tengo registrado un contacto {nombre} en tu agenda.")
        salir()

def listar():
    print("Vamos a mostrar todos los contactos de tu agenda.")
    for contacto in contactos:
        print(f"Nombre: {contacto} | Número: {contactos[contacto]}")
    salir()


print("¡Bienvenido a la agenda!")
respuestaMenu = 0
while respuestaMenu != 5:
    mostrarmenu()
    respuestaMenu = int(input())
    if respuestaMenu == 1:
        añadirmodificar()
    elif respuestaMenu == 2:
        buscar()
    elif respuestaMenu == 3:
        borrar()
    elif respuestaMenu == 4:
        listar()
    elif respuestaMenu == 5:
        print("¡Saliendo del programa!")
        break
    else:
        print("¡No hay una función con ese número!")
        salir()

