package hilos.examenRECDBR;

public class Bebida {
    int id;
    boolean isAlcoholica;

    public Bebida(int id){
        this.id = id;
        double random = Math.random();
        if (random<=0.5){
            isAlcoholica = true;
        }
    }

    public int getId() {
        return id;
    }

    public boolean isAlcoholica() {
        return isAlcoholica;
    }
}
