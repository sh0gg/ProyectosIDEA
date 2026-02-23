package POJOS;

import java.util.HashSet;
import java.util.Set;


public class Departamento  implements java.io.Serializable {

     private int numDepartamento;
     private String nomeDepartamento;
     //mapeo de las funciones de un departamento
     private Set<String> funciones = new HashSet<>();
    // empregados que pertenecen a un departamento 
    private Set<Empregado> empregados = new HashSet(0);
     // el empleado fijo que es director. Cada departamento tiene que dirigirlo un empregado fixo diferente
     private Empregadofixo director;
    //mapeo de los proyectos que controla un departamento
     private Set<Proxecto> proxectos = new HashSet(0);
     
     //Mapeo de los lugares de los departamentos. Lugar entidad aparte
     private Set<Lugar> lugares = new HashSet<>();
 
       


    public Departamento() {
    }

    public Departamento(int numDepartamento, String nomeDepartamento) {
        this.numDepartamento = numDepartamento;
        this.nomeDepartamento = nomeDepartamento;
    }
    

    public int getNumDepartamento() {
        return numDepartamento;
    }

    public void setNumDepartamento(int numDepartamento) {
        this.numDepartamento = numDepartamento;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    public Set<String> getFunciones() {
        return funciones;
    }

    public void setFunciones(Set<String> funciones) {
        this.funciones = funciones;
    }

    public Set<Empregado> getEmpregados() {
        return empregados;
    }

    public void setEmpregados(Set<Empregado> empregados) {
        this.empregados = empregados;
    }

    public Empregadofixo getDirector() {
        return director;
    }

    public void setDirector(Empregadofixo director) {
        this.director = director;
    }

    public Set<Proxecto> getProxectos() {
        return proxectos;
    }

    public void setProxectos(Set<Proxecto> proxectos) {
        this.proxectos = proxectos;
    }

    public Set<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(Set<Lugar> lugares) {
        this.lugares = lugares;
    }

   

   
}

