/*
 * To change this license header, choose License Headers  Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Towary;

import jarex.DaneSklepu;
import jarex.MyJPanel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Łukasz Królik
 */
public class AddDostawa extends MyJPanel {

    int i = 0;

    /**
     * Creates new form AddDostawa
     */
    public AddDostawa() {
        initComponents();
    }

    @Override
    public void wyczyscTabele() {
        DefaultTableModel dm = (DefaultTableModel) AddDostawaTable.getModel();
        int rowCount = dm.getRowCount();
//Remove rows one by one from the end of the table
        for (int i1 = rowCount - 1; i1 >= 0; i1--) {
            dm.removeRow(i1);
        }
    }

    @Override
    public void wypelnijTabele() {

        try {
            DefaultTableModel model = (DefaultTableModel) AddDostawaTable.getModel();
            Statement stmt;
            stmt = DaneSklepu.getConn().createStatement();

            ResultSet rs;
            rs = stmt.executeQuery("select nr_kolejny, t.nazwa, TO_CHAR(t.cena_zamow,'99999.99'), ilosc from towary_w_dost x join towary t on t.kod = x.kod_towaru where id_dost = ' " + DaneSklepu.getStrony().get("AddDostawa").getCurrentID() + "'order by nr_kolejny");

            while (rs.next()) {
                model.addRow(new Object[]{String.valueOf(rs.getInt(1)), rs.getString(2), Double.valueOf(rs.getString(3)), String.valueOf(rs.getInt(4)), Double.valueOf(rs.getString(3)) * rs.getInt(4)});
            }

//            rs = stmt.executeQuery("select sum(cena*ilosc) from towary_w_dost where id = " + String.valueOf(DaneSklepu.getStrony().get("AddDostawa").getCurrentID()));
//            
//            model.addRow(new Object[]{});
//            model.addRow(new Object[]{"", "", "RAZEM : ", rs.getInt(1) });
        } catch (SQLException ex) {
            Logger.getLogger(MenuTowarow.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regener1ated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        AddDostawaTable = new javax.swing.JTable();
        WybierzTowarButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        AddDostawaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Nazwa", "Cena", "Ilość", "Łącznie"
            }
        ));
        AddDostawaTable.setRowHeight(25);
        jScrollPane1.setViewportView(AddDostawaTable);
        if (AddDostawaTable.getColumnModel().getColumnCount() > 0) {
            AddDostawaTable.getColumnModel().getColumn(0).setMinWidth(0);
            AddDostawaTable.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        WybierzTowarButton.setText("Dodaj pozycję");
        WybierzTowarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WybierzTowarButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Zakończ i dodaj");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Anuluj dostawę");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("Edytuj pozycję");

        jButton4.setText("Usuń pozycję");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(WybierzTowarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(WybierzTowarButton)
                    .addComponent(jButton1)
                    .addComponent(jButton4)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {WybierzTowarButton, jButton1, jButton2, jButton3, jButton4});

    }// </editor-fold>//GEN-END:initComponents

    private void WybierzTowarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WybierzTowarButtonActionPerformed
        try {
            Statement stmt = null;
            stmt = DaneSklepu.getConn().createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery("select max(nr_kolejny) from towary_w_dost where id_dost = " + String.valueOf(DaneSklepu.getStrony().get("AddDostawa").getCurrentID()) + "");
            if (rs.next()) {
                System.out.println("NR KOL: " + rs.getInt(1));
                DaneSklepu.getStrony().get("AddDostawa").setNrKolejny(rs.getInt(1) + 1);
            }
            
            jarex.Jarex.przejdz("GetTowar");// TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(AddDostawa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_WybierzTowarButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            DaneSklepu.getConn().commit();// TODO add your handling code here:
            DaneSklepu.getConn().setAutoCommit(true);
            jarex.Jarex.przejdz("MenuDostaw");
        } catch (SQLException ex) {
            Logger.getLogger(AddDostawa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            DaneSklepu.getConn().rollback();// TODO add your handling code here:
            DaneSklepu.getConn().setAutoCommit(true);
            jarex.Jarex.przejdz("MenuDostaw");
        } catch (SQLException ex) {
            Logger.getLogger(AddDostawa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AddDostawaTable;
    private javax.swing.JButton WybierzTowarButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
