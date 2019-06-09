/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Manutencao;

/**
 *
 * @author user
 */
public class ManutencaoDAO {
    public void InserirManutencao(Manutencao manutencao) throws SQLException {

        String SQL = "INSERT INTO cadastros.manutencao (id,descricao) values (?, ?)";

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

        stmt.setInt(1, 0);
        stmt.setString(2,manutencao.getDescricao());

        stmt.execute();
        stmt.close();
    }
    
     public void RemoverManutencao(Manutencao manutencao) throws SQLException {
        String SQL = "Delete from cadastros.manutencao where id=?";

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        stmt.setInt(1, manutencao.getId());

        stmt.execute();
        stmt.close();
     }
     
     public void AlterarManutencao(Manutencao manutencao) throws SQLException {
        String SQL = "update cadastros.manutencao set descricao=? where id=?";

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

        stmt.setString(1, manutencao.getDescricao());
        stmt.setInt(5, manutencao.getId());
        stmt.execute();
        stmt.close();
    }
    
}
