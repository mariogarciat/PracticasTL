/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author mario.garciat
 */
public class Automata {
    
    private ArrayList<String> simbolos;
    private ArrayList<String> estados;
    private ArrayList<Integer> aceptacion;
    private ArrayList<Integer> validaNuevos;
    private ArrayList<ArrayList<String>> transicionesNuevas;
    private ArrayList<ArrayList<String>> transiciones;
    private ArrayList<String> estadosNuevos;
    private int columnas;
    
    //Constructor de la clase Autómata
    public Automata(ArrayList<String> estados, ArrayList<String> simbolos, ArrayList<Integer> aceptacion, ArrayList<ArrayList<String>> transiciones) {

        columnas = transiciones.get(0).size();

        this.estados = estados;
        this.aceptacion = aceptacion;
        this.transiciones = transiciones;
        this.simbolos = simbolos;

        transicionesNuevas = new ArrayList();
        transicionesNuevas.add(this.transiciones.get(0));
        estadosNuevos = new ArrayList<String>();
        estadosNuevos.add(this.estados.get(0));
        validaNuevos = new ArrayList<Integer>();
        validaNuevos.add(this.aceptacion.get(0));
    }
    
    //Verifíca si un autómata es Vacio 
    //Retorna true en caso de que sea vacío, false de lo contrario
    public boolean esVacio() {
        if (estados.size() == 0) {
            return true;
        }
        return false;
    }
    
    //Almacena en una variable de tipo String llamada datos la información
    //Correspondiente a un autómata creado apartir de instancias de la clase
    //Estado y almacenado en un ArrayLIst al cual llamamos arreglo
    public String mostrar(String datos, ArrayList<Estado> arreglo){
        for (int i = 0; i < arreglo.size(); i++) {
            datos = datos + "Estado: --------------- " + arreglo.get(i).getNombre();
            datos = datos + "\nSiguiente con 0: --- " + arreglo.get(i).getSiguiente0();
            datos = datos + "\nSiguiente con 1: --- " + arreglo.get(i).getSiguiente1();
            datos = datos + "\nValida: ---------------- " + arreglo.get(i).getValida() + "\n\n";
        }
        return datos;
    }
    
    //Almacena en una variable de tipo String llamada datos la información
    //Correspondiente a un autómata creado apartir de instancias de la clase
    //Automata y almacenado en un conjusnto de ArrayList a los cuales llamamos 
    //estado, transiciones y aceptacion.
    public String mostrarAutomata() {
        String datos = "";
        for (int i = 0; i < estados.size(); i++) {
            datos = datos + "Estado: --------------- " + estados.get(i);
            datos = datos + "\nTransiciones: --- " + transiciones.get(i);
            datos = datos + "\nValida: ---------------- " + aceptacion.get(i) + "\n\n";
        }
        return datos;
    }
}
