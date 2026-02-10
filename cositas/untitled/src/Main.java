public class Main {
    public static void main(String[] args) {
        // Soldado básico
        Soldado recluta = new SoldadoBase("García");
        recluta.atacar();
        // Soldado García ataca causando 10 de daño

        // Soldado con rifle
        Soldado lopez = new SoldadoBase("López");
        Soldado fusilero = new Rifle(lopez);
        fusilero.atacar();
        // Soldado López + Rifle ataca causando 60 de daño

        // Soldado equipado al máximo (decoradores apilados)
        Soldado comandante = new ChalecoBlinado(
                new Granada(
                        new Rifle(
                                new Cuchillo(
                                        new SoldadoBase("Ramírez")))));

        System.out.println(comandante.getDescripcion());
        // Soldado Ramírez + Cuchillo + Rifle + Granada + Chaleco blindado

        System.out.println("Daño total: " + comandante.getDanio());
        // Daño total: 165 (10 + 25 + 50 + 80)

        comandante.atacar();
    }
}