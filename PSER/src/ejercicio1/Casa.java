package ejercicio1;

public class Casa {
    private static int[] tabiques;
    private static boolean ocupada;

    public Casa(int numTabiques) {
        tabiques = new int[numTabiques];
        ocupada = false;
    }

    public static int[] getTabiques() {
        return tabiques;
    }

    public static void setTabiques(int[] tabiques) {
        Casa.tabiques = tabiques;
    }

    public static boolean isOcupada() {
        return ocupada;
    }

    public static void setOcupada(boolean ocupada) {
        Casa.ocupada = ocupada;
    }
}
