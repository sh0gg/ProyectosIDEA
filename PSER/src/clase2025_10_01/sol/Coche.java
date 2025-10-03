package clase2025_10_01.sol;

import java.util.Random;

public class Coche extends Thread{
    Aparcamiento aparcamiento;
    public Coche(String nombre, Aparcamiento aparcamiento) {
        super(nombre);
        this.aparcamiento = aparcamiento;
    }

    @Override public String toString(){return "C"+getName();}

    @Override public void run(){
        aparcamiento.aparcar(this);
        try { sleep(new Random().nextInt(100) +1);}
        catch(InterruptedException ex){}
        aparcamiento.salir(this);
    }
}
