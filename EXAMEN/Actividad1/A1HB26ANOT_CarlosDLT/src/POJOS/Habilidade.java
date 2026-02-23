
package POJOS;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "HABILIDADE")
public class Habilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDHABILIDADE")
    private int id;
    @Column(name = "NOME", length = 50, nullable = false, unique = true)
    private String nome;
    
    //--Mapeo de los empleados que tienen una habilidad
    /*
    mappedBy = "habilidades" significa:?Yo no soy el propietario. El propietario es el atributo habilidades dentro de Empregado.?
    */
    @ManyToMany(mappedBy = "habilidades") // --- Relación Many-to-Many inversa ---
    private Set<Empregado> empregados = new HashSet<>();

    public Habilidade() {
    }

    public Habilidade(String nome) {
        this.nome = nome;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    

    public Set<Empregado> getEmpregados() {
        return empregados;
    }

    public void setEmpregados(Set<Empregado> empregados) {
        this.empregados = empregados;
    }

   
}

    


