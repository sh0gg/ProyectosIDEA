numero=input("Ingresa un numero de teléfono con esta estructura \"+00-666666666-00\", es decir, prefijo-numero-prefijo: ")
prefijo=numero[0:3]
numTlf=numero[4:13]
extension=numero[14:16]

print("El prefijo es: " + prefijo)
print("El numero es: " + numTlf)
print("La extension es: " + extension)