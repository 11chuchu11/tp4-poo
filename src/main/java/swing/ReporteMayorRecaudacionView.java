package swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReporteMayorRecaudacionView extends JFrame {
    private final JPanel contentPane;
    private final JTextArea textAreaReporte;
    private final JButton btnCerrar;

    public ReporteMayorRecaudacionView() {
        setTitle("Reporte de Películas con Mayor Recaudación");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Películas con Mayor Recaudación", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        textAreaReporte = new JTextArea();
        textAreaReporte.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaReporte);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnCerrar);
        contentPane.add(panelBoton, BorderLayout.SOUTH);
    }

    // Método para que el controlador pueda mostrar el reporte
    public void setReporte(String reporte) {
        textAreaReporte.setText(reporte);
    }
}
