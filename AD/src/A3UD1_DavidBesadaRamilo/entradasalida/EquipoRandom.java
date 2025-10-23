package A3UD1_DavidBesadaRamilo.entradasalida;

import A3UD1_DavidBesadaRamilo.modelo.Equipo;
import A3UD1_DavidBesadaRamilo.modelo.Patrocinador;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

public class EquipoRandom implements AutoCloseable {
    public static final int REG_BYTES = 200;
    private final RandomAccessFile raf;

    public EquipoRandom(Path fichero) throws IOException {
        this.raf = new RandomAccessFile(fichero.toFile(), "rw");
    }

    private static void writeFixedString(RandomAccessFile raf, String s, int len) throws IOException {
        String v = (s == null ? "" : s);
        if (v.length() > len) v = v.substring(0, len);
        // Escribimos como UTF-16 (writeChar) => 2 bytes por char
        for (int i = 0; i < len; i++) {
            char ch = i < v.length() ? v.charAt(i) : ' ';
            raf.writeChar(ch);
        }
    }

    private static String readFixedString(RandomAccessFile raf, int len) throws IOException {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) sb.append(raf.readChar());
        return sb.toString().trim();
    }

    private long posOf(int index){ return (long) index * REG_BYTES; }

    public void writeAt(int index, Equipo e) throws IOException {
        raf.seek(posOf(index));
        raf.writeInt(e.getId());
        writeFixedString(raf, e.getNombre(), 30);
        writeFixedString(raf, e.getPais(), 20);
        raf.writeInt(e.getAnioFundacion());
        raf.writeBoolean(e.isActivo());
        // sponsor principal (si hay)
        String sponsor = e.getSponsors().isEmpty() ? "" : e.getSponsors().get(0).getNombre();
        writeFixedString(raf, sponsor, 30);
        // padding hasta 200
        int usados = 4 + 60 + 40 + 4 + 1 + 60;
        int padding = REG_BYTES - usados;
        raf.write(new byte[padding]);
    }

    public Equipo readAt(int index) throws IOException {
        if (posOf(index) >= raf.length()) return null;
        raf.seek(posOf(index));
        int id = raf.readInt();
        String nombre = readFixedString(raf, 30);
        String pais = readFixedString(raf, 20);
        int anio = raf.readInt();
        boolean activo = raf.readBoolean();
        String sponsor = readFixedString(raf, 30);
        int usados = 4 + 60 + 40 + 4 + 1 + 60;
        int padding = REG_BYTES - usados;
        if (padding > 0) raf.skipBytes(padding);
        Equipo e = new Equipo(id, nombre, pais, anio, activo);
        if (!sponsor.isBlank()) {
            // el nombre del sponsor se guarda, otros campos se pueden cargar después si hace falta
            e.getSponsors().add(new Patrocinador(sponsor, 0f, java.time.LocalDate.now()));
        }
        return e;
    }

    public int size() throws IOException { return (int) (raf.length() / REG_BYTES); }
    public void close() throws IOException { raf.close(); }
}
