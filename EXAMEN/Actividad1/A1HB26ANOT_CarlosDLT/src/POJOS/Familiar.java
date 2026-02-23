package POJOS;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Familiar implements java.io.Serializable {
  /*
 * Con anotaciones, @OrderColumn empieza en 0 por defecto.
 * Si la base de datos espera que el primer familiar tenga número 1,
 * necesitas un atributo 'numero' en la clase Familiar y calcularlo en código
 * al añadir un nuevo familiar. 
 * No se debe mapear como el índice que genera Hibernate automáticamente 
 * (es decir, no usar @OrderColumn). 
 * 'numero' se persiste como una columna más en la tabla FAMILIAR.
 */
    @Column(name = "Numero", nullable = false)
    private int numero;

    @Column(name = "NSS", length = 15, nullable = false)
    private String nss;

    @Column(name = "Nome", length = 25, nullable = false)
    private String nome;

    @Column(name = "Apelido1", length = 25, nullable = false)
    private String apelido1;

    @Column(name = "Apelido2", length = 25)
    private String apelido2;

    @Column(name = "Data_nacimento", columnDefinition = "DATE") //  columnDefinition é opcional, para forzar el tipo d datos en bd a date.
    private LocalDate dataNacimento;

    @Column(name = "Parentesco", length = 20)
    private String parentesco;

    @Column(name = "Sexo", length = 1)
    private Character sexo;

    public Familiar() {
    }

    public Familiar(String nss, String nome, String apelido1, String apelido2, LocalDate dataNacimento, String parentesco, Character sexo) {
        this.nss = nss;
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.dataNacimento = dataNacimento;
        this.parentesco = parentesco;
        this.sexo = sexo;
    }

    public Familiar(int numero, String nss, String nome, String apelido1, String apelido2, LocalDate dataNacimento, String parentesco, Character sexo) {
        this.numero = numero;
        this.nss = nss;
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.dataNacimento = dataNacimento;
        this.parentesco = parentesco;
        this.sexo = sexo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public LocalDate getDataNacimento() {
        return this.dataNacimento;
    }

    public void setDataNacimento(LocalDate dataNacimento) {
        this.dataNacimento = dataNacimento;
    }

    public String getParentesco() {
        return this.parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Character getSexo() {
        return this.sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }
}
