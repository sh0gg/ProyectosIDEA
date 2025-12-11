# ----- IF / ELIF / ELSE -----

edad = 17

if edad >= 18:
    print("Eres mayor de edad")
elif edad >= 13:
    print("Eres adolescente")
else:
    print("Eres niño")

# Comparación sin distinguir mayúsculas
entrada = "Contrasena"
real = "contraseña"

if entrada.lower() == real.lower():
    print("Coincide")
