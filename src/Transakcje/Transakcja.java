/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transakcje;

import Klienci.MenuKlienta;
import Towary.MenuTowarow;
import jarex.DaneSklepu;
import jarex.Jarex;
import jarex.MyJPanel;
import java.sql.Connection;
import static java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Łukasz Królik
 */
public class Transakcja extends MyJPanel {

    Connection conn = null;
    private int idKarty;
    private Integer IdTransakcji = null;

    /**
     * Creates new form Transakcja
     */
    public Transakcja() {

        initComponents();

        this.conn = polacz(conn);

        tworzNowaTransakcje();
        try {
            
            
                conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                

            
            

            ResultSet rs = conn.createStatement().executeQuery("Select max(id) from transakcje");
            if (rs.next()) {
                this.IdTransakcji = rs.getInt(1);
                DaneSklepu.getTransakcje().put(this.IdTransakcji, conn);
                DaneSklepu.getStrony().get("PanelTransakcji").setCurrentID(this.IdTransakcji);
                DaneSklepu.getTransakcje().get(DaneSklepu.getStrony().get("PanelTransakcji").getCurrentID()).setAutoCommit(false);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Transakcja.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void tworzNowaTransakcje() {
        try {
            
            //DaneSklepu.getStrony().get("GetTowar").setTransakcja(true);
            Statement stmt = null;

            stmt = conn.createStatement();

            stmt.executeUpdate("Insert into transakcje(id) values (idtrans.NEXTVAL)");

        } catch (SQLException ex) {
            Logger.getLogger(Transakcja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void wyczyscTabele() {
        DefaultTableModel dm = (DefaultTableModel) TransakcjaTable.getModel();
        int rowCount = dm.getRowCount();
//Remove rows one by one from the end of the table
        for (int i1 = rowCount - 1; i1 >= 0; i1--) {
            dm.removeRow(i1);
        }
    }

    @Override
    public void wypelnijTabele() {

        try {
            DefaultTableModel model = (DefaultTableModel) TransakcjaTable.getModel();
            Statement stmt;
            stmt = conn.createStatement();

            ResultSet rs;
            rs = stmt.executeQuery("select nr_kolejny, t.nazwa, TO_CHAR(cena,'99999.99'), ilosc from towary_w_trans x join towary t on "
                    + "t.kod = x.kod_towaru where id_trans =  " + IdTransakcji + " order by nr_kolejny");

            while (rs.next()) {
                model.addRow(new Object[]{String.valueOf(rs.getInt(1)), rs.getString(2), String.valueOf(rs.getDouble(3)), String.valueOf(rs.getInt(4)), Double.valueOf(rs.getString(3)) * rs.getInt(4)});
            }

            stmt = conn.createStatement();

            rs = stmt.executeQuery("select TO_CHAR(nvl(sum(cena*ilosc),''),'99999.99') from towary_w_trans where id_trans = " + this.getIdTransakcji());
            if (rs.next()) {
                LacznieButton.setText(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MenuTowarow.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    private Connection polacz(Connection con) {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "inf117242");
        connectionProps.put("password", "dupadupa");
        try {
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//admlab2-main.cs.put.poznan.pl:1521/dblab01.cs.put.poznan.pl", connectionProps);
            System.out.println("Połączono z bazą danych");

        } catch (SQLException ex) {
            Logger.getLogger(Jarex.class.getName()).log(Level.SEVERE,
                    "nie udało się połączyć z bazą danych", ex);
            System.exit(-1);
        }
        return con;
    }

    public Transakcja(int id, int IDTrans) {
        initComponents();
        this.idKarty = id;
        this.IdTransakcji = IDTrans;

        this.conn = polacz(conn);
        tworzNowaTransakcje();
         
        try {
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            ResultSet rs = conn.createStatement().executeQuery("Select max(id) from transakcje");
            if (rs.next()) {
                this.IdTransakcji = rs.getInt(1);
                DaneSklepu.getTransakcje().put(this.IdTransakcji, conn);
                DaneSklepu.getStrony().get("PanelTransakcji").setCurrentID(this.IdTransakcji);
                DaneSklepu.getTransakcje().get(DaneSklepu.getStrony().get("PanelTransakcji").getCurrentID()).setAutoCommit(false);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Transakcja.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        NewTowarButton = new javax.swing.JButton();
        ZakonczTransakcjeButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TransakcjaTable = new javax.swing.JTable();
        AnulujButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();
        EditButton = new javax.swing.JButton();
        LacznieButton = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        setBackground(new java.awt.Color(4, 56, 145));

        NewTowarButton.setText("Nowy Towar");
        NewTowarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewTowarButtonActionPerformed(evt);
            }
        });

        ZakonczTransakcjeButton.setText("Zakończ transakcję");
        ZakonczTransakcjeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZakonczTransakcjeButtonActionPerformed(evt);
            }
        });

        TransakcjaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nr_kolejny", "Nazwa", "Cena", "Ilość", "Łącznie"
            }
        ));
        TransakcjaTable.setRowHeight(25);
        TransakcjaTable.setShowVerticalLines(false);
        jScrollPane1.setViewportView(TransakcjaTable);
        if (TransakcjaTable.getColumnModel().getColumnCount() > 0) {
            TransakcjaTable.getColumnModel().getColumn(0).setMinWidth(0);
            TransakcjaTable.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        AnulujButton.setText("Anuluj transakcję");
        AnulujButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnulujButtonActionPerformed(evt);
            }
        });

        DeleteButton.setText("Usuń towar");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        EditButton.setText("Edytuj pozycję");
        EditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditButtonActionPerformed(evt);
            }
        });

        LacznieButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        LacznieButton.setForeground(new java.awt.Color(255, 255, 255));
        LacznieButton.setText("0,00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AnulujButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DeleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EditButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(ZakonczTransakcjeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(NewTowarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(27, 27, 27)
                        .addComponent(LacznieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NewTowarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LacznieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ZakonczTransakcjeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AnulujButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DeleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EditButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void NewTowarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewTowarButtonActionPerformed
        if (getIdTransakcji() == null) {

        }
        try {
            Statement stmt = null;
            stmt = conn.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery("select max(nr_kolejny) from towary_w_trans where id_trans = " + getIdTransakcji() + "");
            if (rs.next()) {
                System.out.println("NR KOL: " + rs.getInt(1) + "ID TRANS: " + String.valueOf(DaneSklepu.getStrony().get("PanelTransakcji").getCurrentID()));
                DaneSklepu.getStrony().get("PanelTransakcji").setNrKolejny(rs.getInt(1) + 1);
            }
            DaneSklepu.getStrony().get("GetTowar").setTransakcja(true);
            DaneSklepu.getStrony().get("PanelTransakcji").setCurrentID(getIdTransakcji());

            Jarex.przejdz("GetTowar");

        } catch (SQLException ex) {
            Logger.getLogger(Transakcja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_NewTowarButtonActionPerformed

    private void UsunTransakcjeZTowarami(Integer idtrans) {
        try {
            this.conn.rollback();

        } catch (SQLException ex) {
            Logger.getLogger(Transakcja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void AnulujButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnulujButtonActionPerformed
        JTabbedPane panelTransakcji = (JTabbedPane) SwingUtilities.getAncestorOfClass(JTabbedPane.class, this);

        //panelTransakcji.remove(this.getIdKarty() - 1);
        //PanelTransakcji.getZestawLiczb().remove(Integer.valueOf(this.getIdKarty()));
        UsunTransakcjeZTowarami(DaneSklepu.getStrony().get("PanelTransakcji").getCurrentID());

        DaneSklepu.getStrony().get("PanelTransakcji").setInterrupt(idKarty);

        if (panelTransakcji.getComponentCount() == 1) {

            int index = panelTransakcji.getSelectedIndex();
            panelTransakcji.remove(index);

            //conn.commit();
            Integer ID = null;

            try {

                Statement stmt = null;
                stmt = conn.createStatement();

                stmt.executeUpdate("Insert into transakcje(id) values (idtrans.NEXTVAL)");

                ResultSet rs = stmt.executeQuery("select max(id) from transakcje");

                if (rs.next()) {
                    ID = rs.getInt(1);

                }
            } catch (SQLException ex) {
                Logger.getLogger(Transakcja.class.getName()).log(Level.SEVERE, null, ex);
            }

            panelTransakcji.addTab("Transakcja: " + (index + 1), new Transakcja(index, ID));

        } else {
            int index = panelTransakcji.getSelectedIndex();
            panelTransakcji.remove(index);
            panelTransakcji.setSelectedIndex(0);
        }


    }//GEN-LAST:event_AnulujButtonActionPerformed

    private void ZakonczTransakcjeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZakonczTransakcjeButtonActionPerformed
        Jarex.przejdz("PlanszaPoTransakcji");// TODO add your handling code here:
    }//GEN-LAST:event_ZakonczTransakcjeButtonActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        int i = new Integer((String) TransakcjaTable.getValueAt(TransakcjaTable.getSelectedRow(), 0));
        System.out.println(i);
        if (i > 0) {
            try {
                PreparedStatement stmt = null;
                stmt = conn.prepareStatement("Delete from towary_w_trans where nr_kolejny = ? and id_trans = ?");
                stmt.setInt(1, i);
                stmt.setInt(2, DaneSklepu.getStrony().get("PanelTransakcji").getCurrentID());
                stmt.executeUpdate();
                DaneSklepu.getStrony().get("PanelTransakcji").wyczyscTabele();
                DaneSklepu.getStrony().get("PanelTransakcji").wypelnijTabele();
            } // TODO add your handling code here:
            catch (SQLException ex) {
                Logger.getLogger(Transakcja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void EditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditButtonActionPerformed
        int i = new Integer((String) TransakcjaTable.getValueAt(TransakcjaTable.getSelectedRow(), 0));

        if (i != -1) {

            JTextField cena = new JTextField();
            JTextField ilosc = new JTextField();

            Object[] message = {
                "Cena sprzedaży:", cena,
                "Ilość sztuk:", ilosc};

            String cenaText = (String) TransakcjaTable.getValueAt(TransakcjaTable.getSelectedRow(), 2);
            String iloscText = (String) TransakcjaTable.getValueAt(TransakcjaTable.getSelectedRow(), 3);

            cena.setText(cenaText);
            ilosc.setText(iloscText);

            PreparedStatement stmt = null;
            UIManager.put("OptionPane.cancelButtonText", "Anuluj");
            int option = JOptionPane.showConfirmDialog(null, message, "Edytuj Pozycję", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                try {
                    stmt = conn.prepareStatement("Update towary_w_trans set cena = ?, ilosc = ? where nr_kolejny = " + i + " and id_trans = ?");
                    stmt.setDouble(1, Double.valueOf(cena.getText()));
                    stmt.setDouble(2, Double.valueOf(ilosc.getText()));
                    stmt.setInt(3, DaneSklepu.getStrony().get("PanelTransakcji").getCurrentID());

                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(MenuKlienta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            wyczyscTabele();
            wypelnijTabele();

        }// TODO add your handling code here:
    }//GEN-LAST:event_EditButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AnulujButton;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton EditButton;
    private javax.swing.JLabel LacznieButton;
    private javax.swing.JButton NewTowarButton;
    private javax.swing.JTable TransakcjaTable;
    private javax.swing.JButton ZakonczTransakcjeButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the idKarty
     */
    public int getIdKarty() {
        return idKarty;
    }

    /**
     * @param idKarty the idKarty to set
     */
    public void setIdKarty(int idKarty) {
        this.idKarty = idKarty;
    }

    /**
     * @return the IdTransakcji
     */
    public Integer getIdTransakcji() {
        return IdTransakcji;
    }

    /**
     * @param IdTransakcji the IdTransakcji to set
     */
    public void setIdTransakcji(Integer IdTransakcji) {
        this.IdTransakcji = IdTransakcji;
    }
}
