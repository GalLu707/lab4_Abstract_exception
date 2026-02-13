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
                juego = new JuegoAhorcadoAzar(AdminPalabraSecreta.getInstance());
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
        panelPrincipal.setBackground(COLOR_FONDO);
        setContentPane(panelPrincipal);

        lblTitulo = createLabelTitle(
                modo == null ? "AHORCADO" : modo,
                200, 20, 400, 50);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(COLOR_SHREK);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 34));
        panelPrincipal.add(lblTitulo);

        lblIntentos = createLabel("Intentos restantes: ", 40, 100, 300, 40);
        lblIntentos.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblIntentos.setForeground(COLOR_SHREK);
        panelPrincipal.add(lblIntentos);

        lblPalabraActual = createLabel("_ _ _ _ _ _ _", 300, 150, 400, 50);
        lblPalabraActual.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblPalabraActual.setForeground(COLOR_MIKU);
        panelPrincipal.add(lblPalabraActual);

        txtLetra = createTextField(380, 220, 50, 50);
        txtLetra.setHorizontalAlignment(JTextField.CENTER);
        txtLetra.setFont(new Font("Arial", Font.BOLD, 22));
        panelPrincipal.add(txtLetra);

        btnAdivinar = createBtn("Adivinar letra");
        btnAdivinar.setBounds(350, 290, 160, 45);
        estilizarBoton(btnAdivinar, COLOR_SHREK);
        panelPrincipal.add(btnAdivinar);

        taFigura = new JTextArea();
        taFigura.setBounds(70, 150, 200, 250);
        taFigura.setEditable(false);
        taFigura.setFont(new Font("Monospaced", Font.PLAIN, 12));
        taFigura.setBackground(Color.WHITE);
        taFigura.setBorder(BorderFactory.createLineBorder(COLOR_SHREK, 2));
        panelPrincipal.add(taFigura);

        spRepetidas = createTable(new String[]{"Letras repetidas"},
                new Object[][]{}, 28);
        spRepetidas.setBounds(540, 220, 180, 200);
        JTable t = (JTable) spRepetidas.getViewport().getView();
        modeloRepetidas = new DefaultTableModel(
                new Object[]{"Letras repetidas"}, 0);
        t.setModel(modeloRepetidas);
        t.setBackground(Color.WHITE);
        t.setFont(new Font("SansSerif", Font.BOLD, 14));
        panelPrincipal.add(spRepetidas);

        btnNuevoJuego = createBtn("Nuevo Juego");
        btnNuevoJuego.setBounds(260, 470, 150, 45);
        estilizarBoton(btnNuevoJuego, COLOR_MIKU);
        panelPrincipal.add(btnNuevoJuego);

        btnSalir = createBtn("Salir");
        btnSalir.setBounds(450, 470, 150, 45);
        btnSalir.setBackground(Color.RED);
        btnSalir.setForeground(Color.WHITE);
        panelPrincipal.add(btnSalir);

        btnSalir.addActionListener(e -> {
            new MenuPrincipal().setVisible(true);
            dispose();
        });

        btnNuevoJuego.addActionListener(e -> {
            if (modo != null && modo.toUpperCase().contains("AZAR")) {
                new GuiAhorcado(modo).setVisible(true);
            } else {
                String nueva = JOptionPane.showInputDialog(this,
                        "Ingrese la nueva palabra para modo FIJO:");
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
                    "¡VICTORIA! \nLa palabra era: "
                            + juego.palabraSecreta,
                    "Ahorcado",
                    JOptionPane.INFORMATION_MESSAGE);
            btnAdivinar.setEnabled(false);
        } else if (juego.intentos == 0) {
            JOptionPane.showMessageDialog(this,
                    "DERROTA\nLa palabra era: "
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