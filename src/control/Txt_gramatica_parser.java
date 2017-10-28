package control;

import Model.Expresion;
import Model.Produccion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Txt_gramatica_parser {

    /* --------------------- CAPTURA DE ARCHIVOS (FILES)  --------------------- */
    public static ArrayList getProductionArrayList(File file) throws FileNotFoundException {

        FileReader f = new FileReader(file);
        try {
            return getProductionArrayList(f);
        } catch (IOException ex) {
            System.out.println("Error en la lectura del archivo");
            Logger.getLogger(Txt_gramatica_parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList getProductionArrayList(String path) throws FileNotFoundException {

        FileReader f = new FileReader(path);
        try {
            return getProductionArrayList(f);
        } catch (IOException ex) {
            System.out.println("Error en la lectura del archivo");
            Logger.getLogger(Txt_gramatica_parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Fase 1: tomar los renglones del archivo .txt y almacenar cada uno de ellos en un ArrayList<String>
    private static ArrayList<String> getProductionArrayList_(FileReader file) throws IOException {
        ArrayList<String> producciones = new ArrayList<>();
        BufferedReader b = new BufferedReader(file);

        String readedString;
        while ((readedString = b.readLine()) != null) { //cuando b.readLine() == null significa que se ha encontrado un salto de linea (retorno de carro —\n—)
            producciones.add(readedString);
        }

        System.out.println("Carga FASE 1 de producciones a ArrayList desde .txt completa");
        return producciones;
    }

    //Fase 2: tomar el ArrayList<String> creado en la fase 1 y depurar cada elemento de este ArrayList;
    // la depuración consiste simplemente en eliminar el indicativo de ordinalidad de cada producción
    // y obtener de cada producción. la expresión del lado derecho y a del lado izquierdo.
    private static ArrayList<Produccion> getProductionArrayList(FileReader file) throws IOException {
        ArrayList<String> ap = getProductionArrayList_(file);
        ArrayList<Produccion> arrayProducciones = new ArrayList<>();

        for (Object o : ap) {
            String prod = (String) o;
            prod = prod.replaceAll(" ", "");
            
            /*
                USO DE REGEX (\\d\\.)((.*)->(.*))
                    Este regEx está compuesto por 5 grupos, a saber:
                        GRUPO 0: está compuesto por todo lo que haga match con el regEx (podría verse como la concatenación de todos los grupos)
                        GRUPO 1: (\\d+\\.) compuesto por UNA O MÁS cantidad de dígitos que estén seguidos por UN caracter punto
                        GRUPO 2: ((.*)->(.*)) compuesto por la concatenación del grupo 3, la flecha, y el grupo 4, en ese orden
                        GRUPO 3: primer (.*) [EXPRESIÓN LADO IZQUIERDO] compuesto por todo lo que esté entre el grupo anterior (grupo 1) y la flecha ( -> )
                        GRUPO 4: segundo (.*) [EXPRESIÓN LADO DERECHO] compuesto por  lo que esté luego de la flecha
             */
            String pattern = "(\\d+\\.)((.*)->(.*))";
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(prod);
            if (m.matches()) {
                Produccion produccion = new Produccion(
                        m.group(2),
                        new Expresion(m.group(3)),
                        new Expresion(m.group(4)));
                arrayProducciones.add(produccion);
            }
        }
        System.out.println("Carga FASE 2 de producciones a ArrayList desde .txt completa");
        return arrayProducciones;
    }
}
