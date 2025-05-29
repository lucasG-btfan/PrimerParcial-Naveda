package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import org.example.model.Videojuegos;

public class Libreria<T extends Videojuegos> {
    private static final Logger logger = LogManager.getLogger(Libreria.class);
    private final List<T> juegos;  // Lista de tipo genérico T

    public Libreria() {
        this.juegos = new ArrayList<>();
        logger.debug("Nueva librería de videojuegos creada");
    }

    public void agregarJuego(T videojuego) {  // Acepta solo T (seguro en tiempo de compilación)
        try {
            juegos.add(videojuego);  // ¡Sin casting necesario!
            logger.info("Videojuego agregado: {}", videojuego.getNombre());
        } catch (Exception e) {
            logger.error("Error al agregar videojuego: {}", videojuego.getNombre(), e);
        }
    }

    public void imprimirJuegos() {
        logger.info("Lista de juegos:");
        juegos.forEach(System.out::println);
    }
}