package clase2025_09_26;

import static clase2025_09_26.EleccionesMunicipales.r;
import static clase2025_09_26.EleccionesMunicipales.votos;

public class Votar {
    static synchronized void votar() {
        votos[r().nextInt(5)]++;
    }
}
