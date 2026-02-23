package POJOS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Empregado implements java.io.Serializable {

    private String nss;
    private String nome;
    private String apelido1;
    private String apelido2;
    private LocalDate dataNacemento;
    private Character sexo;
    //Mapeo como componente
    private Enderezo enderezo;
    //mapeo los telefonos como una colección de componentes map
    private Map<String, String> telefonos = new HashMap();

    //mapeo del departamento que pertenece un empleado
    private Departamento departamento;

    //mapear familiares como colección  de componentes List
    private List<Familiar> familiares = new ArrayList();

    // Mapear las Habilidadades de los empregdos 
    private Set<Habilidade> habilidades = new HashSet();

    //mapeo del vehiculo del empleado
    private Vehiculo vehiculo;

    //mapeo de los proxectoEmpleado
    private Set<EmpregadoProxecto> proxectos = new HashSet(0);

    //se guardamos el superivisor de un empleao
    private Empregado supervisor;
    //mapeo de los supervisados que supervisa un empleado supervisor
    private Set<Empregado> supervisados = new HashSet(0);

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

    public Set<Habilidade> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Set<Habilidade> habilidades) {
        this.habilidades = habilidades;
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

    public Set<EmpregadoProxecto> getProxectos() {
        return proxectos;
    }

    public void setProxectos(Set<EmpregadoProxecto> proxectos) {
        this.proxectos = proxectos;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

}
