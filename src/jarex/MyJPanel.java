/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarex;

/**
 *
 * @author Łukasz Królik
 */
public class MyJPanel extends javax.swing.JPanel implements TabelaDoWypelnienia{
    private Integer currentID;
    private Integer nrKolejny;
    private Integer idKlienta;
    
    private boolean transakcja;
    private boolean poTransakcji = false;
    
    /**
     * Creates new form MyJPanel
     */
    public MyJPanel() {
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void wypelnijTabele() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void zmienLabela() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void wyczyscTabele() {
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the currentID
     */
    public Integer getCurrentID() {
        return currentID;
    }

    /**
     * @param currentID the currentID to set
     */
    public void setCurrentID(Integer currentID) {
        this.currentID = currentID;
    }

    /**
     * @return the nrKolejny
     */
    public Integer getNrKolejny() {
        return nrKolejny;
    }

    /**
     * @param nrKolejny the nrKolejny to set
     */
    public void setNrKolejny(Integer nrKolejny) {
        this.nrKolejny = nrKolejny;
    }

    /**
     * @return the transakcja
     */
    public boolean isTransakcja() {
        return transakcja;
    }

    /**
     * @param transakcja the transakcja to set
     */
    public void setTransakcja(boolean transakcja) {
        this.transakcja = transakcja;
    }

    /**
     * @return the idKlienta
     */
    public Integer getIdKlienta() {
        return idKlienta;
    }

    /**
     * @param idKlienta the idKlienta to set
     */
    public void setIdKlienta(Integer idKlienta) {
        this.idKlienta = idKlienta;
    }

    /**
     * @return the poTransakcji
     */
    public boolean isPoTransakcji() {
        return poTransakcji;
    }

    /**
     * @param poTransakcji the poTransakcji to set
     */
    public void setPoTransakcji(boolean poTransakcji) {
        this.poTransakcji = poTransakcji;
    }

}
