/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author Real
 */
public class ClienteDAO {

    public void InserirCliente(Cliente cliente) throws SQLException {

        String SQL = "INSERT INTO cadastros.cliente (id, nome, cpf, telefone) values (?, ?, ?, ?)";

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

        stmt.setInt(1, 0);
        stmt.setString(2, cliente.getNome());
        stmt.setString(3, cliente.getCpf());
        stmt.setString(4, cliente.getTelefone());

        stmt.execute();
        stmt.close();
    }

    /*public void SalvarData(Cliente cliente) throws SQLException {
        String SQL = "INSERT INTO cadastros.GetData (data) values (?)";

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            stmt.setDate(1, Date.valueOf(cliente.getDetea()));

            stmt.execute();
            stmt.close();
    }*/
    public void RemoverCliente(Cliente cliente) throws SQLException {
        String SQL = "Delete from cadastros.cliente where id=?";

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        stmt.setInt(1, cliente.getId());

        stmt.execute();
        stmt.close();
    }

    public void AlterarCliente(Cliente cliente) throws SQLException {
        String SQL = "update cadastros.cliente set nome=?, cpf=?, descricao=? where id=?";

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getCpf());
        stmt.setString(3, cliente.getTelefone());
        //stmt.setDate(4, cliente.getSaida_concerto());
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
                        rs.getString("cpf"),
                        rs.getString("telefone")
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

        if (cliente.getNome() != null && cliente.getCpf() == null) {
            SQL += "where Nome like ?";
        } else if (cliente.getNome() == null && cliente.getCpf() != null) {
            SQL += "where CPF like ?";
        } else if (cliente.getNome() != null && cliente.getCpf() != null) {
            SQL += "where Nome like ? AND CPF LIKE ?";
        }

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

        if (cliente.getNome() != null && cliente.getCpf() == null) {
            stmt.setString(1, "%" + cliente.getNome() + "%");
        } else if (cliente.getNome() == null && cliente.getCpf() != null) {
            stmt.setString(1, "%" + cliente.getCpf() + "%");
        } else if (cliente.getNome() != null && cliente.getCpf() != null) {
            stmt.setString(1, "%" + cliente.getNome() + "%");
            stmt.setString(2, "%" + cliente.getCpf() + "%");
        }
        try {

            ResultSet rs = stmt.executeQuery();
            System.out.println(SQL);
            while (rs.next()) {
                retorno.add(new Cliente(rs.getInt("id"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone")
                ));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return retorno;
    }
}
