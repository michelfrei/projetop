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
import dao.GarantiaDAO;
import dao.ManutencaoDAO;
import dao.ClienteDAO;
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
import model.Garantia;
import model.Manutencao;

/**
 *
 * @author Michel
 */
public class Principal extends javax.swing.JFrame {

    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<Cliente> ListaCliente;
    List<Cliente> ListaBuscaCliente;

    List<Garantia> ListaGarantia;
    List<Garantia> ListaBuscaGarantia;

    List<Manutencao> ListaManutencao;
    List<Manutencao> ListaBuscaManutencao;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Principal() {
        initComponents();

        PaneClientes.setVisible(true);
        PaneGarantias.setVisible(false);
        setLblColor(BotaoClientes);
        ResetColor(BotaoGarantias);
        ResetColor(BotaoConsertos);
        BotaoCancelarGerenciarServicos.setVisible(false);
        BotaoSalvarGerenciarServicos.setVisible(false);
        BotaoExcluirGerenciarServicos.setVisible(false);
        BotaoAlterarGerenciarServicos.setVisible(false);
        BotaoBuscarGerenciarServicos.setVisible(true);
        BotaoCancelarGerenciarGarantias.setVisible(false);
        BotaoSalvarGerenciarGarantias.setVisible(false);
        BotaoExcluirGerenciarGarantias.setVisible(false);
        BotaoAlterarGerenciarGarantias.setVisible(false);
        BotaoBuscarGerenciarGarantias.setVisible(true);
        BotaoCancelarGerenciarClientes.setVisible(false);
        BotaoSalvarGerenciarClientes.setVisible(false);
        BotaoExcluirGerenciarClientes.setVisible(false);
        BotaoAlterarGerenciarClientes.setVisible(false);
        BotaoBuscarGerenciarClientes.setVisible(true);
        ComboBoxEscolhaConserto();
        atualizarTabelaConsultaCliente();
        atualizarTabelaConsultaGarantia();
        atualizarTabelaConsultaManutencao();
        AutoComplete();
    }

    public String FuncGarantia(LocalDate dataGarantia, String meses) {
        int duracao_garantia = 0;
        try {
            String SQL = "Select duracao_garantia from cadastros.manutencao where descricao = ?";
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            stmt.setString(1, meses);

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

    public void AutoComplete() {
        ClienteDAO clienteDAO = new ClienteDAO();
        //Preenche o combobox com os registros
        ArrayList<String> ListaCliente = new ArrayList<>();

        try {
            for (Cliente cli : clienteDAO.ListaCliente()) {
                ListaCliente.add(cli.getNome());
            }

        } catch (SQLException ex) {
            ex.getMessage();
        }

        SetupAutoComplete.setupAutoComplete(CampoNome, ListaCliente);
    }

    private void ComboBoxEscolhaConserto() { //ok
        try {
            String SQL = "Select * from cadastros.manutencao order by id asc";
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            rs = stmt.executeQuery();
            while (rs.next()) {
                String Nome = rs.getString("descricao");
                ComboEscolhaConserto.addItem(Nome);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizarTabelaConsultaCliente() {
        Cliente cli = new Cliente();
        ClienteDAO clidao = new ClienteDAO();
        try {
            ListaCliente = clidao.ListaCliente();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[ListaCliente.size()][7];
        int i = 0;
        for (Cliente clien : ListaCliente) {
            dados[i][0] = String.valueOf(clien.getId());
            dados[i][1] = clien.getNome();
            dados[i][2] = clien.getCpf();
            dados[i][3] = clien.getTelefone();
            dados[i][4] = clien.getCidade();
            dados[i][5] = clien.getEndereco();
            dados[i][6] = clien.getEmail();
            i++;
        }
        String tituloColuna[] = {"id", "Nome", "CPF", "Telefone", "Cidade", "Endereco", "Email"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TableConsultaCliente.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TableConsultaCliente.getColumnModel().getColumn(0).setPreferredWidth(10);
        TableConsultaCliente.getColumnModel().getColumn(1).setPreferredWidth(100);
        TableConsultaCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
        TableConsultaCliente.getColumnModel().getColumn(3).setPreferredWidth(100);
        TableConsultaCliente.getColumnModel().getColumn(4).setPreferredWidth(100);
        TableConsultaCliente.getColumnModel().getColumn(5).setPreferredWidth(100);
        TableConsultaCliente.getColumnModel().getColumn(6).setPreferredWidth(100);
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TableConsultaCliente.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TableConsultaCliente.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TableConsultaCliente.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        TableConsultaCliente.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        TableConsultaCliente.getColumnModel().getColumn(4).setCellRenderer(centralizado);
        TableConsultaCliente.getColumnModel().getColumn(5).setCellRenderer(centralizado);
        TableConsultaCliente.getColumnModel().getColumn(6).setCellRenderer(centralizado);
        TableConsultaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TableConsultaCliente.setRowHeight(25);
        TableConsultaCliente.updateUI();

    }

    public void atualizarTabelaBuscaCliente() {
        Cliente cli = new Cliente();
        String dados[][] = new String[ListaBuscaCliente.size()][7];
        int i = 0;
        for (Cliente clien : ListaBuscaCliente) {
            dados[i][0] = String.valueOf(clien.getId());
            dados[i][1] = clien.getNome();
            dados[i][2] = String.valueOf(clien.getCpf());
            dados[i][3] = clien.getTelefone();
            dados[i][4] = clien.getCidade();
            dados[i][5] = clien.getEndereco();
            dados[i][6] = clien.getEmail();
            i++;
        }
        String tituloColuna[] = {"id", "Nome", "CPF", "Telefone", "Cidade", "Endereco", "Email"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TableConsultaCliente.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TableConsultaCliente.getColumnModel().getColumn(0).setPreferredWidth(20);
        TableConsultaCliente.getColumnModel().getColumn(1).setPreferredWidth(100);
        TableConsultaCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
        TableConsultaCliente.getColumnModel().getColumn(3).setPreferredWidth(100);
        TableConsultaCliente.getColumnModel().getColumn(4).setPreferredWidth(100);
        TableConsultaCliente.getColumnModel().getColumn(5).setPreferredWidth(100);
        TableConsultaCliente.getColumnModel().getColumn(6).setPreferredWidth(100);
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TableConsultaCliente.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TableConsultaCliente.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TableConsultaCliente.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        TableConsultaCliente.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        TableConsultaCliente.getColumnModel().getColumn(4).setCellRenderer(centralizado);
        TableConsultaCliente.getColumnModel().getColumn(5).setCellRenderer(centralizado);
        TableConsultaCliente.getColumnModel().getColumn(6).setCellRenderer(centralizado);
        TableConsultaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TableConsultaCliente.setRowHeight(25);
        TableConsultaCliente.updateUI();
    }

    public void atualizarTabelaConsultaGarantia() {
        Garantia gar = new Garantia();
        GarantiaDAO gardao = new GarantiaDAO();
        try {
            ListaGarantia = gardao.ListaGarantia();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[ListaGarantia.size()][5];
        int i = 0;
        for (Garantia gara : ListaGarantia) {
            dados[i][0] = String.valueOf(gara.getId());
            dados[i][1] = gara.getNome();
            dados[i][2] = gara.getDescricao();
            dados[i][3] = String.valueOf(gara.getSaida_concerto().format(formatter));
            dados[i][4] = String.valueOf(gara.getDt_garantia().format(formatter));
            i++;
        }
        String tituloColuna[] = {"id", "Nome", "Descricao", "Saida do dispositivo", "Garantia"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TableConsultaGarantia.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TableConsultaGarantia.getColumnModel().getColumn(0).setPreferredWidth(20);
        TableConsultaGarantia.getColumnModel().getColumn(1).setPreferredWidth(180);
        TableConsultaGarantia.getColumnModel().getColumn(2).setPreferredWidth(100);
        TableConsultaGarantia.getColumnModel().getColumn(3).setPreferredWidth(100);
        TableConsultaGarantia.getColumnModel().getColumn(4).setPreferredWidth(100);
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TableConsultaGarantia.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TableConsultaGarantia.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TableConsultaGarantia.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        TableConsultaGarantia.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        TableConsultaGarantia.getColumnModel().getColumn(4).setCellRenderer(centralizado);
        TableConsultaGarantia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TableConsultaGarantia.setRowHeight(25);
        TableConsultaGarantia.updateUI();
    }

    public void atualizarTabelaConsultaManutencao() {
        Manutencao man = new Manutencao();
        ManutencaoDAO mandao = new ManutencaoDAO();
        try {
            ListaManutencao = mandao.ListaManutencao();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[ListaManutencao.size()][2];
        int i = 0;
        for (Manutencao manu : ListaManutencao) {
            dados[i][0] = String.valueOf(manu.getId());
            dados[i][1] = manu.getDescricao();
            i++;
        }
        String tituloColuna[] = {"id", "descricao",};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaConsultaManutencao.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TabelaConsultaManutencao.getColumnModel().getColumn(0).setPreferredWidth(10);
        TabelaConsultaManutencao.getColumnModel().getColumn(1).setPreferredWidth(190);
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaConsultaManutencao.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaConsultaManutencao.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TabelaConsultaManutencao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TabelaConsultaManutencao.setRowHeight(25);
        TabelaConsultaManutencao.updateUI();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SideBoard = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        BotaoGarantias = new javax.swing.JLabel();
        BotaoClientes = new javax.swing.JLabel();
        BotaoConsertos = new javax.swing.JLabel();
        PaneMae = new javax.swing.JPanel();
        PaneClientes = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        CadastrarClientes = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        lblNome3 = new javax.swing.JLabel();
        FieldCadastroNomeCliente = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        BotaoSalvarCadastroCliente = new javax.swing.JButton();
        jSeparator12 = new javax.swing.JSeparator();
        lblNome4 = new javax.swing.JLabel();
        BotaoNovoCadastroCliente = new javax.swing.JButton();
        lblCPF4 = new javax.swing.JLabel();
        FieldCadastroCPFCliente = new javax.swing.JFormattedTextField();
        jSeparator14 = new javax.swing.JSeparator();
        FieldCadastroTelefoneCliente = new javax.swing.JFormattedTextField();
        BotaoCancelarCadastroCliente = new javax.swing.JButton();
        lblNome6 = new javax.swing.JLabel();
        FieldCadastroEnderecoCliente = new javax.swing.JTextField();
        jSeparator16 = new javax.swing.JSeparator();
        lblNome8 = new javax.swing.JLabel();
        FieldCadastroEmailCliente = new javax.swing.JTextField();
        jSeparator17 = new javax.swing.JSeparator();
        lblNome9 = new javax.swing.JLabel();
        FieldCadastroCidadeCliente = new javax.swing.JTextField();
        jSeparator21 = new javax.swing.JSeparator();
        ConsultarClientes = new javax.swing.JPanel();
        lblNome2 = new javax.swing.JLabel();
        FieldConsultaNomeCliente = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        lblCPF2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableConsultaCliente = new javax.swing.JTable();
        jSeparator11 = new javax.swing.JSeparator();
        BotaoBuscarConsultaCliente = new javax.swing.JButton();
        FieldConsultaCPFCliente = new javax.swing.JTextField();
        GerenciarClientes = new javax.swing.JPanel();
        BotaoSalvarGerenciarClientes = new javax.swing.JButton();
        BotaoCancelarGerenciarClientes = new javax.swing.JButton();
        jSeparator18 = new javax.swing.JSeparator();
        FieldNomeGerenciarClientes = new javax.swing.JTextField();
        lblNome10 = new javax.swing.JLabel();
        BotaoExcluirGerenciarClientes = new javax.swing.JButton();
        BotaoBuscarGerenciarClientes = new javax.swing.JButton();
        BotaoAlterarGerenciarClientes = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableGerenciarClientes = new javax.swing.JTable();
        FieldCPFGerenciarClientes = new javax.swing.JFormattedTextField();
        lblCPF3 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel17 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        PaneGarantias = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        CadastrarGarantias = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        CampoNome = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        lblData = new javax.swing.JLabel();
        CampoDataFormatada = new javax.swing.JFormattedTextField();
        jSeparator4 = new javax.swing.JSeparator();
        lblConserto = new javax.swing.JLabel();
        BotaoSalvarNovaRevista = new javax.swing.JButton();
        ComboEscolhaConserto = new javax.swing.JComboBox<>();
        ConsultarGarantias = new javax.swing.JPanel();
        lblNome1 = new javax.swing.JLabel();
        TxtNome1 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        lblCPF1 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableConsultaGarantia = new javax.swing.JTable();
        FieldCPFConsultarGarantias = new javax.swing.JFormattedTextField();
        BotaoBuscarConsultarGarantias = new javax.swing.JButton();
        GerenciarGarantias = new javax.swing.JPanel();
        jSeparator19 = new javax.swing.JSeparator();
        BotaoSalvarGerenciarGarantias = new javax.swing.JButton();
        BotaoCancelarGerenciarGarantias = new javax.swing.JButton();
        BotaoExcluirGerenciarGarantias = new javax.swing.JButton();
        FieldNomeGerenciarGarantias = new javax.swing.JTextField();
        lblNome11 = new javax.swing.JLabel();
        BotaoBuscarGerenciarGarantias = new javax.swing.JButton();
        BotaoAlterarGerenciarGarantias = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        TableGerenciarGarantias = new javax.swing.JTable();
        FieldCPFGerenciaGarantias = new javax.swing.JFormattedTextField();
        lblCPF5 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        PaneServicos = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        CadastrarServicos = new javax.swing.JPanel();
        lblNome5 = new javax.swing.JLabel();
        FieldNomeCadastrarConsertos = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        BotaoNovoCadastroConserto = new javax.swing.JButton();
        BotaoSalvarCadastroConserto = new javax.swing.JButton();
        BotaoCancelarCadastroConserto = new javax.swing.JButton();
        ConsultarServicos = new javax.swing.JPanel();
        FieldConsultarGarantias = new javax.swing.JTextField();
        lblNome7 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        TabelaConsultaManutencao = new javax.swing.JTable();
        BotaoBuscarGarantias = new javax.swing.JButton();
        GerenciarServicos = new javax.swing.JPanel();
        BotaoSalvarGerenciarServicos = new javax.swing.JButton();
        jSeparator20 = new javax.swing.JSeparator();
        BotaoCancelarGerenciarServicos = new javax.swing.JButton();
        FieldNomeGerenciarServicos = new javax.swing.JTextField();
        lblNome12 = new javax.swing.JLabel();
        BotaoExcluirGerenciarServicos = new javax.swing.JButton();
        BotaoBuscarGerenciarServicos = new javax.swing.JButton();
        BotaoAlterarGerenciarServicos = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        TableGerenciarServicos = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UEMG Frutal - Revistas");
        setBackground(new java.awt.Color(230, 230, 230));
        setIconImages(null);
        setPreferredSize(new java.awt.Dimension(1364, 750));

        SideBoard.setBackground(new java.awt.Color(230, 230, 230));
        SideBoard.setForeground(new java.awt.Color(230, 230, 230));
        SideBoard.setToolTipText("");
        SideBoard.setMaximumSize(new java.awt.Dimension(300, 400));

        jSeparator1.setBackground(new java.awt.Color(25, 25, 112));
        jSeparator1.setForeground(new java.awt.Color(230, 230, 230));

        BotaoGarantias.setBackground(new java.awt.Color(230, 230, 230));
        BotaoGarantias.setFont(new java.awt.Font("Century Gothic", 0, 26)); // NOI18N
        BotaoGarantias.setForeground(new java.awt.Color(25, 25, 112));
        BotaoGarantias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoGarantias.setText("Garantias");
        BotaoGarantias.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoGarantias.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoGarantias.setOpaque(true);
        BotaoGarantias.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoGarantias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoGarantiasMouseClicked(evt);
            }
        });

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
        });

        BotaoConsertos.setBackground(new java.awt.Color(230, 230, 230));
        BotaoConsertos.setFont(new java.awt.Font("Century Gothic", 0, 26)); // NOI18N
        BotaoConsertos.setForeground(new java.awt.Color(23, 23, 112));
        BotaoConsertos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoConsertos.setText("Consertos");
        BotaoConsertos.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoConsertos.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoConsertos.setOpaque(true);
        BotaoConsertos.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoConsertos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoConsertosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout SideBoardLayout = new javax.swing.GroupLayout(SideBoard);
        SideBoard.setLayout(SideBoardLayout);
        SideBoardLayout.setHorizontalGroup(
            SideBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BotaoClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BotaoGarantias, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
            .addComponent(BotaoConsertos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(16, 16, 16)
                .addComponent(BotaoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BotaoGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BotaoConsertos, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PaneMae.setPreferredSize(new java.awt.Dimension(1144, 750));
        PaneMae.setLayout(new java.awt.CardLayout());

        PaneClientes.setBackground(new java.awt.Color(230, 230, 230));
        PaneClientes.setPreferredSize(new java.awt.Dimension(1144, 429));

        jTabbedPane3.setOpaque(true);
        jTabbedPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane3MouseClicked(evt);
            }
        });

        CadastrarClientes.setForeground(new java.awt.Color(240, 240, 240));
        CadastrarClientes.setDoubleBuffered(false);
        CadastrarClientes.setPreferredSize(new java.awt.Dimension(1144, 677));
        CadastrarClientes.setRequestFocusEnabled(false);
        CadastrarClientes.setVerifyInputWhenFocusTarget(false);

        jPanel15.setPreferredSize(new java.awt.Dimension(356, 200));

        lblNome3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome3.setText("Nome do cliente");

        FieldCadastroNomeCliente.setBackground(new java.awt.Color(240, 240, 240));
        FieldCadastroNomeCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        FieldCadastroNomeCliente.setBorder(null);
        FieldCadastroNomeCliente.setMaximumSize(new java.awt.Dimension(25, 25));
        FieldCadastroNomeCliente.setMinimumSize(new java.awt.Dimension(25, 25));
        CampoNome.setDocument(new JTextFieldLimit(40, true));
        FieldCadastroNomeCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FieldCadastroNomeClienteMouseClicked(evt);
            }
        });
        FieldCadastroNomeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldCadastroNomeClienteActionPerformed(evt);
            }
        });
        FieldCadastroNomeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FieldCadastroNomeClienteKeyPressed(evt);
            }
        });

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));

        BotaoSalvarCadastroCliente.setBackground(new java.awt.Color(230, 230, 230));
        BotaoSalvarCadastroCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoSalvarCadastroCliente.setText("Salvar");
        BotaoSalvarCadastroCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoSalvarCadastroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSalvarCadastroClienteActionPerformed(evt);
            }
        });

        jSeparator12.setForeground(new java.awt.Color(0, 0, 0));

        lblNome4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome4.setText("Celular");

        BotaoNovoCadastroCliente.setBackground(new java.awt.Color(230, 230, 230));
        BotaoNovoCadastroCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoNovoCadastroCliente.setText("Novo");
        BotaoNovoCadastroCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoNovoCadastroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoNovoCadastroClienteActionPerformed(evt);
            }
        });

        lblCPF4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblCPF4.setText("CPF");

        FieldCadastroCPFCliente.setBackground(new java.awt.Color(240, 240, 240));
        FieldCadastroCPFCliente.setBorder(null);
        try {
            FieldCadastroCPFCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        FieldCadastroCPFCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        FieldCadastroCPFCliente.setPreferredSize(new java.awt.Dimension(112, 25));
        FieldCadastroCPFCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldCadastroCPFClienteActionPerformed(evt);
            }
        });

        jSeparator14.setForeground(new java.awt.Color(0, 0, 0));

        FieldCadastroTelefoneCliente.setBackground(new java.awt.Color(240, 240, 240));
        FieldCadastroTelefoneCliente.setBorder(null);
        try {
            FieldCadastroTelefoneCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        FieldCadastroTelefoneCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        FieldCadastroTelefoneCliente.setPreferredSize(new java.awt.Dimension(49, 25));

        BotaoCancelarCadastroCliente.setBackground(new java.awt.Color(230, 230, 230));
        BotaoCancelarCadastroCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoCancelarCadastroCliente.setText("Cancelar");
        BotaoCancelarCadastroCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoCancelarCadastroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCancelarCadastroClienteActionPerformed(evt);
            }
        });

        lblNome6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome6.setText("Endereço");

        FieldCadastroEnderecoCliente.setBackground(new java.awt.Color(240, 240, 240));
        FieldCadastroEnderecoCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        FieldCadastroEnderecoCliente.setBorder(null);
        FieldCadastroEnderecoCliente.setMaximumSize(new java.awt.Dimension(25, 25));
        FieldCadastroEnderecoCliente.setMinimumSize(new java.awt.Dimension(25, 25));
        CampoNome.setDocument(new JTextFieldLimit(40, true));
        FieldCadastroEnderecoCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FieldCadastroEnderecoClienteMouseClicked(evt);
            }
        });
        FieldCadastroEnderecoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldCadastroEnderecoClienteActionPerformed(evt);
            }
        });
        FieldCadastroEnderecoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FieldCadastroEnderecoClienteKeyPressed(evt);
            }
        });

        jSeparator16.setForeground(new java.awt.Color(0, 0, 0));

        lblNome8.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome8.setText("Email");

        FieldCadastroEmailCliente.setBackground(new java.awt.Color(240, 240, 240));
        FieldCadastroEmailCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        FieldCadastroEmailCliente.setBorder(null);
        FieldCadastroEmailCliente.setMaximumSize(new java.awt.Dimension(25, 25));
        FieldCadastroEmailCliente.setMinimumSize(new java.awt.Dimension(25, 25));
        CampoNome.setDocument(new JTextFieldLimit(40, true));
        FieldCadastroEmailCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FieldCadastroEmailClienteMouseClicked(evt);
            }
        });
        FieldCadastroEmailCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldCadastroEmailClienteActionPerformed(evt);
            }
        });
        FieldCadastroEmailCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FieldCadastroEmailClienteKeyPressed(evt);
            }
        });

        jSeparator17.setForeground(new java.awt.Color(0, 0, 0));

        lblNome9.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome9.setText("Cidade");

        FieldCadastroCidadeCliente.setBackground(new java.awt.Color(240, 240, 240));
        FieldCadastroCidadeCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        FieldCadastroCidadeCliente.setBorder(null);
        FieldCadastroCidadeCliente.setMaximumSize(new java.awt.Dimension(25, 25));
        FieldCadastroCidadeCliente.setMinimumSize(new java.awt.Dimension(25, 25));
        CampoNome.setDocument(new JTextFieldLimit(40, true));
        FieldCadastroCidadeCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FieldCadastroCidadeClienteMouseClicked(evt);
            }
        });
        FieldCadastroCidadeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldCadastroCidadeClienteActionPerformed(evt);
            }
        });
        FieldCadastroCidadeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FieldCadastroCidadeClienteKeyPressed(evt);
            }
        });

        jSeparator21.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNome8)
                            .addComponent(FieldCadastroEmailCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(BotaoNovoCadastroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 522, Short.MAX_VALUE)
                                .addComponent(BotaoSalvarCadastroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(BotaoCancelarCadastroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblNome3)
                                    .addComponent(FieldCadastroNomeCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                                    .addComponent(jSeparator9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCPF4)
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(FieldCadastroCPFCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(71, 71, 71)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNome4)
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(FieldCadastroTelefoneCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSeparator12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblNome9)
                                    .addComponent(FieldCadastroCidadeCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                                    .addComponent(jSeparator21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNome6)
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(FieldCadastroEnderecoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(58, 58, 58))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(lblNome4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FieldCadastroTelefoneCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(lblNome3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FieldCadastroNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(lblCPF4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FieldCadastroCPFCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(lblNome6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FieldCadastroEnderecoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(lblNome9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FieldCadastroCidadeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator21, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addComponent(lblNome8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(FieldCadastroEmailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoSalvarCadastroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoNovoCadastroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoCancelarCadastroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(119, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CadastrarClientesLayout = new javax.swing.GroupLayout(CadastrarClientes);
        CadastrarClientes.setLayout(CadastrarClientesLayout);
        CadastrarClientesLayout.setHorizontalGroup(
            CadastrarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1144, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        CadastrarClientesLayout.setVerticalGroup(
            CadastrarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Cadastrar", CadastrarClientes);

        ConsultarClientes.setPreferredSize(new java.awt.Dimension(1144, 429));

        lblNome2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome2.setText("Nome do cliente");

        FieldConsultaNomeCliente.setBackground(new java.awt.Color(240, 240, 240));
        FieldConsultaNomeCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        FieldConsultaNomeCliente.setBorder(null);
        FieldConsultaNomeCliente.setMaximumSize(new java.awt.Dimension(25, 25));
        FieldConsultaNomeCliente.setMinimumSize(new java.awt.Dimension(25, 25));
        CampoNome.setDocument(new JTextFieldLimit(40, true));
        FieldConsultaNomeCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FieldConsultaNomeClienteMouseClicked(evt);
            }
        });
        FieldConsultaNomeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldConsultaNomeClienteActionPerformed(evt);
            }
        });
        FieldConsultaNomeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FieldConsultaNomeClienteKeyPressed(evt);
            }
        });

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));

        lblCPF2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblCPF2.setText("CPF");

        TableConsultaCliente.setAutoCreateRowSorter(true);
        TableConsultaCliente.setBackground(new java.awt.Color(240, 240, 240));
        TableConsultaCliente.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        TableConsultaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Nome", "CPF", "Telefone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TableConsultaCliente.setFocusable(false);
        TableConsultaCliente.setGridColor(new java.awt.Color(230, 230, 230));
        TableConsultaCliente.setSelectionBackground(new java.awt.Color(230, 230, 230));
        TableConsultaCliente.setSelectionForeground(new java.awt.Color(230, 230, 230));
        TableConsultaCliente.setShowHorizontalLines(false);
        jScrollPane2.setViewportView(TableConsultaCliente);

        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));

        BotaoBuscarConsultaCliente.setBackground(new java.awt.Color(230, 230, 230));
        BotaoBuscarConsultaCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoBuscarConsultaCliente.setText("Buscar");
        BotaoBuscarConsultaCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoBuscarConsultaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoBuscarConsultaClienteActionPerformed(evt);
            }
        });

        FieldConsultaCPFCliente.setBackground(new java.awt.Color(240, 240, 240));
        FieldConsultaCPFCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        FieldConsultaCPFCliente.setBorder(null);
        FieldConsultaCPFCliente.setMaximumSize(new java.awt.Dimension(25, 25));
        FieldConsultaCPFCliente.setMinimumSize(new java.awt.Dimension(25, 25));
        CampoNome.setDocument(new JTextFieldLimit(40, true));
        FieldConsultaCPFCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FieldConsultaCPFClienteMouseClicked(evt);
            }
        });
        FieldConsultaCPFCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldConsultaCPFClienteActionPerformed(evt);
            }
        });
        FieldConsultaCPFCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FieldConsultaCPFClienteKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout ConsultarClientesLayout = new javax.swing.GroupLayout(ConsultarClientes);
        ConsultarClientes.setLayout(ConsultarClientesLayout);
        ConsultarClientesLayout.setHorizontalGroup(
            ConsultarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConsultarClientesLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(ConsultarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConsultarClientesLayout.createSequentialGroup()
                        .addGroup(ConsultarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNome2)
                            .addComponent(FieldConsultaNomeCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(ConsultarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator11, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                            .addComponent(lblCPF2)
                            .addComponent(FieldConsultaCPFCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(69, 69, 69)
                        .addComponent(BotaoBuscarConsultaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGap(58, 58, 58))
        );
        ConsultarClientesLayout.setVerticalGroup(
            ConsultarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConsultarClientesLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(ConsultarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ConsultarClientesLayout.createSequentialGroup()
                        .addComponent(lblNome2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FieldConsultaNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ConsultarClientesLayout.createSequentialGroup()
                        .addGroup(ConsultarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ConsultarClientesLayout.createSequentialGroup()
                                .addComponent(lblCPF2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(FieldConsultaCPFCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(BotaoBuscarConsultaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator11)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Consultar", ConsultarClientes);

        BotaoSalvarGerenciarClientes.setBackground(new java.awt.Color(230, 230, 230));
        BotaoSalvarGerenciarClientes.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoSalvarGerenciarClientes.setText("Salvar");
        BotaoSalvarGerenciarClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoSalvarGerenciarClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoSalvarGerenciarClientesMouseClicked(evt);
            }
        });
        BotaoSalvarGerenciarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSalvarGerenciarClientesActionPerformed(evt);
            }
        });

        BotaoCancelarGerenciarClientes.setBackground(new java.awt.Color(230, 230, 230));
        BotaoCancelarGerenciarClientes.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoCancelarGerenciarClientes.setText("Cancelar");
        BotaoCancelarGerenciarClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoCancelarGerenciarClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoCancelarGerenciarClientesMouseClicked(evt);
            }
        });
        BotaoCancelarGerenciarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCancelarGerenciarClientesActionPerformed(evt);
            }
        });

        jSeparator18.setForeground(new java.awt.Color(0, 0, 0));

        FieldNomeGerenciarClientes.setBackground(new java.awt.Color(240, 240, 240));
        FieldNomeGerenciarClientes.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        FieldNomeGerenciarClientes.setBorder(null);
        FieldNomeGerenciarClientes.setMaximumSize(new java.awt.Dimension(25, 25));
        FieldNomeGerenciarClientes.setMinimumSize(new java.awt.Dimension(25, 25));
        FieldNomeCadastrarConsertos.setDocument(new JTextFieldLimit(40, true));
        FieldNomeGerenciarClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FieldNomeGerenciarClientesMouseClicked(evt);
            }
        });
        FieldNomeGerenciarClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FieldNomeGerenciarClientesKeyPressed(evt);
            }
        });

        lblNome10.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome10.setText("Nome do cliente");

        BotaoExcluirGerenciarClientes.setBackground(new java.awt.Color(230, 230, 230));
        BotaoExcluirGerenciarClientes.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoExcluirGerenciarClientes.setText("Excluir");
        BotaoExcluirGerenciarClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoExcluirGerenciarClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoExcluirGerenciarClientesMouseClicked(evt);
            }
        });
        BotaoExcluirGerenciarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoExcluirGerenciarClientesActionPerformed(evt);
            }
        });

        BotaoBuscarGerenciarClientes.setBackground(new java.awt.Color(230, 230, 230));
        BotaoBuscarGerenciarClientes.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoBuscarGerenciarClientes.setText("Buscar");
        BotaoBuscarGerenciarClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoBuscarGerenciarClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoBuscarGerenciarClientesMouseClicked(evt);
            }
        });
        BotaoBuscarGerenciarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoBuscarGerenciarClientesActionPerformed(evt);
            }
        });

        BotaoAlterarGerenciarClientes.setBackground(new java.awt.Color(230, 230, 230));
        BotaoAlterarGerenciarClientes.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoAlterarGerenciarClientes.setText("Alterar");
        BotaoAlterarGerenciarClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoAlterarGerenciarClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoAlterarGerenciarClientesMouseClicked(evt);
            }
        });
        BotaoAlterarGerenciarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAlterarGerenciarClientesActionPerformed(evt);
            }
        });

        TableGerenciarClientes.setAutoCreateRowSorter(true);
        TableGerenciarClientes.setBackground(new java.awt.Color(240, 240, 240));
        TableGerenciarClientes.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TableGerenciarClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Nome", "CPF", "Telefone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TableGerenciarClientes.setFocusable(false);
        TableGerenciarClientes.setGridColor(new java.awt.Color(230, 230, 230));
        TableGerenciarClientes.setSelectionBackground(new java.awt.Color(230, 230, 230));
        TableGerenciarClientes.setSelectionForeground(new java.awt.Color(230, 230, 230));
        TableGerenciarClientes.setShowHorizontalLines(false);
        jScrollPane5.setViewportView(TableGerenciarClientes);

        FieldCPFGerenciarClientes.setBackground(new java.awt.Color(240, 240, 240));
        FieldCPFGerenciarClientes.setBorder(null);
        try {
            FieldCPFGerenciarClientes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        FieldCPFGerenciarClientes.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        FieldCPFGerenciarClientes.setPreferredSize(new java.awt.Dimension(112, 25));
        FieldCPFGerenciarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldCPFGerenciarClientesActionPerformed(evt);
            }
        });

        lblCPF3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblCPF3.setText("CPF");

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout GerenciarClientesLayout = new javax.swing.GroupLayout(GerenciarClientes);
        GerenciarClientes.setLayout(GerenciarClientesLayout);
        GerenciarClientesLayout.setHorizontalGroup(
            GerenciarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GerenciarClientesLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(GerenciarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNome10)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addComponent(FieldNomeGerenciarClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator18))
                .addGap(43, 43, 43)
                .addGroup(GerenciarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GerenciarClientesLayout.createSequentialGroup()
                        .addComponent(BotaoExcluirGerenciarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(BotaoAlterarGerenciarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(GerenciarClientesLayout.createSequentialGroup()
                        .addGroup(GerenciarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator8)
                            .addComponent(FieldCPFGerenciarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCPF3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(GerenciarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BotaoBuscarGerenciarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoSalvarGerenciarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoCancelarGerenciarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58))))
        );
        GerenciarClientesLayout.setVerticalGroup(
            GerenciarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GerenciarClientesLayout.createSequentialGroup()
                .addGroup(GerenciarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GerenciarClientesLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(GerenciarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNome10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCPF3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(GerenciarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FieldNomeGerenciarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FieldCPFGerenciarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(GerenciarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GerenciarClientesLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaoBuscarGerenciarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)))
                .addGroup(GerenciarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(GerenciarClientesLayout.createSequentialGroup()
                        .addComponent(BotaoSalvarGerenciarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaoCancelarGerenciarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(GerenciarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoExcluirGerenciarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoAlterarGerenciarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(107, 107, 107))
        );

        jTabbedPane3.addTab("Gerenciar", GerenciarClientes);

        jPanel17.setPreferredSize(new java.awt.Dimension(1144, 199));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(25, 25, 112));
        jLabel10.setText("CLIENTES");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(474, 474, 474))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PaneClientesLayout = new javax.swing.GroupLayout(PaneClientes);
        PaneClientes.setLayout(PaneClientesLayout);
        PaneClientesLayout.setHorizontalGroup(
            PaneClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 1149, Short.MAX_VALUE)
        );
        PaneClientesLayout.setVerticalGroup(
            PaneClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneClientesLayout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE))
        );

        PaneMae.add(PaneClientes, "card3");

        PaneGarantias.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        PaneGarantias.setPreferredSize(new java.awt.Dimension(1144, 429));

        jTabbedPane2.setPreferredSize(new java.awt.Dimension(1144, 429));
        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseClicked(evt);
            }
        });

        CadastrarGarantias.setForeground(new java.awt.Color(255, 255, 255));
        CadastrarGarantias.setDoubleBuffered(false);
        CadastrarGarantias.setPreferredSize(new java.awt.Dimension(1144, 429));
        CadastrarGarantias.setRequestFocusEnabled(false);
        CadastrarGarantias.setVerifyInputWhenFocusTarget(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(356, 200));

        lblNome.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome.setText("Nome do cliente");

        CampoNome.setBackground(new java.awt.Color(240, 240, 240));
        CampoNome.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        CampoNome.setBorder(null);
        CampoNome.setMaximumSize(new java.awt.Dimension(25, 25));
        CampoNome.setMinimumSize(new java.awt.Dimension(25, 25));
        CampoNome.setDocument(new JTextFieldLimit(40, true));
        CampoNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CampoNomeMouseClicked(evt);
            }
        });
        CampoNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CampoNomeKeyPressed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        lblData.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblData.setText("Data do conserto");

        CampoDataFormatada.setBackground(new java.awt.Color(240, 240, 240));
        CampoDataFormatada.setBorder(null);
        try {
            CampoDataFormatada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        CampoDataFormatada.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        CampoDataFormatada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoDataFormatadaActionPerformed(evt);
            }
        });

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        lblConserto.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblConserto.setText("Conserto");

        BotaoSalvarNovaRevista.setBackground(new java.awt.Color(230, 230, 230));
        BotaoSalvarNovaRevista.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoSalvarNovaRevista.setText("Salvar");
        BotaoSalvarNovaRevista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoSalvarNovaRevista.setPreferredSize(new java.awt.Dimension(151, 50));
        BotaoSalvarNovaRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSalvarNovaRevistaActionPerformed(evt);
            }
        });

        ComboEscolhaConserto.setBackground(new java.awt.Color(240, 240, 240));
        ComboEscolhaConserto.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNome)
                            .addComponent(jSeparator2)
                            .addComponent(CampoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblData)
                            .addComponent(jSeparator4)
                            .addComponent(CampoDataFormatada, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(259, 259, 259))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblConserto)
                            .addComponent(ComboEscolhaConserto, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BotaoSalvarNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(270, 270, 270))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CampoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CampoDataFormatada, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(73, 73, 73)
                .addComponent(lblConserto)
                .addGap(18, 18, 18)
                .addComponent(ComboEscolhaConserto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(BotaoSalvarNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout CadastrarGarantiasLayout = new javax.swing.GroupLayout(CadastrarGarantias);
        CadastrarGarantias.setLayout(CadastrarGarantiasLayout);
        CadastrarGarantiasLayout.setHorizontalGroup(
            CadastrarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1139, Short.MAX_VALUE)
        );
        CadastrarGarantiasLayout.setVerticalGroup(
            CadastrarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Cadastrar", CadastrarGarantias);

        ConsultarGarantias.setPreferredSize(new java.awt.Dimension(1144, 429));

        lblNome1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome1.setText("Nome do cliente");

        TxtNome1.setBackground(new java.awt.Color(240, 240, 240));
        TxtNome1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TxtNome1.setBorder(null);
        TxtNome1.setMaximumSize(new java.awt.Dimension(25, 25));
        TxtNome1.setMinimumSize(new java.awt.Dimension(25, 25));
        TxtNome1.setPreferredSize(new java.awt.Dimension(1, 25));
        CampoNome.setDocument(new JTextFieldLimit(40, true));
        TxtNome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtNome1MouseClicked(evt);
            }
        });
        TxtNome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNome1ActionPerformed(evt);
            }
        });
        TxtNome1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNome1KeyPressed(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        lblCPF1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblCPF1.setText("CPF");

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        TableConsultaGarantia.setAutoCreateRowSorter(true);
        TableConsultaGarantia.setBackground(new java.awt.Color(240, 240, 240));
        TableConsultaGarantia.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        TableConsultaGarantia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "CPF", "Telefone", "Conserto", "Data do conserto", "Tempo de garantia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TableConsultaGarantia.setFocusable(false);
        TableConsultaGarantia.setGridColor(new java.awt.Color(230, 230, 230));
        TableConsultaGarantia.setSelectionBackground(new java.awt.Color(230, 230, 230));
        TableConsultaGarantia.setSelectionForeground(new java.awt.Color(230, 230, 230));
        TableConsultaGarantia.setShowHorizontalLines(false);
        jScrollPane1.setViewportView(TableConsultaGarantia);

        FieldCPFConsultarGarantias.setBackground(new java.awt.Color(240, 240, 240));
        FieldCPFConsultarGarantias.setBorder(null);
        try {
            FieldCPFConsultarGarantias.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        FieldCPFConsultarGarantias.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        FieldCPFConsultarGarantias.setPreferredSize(new java.awt.Dimension(112, 25));
        FieldCPFConsultarGarantias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldCPFConsultarGarantiasActionPerformed(evt);
            }
        });

        BotaoBuscarConsultarGarantias.setBackground(new java.awt.Color(230, 230, 230));
        BotaoBuscarConsultarGarantias.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoBuscarConsultarGarantias.setText("Buscar");
        BotaoBuscarConsultarGarantias.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoBuscarConsultarGarantias.setPreferredSize(new java.awt.Dimension(151, 50));
        BotaoBuscarConsultarGarantias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoBuscarConsultarGarantiasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ConsultarGarantiasLayout = new javax.swing.GroupLayout(ConsultarGarantias);
        ConsultarGarantias.setLayout(ConsultarGarantiasLayout);
        ConsultarGarantiasLayout.setHorizontalGroup(
            ConsultarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConsultarGarantiasLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(ConsultarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ConsultarGarantiasLayout.createSequentialGroup()
                        .addGroup(ConsultarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNome1)
                            .addComponent(TxtNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(98, 98, 98)
                        .addGroup(ConsultarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCPF1)
                            .addComponent(FieldCPFConsultarGarantias, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(jSeparator6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaoBuscarConsultarGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1026, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        ConsultarGarantiasLayout.setVerticalGroup(
            ConsultarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConsultarGarantiasLayout.createSequentialGroup()
                .addGroup(ConsultarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConsultarGarantiasLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(ConsultarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNome1)
                            .addComponent(lblCPF1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ConsultarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtNome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FieldCPFConsultarGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(ConsultarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ConsultarGarantiasLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(BotaoBuscarConsultarGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Consultar", ConsultarGarantias);

        GerenciarGarantias.setPreferredSize(new java.awt.Dimension(1144, 429));

        jSeparator19.setForeground(new java.awt.Color(0, 0, 0));

        BotaoSalvarGerenciarGarantias.setBackground(new java.awt.Color(230, 230, 230));
        BotaoSalvarGerenciarGarantias.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoSalvarGerenciarGarantias.setText("Salvar");
        BotaoSalvarGerenciarGarantias.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoSalvarGerenciarGarantias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoSalvarGerenciarGarantiasMouseClicked(evt);
            }
        });
        BotaoSalvarGerenciarGarantias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSalvarGerenciarGarantiasActionPerformed(evt);
            }
        });

        BotaoCancelarGerenciarGarantias.setBackground(new java.awt.Color(230, 230, 230));
        BotaoCancelarGerenciarGarantias.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoCancelarGerenciarGarantias.setText("Cancelar");
        BotaoCancelarGerenciarGarantias.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoCancelarGerenciarGarantias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoCancelarGerenciarGarantiasMouseClicked(evt);
            }
        });
        BotaoCancelarGerenciarGarantias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCancelarGerenciarGarantiasActionPerformed(evt);
            }
        });

        BotaoExcluirGerenciarGarantias.setBackground(new java.awt.Color(230, 230, 230));
        BotaoExcluirGerenciarGarantias.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoExcluirGerenciarGarantias.setText("Excluir");
        BotaoExcluirGerenciarGarantias.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoExcluirGerenciarGarantias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoExcluirGerenciarGarantiasMouseClicked(evt);
            }
        });
        BotaoExcluirGerenciarGarantias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoExcluirGerenciarGarantiasActionPerformed(evt);
            }
        });

        FieldNomeGerenciarGarantias.setBackground(new java.awt.Color(240, 240, 240));
        FieldNomeGerenciarGarantias.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        FieldNomeGerenciarGarantias.setBorder(null);
        FieldNomeGerenciarGarantias.setMaximumSize(new java.awt.Dimension(25, 25));
        FieldNomeGerenciarGarantias.setMinimumSize(new java.awt.Dimension(25, 25));
        FieldNomeGerenciarGarantias.setPreferredSize(new java.awt.Dimension(1, 25));
        FieldNomeCadastrarConsertos.setDocument(new JTextFieldLimit(40, true));
        FieldNomeGerenciarGarantias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FieldNomeGerenciarGarantiasMouseClicked(evt);
            }
        });
        FieldNomeGerenciarGarantias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FieldNomeGerenciarGarantiasKeyPressed(evt);
            }
        });

        lblNome11.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome11.setText("Nome do cliente");

        BotaoBuscarGerenciarGarantias.setBackground(new java.awt.Color(230, 230, 230));
        BotaoBuscarGerenciarGarantias.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoBuscarGerenciarGarantias.setText("Buscar");
        BotaoBuscarGerenciarGarantias.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoBuscarGerenciarGarantias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoBuscarGerenciarGarantiasMouseClicked(evt);
            }
        });
        BotaoBuscarGerenciarGarantias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoBuscarGerenciarGarantiasActionPerformed(evt);
            }
        });

        BotaoAlterarGerenciarGarantias.setBackground(new java.awt.Color(230, 230, 230));
        BotaoAlterarGerenciarGarantias.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoAlterarGerenciarGarantias.setText("Alterar");
        BotaoAlterarGerenciarGarantias.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoAlterarGerenciarGarantias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoAlterarGerenciarGarantiasMouseClicked(evt);
            }
        });
        BotaoAlterarGerenciarGarantias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAlterarGerenciarGarantiasActionPerformed(evt);
            }
        });

        TableGerenciarGarantias.setAutoCreateRowSorter(true);
        TableGerenciarGarantias.setBackground(new java.awt.Color(240, 240, 240));
        TableGerenciarGarantias.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TableGerenciarGarantias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "CPF", "Telefone", "Conserto", "Data do conserto", "Tempo de garantia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TableGerenciarGarantias.setFocusable(false);
        TableGerenciarGarantias.setGridColor(new java.awt.Color(230, 230, 230));
        TableGerenciarGarantias.setSelectionBackground(new java.awt.Color(230, 230, 230));
        TableGerenciarGarantias.setSelectionForeground(new java.awt.Color(230, 230, 230));
        TableGerenciarGarantias.setShowHorizontalLines(false);
        jScrollPane6.setViewportView(TableGerenciarGarantias);

        FieldCPFGerenciaGarantias.setBackground(new java.awt.Color(240, 240, 240));
        FieldCPFGerenciaGarantias.setBorder(null);
        try {
            FieldCPFGerenciaGarantias.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        FieldCPFGerenciaGarantias.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        FieldCPFGerenciaGarantias.setPreferredSize(new java.awt.Dimension(112, 25));
        FieldCPFGerenciaGarantias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldCPFGerenciaGarantiasActionPerformed(evt);
            }
        });

        lblCPF5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblCPF5.setText("CPF");

        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout GerenciarGarantiasLayout = new javax.swing.GroupLayout(GerenciarGarantias);
        GerenciarGarantias.setLayout(GerenciarGarantiasLayout);
        GerenciarGarantiasLayout.setHorizontalGroup(
            GerenciarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GerenciarGarantiasLayout.createSequentialGroup()
                .addGroup(GerenciarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(GerenciarGarantiasLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BotaoAlterarGerenciarGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(BotaoExcluirGerenciarGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(GerenciarGarantiasLayout.createSequentialGroup()
                        .addGroup(GerenciarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, GerenciarGarantiasLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(GerenciarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblNome11)
                                    .addComponent(FieldNomeGerenciarGarantias, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                                    .addComponent(jSeparator19))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                                .addGroup(GerenciarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator10)
                                    .addComponent(lblCPF5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FieldCPFGerenciaGarantias, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)))
                            .addGroup(GerenciarGarantiasLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(57, 57, 57)
                        .addGroup(GerenciarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BotaoBuscarGerenciarGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoSalvarGerenciarGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoCancelarGerenciarGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(55, 55, 55))
        );
        GerenciarGarantiasLayout.setVerticalGroup(
            GerenciarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GerenciarGarantiasLayout.createSequentialGroup()
                .addGroup(GerenciarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GerenciarGarantiasLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(GerenciarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNome11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCPF5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(GerenciarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FieldNomeGerenciarGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FieldCPFGerenciaGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(GerenciarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))
                    .addGroup(GerenciarGarantiasLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(BotaoBuscarGerenciarGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)))
                .addGroup(GerenciarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(GerenciarGarantiasLayout.createSequentialGroup()
                        .addComponent(BotaoSalvarGerenciarGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BotaoCancelarGerenciarGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addGroup(GerenciarGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoAlterarGerenciarGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoExcluirGerenciarGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        jTabbedPane2.addTab("Gerenciar", GerenciarGarantias);

        jPanel11.setPreferredSize(new java.awt.Dimension(1144, 199));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(25, 25, 112));
        jLabel9.setText("GARANTIAS");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(440, 440, 440)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PaneGarantiasLayout = new javax.swing.GroupLayout(PaneGarantias);
        PaneGarantias.setLayout(PaneGarantiasLayout);
        PaneGarantiasLayout.setHorizontalGroup(
            PaneGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PaneGarantiasLayout.setVerticalGroup(
            PaneGarantiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PaneMae.add(PaneGarantias, "card2");

        PaneServicos.setPreferredSize(new java.awt.Dimension(1144, 429));

        jPanel7.setPreferredSize(new java.awt.Dimension(1144, 429));

        jTabbedPane1.setBackground(new java.awt.Color(230, 230, 230));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        lblNome5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome5.setText("Conserto");

        FieldNomeCadastrarConsertos.setBackground(new java.awt.Color(240, 240, 240));
        FieldNomeCadastrarConsertos.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        FieldNomeCadastrarConsertos.setBorder(null);
        FieldNomeCadastrarConsertos.setMaximumSize(new java.awt.Dimension(25, 25));
        FieldNomeCadastrarConsertos.setMinimumSize(new java.awt.Dimension(25, 25));
        CampoNome.setDocument(new JTextFieldLimit(40, true));
        FieldNomeCadastrarConsertos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FieldNomeCadastrarConsertosMouseClicked(evt);
            }
        });
        FieldNomeCadastrarConsertos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FieldNomeCadastrarConsertosKeyPressed(evt);
            }
        });

        jSeparator13.setForeground(new java.awt.Color(0, 0, 0));

        BotaoNovoCadastroConserto.setBackground(new java.awt.Color(230, 230, 230));
        BotaoNovoCadastroConserto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoNovoCadastroConserto.setText("Novo");
        BotaoNovoCadastroConserto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoNovoCadastroConserto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoNovoCadastroConsertoActionPerformed(evt);
            }
        });

        BotaoSalvarCadastroConserto.setBackground(new java.awt.Color(230, 230, 230));
        BotaoSalvarCadastroConserto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoSalvarCadastroConserto.setText("Salvar");
        BotaoSalvarCadastroConserto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoSalvarCadastroConserto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSalvarCadastroConsertoActionPerformed(evt);
            }
        });

        BotaoCancelarCadastroConserto.setBackground(new java.awt.Color(230, 230, 230));
        BotaoCancelarCadastroConserto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoCancelarCadastroConserto.setText("Cancelar");
        BotaoCancelarCadastroConserto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout CadastrarServicosLayout = new javax.swing.GroupLayout(CadastrarServicos);
        CadastrarServicos.setLayout(CadastrarServicosLayout);
        CadastrarServicosLayout.setHorizontalGroup(
            CadastrarServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CadastrarServicosLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(CadastrarServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CadastrarServicosLayout.createSequentialGroup()
                        .addGroup(CadastrarServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNome5)
                            .addComponent(FieldNomeCadastrarConsertos, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                            .addComponent(jSeparator13))
                        .addContainerGap(665, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CadastrarServicosLayout.createSequentialGroup()
                        .addComponent(BotaoNovoCadastroConserto, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaoSalvarCadastroConserto, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(BotaoCancelarCadastroConserto, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))))
        );
        CadastrarServicosLayout.setVerticalGroup(
            CadastrarServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CadastrarServicosLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(lblNome5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(FieldNomeCadastrarConsertos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(247, 247, 247)
                .addGroup(CadastrarServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoNovoCadastroConserto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoSalvarCadastroConserto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoCancelarCadastroConserto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );

        jTabbedPane1.addTab("Cadastrar", CadastrarServicos);

        FieldConsultarGarantias.setBackground(new java.awt.Color(240, 240, 240));
        FieldConsultarGarantias.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        FieldConsultarGarantias.setBorder(null);
        FieldConsultarGarantias.setMaximumSize(new java.awt.Dimension(25, 25));
        FieldConsultarGarantias.setMinimumSize(new java.awt.Dimension(25, 25));
        FieldNomeCadastrarConsertos.setDocument(new JTextFieldLimit(40, true));
        FieldConsultarGarantias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FieldConsultarGarantiasMouseClicked(evt);
            }
        });
        FieldConsultarGarantias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FieldConsultarGarantiasKeyPressed(evt);
            }
        });

        lblNome7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome7.setText("Conserto");

        jSeparator15.setForeground(new java.awt.Color(0, 0, 0));

        TabelaConsultaManutencao.setAutoCreateRowSorter(true);
        TabelaConsultaManutencao.setBackground(new java.awt.Color(240, 240, 240));
        TabelaConsultaManutencao.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TabelaConsultaManutencao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Conserto", "Tempo de garantia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TabelaConsultaManutencao.setFocusable(false);
        TabelaConsultaManutencao.setGridColor(new java.awt.Color(230, 230, 230));
        TabelaConsultaManutencao.setSelectionBackground(new java.awt.Color(230, 230, 230));
        TabelaConsultaManutencao.setSelectionForeground(new java.awt.Color(230, 230, 230));
        TabelaConsultaManutencao.setShowHorizontalLines(false);
        jScrollPane3.setViewportView(TabelaConsultaManutencao);

        BotaoBuscarGarantias.setBackground(new java.awt.Color(230, 230, 230));
        BotaoBuscarGarantias.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoBuscarGarantias.setText("Buscar");
        BotaoBuscarGarantias.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoBuscarGarantias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoBuscarGarantiasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ConsultarServicosLayout = new javax.swing.GroupLayout(ConsultarServicos);
        ConsultarServicos.setLayout(ConsultarServicosLayout);
        ConsultarServicosLayout.setHorizontalGroup(
            ConsultarServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConsultarServicosLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(ConsultarServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNome7)
                    .addComponent(FieldConsultarGarantias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator15)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(BotaoBuscarGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(393, 393, 393))
        );
        ConsultarServicosLayout.setVerticalGroup(
            ConsultarServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConsultarServicosLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(ConsultarServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BotaoBuscarGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ConsultarServicosLayout.createSequentialGroup()
                        .addComponent(lblNome7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FieldConsultarGarantias, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1, 1, 1)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consultar", ConsultarServicos);

        GerenciarServicos.setPreferredSize(new java.awt.Dimension(1190, 451));

        BotaoSalvarGerenciarServicos.setBackground(new java.awt.Color(230, 230, 230));
        BotaoSalvarGerenciarServicos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoSalvarGerenciarServicos.setText("Salvar");
        BotaoSalvarGerenciarServicos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoSalvarGerenciarServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoSalvarGerenciarServicosMouseClicked(evt);
            }
        });
        BotaoSalvarGerenciarServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSalvarGerenciarServicosActionPerformed(evt);
            }
        });

        jSeparator20.setForeground(new java.awt.Color(0, 0, 0));

        BotaoCancelarGerenciarServicos.setBackground(new java.awt.Color(230, 230, 230));
        BotaoCancelarGerenciarServicos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoCancelarGerenciarServicos.setText("Cancelar");
        BotaoCancelarGerenciarServicos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoCancelarGerenciarServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoCancelarGerenciarServicosMouseClicked(evt);
            }
        });
        BotaoCancelarGerenciarServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCancelarGerenciarServicosActionPerformed(evt);
            }
        });

        FieldNomeGerenciarServicos.setBackground(new java.awt.Color(240, 240, 240));
        FieldNomeGerenciarServicos.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        FieldNomeGerenciarServicos.setBorder(null);
        FieldNomeGerenciarServicos.setMaximumSize(new java.awt.Dimension(25, 25));
        FieldNomeGerenciarServicos.setMinimumSize(new java.awt.Dimension(25, 25));
        FieldNomeCadastrarConsertos.setDocument(new JTextFieldLimit(40, true));
        FieldNomeGerenciarServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FieldNomeGerenciarServicosMouseClicked(evt);
            }
        });
        FieldNomeGerenciarServicos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FieldNomeGerenciarServicosKeyPressed(evt);
            }
        });

        lblNome12.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome12.setText("Conserto");

        BotaoExcluirGerenciarServicos.setBackground(new java.awt.Color(230, 230, 230));
        BotaoExcluirGerenciarServicos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoExcluirGerenciarServicos.setText("Excluir");
        BotaoExcluirGerenciarServicos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoExcluirGerenciarServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoExcluirGerenciarServicosMouseClicked(evt);
            }
        });
        BotaoExcluirGerenciarServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoExcluirGerenciarServicosActionPerformed(evt);
            }
        });

        BotaoBuscarGerenciarServicos.setBackground(new java.awt.Color(230, 230, 230));
        BotaoBuscarGerenciarServicos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoBuscarGerenciarServicos.setText("Buscar");
        BotaoBuscarGerenciarServicos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoBuscarGerenciarServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoBuscarGerenciarServicosMouseClicked(evt);
            }
        });
        BotaoBuscarGerenciarServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoBuscarGerenciarServicosActionPerformed(evt);
            }
        });

        BotaoAlterarGerenciarServicos.setBackground(new java.awt.Color(230, 230, 230));
        BotaoAlterarGerenciarServicos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BotaoAlterarGerenciarServicos.setText("Alterar");
        BotaoAlterarGerenciarServicos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoAlterarGerenciarServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoAlterarGerenciarServicosMouseClicked(evt);
            }
        });
        BotaoAlterarGerenciarServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAlterarGerenciarServicosActionPerformed(evt);
            }
        });

        TableGerenciarServicos.setAutoCreateRowSorter(true);
        TableGerenciarServicos.setBackground(new java.awt.Color(240, 240, 240));
        TableGerenciarServicos.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TableGerenciarServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Conserto", "Tempo de garantia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TableGerenciarServicos.setFocusable(false);
        TableGerenciarServicos.setGridColor(new java.awt.Color(230, 230, 230));
        TableGerenciarServicos.setSelectionBackground(new java.awt.Color(230, 230, 230));
        TableGerenciarServicos.setSelectionForeground(new java.awt.Color(230, 230, 230));
        TableGerenciarServicos.setShowHorizontalLines(false);
        jScrollPane7.setViewportView(TableGerenciarServicos);

        javax.swing.GroupLayout GerenciarServicosLayout = new javax.swing.GroupLayout(GerenciarServicos);
        GerenciarServicos.setLayout(GerenciarServicosLayout);
        GerenciarServicosLayout.setHorizontalGroup(
            GerenciarServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GerenciarServicosLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(GerenciarServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNome12)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addComponent(FieldNomeGerenciarServicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator20))
                .addGap(63, 63, 63)
                .addGroup(GerenciarServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BotaoBuscarGerenciarServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoExcluirGerenciarServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(GerenciarServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BotaoAlterarGerenciarServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoCancelarGerenciarServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoSalvarGerenciarServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(225, 225, 225))
        );
        GerenciarServicosLayout.setVerticalGroup(
            GerenciarServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GerenciarServicosLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(GerenciarServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(GerenciarServicosLayout.createSequentialGroup()
                        .addComponent(lblNome12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FieldNomeGerenciarServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BotaoBuscarGerenciarServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addGroup(GerenciarServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(GerenciarServicosLayout.createSequentialGroup()
                        .addGroup(GerenciarServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(GerenciarServicosLayout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(BotaoCancelarGerenciarServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(BotaoSalvarGerenciarServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(GerenciarServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BotaoAlterarGerenciarServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoExcluirGerenciarServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(GerenciarServicosLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );

        jTabbedPane1.addTab("Gerenciar", GerenciarServicos);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1149, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jPanel6.setPreferredSize(new java.awt.Dimension(1144, 199));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(25, 25, 112));
        jLabel7.setText("CONSERTOS");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(436, 436, 436)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel7)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PaneServicosLayout = new javax.swing.GroupLayout(PaneServicos);
        PaneServicos.setLayout(PaneServicosLayout);
        PaneServicosLayout.setHorizontalGroup(
            PaneServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1149, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 1149, Short.MAX_VALUE)
        );
        PaneServicosLayout.setVerticalGroup(
            PaneServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneServicosLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE))
        );

        PaneMae.add(PaneServicos, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(SideBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PaneMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SideBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PaneMae, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("Gerenciamento de Garantia");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //----------------------Inicio das ações de revista ------------------------------------
    private void BotaoClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoClientesMouseClicked
        PaneClientes.setVisible(true);
        PaneGarantias.setVisible(false);
        PaneServicos.setVisible(false);
        setLblColor(BotaoClientes);
        ResetColor(BotaoGarantias);
        ResetColor(BotaoConsertos);
    }//GEN-LAST:event_BotaoClientesMouseClicked

    private void BotaoGarantiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoGarantiasMouseClicked
        PaneClientes.setVisible(false);
        PaneGarantias.setVisible(true);
        PaneServicos.setVisible(false);
        setLblColor(BotaoGarantias);
        ResetColor(BotaoClientes);
        ResetColor(BotaoConsertos);
    }//GEN-LAST:event_BotaoGarantiasMouseClicked


    private void BotaoConsertosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoConsertosMouseClicked
        PaneClientes.setVisible(false);
        PaneGarantias.setVisible(false);
        PaneServicos.setVisible(true);
        ResetColor(BotaoClientes);
        ResetColor(BotaoGarantias);
        setLblColor(BotaoConsertos);
    }//GEN-LAST:event_BotaoConsertosMouseClicked

    private void CampoNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CampoNomeMouseClicked

    }//GEN-LAST:event_CampoNomeMouseClicked

    private void CampoNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CampoNomeKeyPressed

    }//GEN-LAST:event_CampoNomeKeyPressed

    private void TxtNome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtNome1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNome1MouseClicked

    private void TxtNome1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNome1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNome1KeyPressed

    private void TxtNome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNome1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNome1ActionPerformed

    private void BotaoSalvarNovaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSalvarNovaRevistaActionPerformed
        /*if (campoTitulo.getText().isEmpty() || campoData.getText().isEmpty() || campoQuantidade.getText().isEmpty() || ComboBoxEspecificacaoNovaRevista.getSelectedItem() == null || ComboBoxAreaNovaRevista.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Há campos não preenchidos", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {*/
        Garantia gar = new Garantia();

        try {
            gar.getId();
            gar.setNome(CampoNome.getText());
            gar.setDescricao((String) ComboEscolhaConserto.getSelectedItem());
            gar.setSaida_concerto(LocalDate.parse(CampoDataFormatada.getText(), formatter));
            gar.setDt_garantia(LocalDate.parse(FuncGarantia(gar.getSaida_concerto(), gar.getDescricao()), formatter));//atenção redobrada nessa linha

            GarantiaDAO garantiaDAO = new GarantiaDAO();
            garantiaDAO.InserirGarantia(gar);

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
    }//GEN-LAST:event_BotaoSalvarNovaRevistaActionPerformed

    private void CampoDataFormatadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoDataFormatadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoDataFormatadaActionPerformed

    private void FieldConsultaNomeClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FieldConsultaNomeClienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldConsultaNomeClienteMouseClicked

    private void FieldConsultaNomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldConsultaNomeClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldConsultaNomeClienteActionPerformed

    private void FieldConsultaNomeClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FieldConsultaNomeClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldConsultaNomeClienteKeyPressed

    private void FieldNomeCadastrarConsertosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FieldNomeCadastrarConsertosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldNomeCadastrarConsertosMouseClicked

    private void FieldNomeCadastrarConsertosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FieldNomeCadastrarConsertosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldNomeCadastrarConsertosKeyPressed

    private void BotaoNovoCadastroConsertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoNovoCadastroConsertoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoNovoCadastroConsertoActionPerformed

    private void BotaoSalvarCadastroConsertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSalvarCadastroConsertoActionPerformed
        try {
            Manutencao man = new Manutencao();
            ManutencaoDAO mandao = new ManutencaoDAO();

            man.getId();
            man.setDescricao(FieldNomeCadastrarConsertos.getText());

            mandao.InserirManutencao(man);

            try {

                JOptionPane.showMessageDialog(null, "Manutencao cadastrado com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Algo de errado ocorreu! Erro: " + ex.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(ex.getMessage());
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Essa revista já existe! Erro:" + ex.getMessage(), "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_BotaoSalvarCadastroConsertoActionPerformed

    private void FieldNomeGerenciarClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FieldNomeGerenciarClientesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldNomeGerenciarClientesMouseClicked

    private void FieldNomeGerenciarClientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FieldNomeGerenciarClientesKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldNomeGerenciarClientesKeyPressed

    private void BotaoExcluirGerenciarClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoExcluirGerenciarClientesMouseClicked
        BotaoSalvarGerenciarClientes.setVisible(false);
        BotaoBuscarGerenciarClientes.setVisible(true);
        BotaoAlterarGerenciarClientes.setVisible(false);
        BotaoExcluirGerenciarClientes.setVisible(false);
        BotaoCancelarGerenciarClientes.setVisible(false);
    }//GEN-LAST:event_BotaoExcluirGerenciarClientesMouseClicked

    private void BotaoExcluirGerenciarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoExcluirGerenciarClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoExcluirGerenciarClientesActionPerformed

    private void BotaoBuscarGerenciarClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoBuscarGerenciarClientesMouseClicked
        BotaoSalvarGerenciarClientes.setVisible(false);
        BotaoBuscarGerenciarClientes.setVisible(true);
        BotaoAlterarGerenciarClientes.setVisible(true);
        BotaoExcluirGerenciarClientes.setVisible(true);
        BotaoCancelarGerenciarClientes.setVisible(false);
    }//GEN-LAST:event_BotaoBuscarGerenciarClientesMouseClicked

    private void BotaoBuscarGerenciarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoBuscarGerenciarClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoBuscarGerenciarClientesActionPerformed

    private void BotaoAlterarGerenciarClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoAlterarGerenciarClientesMouseClicked
        BotaoSalvarGerenciarClientes.setVisible(true);
        BotaoBuscarGerenciarClientes.setVisible(true);
        BotaoAlterarGerenciarClientes.setVisible(false);
        BotaoExcluirGerenciarClientes.setVisible(false);
        BotaoCancelarGerenciarClientes.setVisible(true);
    }//GEN-LAST:event_BotaoAlterarGerenciarClientesMouseClicked

    private void BotaoAlterarGerenciarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAlterarGerenciarClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoAlterarGerenciarClientesActionPerformed

    private void BotaoSalvarGerenciarClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoSalvarGerenciarClientesMouseClicked
        BotaoSalvarGerenciarClientes.setVisible(false);
        BotaoBuscarGerenciarClientes.setVisible(true);
        BotaoAlterarGerenciarClientes.setVisible(true);
        BotaoExcluirGerenciarClientes.setVisible(true);
        BotaoCancelarGerenciarClientes.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoSalvarGerenciarClientesMouseClicked

    private void BotaoSalvarGerenciarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSalvarGerenciarClientesActionPerformed

    }//GEN-LAST:event_BotaoSalvarGerenciarClientesActionPerformed

    private void BotaoCancelarGerenciarClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoCancelarGerenciarClientesMouseClicked
        BotaoSalvarGerenciarClientes.setVisible(false);
        BotaoBuscarGerenciarClientes.setVisible(true);
        BotaoAlterarGerenciarClientes.setVisible(true);
        BotaoExcluirGerenciarClientes.setVisible(true);
        BotaoCancelarGerenciarClientes.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoCancelarGerenciarClientesMouseClicked

    private void BotaoCancelarGerenciarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelarGerenciarClientesActionPerformed

    }//GEN-LAST:event_BotaoCancelarGerenciarClientesActionPerformed

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked

    }//GEN-LAST:event_jTabbedPane2MouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jTabbedPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane3MouseClicked

    }//GEN-LAST:event_jTabbedPane3MouseClicked

    private void FieldCPFGerenciarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldCPFGerenciarClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCPFGerenciarClientesActionPerformed

    private void BotaoSalvarGerenciarGarantiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoSalvarGerenciarGarantiasMouseClicked
        BotaoSalvarGerenciarGarantias.setVisible(false);
        BotaoBuscarGerenciarGarantias.setVisible(true);
        BotaoAlterarGerenciarGarantias.setVisible(true);
        BotaoExcluirGerenciarGarantias.setVisible(true);
        BotaoCancelarGerenciarGarantias.setVisible(false);
    }//GEN-LAST:event_BotaoSalvarGerenciarGarantiasMouseClicked

    private void BotaoSalvarGerenciarGarantiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSalvarGerenciarGarantiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoSalvarGerenciarGarantiasActionPerformed

    private void BotaoCancelarGerenciarGarantiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoCancelarGerenciarGarantiasMouseClicked
        BotaoSalvarGerenciarGarantias.setVisible(false);
        BotaoBuscarGerenciarGarantias.setVisible(true);
        BotaoAlterarGerenciarGarantias.setVisible(true);
        BotaoExcluirGerenciarGarantias.setVisible(true);
        BotaoCancelarGerenciarGarantias.setVisible(false);
    }//GEN-LAST:event_BotaoCancelarGerenciarGarantiasMouseClicked

    private void BotaoCancelarGerenciarGarantiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelarGerenciarGarantiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoCancelarGerenciarGarantiasActionPerformed

    private void BotaoExcluirGerenciarGarantiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoExcluirGerenciarGarantiasMouseClicked
        BotaoSalvarGerenciarGarantias.setVisible(false);
        BotaoBuscarGerenciarGarantias.setVisible(true);
        BotaoAlterarGerenciarGarantias.setVisible(false);
        BotaoExcluirGerenciarGarantias.setVisible(false);
        BotaoCancelarGerenciarGarantias.setVisible(false);
    }//GEN-LAST:event_BotaoExcluirGerenciarGarantiasMouseClicked

    private void BotaoExcluirGerenciarGarantiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoExcluirGerenciarGarantiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoExcluirGerenciarGarantiasActionPerformed

    private void FieldNomeGerenciarGarantiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FieldNomeGerenciarGarantiasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldNomeGerenciarGarantiasMouseClicked

    private void FieldNomeGerenciarGarantiasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FieldNomeGerenciarGarantiasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldNomeGerenciarGarantiasKeyPressed

    private void BotaoBuscarGerenciarGarantiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoBuscarGerenciarGarantiasMouseClicked
        BotaoSalvarGerenciarGarantias.setVisible(false);
        BotaoBuscarGerenciarGarantias.setVisible(true);
        BotaoAlterarGerenciarGarantias.setVisible(true);
        BotaoExcluirGerenciarGarantias.setVisible(true);
        BotaoCancelarGerenciarGarantias.setVisible(false);
    }//GEN-LAST:event_BotaoBuscarGerenciarGarantiasMouseClicked

    private void BotaoBuscarGerenciarGarantiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoBuscarGerenciarGarantiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoBuscarGerenciarGarantiasActionPerformed

    private void BotaoAlterarGerenciarGarantiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoAlterarGerenciarGarantiasMouseClicked
        BotaoSalvarGerenciarGarantias.setVisible(true);
        BotaoBuscarGerenciarGarantias.setVisible(false);
        BotaoAlterarGerenciarGarantias.setVisible(false);
        BotaoExcluirGerenciarGarantias.setVisible(false);
        BotaoCancelarGerenciarGarantias.setVisible(true);
    }//GEN-LAST:event_BotaoAlterarGerenciarGarantiasMouseClicked

    private void BotaoAlterarGerenciarGarantiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAlterarGerenciarGarantiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoAlterarGerenciarGarantiasActionPerformed

    private void FieldCPFGerenciaGarantiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldCPFGerenciaGarantiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCPFGerenciaGarantiasActionPerformed

    private void BotaoSalvarGerenciarServicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoSalvarGerenciarServicosMouseClicked
        BotaoSalvarGerenciarServicos.setVisible(false);
        BotaoBuscarGerenciarServicos.setVisible(true);
        BotaoAlterarGerenciarServicos.setVisible(true);
        BotaoExcluirGerenciarServicos.setVisible(true);
        BotaoCancelarGerenciarServicos.setVisible(false);
    }//GEN-LAST:event_BotaoSalvarGerenciarServicosMouseClicked

    private void BotaoSalvarGerenciarServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSalvarGerenciarServicosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoSalvarGerenciarServicosActionPerformed

    private void BotaoCancelarGerenciarServicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoCancelarGerenciarServicosMouseClicked
        BotaoSalvarGerenciarServicos.setVisible(false);
        BotaoBuscarGerenciarServicos.setVisible(true);
        BotaoAlterarGerenciarServicos.setVisible(true);
        BotaoExcluirGerenciarServicos.setVisible(true);
        BotaoCancelarGerenciarServicos.setVisible(false);
    }//GEN-LAST:event_BotaoCancelarGerenciarServicosMouseClicked

    private void BotaoCancelarGerenciarServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelarGerenciarServicosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoCancelarGerenciarServicosActionPerformed

    private void FieldNomeGerenciarServicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FieldNomeGerenciarServicosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldNomeGerenciarServicosMouseClicked

    private void FieldNomeGerenciarServicosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FieldNomeGerenciarServicosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldNomeGerenciarServicosKeyPressed

    private void BotaoExcluirGerenciarServicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoExcluirGerenciarServicosMouseClicked
        BotaoSalvarGerenciarServicos.setVisible(false);
        BotaoBuscarGerenciarServicos.setVisible(true);
        BotaoAlterarGerenciarServicos.setVisible(false);
        BotaoExcluirGerenciarServicos.setVisible(false);
        BotaoCancelarGerenciarServicos.setVisible(false);
    }//GEN-LAST:event_BotaoExcluirGerenciarServicosMouseClicked

    private void BotaoExcluirGerenciarServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoExcluirGerenciarServicosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoExcluirGerenciarServicosActionPerformed

    private void BotaoBuscarGerenciarServicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoBuscarGerenciarServicosMouseClicked
        BotaoSalvarGerenciarServicos.setVisible(false);
        BotaoBuscarGerenciarServicos.setVisible(true);
        BotaoAlterarGerenciarServicos.setVisible(true);
        BotaoExcluirGerenciarServicos.setVisible(true);
        BotaoCancelarGerenciarServicos.setVisible(false);
    }//GEN-LAST:event_BotaoBuscarGerenciarServicosMouseClicked

    private void BotaoBuscarGerenciarServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoBuscarGerenciarServicosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoBuscarGerenciarServicosActionPerformed

    private void BotaoAlterarGerenciarServicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoAlterarGerenciarServicosMouseClicked
        BotaoSalvarGerenciarServicos.setVisible(false);
        BotaoBuscarGerenciarServicos.setVisible(false);
        BotaoAlterarGerenciarServicos.setVisible(true);
        BotaoExcluirGerenciarServicos.setVisible(true);
        BotaoCancelarGerenciarServicos.setVisible(false);
    }//GEN-LAST:event_BotaoAlterarGerenciarServicosMouseClicked

    private void BotaoAlterarGerenciarServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAlterarGerenciarServicosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoAlterarGerenciarServicosActionPerformed

    private void FieldConsultarGarantiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FieldConsultarGarantiasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldConsultarGarantiasMouseClicked

    private void FieldConsultarGarantiasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FieldConsultarGarantiasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldConsultarGarantiasKeyPressed

    private void BotaoBuscarGarantiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoBuscarGarantiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoBuscarGarantiasActionPerformed

    private void BotaoBuscarConsultaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoBuscarConsultaClienteActionPerformed
        ListaBuscaCliente = null;
        Cliente cli = new Cliente();
        ClienteDAO clidao = new ClienteDAO();
        
        try{
            if(!FieldConsultaNomeCliente.getText().isEmpty()){
                cli.setNome(FieldConsultaNomeCliente.getText());
            }
            if(!FieldConsultaCPFCliente.getText().isEmpty()){
                cli.setCpf(FieldConsultaCPFCliente.getText());
            }
            ListaBuscaCliente = clidao.ListaBuscaCliente(cli);
            atualizarTabelaBuscaCliente();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_BotaoBuscarConsultaClienteActionPerformed

    private void FieldCPFConsultarGarantiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldCPFConsultarGarantiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCPFConsultarGarantiasActionPerformed

    private void BotaoBuscarConsultarGarantiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoBuscarConsultarGarantiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoBuscarConsultarGarantiasActionPerformed

    private void FieldCadastroEmailClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FieldCadastroEmailClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCadastroEmailClienteKeyPressed

    private void FieldCadastroEmailClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldCadastroEmailClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCadastroEmailClienteActionPerformed

    private void FieldCadastroEmailClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FieldCadastroEmailClienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCadastroEmailClienteMouseClicked

    private void FieldCadastroEnderecoClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FieldCadastroEnderecoClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCadastroEnderecoClienteKeyPressed

    private void FieldCadastroEnderecoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldCadastroEnderecoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCadastroEnderecoClienteActionPerformed

    private void FieldCadastroEnderecoClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FieldCadastroEnderecoClienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCadastroEnderecoClienteMouseClicked

    private void BotaoCancelarCadastroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelarCadastroClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoCancelarCadastroClienteActionPerformed

    private void FieldCadastroCPFClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldCadastroCPFClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCadastroCPFClienteActionPerformed

    private void BotaoNovoCadastroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoNovoCadastroClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoNovoCadastroClienteActionPerformed

    private void BotaoSalvarCadastroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSalvarCadastroClienteActionPerformed
        try {
            Cliente cli = new Cliente();
            ClienteDAO clidao = new ClienteDAO();

            cli.getId();
            cli.setNome(FieldCadastroNomeCliente.getText());
            cli.setCpf(FieldCadastroCPFCliente.getText());
            cli.setTelefone(FieldCadastroTelefoneCliente.getText());
            cli.setCidade(FieldCadastroCidadeCliente.getText());
            cli.setEndereco(FieldCadastroEnderecoCliente.getText());
            cli.setEmail(FieldCadastroEmailCliente.getText());
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
    }//GEN-LAST:event_BotaoSalvarCadastroClienteActionPerformed

    private void FieldCadastroNomeClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FieldCadastroNomeClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCadastroNomeClienteKeyPressed

    private void FieldCadastroNomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldCadastroNomeClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCadastroNomeClienteActionPerformed

    private void FieldCadastroNomeClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FieldCadastroNomeClienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCadastroNomeClienteMouseClicked

    private void FieldCadastroCidadeClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FieldCadastroCidadeClienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCadastroCidadeClienteMouseClicked

    private void FieldCadastroCidadeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldCadastroCidadeClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCadastroCidadeClienteActionPerformed

    private void FieldCadastroCidadeClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FieldCadastroCidadeClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCadastroCidadeClienteKeyPressed

    private void FieldConsultaCPFClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FieldConsultaCPFClienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldConsultaCPFClienteMouseClicked

    private void FieldConsultaCPFClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldConsultaCPFClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldConsultaCPFClienteActionPerformed

    private void FieldConsultaCPFClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FieldConsultaCPFClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldConsultaCPFClienteKeyPressed

    public void setLblColor(JLabel lbl) {
        lbl.setBackground(new Color(220, 220, 220));
    }

    public void ResetColor(JLabel lbl) {
        lbl.setBackground(new Color(230, 230, 230));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoAlterarGerenciarClientes;
    private javax.swing.JButton BotaoAlterarGerenciarGarantias;
    private javax.swing.JButton BotaoAlterarGerenciarServicos;
    private javax.swing.JButton BotaoBuscarConsultaCliente;
    private javax.swing.JButton BotaoBuscarConsultarGarantias;
    private javax.swing.JButton BotaoBuscarGarantias;
    private javax.swing.JButton BotaoBuscarGerenciarClientes;
    private javax.swing.JButton BotaoBuscarGerenciarGarantias;
    private javax.swing.JButton BotaoBuscarGerenciarServicos;
    private javax.swing.JButton BotaoCancelarCadastroCliente;
    private javax.swing.JButton BotaoCancelarCadastroConserto;
    private javax.swing.JButton BotaoCancelarGerenciarClientes;
    private javax.swing.JButton BotaoCancelarGerenciarGarantias;
    private javax.swing.JButton BotaoCancelarGerenciarServicos;
    private javax.swing.JLabel BotaoClientes;
    private javax.swing.JLabel BotaoConsertos;
    private javax.swing.JButton BotaoExcluirGerenciarClientes;
    private javax.swing.JButton BotaoExcluirGerenciarGarantias;
    private javax.swing.JButton BotaoExcluirGerenciarServicos;
    private javax.swing.JLabel BotaoGarantias;
    private javax.swing.JButton BotaoNovoCadastroCliente;
    private javax.swing.JButton BotaoNovoCadastroConserto;
    private javax.swing.JButton BotaoSalvarCadastroCliente;
    private javax.swing.JButton BotaoSalvarCadastroConserto;
    private javax.swing.JButton BotaoSalvarGerenciarClientes;
    private javax.swing.JButton BotaoSalvarGerenciarGarantias;
    private javax.swing.JButton BotaoSalvarGerenciarServicos;
    private javax.swing.JButton BotaoSalvarNovaRevista;
    private javax.swing.JPanel CadastrarClientes;
    private javax.swing.JPanel CadastrarGarantias;
    private javax.swing.JPanel CadastrarServicos;
    private javax.swing.JFormattedTextField CampoDataFormatada;
    private javax.swing.JTextField CampoNome;
    private javax.swing.JComboBox<String> ComboEscolhaConserto;
    private javax.swing.JPanel ConsultarClientes;
    private javax.swing.JPanel ConsultarGarantias;
    private javax.swing.JPanel ConsultarServicos;
    private javax.swing.JFormattedTextField FieldCPFConsultarGarantias;
    private javax.swing.JFormattedTextField FieldCPFGerenciaGarantias;
    private javax.swing.JFormattedTextField FieldCPFGerenciarClientes;
    private javax.swing.JFormattedTextField FieldCadastroCPFCliente;
    private javax.swing.JTextField FieldCadastroCidadeCliente;
    private javax.swing.JTextField FieldCadastroEmailCliente;
    private javax.swing.JTextField FieldCadastroEnderecoCliente;
    private javax.swing.JTextField FieldCadastroNomeCliente;
    private javax.swing.JFormattedTextField FieldCadastroTelefoneCliente;
    private javax.swing.JTextField FieldConsultaCPFCliente;
    private javax.swing.JTextField FieldConsultaNomeCliente;
    private javax.swing.JTextField FieldConsultarGarantias;
    private javax.swing.JTextField FieldNomeCadastrarConsertos;
    private javax.swing.JTextField FieldNomeGerenciarClientes;
    private javax.swing.JTextField FieldNomeGerenciarGarantias;
    private javax.swing.JTextField FieldNomeGerenciarServicos;
    private javax.swing.JPanel GerenciarClientes;
    private javax.swing.JPanel GerenciarGarantias;
    private javax.swing.JPanel GerenciarServicos;
    private javax.swing.JPanel PaneClientes;
    private javax.swing.JPanel PaneGarantias;
    private javax.swing.JPanel PaneMae;
    private javax.swing.JPanel PaneServicos;
    private javax.swing.JPanel SideBoard;
    private javax.swing.JTable TabelaConsultaManutencao;
    private javax.swing.JTable TableConsultaCliente;
    private javax.swing.JTable TableConsultaGarantia;
    private javax.swing.JTable TableGerenciarClientes;
    private javax.swing.JTable TableGerenciarGarantias;
    private javax.swing.JTable TableGerenciarServicos;
    private javax.swing.JTextField TxtNome1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lblCPF1;
    private javax.swing.JLabel lblCPF2;
    private javax.swing.JLabel lblCPF3;
    private javax.swing.JLabel lblCPF4;
    private javax.swing.JLabel lblCPF5;
    private javax.swing.JLabel lblConserto;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNome1;
    private javax.swing.JLabel lblNome10;
    private javax.swing.JLabel lblNome11;
    private javax.swing.JLabel lblNome12;
    private javax.swing.JLabel lblNome2;
    private javax.swing.JLabel lblNome3;
    private javax.swing.JLabel lblNome4;
    private javax.swing.JLabel lblNome5;
    private javax.swing.JLabel lblNome6;
    private javax.swing.JLabel lblNome7;
    private javax.swing.JLabel lblNome8;
    private javax.swing.JLabel lblNome9;
    // End of variables declaration//GEN-END:variables
}
