package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeSet;

public class Gramatica {

    private final ArrayList<Produccion> producciones;
    private final TreeSet<String> terminales;
    private final TreeSet<String> noTerminales;
    private final TreeSet<String> noTerminalesInalcanzables;
    private final TreeSet<String> noTerminalesMuertos;
    private final TreeSet<String> noTerminalesVivos;
    private static String NO_TERMINAL_NULO;

    public Gramatica(File file) throws FileNotFoundException {
        producciones = control.Txt_gramatica_parser.getProductionArrayList(file);
        terminales = new TreeSet<>();
        noTerminales = new TreeSet<>();
        noTerminalesMuertos = new TreeSet<>();
        noTerminalesVivos = new TreeSet<>();
        noTerminalesInalcanzables = new TreeSet<>();
        getTerminales_noTerminales_();
        getNoTerminalesInalcanzables_();
        getNoTerminalesVivos_();
        getNoTerminalesMuertos_();
        NO_TERMINAL_NULO = "_";
    }

    public Gramatica(String path) throws FileNotFoundException {
        FileReader f = new FileReader(path);
        producciones = control.Txt_gramatica_parser.getProductionArrayList(path);
        terminales = new TreeSet<>();
        noTerminales = new TreeSet<>();
        noTerminalesMuertos = new TreeSet<>();
        noTerminalesVivos = new TreeSet<>();
        noTerminalesInalcanzables = new TreeSet<>();
        getTerminales_noTerminales_();
        getNoTerminalesInalcanzables_();
        getNoTerminalesVivos_();
        getNoTerminalesMuertos_();
        NO_TERMINAL_NULO = "_";
    }

    private Gramatica(ArrayList<Produccion> producciones) {
        this.producciones = producciones;
        terminales = new TreeSet<>();
        noTerminales = new TreeSet<>();
        noTerminalesMuertos = new TreeSet<>();
        noTerminalesVivos = new TreeSet<>();
        noTerminalesInalcanzables = new TreeSet<>();
        getTerminales_noTerminales_();
        getNoTerminalesInalcanzables_();
        getNoTerminalesVivos_();
        getNoTerminalesMuertos_();
        NO_TERMINAL_NULO = "_";
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

    private void getNoTerminalesInalcanzables_() {
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
            TreeSet<String> nT = p.getLadoDer().getNoTerminales();

            // para cada no terminal encontrado es necesario hacer lo siguiente
            for (String noTerminal : nT) {

                //SOLO si el no terminal aún no pertenece al conjunto de no terminales que se saben alcanzables
                //entonces hacer lo siguiente
                if (!noTerminalesAlcanzables.contains(noTerminal)) {
                    // agregarlos al conjunto de no terminales que se saben alcanzables
                    noTerminalesAlcanzables.add(noTerminal);
                    //agregar a la pila de producciones alcanzables todas las producciones cuyo lado
                    //izquierdo contienen el no terminal que se sabe alcanzable 
                    ArrayList<Produccion> array = getProducciones(noTerminal);
                    for (Produccion produccion : array) {
                        produccionesAlcanzables.push(produccion);
                    }
                }
            }
        }
        TreeSet noTerminalesInalcanzables_ = (TreeSet) getNoTerminales().clone();
        noTerminalesInalcanzables_.removeAll(noTerminalesAlcanzables);
        noTerminalesInalcanzables.addAll(noTerminalesInalcanzables_);
    }

    //Los no terminales vivos son todos aquellos que están definidos solamente con
    //no terminales vivos o terminales en su lado derecho
    private void getNoTerminalesVivos_() {

        //Se hace una clonación de la lista original, pues se van a ir eliminando producciones 
        //conforme se van encontrando terminales vivos
        ArrayList<Produccion> produccionesClon = (ArrayList) producciones.clone();

        //Necesario de definir, pues no se pueden eliminar elementos del ArrayList dentro del bucle for
        ArrayList<Produccion> producciones_aRemover = new ArrayList<>();
        boolean seAgregoNuevoNoTerminal;

        //Se busca cuáles no terminales están sólo definidos por terminales, la secuancia nula, o no terminales vivos en sus lados derechos
        //En otras palabras, cuáles no se definen por ningún no terminal (a menos que éste esté en la lista de los no terminales vivos)
        do {
            seAgregoNuevoNoTerminal = false;
            for (Produccion produccion : produccionesClon) {
                Expresion exDerecha = produccion.getLadoDer();
                //NT_exDer : no terminales de la expresión izquierda
                TreeSet NT_exDer = (TreeSet) exDerecha.getNoTerminales().clone();
                //Del conjunto anterior se eliman los terminales que ya se saben son vivos, para ver si queda algún
                //terminal que aún no se sabe si es vivo o muerto
                NT_exDer.removeAll(noTerminalesVivos);

                if (NT_exDer.isEmpty()) {
                    String noTVivo = produccion.getLadoIzq().getExpresion();
                    noTerminalesVivos.add(noTVivo);
                    producciones_aRemover.add(produccion);
                    seAgregoNuevoNoTerminal = true;
                }
            }
            produccionesClon.removeAll(producciones_aRemover);
        } while (seAgregoNuevoNoTerminal); //El ciclo termina cuando se dejaron de encontrar terminales vivos, RIP terminales :c

    }

    private void getNoTerminalesMuertos_() {
        TreeSet<String> noTMuertos = (TreeSet<String>) noTerminales.clone();
        noTMuertos.removeAll(noTerminalesVivos);
        noTerminalesMuertos.addAll(noTMuertos);
    }

    public ArrayList<Produccion> getProducciones() {
        return producciones;
    }

    public TreeSet getTerminales() {
        return terminales;
    }

    public TreeSet getNoTerminales() {
        return noTerminales;
    }

    public TreeSet<String> getNoTerminalesInalcanzables() {
        return noTerminalesInalcanzables;
    }

    public TreeSet<String> getNoTerminalesMuertos() {
        return noTerminalesMuertos;
    }

    public TreeSet<String> getNoTerminalesVivos() {
        return noTerminalesVivos;
    }

    public ArrayList<Produccion> getProduccionesInalcanzables() {
        ArrayList<Produccion> produccionesInalcanzables = new ArrayList<>();
        //int cantidadProducciones = producciones.size();
        TreeSet conjuntoProduccionesInalcanzables = new TreeSet(producciones);

        return null;
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

    public Gramatica simplificar() {
        if (!(noTerminalesMuertos == null) && !(noTerminalesInalcanzables == null)) {
            ArrayList<Produccion> producciones_aRemover = new ArrayList<>();
            for (Produccion produccion : producciones) {
                String NTexIz = produccion.getLadoIzq().getExpresion();
                TreeSet<String> NTexDer = produccion.getLadoDer().getNoTerminales();
                if (noTerminalesMuertos.contains(NTexIz)) {
                    producciones_aRemover.add(produccion);
                } else if (noTerminalesInalcanzables.contains(NTexIz)) {
                    producciones_aRemover.add(produccion);
                } else {
                    for (String noTerminal : NTexDer) {
                        if (noTerminalesMuertos.contains(noTerminal)) {
                            producciones_aRemover.add(produccion);
                            break;
                        } else if (noTerminalesInalcanzables.contains(noTerminal)) {
                            producciones_aRemover.add(produccion);
                            break;
                        }
                    }
                }
            }
            ArrayList<Produccion> produccionesReducidas = (ArrayList<Produccion>) producciones.clone();
            produccionesReducidas.removeAll(producciones_aRemover);
            Gramatica gramaticaSimplificada = new Gramatica(produccionesReducidas);
            return gramaticaSimplificada;
        }
        return null;
    }

    void mostrarGramatica() {
        int i = 1;
        for (Produccion produccion : producciones) {
            System.out.println(i + ". " + produccion.getProduccion());
            i++;
        }
    }

    public boolean esEspecial() {
        for (Produccion produccion : producciones) {
            boolean esEspecial = false;
            Expresion ExDer = produccion.getLadoDer();
            if (ExDer.getExpresionOrdenada().size() <= 2) {
                ArrayList<String> elementos = ExDer.getExpresionOrdenada();

                if (ExDer.getExpresionOrdenada().size() == 2) {
                    if (noTerminales.contains(elementos.get(1))) {
                        if (terminales.contains(elementos.get(0))) {
                            if (elementos.get(0).compareTo(NO_TERMINAL_NULO) != 0) {
                                esEspecial = true;
                            }
                        }
                    }
                } else {
                    if (elementos.get(0).compareTo(NO_TERMINAL_NULO) == 0) {
                        esEspecial = true;
                    }
                }
            }
            if (!esEspecial) {
                return false;
            }
        }
        return true;
    }

    public boolean esLinealPorDerecha() {
        for (Produccion produccion : producciones) {
            boolean esLinealPorDerecha = false;
            Expresion ExDer = produccion.getLadoDer();
            ArrayList<String> elementos = ExDer.getExpresionOrdenada();
            if (ExDer.getExpresionOrdenada().size() == 1) {
                if (noTerminales.contains(elementos.get(0))) {
                    esLinealPorDerecha = true;
                } else if (elementos.get(0).compareTo(NO_TERMINAL_NULO) == 0) {
                    esLinealPorDerecha = true;
                }
            } else {
                //obtiene el número de elementos
                int numElementos = ExDer.getExpresionOrdenada().size();
                //obtiene el último elemento
                String lastElemento = ExDer.getExpresionOrdenada().get(numElementos-1);
                if (noTerminales.contains(lastElemento)) {
                    esLinealPorDerecha = true;
                }
            }
            if (!esLinealPorDerecha) {
                return false;
            }
        }
        return true;
    }
    
    public boolean esRegular(){
        return esLinealPorDerecha();
    }

    public void setTerminalNulo(String NT_nulo) {
        NO_TERMINAL_NULO = NT_nulo;
    }

    public String getTerminalNulo() {
        return NO_TERMINAL_NULO;
    }
}
