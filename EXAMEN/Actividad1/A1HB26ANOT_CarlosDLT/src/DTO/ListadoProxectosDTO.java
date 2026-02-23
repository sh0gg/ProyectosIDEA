package DTO;

public class ListadoProxectosDTO {
        private int numero;
        private String nome;
        private String lugar;

        public ListadoProxectosDTO(int numero, String nome, String lugar) {
            this.numero = numero;
            this.nome = nome;
            this.lugar = lugar;
        }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

       

    }
