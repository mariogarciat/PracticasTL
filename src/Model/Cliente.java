package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Cliente {
    public static void main(String[] args) throws Exception {
        String rutaProyecto = System.getProperty("user.dir") + "\\pruebas";
        System.out.println(rutaProyecto);
        File archivo = crearArchivo(rutaProyecto, "textoPrueba");
        escribirArchivo(archivo);
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
