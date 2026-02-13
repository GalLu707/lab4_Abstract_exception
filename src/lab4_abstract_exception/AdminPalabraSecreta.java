/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_abstract_exception;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author USER
 */
public final class AdminPalabraSecreta {
    private static final AdminPalabraSecreta INSTANCE = new AdminPalabraSecreta();
    public final ArrayList<String> palabraSecreta;
    public final Random aleatorio;
    
    private AdminPalabraSecreta(){
        palabraSecreta = new ArrayList<>();
        aleatorio = new Random();
        palabraSecreta.add("MANZANA");
        palabraSecreta.add("MESA");
        palabraSecreta.add("TELEFONO");
        palabraSecreta.add("CARRO");
        palabraSecreta.add("AMARILLO");
        palabraSecreta.add("CEBOLLA");
        palabraSecreta.add("PLUMA");
        palabraSecreta.add("BOTELLA");
    }
    
    public static AdminPalabraSecreta getInstance(){
        return INSTANCE;
    }
    
    public boolean AgregarPalabra(String Palabra){
        if(Palabra == null || Palabra.isEmpty()){
            return false;
        }
        for (String s : palabraSecreta) {
            if (s.equalsIgnoreCase(Palabra)) {
                return false;
            }
        }
        palabraSecreta.add(Palabra.toUpperCase());
        return true;
    }
    
    public String ObtenerPalabraAlAzar(){
        if(palabraSecreta.isEmpty()){
            return "";
        }
        int i = aleatorio.nextInt(palabraSecreta.size());
        return palabraSecreta.get(i);
    }
}
