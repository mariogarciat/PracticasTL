package Model;

import control.LinealPorDerechaToEspecialParser;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Cliente {
    public static void main(String[] args) throws FileNotFoundException {
        Gramatica gramatica = new Gramatica("gramatica_lineal_derecha2.txt");
        gramatica.mostrarGramatica();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        Gramatica gramaticaEspecial = LinealPorDerechaToEspecialParser.convertir(gramatica);
        gramaticaEspecial.mostrarGramatica();
    }
}
