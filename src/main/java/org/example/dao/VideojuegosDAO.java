package org.example.dao;

import org.example.model.Videojuegos;
import java.util.List;

public interface VideojuegosDAO {
    int crear(Videojuegos videojuego);

    Videojuegos buscarPorId(int id);
    List<Videojuegos> listarTodo();

    boolean actualizar(int id, Videojuegos videojuego);
    boolean eliminar(int id);
}
