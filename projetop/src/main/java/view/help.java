/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import controller.JTextFieldLimit;
import controller.SetupAutoComplete;
import java.awt.BorderLayout;
import java.awt.Color;
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
public class help extends javax.swing.JFrame {

    PreparedStatement stmt = null;
    ResultSet rs = null;


    public help() {
        initComponents();
        
        PaneRevista.setVisible(true);
        PaneFerramentas.setVisible(false);
        setLblColor(BotaoRevista);
        ResetColor(BotaoFerramenta);
        ResetColor(BotaoRegistro);

    }
    
    public void garantia(date dataRealizacao){
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SideBoard = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        BotaoFerramenta = new javax.swing.JLabel();
        BotaoRevista = new javax.swing.JLabel();
        BotaoRegistro = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        PaneMae = new javax.swing.JPanel();
        PaneRevista = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        BotaoCancelarNovaRevista = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        campoTitulo = new javax.swing.JTextField();
        lblData = new javax.swing.JLabel();
        dataRealizacao = new javax.swing.JTextField();
        ComboBoxEspecificacaoNovaRevista = new javax.swing.JComboBox<>();
        lblOrigem1 = new javax.swing.JLabel();
        lblTitulo1 = new javax.swing.JLabel();
        campoTitulo1 = new javax.swing.JTextField();
        PaneFerramentas = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        PaneRegistro = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UEMG Frutal - Revistas");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImages(null);

        SideBoard.setBackground(new java.awt.Color(0, 6, 205));
        SideBoard.setForeground(new java.awt.Color(37, 103, 125));
        SideBoard.setToolTipText("");
        SideBoard.setMaximumSize(new java.awt.Dimension(300, 400));

        BotaoFerramenta.setBackground(new java.awt.Color(0, 6, 205));
        BotaoFerramenta.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        BotaoFerramenta.setForeground(new java.awt.Color(255, 255, 255));
        BotaoFerramenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoFerramenta.setText("Consulta");
        BotaoFerramenta.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoFerramenta.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoFerramenta.setOpaque(true);
        BotaoFerramenta.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoFerramenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoFerramentaMouseClicked(evt);
            }
        });

        BotaoRevista.setBackground(new java.awt.Color(0, 6, 205));
        BotaoRevista.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        BotaoRevista.setForeground(new java.awt.Color(255, 255, 255));
        BotaoRevista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoRevista.setText("Cadastro");
        BotaoRevista.setOpaque(true);
        BotaoRevista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoRevistaMouseClicked(evt);
            }
        });

        BotaoRegistro.setBackground(new java.awt.Color(0, 6, 205));
        BotaoRegistro.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        BotaoRegistro.setForeground(new java.awt.Color(255, 255, 255));
        BotaoRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoRegistro.setText("Gerenciar");
        BotaoRegistro.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoRegistro.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoRegistro.setOpaque(true);
        BotaoRegistro.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoRegistroMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 204));
        jLabel1.setText("Logo");

        javax.swing.GroupLayout SideBoardLayout = new javax.swing.GroupLayout(SideBoard);
        SideBoard.setLayout(SideBoardLayout);
        SideBoardLayout.setHorizontalGroup(
            SideBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BotaoRevista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BotaoFerramenta, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
            .addComponent(BotaoRegistro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(74, 74, 74)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BotaoRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(BotaoFerramenta, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BotaoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PaneMae.setBackground(new java.awt.Color(255, 255, 255));
        PaneMae.setLayout(new java.awt.CardLayout());

        PaneRevista.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setDoubleBuffered(false);
        jPanel3.setRequestFocusEnabled(false);
        jPanel3.setVerifyInputWhenFocusTarget(false);

        jPanel1.setBackground(new java.awt.Color(0, 6, 205));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cadastrar cliente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                .addContainerGap(470, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        BotaoCancelarNovaRevista.setBackground(new java.awt.Color(255, 255, 255));
        BotaoCancelarNovaRevista.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoCancelarNovaRevista.setText("Cancelar");
        BotaoCancelarNovaRevista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoCancelarNovaRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCancelarNovaRevistaActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setText("Nome");

        campoTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoTitulo.setMaximumSize(new java.awt.Dimension(25, 25));
        campoTitulo.setMinimumSize(new java.awt.Dimension(25, 25));
        campoTitulo.setDocument(new JTextFieldLimit(40, true));
        campoTitulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campoTituloMouseClicked(evt);
            }
        });
        campoTitulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoTituloKeyPressed(evt);
            }
        });

        lblData.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblData.setText("Data de realização");

        dataRealizacao.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        dataRealizacao.setPreferredSize(new java.awt.Dimension(35, 26));
        dataRealizacao.setDocument(new JTextFieldLimit(10, false, true));
        dataRealizacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataRealizacaoActionPerformed(evt);
            }
        });

        ComboBoxEspecificacaoNovaRevista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxEspecificacaoNovaRevista.setToolTipText("");
        ComboBoxEspecificacaoNovaRevista.setBorder(null);
        ComboBoxEspecificacaoNovaRevista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ComboBoxEspecificacaoNovaRevista.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        ComboBoxEspecificacaoNovaRevista.setDoubleBuffered(true);
        ComboBoxEspecificacaoNovaRevista.setEditor(null);
        ComboBoxEspecificacaoNovaRevista.setFocusable(false);
        ComboBoxEspecificacaoNovaRevista.setMaximumSize(new java.awt.Dimension(35, 26));
        ComboBoxEspecificacaoNovaRevista.setName("Selecione"); // NOI18N
        ComboBoxEspecificacaoNovaRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxEspecificacaoNovaRevistaActionPerformed(evt);
            }
        });

        lblOrigem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem1.setText("Manutenção");

        lblTitulo1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo1.setText("CPF");

        campoTitulo1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoTitulo1.setMaximumSize(new java.awt.Dimension(25, 25));
        campoTitulo1.setMinimumSize(new java.awt.Dimension(25, 25));
        campoTitulo.setDocument(new JTextFieldLimit(40, true));
        campoTitulo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campoTitulo1MouseClicked(evt);
            }
        });
        campoTitulo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoTitulo1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblTitulo)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BotaoCancelarNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(268, 268, 268))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo1)
                            .addComponent(campoTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOrigem1)
                            .addComponent(ComboBoxEspecificacaoNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(148, 148, 148)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblData)
                            .addComponent(dataRealizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(89, 89, 89))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataRealizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblTitulo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblOrigem1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboBoxEspecificacaoNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 257, Short.MAX_VALUE)
                .addComponent(BotaoCancelarNovaRevista)
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout PaneRevistaLayout = new javax.swing.GroupLayout(PaneRevista);
        PaneRevista.setLayout(PaneRevistaLayout);
        PaneRevistaLayout.setHorizontalGroup(
            PaneRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PaneRevistaLayout.setVerticalGroup(
            PaneRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PaneMae.add(PaneRevista, "card3");

        PaneFerramentas.setBackground(new java.awt.Color(255, 255, 255));
        PaneFerramentas.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 6, 205));
        jPanel2.setPreferredSize(new java.awt.Dimension(627, 134));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Consultar clientes");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(422, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1104, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(498, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PaneFerramentasLayout = new javax.swing.GroupLayout(PaneFerramentas);
        PaneFerramentas.setLayout(PaneFerramentasLayout);
        PaneFerramentasLayout.setHorizontalGroup(
            PaneFerramentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PaneFerramentasLayout.setVerticalGroup(
            PaneFerramentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PaneMae.add(PaneFerramentas, "card2");

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
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                .addContainerGap(470, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout PaneRegistroLayout = new javax.swing.GroupLayout(PaneRegistro);
        PaneRegistro.setLayout(PaneRegistroLayout);
        PaneRegistroLayout.setHorizontalGroup(
            PaneRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PaneRegistroLayout.setVerticalGroup(
            PaneRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneRegistroLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(498, Short.MAX_VALUE))
        );

        PaneMae.add(PaneRegistro, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(SideBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
//-------------------------Inicio das ações de revista ------------------------------------//
    private void BotaoRevistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoRevistaMouseClicked
        PaneRevista.setVisible(true);
        PaneFerramentas.setVisible(false);
        PaneRegistro.setVisible(false);
        setLblColor(BotaoRevista);
        ResetColor(BotaoFerramenta);
        ResetColor(BotaoRegistro);
    }//GEN-LAST:event_BotaoRevistaMouseClicked

    private void BotaoFerramentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoFerramentaMouseClicked
        PaneRevista.setVisible(false);
        PaneFerramentas.setVisible(true);
        PaneRegistro.setVisible(false);
        setLblColor(BotaoFerramenta);
        ResetColor(BotaoRevista);
        ResetColor(BotaoRegistro);
    }//GEN-LAST:event_BotaoFerramentaMouseClicked


    private void BotaoRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoRegistroMouseClicked
        PaneRevista.setVisible(false);
        PaneFerramentas.setVisible(false);
        PaneRegistro.setVisible(true);
        ResetColor(BotaoRevista);
        ResetColor(BotaoFerramenta);
        setLblColor(BotaoRegistro);
    }//GEN-LAST:event_BotaoRegistroMouseClicked

    private void BotaoCancelarNovaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelarNovaRevistaActionPerformed

    }//GEN-LAST:event_BotaoCancelarNovaRevistaActionPerformed

    private void campoTituloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campoTituloMouseClicked
        System.out.println("Teste");
    }//GEN-LAST:event_campoTituloMouseClicked

    private void campoTituloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoTituloKeyPressed
        
    }//GEN-LAST:event_campoTituloKeyPressed

    private void dataRealizacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataRealizacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataRealizacaoActionPerformed

    private void ComboBoxEspecificacaoNovaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxEspecificacaoNovaRevistaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxEspecificacaoNovaRevistaActionPerformed

    private void campoTitulo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campoTitulo1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTitulo1MouseClicked

    private void campoTitulo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoTitulo1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTitulo1KeyPressed


    public void setLblColor(JLabel lbl) {
        lbl.setBackground(new Color(200, 200, 200));
    }

    public void ResetColor(JLabel lbl) {
        lbl.setBackground(new Color(0, 6, 205));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoCancelarNovaRevista;
    private javax.swing.JLabel BotaoFerramenta;
    private javax.swing.JLabel BotaoRegistro;
    private javax.swing.JLabel BotaoRevista;
    private javax.swing.JComboBox<String> ComboBoxEspecificacaoNovaRevista;
    private javax.swing.JPanel PaneFerramentas;
    private javax.swing.JPanel PaneMae;
    private javax.swing.JPanel PaneRegistro;
    private javax.swing.JPanel PaneRevista;
    private javax.swing.JPanel SideBoard;
    private javax.swing.JTextField campoTitulo;
    private javax.swing.JTextField campoTitulo1;
    private javax.swing.JTextField dataRealizacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblOrigem1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo1;
    // End of variables declaration//GEN-END:variables
}
