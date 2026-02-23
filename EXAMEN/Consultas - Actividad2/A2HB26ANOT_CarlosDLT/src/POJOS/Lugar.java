package POJOS;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "LUGAR", schema = "dbo", catalog = "EMPRESAHB26",
//        Restricción: Impide que un mesmo departamento teña o mesmo nome de lugar duplicado. Hay una restriccion unique
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"Num_departamento", "Lugar"})
        })
public class Lugar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID")
    private int id;

    @Column(name = "Lugar", length = 15, nullable = false)
    private String lugar;
    //Mapeo del departamento que pertenece el lugar
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Num_departamento", nullable = false) // FK en la tabla LUGAR
    private Departamento departamento;

    public Lugar() {
    }

    public Lugar(String lugar) {
        this.lugar = lugar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

}
