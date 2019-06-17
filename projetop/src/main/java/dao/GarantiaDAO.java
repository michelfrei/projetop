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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Garantia;

/**
 *
 * @author Real
 */
public class GarantiaDAO {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public void InserirGarantia(Garantia garantia) throws SQLException {

        String SQL = "INSERT INTO cadastros.garantia (id, nome, descricao, saida_concerto, garantia) values (?, ?, ?, ?, ?)";

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

        stmt.setInt(1, 0);
        stmt.setString(2, garantia.getNome());
        stmt.setString(3, garantia.getDescricao());
        stmt.setDate(4, Date.valueOf(garantia.getSaida_concerto()));
        stmt.setDate(5, Date.valueOf(garantia.getDt_garantia()));

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
    public void RemoverGarantia(Garantia gar) throws SQLException {
        String SQL = "Delete from cadastros.garantia where id=?";
        
        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        stmt.setInt(1, gar.getId());

        stmt.execute();
        stmt.close();
    }

    public void AlterarGarantia(Garantia gar) throws SQLException {
        String SQL = "update cadastros.garantia set nome=?, descricao=?, saida_concerto=?, garantia=?  where id=?";

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        System.out.println(gar.getNome() + " , "+ gar.getDescricao()+ " , " + gar.getSaida_concerto()+ " , " + gar.getDt_garantia()+ " , "+ gar.getId());
        stmt.setString(1, gar.getNome());
        stmt.setString(2, gar.getDescricao());
        stmt.setDate(3, Date.valueOf(gar.getSaida_concerto()));
        stmt.setDate(4, Date.valueOf(gar.getDt_garantia()));
        stmt.setInt(5, gar.getId());
        
        stmt.execute();
        stmt.close();
    }

    public List<Garantia> ListaGarantia() throws SQLException {

        List<Garantia> ListaGarantia;
        ListaGarantia = new ArrayList<>();

        String SQL = "select* from cadastros.garantia";
        try {

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ListaGarantia.add(new Garantia(rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getObject("saida_concerto", LocalDate.class),
                        rs.getObject("garantia", LocalDate.class)
                ));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ListaGarantia;
    }

    /*public List<Cliente> ListaBuscaCliente(Cliente cliente) throws SQLException {
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
    }*/
}
