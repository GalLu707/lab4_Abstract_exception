/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_abstract_exception;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 * GUI para el juego del Ahorcado.
 * Depende de:
 *  - BaseGUI (clase auxiliar para crear labels/buttons/panels)
 *  - JuegoAhorcadoBase, JuegoAhorcadoFijo, JuegoAhorcadoAzar
 *  - AdminPalabrasSecretas (Singleton)
 *  - MenuPrincipal (para volver al menú)
 */

public class GuiAhorcado extends GuiBase {

    private JPanel panelPrincipal;
    private JLabel lblTitulo, lblIntentos, lblPalabraActual;
    private JButton btnAdivinar, btnNuevoJuego, btnSalir;
    private JTextField txtLetra;
    private JTextArea taFigura;
    private JScrollPane spRepetidas;
    private DefaultTableModel modeloRepetidas;

    private final String modo;
    private JuegoAhorcadoBase juego;
    private final String palabraFija;

    // 🎨 COLORES TEMA
    private final Color COLOR_SHREK = new Color(110, 190, 70);
    private final Color COLOR_MIKU = new Color(57, 197, 187);
    private final Color COLOR_FONDO = new Color(230, 255, 250);

    public GuiAhorcado(String modo) {
        super("Ahorcado Shriku", 770, 560);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.modo = modo;
        this.palabraFija = null;
        initComponents();
        iniciarJuego();
        render();
    }

    public GuiAhorcado(String modo, String palabraFija) {
        super("Ahorcado Shriku", 770, 560);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.modo = modo;
        this.palabraFija = palabraFija;
        initComponents();
        iniciarJuego();
        render();
    }

    private void iniciarJuego() {
        try {
            if (modo != null && modo.toUpperCase().contains("AZAR")) {
                //juego = new JuegoAhorcadoAzar(AdminPalabrasSecretas.getInstance());
            } else {
                String p = (palabraFija == null) ? "" : palabraFija.toUpperCase();
                juego = new JuegoAhorcadoFijo(p);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al iniciar el juego: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            juego = null;
            if (btnAdivinar != null) btnAdivinar.setEnabled(false);
        }
    }

    public void initComponents() {

    panelPrincipal = createPanelPrincipal();
    panelPrincipal.setLayout(null);
    panelPrincipal.setBackground(new Color(230, 255, 250));
    setContentPane(panelPrincipal);

    // 🎨 COLORES
    Color COLOR_SHREK = new Color(110, 190, 70);
    Color COLOR_MIKU = new Color(57, 197, 187);

    // 🏆 TÍTULO SUPERIOR
    lblTitulo = createLabelTitle(
            modo == null ? "AHORCADO ARENA" : modo,
            200, 10, 370, 50);
    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
    lblTitulo.setForeground(COLOR_SHREK);
    panelPrincipal.add(lblTitulo);

    // 🟢 PANEL IZQUIERDO - FIGURA
    JPanel panelFigura = new JPanel();
    panelFigura.setLayout(null);
    panelFigura.setBounds(30, 120, 250, 330);
    panelFigura.setBackground(new Color(220, 245, 220));
    panelFigura.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_SHREK, 3),
            "Zona Shrek",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            COLOR_SHREK));
    panelPrincipal.add(panelFigura);

    taFigura = new JTextArea();
    taFigura.setBounds(25, 40, 200, 260);
    taFigura.setEditable(false);
    taFigura.setFont(new Font("Monospaced", Font.BOLD, 13));
    taFigura.setBackground(Color.WHITE);
    panelFigura.add(taFigura);

    // 🟦 PANEL DERECHO - LETRAS REPETIDAS
    JPanel panelRepetidas = new JPanel();
    panelRepetidas.setLayout(null);
    panelRepetidas.setBounds(490, 120, 250, 330);
    panelRepetidas.setBackground(new Color(220, 250, 255));
    panelRepetidas.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_MIKU, 3),
            "Zona Miku",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            COLOR_MIKU));
    panelPrincipal.add(panelRepetidas);

    spRepetidas = createTable(
            new String[]{"Letras usadas"},
            new Object[][]{}, 28);

    spRepetidas.setBounds(25, 40, 200, 260);
    JTable t = (JTable) spRepetidas.getViewport().getView();
    modeloRepetidas = new DefaultTableModel(
            new Object[]{"Letras usadas"}, 0);
    t.setModel(modeloRepetidas);
    t.setFont(new Font("SansSerif", Font.BOLD, 14));
    panelRepetidas.add(spRepetidas);

    // 🎯 CENTRO - PALABRA
    lblPalabraActual = createLabel("_ _ _ _ _ _ _", 300, 150, 260, 60);
    lblPalabraActual.setHorizontalAlignment(SwingConstants.CENTER);
    lblPalabraActual.setFont(new Font("Arial", Font.BOLD, 34));
    lblPalabraActual.setForeground(COLOR_MIKU);
    panelPrincipal.add(lblPalabraActual);

    // Intentos
    lblIntentos = createLabel("Intentos restantes: ", 300, 90, 300, 30);
    lblIntentos.setFont(new Font("SansSerif", Font.BOLD, 16));
    lblIntentos.setForeground(COLOR_SHREK);
    panelPrincipal.add(lblIntentos);

    // ✏️ INPUT LETRA
    txtLetra = createTextField(380, 240, 60, 60);
    txtLetra.setHorizontalAlignment(JTextField.CENTER);
    txtLetra.setFont(new Font("Arial", Font.BOLD, 28));
    txtLetra.setBorder(BorderFactory.createLineBorder(COLOR_MIKU, 3));
    panelPrincipal.add(txtLetra);

    // 🎯 BOTÓN ADIVINAR
    btnAdivinar = createBtn("Confirmar Letra");
    btnAdivinar.setBounds(310, 310, 180, 45);
    estilizarBoton(btnAdivinar, COLOR_SHREK);
    panelPrincipal.add(btnAdivinar);

    // 🔄 NUEVO JUEGO
    btnNuevoJuego = createBtn("Reiniciar Arena");
    btnNuevoJuego.setBounds(200, 460, 180, 45);
    estilizarBoton(btnNuevoJuego, COLOR_MIKU);
    panelPrincipal.add(btnNuevoJuego);

    // 🚪 SALIR
    btnSalir = createBtn("Salir");
    btnSalir.setBounds(420, 460, 150, 45);
    btnSalir.setBackground(Color.RED);
    btnSalir.setForeground(Color.WHITE);
    panelPrincipal.add(btnSalir);

    // LISTENERS (NO TOCAMOS LÓGICA)
    btnSalir.addActionListener(e -> {
        new MenuPrincipal().setVisible(true);
        dispose();
    });

    btnNuevoJuego.addActionListener(e -> {
        if (modo != null && modo.toUpperCase().contains("AZAR")) {
            new GuiAhorcado(modo).setVisible(true);
        } else {
            String nueva = JOptionPane.showInputDialog(this,
                    "Ingrese la nueva palabra:");
            if (nueva != null && !nueva.equals("")) {
                new GuiAhorcado(modo, nueva).setVisible(true);
            } else {
                return;
            }
        }
        dispose();
    });

    btnAdivinar.addActionListener(e -> adivinar());
    txtLetra.addActionListener(e -> adivinar());
}

    private void estilizarBoton(JButton btn, Color colorFondo) {
        btn.setBackground(colorFondo);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    // 🔹 TODA TU LÓGICA ORIGINAL INTACTA
    private void adivinar() {
        if (juego == null) return;

        String s = txtLetra.getText();
        if (s == null || s.length() == 0) {
            txtLetra.setText("");
            return;
        }

        char c = Character.toUpperCase(s.charAt(0));

        if (!Character.isLetter(c)) {
            txtLetra.setText("");
            return;
        }

        if (juego.letraUsadas.contains(c)) {
            if (!yaListado(c)) {
                modeloRepetidas.addRow(new Object[]{String.valueOf(c)});
            }
        } else {
            juego.letraUsadas.add(c);

            boolean ok = juego.verificarLetra(c);
            if (ok) {
                juego.actualizarPalabraActual(c);
            } else {
                juego.intentos = Math.max(0, juego.intentos - 1);
                if (!yaListado(c)) {
                    modeloRepetidas.addRow(new Object[]{String.valueOf(c)});
                }
            }
        }

        txtLetra.setText("");
        render();

        if (juego.hasGanado()) {
            JOptionPane.showMessageDialog(this,
                    "¡VICTORIA SHREK x MIKU! 🏆\nLa palabra era: "
                            + juego.palabraSecreta,
                    "Ahorcado",
                    JOptionPane.INFORMATION_MESSAGE);
            btnAdivinar.setEnabled(false);
        } else if (juego.intentos == 0) {
            JOptionPane.showMessageDialog(this,
                    "DERROTA 💀\nLa palabra era: "
                            + juego.palabraSecreta,
                    "Ahorcado",
                    JOptionPane.ERROR_MESSAGE);
            btnAdivinar.setEnabled(false);
        }
    }

    private boolean yaListado(char c) {
        for (int i = 0; i < modeloRepetidas.getRowCount(); i++) {
            if (modeloRepetidas.getValueAt(i, 0)
                    .toString().equalsIgnoreCase(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }

    private void render() {
        if (juego == null) return;
        lblIntentos.setText("Intentos restantes: "
                + juego.intentos + "/" + juego.limiteIntentos);
        lblPalabraActual.setText(formatearPalabra(juego.palabraActual));
        int etapa = juego.limiteIntentos - juego.intentos;
        etapa = Math.max(0,
                Math.min(etapa, juego.figuraAhorcado.size() - 1));
        taFigura.setText(juego.figuraAhorcado.get(etapa));
    }

    private String formatearPalabra(char[] arr) {
        String res = "";
        for (int i = 0; i < arr.length; i++) {
            if (!res.equals("")) res = res + ' ';
            res = res + arr[i];
        }
        return res;
    }
}