package armaggedon;

public class BomberoSurtidor extends Nave{

    public BomberoSurtidor(int id) {
        super(id);
    }

    @Override
    public void run() {
        while (true) {
            Meteorito m = irMeteoritoAleatorio();
            if (m == null) { mensajeFin(); return; }

            if (m.repostarYBombearSinEsperar()) {
                log("repostando en meteorito " + m.getId() + " y colocando bomba ¡BOOM!");
            } else {
                if (m.isExplotado()) {
                    log("meteorito " + m.getId() + " ya explotado, buscando otro");
                } else if (!m.estaTaladrado()) {
                    log("meteorito " + m.getId() + " aún sin taladrar, buscando otro");
                } else {
                    log("meteorito " + m.getId() + " ya atendido, buscando otro");
                }
            }

            dormirAleatorio(150, 400);
        }
    }
}
