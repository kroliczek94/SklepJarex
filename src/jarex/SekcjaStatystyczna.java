/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarex;

import Towary.MenuTowarow;
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
public class SekcjaStatystyczna extends MyJPanel {

    /**
     * Creates new form SekcjaStatystyczna
     */
    public SekcjaStatystyczna() {
        initComponents();
    }

    public void wyczyscTabele() {
        DefaultTableModel dm = (DefaultTableModel) StatyTable.getModel();
        int rowCount = dm.getRowCount();
//Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
    }

    @Override
    public void wypelnijTabele() {

        try {
            DefaultTableModel model = (DefaultTableModel) StatyTable.getModel();

            PreparedStatement stmt = null;
            ResultSet rs = null;

            model.addRow(new Object[]{"PRZYCHODY:", ""});
            model.addRow(new Object[]{"", ""});

            stmt = DaneSklepu.getConn().prepareStatement("select sum(cena*ilosc) from transakcje t join "
                    + "towary_w_trans x on t.id = x.id_trans where "
                    + "EXTRACT(day from data)=extract(day from sysdate) and "
                    + "EXTRACT(month from data)=extract(month from sysdate) and "
                    + "EXTRACT(year from data)=extract(year from sysdate) ");

            rs = stmt.executeQuery();
            if (rs.next()) {
                model.addRow(new Object[]{"Dzienne:", rs.getDouble(1)});
            }

            stmt = DaneSklepu.getConn().prepareStatement("Select sum(cena*ilosc) from towary_w_trans t join transakcje x "
                    + "on t.id_trans = x.id where kod_towaru > 1 and data > sysdate - 7");

            rs = stmt.executeQuery();
            if (rs.next()) {
                model.addRow(new Object[]{"Tygodniowo:", rs.getDouble(1)});
            }

            stmt = DaneSklepu.getConn().prepareStatement("Select sum(cena*ilosc) from towary_w_trans t join transakcje x "
                    + "on t.id_trans = x.id where kod_towaru > 1 and data > sysdate - 30");

            rs = stmt.executeQuery();
            if (rs.next()) {
                model.addRow(new Object[]{"Miesięcznie:", rs.getDouble(1)});
            }
            model.addRow(new Object[]{"", ""});

            stmt = DaneSklepu.getConn().prepareStatement("Select count(count(id)) from "
                    + "transakcje t join towary_w_trans x on t.id = x.id_trans "
                    + "join towary p on p.kod = x.kod_towaru where "
                    + "EXTRACT(day from data)=extract(day from sysdate) and "
                    + "EXTRACT(month from data)=extract(month from sysdate) and "
                    + "EXTRACT(year from data)=extract(year from sysdate) group by id");

            rs = stmt.executeQuery();
            if (rs.next()) {
                model.addRow(new Object[]{"Dzisiaj transakcji:", rs.getInt(1)});
            }
            model.addRow(new Object[]{"", ""});

            stmt = DaneSklepu.getConn().prepareStatement("select sum(dozaplaty) from transakcje t join towary_w_trans x on t.id = x.id_trans where EXTRACT(day from data)=extract(day from sysdate) and EXTRACT(month from data)=extract(month from sysdate) and EXTRACT(year from data)=extract(year from sysdate)");

            rs = stmt.executeQuery();
            if (rs.next()) {
                model.addRow(new Object[]{"Dzisiaj długów:", rs.getDouble(1)});
            }

            stmt = DaneSklepu.getConn().prepareStatement("select sum(cena) from transakcje t "
                    + "join towary_w_trans x on t.id = x.id_trans where "
                    + "EXTRACT(day from data)=extract(day from sysdate) and "
                    + "EXTRACT(month from data)=extract(month from sysdate) and "
                    + "EXTRACT(year from data)=extract(year from sysdate) and kod_towaru = 0");

            rs = stmt.executeQuery();
            if (rs.next()) {
                model.addRow(new Object[]{"Dzisiaj zwrotów:", rs.getDouble(1)});
            }
            model.addRow(new Object[]{"", ""});
            stmt = DaneSklepu.getConn().prepareStatement("select sum(cena*ilosc) - sum(cena_zamow*ilosc) "
                    + "from transakcje t join towary_w_trans x on t.id = x.id_trans join towary p on "
                    + "p.kod = x.kod_towaru where EXTRACT(day from data)=extract(day from sysdate) and "
                    + "EXTRACT(month from data)=extract(month from sysdate) and "
                    + "EXTRACT(year from data)=extract(year from sysdate)");

            rs = stmt.executeQuery();
            if (rs.next()) {
                model.addRow(new Object[]{"Dzisiaj zysku:", rs.getDouble(1)});
            }

        } catch (SQLException ex) {
            Logger.getLogger(SekcjaStatystyczna.class.getName()).log(Level.SEVERE, null, ex);
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

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        StatyTable = new javax.swing.JTable();

        jButton1.setText("Przeglądaj zeszłe transakcje");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Raport...");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        StatyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        StatyTable.setRowHeight(25);
        jScrollPane1.setViewportView(StatyTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(5, 5, 5)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Jarex.przejdz("MenuTransakcji");// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JOptionPane.showMessageDialog(null, "Drukowanie raportu dziennego...");// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable StatyTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
