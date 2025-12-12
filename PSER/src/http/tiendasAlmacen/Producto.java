package http.tiendasAlmacen;

public class Producto {
    String cod;
    String nombre;
    int stock;

    public Producto(String cod, String nombre, int stock) {
        this.cod = cod;
        this.nombre = nombre;
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
