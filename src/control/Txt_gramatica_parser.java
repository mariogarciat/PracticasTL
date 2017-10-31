package control;

import Model.Expresion;
import Model.Produccion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Txt_gramatica_parser {

    /* --------------------- CAPTURA DE ARCHIVOS (FILES)  --------------------- */
    public static ArrayList<Produccion> getProducciones(File file) throws Exception {

        FileReader f = new FileReader(file);
        return getProductionArrayList(f);
    }

    public static ArrayList getProductionArrayList(String path) throws Exception {

        FileReader f = new FileReader(path);
        return getProductionArrayList(f);
    }

    // Fase 1: tomar los renglones del archivo .txt y almacenar cada uno de ellos en un ArrayList<String>
    public static ArrayList<String> getRenglones(FileReader file) throws IOException {
        ArrayList<String> producciones = new ArrayList<>();
        BufferedReader b = new BufferedReader(file);

        String readedString;
        while ((readedString = b.readLine()) != null) { //cuando b.readLine() == null significa que se ha encontrado un salto de linea (retorno de carro —\n—)
            producciones.add(readedString);
        }
        return producciones;
    }
    
    //Fase 2: tomar el ArrayList<String> creado en la fase 1 y depurar cada elemento de este ArrayList;
    // la depuración consiste simplemente en eliminar el indicativo de ordinalidad de cada producción
    // y obtener de cada producción. la expresión del lado derecho y a del lado izquierdo.
    private static ArrayList<Produccion> getProductionArrayList(FileReader file) throws IOException {
        ArrayList<String> ap = getRenglones(file);
        ArrayList<Produccion> arrayProducciones = new ArrayList<>();

        for (Object o : ap) {
            String prod = (String) o;
            prod = prod.replaceAll(" ", "");
            
            /*
                USO DE REGEX ((.*)->(.*))
                    Este regEx está compuesto por 5 grupos, a saber:
                        GRUPO 0: está compuesto por todo lo que haga match con el regEx (podría verse como la concatenación de todos los grupos)
                        GRUPO 1: ((.*)->(.*)) compuesto por la concatenación del grupo 3, la flecha, y el grupo 4, en ese orden
                        GRUPO 2: primer (.*) [EXPRESIÓN LADO IZQUIERDO] compuesto por todo lo que esté entre el grupo anterior (grupo 1) y la flecha ( -> )
                        GRUPO 3: segundo (.*) [EXPRESIÓN LADO DERECHO] compuesto por  lo que esté luego de la flecha
             */
            String pattern = "((.*)->(.*))";
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(prod);
            if (m.matches()) {
                Produccion produccion = new Produccion(
                        m.group(1),
                        new Expresion(m.group(2)),
                        new Expresion(m.group(3)));
                arrayProducciones.add(produccion);
            }
        }
        return arrayProducciones;
    }
}
