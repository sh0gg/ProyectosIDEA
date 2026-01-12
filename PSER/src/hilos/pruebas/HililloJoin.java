package hilos.pruebas;

public class HililloJoin extends Thread{

    static final int TOTAL_HILOS = 10;
    int num = 0;

    @Override
    public void run() {
        while (num < 100) {
            num++;
            System.out.println(this + " " + num);
        }
        System.out.println(this + " llegó a 100");
    }

    public static void main(String[] args) throws InterruptedException {

        HililloJoin[] h = new HililloJoin[TOTAL_HILOS];
        for (int i = 0; i < TOTAL_HILOS; i++) {
            h[i] = new HililloJoin();
            h[i].start();
        }

        for (int i = 0; i < TOTAL_HILOS; i++) {
            h[i].join();
        }
    }
}
