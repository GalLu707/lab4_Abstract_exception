/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_abstract_exception;

import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class JuegoAhorcadoBase implements JuegoAhorcado {
    protected String palabraSecreta;
    protected char[] palabraActual;
    protected int intentos;
    protected final int limiteIntentos=6;
    protected ArrayList<Character> letraUsadas;
    protected ArrayList<String> figuraAhorcado;
    
    protected JuegoAhorcadoBase(){
        letraUsadas=new ArrayList<>();
        figuraAhorcado=new ArrayList<>();
        crearFigura();
        intentos=limiteIntentos;
    }
    
    protected void crearFigura(){
        
    }
    
    
    protected abstract void actualizarPalabraActual(char letra);
    protected abstract boolean verificarLetra(char letra);
    protected abstract boolean hasGanado();
}
