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
    
    public AdminPalabraSecreta PalabraSecrata;

    public JuegoAhorcadoAzar(AdminPalabraSecreta PalabraSecrata) throws PalabraNoValidaException {
        super();
        this.PalabraSecrata = PalabraSecrata;
        inicializarPalabraSecreta();
    } 

    public void actualizarPalabraActual(char letra) {
        char letraMayus = Character.toUpperCase(letra);
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                palabraActual[i] = letra;
            }
        }
    }

    @Override
    public boolean verificarLetra(char letra) {
        return palabraSecreta.indexOf(letra) >= 0;
    }

    @Override
    public boolean hasGanado() {
        for (char c : palabraActual) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    @Override
    public void inicializarPalabraSecreta() throws PalabraNoValidaException {
        this.palabraSecreta = PalabraSecrata.ObtenerPalabraAlAzar();

        palabraActual = new char[palabraSecreta.length()];
        for (int i = 0; i < palabraSecreta.length(); i++) {
            palabraActual[i] = '_';
        }
        letraUsadas.clear();
        intentos = limiteIntentos;
    }

    
    @Override
    public void jugar(String palabra) throws PalabraNoValidaException {
        this.PalabraSecrata = PalabraSecrata;
        inicializarPalabraSecreta();
    }
}
