public class Main {

    public static final int MAX = 15;
    public static void main(String[] args) {
        Thread hilo = new Thread() {
            @Override
            public void run() {
                int contador = 0;
                while (contador < MAX) {
                    contador++;
                    System.out.println(contador);
                };
            }
        };
        hilo.start();
    }
}