/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Towary;

import jarex.DaneSklepu;
import jarex.Jarex;
import jarex.MyJPanel;
import java.sql.PreparedStatement;
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
public class GetTowar extends MyJPanel {

    /**
     * Creates new form GetTowarTrans
     */
    public GetTowar() {
        initComponents();
        //DaneSklepu.getStrony().get("PanelTransakcji").wypelnijTabele();
        //super();

    }

    @Override
    public void wyczyscTabele() {
        DefaultTableModel dm = (DefaultTableModel) GetTowarTable.getModel();
        int rowCount = dm.getRowCount();
//Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
    }

    @Override
    public void wypelnijTabele() {
        boolean transakcja = DaneSklepu.getStrony().get("GetTowar").isTransakcja();
        try {
            DefaultTableModel model = (DefaultTableModel) GetTowarTable.getModel();
            Statement stmt;
            stmt = DaneSklepu.getConn().createStatement();

            ResultSet rs;
            if (transakcja) {
                rs = stmt.executeQuery("select kod, nazwa, cena_zakup from towary where kod > 0 and kod like '%" + kodField.getText() + "%' and nazwa like '%" + NazwaField.getText() + "%' order by kod");
                while (rs.next()) {
                    model.addRow(new Object[]{String.valueOf(rs.getInt(1)), rs.getString(2), String.valueOf(rs.getDouble(3))});
                }
            } else {
                model.setColumnIdentifiers(new Object[]{"Kod", "Nazwa", "Cena w dostawie"});
                rs = stmt.executeQuery("select kod, nazwa, cena_zamow from towary where kod > 0 order by kod");
                while (rs.next()) {
                    model.addRow(new Object[]{String.valueOf(rs.getInt(1)), rs.getString(2), String.valueOf(rs.getDouble(3))});
                }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        GetTowarTable = new javax.swing.JTable();
        kodField = new javax.swing.JTextField();
        NazwaField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jScrollPane1.setBackground(new java.awt.Color(93, 141, 223));

        GetTowarTable.setAutoCreateRowSorter(true);
        GetTowarTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Kod", "Nazwa", "Cena"
            }
        ));
        GetTowarTable.setRowHeight(25);
        jScrollPane1.setViewportView(GetTowarTable);

        kodField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kodFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kodFieldKeyReleased(evt);
            }
        });

        NazwaField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NazwaFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NazwaFieldKeyReleased(evt);
            }
        });

        jButton1.setText("Wybierz");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setText("Anuluj");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Kod");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nazwa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kodField, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NazwaField)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kodField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NazwaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Jarex.przejdz((String) DaneSklepu.getStos().pollLast());// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JTextField ilosc = new JTextField();

        Object[] message = {
            "Podaj ilosc wzietego towaru:", ilosc
        };
        UIManager.put("OptionPane.cancelButtonText", "Anuluj");
        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);

        if (GetTowarTable.getSelectedRow() != -1) {
            boolean transakcja = DaneSklepu.getStrony().get("GetTowar").isTransakcja();
            if (!transakcja) {

                Integer i = new Integer((String) GetTowarTable.getValueAt(GetTowarTable.getSelectedRow(), 0));
                try {
                    PreparedStatement stmt = null;
                    stmt = DaneSklepu.getConn().prepareStatement("insert into TOWARY_W_DOST values (? , ?, ? ,?, (select cena_zamow from towary where kod = " + i + "))");
                    stmt.setInt(1, DaneSklepu.getStrony().get("AddDostawa").getNrKolejny());
                    stmt.setInt(2, new Integer(ilosc.getText()));
                    stmt.setInt(3, i);
                    stmt.setInt(4, DaneSklepu.getStrony().get("AddDostawa").getCurrentID());
                    stmt.executeUpdate();

                    PreparedStatement stmt1 = null;

                    stmt1 = DaneSklepu.getConn().prepareStatement("update towary set ilosc_w_magazynie = nvl(ilosc_w_magazynie,0) + ?, do_zamowienia = 'NIE' where kod = ?");
                    stmt1.setInt(1, new Integer(ilosc.getText()));
                    stmt1.setInt(2, i);
                    int x = stmt1.executeUpdate();

                    System.out.println(x);

                } catch (SQLException ex) {
                    Logger.getLogger(GetTowar.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Integer i = new Integer((String) GetTowarTable.getValueAt(GetTowarTable.getSelectedRow(), 0));
                try {
                    PreparedStatement stmt = null;

                    stmt = DaneSklepu.getConn().prepareStatement("insert into towary_w_trans(nr_kolejny, ilosc, rabat, kod_towaru, id_trans, cena) values(?,?,3,?,?,(select cena_zakup from towary where kod = " + i + "))");

                    stmt.setInt(1, DaneSklepu.getStrony().get("PanelTransakcji").getNrKolejny());
                    stmt.setInt(2, new Integer(ilosc.getText()));
                    stmt.setInt(3, i);
                    stmt.setInt(4, DaneSklepu.getStrony().get("PanelTransakcji").getCurrentID());
                    stmt.executeUpdate();

                    PreparedStatement stmt1 = null;

                    Integer wynik = null;
                    Statement stmt2 = null;
                    stmt2 = DaneSklepu.getConn().createStatement();
                    ResultSet rs = stmt2.executeQuery("select ilosc_w_magazynie from towary where kod = " + i);
                    if (rs.next()) {
                        wynik = rs.getInt(1);
                    }

                    stmt1 = DaneSklepu.getConn().prepareStatement("update towary set ilosc_w_magazynie = ?, do_zamowienia = ? where kod = ?");
                    Integer reszta = wynik - Integer.valueOf(ilosc.getText());
                    if (reszta > 0) {
                        stmt1.setInt(1, reszta);
                        stmt1.setString(2, "NIE");
                    } else {
                        stmt1.setInt(1, 0);
                        stmt1.setString(2, "TAK");
                        JOptionPane.showMessageDialog(null, "Stan w magazynie mniejszy od zera!");
                    }

                    stmt1.setInt(3, i);
                    stmt1.executeUpdate();

                } catch (SQLException ex) {
                    Logger.getLogger(GetTowar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        //DaneSklepu.getStrony().get(DaneSklepu.getStos().peekLast()).setCurrentID(i);
        Jarex.przejdz((String) DaneSklepu.getStos().pollLast());// TODO add your handling code here:


    }//GEN-LAST:event_jButton1ActionPerformed

    private void kodFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodFieldKeyPressed

    private void NazwaFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NazwaFieldKeyPressed

// TODO add your handling code here:
    }//GEN-LAST:event_NazwaFieldKeyPressed

    private void kodFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodFieldKeyReleased
        kodField.setText(kodField.getText().toUpperCase());
        wyczyscTabele();
        wypelnijTabele();
// TODO add your handling code here:
    }//GEN-LAST:event_kodFieldKeyReleased

    private void NazwaFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NazwaFieldKeyReleased
        NazwaField.setText(NazwaField.getText().toUpperCase());
        wyczyscTabele();
        wypelnijTabele();
// TODO add your handling code here:
    }//GEN-LAST:event_NazwaFieldKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable GetTowarTable;
    private javax.swing.JTextField NazwaField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kodField;
    // End of variables declaration//GEN-END:variables
}
