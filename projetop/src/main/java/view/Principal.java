/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import controller.JTextFieldLimit;
import controller.SetupAutoComplete;
import dao.Conexao;
import dao.clienteDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Cliente;

/**
 *
 * @author Michel
 */
public class Principal extends javax.swing.JFrame {

    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    List<Cliente> ListaCliente;
    List<Cliente> ListaBuscaCliente;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public Principal() {
        initComponents();

        PanelCadastrarCliente.setVisible(true);
        PanelCadastrarServico.setVisible(false);
        PanelConsultarServico.setVisible(false);
        setLblColor(BotaoClientes);
        ResetColor(BotaoServiços);
        ComboBoxConserto();
        atualizarTabelaClientes();
    }

    public String FuncGarantia(LocalDate dataGarantia, String descricao){
        int duracao_garantia = 0;
        try {
            String SQL = "Select duracao_garantia from cadastros.manutencao where nome = ?";
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            stmt.setString(1, descricao);

            rs = stmt.executeQuery();
            while (rs.next()) {
                duracao_garantia = rs.getInt("duracao_garantia");
            }

            dataGarantia = dataGarantia.plusMonths(duracao_garantia);
            return dataGarantia.format(formatter);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Algo de errado ocorreu! Erro: " + e.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
        return dataGarantia.format(formatter);
    }
    private void ComboBoxConserto() { //ok
        try {
            String SQL = "Select * from cadastros.manutencao order by id asc";
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            rs = stmt.executeQuery();
            while (rs.next()) {
                String Nome = rs.getString("nome");
                BoxConsertoCadastrarServico.addItem(Nome);

            }

        } catch (Exception e) {
            System.out.println("problema na combobox");
        }
    }

    public void atualizarTabelaClientes() {
        Cliente cli = new Cliente();
        clienteDAO clidao = new clienteDAO();
        try {
            ListaCliente = clidao.ListaCliente();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[ListaCliente.size()][6];
        int i = 0;
        for (Cliente clien : ListaCliente) {
            dados[i][0] = String.valueOf(clien.getId());
            dados[i][1] = clien.getNome();
            dados[i][2] = clien.getDescricao();
            dados[i][3] = String.valueOf(clien.getCpf());
            dados[i][4] = String.valueOf(clien.getSaida_concerto().format(formatter));
            dados[i][5] = String.valueOf(clien.getGarantia().format(formatter));
            i++;
        }
        String tituloColuna[] = {"id", "Nome", "Manutencao", "Cpf", "Saida concerto", "Garantia até"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        jTable1.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(180);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(centralizado);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(centralizado);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jTable1.setRowHeight(25);
        jTable1.updateUI();

    }

    //Tava do PaneRevista, referente a aba PaneGuiaAlteraRevista
    /*public void BuscaRevistaComFiltro() {

        Revistas revistas = new Revistas();
        String dados[][] = new String[ListaBuscaRevista.size()][7];
        int i = 0;
        for (Revistas rev : ListaBuscaRevista) {
            dados[i][0] = String.valueOf(rev.getID());
            dados[i][1] = rev.getTitulo();
            dados[i][2] = rev.getArea();
            dados[i][3] = rev.getEspecificacao();
            dados[i][4] = String.valueOf(rev.getData());
            dados[i][5] = String.valueOf(rev.getQuantidade());
            dados[i][6] = rev.getOrigem();
            i++;
        }
        String tituloColuna[] = {"id", "Titulo", "Área", "Especificação", "Data", "Quantidade", "Origem"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaAlterarOuRemoverRevista.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false,};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(0).setPreferredWidth(20);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(1).setPreferredWidth(180);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(2).setPreferredWidth(100);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(3).setPreferredWidth(100);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(4).setPreferredWidth(30);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(5).setPreferredWidth(50);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(6).setPreferredWidth(60);
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaConsultaCliente.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaConsultaCliente.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TabelaConsultaCliente.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        TabelaConsultaCliente.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        TabelaConsultaCliente.getColumnModel().getColumn(4).setCellRenderer(centralizado);
        TabelaConsultaCliente.getColumnModel().getColumn(5).setCellRenderer(centralizado);
        TabelaConsultaCliente.getColumnModel().getColumn(6).setCellRenderer(centralizado);
        TabelaConsultaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TabelaConsultaCliente.setRowHeight(25);
        TabelaConsultaCliente.updateUI();
    }*/

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SideBoard = new javax.swing.JPanel();
        BotaoClientes = new javax.swing.JLabel();
        BotaoServiços = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        PaneMae = new javax.swing.JPanel();
        PanelCadastrarCliente = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        PanelGuias = new javax.swing.JPanel();
        BotaoCadastrarCadastrarCliente = new javax.swing.JLabel();
        BotaoGerenciarCadastrarCliente = new javax.swing.JLabel();
        lblNome2 = new javax.swing.JLabel();
        TxtNomeCadastrarCliente = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        lblCPF2 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        lblTelefone = new javax.swing.JLabel();
        TxtTelefoneCadastrarCliente = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        BotaoSalvarCadastrarCliente = new javax.swing.JButton();
        TxtCPFCadastrarCliente = new javax.swing.JTextField();
        PanelCadastrarServico = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        BotaoCadastrarCadastrarServico = new javax.swing.JLabel();
        BotaoConsultarCadastrarServico = new javax.swing.JLabel();
        BotaoGerenciarCadastrarServico = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        TxtNomeCadastrarServico = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        lblConserto = new javax.swing.JLabel();
        BoxConsertoCadastrarServico = new java.awt.Choice();
        lblCPF = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        lblData = new javax.swing.JLabel();
        TxtDataCadastrarServico = new javax.swing.JFormattedTextField();
        jSeparator4 = new javax.swing.JSeparator();
        BotaoBuscarCadastrarServico = new javax.swing.JButton();
        BotaoCadastrarCadastrarServicos = new javax.swing.JButton();
        TxtCPFCadastrarServico = new javax.swing.JTextField();
        PanelConsultarServico = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        BotaoCadastrarConsultarServico = new javax.swing.JLabel();
        BotaoConsultarConsultarServico = new javax.swing.JLabel();
        BotaoGerenciarConsultarServico = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        BotaoBuscarConsultarServico = new javax.swing.JButton();
        lblNome1 = new javax.swing.JLabel();
        TxtNomeConsultarServico = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        lblCPF1 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        TxtCPFConsultarServico = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UEMG Frutal - Revistas");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImages(null);

        SideBoard.setBackground(new java.awt.Color(230, 230, 230));
        SideBoard.setForeground(new java.awt.Color(230, 230, 230));
        SideBoard.setToolTipText("");
        SideBoard.setMaximumSize(new java.awt.Dimension(300, 400));

        BotaoClientes.setBackground(new java.awt.Color(230, 230, 230));
        BotaoClientes.setFont(new java.awt.Font("Century Gothic", 0, 26)); // NOI18N
        BotaoClientes.setForeground(new java.awt.Color(25, 25, 112));
        BotaoClientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoClientes.setText("Clientes");
        BotaoClientes.setOpaque(true);
        BotaoClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoClientesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotaoClientesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotaoClientesMouseExited(evt);
            }
        });

        BotaoServiços.setBackground(new java.awt.Color(230, 230, 230));
        BotaoServiços.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        BotaoServiços.setForeground(new java.awt.Color(25, 25, 112));
        BotaoServiços.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoServiços.setText("Serviços");
        BotaoServiços.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoServiços.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoServiços.setOpaque(true);
        BotaoServiços.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoServiços.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoServiçosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotaoServiçosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotaoServiçosMouseExited(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(25, 25, 112));
        jSeparator1.setForeground(new java.awt.Color(230, 230, 230));

        javax.swing.GroupLayout SideBoardLayout = new javax.swing.GroupLayout(SideBoard);
        SideBoard.setLayout(SideBoardLayout);
        SideBoardLayout.setHorizontalGroup(
            SideBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BotaoClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BotaoServiços, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
            .addGroup(SideBoardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        SideBoardLayout.setVerticalGroup(
            SideBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBoardLayout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(BotaoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BotaoServiços, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PaneMae.setBackground(new java.awt.Color(255, 255, 255));
        PaneMae.setPreferredSize(new java.awt.Dimension(1145, 677));
        PaneMae.setLayout(new java.awt.CardLayout());

        jPanel8.setPreferredSize(new java.awt.Dimension(1144, 199));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(25, 25, 112));
        jLabel7.setText("CADASTRAR CLIENTE");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(351, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(324, 324, 324))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        PanelGuias.setBackground(new java.awt.Color(230, 230, 230));
        PanelGuias.setPreferredSize(new java.awt.Dimension(1145, 50));

        BotaoCadastrarCadastrarCliente.setBackground(new java.awt.Color(220, 220, 220));
        BotaoCadastrarCadastrarCliente.setFont(new java.awt.Font("Century Gothic", 0, 26)); // NOI18N
        BotaoCadastrarCadastrarCliente.setForeground(new java.awt.Color(23, 23, 112));
        BotaoCadastrarCadastrarCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoCadastrarCadastrarCliente.setText("Cadastrar");
        BotaoCadastrarCadastrarCliente.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoCadastrarCadastrarCliente.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoCadastrarCadastrarCliente.setOpaque(true);
        BotaoCadastrarCadastrarCliente.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoCadastrarCadastrarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoCadastrarCadastrarClienteMouseClicked(evt);
            }
        });

        BotaoGerenciarCadastrarCliente.setBackground(new java.awt.Color(230, 230, 230));
        BotaoGerenciarCadastrarCliente.setFont(new java.awt.Font("Century Gothic", 0, 26)); // NOI18N
        BotaoGerenciarCadastrarCliente.setForeground(new java.awt.Color(23, 23, 112));
        BotaoGerenciarCadastrarCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoGerenciarCadastrarCliente.setText("Gerenciar");
        BotaoGerenciarCadastrarCliente.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoGerenciarCadastrarCliente.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoGerenciarCadastrarCliente.setOpaque(true);
        BotaoGerenciarCadastrarCliente.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoGerenciarCadastrarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoGerenciarCadastrarClienteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotaoGerenciarCadastrarClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotaoGerenciarCadastrarClienteMouseExited(evt);
            }
        });

        javax.swing.GroupLayout PanelGuiasLayout = new javax.swing.GroupLayout(PanelGuias);
        PanelGuias.setLayout(PanelGuiasLayout);
        PanelGuiasLayout.setHorizontalGroup(
            PanelGuiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGuiasLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(BotaoCadastrarCadastrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BotaoGerenciarCadastrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelGuiasLayout.setVerticalGroup(
            PanelGuiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelGuiasLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(PanelGuiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoCadastrarCadastrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoGerenciarCadastrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblNome2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome2.setText("Nome");

        TxtNomeCadastrarCliente.setBackground(new java.awt.Color(240, 240, 240));
        TxtNomeCadastrarCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TxtNomeCadastrarCliente.setBorder(null);
        TxtNomeCadastrarCliente.setMaximumSize(new java.awt.Dimension(25, 25));
        TxtNomeCadastrarCliente.setMinimumSize(new java.awt.Dimension(25, 25));
        TxtNomeCadastrarServico.setDocument(new JTextFieldLimit(40, true));
        TxtNomeCadastrarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtNomeCadastrarClienteMouseClicked(evt);
            }
        });
        TxtNomeCadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNomeCadastrarClienteActionPerformed(evt);
            }
        });
        TxtNomeCadastrarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNomeCadastrarClienteKeyPressed(evt);
            }
        });

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));

        lblCPF2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblCPF2.setText("CPF");

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));

        lblTelefone.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblTelefone.setText("Telefone");

        TxtTelefoneCadastrarCliente.setBackground(new java.awt.Color(240, 240, 240));
        TxtTelefoneCadastrarCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TxtTelefoneCadastrarCliente.setBorder(null);
        TxtTelefoneCadastrarCliente.setPreferredSize(new java.awt.Dimension(35, 26));
        TxtCPFConsultarServico.setDocument(new JTextFieldLimit(10, false, true));
        TxtTelefoneCadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtTelefoneCadastrarClienteActionPerformed(evt);
            }
        });

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));

        BotaoSalvarCadastrarCliente.setBackground(new java.awt.Color(230, 230, 230));
        BotaoSalvarCadastrarCliente.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoSalvarCadastrarCliente.setText("Salvar");
        BotaoSalvarCadastrarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoSalvarCadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSalvarCadastrarClienteActionPerformed(evt);
            }
        });

        TxtCPFCadastrarCliente.setBackground(new java.awt.Color(240, 240, 240));
        TxtCPFCadastrarCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TxtCPFCadastrarCliente.setBorder(null);

        javax.swing.GroupLayout PanelCadastrarClienteLayout = new javax.swing.GroupLayout(PanelCadastrarCliente);
        PanelCadastrarCliente.setLayout(PanelCadastrarClienteLayout);
        PanelCadastrarClienteLayout.setHorizontalGroup(
            PanelCadastrarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCadastrarClienteLayout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(PanelCadastrarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCadastrarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblNome2)
                        .addComponent(TxtNomeCadastrarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtTelefoneCadastrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelefone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                .addGroup(PanelCadastrarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCPF2)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(BotaoSalvarCadastrarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(TxtCPFCadastrarCliente))
                .addGap(202, 202, 202))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 1145, Short.MAX_VALUE)
            .addComponent(PanelGuias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelCadastrarClienteLayout.setVerticalGroup(
            PanelCadastrarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCadastrarClienteLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PanelGuias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(PanelCadastrarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome2)
                    .addComponent(lblCPF2))
                .addGap(11, 11, 11)
                .addGroup(PanelCadastrarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtNomeCadastrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCPFCadastrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(PanelCadastrarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85)
                .addComponent(lblTelefone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelCadastrarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCadastrarClienteLayout.createSequentialGroup()
                        .addComponent(TxtTelefoneCadastrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BotaoSalvarCadastrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        PaneMae.add(PanelCadastrarCliente, "card5");

        PanelCadastrarServico.setForeground(new java.awt.Color(240, 240, 240));
        PanelCadastrarServico.setDoubleBuffered(false);
        PanelCadastrarServico.setPreferredSize(new java.awt.Dimension(1145, 677));
        PanelCadastrarServico.setRequestFocusEnabled(false);
        PanelCadastrarServico.setVerifyInputWhenFocusTarget(false);

        jPanel10.setBackground(new java.awt.Color(230, 230, 230));
        jPanel10.setPreferredSize(new java.awt.Dimension(1145, 50));

        BotaoCadastrarCadastrarServico.setBackground(new java.awt.Color(220, 220, 220));
        BotaoCadastrarCadastrarServico.setFont(new java.awt.Font("Century Gothic", 0, 26)); // NOI18N
        BotaoCadastrarCadastrarServico.setForeground(new java.awt.Color(23, 23, 112));
        BotaoCadastrarCadastrarServico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoCadastrarCadastrarServico.setText("Cadastrar");
        BotaoCadastrarCadastrarServico.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoCadastrarCadastrarServico.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoCadastrarCadastrarServico.setOpaque(true);
        BotaoCadastrarCadastrarServico.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoCadastrarCadastrarServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoCadastrarCadastrarServicoMouseClicked(evt);
            }
        });

        BotaoConsultarCadastrarServico.setBackground(new java.awt.Color(230, 230, 230));
        BotaoConsultarCadastrarServico.setFont(new java.awt.Font("Century Gothic", 0, 26)); // NOI18N
        BotaoConsultarCadastrarServico.setForeground(new java.awt.Color(23, 23, 112));
        BotaoConsultarCadastrarServico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoConsultarCadastrarServico.setText("Consultar");
        BotaoConsultarCadastrarServico.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoConsultarCadastrarServico.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoConsultarCadastrarServico.setOpaque(true);
        BotaoConsultarCadastrarServico.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoConsultarCadastrarServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoConsultarCadastrarServicoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotaoConsultarCadastrarServicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotaoConsultarCadastrarServicoMouseExited(evt);
            }
        });

        BotaoGerenciarCadastrarServico.setBackground(new java.awt.Color(230, 230, 230));
        BotaoGerenciarCadastrarServico.setFont(new java.awt.Font("Century Gothic", 0, 26)); // NOI18N
        BotaoGerenciarCadastrarServico.setForeground(new java.awt.Color(23, 23, 112));
        BotaoGerenciarCadastrarServico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoGerenciarCadastrarServico.setText("Gerenciar");
        BotaoGerenciarCadastrarServico.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoGerenciarCadastrarServico.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoGerenciarCadastrarServico.setOpaque(true);
        BotaoGerenciarCadastrarServico.setPreferredSize(new java.awt.Dimension(163, 50));
        BotaoGerenciarCadastrarServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoGerenciarCadastrarServicoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotaoGerenciarCadastrarServicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotaoGerenciarCadastrarServicoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(BotaoCadastrarCadastrarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BotaoConsultarCadastrarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BotaoGerenciarCadastrarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(656, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoCadastrarCadastrarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoGerenciarCadastrarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoConsultarCadastrarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setPreferredSize(new java.awt.Dimension(1145, 199));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(25, 25, 112));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("CADASTRAR SERVIÇO");
        jLabel4.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel4AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addComponent(jLabel4)
                .addContainerGap(325, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel4)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        lblNome.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome.setText("Nome");

        TxtNomeCadastrarServico.setBackground(new java.awt.Color(240, 240, 240));
        TxtNomeCadastrarServico.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TxtNomeCadastrarServico.setBorder(null);
        TxtNomeCadastrarServico.setMaximumSize(new java.awt.Dimension(25, 25));
        TxtNomeCadastrarServico.setMinimumSize(new java.awt.Dimension(25, 25));
        TxtNomeCadastrarServico.setDocument(new JTextFieldLimit(40, true));
        TxtNomeCadastrarServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtNomeCadastrarServicoMouseClicked(evt);
            }
        });
        TxtNomeCadastrarServico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNomeCadastrarServicoKeyPressed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        lblConserto.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblConserto.setText("Conserto");

        BoxConsertoCadastrarServico.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BoxConsertoCadastrarServico.setFocusable(false);
        BoxConsertoCadastrarServico.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        BoxConsertoCadastrarServico.setForeground(new java.awt.Color(1, 1, 1));

        lblCPF.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblCPF.setText("CPF");

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        lblData.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblData.setText("Data do conserto");

        TxtDataCadastrarServico.setBackground(new java.awt.Color(240, 240, 240));
        TxtDataCadastrarServico.setBorder(null);
        try {
            TxtDataCadastrarServico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtDataCadastrarServico.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        TxtDataCadastrarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtDataCadastrarServicoActionPerformed(evt);
            }
        });

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        BotaoBuscarCadastrarServico.setBackground(new java.awt.Color(230, 230, 230));
        BotaoBuscarCadastrarServico.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoBuscarCadastrarServico.setText("Buscar Cliente");
        BotaoBuscarCadastrarServico.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoBuscarCadastrarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoBuscarCadastrarServicoActionPerformed(evt);
            }
        });

        BotaoCadastrarCadastrarServicos.setBackground(new java.awt.Color(230, 230, 230));
        BotaoCadastrarCadastrarServicos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoCadastrarCadastrarServicos.setText("Cadastrar Serviço");
        BotaoCadastrarCadastrarServicos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoCadastrarCadastrarServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCadastrarCadastrarServicosActionPerformed(evt);
            }
        });

        TxtCPFCadastrarServico.setBackground(new java.awt.Color(240, 240, 240));
        TxtCPFCadastrarServico.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TxtCPFCadastrarServico.setBorder(null);

        javax.swing.GroupLayout PanelCadastrarServicoLayout = new javax.swing.GroupLayout(PanelCadastrarServico);
        PanelCadastrarServico.setLayout(PanelCadastrarServicoLayout);
        PanelCadastrarServicoLayout.setHorizontalGroup(
            PanelCadastrarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(PanelCadastrarServicoLayout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(PanelCadastrarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblConserto)
                    .addComponent(BoxConsertoCadastrarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelCadastrarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblNome)
                        .addComponent(TxtNomeCadastrarServico, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)))
                .addGap(102, 102, 102)
                .addGroup(PanelCadastrarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCPF)
                    .addComponent(lblData)
                    .addComponent(TxtDataCadastrarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelCadastrarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(TxtCPFCadastrarServico, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)))
                .addGap(48, 48, 48)
                .addGroup(PanelCadastrarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BotaoCadastrarCadastrarServicos)
                    .addComponent(BotaoBuscarCadastrarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        PanelCadastrarServicoLayout.setVerticalGroup(
            PanelCadastrarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCadastrarServicoLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(PanelCadastrarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelCadastrarServicoLayout.createSequentialGroup()
                        .addComponent(lblNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelCadastrarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelCadastrarServicoLayout.createSequentialGroup()
                                .addComponent(TxtNomeCadastrarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(BotaoBuscarCadastrarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelCadastrarServicoLayout.createSequentialGroup()
                        .addComponent(lblCPF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtCPFCadastrarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addGap(77, 77, 77)
                .addGroup(PanelCadastrarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblData)
                    .addComponent(lblConserto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelCadastrarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BotaoCadastrarCadastrarServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelCadastrarServicoLayout.createSequentialGroup()
                        .addGroup(PanelCadastrarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxtDataCadastrarServico)
                            .addComponent(BoxConsertoCadastrarServico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13))
        );

        PaneMae.add(PanelCadastrarServico, "card5");

        PanelConsultarServico.setPreferredSize(new java.awt.Dimension(1145, 677));

        jPanel9.setBackground(new java.awt.Color(230, 230, 230));

        BotaoCadastrarConsultarServico.setBackground(new java.awt.Color(230, 230, 230));
        BotaoCadastrarConsultarServico.setFont(new java.awt.Font("Century Gothic", 0, 26)); // NOI18N
        BotaoCadastrarConsultarServico.setForeground(new java.awt.Color(23, 23, 112));
        BotaoCadastrarConsultarServico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoCadastrarConsultarServico.setText("Cadastrar");
        BotaoCadastrarConsultarServico.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoCadastrarConsultarServico.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoCadastrarConsultarServico.setOpaque(true);
        BotaoCadastrarConsultarServico.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoCadastrarConsultarServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoCadastrarConsultarServicoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotaoCadastrarConsultarServicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotaoCadastrarConsultarServicoMouseExited(evt);
            }
        });

        BotaoConsultarConsultarServico.setBackground(new java.awt.Color(220, 220, 220));
        BotaoConsultarConsultarServico.setFont(new java.awt.Font("Century Gothic", 0, 26)); // NOI18N
        BotaoConsultarConsultarServico.setForeground(new java.awt.Color(23, 23, 112));
        BotaoConsultarConsultarServico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoConsultarConsultarServico.setText("Consultar");
        BotaoConsultarConsultarServico.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoConsultarConsultarServico.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoConsultarConsultarServico.setOpaque(true);
        BotaoConsultarConsultarServico.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoConsultarConsultarServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoConsultarConsultarServicoMouseClicked(evt);
            }
        });

        BotaoGerenciarConsultarServico.setBackground(new java.awt.Color(230, 230, 230));
        BotaoGerenciarConsultarServico.setFont(new java.awt.Font("Century Gothic", 0, 26)); // NOI18N
        BotaoGerenciarConsultarServico.setForeground(new java.awt.Color(23, 23, 112));
        BotaoGerenciarConsultarServico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoGerenciarConsultarServico.setText("Gerenciar");
        BotaoGerenciarConsultarServico.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoGerenciarConsultarServico.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoGerenciarConsultarServico.setOpaque(true);
        BotaoGerenciarConsultarServico.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoGerenciarConsultarServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoGerenciarConsultarServicoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotaoGerenciarConsultarServicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotaoGerenciarConsultarServicoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(BotaoCadastrarConsultarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BotaoConsultarConsultarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BotaoGerenciarConsultarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoCadastrarConsultarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoGerenciarConsultarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoConsultarConsultarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setPreferredSize(new java.awt.Dimension(1144, 199));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(25, 25, 112));
        jLabel6.setText("CONSULTAR SERVIÇO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(332, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(324, 324, 324))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel6)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        BotaoBuscarConsultarServico.setBackground(new java.awt.Color(230, 230, 230));
        BotaoBuscarConsultarServico.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoBuscarConsultarServico.setText("Buscar Cliente");
        BotaoBuscarConsultarServico.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoBuscarConsultarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoBuscarConsultarServicoActionPerformed(evt);
            }
        });

        lblNome1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome1.setText("Nome");

        TxtNomeConsultarServico.setBackground(new java.awt.Color(240, 240, 240));
        TxtNomeConsultarServico.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TxtNomeConsultarServico.setBorder(null);
        TxtNomeConsultarServico.setMaximumSize(new java.awt.Dimension(25, 25));
        TxtNomeConsultarServico.setMinimumSize(new java.awt.Dimension(25, 25));
        TxtNomeCadastrarServico.setDocument(new JTextFieldLimit(40, true));
        TxtNomeConsultarServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtNomeConsultarServicoMouseClicked(evt);
            }
        });
        TxtNomeConsultarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNomeConsultarServicoActionPerformed(evt);
            }
        });
        TxtNomeConsultarServico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNomeConsultarServicoKeyPressed(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        lblCPF1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblCPF1.setText("CPF");

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBackground(new java.awt.Color(240, 240, 240));
        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Nome", "CPF", "Conserto", "Data do conserto", "Tempo de garantia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(190, 190, 190));
        jTable1.setSelectionBackground(new java.awt.Color(230, 230, 230));
        jTable1.setSelectionForeground(new java.awt.Color(230, 230, 230));
        jTable1.setShowHorizontalLines(false);
        jScrollPane1.setViewportView(jTable1);

        TxtCPFConsultarServico.setBackground(new java.awt.Color(240, 240, 240));
        TxtCPFConsultarServico.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TxtCPFConsultarServico.setBorder(null);

        javax.swing.GroupLayout PanelConsultarServicoLayout = new javax.swing.GroupLayout(PanelConsultarServico);
        PanelConsultarServico.setLayout(PanelConsultarServicoLayout);
        PanelConsultarServicoLayout.setHorizontalGroup(
            PanelConsultarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelConsultarServicoLayout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(PanelConsultarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 923, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelConsultarServicoLayout.createSequentialGroup()
                        .addGroup(PanelConsultarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNome1)
                            .addComponent(TxtNomeConsultarServico, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                            .addComponent(jSeparator3))
                        .addGap(118, 118, 118)
                        .addGroup(PanelConsultarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCPF1)
                            .addGroup(PanelConsultarServicoLayout.createSequentialGroup()
                                .addGroup(PanelConsultarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                    .addComponent(TxtCPFConsultarServico))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BotaoBuscarConsultarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelConsultarServicoLayout.createSequentialGroup()
                .addGroup(PanelConsultarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1145, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PanelConsultarServicoLayout.setVerticalGroup(
            PanelConsultarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelConsultarServicoLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(PanelConsultarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome1)
                    .addComponent(lblCPF1))
                .addGap(11, 11, 11)
                .addGroup(PanelConsultarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelConsultarServicoLayout.createSequentialGroup()
                        .addGroup(PanelConsultarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtCPFConsultarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtNomeConsultarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(PanelConsultarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelConsultarServicoLayout.createSequentialGroup()
                        .addComponent(BotaoBuscarConsultarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        PaneMae.add(PanelConsultarServico, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(SideBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PaneMae, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SideBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PaneMae, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //----------------------Inicio das ações de revista ------------------------------------
    

    private void TxtNomeCadastrarServicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtNomeCadastrarServicoMouseClicked
        System.out.println("Teste");
    }//GEN-LAST:event_TxtNomeCadastrarServicoMouseClicked

    private void TxtNomeCadastrarServicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNomeCadastrarServicoKeyPressed

    }//GEN-LAST:event_TxtNomeCadastrarServicoKeyPressed

    private void jLabel4AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel4AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4AncestorAdded

    private void TxtNomeConsultarServicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtNomeConsultarServicoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNomeConsultarServicoMouseClicked

    private void TxtNomeConsultarServicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNomeConsultarServicoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNomeConsultarServicoKeyPressed

    private void TxtNomeConsultarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNomeConsultarServicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNomeConsultarServicoActionPerformed

    private void BotaoCadastrarCadastrarServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCadastrarCadastrarServicosActionPerformed
        /*if (campoTitulo.getText().isEmpty() || campoData.getText().isEmpty() || campoQuantidade.getText().isEmpty() || ComboBoxEspecificacaoNovaRevista.getSelectedItem() == null || ComboBoxAreaNovaRevista.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Há campos não preenchidos", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {*/
        Cliente cli = new Cliente();
                
        try {
            cli.getId();
            cli.setNome(TxtNomeCadastrarServico.getText());
            cli.setCpf(Integer.parseInt(TxtCPFCadastrarServico.getText()));
            cli.setDescricao((String) BoxConsertoCadastrarServico.getSelectedItem());
            cli.setSaida_concerto(LocalDate.parse(TxtDataCadastrarServico.getText(), formatter));
            cli.setGarantia(LocalDate.parse(FuncGarantia(cli.getSaida_concerto(), cli.getDescricao()), formatter));//atenção redobrada nessa linha

            clienteDAO clidao = new clienteDAO();
            clidao.InserirCliente(cli);

            try {


                JOptionPane.showMessageDialog(null, "Concerto cadastrado com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Algo de errado ocorreu! Erro: " + ex.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(ex.getMessage());
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Essa revista já existe! Erro:" + ex.getMessage(), "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(ex.getMessage());
        }
        //}
    }//GEN-LAST:event_BotaoCadastrarCadastrarServicosActionPerformed
 
    private void BotaoBuscarConsultarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoBuscarConsultarServicoActionPerformed
        atualizarTabelaClientes();
    }//GEN-LAST:event_BotaoBuscarConsultarServicoActionPerformed

    private void TxtDataCadastrarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDataCadastrarServicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDataCadastrarServicoActionPerformed

    private void BotaoBuscarCadastrarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoBuscarCadastrarServicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoBuscarCadastrarServicoActionPerformed

    private void TxtNomeCadastrarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtNomeCadastrarClienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNomeCadastrarClienteMouseClicked

    private void TxtNomeCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNomeCadastrarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNomeCadastrarClienteActionPerformed

    private void TxtNomeCadastrarClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNomeCadastrarClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNomeCadastrarClienteKeyPressed

    private void BotaoSalvarCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSalvarCadastrarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoSalvarCadastrarClienteActionPerformed

    private void TxtTelefoneCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtTelefoneCadastrarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTelefoneCadastrarClienteActionPerformed

    private void BotaoGerenciarCadastrarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoGerenciarCadastrarClienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoGerenciarCadastrarClienteMouseClicked

    private void BotaoCadastrarCadastrarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoCadastrarCadastrarClienteMouseClicked
        PanelCadastrarCliente.setVisible(true);
        PanelCadastrarServico.setVisible(false);
        PanelConsultarServico.setVisible(false);
    }//GEN-LAST:event_BotaoCadastrarCadastrarClienteMouseClicked

    private void BotaoGerenciarConsultarServicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoGerenciarConsultarServicoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoGerenciarConsultarServicoMouseClicked

    private void BotaoCadastrarConsultarServicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoCadastrarConsultarServicoMouseClicked
        PanelCadastrarCliente.setVisible(false);
        PanelCadastrarServico.setVisible(true);
        PanelConsultarServico.setVisible(false);
    }//GEN-LAST:event_BotaoCadastrarConsultarServicoMouseClicked

    private void BotaoConsultarConsultarServicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoConsultarConsultarServicoMouseClicked
        PanelCadastrarCliente.setVisible(false);
        PanelCadastrarServico.setVisible(false);
        PanelConsultarServico.setVisible(true);
    }//GEN-LAST:event_BotaoConsultarConsultarServicoMouseClicked

    private void BotaoGerenciarCadastrarServicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoGerenciarCadastrarServicoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoGerenciarCadastrarServicoMouseClicked

    private void BotaoCadastrarCadastrarServicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoCadastrarCadastrarServicoMouseClicked
        PanelCadastrarCliente.setVisible(false);
        PanelCadastrarServico.setVisible(true);
        PanelConsultarServico.setVisible(false);
    }//GEN-LAST:event_BotaoCadastrarCadastrarServicoMouseClicked

    private void BotaoConsultarCadastrarServicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoConsultarCadastrarServicoMouseClicked
        PanelCadastrarCliente.setVisible(false);
        PanelCadastrarServico.setVisible(false);
        PanelConsultarServico.setVisible(true);
    }//GEN-LAST:event_BotaoConsultarCadastrarServicoMouseClicked

    private void BotaoGerenciarCadastrarClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoGerenciarCadastrarClienteMouseEntered
       BotaoGerenciarCadastrarCliente.setBackground(new Color (220, 220, 220));
    }//GEN-LAST:event_BotaoGerenciarCadastrarClienteMouseEntered

    private void BotaoGerenciarCadastrarClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoGerenciarCadastrarClienteMouseExited
        ResetColor(BotaoGerenciarCadastrarCliente);
    }//GEN-LAST:event_BotaoGerenciarCadastrarClienteMouseExited

    private void BotaoConsultarCadastrarServicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoConsultarCadastrarServicoMouseEntered
        BotaoConsultarCadastrarServico.setBackground(new Color (220, 220, 220));
    }//GEN-LAST:event_BotaoConsultarCadastrarServicoMouseEntered

    private void BotaoConsultarCadastrarServicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoConsultarCadastrarServicoMouseExited
        ResetColor(BotaoConsultarCadastrarServico);
    }//GEN-LAST:event_BotaoConsultarCadastrarServicoMouseExited

    private void BotaoGerenciarCadastrarServicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoGerenciarCadastrarServicoMouseEntered
        BotaoGerenciarCadastrarServico.setBackground(new Color (220, 220, 220));
    }//GEN-LAST:event_BotaoGerenciarCadastrarServicoMouseEntered

    private void BotaoGerenciarCadastrarServicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoGerenciarCadastrarServicoMouseExited
        ResetColor(BotaoGerenciarCadastrarServico);
    }//GEN-LAST:event_BotaoGerenciarCadastrarServicoMouseExited

    private void BotaoCadastrarConsultarServicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoCadastrarConsultarServicoMouseEntered
        BotaoCadastrarConsultarServico.setBackground(new Color (220, 220, 220));
    }//GEN-LAST:event_BotaoCadastrarConsultarServicoMouseEntered

    private void BotaoCadastrarConsultarServicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoCadastrarConsultarServicoMouseExited
        ResetColor(BotaoCadastrarConsultarServico);
    }//GEN-LAST:event_BotaoCadastrarConsultarServicoMouseExited

    private void BotaoGerenciarConsultarServicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoGerenciarConsultarServicoMouseEntered
        BotaoGerenciarConsultarServico.setBackground(new Color (220, 220, 220));
    }//GEN-LAST:event_BotaoGerenciarConsultarServicoMouseEntered

    private void BotaoGerenciarConsultarServicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoGerenciarConsultarServicoMouseExited
        ResetColor(BotaoGerenciarConsultarServico);
    }//GEN-LAST:event_BotaoGerenciarConsultarServicoMouseExited

    private void BotaoServiçosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoServiçosMouseEntered
        //BotaoServiços.setBackground(new Color (220, 220, 220));
    }//GEN-LAST:event_BotaoServiçosMouseEntered

    private void BotaoServiçosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoServiçosMouseClicked
        PanelCadastrarCliente.setVisible(false);
        PanelCadastrarServico.setVisible(true);
        PanelConsultarServico.setVisible(false);
        setLblColor(BotaoServiços);
        ResetColor(BotaoClientes);
    }//GEN-LAST:event_BotaoServiçosMouseClicked

    private void BotaoClientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoClientesMouseExited
        //ResetColor(BotaoClientes);
    }//GEN-LAST:event_BotaoClientesMouseExited

    private void BotaoClientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoClientesMouseEntered
        //BotaoClientes.setBackground(new Color (220, 220, 220));
    }//GEN-LAST:event_BotaoClientesMouseEntered

    private void BotaoClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoClientesMouseClicked
        PanelCadastrarCliente.setVisible(true);
        PanelCadastrarServico.setVisible(false);
        PanelConsultarServico.setVisible(false);
        setLblColor(BotaoClientes);
        ResetColor(BotaoServiços);  
    }//GEN-LAST:event_BotaoClientesMouseClicked

    private void BotaoServiçosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoServiçosMouseExited
       // ResetColor(BotaoServiços);
    }//GEN-LAST:event_BotaoServiçosMouseExited

    public void setLblColor(JLabel lbl) {
        lbl.setBackground(new Color(220, 220, 220));
    }

    public void ResetColor(JLabel lbl) {
        lbl.setBackground(new Color(230, 230, 230));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoBuscarCadastrarServico;
    private javax.swing.JButton BotaoBuscarConsultarServico;
    private javax.swing.JLabel BotaoCadastrarCadastrarCliente;
    private javax.swing.JLabel BotaoCadastrarCadastrarServico;
    private javax.swing.JButton BotaoCadastrarCadastrarServicos;
    private javax.swing.JLabel BotaoCadastrarConsultarServico;
    private javax.swing.JLabel BotaoClientes;
    private javax.swing.JLabel BotaoConsultarCadastrarServico;
    private javax.swing.JLabel BotaoConsultarConsultarServico;
    private javax.swing.JLabel BotaoGerenciarCadastrarCliente;
    private javax.swing.JLabel BotaoGerenciarCadastrarServico;
    private javax.swing.JLabel BotaoGerenciarConsultarServico;
    private javax.swing.JButton BotaoSalvarCadastrarCliente;
    private javax.swing.JLabel BotaoServiços;
    private java.awt.Choice BoxConsertoCadastrarServico;
    private javax.swing.JPanel PaneMae;
    private javax.swing.JPanel PanelCadastrarCliente;
    private javax.swing.JPanel PanelCadastrarServico;
    private javax.swing.JPanel PanelConsultarServico;
    private javax.swing.JPanel PanelGuias;
    private javax.swing.JPanel SideBoard;
    private javax.swing.JTextField TxtCPFCadastrarCliente;
    private javax.swing.JTextField TxtCPFCadastrarServico;
    private javax.swing.JTextField TxtCPFConsultarServico;
    private javax.swing.JFormattedTextField TxtDataCadastrarServico;
    private javax.swing.JTextField TxtNomeCadastrarCliente;
    private javax.swing.JTextField TxtNomeCadastrarServico;
    private javax.swing.JTextField TxtNomeConsultarServico;
    private javax.swing.JTextField TxtTelefoneCadastrarCliente;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblCPF1;
    private javax.swing.JLabel lblCPF2;
    private javax.swing.JLabel lblConserto;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNome1;
    private javax.swing.JLabel lblNome2;
    private javax.swing.JLabel lblTelefone;
    // End of variables declaration//GEN-END:variables

    private void makeTableTransparente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
