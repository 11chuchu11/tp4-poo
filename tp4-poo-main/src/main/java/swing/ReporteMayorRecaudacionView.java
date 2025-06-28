package swing;

import controllers.PeliculasController;
import dtos.PeliculaDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReporteMayorRecaudacionView extends JFrame {
    private final JPanel contentPane;
    private final JTextArea textAreaReporte;
    private final JButton btnCerrar;

    public ReporteMayorRecaudacionView() {
        setTitle("Reporte de Mayor Recaudación");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));

        textAreaReporte = new JTextArea();
        textAreaReporte.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaReporte);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnCerrar);
        contentPane.add(panelBoton, BorderLayout.SOUTH);

        mostrarReporte();
    }

    private void mostrarReporte() {
        PeliculaDTO pelicula = PeliculasController.getINSTANCE().buscarPeliculaConMasRecaudacion();
        if (pelicula != null) {
            textAreaReporte.setText("Película con mayor recaudación:\n\n" +
                    "Nombre: " + pelicula.getNombrePelicula() + "\n" +
                    "Director: " + pelicula.getDirector() + "\n" +
                    "Género: " + pelicula.getGenero() + "\n" +
                    "Tipo: " + pelicula.getTipo() + "\n");
        } else {
            textAreaReporte.setText("No hay datos de recaudación.");
        }
    }
    private void personalizarColores() {
        contentPane.setBackground(new Color(245, 245, 245)); // Fondo general
        textAreaReporte.setBackground(new Color(230, 240, 255)); // Fondo del área de texto
        textAreaReporte.setForeground(new Color(33, 37, 41)); // Texto oscuro
        btnCerrar.setBackground(new Color(100, 149, 237)); // Azul claro
        btnCerrar.setForeground(Color.WHITE); // Texto blanco
    }
}