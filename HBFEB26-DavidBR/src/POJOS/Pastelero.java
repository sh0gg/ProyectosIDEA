package POJOS;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Autor: David Besada Ramilo (53612286E)

public class Pastelero implements Serializable {

    private String codigo;
    private String nome;
    private String apelidos;
    private String alias;
    private LocalDate datanacemento;
    private Character sexo;
    private Contacto contacto;
    private Map<String,String> tecnicas = new HashMap<>();
    private Set<Pasteleria> pastelerias = new HashSet<>();
    private Certificacion certificacion;

    public Pastelero() {
    }

    public Pastelero(String codigo, String nome, String apelidos, String alias, LocalDate datanacemento, Character sexo) {
        this.codigo = codigo;
        this.nome = nome;
        this.apelidos = apelidos;
        this.alias = alias;
        this.datanacemento = datanacemento;
        this.sexo = sexo;
    }

    public Pastelero(String codigo, String nome, String apelidos, String alias, LocalDate datanacemento, Character sexo, Contacto contacto) {
        this.codigo = codigo;
        this.nome = nome;
        this.apelidos = apelidos;
        this.alias = alias;
        this.datanacemento = datanacemento;
        this.sexo = sexo;
        this.contacto = contacto;
    }

    public Pastelero(String codigo, String nome, String apelidos, String alias, LocalDate datanacemento, Character sexo, Contacto contacto, Map<String, String> tecnicas) {
        this.codigo = codigo;
        this.nome = nome;
        this.apelidos = apelidos;
        this.alias = alias;
        this.datanacemento = datanacemento;
        this.sexo = sexo;
        this.contacto = contacto;
        this.tecnicas = tecnicas;
    }

    public Pastelero(String codigo, String nome, String apelidos, String alias, LocalDate datanacemento, Character sexo, Contacto contacto, Map<String, String> tecnicas, Set<Pasteleria> pastelerias) {
        this.codigo = codigo;
        this.nome = nome;
        this.apelidos = apelidos;
        this.alias = alias;
        this.datanacemento = datanacemento;
        this.sexo = sexo;
        this.contacto = contacto;
        this.tecnicas = tecnicas;
        this.pastelerias = pastelerias;
    }

    public Pastelero(String codigo, String nome, String apelidos, String alias, LocalDate datanacemento, Character sexo, Contacto contacto, Map<String, String> tecnicas, Set<Pasteleria> pastelerias, Certificacion certificacion) {
        this.codigo = codigo;
        this.nome = nome;
        this.apelidos = apelidos;
        this.alias = alias;
        this.datanacemento = datanacemento;
        this.sexo = sexo;
        this.contacto = contacto;
        this.tecnicas = tecnicas;
        this.pastelerias = pastelerias;
        this.certificacion = certificacion;
    }

    public Certificacion getCertificacion() {
        return certificacion;
    }

    public void setCertificacion(Certificacion certificacion) {
        this.certificacion = certificacion;
    }

    public Set<Pasteleria> getPastelerias() {
        return pastelerias;
    }

    public void setPastelerias(Set<Pasteleria> pastelerias) {
        this.pastelerias = pastelerias;
    }

    public Map<String, String> getTecnicas() {
        return tecnicas;
    }

    public void setTecnicas(Map<String, String> tecnicas) {
        this.tecnicas = tecnicas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelidos() {
        return apelidos;
    }

    public void setApelidos(String apelidos) {
        this.apelidos = apelidos;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public LocalDate getDatanacemento() {
        return datanacemento;
    }

    public void setDatanacemento(LocalDate datanacemento) {
        this.datanacemento = datanacemento;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }


}
