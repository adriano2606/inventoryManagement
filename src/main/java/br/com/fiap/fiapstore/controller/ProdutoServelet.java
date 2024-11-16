package br.com.fiap.fiapstore.controller;

import br.com.fiap.fiapstore.dao.CategoriaDao;
import br.com.fiap.fiapstore.dao.ProdutoDao;
import br.com.fiap.fiapstore.exception.DBException;
import br.com.fiap.fiapstore.factory.DaoFactory;
import br.com.fiap.fiapstore.model.Categoria;
import br.com.fiap.fiapstore.model.Produto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@WebServlet ("/produtos")
public class ProdutoServelet extends HttpServlet {

    private ProdutoDao daoProduto;
    private CategoriaDao daoCategoria;


    @Override
    public void init() throws ServletException {
        daoProduto = DaoFactory.getProdutoDao();
        daoCategoria = DaoFactory.getCategoriaDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        switch(acao) {
            case "cadastrar":
                cadastrar(req, resp);
                break;
            case "editar":
                editar(req,resp);
                break;
            case "excluir":
                excluir(req,resp);
                break;
            default:

        }

    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        double valor = Double.parseDouble(req.getParameter("valor"));
        int quantidade = Integer.parseInt(req.getParameter("quantidade"));
        LocalDate fabricacao = LocalDate.parse(req.getParameter("fabricacao"));
        int codCategoria = Integer.parseInt(req.getParameter("categoria"));

        Produto produto = new Produto(0, nome, valor, quantidade, fabricacao, codCategoria, "");

        try {
            daoProduto.cadastrar(produto);
            req.setAttribute("mensagem", "Produto cadastrado com sucesso!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar produto");
        }

        req.getRequestDispatcher("cadastro-produto.jsp").forward(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(req.getParameter("codigo"));
            String nome = req.getParameter("nome");
            int quantidade = Integer.parseInt(req.getParameter("quantidade"));
            double preco = Double.parseDouble(req.getParameter("valor"));
            LocalDate fabricacao = LocalDate
                    .parse(req.getParameter("fabricacao"));
            int codCategoria = Integer.parseInt(req.getParameter("categoria"));
            System.out.println(codCategoria);


            Produto produto = new Produto(codigo, nome, preco, quantidade, fabricacao, codCategoria, "");
            daoProduto.atualizar(produto);

            req.setAttribute("msg", "Produto atualizado!");
        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados");
        }
        listar(req,resp);
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int codigo = Integer.parseInt(req.getParameter("codigoExcluir"));
        try {
            daoProduto.remover(codigo);
            req.setAttribute("msg", "Produto removido!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar");
        }
        listar(req,resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String acao = req.getParameter("acao");
        switch (acao){
            case "listar":
                listar(req, resp);
                break;
            case "abrir-form-edicao":
                abrirForm(req, resp);
            case "abrir-form-cadastro":
                abrirFormCadastro(req,resp);
            default:
                listar(req, resp);
        }
    }

    private void abrirFormCadastro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Categoria> listaCategoria = daoCategoria.listarCategorias();
        req.setAttribute("listaCategoria", listaCategoria);
        req.getRequestDispatcher("cadastro-produto.jsp").forward(req, resp);
    }

    private void abrirForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("codigo"));
        Produto produto = daoProduto.buscar(id);
        List<Categoria> listaCategoria = daoCategoria.listarCategorias();
        req.setAttribute("listaCategoria", listaCategoria);
        req.setAttribute("produto", produto);
        req.getRequestDispatcher("editar-produto.jsp")
                .forward(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Produto> produtoList = daoProduto.listar();
        req.setAttribute("produtos", produtoList);
        req.getRequestDispatcher("lista-produto.jsp").forward(req, resp);
    }
}
