/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_abstract_exception;

/**
 *
 * @author jerem
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;

public class MenuPrincipal extends GuiBase {

    private JPanel panelPrincipal;
    private JButton btnAgregarPalabra, btnJugarAzar, btnJugarFijo, btnSalir;
    private JLabel lblTitulo;
    private JLabel lblShrek, lblMiku;

    private final Color COLOR_SHREK = new Color(110, 190, 70);
    private final Color COLOR_MIKU = new Color(57, 197, 187);
    private final Color COLOR_FONDO = new Color(230, 255, 250);

    public MenuPrincipal() {
        super("Menu Principal Ahorcado", 615, 500);
        initComponents();
    }

    public void initComponents() {
        panelPrincipal = createPanelPrincipal();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(COLOR_FONDO);

        lblTitulo = new JLabel("D:< AHORCADO SHRIKU >:D");
        lblTitulo.setBounds(0, 20, 615, 60);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(COLOR_SHREK);

        lblTitulo.setFont(new Font("Impact", Font.PLAIN, 48));

        lblTitulo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_MIKU, 2, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        panelPrincipal.add(lblTitulo);

        ImageIcon iconShrekOriginal = new ImageIcon(
                getClass().getResource("/img/shrek.png")
        );

        Image imgShrek = iconShrekOriginal.getImage().getScaledInstance(
                180, 250, Image.SCALE_SMOOTH
        );

        ImageIcon iconShrek = new ImageIcon(imgShrek);
        lblShrek = new JLabel(iconShrek);
        lblShrek.setBounds(10, 150, 180, 250);
        panelPrincipal.add(lblShrek);

        ImageIcon iconMikuOriginal = new ImageIcon(
                getClass().getResource("/img/miku.png")
        );

        Image imgMiku = iconMikuOriginal.getImage().getScaledInstance(
                180, 250, Image.SCALE_SMOOTH
        );

        ImageIcon iconMiku = new ImageIcon(imgMiku);
        lblMiku = new JLabel(iconMiku);
        lblMiku.setBounds(430, 150, 180, 250);
        panelPrincipal.add(lblMiku);

        btnAgregarPalabra = createBtn("Agregar Palabras");
        estilarBoton(btnAgregarPalabra, COLOR_MIKU);
        btnAgregarPalabra.setBounds(200, 130, 220, 50);
        panelPrincipal.add(btnAgregarPalabra);

        btnJugarAzar = createBtn("Jugar Ahorcado Azar");
        estilarBoton(btnJugarAzar, COLOR_SHREK);
        btnJugarAzar.setBounds(200, 210, 220, 50);
        panelPrincipal.add(btnJugarAzar);

        btnJugarFijo = createBtn("Jugar Ahorcado Fijo");
        estilarBoton(btnJugarFijo, COLOR_MIKU);
        btnJugarFijo.setBounds(200, 280, 220, 50);
        panelPrincipal.add(btnJugarFijo);

        btnSalir = createBtn("Salir");
        btnSalir.setBounds(260, 400, 100, 40);
        btnSalir.setBackground(Color.RED);
        btnSalir.setForeground(Color.WHITE);
        panelPrincipal.add(btnSalir);

        btnAgregarPalabra.addActionListener(e -> {
            new PalabrasSecretasGui().setVisible(true);
            dispose();
        });

        btnJugarAzar.addActionListener(e -> {
            new GuiAhorcado("AHORCADO AZAR").setVisible(true);
            dispose();
        });

        btnJugarFijo.addActionListener(e -> {
            String p = JOptionPane.showInputDialog(this, "Palabra fija:");
            if (p == null) {
                return;
            }
            p = p.trim();
            if (p.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No puedes dejar la palabra vacía.");
                return;
            }

            new GuiAhorcado("AHORCADO FIJO", p.toUpperCase()).setVisible(true);
            dispose();
        });

        btnSalir.addActionListener(e -> dispose());

        setContentPane(panelPrincipal);
    }

    private void estilarBoton(JButton btn, Color colorFondo) {
        btn.setBackground(colorFondo);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    public static void main(String[] args) {
        new MenuPrincipal().setVisible(true);
    }
}
