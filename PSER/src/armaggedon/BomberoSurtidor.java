package armaggedon;

public class BomberoSurtidor extends Nave {

    public BomberoSurtidor(int id) {
        super(id);
    }

    @Override
    public void run() {
        while (true) {
            Meteorito m = irMeteoritoAleatorio();
            if (m == null) { mensajeFin(); return; }

            if (m.repostarYExplotar()) {
                souts("Repostando y colocando bomba en meteorito " + m.getId());
            } else {
                if (m.estaExplotado()) {
                    souts("Meteorito " + m.getId() + " ya explotado, buscando otro.");
                } else if (!m.estaTaladrado()) {
                    souts("Meteorito " + m.getId() + " aún no listo, buscando otro.");
                } else {
                    souts("Meteorito " + m.getId() + " ya atendido, buscando otro.");
                }
            }

            dormirAleatorio(150, 400);
        }
    }
}
