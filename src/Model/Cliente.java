package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class Cliente {
    public static void main(String[] args) throws Exception {
        Gramatica gramatica = new Gramatica("gram.txt");
        TreeSet<String> noTerminales = gramatica.getNoTerminales();
        ArrayList<String> noTerminalesOrganizados = gramatica.getNoTerminalesOrganizados();
        System.out.println("ok");
    }
    
    public static File crearArchivo(String ruta, String nombreFile) throws IOException {
        ruta = ruta + "\\" + nombreFile + ".txt";
        File archivo = new File(ruta);
        archivo.createNewFile();
        return archivo;
    }
    
    public static void escribirArchivo(File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write("Jijiji");
        fw.write("\r\nJojojo");
        fw.write("\r\nJujuju");
        fw.close();
    }
    
    
}
