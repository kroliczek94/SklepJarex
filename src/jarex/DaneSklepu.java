/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarex;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;

/**
 *
 * @author Łukasz Królik
 */
public class DaneSklepu {
    private static HashMap<String, Component> strony = new HashMap<>();
    private static boolean managerMode = false;

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
    
    public DaneSklepu() {
        dodajStrony();
    }

    /**
     * @return the strony
     */
    public static HashMap<String, Component> getStrony() {
        return strony;
    }

    /**
     * @param strony the strony to set
     */
    public static void setStrony(HashMap<String, Component> strony) {
        DaneSklepu.strony = strony;
    }
    
    private static void dodajStrony(){
        
        MenuStartowe menu = new MenuStartowe();
        GetClient client = new GetClient();
        PanelTransakcji panel = new PanelTransakcji();
        Transakcja transact = new Transakcja();
        
        getStrony().put("MenuStartowe", menu);
        getStrony().put("GetClient", client);
        getStrony().put("PanelTransakcji", panel);
        getStrony().put("Transakcja", transact);
        
        
        
        for (Component c : getStrony().values()){
            c.setBackground(new Color(4, 56, 145));
        }
    }
}
