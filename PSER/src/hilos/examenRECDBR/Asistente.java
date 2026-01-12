package hilos.examenRECDBR;

import java.util.ArrayList;
import java.util.List;

import static hilos.examenRECDBR.Fiesta.getBebida;

public class Asistente extends Thread {

    final int MAX_BEBIDAS_RICAS = 5;
    private final Fiesta.ControlInicio control;

    String nombre;
    List<Bebida> bebidasTomadas = new ArrayList<>();
    int numBebidasRicas = 0;
    double gustoAlcohol;
//    boolean pillado;

    public Asistente(String nombre, Fiesta.ControlInicio c) {
        this.nombre = nombre;
        this.control = c;
        gustoAlcohol = 0.1;
//        pillado = false;
    }

    @Override
    public void run(){
        try {
            System.out.println( this + ": A la espera de empezar la fiesta!");
            control.esperarFiesta(this.toString());
            while (numBebidasRicas < MAX_BEBIDAS_RICAS /*|| !pillado */) {
                Thread.sleep(Math.round(Math.random()*100));
                if (beber()) {
                    numBebidasRicas++;
                }
            }
//            if (pillado) {
//                System.out.println("Han pillado a " + this + " bebiendo alcohol! Se va de la fiesta...");
//            }

        } catch (InterruptedException ignored) {}
    }

    private boolean beber() {
        Bebida bebida = getBebida();
        bebidasTomadas.add(bebida);
        if (bebida.isAlcoholica) {
            double chance = Math.random();
            if  (chance <= gustoAlcohol) {
                System.out.println("A " + this + " le ha gustado la bebida numero " + bebidasTomadas.size());
                gustoAlcohol =+ 0.2;
                return true;
            } else {
                System.out.println("A " + this + " NO le ha gustado la bebida numero " + bebidasTomadas.size());
                gustoAlcohol =+ 0.2;
                return false;
            }
        } else {
            System.out.println("A " + this + " le ha gustado la bebida numero " + bebidasTomadas.size());
            return true;
        }
    }

    @Override
    public String toString(){
        return nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Bebida> getBebidasTomadas() {
        return bebidasTomadas;
    }

    public int getNumBebidasRicas() {
        return numBebidasRicas;
    }
}
