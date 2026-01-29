package POJOS;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Empregado implements java.io.Serializable {

    private String nss;
    private String nome;
    private String apelido1;
    private String apelido2;
    private LocalDate dataNacemento;
    private Character sexo;
    private Enderezo enderezo;
    // mapeo componente telefono
    private Map<String,String> telefonos = new HashMap<String,String>();
    // mapeo componente familiar
    private List<Familiar> familiares = new ArrayList<Familiar>();

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

    public Empregado(String nss, String nome, String apelido1, String apelido2, Character sexo, Enderezo enderezo) {
        this.nss = nss;
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.sexo = sexo;
        this.enderezo = enderezo;
    }

    public Empregado(String nss, String nome, String apelido1, String apelido2, LocalDate dataNacemento, Character sexo, Enderezo enderezo, Map<String, String> telefonos) {
        this.nss = nss;
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.dataNacemento = dataNacemento;
        this.sexo = sexo;
        this.enderezo = enderezo;
        this.telefonos = telefonos;
    }

    public Empregado(String nss, String nome, String apelido1, String apelido2, LocalDate dataNacemento, Character sexo, Enderezo enderezo, Map<String, String> telefonos, List<Familiar> familiares) {
        this.nss = nss;
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.dataNacemento = dataNacemento;
        this.sexo = sexo;
        this.enderezo = enderezo;
        this.telefonos = telefonos;
        this.familiares = familiares;
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
}
