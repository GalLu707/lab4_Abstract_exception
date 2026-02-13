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
        cabeza    = cargar(RUTA + "cabezap.png");
        cuerpo    = cargar(RUTA + "cuerpo.png");
        brazoIzq  = cargar(RUTA + "brazo izq.png");
        brazoDer  = cargar(RUTA + "brazodere.png");
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

        g.drawLine(50, 20, 220, 20);
        g.drawLine(130, 20, 130, 320);
        g.drawLine(50, 320, 220, 320);

        int x = getWidth() / 2 - 30;
        int y = 60;

        if (errores >= 1 && cabeza != null)    g.drawImage(cabeza, x, y, 60, 60, this);
        if (errores >= 2 && cuerpo != null)    g.drawImage(cuerpo, x, y + 60, 60, 80, this);
        if (errores >= 3 && brazoIzq != null)  g.drawImage(brazoIzq, x - 40, y + 60, 60, 60, this);
        if (errores >= 4 && brazoDer != null)  g.drawImage(brazoDer, x + 40, y + 60, 60, 60, this);
        if (errores >= 5 && piernaIzq != null) g.drawImage(piernaIzq, x, y + 130, 60, 60, this);
        if (errores >= 6 && piernaDer != null) g.drawImage(piernaDer, x + 20, y + 130, 60, 60, this);
    }
}
