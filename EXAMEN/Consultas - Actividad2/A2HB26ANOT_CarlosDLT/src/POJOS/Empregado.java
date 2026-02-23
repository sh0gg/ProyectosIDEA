package POJOS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "EMPREGADO", schema = "dbo", catalog = "EMPRESAHB26")
@Inheritance(strategy = InheritanceType.JOINED)
public class Empregado implements java.io.Serializable {

    @Id
    @Column(name = "NSS", length = 15,nullable = false)
    private String nss;

    @Column(name = "Nome", length = 25, nullable = false)
    private String nome;

    @Column(name = "Apelido1", length = 25, nullable = false)
    private String apelido1;

    @Column(name = "Apelido2", length = 25)
    private String apelido2;
  //El tipo de dato Date o Calendar hay que añadir @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DataNacemento", columnDefinition = "DATE") // columnDefinition = "DATE" es opcional, si gueremos que se guarde en bd coomo date
    private LocalDate dataNacemento;   

    @Column(name = "Sexo", length = 1)
    private Character sexo;
    //Guardamos o enderezo como un componente
    @Embedded
    private Enderezo enderezo;
    
    //mapear componente los telefonos
    @ElementCollection
    @CollectionTable(name = "TELEFONO", joinColumns = @JoinColumn(name = "NSS"))
    @MapKeyColumn(name = "Telefono")
    @Column(name = "Informacion")
    private Map<String, String> telefonos = new HashMap();
    
      //mapear familiares como colección List de componentes
    @ElementCollection
    @CollectionTable(name = "FAMILIAR", joinColumns = @JoinColumn(name = "NSS_empregado"))
    //@OrderColumn(name = "Numero") //Nota: en anotaciones el indice comienza en 0 y no se puede modificar. 
    //si queremos que empiece en 1 esta anotación  no se podria y lo generaríamos por código
    private List<Familiar> familiares = new ArrayList();

    //mapeo del departamento que pertenece un empleado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumDepartamentoPertenece")
    private Departamento departamento;
    
    // --- Mapeo de las Habilidades de un empregao  --- 
    //Este lado es el propietario ,Aquí defines la tabla intermedia @JoinTable
    //y las  las columnas NSS e IDHABILIDADE ( Clave FK)
    //esto implica ,Solo los cambios hechos en Empregado.getHabilidades() se guardan en BD, Hibernate usa este lado para:
    //insertar ,  borrar y actualizar relaciones
    
    @ManyToMany
    @JoinTable( name = "EMPREGADO_HABILIDADE",
    joinColumns = @JoinColumn(name = "NSS"),
    inverseJoinColumns = @JoinColumn(name = "IDHABILIDADE") )
    private Set<Habilidade> habilidades = new HashSet<>();
    
   //mapeo del vehiculo del empleado
    //Es el lado inverso.
     @OneToOne(mappedBy = "empregado", cascade = CascadeType.ALL)
      private Vehiculo vehiculo;
    
      //mapeo de los empreados proyectos 
     /*
     mappedBy = "empregado" indica que o lado propietario é EmpregadoProxecto
     Non se especifica a columna aquí porque é o lado inverso
     */
    @OneToMany(mappedBy = "empregado",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<EmpregadoProxecto> proxectos = new HashSet();
    
        
//se guardamos el superivisor de un empleao
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NSSSupervisa")  //es el lado propietario y se pone aqui la FK
    private Empregado supervisor;

    //mapeo de los supervisados que supervisa un empleado supervisor
    @OneToMany(mappedBy = "supervisor", fetch = FetchType.LAZY)
    private Set<Empregado> supervisados = new HashSet(0);

    public Empregado() {
    }

    public Empregado(String nss, String nome, String apelido1, String apelido2, LocalDate dataNacemento, Character sexo) {
        this.nss = nss;
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.dataNacemento = dataNacemento;
        this.sexo = sexo;
    }
     public Empregado(String nss,String nome, String apelido1, String apelido2,LocalDate dataNacemento,
            Character sexo,Enderezo enderezo) {

        this.nss = nss;
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.dataNacemento = dataNacemento;
        this.sexo = sexo;
        this.enderezo = enderezo;
    }

    public Empregado(String nss) {
        this.nss = nss;
    }

    public Empregado(String nss, String nome, String apelido1) {
        this.nss = nss;
        this.nome = nome;
        this.apelido1 = apelido1;
    }

    public String getNss() {
        return this.nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public Empregado getSupervisor() {
        return this.supervisor;
    }

    public Set<Empregado> getSupervisados() {
        return supervisados;
    }

    public Set<Habilidade> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Set<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }

    public void setSupervisados(Set<Empregado> supervisados) {
        this.supervisados = supervisados;
    }

    public void setSupervisor(Empregado supervisor) {
        this.supervisor = supervisor;
    }

    public Departamento getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido1() {
        return this.apelido1;
    }

    public void setApelido1(String apelido1) {
        this.apelido1 = apelido1;
    }

    public String getApelido2() {
        return this.apelido2;
    }

    public void setApelido2(String apelido2) {
        this.apelido2 = apelido2;
    }

    public LocalDate getDataNacemento() {
        return this.dataNacemento;
    }

    public void setDataNacemento(LocalDate dataNacemento) {
        this.dataNacemento = dataNacemento;
    }

    public Character getSexo() {
        return this.sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Map<String, String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Map<String, String> telefonos) {
        this.telefonos = telefonos;
    }

    public Set<EmpregadoProxecto> getProxectos() {
        return proxectos;
    }

    public void setProxectos(Set<EmpregadoProxecto> proxectos) {
        this.proxectos = proxectos;
    }

    public Vehiculo getVehiculo() {
        return this.vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Enderezo getEnderezo() {
        return enderezo;
    }

    public void setEnderezo(Enderezo enderezo) {
        this.enderezo = enderezo;
    }

    public List<Familiar> getFamiliares() {
        return familiares;
    }

    public void setFamiliares(List<Familiar> familiares) {
        this.familiares = familiares;
    }

   

}