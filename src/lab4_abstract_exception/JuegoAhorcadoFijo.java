/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_abstract_exception;

/**
 *
 * @author USER
 */
public class JuegoAhorcadoFijo extends JuegoAhorcadoBase {

    public JuegoAhorcadoFijo(String palabra)throws PalabraNoValidaException {
        super();
        jugar(palabra);
    }
    
    protected void actualizarPalabraActual(char letra){
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if(palabraSecreta.charAt(i)==letra){
                palabraActual[i]= letra;
            }
        }
    }
    
    protected boolean verificarLetra(char letra){
        return palabraSecreta.indexOf(letra)>=0;
    }
    
    protected boolean hasGanado(){
        for (char letra : palabraActual) {
            if (letra == '_') {
                return false;
            }
        }
        return true;
    }
    
    public void inicializarPalabraSecreta()throws PalabraNoValidaException{
        if (palabraSecreta==null||palabraSecreta.isEmpty()) {
            throw new PalabraNoValidaException("La palabra secreta no puede estar vacía.");
        }
        palabraActual=new char[palabraSecreta.length()];
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (Character.isLetter(palabraSecreta.charAt(i))) {
                palabraActual[i] = '_';
            } else {
                palabraActual[i] = palabraSecreta.charAt(i);
            }
        }
    }
    
    public void jugar(String palabra) throws PalabraNoValidaException{
        this.palabraSecreta=palabra;
        inicializarPalabraSecreta();
    }
}
