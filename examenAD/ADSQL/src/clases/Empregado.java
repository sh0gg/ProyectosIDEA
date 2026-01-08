package clases;

public class Empregado {
    String nome;
    String apelido1;
    String apelido2;
    String nss;
    String rua;
    int num_calle;
    String piso;
    String cp;
    String localidade;
    String provincia;
    String data_nacemento;
    boolean sexo;
    String nssSupervisa;
    int numDepartamentoPertenece;
    int edad;


    public Empregado() {}

    public Empregado(String nome, String apelido1, String apelido2, int edad) {
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.edad = edad;
    }

    public Empregado(String nome, String apelido1, String apelido2) {
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
    }

    public Empregado(String nss, String nome, String apelido1, String apelido2, int edad) {
        this.nss=nss;
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.edad = edad;
    }

    public Empregado(String nss, String nome, String apelido1, String apelido2) {
        this.nss=nss;
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
    }

    public String getNomeEmpregado() {
        return nome;
    }

    public void setNomeEmpregado(String nombre) {
        this.nome = nombre;
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

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNum_calle() {
        return num_calle;
    }

    public void setNum_calle(int num_calle) {
        this.num_calle = num_calle;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getData_nacemento() {
        return data_nacemento;
    }

    public void setData_nacemento(String data_nacemento) {
        this.data_nacemento = data_nacemento;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        if (getEdad() > 0) {
            return getNss() + " - " + getNomeEmpregado() + " " + getApelido1() + " " + getApelido2() + ". Edad: " + getEdad();
        } else {
            return getNss() + " - " + getNomeEmpregado() + " " + getApelido1() + " " + getApelido2() + ".";
        }
    }

}
