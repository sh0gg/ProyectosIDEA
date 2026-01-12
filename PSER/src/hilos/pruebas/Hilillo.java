package hilos.pruebas;

public class Hilillo extends Thread{

    int numero = 0;

    @Override
    public void run(){
        while (numero < 100){
            numero++;
            System.out.println("Hilillo " + numero);
        }
        System.out.println("El numero del hilillo llego a 100");
    }

    public static void main(String[] args){
        Hilillo hilillo = new Hilillo();
        hilillo.start();
    }
}
