package http;

import java.util.Scanner;

public class Menu {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        mostrarMenu();
        int respuesta = eleccion();
        mostrarOpcion(respuesta);

    }

    private static int eleccion() {
        System.out.println("¿Qué opción eliges? ");
        int respuesta = sc.nextInt();
        return respuesta;
    }

    private static void mostrarMenu() {
        System.out.println("Menu");
        System.out.println("===============");
        System.out.println("Opción 1 : -");
        System.out.println("Opción 2 : -");
        System.out.println("Opción 3 : -");
        System.out.println("Opción 4 : -");
        System.out.println("Opción 5 : Salir");
        System.out.println("===============");
        System.out.println(" ");
    }

    private static void mostrarOpcion(int respuesta) {
        switch (respuesta) {
            case 1:
                metodoUno();
                break;
            case 2:
                metodoDos();
                break;
            case 3:
                metodoTres();
                break;
            case 4:
                metodoCuatro();
                break;
            case 5:
                System.out.println("Chao pescao");
                break;
            default:
                System.out.println("¡Esa opción no es valida!");
                System.out.println(" ");
                mostrarMenu();
                int res = eleccion();
                mostrarOpcion(res);
                break;
        }
    }

    private static void metodoCuatro() {
    }

    private static void metodoTres() {
    }

    private static void metodoDos() {
    }

    private static void metodoUno() {
    }

}