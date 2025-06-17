package swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConsultarPeliculaPorGeneroView extends JFrame {
    private JPanel contentPane;
    private JComboBox<String> comboBoxGenero;
    private JTextArea textAreaPeliculas;
    private JButton btnCerrar;

    public ConsultarPeliculaPorGeneroView() {
        setTitle("Consultar Películas por Género");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());
        JLabel lblGenero = new JLabel("Género:");
        comboBoxGenero = new JComboBox<>();
        panelSuperior.add(lblGenero);
        panelSuperior.add(comboBoxGenero);
        contentPane.add(panelSuperior, BorderLayout.NORTH);

        textAreaPeliculas = new JTextArea();
        textAreaPeliculas.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaPeliculas);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnCerrar);
        contentPane.add(panelBoton, BorderLayout.SOUTH);
    }

    // Método para que el controlador pueda mostrar las películas
    public void setPeliculasPorGenero(String peliculas) {
        textAreaPeliculas.setText(peliculas);
    }

    public JComboBox<String> getComboBoxGenero() {
        return comboBoxGenero;
    }
}
