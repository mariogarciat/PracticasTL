package Model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    public static void main(String[] args) {
        Gramatica gramatica;
        try {
            //gramatica = new Gramatica("gramatica_especial1.txt");
            gramatica = new Gramatica("gramatica_lineal_derecha.txt");
            ArrayList<Produccion> producciones = gramatica.getProducciones();
            
            for (Produccion produccion : producciones) {
                System.out.println(produccion.getProduccion());
                System.out.println("Orden expresion");
                ArrayList<String> orden = produccion.getLadoDer().getExpresionOrdenada();
                for (String string : orden) {
                    System.out.println(string);
                }
                System.out.println("");
                System.out.println("");
            }
            
            System.out.println(gramatica.esEspecial());
            System.out.println(gramatica.esLinealPorDerecha());
            System.out.println(gramatica.esRegular());
            
            
        } catch (FileNotFoundException ex) {
            System.out.println("------------------ Archivo no encontrado ------------------");
            Logger.getLogger(Gramatica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
