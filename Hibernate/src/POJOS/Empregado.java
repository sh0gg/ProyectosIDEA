package POJOS;

import java.time.LocalDate;
import java.util.*;

public class Empregado implements java.io.Serializable {

    private String nss;
    private String nome;
    private String apelido1;
    private String apelido2;
    private LocalDate dataNacemento;
    private Character sexo;
    //Mapeo como componente
    private Enderezo enderezo;
    //mapeo componente los telefonos
    private Map<String, String> telefonos = new HashMap();

    //mapear familiares como coleccion List
    private List<Familiar> familiares = new ArrayList();

    //se guardamos el superivisor de un empleao
    private Empregado supervisor;
    //mapeo de los supervisados que supervisa un empleado supervisor
    private Set<Empregado> supervisados = new HashSet(0);
    //mapeo del departamento que pertenece un empleado
    private Departamento departamento;
    //mapeo de los empreados proyectos 
    private Set<EmpregadoProxecto> empregadoProxectos = new HashSet(0);

    //mapeo del vehiculo del empleado
    private Vehiculo vehiculo;

    private Set<Habilidade> habilidades = new HashSet<>();


    public Empregado() {
    }

    public Empregado(String nss) {
        this.nss = nss;
    }

    public Empregado(String nss, String nome, String apelido1) {
        this.nss = nss;
        this.nome = nome;
        this.apelido1 = apelido1;
    }

    public Empregado(String nss, String nome, String apelido1, String apelido2, LocalDate dataNacemento, Character sexo) {
        this.nss = nss;
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.dataNacemento = dataNacemento;
        this.sexo = sexo;
    }

    public Empregado(String nss, String nome, String apelido1, String apelido2, LocalDate dataNacemento, Character sexo, Enderezo enderezo, Map<String, String> telefonos, List<Familiar> familiares, Empregado supervisor, Set<Empregado> supervisados, Departamento departamento, Set<EmpregadoProxecto> empregadoProxectos, Vehiculo vehiculo, Set<Habilidade> habilidades) {
        this.nss = nss;
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.dataNacemento = dataNacemento;
        this.sexo = sexo;
        this.enderezo = enderezo;
        this.telefonos = telefonos;
        this.familiares = familiares;
        this.supervisor = supervisor;
        this.supervisados = supervisados;
        this.departamento = departamento;
        this.empregadoProxectos = empregadoProxectos;
        this.vehiculo = vehiculo;
        this.habilidades = habilidades;
    }

    public String getNss() {
        return this.nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
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

    public Enderezo getEnderezo() {
        return enderezo;
    }

    public void setEnderezo(Enderezo enderezo) {
        this.enderezo = enderezo;
    }

    public Map<String, String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Map<String, String> telefonos) {
        this.telefonos = telefonos;
    }

    public List<Familiar> getFamiliares() {
        return familiares;
    }

    public void setFamiliares(List<Familiar> familiares) {
        this.familiares = familiares;
    }

    public Empregado getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Empregado supervisor) {
        this.supervisor = supervisor;
    }

    public Set<Empregado> getSupervisados() {
        return supervisados;
    }

    public void setSupervisados(Set<Empregado> supervisados) {
        this.supervisados = supervisados;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Set<EmpregadoProxecto> getEmpregadoProxectos() {
        return empregadoProxectos;
    }

    public void setEmpregadoProxectos(Set<EmpregadoProxecto> empregadoProxectos) {
        this.empregadoProxectos = empregadoProxectos;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }


    public Set<Habilidade> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Set<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }
}
