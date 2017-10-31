package control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileCreator {

    
    // retorna 1 si se creó el archivo
    // retorna 0 si el archivo ya existe
    public static File crearArchivoTXT(String ruta, String nombreArchivo) {
        ruta = ruta + "\\" + nombreArchivo + ".txt";
        File archivo = new File(ruta);
        try {
            archivo.createNewFile();
        } catch (IOException ex) {
            System.err.println("Error en la creación de archivo");
        }
        return archivo;
    }
    
    public static void escribirArchivo(File file, ArrayList<String> producciones) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write("Jijiji");
        fw.write("\r\nJojojo");
        fw.write("\r\nJujuju");
        fw.close();
    }
}
