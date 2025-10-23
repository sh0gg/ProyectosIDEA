print("Bienvenido a la sala de juegos.")

edad = int(input("Edad: "))

while edad > 0:
    if edad < 0:
        print("Edad negativa!")
        edad = int(input("Edad: "))
    else:
        print("Edad positiva!")

entrada = 0
if edad < 4:
    entrada = 0
elif 4 >= edad < 18:
    entrada = 5
elif 18 >= edad:
    entrada = 10

print("Tu entrada son " + str(entrada) + " euros.")
