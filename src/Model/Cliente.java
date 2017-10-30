package Model;

import control.LinealPorDerechaToEspecialParser;
import java.io.FileNotFoundException;
import java.util.TreeSet;

public class Cliente {
    public static void main(String[] args) throws FileNotFoundException {
        Gramatica gramatica = new Gramatica("gramatica_lineal_derecha.txt");
        gramatica.mostrarGramatica();
        System.out.println("Es especial: " + gramatica.esEspecial());
        TreeSet<String> noTerminales = gramatica.getNoTerminales();
        for (String noTerminal : noTerminales) {
            System.out.println(noTerminal);
        }
        
        
       
        System.out.println("");
        System.out.println("");
        Gramatica gramaticaEspecial = LinealPorDerechaToEspecialParser.convertir(gramatica);
        gramaticaEspecial.mostrarGramatica();       
        System.out.println("Es especial: " + gramaticaEspecial.esEspecial());
    }
}
