package swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroFuncionPorGeneroView extends JFrame {
    private final JPanel contentPane;
    private final JTextField textFieldFecha;
    private final JTextField textFieldHora;
    private final JComboBox<String> comboBoxGenero;
    private final JComboBox<String> comboBoxPelicula;
    private final JComboBox<String> comboBoxSala;

    public RegistroFuncionPorGeneroView() {
        setTitle("Registrar Nueva Función por Género");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblGenero = new JLabel("Género:");
        lblGenero.setBounds(10, 10, 100, 20);
        contentPane.add(lblGenero);

        comboBoxGenero = new JComboBox<>();
        comboBoxGenero.setBounds(120, 10, 200, 20);
        contentPane.add(comboBoxGenero);

        JLabel lblPelicula = new JLabel("Película:");
        lblPelicula.setBounds(10, 40, 100, 20);
        contentPane.add(lblPelicula);

        comboBoxPelicula = new JComboBox<>();
        comboBoxPelicula.setBounds(120, 40, 200, 20);
        contentPane.add(comboBoxPelicula);

        JLabel lblSala = new JLabel("Sala:");
        lblSala.setBounds(10, 70, 100, 20);
        contentPane.add(lblSala);

        comboBoxSala = new JComboBox<>();
        comboBoxSala.setBounds(120, 70, 200, 20);
        contentPane.add(comboBoxSala);

        JLabel lblFecha = new JLabel("Fecha (dd/mm/yyyy):");
        lblFecha.setBounds(10, 100, 150, 20);
        contentPane.add(lblFecha);

        textFieldFecha = new JTextField();
        textFieldFecha.setBounds(170, 100, 150, 20);
        contentPane.add(textFieldFecha);

        JLabel lblHora = new JLabel("Hora (hh:mm):");
        lblHora.setBounds(10, 130, 100, 20);
        contentPane.add(lblHora);

        textFieldHora = new JTextField();
        textFieldHora.setBounds(120, 130, 100, 20);
        contentPane.add(textFieldHora);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Función registrada (lógica a implementar)");
                limpiar();
            }
        });
        btnAceptar.setBounds(100, 200, 100, 25);
        contentPane.add(btnAceptar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        btnCancelar.setBounds(250, 200, 100, 25);
        contentPane.add(btnCancelar);
    }

    private void limpiar() {
        textFieldFecha.setText("");
        textFieldHora.setText("");
        comboBoxGenero.setSelectedIndex(-1);
        comboBoxPelicula.setSelectedIndex(-1);
        comboBoxSala.setSelectedIndex(-1);
    }
}
