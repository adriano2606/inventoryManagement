package br.com.fiap.fiapstore.teste;

import br.com.fiap.fiapstore.dao.ProdutoDao;
import br.com.fiap.fiapstore.exception.DBException;
import br.com.fiap.fiapstore.factory.DaoFactory;
import br.com.fiap.fiapstore.model.Produto;

import java.time.LocalDate;
import java.util.List;

public class ProdutoDaoTeste {

    public static void main(String[] args) {

        ProdutoDao dao = DaoFactory.getProdutoDao();


//        Cadastrar
//        Produto produtoTeste = new Produto(0, "Seila", 150.99, 10, LocalDate.of(2024, 11, 10));
//        try {
//            dao.cadastrar(produtoTeste);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

//       Buscar
//        Produto produto = dao.buscar(1);
//        System.out.println(produto.getCodigo());
//        System.out.println(produto.getNome());
//        System.out.println(produto.getValor());
//        System.out.println(produto.getQuantidade());
//        System.out.println(produto.getDataFabricacao());

//        Remover
//        try {
//            dao.remover(1);
//        } catch (DBException e) {
//            System.out.println(e.getMessage());
//        }


// Buscar todos
//        List<Produto> listaProdutos = dao.listar();
//        for (Produto produto: listaProdutos){
//            System.out.println(produto.getCodigo());
//            System.out.println(produto.getNome());
//            System.out.println(produto.getValor());
//            System.out.println(produto.getQuantidade());
//            System.out.println(produto.getDataFabricacao());
//        }

    }


}
