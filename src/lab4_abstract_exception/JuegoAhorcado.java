/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_abstract_exception;

/**
 *
 * @author USER
 */
//clarissa

public interface JuegoAhorcado {
    void inicializarPalabraSecreta() throws PalabraNoValidaException;
    void jugar(String palabra) throws PalabraNoValidaException; 
}

