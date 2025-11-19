package dialogos;

import java.util.ArrayList;

public class AlmacenDeUsuarios {

    private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private static int seguinteId = 1;

    // Devólvese o ID que se lle asignaría ao seguinte usuario
    public static int getSeguinteId() {
        return seguinteId;
    }

    // Engade un usuario e incrementa o seguinte ID
    public static void engadirUsuario(Usuario u) {
        if (u != null) {
            usuarios.add(u);
            seguinteId++;
        }
    }

    public static int getNumeroUsuarios() {
        return usuarios.size();
    }

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
}
