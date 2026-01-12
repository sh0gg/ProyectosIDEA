package hilos.pruebas;

public class HililloJoin2 implements Runnable {

    static final int TOTAL_HILOS = 10;
    int num = 0;

    @Override
    public void run() {
        while (num < 100) {
            num++;
            System.out.println(Thread.currentThread().getName() + " " + num);
        }
        System.out.println(Thread.currentThread().getName() + " llegó a 100");
    }

    public static void main(String[] args) throws InterruptedException {

        Thread[] hilos = new Thread[TOTAL_HILOS];
        HililloJoin2[] tareas = new HililloJoin2[TOTAL_HILOS];

        for (int i = 0; i < TOTAL_HILOS; i++) {
            tareas[i] = new HililloJoin2();
            hilos[i] = new Thread(tareas[i], "Hilo-" + i);
            hilos[i].start();
        }

        for (int i = 0; i < TOTAL_HILOS; i++) {
            hilos[i].join();
        }
    }
}
