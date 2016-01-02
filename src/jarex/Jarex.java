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

    private static DaneSklepu s = new DaneSklepu();
    private static MainWindow okno = new MainWindow();

    public static void polacz(Connection conn) {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "inf117242");
        connectionProps.put("password", "dupadupa");
        try {
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//admlab2-main.cs.put.poznan.pl:1521/dblab01.cs.put.poznan.pl", connectionProps);
            System.out.println("Połączono z bazą danych");
        } catch (SQLException ex) {
            Logger.getLogger(Jarex.class.getName()).log(Level.SEVERE,
                    "nie udało się połączyć z bazą danych", ex);
            System.exit(-1);
        }

    }

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

        window.add(okno);
        okno.dodajNaglowek();
        okno.dodajElement(s.getStrony().get("MenuPowitalne"));
        window.setVisible(true);

        while (true) {
            try {
                Thread.sleep(40);

            } catch (InterruptedException ex) {
                Logger.getLogger(Jarex.class.getName()).log(Level.SEVERE, null, ex);
            }
            okno.repaint();
            if (akcja) {
                okno.dodajElement(s.getStrony().get(doPokazania));
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
