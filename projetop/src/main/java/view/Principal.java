/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import controller.JTextFieldLimit;
import controller.SetupAutoComplete;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michel
 */
public class Principal extends javax.swing.JFrame {

    PreparedStatement stmt = null;
    ResultSet rs = null;


    public Principal() {
        initComponents();
        
        PanelCadastro.setVisible(true);
        PanelConsulta.setVisible(false);
        setLblColor(BotaoCadastro);
        ResetColor(BotaoConsulta);
        ResetColor(BotaoGerenciamento);

    }
    
    /*public void garantia(date dataRealizacao){
        
    }*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SideBoard = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        BotaoConsulta = new javax.swing.JLabel();
        BotaoCadastro = new javax.swing.JLabel();
        BotaoGerenciamento = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        PaneMae = new javax.swing.JPanel();
        PanelCadastro = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        TxtNome = new javax.swing.JTextField();
        lblData = new javax.swing.JLabel();
        TxtData = new javax.swing.JTextField();
        lblConserto = new javax.swing.JLabel();
        lblCPF = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        BotaoCancelarCadastro = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        BotaoOkCadastro = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        CampoescolhaConserto = new java.awt.Choice();
        TxtCPF = new javax.swing.JTextField();
        PanelConsulta = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblNome1 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        lblCPF1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        TxtCPF1 = new javax.swing.JTextField();
        TxtNome1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BotaoPesquisarConsulta = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        PanelGerenciamento = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UEMG Frutal - Revistas");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImages(null);

        SideBoard.setBackground(new java.awt.Color(230, 230, 230));
        SideBoard.setForeground(new java.awt.Color(230, 230, 230));
        SideBoard.setToolTipText("");
        SideBoard.setMaximumSize(new java.awt.Dimension(300, 400));

        jSeparator1.setBackground(new java.awt.Color(25, 25, 112));
        jSeparator1.setForeground(new java.awt.Color(230, 230, 230));

        BotaoConsulta.setBackground(new java.awt.Color(230, 230, 230));
        BotaoConsulta.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        BotaoConsulta.setForeground(new java.awt.Color(25, 25, 112));
        BotaoConsulta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoConsulta.setText("Consulta");
        BotaoConsulta.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoConsulta.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoConsulta.setOpaque(true);
        BotaoConsulta.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoConsultaMouseClicked(evt);
            }
        });

        BotaoCadastro.setBackground(new java.awt.Color(230, 230, 230));
        BotaoCadastro.setFont(new java.awt.Font("Century Gothic", 0, 26)); // NOI18N
        BotaoCadastro.setForeground(new java.awt.Color(25, 25, 112));
        BotaoCadastro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoCadastro.setText("Cadastro");
        BotaoCadastro.setOpaque(true);
        BotaoCadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoCadastroMouseClicked(evt);
            }
        });

        BotaoGerenciamento.setBackground(new java.awt.Color(230, 230, 230));
        BotaoGerenciamento.setFont(new java.awt.Font("Century Gothic", 0, 26)); // NOI18N
        BotaoGerenciamento.setForeground(new java.awt.Color(23, 23, 112));
        BotaoGerenciamento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoGerenciamento.setText("Gerenciamento");
        BotaoGerenciamento.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoGerenciamento.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoGerenciamento.setOpaque(true);
        BotaoGerenciamento.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoGerenciamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoGerenciamentoMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 204));

        javax.swing.GroupLayout SideBoardLayout = new javax.swing.GroupLayout(SideBoard);
        SideBoard.setLayout(SideBoardLayout);
        SideBoardLayout.setHorizontalGroup(
            SideBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BotaoCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BotaoConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
            .addComponent(BotaoGerenciamento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(SideBoardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(SideBoardLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SideBoardLayout.setVerticalGroup(
            SideBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBoardLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel1)
                .addGap(113, 113, 113)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(BotaoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BotaoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BotaoGerenciamento, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PaneMae.setBackground(new java.awt.Color(255, 255, 255));
        PaneMae.setPreferredSize(new java.awt.Dimension(1144, 677));
        PaneMae.setLayout(new java.awt.CardLayout());

        PanelCadastro.setBackground(new java.awt.Color(255, 255, 255));
        PanelCadastro.setPreferredSize(new java.awt.Dimension(1144, 677));

        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setDoubleBuffered(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(1144, 677));
        jPanel3.setRequestFocusEnabled(false);
        jPanel3.setVerifyInputWhenFocusTarget(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(356, 200));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(25, 25, 112));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("CADASTRAR CLIENTES");
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
                .addContainerGap(324, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        lblNome.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome.setText("Nome");

        TxtNome.setBackground(new java.awt.Color(240, 240, 240));
        TxtNome.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TxtNome.setBorder(null);
        TxtNome.setMaximumSize(new java.awt.Dimension(25, 25));
        TxtNome.setMinimumSize(new java.awt.Dimension(25, 25));
        TxtNome.setDocument(new JTextFieldLimit(40, true));
        TxtNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtNomeMouseClicked(evt);
            }
        });
        TxtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNomeKeyPressed(evt);
            }
        });

        lblData.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblData.setText("Data do conserto");

        TxtData.setBackground(new java.awt.Color(240, 240, 240));
        TxtData.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TxtData.setBorder(null);
        TxtData.setPreferredSize(new java.awt.Dimension(35, 26));
        TxtData.setDocument(new JTextFieldLimit(10, false, true));
        TxtData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtDataActionPerformed(evt);
            }
        });

        lblConserto.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblConserto.setText("Conserto");

        lblCPF.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblCPF.setText("CPF");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        BotaoCancelarCadastro.setBackground(new java.awt.Color(230, 230, 230));
        BotaoCancelarCadastro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(190, 190, 190)));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel2.setText("Cancelar");
        jLabel2.setAlignmentX(0.5F);

        javax.swing.GroupLayout BotaoCancelarCadastroLayout = new javax.swing.GroupLayout(BotaoCancelarCadastro);
        BotaoCancelarCadastro.setLayout(BotaoCancelarCadastroLayout);
        BotaoCancelarCadastroLayout.setHorizontalGroup(
            BotaoCancelarCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BotaoCancelarCadastroLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel2)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        BotaoCancelarCadastroLayout.setVerticalGroup(
            BotaoCancelarCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BotaoCancelarCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BotaoOkCadastro.setBackground(new java.awt.Color(230, 230, 230));
        BotaoOkCadastro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(190, 190, 190)));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel5.setText("OK");
        jLabel5.setAlignmentX(0.5F);

        javax.swing.GroupLayout BotaoOkCadastroLayout = new javax.swing.GroupLayout(BotaoOkCadastro);
        BotaoOkCadastro.setLayout(BotaoOkCadastroLayout);
        BotaoOkCadastroLayout.setHorizontalGroup(
            BotaoOkCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BotaoOkCadastroLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel5)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        BotaoOkCadastroLayout.setVerticalGroup(
            BotaoOkCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BotaoOkCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CampoescolhaConserto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CampoescolhaConserto.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N

        TxtCPF.setBackground(new java.awt.Color(240, 240, 240));
        TxtCPF.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TxtCPF.setBorder(null);
        TxtCPF.setPreferredSize(new java.awt.Dimension(35, 26));
        TxtData.setDocument(new JTextFieldLimit(10, false, true));
        TxtCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCPFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1144, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BotaoOkCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(BotaoCancelarCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(146, 146, 146))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNome)
                            .addComponent(TxtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                            .addComponent(jSeparator2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(lblData)
                                .addGap(212, 212, 212))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jSeparator4)
                                    .addComponent(TxtData, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                                .addGap(272, 272, 272))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCPF, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtCPF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblConserto)
                            .addComponent(CampoescolhaConserto, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(111, 111, 111))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(lblData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtData, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCPF)
                    .addComponent(lblConserto))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CampoescolhaConserto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TxtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BotaoCancelarCadastro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoOkCadastro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64))
        );

        javax.swing.GroupLayout PanelCadastroLayout = new javax.swing.GroupLayout(PanelCadastro);
        PanelCadastro.setLayout(PanelCadastroLayout);
        PanelCadastroLayout.setHorizontalGroup(
            PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelCadastroLayout.setVerticalGroup(
            PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PaneMae.add(PanelCadastro, "card3");

        PanelConsulta.setBackground(new java.awt.Color(255, 255, 255));
        PanelConsulta.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N

        jPanel2.setPreferredSize(new java.awt.Dimension(1144, 199));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(25, 25, 112));
        jLabel6.setText("CONSULTAR CLIENTES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(331, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(324, 324, 324))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        lblNome1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome1.setText("Nome");

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        lblCPF1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblCPF1.setText("CPF");

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        TxtCPF1.setBackground(new java.awt.Color(240, 240, 240));
        TxtCPF1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TxtCPF1.setBorder(null);
        TxtCPF1.setPreferredSize(new java.awt.Dimension(35, 26));
        TxtData.setDocument(new JTextFieldLimit(10, false, true));
        TxtCPF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCPF1ActionPerformed(evt);
            }
        });

        TxtNome1.setBackground(new java.awt.Color(240, 240, 240));
        TxtNome1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TxtNome1.setBorder(null);
        TxtNome1.setMaximumSize(new java.awt.Dimension(25, 25));
        TxtNome1.setMinimumSize(new java.awt.Dimension(25, 25));
        TxtNome.setDocument(new JTextFieldLimit(40, true));
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

        BotaoPesquisarConsulta.setBackground(new java.awt.Color(230, 230, 230));
        BotaoPesquisarConsulta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(190, 190, 190)));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel7.setText("Pesquisar");
        jLabel7.setAlignmentX(0.5F);

        javax.swing.GroupLayout BotaoPesquisarConsultaLayout = new javax.swing.GroupLayout(BotaoPesquisarConsulta);
        BotaoPesquisarConsulta.setLayout(BotaoPesquisarConsultaLayout);
        BotaoPesquisarConsultaLayout.setHorizontalGroup(
            BotaoPesquisarConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BotaoPesquisarConsultaLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel7)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        BotaoPesquisarConsultaLayout.setVerticalGroup(
            BotaoPesquisarConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BotaoPesquisarConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(BotaoPesquisarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TxtNome1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                                    .addComponent(jSeparator3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TxtCPF1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCPF1)
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(111, 111, 111))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblNome1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCPF1)
                    .addComponent(lblNome1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtCPF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(BotaoPesquisarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout PanelConsultaLayout = new javax.swing.GroupLayout(PanelConsulta);
        PanelConsulta.setLayout(PanelConsultaLayout);
        PanelConsultaLayout.setHorizontalGroup(
            PanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelConsultaLayout.setVerticalGroup(
            PanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PaneMae.add(PanelConsulta, "card2");

        jPanel4.setBackground(new java.awt.Color(0, 6, 205));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Gerenciar clientes");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
                .addContainerGap(492, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout PanelGerenciamentoLayout = new javax.swing.GroupLayout(PanelGerenciamento);
        PanelGerenciamento.setLayout(PanelGerenciamentoLayout);
        PanelGerenciamentoLayout.setHorizontalGroup(
            PanelGerenciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelGerenciamentoLayout.setVerticalGroup(
            PanelGerenciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGerenciamentoLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(542, Short.MAX_VALUE))
        );

        PaneMae.add(PanelGerenciamento, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(SideBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
   private void BotaoCancelarCadastroMouseClicked(java.awt.event.MouseEvent evt){
   
   }
    
    private void BotaoOkCadastroMouseClicked(java.awt.event.MouseEvent evt){
    
    
    }
    private void BotaoPesquisarConsultaMouseClicked(java.awt.event.MouseEvent evt){
        
          
     
    }
    private void BotaoCadastroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoCadastroMouseClicked
        PanelCadastro.setVisible(true);
        PanelConsulta.setVisible(false);
        PanelGerenciamento.setVisible(false);
        setLblColor(BotaoCadastro);
        ResetColor(BotaoConsulta);
        ResetColor(BotaoGerenciamento);
    }//GEN-LAST:event_BotaoCadastroMouseClicked

    private void BotaoConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoConsultaMouseClicked
        PanelCadastro.setVisible(false);
        PanelConsulta.setVisible(true);
        PanelGerenciamento.setVisible(false);
        setLblColor(BotaoConsulta);
        ResetColor(BotaoCadastro);
        ResetColor(BotaoGerenciamento);
    }//GEN-LAST:event_BotaoConsultaMouseClicked


    private void BotaoGerenciamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoGerenciamentoMouseClicked
        PanelCadastro.setVisible(false);
        PanelConsulta.setVisible(false);
        PanelGerenciamento.setVisible(true);
        ResetColor(BotaoCadastro);
        ResetColor(BotaoConsulta);
        setLblColor(BotaoGerenciamento);
    }//GEN-LAST:event_BotaoGerenciamentoMouseClicked

    private void TxtNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtNomeMouseClicked
        System.out.println("Teste");
    }//GEN-LAST:event_TxtNomeMouseClicked

    private void TxtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNomeKeyPressed
        
    }//GEN-LAST:event_TxtNomeKeyPressed

    private void TxtDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDataActionPerformed

    private void TxtCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCPFActionPerformed

    private void jLabel4AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel4AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4AncestorAdded

    private void TxtNome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtNome1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNome1MouseClicked

    private void TxtNome1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNome1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNome1KeyPressed

    private void TxtCPF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCPF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCPF1ActionPerformed

    private void TxtNome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNome1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNome1ActionPerformed


    public void setLblColor(JLabel lbl) {
        lbl.setBackground(new Color(220, 220, 220));
    }

    public void ResetColor(JLabel lbl) {
        lbl.setBackground(new Color(230,230,230));
    }
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BotaoCadastro;
    private javax.swing.JPanel BotaoCancelarCadastro;
    private javax.swing.JLabel BotaoConsulta;
    private javax.swing.JLabel BotaoGerenciamento;
    private javax.swing.JPanel BotaoOkCadastro;
    private javax.swing.JPanel BotaoPesquisarConsulta;
    private java.awt.Choice CampoescolhaConserto;
    private javax.swing.JPanel PaneMae;
    private javax.swing.JPanel PanelCadastro;
    private javax.swing.JPanel PanelConsulta;
    private javax.swing.JPanel PanelGerenciamento;
    private javax.swing.JPanel SideBoard;
    private javax.swing.JTextField TxtCPF;
    private javax.swing.JTextField TxtCPF1;
    private javax.swing.JTextField TxtData;
    private javax.swing.JTextField TxtNome;
    private javax.swing.JTextField TxtNome1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblCPF1;
    private javax.swing.JLabel lblConserto;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNome1;
    // End of variables declaration//GEN-END:variables

    private void makeTableTransparente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
