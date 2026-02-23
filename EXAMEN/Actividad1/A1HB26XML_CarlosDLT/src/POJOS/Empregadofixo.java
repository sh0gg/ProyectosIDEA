package POJOS;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class Empregadofixo extends Empregado implements java.io.Serializable {

    private Double salario;
    private LocalDate dataAlta;
    private String categoria;
    //departamento que pueden ser director. Solo puede ser de uno.
    private Departamento deptodirector;    
    // horas extras de un empleado fijo como map de componentes 
    private Map<LocalDate, Double> horasextras = new TreeMap();

    public Empregadofixo() {
    }

    public Empregadofixo(String nss) {
        super(nss);
    }

    public Empregadofixo(String nss, String nome, String apelido1) {
        super(nss, nome, apelido1);
    }

    public Empregadofixo(Double salario, LocalDate dataAlta, String categoria, String nss, String nome, String apelido1) {
        super(nss, nome, apelido1);
        this.salario = salario;
        this.dataAlta = dataAlta;
        this.categoria = categoria;
    }

    public Empregadofixo(String nss, String nome, String apelido1, String apelido2,LocalDate dataNacemento,
            Character sexo,Enderezo enderezo,
            Double salario, LocalDate dataAlta, String categoria) {

        super(nss, nome, apelido1, apelido2, dataNacemento, sexo, enderezo);

        this.salario = salario;
        this.dataAlta = dataAlta;
        this.categoria = categoria;
    }

    public Double getSalario() {
        return this.salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public LocalDate getDataAlta() {
        return this.dataAlta;
    }

    public void setDataAlta(LocalDate dataAlta) {
        this.dataAlta = dataAlta;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Departamento getDeptodirector() {
        return deptodirector;
    }

    public void setDeptodirector(Departamento deptodirector) {
        this.deptodirector = deptodirector;
    }

    public Map<LocalDate, Double> getHorasextras() {
        return horasextras;
    }

    public void setHorasextras(Map<LocalDate, Double> horasextras) {
        this.horasextras = horasextras;
    }

}
