/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        Gramatica gramatica;
        try {
            gramatica = new Gramatica("gramatica_prueba.txt");
            //gramatica.mostrarGramatica();
            ArrayList<Produccion> array = gramatica.getProducciones();
            Expresion exp = array.get(0).getLadoDer();
            TreeSet<String> terminales = exp.getTerminales();
            System.out.println("Expresión: " + exp.getExpresion());
            for (String str : terminales) {
                System.out.println(str);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("------------------ Archivo no encontrado ------------------");
            Logger.getLogger(Gramatica.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public ArrayList<Produccion> getProducciones() {
        return producciones;
    }
    
    public Gramatica(File file) throws FileNotFoundException{
        producciones = control.Txt_gramatica_parser.getProductionArrayList(file);
    }

    public Gramatica(String path) throws FileNotFoundException {
        producciones = control.Txt_gramatica_parser.getProductionArrayList(path);
    }
    
    void mostrarGramatica() {
        int i = 1;
        for (Produccion produccion : producciones) {
            System.out.println(i + ". " + produccion.getProduccion());
            i++;
        }
    }
    
    
    
}
