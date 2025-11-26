package hilos.armaggedon;

public class Armaggedon extends Nave {
    boolean hasDrilled;

    public Armaggedon(int id) {
        super(id);
        hasDrilled = false;
    }

    @Override
    public void run() {
        while (true) {
            Meteorito m = irMeteoritoAleatorio();
            if (m == null) {
                mensajeFin();
                return;
            }

            if (!m.intentarTaladrar()) {
                souts("Meteorito " + m.getId() + " ya taladrado o explotado. Buscando otro...");
                continue;
            }

            souts("Taladrando el meteorito " + m.getId());
            dormirAleatorio(800, 1500);
            hasDrilled = true;
            m.finTaladro();
            souts("Taladro terminado en meteorito " + m.getId());

            try {
                m.esperarRepostajeOSalida();
            } catch (InterruptedException e) {
                return;
            }

            if (m.estaExplotado()) {
                souts("Meteorito " + m.getId() + " explotado, despegando.");
            } else {
                souts("Repostaje y bomba colocada en meteorito " + m.getId() + ", despegando.");
            }

            dormirAleatorio(100, 300);
        }
    }
}
