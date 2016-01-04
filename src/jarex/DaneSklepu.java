/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarex;

import Klienci.*;
import Transakcje.*;
import Towary.*;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;


/**
 *
 * @author Łukasz Królik
 */
public class DaneSklepu {
    private static String tytul;
    private static HashMap<String, MyJPanel> strony = new HashMap<>();
    private static boolean managerMode = true;
    private static ConcurrentLinkedDeque<String> stos = new ConcurrentLinkedDeque<>();
    private static boolean wsteczButton = false;
    
    private static Connection conn = null;
    
    public static void polacz() {
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
     * @return the managerMode
     */
    public static boolean isManagerMode() {
        return managerMode;
    }

    /**
     * @param aManagerMode the managerMode to set
     */
    public static void setManagerMode(boolean aManagerMode) {
        managerMode = aManagerMode;
    }

    /**
     * @return the wsteczButton
     */
    public static boolean isWsteczButton() {
        return wsteczButton;
    }

    /**
     * @param aWsteczButton the wsteczButton to set
     */
    public static void setWsteczButton(boolean aWsteczButton) {
        wsteczButton = aWsteczButton;
    }

    /**
     * @return the conn
     */
    public static Connection getConn() {
        return conn;
    }

    /**
     * @param aConn the conn to set
     */
    public static void setConn(Connection aConn) {
        conn = aConn;
    }

    public DaneSklepu() {
        //DaneSklepu.polacz();
        dodajStrony();
        
        
        
    }

    /**
     * @return the strony
     */
    public static HashMap<String, MyJPanel> getStrony() {
        return strony;
    }

    /**
     * @param strony the strony to set
     */
    public static void setStrony(HashMap<String, MyJPanel> strony) {
        DaneSklepu.strony = strony;
    }

    private static void dodajStrony() {
        
        MenuStartowe menu = new MenuStartowe();
        GetClient client = new GetClient();
        PanelTransakcji panel1 = new PanelTransakcji();

        MenuPowitalne mstartowe = new MenuPowitalne();
        MenuKlienta mklienta = new MenuKlienta();
        MenuDostaw mdostaw = new MenuDostaw();
        MenuTransakcji mtransakcji = new MenuTransakcji();
        MenuTowarow mtowarow = new MenuTowarow();
        SekcjaStatystyczna stats = new SekcjaStatystyczna();
        PlanszaPoTransakcji ppt1 = new PlanszaPoTransakcji();
      

        GetTowar gtowar = new GetTowar();
        GetClient gclient = new GetClient();
        AddDostawa adost = new AddDostawa();
        
        getStrony().put("MenuStartowe", menu);
        getStrony().put("GetClient", client);
        getStrony().put("PanelTransakcji", panel1);
        getStrony().put("MenuPowitalne", mstartowe);
        getStrony().put("SekcjaStatystyczna", stats);
        getStrony().put("MenuKlienta", mklienta);
        getStrony().put("MenuTransakcji", mtransakcji);
        getStrony().put("MenuDostaw", mdostaw);
        getStrony().put("MenuTowarow", mtowarow);
        getStrony().put("MenuKlienta", mklienta);
        strony.put("GetTowar", gtowar);
        strony.put("GetClient", gclient);
        strony.put("PlanszaPoTransakcji", ppt1);
        strony.put("AddDostawa", adost);
        
        
        for (Component c : getStrony().values()) {
            c.setBackground(new Color(4, 56, 145));
        }
    }

    /**
     * @return the stos
     */
    public static ConcurrentLinkedDeque getStos() {
        //System.out.println(getStos().);
        return stos;
    }

    /**
     * @param stos the stos to set
     */
    public static void setStos(ConcurrentLinkedDeque stos) {
        DaneSklepu.stos = stos;
    }
}
