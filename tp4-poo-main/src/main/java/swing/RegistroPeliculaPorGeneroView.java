package swing;

import controllers.DescuentoController;
import controllers.PeliculasController;
import dtos.CondicionesDescuentoDTO;
import dtos.PeliculaDTO;
import types.TipoGenero;
import types.TipoProyeccion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegistroPeliculaPorGeneroView extends JFrame {
    private final JPanel contentPane;
    private final JComboBox<TipoGenero> comboBoxGenero;
    private final JTextField textFieldNombre;
    private final JTextField textFieldDuracion;
    private final JTextField textFieldDirector;
    private final JComboBox<TipoProyeccion> comboBoxTipo;
    private final JComboBox<CondicionesDescuentoDTO> comboBoxDescuento;
    private final JButton btnRegistrar;
    private final JButton btnCancelar;

    public RegistroPeliculaPorGeneroView() {
        setTitle("Registrar Película por Género");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 350); // Aumenta el ancho de la ventana
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        int labelWidth = 140;
        int fieldWidth = 350; // Aumenta el ancho de los campos
        int y = 20, yStep = 40;

        JLabel lblGenero = new JLabel("Género:");
        lblGenero.setBounds(20, y, labelWidth, 20);
        contentPane.add(lblGenero);

        comboBoxGenero = new JComboBox<>(TipoGenero.values());
        comboBoxGenero.setBounds(170, y, fieldWidth, 20);
        contentPane.add(comboBoxGenero);

        y += yStep;
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, y, labelWidth, 20);
        contentPane.add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(170, y, fieldWidth, 20);
        contentPane.add(textFieldNombre);

        y += yStep;
        JLabel lblDuracion = new JLabel("Duración (min):");
        lblDuracion.setBounds(20, y, labelWidth, 20);
        contentPane.add(lblDuracion);

        textFieldDuracion = new JTextField();
        textFieldDuracion.setBounds(170, y, fieldWidth, 20);
        contentPane.add(textFieldDuracion);

        y += yStep;
        JLabel lblDirector = new JLabel("Director:");
        lblDirector.setBounds(20, y, labelWidth, 20);
        contentPane.add(lblDirector);

        textFieldDirector = new JTextField();
        textFieldDirector.setBounds(170, y, fieldWidth, 20);
        contentPane.add(textFieldDirector);

        y += yStep;
        JLabel lblTipo = new JLabel("Tipo Proyección:");
        lblTipo.setBounds(20, y, labelWidth, 20);
        contentPane.add(lblTipo);

        comboBoxTipo = new JComboBox<>(TipoProyeccion.values());
        comboBoxTipo.setBounds(170, y, fieldWidth, 20);
        contentPane.add(comboBoxTipo);

        y += yStep;
        JLabel lblDescuento = new JLabel("Condición Descuento:");
        lblDescuento.setBounds(20, y, labelWidth, 20);
        contentPane.add(lblDescuento);

        comboBoxDescuento = new JComboBox<>();
        comboBoxDescuento.setBounds(170, y, fieldWidth, 20);
        contentPane.add(comboBoxDescuento);

        // Poblar descuentos usando solo métodos públicos
        for (int i = 1; i <= 20; i++) {
            CondicionesDescuentoDTO dto = DescuentoController.getINSTANCE().buscarDescuentoPorId(i);
            if (dto != null) comboBoxDescuento.addItem(dto);
        }

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(180, 270, 120, 30);
        btnRegistrar.addActionListener(e -> registrarPelicula());
        contentPane.add(btnRegistrar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(320, 270, 120, 30);
        btnCancelar.addActionListener(e -> dispose());
        contentPane.add(btnCancelar);
    }

    private void registrarPelicula() {
        try {
            TipoGenero genero = (TipoGenero) comboBoxGenero.getSelectedItem();
            String nombre = textFieldNombre.getText();
            String duracionStr = textFieldDuracion.getText();
            String director = textFieldDirector.getText();
            TipoProyeccion tipo = (TipoProyeccion) comboBoxTipo.getSelectedItem();
            CondicionesDescuentoDTO descuento = (CondicionesDescuentoDTO) comboBoxDescuento.getSelectedItem();

            if (genero == null || nombre.isEmpty() || duracionStr.isEmpty() || director.isEmpty() || tipo == null || descuento == null) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.");
                return;
            }

            int duracion = Integer.parseInt(duracionStr);

            PeliculaDTO pelicula = new PeliculaDTO(genero, nombre, duracion, director, tipo);
            pelicula.setCondicionesDescuento(descuento);

            int id = PeliculasController.getINSTANCE().altaNuevaPelicula(pelicula);
            if (id > 0) {
                JOptionPane.showMessageDialog(this, "Película registrada con éxito.");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la película.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos: " + ex.getMessage());
        }
    }

    private void limpiar() {
        comboBoxGenero.setSelectedIndex(-1);
        textFieldNombre.setText("");
        textFieldDuracion.setText("");
        textFieldDirector.setText("");
        comboBoxTipo.setSelectedIndex(-1);
        comboBoxDescuento.setSelectedIndex(-1);
    }
}