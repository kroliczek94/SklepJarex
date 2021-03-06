/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transakcje;

import Towary.MenuDostaw;
import jarex.DaneSklepu;
import jarex.MyJPanel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oracle.sql.DATE;

/**
 *
 * @author Łukasz Królik
 */
public class PanelTransakcji extends MyJPanel {

    private static ArrayList<Integer> zestawLiczb = new ArrayList<>();
    private int ktoryCheckPoint = 0;

    /**
     * Creates new form PanelTransakcji
     */
    public PanelTransakcji() {

        zestawLiczb.add(1);
        initComponents();

    }

    @Override
    public void wyczyscTabele() {
        if (DaneSklepu.getStrony().get("PanelTransakcji").isPoTransakcji()) {
           // zmienLabela();
            
            //TablicaTransakcji.setSelectedIndex(0);

        }

    }

    public void zmienLabela(){
        DaneSklepu.getStrony().get("PanelTransakcji").setCurrentID(-1);
        int index = TablicaTransakcji.getSelectedIndex();
            TablicaTransakcji.remove(index);
            if (TablicaTransakcji.getComponentCount() == 0){
            TablicaTransakcji.addTab("Transakcja: " + String.valueOf(zestawLiczb.get(zestawLiczb.size() - 1)), new Transakcja());
             TablicaTransakcji.setSelectedIndex(TablicaTransakcji.getComponentCount() - 1);
            }else{
                TablicaTransakcji.setSelectedIndex(TablicaTransakcji.getComponentCount() - 1);
            }
    }
    
    @Override
    public void wypelnijTabele() {
        DaneSklepu.getStrony().get("PanelTransakcji").setPoTransakcji(false);
        
        if (TablicaTransakcji.getComponentCount() == 0) {
            System.out.println("A");
            TablicaTransakcji.addTab("Transakcja: " + String.valueOf(zestawLiczb.get(zestawLiczb.size() - 1)), new Transakcja());
            TablicaTransakcji.setSelectedIndex(TablicaTransakcji.getComponentCount() - 1);
        }else{
            //System.out.println(TablicaTransakcji.getComponentCount());
            //TablicaTransakcji.setSelectedIndex(0);
        }
        
        MyJPanel tab = (MyJPanel) TablicaTransakcji.getSelectedComponent();
        System.out.println("2");
        tab.wyczyscTabele();
        tab.wypelnijTabele();
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
        TablicaTransakcji = new javax.swing.JTabbedPane();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jButton1.setText("Nowa transakcja");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        TablicaTransakcji.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        TablicaTransakcji.setFocusTraversalPolicyProvider(true);
        TablicaTransakcji.setRequestFocusEnabled(false);
        TablicaTransakcji.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TablicaTransakcjiStateChanged(evt);
            }
        });
        TablicaTransakcji.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                TablicaTransakcjiComponentShown(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                    .addComponent(TablicaTransakcji))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TablicaTransakcji, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private int DodajTransakcje() {
        Integer ID = null;

        try {
            //DaneSklepu.getStrony().get("GetTowar").setTransakcja(true);
            Statement stmt = null;
            stmt = DaneSklepu.getConn().createStatement();

            stmt.executeUpdate("Insert into transakcje(id) values (idtrans.NEXTVAL)");

            ResultSet rs = stmt.executeQuery("select max(id) from transakcje");

            if (rs.next()) {
                ID = rs.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Transakcja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ID;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int index = DaneSklepu.getStrony().get("PanelTransakcji").getInterrupt() - 1;
        DaneSklepu.getStrony().get("PanelTransakcji").setInterrupt(-1);
        if (index > 0) {
            zestawLiczb.remove(index);
        }
        Integer ID = DodajTransakcje();
        if (zestawLiczb.size() < 10) {
            getZestawLiczb().add(znajdzNajmniejszaMozliwaLiczbe());
            for (Integer i : zestawLiczb) {
                System.out.println("Dodane przed:" + i);
            }

            TablicaTransakcji.addTab("Transakcja: " + String.valueOf(getZestawLiczb().get(getZestawLiczb().size() - 1)), new Transakcja(TablicaTransakcji.getComponentCount() + 1, ID));
            TablicaTransakcji.setSelectedIndex(TablicaTransakcji.getComponentCount() - 1);
        } else {
            JOptionPane.showMessageDialog(this, "Nie można utworzyc więcej transakcji");
        }
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TablicaTransakcjiComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_TablicaTransakcjiComponentShown
        System.out.println("To sie moze przydac :P ");
    }//GEN-LAST:event_TablicaTransakcjiComponentShown

    private void TablicaTransakcjiStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TablicaTransakcjiStateChanged
        try {
            Transakcja trans = (Transakcja) TablicaTransakcji.getSelectedComponent();
            DaneSklepu.getStrony().get("PanelTransakcji").setCurrentID(trans.getIdTransakcji());

        } catch (NullPointerException ex) {
        }// TODO add your handling code here:        // TODO add your handling code here:// TODO add your handling code here:
    }//GEN-LAST:event_TablicaTransakcjiStateChanged

    public int znajdzNajmniejszaMozliwaLiczbe() {
        int najmniejsza = -1;
        Collections.sort(getZestawLiczb());
        for (int i = 0; i < getZestawLiczb().size() - 1; i++) {
            if (zestawLiczb.get(i + 1) - zestawLiczb.get(i) == 1) {
                continue;
            }
            najmniejsza = zestawLiczb.get(i) + 1;
            break;
        }
        if (najmniejsza == -1) {
            //if (zestawLiczb.size() == 1) return 1;else 
            return zestawLiczb.size() + 1;
        } else {
            return najmniejsza;
        }

    }

    public void zakonczTransakcje() {

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane TablicaTransakcji;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the zestawLiczb
     */
    public static ArrayList<Integer> getZestawLiczb() {
        return zestawLiczb;
    }

    /**
     * @param zestawLiczb the zestawLiczb to set
     */
    public static void setZestawLiczb(ArrayList<Integer> zestawLiczb) {
        PanelTransakcji.zestawLiczb = zestawLiczb;
    }
}
