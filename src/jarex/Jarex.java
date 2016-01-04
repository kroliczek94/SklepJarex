    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarex;

import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.sql.*;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Łukasz Królik
 */
public class Jarex {

    private static String doPokazania;
    private static boolean akcja = false;



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Connection conn = null;
        // Jarex.polacz(conn);

        JFrame window = new JFrame("JAREX");
        ustawFullScreena(window);

        DaneSklepu s = new DaneSklepu();
        MainWindow okno = new MainWindow();
        
        PasekGorny p = new PasekGorny();
        window.add(okno);
        okno.dodajNaglowek(p);
        //okno.dodajElement("PasekMiejsca");
        //okno.dodajElement(s.getStrony().get("MenuPowitalne"));
        przejdz("MenuPowitalne");
        window.setVisible(true);

        while (true) {
            try {
                Thread.sleep(40);

            } catch (InterruptedException ex) {
                Logger.getLogger(Jarex.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            okno.repaint();
            p.setTime();
            p.setMode();
            if (akcja) {
                okno.dodajElement(doPokazania);
            }
            akcja = false;
        }

        // TODO code application logic here
    }

    private static void ustawFullScreena(JFrame window) {
        window.setUndecorated(true);
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle maximumWindowBounds = graphicsEnvironment.getMaximumWindowBounds();
        window.setBounds(maximumWindowBounds);
    }

    public static void przejdz(String str) {
        Jarex.setDoPokazania(str);
        Jarex.setAkcja(true);
        
    }

    /**
     * @return the doPokazania
     */
    public static String getDoPokazania() {
        return doPokazania;
    }

    /**
     * @param aDoPokazania the doPokazania to set
     */
    public static void setDoPokazania(String aDoPokazania) {
        doPokazania = aDoPokazania;
    }

    /**
     * @return the akcja
     */
    public static boolean isAkcja() {
        return akcja;
    }

    /**
     * @param aAkcja the akcja to set
     */
    public static void setAkcja(boolean aAkcja) {
        akcja = aAkcja;
    }

}
