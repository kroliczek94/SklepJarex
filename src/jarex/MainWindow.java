/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarex;

import java.awt.BorderLayout;
import java.awt.Component;

/**
 *
 * @author Łukasz Królik
 */
public class MainWindow extends javax.swing.JPanel {

    String current = null;
    String previous = null;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        setLayout(new java.awt.BorderLayout());
        //this.dodajNaglowek();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void dodajNaglowek(PasekGorny pasek) {
        //PasekGorny pasek = new PasekGorny();
        this.add(pasek, BorderLayout.NORTH);

        validate();
        repaint();
    }

    public void dodajElement(String str) {
        //String str = DaneSklepu.getStrony().
        if (current != null) {
            this.remove(DaneSklepu.getStrony().get(current));
        }
        this.add(DaneSklepu.getStrony().get(str), BorderLayout.CENTER);
        DaneSklepu.getStrony().get(str).wypelnijTabele();
        previous = current;
        if (previous != null && !DaneSklepu.isWsteczButton()) {
            
            DaneSklepu.getStos().add(previous);
            DaneSklepu.setWsteczButton(false);
        }
        current = str;
        validate();
        repaint();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
