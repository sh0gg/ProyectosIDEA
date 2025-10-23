punt = int(input("Introduce tu puntuación: 0."))
bonus = 2400 * (punt/10)
if punt == 0:
    print("Rendimiento inaceptable!")
elif punt == 4:
    print("Rendimiento aceptable!")
    print("Bonus de " + str(bonus) + " euros")
elif punt >= 6:
    print("Rendimiento meritorio!")
    print("Bonus de " + str(bonus) + " euros")
else:
    print("Puntuacion de rendimiento invalida")