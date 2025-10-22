nombre = input("Nombre: ")
sexo = input("Sexo (M/H): ")
nombre = nombre.upper()

if (sexo == "M" and nombre[0] < "M") or (sexo == "H" and nombre[0] > "N"):
    print("Perteneces al grupo A")
else:
    print("Perteneces al grupo B")
    