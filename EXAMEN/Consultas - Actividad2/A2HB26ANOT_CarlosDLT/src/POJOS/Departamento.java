package POJOS;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "DEPARTAMENTO", schema = "dbo", catalog = "EMPRESAHB26")
public class Departamento implements java.io.Serializable {

    @Id  // Marca o campo como a chave primaria da entidade.
     @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment") //ño genera Hobernate
    @GenericGenerator(name = "increment", strategy = "increment")
    private int numDepartamento;

    @Column(name = "NomeDepartamento", length = 25, nullable = false, unique = true)  // Define as propiedades da columna na táboa da base de datos.
    private String nomeDepartamento;

// Mapeo das funcións dun departamento como valores simples (tipo valor). Cada función gárdase nunha táboa separada como un elemento da colección    // asociada ao departamento correspondente.
    @ElementCollection(fetch = FetchType.LAZY) //Indica que funciones é unha colección de valores simples (non entidades),Hibernate gardará estes valores nunha táboa separada.
    @CollectionTable(
            name = "DEPARTAMENTOFUNCION",
            joinColumns = @JoinColumn(name = "NumDepartamento")//Indica a columna que relaciona cada función co seu departamento.É a clave foránea cara á táboa DEPARTAMENTO.
    )
    @Column(name = "Funcion")
    private Set<String> funciones = new HashSet<>();

    // empregados que pertenecen a un departamento 
    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)  // Define unha relaciónn un-a-moitos entre Departamento e Empregado. 'mappedBy' indica o campo na entidade Empregado que mantÃ©n a relaciÃ³n.
    private Set<Empregado> empregados = new HashSet(0);

    // el empleado fijo que es director    
    @ManyToOne(fetch = FetchType.LAZY)  // Define unha relación moitos-a-un entre Departamento e Empregadofixo. FetchType.LAZY significa que os datos sÃ³ se cargan cando se necesitan.
    @JoinColumn(name = "NSSDirector", nullable = false)  // Especifica a columna de uniÃ³n na tÃ¡boa da base de datos.
    private Empregadofixo director;

    //mapeo de los lugares 
    //  Un departamento ten varios lugares
    // indica que o campo FK está definido na clase Lugar
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Lugar> lugares = new HashSet<>();

    //mapeo de los proyectos que controla un departamento
    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Proxecto> proxectos = new HashSet<>();

    public Departamento() {
    }

    public int getNumDepartamento() {
        return numDepartamento;
    }

    public void setNumDepartamento(int numDepartamento) {
        this.numDepartamento = numDepartamento;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    public Set<String> getFunciones() {
        return funciones;
    }

    public void setFunciones(Set<String> funciones) {
        this.funciones = funciones;
    }

    public Set<Empregado> getEmpregados() {
        return empregados;
    }

    public void setEmpregados(Set<Empregado> empregados) {
        this.empregados = empregados;
    }

    public Empregadofixo getDirector() {
        return director;
    }

    public void setDirector(Empregadofixo director) {
        this.director = director;
    }

    public Set<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(Set<Lugar> lugares) {
        this.lugares = lugares;
    }

    public Set<Proxecto> getProxectos() {
        return proxectos;
    }

    public void setProxectos(Set<Proxecto> proxectos) {
        this.proxectos = proxectos;
    }

    
}
