"""nombre = input("Nombre: ")
apellido1 = input("Primer apellido: ")
apellido2 = input("Segundo apellido: ")"""

print("Has escrito tu nombre así de mal: ")
nombre = "daVId"
apellido1 = "beSaDa"
apellido2 = "rAMilo"

print(nombre, apellido1, apellido2)

print("Nombre todo en mayúsculas: ")
print(nombre.upper() + " " + apellido1.upper() + " " + apellido2.upper())

print("Nombre todo en minúsculas: ")
print(nombre.lower() + " " + apellido1.lower() + " " + apellido2.lower())

print("Nombre con mayúsculas la primera (usando capitalize()): ")
print(nombre.capitalize() + " " + apellido1.capitalize() + " " + apellido2.capitalize())

print("Nombre con mayúsculas la primera (concatenando funciones de cadenas): ")
print(nombre[0:1].upper() + nombre[1:len(nombre)].lower() + " "
      + apellido1[0:1].upper() + apellido1[1:len(apellido1)].lower() + " "
      + apellido2[0:1].upper() + apellido2[1:len(apellido2)].lower())
