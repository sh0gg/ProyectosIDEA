package armaggedon;

public class Meteorito {
    int id;

    private boolean taladrando = false;
    private boolean taladrado = false;
    private boolean repostado = false;
    private boolean bombaColocada = false;
    private boolean explotado = false;

    public Meteorito(int id) {
        this.id = id;
    }

    public synchronized boolean isExplotado() {
        return explotado;
    }

    public synchronized boolean estaTaladrado() {
        return taladrado;
    }

    public int getId() {
        return id;
    }

    public synchronized boolean intentarComenzarTaladro() {
        if (explotado || taladrado || taladrando) return false;
        taladrando = true;
        return true;
    }

    public synchronized void finTaladro() {
        taladrando = false;
        taladrado = true;
        notifyAll();
    }

    public synchronized void esperarRepostajeOSalida() throws InterruptedException {
        while (!repostado && !explotado) {
            wait();
        }
    }

    public synchronized boolean repostarYBombearSinEsperar() {
        if (explotado || bombaColocada) return false;
        if (!taladrado) return false;
        repostado = true;
        notifyAll();
        bombaColocada = true;
        explotado = true;
        notifyAll();
        return true;
    }
}
