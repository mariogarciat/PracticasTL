/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Daniel Jaramillo
 */
public class Expresion {

    private final String expresion;
    private final TreeSet<String> terminales;
    private final TreeSet<String> noTerminales;

    public Expresion(String expresion) {
        this.expresion = expresion;
        // TreeSet es una clase que de la API de Java que implementa la interfaz Set<E>
        terminales = new TreeSet<>(); 
        noTerminales = new TreeSet<>();
        getTerminales_();
        getNoTerminales_();
    }

    private void getNoTerminales_() {
        /*
                USO DE REGEX (\\d\\.)((.*)->(.*))
                    Este regEx está compuesto por 5 grupos, a saber:
                        GRUPO 0: está compuesto por todo lo que haga match con el regEx (podría verse como la concatenación de todos los grupos)
                        GRUPO 1: (\\d+\\.) compuesto por UNA O MÁS cantidad de dígitos que estén seguidos por UN caracter punto
                        GRUPO 2: ((.*)->(.*)) compuesto por la concatenación del grupo 3, la flecha, y el grupo 4, en ese orden
                        GRUPO 3: primer (.*) [EXPRESIÓN LADO IZQUIERDO] compuesto por todo lo que esté entre el grupo anterior (grupo 1) y la flecha ( -> )
                        GRUPO 4: segundo (.*) [EXPRESIÓN LADO DERECHO] compuesto por  lo que esté luego de la flecha
         */
        String pattern = "<\\p{Upper}+>"; // los terminales DEBEN SER ESCRITOS EN MAYÚSCULA
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(expresion);
        while (m.find()) {
            int primero = m.start();
            int ultimo = m.end();
            String noTerminal = expresion.substring(primero, ultimo);
            noTerminales.add(noTerminal);
        }
    }

    private void getTerminales_() {
        int charIndex = 0;
        int numCaracteres = expresion.length();
        char charAtIndex;
        while (charIndex < numCaracteres) {
            if (expresion.charAt(charIndex) == '<') {
                charIndex = charIndex + 2; //no pueden haber no terminales vacíos
                while (expresion.charAt(charIndex) != '>') {                    
                    charIndex++;
                }
                charIndex++;
            } else {
                charAtIndex = expresion.charAt(charIndex);
                terminales.add(String.valueOf(charAtIndex));
                charIndex++;
            }
        }

    }

    public String getExpresion() {
        return expresion;
    }

    public TreeSet<String> getNoTerminales() {
        return noTerminales;
    }

    public TreeSet<String> getTerminales() {
        return terminales;
    }
}
