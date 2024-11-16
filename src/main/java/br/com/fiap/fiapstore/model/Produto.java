package br.com.fiap.fiapstore.model;
import java.time.LocalDate;

public class Produto {

    private int codigo;
    private String nome;
    private double valor;
    private int quantidade;
    private LocalDate dataFabricacao;
    private int codigoCategoria;
    private String nomeCategoria;


    public Produto(){}

    public Produto(int codigo, String nome, double valor, int quantidade, LocalDate dataFabricacao, int codigoCategoria, String nomeCategoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.dataFabricacao = dataFabricacao;
        this.codigoCategoria = codigoCategoria;
        this.nomeCategoria = nomeCategoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public int getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(int codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
}
