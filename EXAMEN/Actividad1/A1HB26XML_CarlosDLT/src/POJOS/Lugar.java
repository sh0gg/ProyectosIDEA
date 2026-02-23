
package POJOS;
public class Lugar {

    private int id;
    private String lugar;
    //Mapeo del lugar del departamento 
    private Departamento departamento;

    public Lugar() {}

    public Lugar(String lugar) {
        this.lugar = lugar;
    }
    

    public int getId() { return id; }
    public void setId(int  id) { this.id = id; }

    public String getLugar() { return lugar; }
    public void setLugar(String lugar) { this.lugar = lugar; }

    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }
}
