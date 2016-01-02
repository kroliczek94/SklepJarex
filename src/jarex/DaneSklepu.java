/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarex;

import Klienci.*;
import Transakcje.*;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;


/**
 *
 * @author Łukasz Królik
 */
public class DaneSklepu {

    private static HashMap<String, Component> strony = new HashMap<>();
    private static boolean managerMode = true;
    private static ConcurrentLinkedDeque<String> stos = new ConcurrentLinkedDeque<>();
    private static boolean wsteczButton = false;

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

    private static void dodajStrony() {

        MenuStartowe menu = new MenuStartowe();
        GetClient client = new GetClient();
        PanelTransakcji panel = new PanelTransakcji();

        MenuPowitalne mstartowe = new MenuPowitalne();
        MenuKlienta mklienta = new MenuKlienta();

        SekcjaStatystyczna stats = new SekcjaStatystyczna();

        getStrony().put("MenuStartowe", menu);
        getStrony().put("GetClient", client);
        getStrony().put("PanelTransakcji", panel);
        getStrony().put("MenuPowitalne", mstartowe);
        getStrony().put("SekcjaStatystyczna", stats);
        getStrony().put("MenuKlienta", mklienta);

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
