package controllers;

import dtos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import types.TipoGenero;
import types.TipoProyeccion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllersTest {

    private FuncionController funcionController;
    private SucursalController sucursalController;
    private PeliculasController peliculasController;
    private DescuentoController condicionesDescuentoController;
    private VentasController ventasController;

    @BeforeEach
    void setUp() {
        funcionController = FuncionController.getINSTANCE();
        sucursalController = SucursalController.getINSTANCE();
        peliculasController = PeliculasController.getINSTANCE();
        condicionesDescuentoController= DescuentoController.getINSTANCE();
        ventasController = VentasController.getINSTANCE();
    }

    @Test
    void testAltaNuevaFuncion_SalaPorGenero() {
        SucursalDTO sucursalDTO = new SucursalDTO("Sucursal De Prueba", "Direccion de Prueba");
        int idSucursal = sucursalController.altaNuevaSucursal(sucursalDTO);
        sucursalDTO = sucursalController.buscarSucursalPorID(idSucursal);

        SalaDTO salaDto = new SalaDTO(100, "Sala de Prueba", TipoGenero.Terror);
        salaDto.setSucursal(sucursalDTO);
        int idSala = sucursalController.altaNuevaSala(salaDto);
        salaDto = sucursalController.buscarSalaPorID(idSala);

        PeliculaDTO peliculaDto = peliculasController.buscarPeliculaPorID(1); // Asumiendo que la película con ID 1 existe

        FuncionDTO funcionDTO = new FuncionDTO("20:00", LocalDateTime.now().plusDays(1), new ArrayList<>());
        funcionDTO.setSala(salaDto);
        funcionDTO.setPelicula(peliculaDto);

        int result = funcionController.altaNuevaFuncion(funcionDTO);

        assertTrue(result >0);
    }
    @Test
    void testAltaNuevaPelicula_PorGenero(){
        CondicionesDescuentoDTO condicionesDescuentoDTO = condicionesDescuentoController.buscarDescuentoPorId(1); // Asumiendo que la condición de descuento con ID 1 existe

        PeliculaDTO peliculaDto = new PeliculaDTO(TipoGenero.Terror, "Pelicula de Terror", 120, "Director de Terror", TipoProyeccion.DosD);
        peliculaDto.setCondicionesDescuento(condicionesDescuentoDTO);
        int idPelicula = peliculasController.altaNuevaPelicula(peliculaDto);

        assertTrue(idPelicula > 0, "Debe permitir crear película con género Terror");
    }
    
    @Test
    void testConsultarPeliculasPorGenero(){
        TipoGenero genero = TipoGenero.Terror;
        List<PeliculaDTO> peliculas = peliculasController.buscarPeliculasPorGenero(genero);

        assertNotNull(peliculas, "La lista de películas no debe ser nula");
        assertFalse(peliculas.isEmpty(), "Debe haber al menos una película del género Terror");

        for (PeliculaDTO pelicula : peliculas) {
            assertEquals(genero, pelicula.getGenero(), "Todas las películas deben ser del género Terror");
        }

    }

    @Test
    void testEmitirUnReportePorMayorRecaudacion(){
        PeliculaDTO peliculaDTO = peliculasController.buscarPeliculaConMasRecaudacion();
        assertNotNull(peliculaDTO);
    }

    @Test
    void testRecaudacionPorPelicula(){
        float recaudacionPorPelicula = ventasController.recaudacionPorPelicula(1);
        assertTrue(recaudacionPorPelicula>0);// Asumiendo que la película con ID 1 existe
    }
}
