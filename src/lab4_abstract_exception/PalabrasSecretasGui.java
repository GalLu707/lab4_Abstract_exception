/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_abstract_exception;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class PalabrasSecretasGui extends GuiBase {

    private JPanel panelPrincipal;
    private JButton btnAgregarPalabra, btnSalir;
    private JLabel lblTitulo, lblNueva;
    private JScrollPane palabrasActuales;
    private JTextField txtPalabra;
    private DefaultTableModel modelo;
    private final AdminPalabraSecreta admin = AdminPalabraSecreta.getInstance();

    public PalabrasSecretasGui() {
        super("administrar palabras secretas ", 615, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();

    }

    public void initComponents() {
        panelPrincipal = createPanelPrincipal();
        panelPrincipal.setLayout(null);

        lblTitulo = createLabelTitle("palabras secretas", 115, 20, 400, 50);
        panelPrincipal.add(lblTitulo);

        lblNueva = createLabel("nueva palabra:", 60, 170, 270, 40);
        panelPrincipal.add(lblNueva);

        txtPalabra = createTextField(60, 170, 270, 40);
        panelPrincipal.add(txtPalabra);

        btnAgregarPalabra = createBtn("Agregar Palabra");
        btnAgregarPalabra.setBounds(350, 170, 160, 40);
        panelPrincipal.add(btnAgregarPalabra);

        palabrasActuales = createTable(new String[]{"palabra"}, new Object[][]{}, 28);
        palabrasActuales.setBounds(60, 240, 450, 180);
        panelPrincipal.add(palabrasActuales);

        JTable t = (JTable) palabrasActuales.getViewport().getView();
        modelo = new DefaultTableModel(new Object[]{"palabra"}, 0);
        t.setModel(modelo);
        admin.palabraSecreta.forEach(e -> modelo.addRow(new Object[]{e}));

        btnSalir = createBtn("salir");
        btnSalir.setBounds(470, 440, 80, 30);
        btnSalir.setBackground(Color.green);
        panelPrincipal.add(btnSalir);

        btnAgregarPalabra.addActionListener(e -> {
            String p = txtPalabra.getText().trim();
            if (admin.AgregarPalabra(p)) {
                modelo.addRow(new Object[]{p.toUpperCase()});
                txtPalabra.setText("");
                txtPalabra.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "no se pudo agregar", "AVISO", JOptionPane.WARNING_MESSAGE);
            }
        });
        txtPalabra.addActionListener(e -> btnAgregarPalabra.doClick());
        btnSalir.addActionListener(e -> {
            new MenuPrincipal().setVisible(true);
            dispose();

        });

        setContentPane(panelPrincipal);

    }

    public static void main(String[] args) {
        new PalabrasSecretasGui().setVisible(true);
    }

}
