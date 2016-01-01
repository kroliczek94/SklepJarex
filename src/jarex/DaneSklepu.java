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
        
        strony.put("MenuStartowe", menu);
        strony.put("GetClient", client);
        
        
        for (Component c : strony.values()){
            c.setBackground(new Color(4, 56, 145));
        }
    }
}
