/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1compiladores;

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
    
    private JFrame mainFrame;
    private String choosenFileName;
    
    public String abrirArchivo(){
        String fileName = "";
        JFileChooser  fileDialog = new JFileChooser();
        int returnVal = fileDialog.showOpenDialog(mainFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            choosenFileName = fileDialog.getSelectedFile().getName();
            fileName = fileDialog.getSelectedFile().getAbsolutePath();
               
        } else { 
           fileName = "";
        }
        return fileName;
    }
    
    public String leerDatos(String delimiter) throws FileNotFoundException, IOException{
        String filePath = abrirArchivo();
        InputStream in = null;
        BufferedInputStream s;
        BufferedReader myInput;
        StringTokenizer st;
        double dato = 0;
        try{
            in = new FileInputStream(filePath);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No ha abierto ningun archivo");
            return null;
        }
        String thisLine;
        s = new BufferedInputStream(in);
        myInput = new BufferedReader(new InputStreamReader(s));
        while ((thisLine = myInput.readLine()) != null) {
            st = new StringTokenizer(thisLine, delimiter);
            while(st.hasMoreElements()){
                try{
                    dato = Double.parseDouble((String) st.nextElement());
                } catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null,"Error al cargar los datos.\n Formato erròneo\nPor favor verifique el archivo e intente nuevamente :)");
                    ListaLigada leerDatos = leerDatos(delimiter);
                    return leerDatos;
                }                
                value = new Nodo(dato);
                values.addNodo(value);
            }
        }
        return(values);
    }
    
}
