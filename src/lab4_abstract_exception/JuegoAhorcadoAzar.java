/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_abstract_exception;

/**
 *
 * @author USER
 */
public class JuegoAhorcadoAzar extends JuegoAhorcadoBase{
    public AdminPalabrasSecretas PalabraSecreta;

    public JuegoAhorcadoAzar(AdminPalabrasSecretas PalabraSecreta)throws PalabraNoValidaException {
        this.PalabraSecreta = PalabraSecreta;
        inicializarPalabraSecreta();
    }
    
    public void ActualizarPalabraActual(char letra){
     char letraMayus=Character.toUpperCase(letra);
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i)==letra) {
                palabraActual[i]=letra;
            }
        }
    }
    
    public boolean verificarLetra(char letra){
        return palabraSecreta.indexOf(letra)>=0;
    }
    
    public boolean hasGanado(){
        for (char c : palabraActual) {
            if (c=='_') {
                return false;
            }
        }
        return true;
    }
    
    public void inicializarPalabraSecreta()throws PalabraNoValidaException{
        this.palabraSecreta=PalabraSecreta.ObtenerPalabraAzar();
        palabraActual=new char[palabrasSecreta.length()];
        for (int i = 0; i < palabraSecreta.length();i++) {
            palabraActual[i]='_';
        }
        letraUsadas.clear();
        intentos=limiteIntentos;
    }
    
    public void jugar(String palabra)throws PalabraNoValidaException{
        this.PalabraSecreta=PalabraSecreta;
        inicializarPalabraSecreta();
    }
}
