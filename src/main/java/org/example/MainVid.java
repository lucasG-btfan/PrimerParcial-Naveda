package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Videojuegos;

public class MainVid {
    private static final Logger logger = LogManager.getLogger(MainVid.class);

    public static void main(String[] args) {
        logger.info("Iniciando aplicación de gestión de videojuegos");

        Libreria<Videojuegos> libreriaGamer = new Libreria<>();

        try {
            Videojuegos videojuego1 = new Videojuegos(1, "The legend of Spyro dawn of the new dragon",
                    13, false, true, 1);  // Añadido id_consola
            Videojuegos videojuego2 = new Videojuegos("Principe de Persia: Las Arenas del tiempo",
                    12, false, true, 2);  // Añadido id_consola
            Videojuegos videojuego3 = new Videojuegos("Fortnite", 12, true, false, 3);  // Añadido id_consola
            Videojuegos videojuego4 = new Videojuegos(4, "Call of Duty Black Ops", 17, false, false, 4);  // Añadido id_consola

            libreriaGamer.agregarJuego(videojuego1);
            libreriaGamer.agregarJuego(videojuego2);
            libreriaGamer.agregarJuego(videojuego3);
            libreriaGamer.agregarJuego(videojuego4);

            logger.info("Videojuegos agregados exitosamente");
        } catch (Exception e) {
            logger.error("Error al agregar videojuegos", e);
        }
        libreriaGamer.imprimirJuegos();
    }
}