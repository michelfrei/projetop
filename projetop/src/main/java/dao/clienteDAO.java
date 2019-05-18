/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author Real
 */
public class clienteDAO {

    public void InserirArea(Cliente cliente) throws SQLException {

        String SQL = "INSERT INTO cadastros.cliente (id, nome, cpf, descricao, saida_concerto) values (?, ?, ?, ?, ?)";

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            stmt.setInt(1, 0);
            stmt.setString(2, cliente.getNome());
            stmt.setInt(3, cliente.getCpf());
            stmt.setString(4, cliente.getDescricao());
            stmt.setDate(4, cliente.getSaida_concerto());

            stmt.execute();
            stmt.close();
    }

    public void RemoverArea(Cliente cliente) throws SQLException {
        String SQL = "Delete from cadastros.cliente where id=?";

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            stmt.setInt(1, cliente.getId());

            stmt.execute();
            stmt.close();
    }

    public void AlterarArea(Cliente cliente) throws SQLException {
        String SQL = "update cadastros.cliente set nome=?, cpf=?, descricao=?, saida_concerto=? where id=?";

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getCpf());
            stmt.setString(3, cliente.getDescricao());
            stmt.setDate(4, cliente.getSaida_concerto());
            stmt.setInt(5, cliente.getId());
            stmt.execute();
            stmt.close();
    }

    public List<Cliente> ListaCliente() throws SQLException {

        List<Cliente> ListaCliente;
        ListaCliente = new ArrayList<>();

        String SQL = "select* from cadastros.cliente";
        try {

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ListaCliente.add(new Cliente(rs.getInt("id"),
                        rs.getString("Nome"),
                        rs.getInt("cpf"),
                        rs.getString("descricao"),
                        rs.getDate("saida_concerto")                
                ));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ListaCliente;
    }

    public List<Cliente> ListaBuscaCliente(Cliente cliente) throws SQLException {
        List<Cliente> retorno = new ArrayList<Cliente>();

        String SQL = "select * from cadastros.cliente ";
        
        
        if(cliente.getNome() != null){
            SQL += "where Nome like ?";
        }
        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        
        if(cliente.getNome() != null){
            stmt.setString(1, "%" + cliente.getNome() + "%");
        }

        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                retorno.add(new Cliente(rs.getInt("id"),
                        rs.getString("Nome"),
                        rs.getInt("cpf"),
                        rs.getString("descricao"),
                        rs.getDate("saida_concerto")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        stmt.close();
        Conexao.getConexaoMySQL().close();

        return retorno;
    }

}
