package randomAccessFiles;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Producto {

     // revisar este ejercicio

    private int id;
    private String nombre;
    private int stock;
    private float precio;

    public Producto(int id, String nombre, int stock, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }


    public static void main(String[] args) throws IOException {

        RandomAccessFile raf = new RandomAccessFile("productos.dat", "rw");

        Producto producto = new Producto(1, "Café", 3, 3.25f);

        int tamRegistro = 100;
        int bytesAEscribir = 4 + producto.getNombre().getBytes("UTF-8").length + 2 + 4 + 4;
        if (bytesAEscribir > tamRegistro) {
            throw new IllegalArgumentException("El registro excede el tamaño máximo permitido");
        }

        raf.seek(0);
        raf.writeInt(producto.getId());
        raf.writeUTF(producto.getNombre());
        raf.writeInt(producto.getStock());
        raf.writeFloat(producto.getPrecio());

        Producto producto2 = new Producto(2, "Huevo", 200, 0.25f);

        raf.seek(raf.length());
        raf.writeInt(producto2.getId());
        raf.writeUTF(producto2.getNombre());
        raf.writeInt(producto2.getStock());
        raf.writeFloat(producto2.getPrecio());

        // Una vez añadidos dos productos, vamos a cambiar el precio del café

        raf.seek(0);
        raf.writeInt(producto.getId());
        raf.writeUTF(producto.getNombre());
        raf.writeInt(producto.getStock());
        raf.writeFloat(4.75f);

        for (int i = 0; i > raf.length(); i++) {
            raf.seek(i);
            int id = raf.readInt();
            String nombre = raf.readUTF();
            int stock = raf.readInt();
            float precio = raf.readFloat();

            System.out.println("El producto: " + nombre + " tiene el id " + id + " y vale " + precio + ". Hay " + stock + " unidad(es).");
        }

    }
}

