# ----- DICCIONARIOS -----

precios = {
    "manzana": 1.2,
    "pera": 1.5,
    "uva": 2.0
}

print(precios["manzana"])   # Acceso por clave

precios["melon"] = 3.0      # Añadir clave/valor
del precios["pera"]          # Eliminar clave

# Recorrer claves y valores
for fruta, precio in precios.items():
    print(f"{fruta} vale {precio}€")

# Comprobar existencia
if "kiwi" in precios:
    print("Está en el diccionario")
else:
    print("No está")
