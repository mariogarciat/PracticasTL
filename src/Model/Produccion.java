/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author mario.garciat
 */
public class Produccion {
    
    private String produccion;
    private Expresion ladoIzq;
    private Expresion ladoDer;

    public Produccion(String produccion, Expresion ladoIzq, Expresion ladoDer) {
        this.produccion = produccion;
        this.ladoIzq = ladoIzq;
        this.ladoDer = ladoDer;
        
    }
}
