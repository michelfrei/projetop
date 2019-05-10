/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SideBoard = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        BotaoFerramenta = new javax.swing.JLabel();
        BotaoRevista = new javax.swing.JLabel();
        BotaoRegistro = new javax.swing.JLabel();
        PaneMae = new javax.swing.JPanel();
        PaneRevista = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
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

        SideBoard.setBackground(new java.awt.Color(37, 103, 125));
        SideBoard.setForeground(new java.awt.Color(37, 103, 125));
        SideBoard.setMaximumSize(new java.awt.Dimension(300, 400));

        BotaoFerramenta.setBackground(new java.awt.Color(37, 103, 125));
        BotaoFerramenta.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        BotaoFerramenta.setForeground(new java.awt.Color(255, 255, 255));
        BotaoFerramenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoFerramenta.setText("USE");
        BotaoFerramenta.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoFerramenta.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoFerramenta.setOpaque(true);
        BotaoFerramenta.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoFerramenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoFerramentaMouseClicked(evt);
            }
        });

        BotaoRevista.setBackground(new java.awt.Color(37, 103, 125));
        BotaoRevista.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        BotaoRevista.setForeground(new java.awt.Color(255, 255, 255));
        BotaoRevista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoRevista.setText("USE");
        BotaoRevista.setOpaque(true);
        BotaoRevista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoRevistaMouseClicked(evt);
            }
        });

        BotaoRegistro.setBackground(new java.awt.Color(37, 103, 125));
        BotaoRegistro.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        BotaoRegistro.setForeground(new java.awt.Color(255, 255, 255));
        BotaoRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoRegistro.setText("USE");
        BotaoRegistro.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoRegistro.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoRegistro.setOpaque(true);
        BotaoRegistro.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoRegistroMouseClicked(evt);
            }
        });

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
        );
        SideBoardLayout.setVerticalGroup(
            SideBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBoardLayout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoFerramenta, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        jPanel1.setBackground(new java.awt.Color(44, 106, 129));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1104, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(661, Short.MAX_VALUE))
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

        jPanel2.setBackground(new java.awt.Color(44, 106, 129));
        jPanel2.setPreferredSize(new java.awt.Dimension(627, 134));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ferramentas");

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
                .addContainerGap(661, Short.MAX_VALUE))
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

        jPanel4.setBackground(new java.awt.Color(44, 106, 129));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Registro");

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
                .addContainerGap(661, Short.MAX_VALUE))
        );

        PaneMae.add(PaneRegistro, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(SideBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PaneMae, javax.swing.GroupLayout.PREFERRED_SIZE, 1104, javax.swing.GroupLayout.PREFERRED_SIZE))
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
//-------------------------fim das ações de revista ------------------------------------//

    public void setLblColor(JLabel lbl) {
        lbl.setBackground(new Color(150, 150, 150));
    }

    public void ResetColor(JLabel lbl) {
        lbl.setBackground(new Color(37, 103, 125));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BotaoFerramenta;
    private javax.swing.JLabel BotaoRegistro;
    private javax.swing.JLabel BotaoRevista;
    private javax.swing.JPanel PaneFerramentas;
    private javax.swing.JPanel PaneMae;
    private javax.swing.JPanel PaneRegistro;
    private javax.swing.JPanel PaneRevista;
    private javax.swing.JPanel SideBoard;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
