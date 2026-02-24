import java.util.*;

/**
 * Gestor compartido entre hilos.
 * Controla:
 * - nº de conexiones activas
 * - logging por usuario (opcional)
 *
 * IMPORTANTE: métodos synchronized para evitar condiciones de carrera.
 */
public class ConnectionManagerLimit {
    private int activeClients = 0;
    private final int maxClients;

    private final Map<String, StringBuilder> logs = new HashMap<>();

    public ConnectionManagerLimit(int maxClients) {
        this.maxClients = maxClients;
    }

    public synchronized boolean login(String userId) {
        if (activeClients >= maxClients) {
            logs.computeIfAbsent(userId, k -> new StringBuilder())
                .append("LOGIN RECHAZADO (LIMITE)\n");
            return false;
        }
        activeClients++;
        logs.computeIfAbsent(userId, k -> new StringBuilder())
            .append("LOGIN OK\n");
        return true;
    }

    public synchronized void logout(String userId) {
        activeClients--;
        logs.computeIfAbsent(userId, k -> new StringBuilder())
            .append("LOGOUT\n");
        if (activeClients < 0) activeClients = 0;
    }

    public synchronized String getLog(String userId) {
        return logs.getOrDefault(userId, new StringBuilder("Sin log\n")).toString();
    }

    public synchronized int getActiveClients() {
        return activeClients;
    }
}