package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gramatica {

    private final ArrayList<Produccion> producciones;
    private final TreeSet<String> terminales;
    private final TreeSet<String> noTerminales;

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

            System.out.println("");
            System.out.println("No termiales INALCANZABLES:");            
            TreeSet noTerminalesIn = gramatica.getNoTerminalesInalcanzables();
            for (Object str : noTerminalesIn) {
                String s = (String)str;
                System.out.println(s);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("------------------ Archivo no encontrado ------------------");
            Logger.getLogger(Gramatica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getTerminales_noTerminales_() {
        int numeroProducciones = producciones.size();
        for (int i = 0; i < numeroProducciones; i++) {
            Produccion produccion_i = getProduccion(i);
            Expresion expresion_izquierda_i = produccion_i.getLadoIzq();
            Expresion expresion_derecha_i = produccion_i.getLadoDer();
            terminales.addAll(expresion_izquierda_i.getTerminales());
            terminales.addAll(expresion_derecha_i.getTerminales());
            noTerminales.addAll(expresion_izquierda_i.getNoTerminales());
            noTerminales.addAll(expresion_derecha_i.getNoTerminales());
        }
    }

    public TreeSet getNoTerminales() {
        return noTerminales;
    }

    public TreeSet getTerminales() {
        return terminales;
    }

    public ArrayList<Produccion> getProducciones() {
        return producciones;
    }

    public Gramatica(File file) throws FileNotFoundException {
        producciones = control.Txt_gramatica_parser.getProductionArrayList(file);
        terminales = new TreeSet<>();
        noTerminales = new TreeSet<>();
        getTerminales_noTerminales_();

    }

    public Gramatica(String path) throws FileNotFoundException {
        producciones = control.Txt_gramatica_parser.getProductionArrayList(path);
        terminales = new TreeSet<>();
        noTerminales = new TreeSet<>();
        getTerminales_noTerminales_();
    }

    void mostrarGramatica() {
        int i = 1;
        for (Produccion produccion : producciones) {
            System.out.println(i + ". " + produccion.getProduccion());
            i++;
        }
    }

    public Produccion getProduccion(int i) {
        return producciones.get(i);
    }

    public String getNoTerminalInicial() {
        Expresion expresionInicial = producciones.get(0).getLadoIzq();
        TreeSet noTerminalesIniciales = expresionInicial.getNoTerminales();
        return (String) noTerminalesIniciales.first();
    }

    //obtiene las producciones que comienzan con el no terminal ingresado como noTerminal
    //NOTA: también deben escribirse los corchetes angulares
    public ArrayList<Produccion> getProducciones(String noTerminal) {

        ArrayList<Produccion> listaProducciones = new ArrayList<>();
        int cantidadProducciones = this.producciones.size();

        for (int i = 0; i < cantidadProducciones; i++) {
            Produccion produccion = getProduccion(i);
            Expresion expresion = produccion.getLadoIzq();
            if (expresion.getExpresion().compareTo(noTerminal) == 0) {
                listaProducciones.add(produccion);
            }
        }
        return listaProducciones;
    }

    //obtiene todas las producciones cuyo lado izquierdo son el no terminal inicial
    public ArrayList<Produccion> getProduccionesIniciales() {
        return getProducciones(getNoTerminalInicial());
    }

    public TreeSet getNoTerminalesInalcanzables() {

        // el conjunto noTerminalesAlcanzables inicialmente debe contener al no terminal inicial
        TreeSet noTerminalesAlcanzables = new TreeSet();
        noTerminalesAlcanzables.add(this.getNoTerminalInicial());

        // se van a ir agregando, a medida que se encuentran, producciones cuyo lado izquierdo son no terminales alcanzables.
        // Sólo se agregan a la pila si no hacen parte del conjunto noTerminalesAlcanzables.
        // Inicialmente contine las producciones iniciales (producciones cuyo lado izquierdo son el no terminal inicial)
        Stack<Produccion> produccionesAlcanzables = new Stack<>();
        ArrayList<Produccion> produccionesIniciales = getProduccionesIniciales();
        for (Produccion produccionInicial : produccionesIniciales) {
            produccionesAlcanzables.push(produccionInicial);
        }

        while (!produccionesAlcanzables.empty()) {
            // se desapila y analiza la producción que está en el tope de la pila
            //de producciones alcanzables
            Produccion p = produccionesAlcanzables.pop();

            // se encuentran TODOS los no terminales en el lado derecho de la producción en cuestión
            TreeSet nT = p.getLadoDer().getNoTerminales();

            // para cada no terminal encontrado es necesario hacer lo siguiente
            for (Object o : nT) {
                String noTerminal = (String) o;

                //SOLO si el no terminal aún no pertenece al conjunto de no terminales que se saben alcanzables
                //entonces hacer lo siguiente
                if (!noTerminalesAlcanzables.contains(o)) {
                    // agregarlos al conjunto de no terminales que se saben alcanzables
                    noTerminalesAlcanzables.add(o);
                    //agregar a la pila de producciones alcanzables todas las producciones cuyo lado
                    //izquierdo contienen el no terminal que se sabe alcanzable 
                    ArrayList<Produccion> array = getProducciones(noTerminal);
                    for (Produccion produccion : array) {
                        produccionesAlcanzables.push(produccion);
                    }
                }
            }
        }
        TreeSet noTerminalesInalcanzables = (TreeSet) getNoTerminales().clone();
        noTerminalesInalcanzables.removeAll(noTerminalesAlcanzables);
        return noTerminalesInalcanzables;
    }

    public ArrayList<Produccion> getProduccionesInalcanzables() {
        ArrayList<Produccion> produccionesInalcanzables = new ArrayList<>();
        //int cantidadProducciones = producciones.size();
        TreeSet conjuntoProduccionesInalcanzables = new TreeSet(producciones);

        return null;
    }
}
