package hilos.pruebas;

import java.util.concurrent.atomic.AtomicLong;

public class Principal {
    public static void main(String[] args) {
        int N = 77777777;
        AtomicLong t = new AtomicLong();

        Thread string = new Thread(() -> {
            String str = "";
            t.set(System.currentTimeMillis());
            for (int i = N; i-- > 0; ) {
                str += "x";
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("No terminé a tiempo");
                    break;
                }
            }
            System.out.println(System.currentTimeMillis() - t.get());
        });

        Thread stringBuffer = new Thread(() -> {
            StringBuffer sb = new StringBuffer();
            t.set(System.currentTimeMillis());
            for (int i = N; i-- > 0; ) {
                sb.append("x");
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("No terminé a tiempo");
                    break;
                }
            }
            System.out.println(System.currentTimeMillis() - t.get());
        });

        Thread stringBuilder = new Thread(() -> {
            StringBuilder sb = new StringBuilder();
            t.set(System.currentTimeMillis());
            for (int i = N; i-- > 0; )
                sb.append("x");
            System.out.println(System.currentTimeMillis() - t.get());

            // interrumpe a los otros dos si termina antes
            string.interrupt();
            stringBuffer.interrupt();
        });

        string.start();
        stringBuffer.start();
        stringBuilder.start();


    }
}
