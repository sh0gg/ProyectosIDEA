package A3UD1_DavidBesadaRamilo.servicios;



import A3UD1_DavidBesadaRamilo.modelo.Corredor;
import A3UD1_DavidBesadaRamilo.modelo.Equipo;
import A3UD1_DavidBesadaRamilo.modelo.Puntuacion;

import java.util.*;
import java.util.stream.Collectors;

public class GestorIntegracion {

    public static Map<Integer, Float> totalPuntosPorEquipo(List<Corredor> corredores) {
        Map<Integer, Float> mapa = new HashMap<>();
        for (Corredor c : corredores) {
            float suma = 0f;
            for (Puntuacion p : c.getPuntuaciones()) suma += p.getPuntos();
            mapa.merge(c.getEquipoId(), suma, Float::sum);
        }
        return mapa;
    }

    public static List<Map.Entry<Integer, Float>> ranking(Map<Integer, Float> totales) {
        return totales.entrySet().stream()
                .sorted((a,b)->Float.compare(b.getValue(), a.getValue()))
                .collect(Collectors.toList());
    }

    public static Map<Integer, Double> mediaPorAnioEquipo(int equipoId, List<Corredor> corredores) {
        Map<Integer, List<Float>> temp = new HashMap<>();
        for (Corredor c : corredores) {
            if (c.getEquipoId()!=equipoId) continue;
            for (Puntuacion p : c.getPuntuaciones())
                temp.computeIfAbsent(p.getAnio(), k->new ArrayList<>()).add(p.getPuntos());
        }
        Map<Integer, Double> res = new HashMap<>();
        for (var e: temp.entrySet())
            res.put(e.getKey(), e.getValue().stream().mapToDouble(Float::doubleValue).average().orElse(0d));
        return res;
    }

    public static List<Corredor> corredoresHuerfanos(List<Corredor> corredores, List<Equipo> equipos) {
        Map<Integer, Boolean> activos = new HashMap<>();
        for (Equipo e : equipos) activos.put(e.getId(), e.isActivo());
        List<Corredor> out = new ArrayList<>();
        for (Corredor c : corredores) {
            Boolean ok = activos.get(c.getEquipoId());
            if (ok == null || !ok) out.add(c);
        }
        return out;
    }
}
