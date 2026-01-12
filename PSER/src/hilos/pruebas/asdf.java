package hilos.pruebas;

import java.util.Scanner;

public class asdf {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese una palabra: ");
        String str = sc.nextLine();

        if (str.matches("[a-z]+")) {
            System.out.println(str + " consta solo de minusculas");
        } else if (str.matches("[A-Z]+")) {
            System.out.println(str + " consta solo de mayusculas");
        } else {
            System.out.println(str + " consta de mayusculas y minusculas.");
        }
    }
}
