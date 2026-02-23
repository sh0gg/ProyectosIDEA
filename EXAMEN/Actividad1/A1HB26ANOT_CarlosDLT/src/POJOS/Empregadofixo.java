package POJOS;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "EMPREGADOFIXO", schema = "dbo", catalog = "EMPRESAHB26")
//Mapeo de herencia JOINED en Hibernate.
@PrimaryKeyJoinColumn(name = "NSS") // Se enlaza con la clave primaria de EMPREGADO
public class Empregadofixo extends Empregado implements java.io.Serializable {

    @Column(name = "Salario")
    private Double salario;

    @Column(name = "DataAlta")
    private LocalDate dataAlta;

    @Column(name = "Categoria", length = 20)
    private String categoria;

//    // horas extras de un empleado fijo como componente 
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "HORASEXTRAS",
            schema = "dbo",
            joinColumns = @JoinColumn(name = "NSS") // FK que conecta con Empregadofixo
    )
    @MapKeyColumn(name = "Data") // La columna 'Data' ser  la clave del Map
    @Column(name = "HorasExtras") // La columna 'HorasExtras' ser  el valor del Map
    private Map<LocalDate, Double> horasextras = new HashMap<>();

    //departamentos que pueden ser director    
    @OneToOne(mappedBy = "director", fetch = FetchType.LAZY)
    private Departamento deptodirector;

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

    public Empregadofixo(String nss, String nome, String apelido1, String apelido2, LocalDate dataNacemento,
            Character sexo, Enderezo enderezo,
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
