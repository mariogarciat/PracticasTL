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
    
    private final ArrayList<Produccion> producciones;
    
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
            
            
            System.out.println("No terminal inicial: " + gramatica.getNoTerminalInicial());
            
            
            System.out.println("Producciones iniciales:");
            for (Produccion p : gramatica.getProduccionesIniciales()) {
                System.out.println(p.getProduccion());
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
    
    public Produccion getProduccion(int i){
        return producciones.get(i);
    }
    
    public String getNoTerminalInicial(){
        Expresion expresionInicial = producciones.get(0).getLadoIzq();
        TreeSet noTerminalesIniciales = expresionInicial.getNoTerminales();
        return (String)noTerminalesIniciales.first();
    }
    
    public ArrayList<Produccion> getProduccionesIniciales(){
        
        ArrayList<Produccion> produccionesIniciales = new ArrayList<>();
        produccionesIniciales.add(getProduccion(0));
        
        String noTerminalInicial = getNoTerminalInicial();
        int cantidadProducciones = producciones.size();
        
        //se comienza desde la producción 2, porque la producción 1 POR DEFECTO es la que contiene al no terminal inicial en su expresión izquieda
        for (int i = 1; i < cantidadProducciones; i++) {
            Produccion produccion = getProduccion(i);
            Expresion expresion = produccion.getLadoIzq();
            if (expresion.getExpresion().compareTo(noTerminalInicial) == 0) {
                produccionesIniciales.add(produccion);
            }
        }
        return produccionesIniciales;
    }
    
    public ArrayList<Produccion> getProduccionesInalcanzables(){ 
        ArrayList<Produccion> produccionesInalcanzables = new ArrayList<>();
        //int cantidadProducciones = producciones.size();
        TreeSet conjuntoProduccionesInalcanzables = new TreeSet(producciones);
        
        
        
        return null;
    }
}
