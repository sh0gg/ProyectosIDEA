public class Torre {

    private final int NUM_TORRES = 3;
    private final int ANILLOS_TORRE = NUM_TORRES + 1;
    private final int ESPACIOS_TORRE = ANILLOS_TORRE + 2;
    private int[] contenido = new int[ESPACIOS_TORRE];

    void agregarContenido(){
        for (int i = 0; i < ANILLOS_TORRE; i++) {
            contenido[i] = Util.aleatorio(5,1);
        }
    }


}
