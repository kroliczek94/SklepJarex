/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Towary;

import jarex.DaneSklepu;
import jarex.MyJPanel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Łukasz Królik
 */
public class MenuDostaw extends MyJPanel {

    /**
     * Creates new form MenuDostaw
     */
    public MenuDostaw() {
        initComponents();
    }

    public void wyczyscTabele() {
        DefaultTableModel dm = (DefaultTableModel) DostawyTable.getModel();
        int rowCount = dm.getRowCount();
//Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
    }

    @Override
    public void wypelnijTabele() {

        try {
            DefaultTableModel model = (DefaultTableModel) DostawyTable.getModel();
            Statement stmt;
            stmt = DaneSklepu.getConn().createStatement();

            ResultSet rs;
            rs = stmt.executeQuery("select id, dostawca, data from dostawy order by id");

            while (rs.next()) {
                model.addRow(new Object[]{String.valueOf(rs.getInt(1)), rs.getString(2), rs.getDate(3)});
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuTowarow.class.getName()).log(Level.SEVERE, null, ex);

        }

        //model.removeRow(2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        DostawyTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        DostawyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        DostawyTable.setRowHeight(25);
        jScrollPane1.setViewportView(DostawyTable);

        jButton1.setText("Dodaj nową dostawę");
        jButton1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Edytuj dostawę");

        jButton3.setText("Usuń dostawę");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       // jarex.Jarex.przejdz("AddDostawa");// TODO add your handling code here:
        try {
            JComboBox nazwa = new JComboBox();

            Statement stmt = null;
            stmt = DaneSklepu.getConn().createStatement();

            ResultSet rs = null;
            rs = stmt.executeQuery("select distinct dostawca from dostawy");

            while (rs.next()) {
                nazwa.addItem(rs.getString(1));
            }
            nazwa.setEditable(true);
            Object[] message = {
                "Dostawca:", nazwa};

            PreparedStatement stmt1 = null;

            UIManager.put("OptionPane.cancelButtonText", "Anuluj");
            int option = JOptionPane.showConfirmDialog(null, message, "Nazwa Dostawcy", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                stmt1 = DaneSklepu.getConn().prepareStatement("Insert into dostawy(id, dostawca) values (iddost.NEXTVAL, ?)");
                stmt1.setString(1, (String) nazwa.getSelectedItem());
                System.out.println("Coś tam sie udało");
            }

        } catch (SQLException ex) {
            Logger.getLogger(MenuDostaw.class.getName()).log(Level.SEVERE, null, ex);
        }
        wyczyscTabele();
wypelnijTabele();
       // jarex.Jarex.przejdz("AddDostawa");
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DostawyTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
