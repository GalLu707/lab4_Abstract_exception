/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_abstract_exception;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 *
 * @author Nathan
 */

public class PanelAhorcado extends JPanel {

    private int errores = 0;

    private Image cabeza, cuerpo, brazoIzq, brazoDer, piernaIzq, piernaDer;

    private static final String RUTA = "/lab4_abstract_exception/Imagenes/";

    public PanelAhorcado() {
        cabeza = cargar(RUTA + "cabezap.png");
        cuerpo = cargar(RUTA + "cuerpo.png");
        brazoIzq = cargar(RUTA + "brazo izq.png");
        brazoDer = cargar(RUTA + "brazodere.png");
        piernaIzq = cargar(RUTA + "patitaizq.png");
        piernaDer = cargar(RUTA + "patitadere.png");
    }

    private Image cargar(String path) {
        URL url = getClass().getResource(path);
        if (url == null) {
            System.out.println("NO ENCONTRÉ LA IMAGEN: " + path);
            return null;
        }
        return new ImageIcon(url).getImage();
    }

    public void setErrores(int errores) {
        this.errores = errores;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Horca
        g.drawLine(50, 20, 240, 20);
        g.drawLine(145, 20, 145, 360);
        g.drawLine(50, 360, 240, 360);

        // Centro del personaje
        int centerX = 145;
        int startY = 70;

        int cabezaW = 90, cabezaH = 90;
        int cuerpoW = 110, cuerpoH = 140;
        int brazoW = 90, brazoH = 90;
        int piernaW = 90, piernaH = 90;

        // Cabeza
        if (errores >= 1 && cabeza != null) {
            g.drawImage(cabeza, centerX - cabezaW / 2, startY, cabezaW, cabezaH, this);
        }

        // Cuerpo
        if (errores >= 2 && cuerpo != null) {
            g.drawImage(cuerpo, centerX - cuerpoW / 2, startY + 70, cuerpoW, cuerpoH, this);
        }

        // Brazo izquierdo
        if (errores >= 3 && brazoIzq != null) {
            g.drawImage(brazoIzq, centerX - 95, startY + 80, brazoW, brazoH, this);
        }

        // Brazo derecho
        if (errores >= 4 && brazoDer != null) {
            g.drawImage(brazoDer, centerX + 5, startY + 80, brazoW, brazoH, this);
        }

        // Pierna izquierda
        if (errores >= 5 && piernaIzq != null) {
            g.drawImage(piernaIzq, centerX - 45, startY + 180, piernaW, piernaH, this);
        }

        // Pierna derecha
        if (errores >= 6 && piernaDer != null) {
            g.drawImage(piernaDer, centerX + 5, startY + 180, piernaW, piernaH, this);
        }
    }

}
 