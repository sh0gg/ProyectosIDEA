package clase2025_09_17;

import java.util.Random;

public class Hilo extends Thread{

    public Hilo(String name){
        super(name);
    }

    @Override
    public void run(){
        for (int i = 0; i <= 99; i++){
            System.out.println(getName() + " > " + i);
            try {
                Thread.sleep(r().nextInt(1000));
            } catch (InterruptedException e) {}
        }
    }

    private static Random r() {
        return new Random();
    }

    public static void main(String[] args){
        Hilo hilo1 = new Hilo("Hilo1");
        Hilo hilo2 = new Hilo("Hilo2");
        hilo1.start();
        hilo2.start();
    }
}
