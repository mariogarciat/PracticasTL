/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Daniel Jaramillo
 */
public class Gramatica {
    
    ArrayList<Produccion> producciones;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ArrayList array = control.Txt_gramatica_parser.getProductionArrayList("gramatica_prueba.txt");
            System.out.println("");
        } catch (IOException ex) {
            Logger.getLogger(Gramatica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    Gramatica cargarGramatica() {
        return null;
    }
    
    
    
}
