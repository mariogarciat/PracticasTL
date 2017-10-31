package control;

import Model.Expresion;
import Model.Gramatica;
import Model.Produccion;
import java.util.ArrayList;
import java.util.TreeSet;

// -----IMPORTENTE-----
    // el para convertir una gramática lineal por derecha a una gramática especial
    // no comprueba que quien esté haciendo uso de este método sí sea una gramática lineal por derecha
    // esta comprobación debe hacerse en la clase cliente de este método

public class LinealPorDerechaToEspecialParser {

    static String produccionNula = "<nulo>";

    public static Gramatica convertir(Gramatica gramatica) {
        ArrayList<Produccion> nuevasProducciones = new ArrayList<>();
        
        //se dejan sólo las producciones que no son especiales
        ArrayList<Produccion> producciones = gramatica.getProducciones();
        producciones.removeAll(gramatica.getProduccionesEspeciales());
        ArrayList<Produccion> produccionesCaso4 = new ArrayList<>();
        ArrayList<Produccion> agregarProducciones;
        
        //se identifica el caso de casa produccion y se procede a transformarlo
        for (Produccion produccion : producciones) {
            ArrayList<String> expresionOrdenada = produccion.getLadoDer().getExpresionOrdenada();
            if (expresionOrdenada.size() == 1) {
                if (gramatica.getTerminales().contains(expresionOrdenada.get(0))) {
                    agregarProducciones = caso1(produccion);
                    nuevasProducciones.addAll(agregarProducciones);
                } else {
                    //las producciones de tipo 4 se tratan al final
                    produccionesCaso4.add(produccion);
                }
            } else {
                String ultimoElemento = expresionOrdenada.get(expresionOrdenada.size()-1);
                if (gramatica.getTerminales().contains(ultimoElemento)) {
                    agregarProducciones = caso2(produccion);
                    nuevasProducciones.addAll(agregarProducciones);
                } else {
                    agregarProducciones = caso3(produccion);
                    nuevasProducciones.addAll(agregarProducciones);
                }
            }
        }
        nuevasProducciones.addAll(gramatica.getProduccionesEspeciales());
        for (Produccion produccion : produccionesCaso4) {
            agregarProducciones = caso4(produccion, nuevasProducciones);
            nuevasProducciones.addAll(agregarProducciones);
        }
        return new Gramatica(nuevasProducciones);
    }

    // <A> -> a
    private static ArrayList<Produccion> caso1(Produccion produccion) {
        ArrayList<Produccion> nuevasProducciones = new ArrayList<>();
        Expresion exDer = produccion.getLadoDer();
        String expresionDer = exDer.getExpresion().concat(produccionNula);
        String expresionIz = produccion.getLadoIzq().getExpresion();
        Produccion nuevaProduccion = new Produccion(expresionIz, expresionDer);
        nuevasProducciones.add(nuevaProduccion);
        nuevaProduccion = new Produccion(produccionNula, Gramatica.getTerminalNulo());
        nuevasProducciones.add(nuevaProduccion);
        return nuevasProducciones;
    }

    // <A> -> abcd...
    private static ArrayList<Produccion> caso2(Produccion produccion) {
        ArrayList<Produccion> nuevasProducciones = new ArrayList<>();
        String nuevoNoTerminal;
        
        
        //toma la producción y la convierte en especial
        Expresion expresionDer = produccion.getLadoDer();
        String exIz = produccion.getLadoIzq().getExpresion();
        int numElementosDer = expresionDer.getTerminales().size();
        String nuevaExDer;
        nuevaExDer = expresionDer.getExpresion();
        nuevaExDer = nuevaExDer.substring(1, numElementosDer);
        nuevoNoTerminal = "<" + nuevaExDer + ">";
        nuevaExDer = expresionDer.getExpresion().substring(0, 1);
        nuevaExDer = nuevaExDer + nuevoNoTerminal;
        Produccion nuevaProduccion = new Produccion(exIz, nuevaExDer);
        nuevasProducciones.add(nuevaProduccion);

        
        
        while (nuevoNoTerminal.length() > 3){
            String exDer = nuevaExDer;
            exIz = nuevoNoTerminal;
            String nuevoTerminal = nuevoNoTerminal.substring(1, 2);
            nuevoNoTerminal = "<" + nuevoNoTerminal.substring(2, nuevoNoTerminal.length());
            nuevaExDer = nuevoTerminal + nuevoNoTerminal;
            nuevaProduccion = new Produccion(exIz, nuevaExDer);
            nuevasProducciones.add(nuevaProduccion);
        }
        
        nuevaExDer = nuevoNoTerminal.substring(1, 2);
        nuevasProducciones.addAll(caso1(new Produccion(nuevoNoTerminal, nuevaExDer)));
        
        return nuevasProducciones;
    }
    
    // <A> -> abcd...<ABC>
    private static ArrayList<Produccion> caso3(Produccion produccion) {
        ArrayList<Produccion> nuevasProducciones = new ArrayList<>();
        String nuevoNoTerminal;
        
        
        //toma la producción y la convierte en especial
        Expresion expresionDer = produccion.getLadoDer();
        String exIz = produccion.getLadoIzq().getExpresion();
        int numTerminales = expresionDer.getTerminales().size();
        
        String noTerminal = produccion.getLadoDer().getNoTerminales().first();        
        int numCaracteresNoTerminal = noTerminal.length()-2;
        String caracteresNoTerminal = noTerminal.substring(1, noTerminal.length()-1);
        String nuevaExDer = expresionDer.getExpresion();
        int finalTerminales = nuevaExDer.length()-numCaracteresNoTerminal-2;
        String nuevaExDer_terminales = nuevaExDer.substring(1,finalTerminales);
        nuevaExDer = nuevaExDer_terminales.concat(caracteresNoTerminal);
        nuevoNoTerminal = "<" + nuevaExDer + ">";
        nuevaExDer = expresionDer.getExpresion().substring(0, 1);
        nuevaExDer = nuevaExDer + nuevoNoTerminal;
        Produccion nuevaProduccion = new Produccion(exIz, nuevaExDer);
        nuevasProducciones.add(nuevaProduccion);

        
        //cuando nuevoNoTerminal tenga forma <N>, debe pararse, pues se llegó al caso 1
        while (nuevoNoTerminal.length() > numCaracteresNoTerminal+2){
            
            String exDer = nuevaExDer;
            exIz = nuevoNoTerminal;
            String nuevoTerminal = nuevoNoTerminal.substring(1, 2);
            nuevoNoTerminal = "<" + nuevoNoTerminal.substring(2, nuevoNoTerminal.length());
            nuevaExDer = nuevoTerminal + nuevoNoTerminal;
            nuevaProduccion = new Produccion(exIz, nuevaExDer);
            nuevasProducciones.add(nuevaProduccion);
        }
        
        return nuevasProducciones;
    }
    
    // <A> -> <B>
    private static ArrayList<Produccion> caso4(Produccion produccion, ArrayList<Produccion> producciones){
        ArrayList<Produccion> nuevasProducciones = new ArrayList<>();
        
        String exIz = produccion.getLadoIzq().getExpresion();
        String exDer = produccion.getLadoDer().getExpresion();
        
        //obtener todas las producciones que comincen con el no terminal de la expresión derecha
        ArrayList<Produccion> produccionesInteres = new ArrayList<>();
        for (Produccion prod : producciones) {            
            if (prod.getLadoIzq().getExpresion().compareTo(exDer) == 0) {
                produccionesInteres.add(prod);
            }
        }
        
        for (Produccion prod : produccionesInteres) {
            String nuevaExpDerecha = prod.getLadoDer().getExpresion();
            Produccion nuevaProduccion = new Produccion(exIz, nuevaExpDerecha);
            nuevasProducciones.add(nuevaProduccion);
        }
        return nuevasProducciones;
    }
}