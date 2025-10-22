pwdReal = "abc123."
pwd = input("Ingresa tu contraseña: ")
if pwd == "":
    print("Esta contraseña está vacía.")
else:
    if pwd == pwdReal:
        print("Login correcto!")
    else:
        print("Login incorrecto.")