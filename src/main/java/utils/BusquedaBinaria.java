package utils;

import models.Data;

import java.util.List;

public class BusquedaBinaria {

    public static <T extends Data> T buscarPorId(List<T> lista, int idBuscado) {
        int izquierda = 0;
        int derecha = lista.size() - 1;

        while (izquierda <= derecha) {
            int medio = (izquierda + derecha) / 2;
            T elemento = lista.get(medio);
            int idActual = elemento.getID();

            if (idActual == idBuscado) {
                return elemento;
            } else if (idActual < idBuscado) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }

        return null;
    }
}