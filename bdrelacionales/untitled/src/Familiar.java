package es.ieschandomonte.ud3.empresa25.modelo;

import java.time.LocalDate;

public class Familiar {

    private String nssEmpregado;
    private int numFamiliar;      // Número interno por empregado
    private String nssFamiliar;
    private String nome;
    private String apelido1;
    private String apelido2;
    private LocalDate dataNacemento;
    private String parentesco;
    private String sexo;          // 'H' ou 'M'

    // Getters / setters / toString

    public String getNssEmpregado() {
        return nssEmpregado;
    }

    public void setNssEmpregado(String nssEmpregado) {
        this.nssEmpregado = nssEmpregado;
    }

    public int getNumFamiliar() {
        return numFamiliar;
    }

    public void setNumFamiliar(int numFamiliar) {
        this.numFamiliar = numFamiliar;
    }

    public String getNssFamiliar() {
        return nssFamiliar;
    }

    public void setNssFamiliar(String nssFamiliar) {
        this.nssFamiliar = nssFamiliar;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido1() {
        return apelido1;
    }

    public void setApelido1(String apelido1) {
        this.apelido1 = apelido1;
    }

    public String getApelido2() {
        return apelido2;
    }

    public void setApelido2(String apelido2) {
        this.apelido2 = apelido2;
    }

    public LocalDate getDataNacemento() {
        return dataNacemento;
    }

    public void setDataNacemento(LocalDate dataNacemento) {
        this.dataNacemento = dataNacemento;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Familiar{" +
                "nssEmpregado='" + nssEmpregado + '\'' +
                ", numFamiliar=" + numFamiliar +
                ", nssFamiliar='" + nssFamiliar + '\'' +
                ", nome='" + nome + '\'' +
                ", apelido1='" + apelido1 + '\'' +
                ", apelido2='" + apelido2 + '\'' +
                ", dataNacemento=" + dataNacemento +
                ", parentesco='" + parentesco + '\'' +
                ", sexo='" + sexo + '\'' +
                '}';
    }
}
