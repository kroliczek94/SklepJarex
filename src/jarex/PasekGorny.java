/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarex;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Łukasz Królik
 */
public class PasekGorny extends javax.swing.JPanel {

    /**
     * Creates new form PasekGorny
     */
    public PasekGorny() {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle maximumWindowBounds = graphicsEnvironment.getMaximumWindowBounds();
        setLayout(new java.awt.FlowLayout(200));
        
        setBounds(0, 0, (int) maximumWindowBounds.getX(), 30);
        
        initComponents();
     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(1, 32, 84));

        jButton5.setText("Transakcja");
        add(jButton5);

        jButton4.setText("Towary");
        add(jButton4);

        jButton3.setText("Klienci");
        add(jButton3);

        jButton2.setText("jButton2");
        add(jButton2);

        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    // End of variables declaration//GEN-END:variables
}
