package swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroPeliculaPorGeneroView extends JFrame {
    private JPanel contentPane;
    private JTextField textFieldNombre;
    private JTextField textFieldDirector;
    private JTextField textFieldDuracion;
    private JTextField textFieldActores;
    private JComboBox<String> comboBoxGenero;
    private JComboBox<String> comboBoxTipoProyeccion;

    public RegistroPeliculaPorGeneroView() {
        setTitle("Registrar Película por Género");
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

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 40, 100, 20);
        contentPane.add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(120, 40, 200, 20);
        contentPane.add(textFieldNombre);

        JLabel lblDirector = new JLabel("Director:");
        lblDirector.setBounds(10, 70, 100, 20);
        contentPane.add(lblDirector);

        textFieldDirector = new JTextField();
        textFieldDirector.setBounds(120, 70, 200, 20);
        contentPane.add(textFieldDirector);

        JLabel lblDuracion = new JLabel("Duración (min):");
        lblDuracion.setBounds(10, 100, 100, 20);
        contentPane.add(lblDuracion);

        textFieldDuracion = new JTextField();
        textFieldDuracion.setBounds(120, 100, 200, 20);
        contentPane.add(textFieldDuracion);

        JLabel lblActores = new JLabel("Actores (separados por coma):");
        lblActores.setBounds(10, 130, 200, 20);
        contentPane.add(lblActores);

        textFieldActores = new JTextField();
        textFieldActores.setBounds(210, 130, 200, 20);
        contentPane.add(textFieldActores);

        JLabel lblTipoProyeccion = new JLabel("Tipo Proyección:");
        lblTipoProyeccion.setBounds(10, 160, 120, 20);
        contentPane.add(lblTipoProyeccion);

        comboBoxTipoProyeccion = new JComboBox<>();
        comboBoxTipoProyeccion.setBounds(140, 160, 180, 20);
        contentPane.add(comboBoxTipoProyeccion);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Película registrada (lógica a implementar)");
                limpiar();
            }
        });
        btnAceptar.setBounds(100, 220, 100, 25);
        contentPane.add(btnAceptar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        btnCancelar.setBounds(250, 220, 100, 25);
        contentPane.add(btnCancelar);
    }

    private void limpiar() {
        textFieldNombre.setText("");
        textFieldDirector.setText("");
        textFieldDuracion.setText("");
        textFieldActores.setText("");
        comboBoxGenero.setSelectedIndex(-1);
        comboBoxTipoProyeccion.setSelectedIndex(-1);
    }
}
