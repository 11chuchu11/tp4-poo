package swing;

import controllers.PeliculasController;
import dtos.PeliculaDTO;
import types.TipoGenero;
import types.TipoProyeccion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultarPeliculaPorGeneroView extends JFrame {
    private final JPanel contentPane;
    private final JComboBox<TipoGenero> comboBoxGenero;
    private final JComboBox<TipoProyeccion> comboBoxTipo;
    private final JTextField textFieldDuracion;
    private final JTextField textFieldDirector;
    private final JTextField textFieldActores;
    private final JTextArea textAreaPeliculas;
    private final JButton btnFiltrar;
    private final JButton btnCerrar;

    public ConsultarPeliculaPorGeneroView() {
        setTitle("Consultar Películas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));

        JPanel panelFiltros = new JPanel(new GridLayout(3, 4, 10, 10));

        panelFiltros.add(new JLabel("Género:"));
        comboBoxGenero = new JComboBox<>(TipoGenero.values());
        panelFiltros.add(comboBoxGenero);

        panelFiltros.add(new JLabel("Tipo Proyección:"));
        comboBoxTipo = new JComboBox<>(TipoProyeccion.values());
        panelFiltros.add(comboBoxTipo);

        panelFiltros.add(new JLabel("Duración máx (min):"));
        textFieldDuracion = new JTextField();
        panelFiltros.add(textFieldDuracion);

        panelFiltros.add(new JLabel("Director:"));
        textFieldDirector = new JTextField();
        panelFiltros.add(textFieldDirector);

        panelFiltros.add(new JLabel("Actor (contiene):"));
        textFieldActores = new JTextField();
        panelFiltros.add(textFieldActores);

        btnFiltrar = new JButton("Filtrar");
        panelFiltros.add(btnFiltrar);

        contentPane.add(panelFiltros, BorderLayout.NORTH);

        textAreaPeliculas = new JTextArea();
        textAreaPeliculas.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaPeliculas);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnCerrar);
        contentPane.add(panelBoton, BorderLayout.SOUTH);

        btnFiltrar.addActionListener(e -> mostrarPeliculas());

        // Mostrar todas al abrir
        mostrarPeliculas();
    }

    private void mostrarPeliculas() {
        TipoGenero genero = (TipoGenero) comboBoxGenero.getSelectedItem();
        TipoProyeccion tipo = (TipoProyeccion) comboBoxTipo.getSelectedItem();
        String duracionStr = textFieldDuracion.getText().trim();
        String director = textFieldDirector.getText().trim().toLowerCase();
        String actor = textFieldActores.getText().trim().toLowerCase();

        List<PeliculaDTO> peliculas = PeliculasController.getINSTANCE().buscarPeliculasPorGenero(genero);

        if (tipo != null) {
            peliculas = peliculas.stream()
                    .filter(p -> p.getTipo() == tipo)
                    .collect(Collectors.toList());
        }

        if (!duracionStr.isEmpty()) {
            try {
                int duracionMax = Integer.parseInt(duracionStr);
                peliculas = peliculas.stream()
                        .filter(p -> p.getDuracionEnMinutos() <= duracionMax)
                        .collect(Collectors.toList());
            } catch (NumberFormatException ignored) {}
        }

        if (!director.isEmpty()) {
            peliculas = peliculas.stream()
                    .filter(p -> p.getDirector() != null && p.getDirector().toLowerCase().contains(director))
                    .collect(Collectors.toList());
        }

        if (!actor.isEmpty()) {
            peliculas = peliculas.stream()
                    .filter(p -> p.getActores() != null && p.getActores().stream()
                            .anyMatch(a -> a != null && a.toLowerCase().contains(actor)))
                    .collect(Collectors.toList());
        }

        StringBuilder sb = new StringBuilder();
        for (PeliculaDTO p : peliculas) {
            sb.append("Nombre: ").append(p.getNombrePelicula())
              .append(", Director: ").append(p.getDirector())
              .append(", Duración: ").append(p.getDuracionEnMinutos())
              .append(", Tipo: ").append(p.getTipo());
            if (p.getActores() != null && !p.getActores().isEmpty()) {
                sb.append(", Actores: ").append(String.join(", ", p.getActores()));
            }
            sb.append("\n");
        }
        textAreaPeliculas.setText(sb.toString());
    }
}