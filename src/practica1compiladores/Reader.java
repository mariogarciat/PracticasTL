/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1compiladores;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mario.garciat
 */
public class Reader {
    
    private String ruta;

    public Reader(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public void readData() throws FileNotFoundException {
        Scanner archivo;
        try {
            archivo = new Scanner(new File(ruta));
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no existe en la misma carpeta del ejecutable.");
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        String linea;
        if (archivo.hasNextLine()) {
            while (archivo.hasNextLine()) {
                linea = archivo.nextLine();
            }
        } else {
            System.out.println("El archivo está vacío.");
        }

    }
}
