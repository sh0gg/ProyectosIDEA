package hackers;

public class RainbowEntry {

    private final String clave;
    private final int hash;
    private int usos;

    public RainbowEntry(String clave) {
        this.clave = clave;
        this.hash = clave.hashCode(); // hash calculado 1 única vez
        this.usos = 0;
    }

    public String getClave() {
        return clave;
    }

    public int getHash() {
        return hash;
    }

    public int getUsos() {
        return usos;
    }

    public void incrementarUsos() {
        usos++;
    }

    @Override
    public String toString() {
        return "Clave='" + clave + "', hash=" + hash + ", usos=" + usos;
    }
}
