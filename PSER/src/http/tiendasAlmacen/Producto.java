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

    public String getCod() {
        return cod;
    }

    public int getStock() {
        return stock;
    }

    public void addStock(int cantidad) {
        stock += cantidad;
    }

    @Override
    public String toString() {
        return nombre + " (" + cod + ") - Stock: " + stock;
    }

}
