n1 = int(input("Ingresa un numero: "))
n2 = int(input("Ingresa otro numero: "))

if n2 == 0:
    print("No se puede dividir")
else:
    n3 = n1 // n2
    if n1 % n2 == 0:
        print(str(n1) + "/" + str(n2) + " es igual a " + str(n3) + " y no queda resto.")
    else:
        n4 = n1 % n2
        print(str(n1) + "/" + str(n2) + " es igual a " + str(n3) + " y el resto es " + str(n4) + ".")