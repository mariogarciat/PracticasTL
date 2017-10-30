/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mario.garciat
 */
public class Estado {
    
    private String nombre;
    private int valida;
    private String siguiente0;
    private String siguiente1;
    private String[] siguientes;
    
    //Constructor de la clase estado
//    public Estado(String nombre, String siguiente0, String siguiente1, int valida) {
//        this.nombre = nombre;
//        this.siguiente0 = siguiente0;
//        this.siguiente1 = siguiente1;
//        this.valida = valida;
//    }

    public Estado(String nombre, int valida, String[] transiciones) {
        this.nombre = nombre;
        this.valida = valida;
        this.siguientes = transiciones;
    }
   
    //Get and Set de la clase estado
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValida() {
        return valida;
    }

    public void setValida(byte valida) {
        this.valida = valida;
    }

//    public String getSiguiente0() {
//        return siguiente0;
//    }
//
//    public void setSiguiente0(String siguiente0) {
//        this.siguiente0 = siguiente0;
//    }
//
//    public String getSiguiente1() {
//        return siguiente1;
//    }
//
//    public void setSiguiente1(String siguiente1) {
//        this.siguiente1 = siguiente1;
//    }

    public String[] getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(String[] siguientes) {
        this.siguientes = siguientes;
    }

    
    
    
}
