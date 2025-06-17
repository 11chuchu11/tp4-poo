package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(main::mostrarMenuPrincipal);
    }

    private static void mostrarMenuPrincipal() {
        JFrame menuFrame = new JFrame("Gestión de CINE BlockBuster");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(400, 350);
        menuFrame.setLayout(new GridLayout(6, 1, 10, 10));
        menuFrame.setLocationRelativeTo(null);

        JLabel bienvenida = new JLabel("Bienvenido al sistema. Elija una opción:", SwingConstants.CENTER);
        bienvenida.setFont(new Font("Arial", Font.BOLD, 14));
        menuFrame.add(bienvenida);

        JButton btnFuncion = new JButton("Registrar función por género");
        JButton btnPelicula = new JButton("Registrar película por género");
        JButton btnConsulta = new JButton("Consultar películas por género");
        JButton btnReporte = new JButton("Reporte de mayor recaudación");
        JButton btnSalir = new JButton("Salir");

        btnFuncion.addActionListener(e -> {
            menuFrame.setVisible(false);
            RegistroFuncionPorGeneroView vista = new RegistroFuncionPorGeneroView();
            vista.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    menuFrame.setVisible(true);
                }
                @Override
                public void windowClosing(WindowEvent e) {
                    menuFrame.setVisible(true);
                }
            });
            vista.setVisible(true);
        });

        btnPelicula.addActionListener(e -> {
            menuFrame.setVisible(false);
            RegistroPeliculaPorGeneroView vista = new RegistroPeliculaPorGeneroView();
            vista.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    menuFrame.setVisible(true);
                }
                @Override
                public void windowClosing(WindowEvent e) {
                    menuFrame.setVisible(true);
                }
            });
            vista.setVisible(true);
        });

        btnConsulta.addActionListener(e -> {
            menuFrame.setVisible(false);
            ConsultarPeliculaPorGeneroView vista = new ConsultarPeliculaPorGeneroView();
            if (vista instanceof JFrame) {
                ((JFrame) vista).addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        menuFrame.setVisible(true);
                    }
                    @Override
                    public void windowClosing(WindowEvent e) {
                        menuFrame.setVisible(true);
                    }
                });
                ((JFrame) vista).setVisible(true);
            } else {
                // Si la vista no es JFrame, mostrar el menú de nuevo al cerrarse
                menuFrame.setVisible(true);
            }
        });

        btnReporte.addActionListener(e -> {
            menuFrame.setVisible(false);
            ReporteMayorRecaudacionView vista = new ReporteMayorRecaudacionView();
            if (vista instanceof JFrame) {
                ((JFrame) vista).addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        menuFrame.setVisible(true);
                    }
                    @Override
                    public void windowClosing(WindowEvent e) {
                        menuFrame.setVisible(true);
                    }
                });
                ((JFrame) vista).setVisible(true);
            } else {
                menuFrame.setVisible(true);
            }
        });

        btnSalir.addActionListener(e -> System.exit(0));

        menuFrame.add(btnFuncion);
        menuFrame.add(btnPelicula);
        menuFrame.add(btnConsulta);
        menuFrame.add(btnReporte);
        menuFrame.add(btnSalir);
        menuFrame.setVisible(true);
    }
}