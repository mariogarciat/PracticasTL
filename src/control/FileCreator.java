package control;

import Model.Produccion;
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

    public static File escribirArchivo(File file, ArrayList<Produccion> producciones) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(producciones.get(0).getProduccion());
        for (int i = 1; i < producciones.size(); i++) {
            fw.write("\r\n");
            fw.write(producciones.get(i).getProduccion());
        }
        fw.close();
        
        return file;
    }

    public static File escribirArchivo(File file, String texto) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(texto);
        fw.close();
        return file;
    }
}
