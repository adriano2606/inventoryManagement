package br.com.fiap.fiapstore.model;

public class Categoria {

    private int cod_categoria;
    private String nome_categoria;

    public Categoria() {}

    public Categoria(int cod_categoria, String nome_categoria) {
        this.cod_categoria = cod_categoria;
        this.nome_categoria = nome_categoria;
    }

    public int getCod_categoria() {
        return cod_categoria;
    }


    public void setCod_categoria(int cod_categoria) {
        this.cod_categoria = cod_categoria;
    }

    public String getNome_categoria() {
        return nome_categoria;
    }

    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }
}
