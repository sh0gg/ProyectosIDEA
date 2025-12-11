# ----- LISTAS -----

numeros = [10, 20, 30]

numeros.append(40)   # Añadir elemento
numeros.remove(20)   # Eliminar elemento concreto
numeros.sort()       # Ordenar lista
numeros.reverse()    # Invertir lista

print(numeros)

# Recorrer lista
for n in numeros:
    print("Número:", n)

# List comprehension (muy típico en Python)
cuadrados = [n*n for n in range(1, 6)]
print(cuadrados)
