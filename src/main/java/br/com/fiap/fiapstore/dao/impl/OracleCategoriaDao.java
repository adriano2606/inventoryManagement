package br.com.fiap.fiapstore.dao.impl;

import br.com.fiap.fiapstore.dao.CategoriaDao;
import br.com.fiap.fiapstore.dao.ConnectionManager;
import br.com.fiap.fiapstore.model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleCategoriaDao implements CategoriaDao {


    @Override
    public List<Categoria> listarCategorias() {
        PreparedStatement stmt = null;
        Connection conexao = ConnectionManager.getInstance().getConnection();
        ResultSet result = null;
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM TB_CATEGORIA";

        try {
            stmt = conexao.prepareStatement(sql);
            result = stmt.executeQuery();
            while (result.next()) {
                int codCategoria = result.getInt("COD_CATEGORIA");
                String nomeCategoria = result.getString("NOME_CATEGORIA");
                Categoria categoria = new Categoria(codCategoria, nomeCategoria);
                lista.add(categoria);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
                stmt.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return lista;

    }
}
