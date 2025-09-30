package serializacionObjetos;

public class Puntuacion {
    int ano;
    float puntos;

    Puntuacion(int ano, float puntos) {
        this.ano = ano;
        this.puntos = puntos;
    }

    public static Puntuacion of(int ano, float puntos) {
        return new Puntuacion(ano, puntos);
    }
}
