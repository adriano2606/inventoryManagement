package br.com.fiap.fiapstore.teste;

import br.com.fiap.fiapstore.dao.CategoriaDao;
import br.com.fiap.fiapstore.dao.ProdutoDao;
import br.com.fiap.fiapstore.factory.DaoFactory;
import br.com.fiap.fiapstore.model.Categoria;
import br.com.fiap.fiapstore.model.Produto;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CategoriaDaoTeste {

    public static void main(String[] args) {





        ProdutoDao dao = DaoFactory.getProdutoDao();

        Produto produto = dao.buscar(27);
        System.out.println(produto.getNome());


    }
}
