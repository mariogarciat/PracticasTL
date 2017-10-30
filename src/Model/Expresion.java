package Model;

import java.util.ArrayList;
import java.util.TreeSet;

public class Expresion {

    private final String expresion;
    private final TreeSet<String> terminales;
    private final TreeSet<String> noTerminales;
    private final ArrayList<String> expresionOrdenada;

    public Expresion(String expresion) {
        this.expresion = expresion;
        // TreeSet es una clase que de la API de Java que implementa la interfaz Set<E>
        terminales = new TreeSet<>(); 
        noTerminales = new TreeSet<>();
        expresionOrdenada = new ArrayList<>();
        getAll_();
    }
    
    

    private void getAll_(){
        int charIndex = 0;
        int numCaracteres = expresion.length();
        char charAtIndex;
        while (charIndex < numCaracteres) {
            if (expresion.charAt(charIndex) == '<') {
                int inicioNoTerminal = charIndex;
                int finalNoTerminal;
                charIndex = charIndex + 2; //no pueden haber no terminales vacíos
                while (expresion.charAt(charIndex) != '>') {                    
                    charIndex++;
                }
                finalNoTerminal = ++charIndex;
                String noTerminal = expresion.substring(inicioNoTerminal, finalNoTerminal);
                expresionOrdenada.add(noTerminal);
                noTerminales.add(noTerminal);
            } else {
                charAtIndex = expresion.charAt(charIndex);
                expresionOrdenada.add(String.valueOf(charAtIndex));
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
    
    public ArrayList<String> getExpresionOrdenada() {
        return expresionOrdenada;
    }
    
    public static String getNoterminal(String noTerminal){
        String nTerminal = noTerminal.substring(1, noTerminal.length()-1);
        return nTerminal;
    }
}
