
package POJOS;

import java.util.HashSet;
import java.util.Set;


public class Habilidade {    
    private int id;    
    private String nome;
    
    //--Mapeo de los empleados que tienen una habilidad       
    private Set<Empregado> empregados = new HashSet<>();
    public Habilidade() {
    }

    public Habilidade(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    

    public Set<Empregado> getEmpregados() {
        return empregados;
    }

    public void setEmpregados(Set<Empregado> empregados) {
        this.empregados = empregados;
    }

   
}

    

