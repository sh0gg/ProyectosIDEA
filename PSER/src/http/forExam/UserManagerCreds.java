import java.util.*;

/**
 * Mantiene:
 * - si un usuario está online
 * - cuántas veces se conectó
 */
public class UserManagerCreds {

    private static class User {
        String name;
        boolean online;
        int conexiones;
        User(String name) { this.name = name; }
    }

    private final Map<String, User> users = new HashMap<>();

    /**
     * Devuelve:
     * 0 si OK
     * 1 credenciales incorrectas
     * 2 ya conectado
     */
    public synchronized int login(String user, String pass) {
        if (!credencialesOk(user, pass)) return ConfigLoginCreds.ERR_CREDENCIALES;

        User u = users.get(user);
        if (u == null) {
            u = new User(user);
            users.put(user, u);
        } else {
            if (u.online) return ConfigLoginCreds.ERR_YA_CONECTADO;
        }

        u.online = true;
        u.conexiones++;
        return 0;
    }

    public synchronized void logout(String user) {
        User u = users.get(user);
        if (u != null) u.online = false;
    }

    public synchronized String info(String user) {
        User u = users.get(user);
        if (u == null) return "Usuario no encontrado";
        return (u.online ? "ONLINE " : "OFFLINE ") + u.name + " conexiones=" + u.conexiones;
    }

    // Criterio típico de profe (como en tus ejemplos):
    // pass == usuario + longitud(usuario)
    private boolean credencialesOk(String user, String pass) {
        return pass != null && pass.equalsIgnoreCase(user + user.length());
    }
}