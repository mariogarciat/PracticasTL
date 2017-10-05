/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1compiladores;

import View.main;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author mario.garciat
 */
public class Reader {
    
    private String ruta;
    private View.main view;

    public Reader(main view) {
        this.view = view;
    }

    public Reader() {
    }
    
    

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String readFile() throws FileNotFoundException {
        
        Scanner archivo;
        view = new main();
        String ruta = "";
        JPanel panel = view.getjPanel1();
        JTextArea textArea = view.getjTextArea1();
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(panel);
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
        fc.setFileFilter(filtro);
        File fichero = fc.getSelectedFile();
        ruta = fichero.getAbsolutePath();
        
        try {
            archivo = new Scanner(new File(ruta));
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no existe en la misma carpeta del ejecutable.");
            throw ex;
        }
        String linea = "";
        String concat = "";
        if (archivo.hasNextLine()) {
            while (archivo.hasNextLine()) {
                linea = archivo.nextLine();
                concat = concat + "\n" + linea;
            }
            System.out.println(concat);
            System.out.println(view.getjButton1().getText());
            textArea.setText(concat);
        } else {
            JOptionPane.showMessageDialog(panel, "El archivo está vacío.");
        }
        return concat;
    }
    
    public String setGramatica(String path) throws FileNotFoundException{
        
        Scanner archivo;
        view = new main();
        JTextArea textArea = view.getjTextArea1();
        JPanel panel = view.getjPanel1();
        
        try {
            archivo = new Scanner(new File(ruta));
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no existe en la misma carpeta del ejecutable.");
            throw ex;
        }
        
        String linea = "";
        String concat = "";
        if (archivo.hasNextLine()) {
            while (archivo.hasNextLine()) {
                linea = archivo.nextLine();
                concat = concat + "\n" + linea;
            }
            System.out.println(concat);
            textArea.setText(concat);
        } else {
            JOptionPane.showMessageDialog(panel, "El archivo está vacío.");
        }
        return concat;
    }
    
}
