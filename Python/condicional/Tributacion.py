ingresosAnuales = int(input("Tus ingresos anuales: "))

paga = 0

if ingresosAnuales < 10000:
    paga = 0.05
elif 10000 <= ingresosAnuales < 20000:
    paga = 0.15
elif 20000 <= ingresosAnuales < 35000:
    paga = 0.20
elif 35000 <= ingresosAnuales < 60000:
    paga = 0.30
elif ingresosAnuales >= 60000:
    paga = 0.45

comision = ingresosAnuales - (ingresosAnuales * paga)

print("Vas a pagar un total de " + str(comision) + ", que es un " + str(paga * 100) + "%")