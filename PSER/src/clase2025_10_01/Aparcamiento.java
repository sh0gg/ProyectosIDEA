package clase2025_10_01;

import java.util.Arrays;
import java.util.Random;

class Aparcamiento {
    private final int[] plazas; // -1 = libre, otro valor = id del conductor

    public Aparcamiento(int numPlazas) {
        plazas = new int[numPlazas];
        Arrays.fill(plazas, -1);
        imprimirEstado("Arranque: todas las plazas libres");
    }

    public synchronized int entrar(int idConductor) throws InterruptedException {
        while (!hayPlazaLibre()) {
            wait();
        }
        int plaza = primeraPlazaLibre();
        plazas[plaza] = idConductor;
        imprimirEstado("ENTRA C" + idConductor + " -> ocupa plaza " + plaza);
        return plaza;
    }

    public synchronized void salir(int plaza, int idConductor) {
        if (plaza >= 0 && plaza < plazas.length && plazas[plaza] == idConductor) {
            plazas[plaza] = -1;
            imprimirEstado("SALE C" + idConductor + " <- libera plaza " + plaza);
            notifyAll();
        }
    }

    private boolean hayPlazaLibre() {
        for (int p : plazas) if (p == -1) return true;
        return false;
    }

    private int primeraPlazaLibre() {
        for (int i = 0; i < plazas.length; i++) if (plazas[i] == -1) return i;
        return -1;
    }

    private void imprimirEstado(String transicion) {
        StringBuilder sb = new StringBuilder();
        sb.append(transicion).append('\n');
        sb.append("Plazas: ");
        for (int i = 0; i < plazas.length; i++) {
            if (plazas[i] == -1) {
                sb.append("[ ]");
            } else {
                sb.append(String.format("[C%02d]", plazas[i]));
            }
        }
        System.out.println(sb);
    }
}