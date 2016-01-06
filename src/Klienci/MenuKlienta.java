/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klienci;

import Towary.MenuTowarow;
import jarex.DaneSklepu;
import jarex.MyJPanel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Łukasz Królik
 */
public class MenuKlienta extends MyJPanel {

    /**
     * Creates new form MenuKlienta
     */
    public MenuKlienta() {
        initComponents();
        //wypelnijTabele();

    }

    @Override
    public void wyczyscTabele() {
        DefaultTableModel dm = (DefaultTableModel) TablicaKlient.getModel();
        int rowCount = dm.getRowCount();
//Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
    }

    @Override
    public void wypelnijTabele() {

        try {
            DefaultTableModel model = (DefaultTableModel) TablicaKlient.getModel();
            Statement stmt;
            stmt = DaneSklepu.getConn().createStatement();

            ResultSet rs;
            rs = stmt.executeQuery("select id, imie, nazwisko, stan_konta, stan_naklejek from klienci order by id");
            
            while (rs.next()) {
                model.addRow(new Object[]{String.valueOf(rs.getInt(1)), rs.getString(2), rs.getString(3), String.valueOf(rs.getInt(4)),
                    String.valueOf(rs.getInt(5))});
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

        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablicaKlient = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jButton2.setText("Przyjmij zwrot długu");

        TablicaKlient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Imię", "Nazwisko", "Stan Konta"
            }
        ));
        TablicaKlient.setRowHeight(25);
        jScrollPane1.setViewportView(TablicaKlient);
        if (TablicaKlient.getColumnModel().getColumnCount() > 0) {
            TablicaKlient.getColumnModel().getColumn(0).setMinWidth(0);
            TablicaKlient.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jButton3.setText("Dodaj klienta");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Edytuj klienta");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setText("Usuń klienta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JTextField imie = new JTextField();
        JTextField nazwisko = new JTextField();
        Object[] message = {
            "Imię:", imie,
            "Nazwisko:", nazwisko
        };
        Statement stmt = null;
        UIManager.put("OptionPane.cancelButtonText", "Anuluj");
        int option = JOptionPane.showConfirmDialog(null, message, "Dodaj klienta", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                stmt = DaneSklepu.getConn().createStatement();
                String sql = "INSERT INTO KLIENCI(ID, IMIE, NAZWISKO) VALUES(idKlienta.NEXTVAL, '" + imie.getText() + "' , '" + nazwisko.getText() + "')";
                stmt.executeUpdate(sql);
            } catch (SQLException ex) {
                Logger.getLogger(MenuKlienta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        wyczyscTabele();
        wypelnijTabele();

// TODO add your handling code here:
// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (TablicaKlient.getSelectedRow() != -1) {
            JTextField imie = new JTextField();
            JTextField nazwisko = new JTextField();
            Object[] message = {
                "Imię:", imie,
                "Nazwisko:", nazwisko
            };

            String id = (String) TablicaKlient.getValueAt(TablicaKlient.getSelectedRow(), 0);
            imie.setText((String) TablicaKlient.getValueAt(TablicaKlient.getSelectedRow(), 1));
            nazwisko.setText((String) TablicaKlient.getValueAt(TablicaKlient.getSelectedRow(), 2));
            UIManager.put("OptionPane.cancelButtonText", "Anuluj");
            int option = JOptionPane.showConfirmDialog(null, message, "Edytuj klienta", JOptionPane.OK_CANCEL_OPTION);

            Statement stmt = null;

            if (option == JOptionPane.OK_OPTION) {
                try {
                    stmt = DaneSklepu.getConn().createStatement();
                    String sql = "UPDATE KLIENCI SET IMIE = '" + imie.getText() + "' , NAZWISKO = '" + nazwisko.getText() + "' where id = " + id;
                    stmt.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuKlienta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            wyczyscTabele();
            wypelnijTabele();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String imie = (String) TablicaKlient.getValueAt(TablicaKlient.getSelectedRow(), 1);
        String nazwisko = ((String) TablicaKlient.getValueAt(TablicaKlient.getSelectedRow(), 2));
        String id = (String) TablicaKlient.getValueAt(TablicaKlient.getSelectedRow(), 0);
        
        int option = JOptionPane.showConfirmDialog(null, "Czy usunąć klienta : " + imie + ' ' + nazwisko);

        Statement stmt = null;
        if (option == JOptionPane.OK_OPTION) {
            try {
                stmt = DaneSklepu.getConn().createStatement();
                String sql = "DELETE FROM KLIENCI WHERE ID = " + id;
                stmt.executeUpdate(sql);
            } catch (SQLException ex) {
                Logger.getLogger(MenuKlienta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        wyczyscTabele();
        wypelnijTabele();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablicaKlient;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
