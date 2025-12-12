asignaturas = {
    "Matemáticas": 6,
    "Física": 7,
    "Química": 4,
    "Inglés": 6,
    "Lengua": 1,
    "EF": 7,
    "Gallego": 9,
    "Plática": 7,
    "Tecnología": 5
}

for asignatura, nota in asignaturas.items():
    print(f"<{asignatura}> calificación <{nota}>")

notaMax = 0
notaMin = 10
asigMax = ""
asigMin = ""

for asignatura, nota in asignaturas.items():
    if nota > notaMax:
        notaMax = nota
        asigMax = asignatura

    if nota < notaMin:
        notaMin = nota
        asigMin = asignatura

print(f"La nota máxima es de <{asigMax}> con un <{notaMax}>")
print(f"La nota mínima es de <{asigMin}> con un <{notaMin}>")

asigAprobadas = []
asigSuspensas = []
for asignatura, nota in asignaturas.items():
    if nota >= 5:
        asigAprobadas.append(asignatura)
    if nota < 5:
        asigSuspensas.append(asignatura)

numAprobadas = len(asigAprobadas)
numSuspensas = len(asigSuspensas)

print(f"El número de asignaturas suspensas es {numSuspensas}.")
print(f"El número de asignaturas aprobadas es {numAprobadas}.")

sumNotas = 0
for asignatura, nota in asignaturas.items():
    sumNotas += nota

media = (sumNotas / len(asignaturas)).__floor__()

print(f"La nota media de este curso es de {media}.")

nuevaNotaMax = notaMax + 1
if nuevaNotaMax > 10:
    nuevaNotaMax = 10

asignaturas["Proyecto"] = nuevaNotaMax

for asignatura, nota in asignaturas.items():
    print(f"<{asignatura}> calificación <{nota}>")

# Nueva funcionalidad

print("Buscador de asignaturas:")
asig = input("Introduce el nombre de la asignatura que quieras ver la nota: ")

asig = asig.strip()
asig = asig[0].upper() + asig[1:].lower()

if asig in asignaturas:
    notaAsig = asignaturas[asig]
    print("¡Se ha encontrado tu nota!")
    print(f"¡¡Has sacado un {notaAsig} en {asig}!!")
    if notaAsig >= 8:
        print("¡Enhorabuena!")
    elif notaAsig >= 5:
        print("¡Bien hecho!")
    elif notaAsig < 5:
        print("¡Estoy seguro de que puedes remontarlo!")
else:
    print(f"¡No te matriculaste en {asig}!")
