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
import javax.swing.text.html.HTMLDocument;

/**
 *
 * @author Łukasz Królik
 */
public class PlanszaPoTransakcji extends MyJPanel {

    Double doZaplacenia = null;

    /**
     * Creates new form PlanszaPoTransakcji
     */
    public PlanszaPoTransakcji() {
        initComponents();
    }

    @Override
    public void wyczyscTabele() {
        DefaultTableModel dm = (DefaultTableModel) TablicaTowarow.getModel();
        int rowCount = dm.getRowCount();
//Remove rows one by one from the end of the table
        for (int i1 = rowCount - 1; i1 >= 0; i1--) {
            dm.removeRow(i1);
        }
    }

    @Override
    public void wypelnijTabele() {

        try {
            DefaultTableModel model = (DefaultTableModel) TablicaTowarow.getModel();
            Statement stmt;
            stmt = DaneSklepu.getConn().createStatement();

            ResultSet rs;
            rs = stmt.executeQuery("select nr_kolejny, t.nazwa, cena, ilosc from towary_w_trans x join towary t on "
                    + "t.kod = x.kod_towaru where id_trans =  " + DaneSklepu.getStrony().get("PanelTransakcji").getCurrentID() + " order by nr_kolejny");

            while (rs.next()) {
                model.addRow(new Object[]{String.valueOf(rs.getInt(1)), rs.getString(2), Double.valueOf(rs.getString(3)), String.valueOf(rs.getInt(4)), Double.valueOf(rs.getString(3)) * rs.getInt(4)});
            }

            Statement stmt1 = null;
            stmt1 = DaneSklepu.getConn().createStatement();

            ResultSet rs1 = stmt1.executeQuery("Select sum(ilosc * cena) from towary_w_trans where id_trans = " + DaneSklepu.getStrony().get("PanelTransakcji").getCurrentID());

            Double doZaplaty = null;
            while (rs1.next()) {

                doZaplaty = Double.valueOf(rs1.getString(1));
                this.doZaplacenia = doZaplaty;
            }
            jLabel2.setText("DO ZAPŁACENIA:" + doZaplaty);

            stmt1 = DaneSklepu.getConn().createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(MenuTowarow.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @Override
    public void zmienLabela() {
        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = DaneSklepu.getConn().prepareStatement("Select imie, nazwisko from klienci where id = ?");
            stmt.setInt(1, DaneSklepu.getStrony().get("GetClient").getIdKlienta());

            rs = stmt.executeQuery();

            if (rs.next()) {
                KlientLabel.setText("Wybrany klient: " + rs.getString(1) + " " + rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanszaPoTransakcji.class.getName()).log(Level.SEVERE, null, ex);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        TablicaTowarow = new javax.swing.JTable();
        kwotaField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ResztaLabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        KlientLabel = new javax.swing.JLabel();

        jButton1.setText("Wybierz klienta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        TablicaTowarow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        TablicaTowarow.setRowHeight(25);
        TablicaTowarow.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                TablicaTowarowComponentShown(evt);
            }
        });
        jScrollPane1.setViewportView(TablicaTowarow);

        kwotaField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kwotaFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kwotaFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kwotaFieldKeyTyped(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("jLabel2");

        ResztaLabel.setForeground(new java.awt.Color(255, 255, 255));
        ResztaLabel.setText("WIĘCEJ!");

        jButton2.setText("Zakończ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        KlientLabel.setForeground(new java.awt.Color(255, 255, 255));
        KlientLabel.setText("WYBRANY KLIENT: BRAK");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(KlientLabel)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(146, 146, 146)
                                        .addComponent(ResztaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(kwotaField))
                                .addGap(2, 2, 2))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(kwotaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ResztaLabel)
                    .addComponent(jLabel2)
                    .addComponent(KlientLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(22, 22, 22))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ResztaLabel, jButton2, jLabel2, kwotaField});

    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jarex.Jarex.przejdz("GetClient");// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void kwotaFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kwotaFieldKeyTyped

    }//GEN-LAST:event_kwotaFieldKeyTyped

    private void kwotaFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kwotaFieldKeyPressed

    }//GEN-LAST:event_kwotaFieldKeyPressed

    private void kwotaFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kwotaFieldKeyReleased
        if (!kwotaField.getText().isEmpty()) {

            Double liczba = Double.valueOf(kwotaField.getText());
            Double reszta = liczba - doZaplacenia;
            if (reszta > 0) {
                ResztaLabel.setText("RESZTA: " + reszta);
            } else {
                ResztaLabel.setText("WIĘCEJ!");
            }
        }// TODO add your handling code here: // TODO add your handling code here:
    }//GEN-LAST:event_kwotaFieldKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (ResztaLabel.getText() == "WIĘCEJ!" && KlientLabel.getText() == "WYBRANY KLIENT: BRAK") {
            JOptionPane.showMessageDialog(null, "Podaj wyższą kwotę wpłaty, albo wybierz dłużnika do obciążenia");
        } else {
            if (ResztaLabel.getText() == "WIĘCEJ!") {
                try {
                    Statement stmt = null;
                    stmt = DaneSklepu.getConn().createStatement();
                    Double kasa = 0.0;
                    if (!kwotaField.getText().isEmpty()) {
                        kasa = Double.valueOf(kwotaField.getText());
                    }
                    Double wynik = doZaplacenia - kasa;
                    stmt.executeUpdate("Update transakcje set dozaplaty = " + wynik + " ,id_klienta = " + DaneSklepu.getStrony().get("GetClient").getIdKlienta() + " where id = " + DaneSklepu.getStrony().get("PanelTransakcji").getCurrentID());

// TODO add your handling code here:
                } catch (SQLException ex) {
                    Logger.getLogger(PlanszaPoTransakcji.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            KlientLabel.setText("WYBRANY KLIENT: BRAK");
            DaneSklepu.getStrony().get("GetClient").setIdKlienta(null);
            kwotaField.setText(null);
            DaneSklepu.getStrony().get("PanelTransakcji").setPoTransakcji(true);
            jarex.Jarex.przejdz("PanelTransakcji");
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void TablicaTowarowComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_TablicaTowarowComponentShown

    }//GEN-LAST:event_TablicaTowarowComponentShown


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel KlientLabel;
    private javax.swing.JLabel ResztaLabel;
    private javax.swing.JTable TablicaTowarow;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kwotaField;
    // End of variables declaration//GEN-END:variables
}
