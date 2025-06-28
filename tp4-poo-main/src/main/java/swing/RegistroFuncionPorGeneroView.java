package swing;

import controllers.FuncionController;
import controllers.PeliculasController;
import controllers.SucursalController;
import dtos.FuncionDTO;
import dtos.PeliculaDTO;
import dtos.SalaDTO;
import dtos.SucursalDTO;
import types.TipoGenero;
import utils.DatePicker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.IntStream;

public class RegistroFuncionPorGeneroView extends JFrame {
    private final JPanel contentPane;
    private final JTextField campoFecha;
    private final JComboBox<Integer> comboHora;
    private final JComboBox<Integer> comboMinuto;
    private final JComboBox<SucursalDTO> comboBoxSucursal;
    private final JComboBox<PeliculaDTO> comboBoxPelicula;
    private final JComboBox<SalaDTO> comboBoxSala;

    public RegistroFuncionPorGeneroView() {
        setTitle("Registrar Nueva Función");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSucursal = new JLabel("Sucursal:");
        lblSucursal.setBounds(10, 10, 100, 20);
        contentPane.add(lblSucursal);

        comboBoxSucursal = new JComboBox<>();
        comboBoxSucursal.setBounds(120, 10, 300, 20);
        contentPane.add(comboBoxSucursal);

        JLabel lblPelicula = new JLabel("Película:");
        lblPelicula.setBounds(10, 40, 100, 20);
        contentPane.add(lblPelicula);

        comboBoxPelicula = new JComboBox<>();
        comboBoxPelicula.setBounds(120, 40, 300, 20);
        contentPane.add(comboBoxPelicula);

        JLabel lblSala = new JLabel("Sala:");
        lblSala.setBounds(10, 70, 100, 20);
        contentPane.add(lblSala);

        comboBoxSala = new JComboBox<>();
        comboBoxSala.setBounds(120, 70, 300, 20);
        contentPane.add(comboBoxSala);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(10, 100, 100, 20);
        contentPane.add(lblFecha);

        campoFecha = new JTextField();
        campoFecha.setBounds(120, 100, 150, 20);
        contentPane.add(campoFecha);
        DatePicker.attachTo(campoFecha, this);

        JLabel lblHora = new JLabel("Hora:");
        lblHora.setBounds(10, 130, 100, 20);
        contentPane.add(lblHora);

        comboHora = new JComboBox<>(IntStream.rangeClosed(0, 23).boxed().toArray(Integer[]::new));
        comboHora.setBounds(120, 130, 50, 20);
        comboHora.setMaximumRowCount(24);
        contentPane.add(comboHora);

        comboMinuto = new JComboBox<>(new Integer[]{0, 15, 30, 45});
        comboMinuto.setBounds(180, 130, 50, 20);
        comboMinuto.setMaximumRowCount(4);
        contentPane.add(comboMinuto);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> registrarFuncion());
        btnAceptar.setBounds(100, 200, 100, 25);
        contentPane.add(btnAceptar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        btnCancelar.setBounds(250, 200, 100, 25);
        contentPane.add(btnCancelar);

        cargarSucursales();
        cargarPeliculas();

        comboBoxSucursal.addActionListener(e -> cargarSalas());
        comboBoxPelicula.addActionListener(e -> cargarSalas());

        if (comboBoxSucursal.getItemCount() > 0 && comboBoxPelicula.getItemCount() > 0) {
            comboBoxSucursal.setSelectedIndex(0);
            comboBoxPelicula.setSelectedIndex(0);
            cargarSalas();
        }
    }

    private void cargarSucursales() {
        comboBoxSucursal.removeAllItems();
        for (int i = 1; i <= 10; i++) {
            SucursalDTO suc = SucursalController.getINSTANCE().buscarSucursalPorID(i);
            if (suc != null) comboBoxSucursal.addItem(suc);
        }
    }

    private void cargarPeliculas() {
        comboBoxPelicula.removeAllItems();
        for (int i = 1; i <= 100; i++) {
            PeliculaDTO peli = PeliculasController.getINSTANCE().buscarPeliculaPorID(i);
            if (peli != null) comboBoxPelicula.addItem(peli);
        }
    }

    private void cargarSalas() {
        comboBoxSala.removeAllItems();
        SucursalDTO sucursal = (SucursalDTO) comboBoxSucursal.getSelectedItem();
        PeliculaDTO pelicula = (PeliculaDTO) comboBoxPelicula.getSelectedItem();
        if (sucursal != null && pelicula != null) {
            List<SalaDTO> salas = SucursalController.getINSTANCE().buscarSalasPorSucursalID(sucursal.getID());
            TipoGenero generoPelicula = pelicula.getGenero();
            Set<String> denominacionesAgregadas = new HashSet<>();
            for (SalaDTO sala : salas) {
                if ((sala.getGenero() == null || sala.getGenero() == generoPelicula)
                        && denominacionesAgregadas.add(sala.getDenominacion())) {
                    comboBoxSala.addItem(sala);
                }
            }
        }
    }

    private void registrarFuncion() {
        try {
            PeliculaDTO pelicula = (PeliculaDTO) comboBoxPelicula.getSelectedItem();
            SalaDTO sala = (SalaDTO) comboBoxSala.getSelectedItem();
            String fechaStr = campoFecha.getText();
            Integer hora = (Integer) comboHora.getSelectedItem();
            Integer minuto = (Integer) comboMinuto.getSelectedItem();

            if (pelicula == null || sala == null || fechaStr.isEmpty() || hora == null || minuto == null) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.");
                return;
            }

            LocalDate fecha = LocalDate.parse(fechaStr);
            LocalDateTime fechaHora = fecha.atTime(hora, minuto);
            String horaStr = String.format("%02d:%02d", hora, minuto);

            FuncionDTO funcion = new FuncionDTO(horaStr, fechaHora, new ArrayList<>());
            funcion.setPelicula(pelicula);
            funcion.setSala(sala);

            int id = FuncionController.getINSTANCE().altaNuevaFuncion(funcion);
            if (id > 0) {
                JOptionPane.showMessageDialog(this, "Función registrada con éxito.");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la función.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos: " + ex.getMessage());
        }
    }

    private void limpiar() {
        campoFecha.setText("");
        comboHora.setSelectedIndex(0);
        comboMinuto.setSelectedIndex(0);
        comboBoxSucursal.setSelectedIndex(-1);
        comboBoxPelicula.setSelectedIndex(-1);
        comboBoxSala.removeAllItems();
    }
}