# Programa que pide fruta y kilos, y calcula precio usando un diccionario

precios = {"manzana": 2.5, "pera": 3.0, "uva": 4.0}

fruta = input("Fruta: ").lower()
kilos = float(input("Kilos: "))

if fruta in precios:
    total = precios[fruta] * kilos
    print(f"Total a pagar: {total}€")
else:
    print("Esa fruta no está disponible.")
