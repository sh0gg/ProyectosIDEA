import random

asignaturas=["Matemáticas", "Física", "Química", "Historia", "Lengua"]

print("Las asignaturas son :")
for asignatura in asignaturas:
    print("    - " + asignatura)


for asignatura in asignaturas:
    print("Yo estudio " + asignatura)

print("Vamos a añadir asignaturas")
nAsignaturas = int(input("¿Cuántas quieres añadir? "))

for i in range(int(nAsignaturas)):
    newAsignatura = input("Nombre de la asignatura: ")
    asignaturas.append(newAsignatura)

print("Ahora las asignaturas son: ")
for asignatura in asignaturas:
    print("    - " + asignatura)


print("")
print("")
print("")

print("¡Ahora vamos a guardar tus notas!")
notas = []
for asignatura in asignaturas:
    if asignatura not in notas:
# NUMERO ALEATORIO PARA LA NOTA DE LA ASIGNATURA EN VEZ DE PONERLA A MANO
        notaAsignatura = random.randint(1, 10)
        notas.append([asignatura, notaAsignatura])
#         notaAsignatura = int(input("¿Que sacaste en " + asignatura + "? "))
#         notas.append([asignatura, notaAsignatura])

print("Ahora mostramos notas: ")
for i in range(len(notas)):
    print("    - " + notas[i][0] + " " + str(int(notas[i][1])))

print("Cambiemos una nota...")
materia = input("¿A qué materia le cambiamos la nota? ")

encontrada = False

for i in range(len(notas)):
    if materia == notas[i][0]:
        nota = int(input("Nueva nota: "))
        notas[i][1] = nota
        encontrada = True
        break

if not encontrada:
    print("¡La materia no existe!")

print("Ahora mostramos notas: ")
for i in range(len(notas)):
    print("    - " + notas[i][0] + " " + str(int(notas[i][1])))