package br.com.fiap.fiapstore.dao.impl;

import br.com.fiap.fiapstore.dao.ConnectionManager;
import br.com.fiap.fiapstore.dao.ProdutoDao;
import br.com.fiap.fiapstore.exception.DBException;
import br.com.fiap.fiapstore.model.Categoria;
import br.com.fiap.fiapstore.model.Produto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleProdutoDao implements ProdutoDao {

    @Override
    public void cadastrar(Produto produto) throws DBException {

        PreparedStatement stmt = null;
        Connection conexao = ConnectionManager.getInstance().getConnection();
        String sql = "INSERT INTO TB_PRODUTO " +
                "(COD_PRODUTO, NOME_PRODUTO, QTDE_PRODUTO, " +
                "VALOR_PRODUTO, DATA_FABRICACAO, COD_CATEGORIA) " +
                "VALUES (SQ_TB_PRODUTO.NEXTVAL, ?, ?, ?, ?, ?)";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getValor());
            stmt.setDate(4, Date.valueOf(produto.getDataFabricacao()));
            stmt.setInt(5, produto.getCodigoCategoria());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao cadastrar.");
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void atualizar(Produto produto) throws DBException {

        PreparedStatement stmt = null;
        Connection conexao = ConnectionManager.getInstance().getConnection();

        try {

            String sql = "UPDATE TB_PRODUTO SET " +
                    "NOME_PRODUTO = ?, " +
                    "QTDE_PRODUTO = ?, " +
                    "VALOR_PRODUTO = ?, " +
                    "DATA_FABRICACAO = ?," +
                    "COD_CATEGORIA = ?" +
                    "WHERE COD_PRODUTO = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getValor());
            stmt.setDate(4, Date.valueOf(produto.getDataFabricacao()));
            stmt.setInt(5, produto.getCodigoCategoria());
            stmt.setInt(6, produto.getCodigo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar.");
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void remover(int codigo) throws DBException {

        PreparedStatement stmt = null;

        Connection conexao = ConnectionManager.getInstance().getConnection();
        try {
            String sql = "DELETE FROM TB_PRODUTO WHERE COD_PRODUTO = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao remover.");
        } finally {
            try {
                conexao.close();
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public Produto buscar(int id) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conexao = ConnectionManager.getInstance().getConnection();
        String sql = "SELECT COD_PRODUTO, NOME_PRODUTO, VALOR_PRODUTO, DATA_FABRICACAO, QTDE_PRODUTO, NOME_CATEGORIA FROM TB_PRODUTO tbp JOIN TB_CATEGORIA tbc ON tbc.COD_CATEGORIA = tbp.COD_CATEGORIA WHERE COD_PRODUTO = ?";
        Produto produto = null;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()){
                int codigo = rs.getInt("COD_PRODUTO");
                String nome = rs.getString("NOME_PRODUTO");
                double valor = rs.getDouble("VALOR_PRODUTO");
                int quantidade = rs.getInt("QTDE_PRODUTO");
                LocalDate dataFabricacao = rs.getDate("DATA_FABRICACAO").toLocalDate();
                String nomeCategoria = rs.getString("NOME_CATEGORIA");
                produto = new Produto(codigo, nome, valor, quantidade, dataFabricacao,0, nomeCategoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conexao.close();
                stmt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return produto;
    }

    @Override
    public List<Produto> listar() {

        PreparedStatement stmt = null;
        Connection conexao = ConnectionManager.getInstance().getConnection();
        List<Produto> lista = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT COD_PRODUTO, NOME_PRODUTO, VALOR_PRODUTO, DATA_FABRICACAO, QTDE_PRODUTO, NOME_CATEGORIA FROM TB_PRODUTO tbp JOIN TB_CATEGORIA tbc ON tbc.COD_CATEGORIA = tbp.COD_CATEGORIA";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codigo = rs.getInt("COD_PRODUTO");
                String nome = rs.getString("NOME_PRODUTO");
                double valor = rs.getDouble("VALOR_PRODUTO");
                int quantidade = rs.getInt("QTDE_PRODUTO");
                LocalDate dataFabricacao = rs.getDate("DATA_FABRICACAO").toLocalDate();
                String nomeCategoria = rs.getString("NOME_CATEGORIA");
                Produto produto = new Produto(codigo, nome, valor, quantidade, dataFabricacao,0, nomeCategoria);
                lista.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conexao.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lista;
    }


}
