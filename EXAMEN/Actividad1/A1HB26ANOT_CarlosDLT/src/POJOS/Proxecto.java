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
@Table(name = "PROXECTO", schema = "dbo", catalog = "EMPRESAHB2_25")
public class Proxecto implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "NumProxecto")
    private int numProxecto;

    @Column(name = "NomeProxecto", unique = true, nullable = false, length = 25)
    private String nomeProxecto;

    @Column(name = "Lugar", nullable = false, length = 25)
    private String lugar;

    //mapear las fase de proyecto como componente
    @ElementCollection (fetch = FetchType.LAZY) //es el comportameiento por defecto. 
    @CollectionTable(
            name = "PROXECTOFASE",
            joinColumns = @JoinColumn(name = "NumProxecto")
    )
    private Set<ProxectoFase> fases = new HashSet<>();

      //Mapeo de del departamento que controla el proyecto
    //Este lado es el dueño de la relación porque contiene la clave ajena (FK).
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumDepartControla", nullable = false)
    private Departamento departamento;

    //los empleados de un proyecto
    //mappedBy = "proxecto" nombre de la columna en empleadoproxecto
    @OneToMany(mappedBy = "proxecto",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY )
    private Set <EmpregadoProxecto> empregados = new HashSet<>();

    public Proxecto() {
    }

    public Proxecto(int numProxecto, Departamento departamento, String nomeProxecto, String lugar) {
        this.numProxecto = numProxecto;
        this.departamento = departamento;
        this.nomeProxecto = nomeProxecto;
        this.lugar = lugar;
    }

    public Proxecto(int numProxecto, Departamento departamento, String nomeProxecto) {
        this.numProxecto = numProxecto;
        this.departamento = departamento;
        this.nomeProxecto = nomeProxecto;
    }

    public int getNumProxecto() {
        return this.numProxecto;
    }

    public void setNumProxecto(int numProxecto) {
        this.numProxecto = numProxecto;
    }

    public Departamento getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getNomeProxecto() {
        return this.nomeProxecto;
    }

    public void setNomeProxecto(String nomeProxecto) {
        this.nomeProxecto = nomeProxecto;
    }

    public String getLugar() {
        return this.lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Set<EmpregadoProxecto> getEmpregados() {
        return empregados;
    }

    public void setEmpregados(Set<EmpregadoProxecto> empregados) {
        this.empregados = empregados;
    }

 

    public Set<ProxectoFase> getFases() {
        return fases;
    }

    public void setFases(Set<ProxectoFase> fases) {
        this.fases = fases;
    }

}
