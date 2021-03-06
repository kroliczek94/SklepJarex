/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transakcje;

import Towary.MenuTowarow;
import jarex.DaneSklepu;
import jarex.MyJPanel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Łukasz Królik
 */
public class MenuTransakcji extends MyJPanel {

    /**
     * Creates new form MenuTransakcji
     */
    public MenuTransakcji() {
        initComponents();

    }

    @Override
    public void wyczyscTabele() {
        DefaultTableModel dm = (DefaultTableModel) TransakcjeTable.getModel();
        int rowCount = dm.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
    }

    @Override
    public void wypelnijTabele() {

        try {
            DefaultTableModel model = (DefaultTableModel) TransakcjeTable.getModel();
            Statement stmt;
            stmt = DaneSklepu.getConn().createStatement();

            ResultSet rs;
            rs = stmt.executeQuery("select d.id, TO_CHAR(data, 'DD-MM-YYYY HH24:MI'), k.imie ||' '|| k.nazwisko, TO_CHAR(sum(ilosc*cena),'99999.99'), sum(d.dozaplaty) \n"
                    + "                    from transakcje d left join klienci k on d.id_klienta = k.id \n"
                    + "                    join towary_w_trans x on d.id = x.id_trans group by k.imie, k.nazwisko, data, d.id order by DATA DESC");

            while (rs.next()) {
                String kwota = "";
                if (rs.getDouble(5) != 0.0) {
                    kwota = " (" + String.valueOf(rs.getDouble(5) + ")");
                }
                model.addRow(new Object[]{rs.getString(1), String.valueOf(rs.getString(2)), rs.getString(3) + kwota, rs.getString(4)});
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuTowarow.class.getName()).log(Level.SEVERE, null, ex);

        }

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
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TransakcjeTable = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();

        jButton2.setText("Wyświetl transakcje");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Raporty...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        TransakcjeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Data", "Dłużnik (kwota do zapłacenia)", "Łączna wartość"
            }
        ));
        TransakcjeTable.setRowHeight(25);
        jScrollPane1.setViewportView(TransakcjeTable);
        if (TransakcjeTable.getColumnModel().getColumnCount() > 0) {
            TransakcjeTable.getColumnModel().getColumn(0).setMinWidth(0);
            TransakcjeTable.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jButton5.setText("Okno sprzedaży");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(178, 178, 178)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jarex.Jarex.przejdz("PanelTransakcji");// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (TransakcjeTable.getSelectedRow() != -1) {
            int i = new Integer((String) TransakcjeTable.getValueAt(TransakcjeTable.getSelectedRow(), 0));
            DaneSklepu.getStrony().get("MenuTransakcji").setCurrentID(i);
            jarex.Jarex.przejdz("HistorycznaTransakcja");// TODO add your handling code here:
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JOptionPane.showMessageDialog(null, "Drukowanie raportu dziennego...");
        
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TransakcjeTable;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
