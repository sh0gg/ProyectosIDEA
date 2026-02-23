package POJOS;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICULO", schema = "dbo", catalog = "EMPRESAHB26")
public class Vehiculo implements java.io.Serializable {

    @Id
    @Column(name = "NSS", length = 15)
    private String nss;

    @Column(name = "Matricula", length = 10, unique = true)
    private String matricula;

    @Column(name = "Marca", length = 15)
    private String marca;

    @Column(name = "Modelo", length = 25)
    private String modelo;

    @Column(name = "DataCompra")
    private LocalDate dataCompra;

    //Empleado de vehiculo
    //Vehiculo no genera clave propia
    //VEHICULO é o lado propietario   
    //Hibernate só pode poñer @JoinColumn no lado propietario.
    //Un vehículo no puede existir sin empleado. La tabla VEHICULO usa como clave primaria el mismo NSS que EMPREGADO
    @OneToOne
    @MapsId //La PK de Vehiculo es la misma que la PK de Empregado
    @JoinColumn(name = "NSS", referencedColumnName = "NSS") //La columna NSS es FK    
    private Empregado empregado;

    public Vehiculo() {
    }

    public Vehiculo(Empregado empregado) {
        this.empregado = empregado;
    }

    public Vehiculo(Empregado empregado, String matricula, String marca, String modelo, LocalDate dataCompra) {
        this.empregado = empregado;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.dataCompra = dataCompra;
    }

    public String getNss() {
        return this.nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    

    public Empregado getEmpregado() {
        return this.empregado;
    }

    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public  LocalDate getDataCompra() {
        return this.dataCompra;
    }

    public void setDataCompra( LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

}
