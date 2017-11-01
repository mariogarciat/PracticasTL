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
    private String[] siguientes;
    
    public Estado(String nombre, int valida, String[] siguientes) {
        this.nombre = nombre;
        this.valida = valida;
        this.siguientes = siguientes;
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

    public String[] getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(String[] siguientes) {
        this.siguientes = siguientes;
    }
}
