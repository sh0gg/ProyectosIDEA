package armaggedon;

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

            if (!m.intentarComenzarTaladro()) {
                log("meteorito " + m.getId() + " ocupado o resuelto, buscando otro");
                continue;
            }

            log("taladrando meteorito " + m.getId());
            dormirAleatorio(800, 1500);
            hasDrilled = true;
            m.finTaladro();
            log("taladro finalizado en meteorito " + m.getId() + ", esperando repostaje");

            try {
                m.esperarRepostajeOSalida();
            } catch (InterruptedException e) {
                return;
            }

            if (m.isExplotado()) {
                log("repostaje recibido y meteorito " + m.getId() + " explosionado, despegando");
            } else {
                log("repostaje recibido en meteorito " + m.getId() + ", despegando");
            }

            dormirAleatorio(100, 300);
        }
    }
}
