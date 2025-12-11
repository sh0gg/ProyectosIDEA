# ----- STRINGS Y SLICING -----

mensaje = "Python"

# Acceder por índice
primera = mensaje[0]        # 'P'
ultima = mensaje[-1]        # 'n'

# Slicing: [inicio:fin]
slice1 = mensaje[1:4]       # 'yth'

# Slicing con pasos
invertido = mensaje[::-1]   # 'nohtyP'

# Métodos útiles
mayus = mensaje.upper()     # 'PYTHON'
minus = mensaje.lower()     # 'python'
reemplazo = mensaje.replace("Py", "Mi")  # 'Mithon'

print(primera, ultima, slice1, invertido)
