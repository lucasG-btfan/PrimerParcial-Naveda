package org.example.dao;

import org.example.model.Videojuegos;
import java.util.List;

public interface VideojuegosDAO {
    int crear(Videojuegos videojuego);

    Videojuegos buscarPorId(int id);
    List<Videojuegos> listarTodo();

    boolean actualizar(Videojuegos videojuego);
    // boolean actualizar(int id, Videojuegos videojuego); id busca el obj a modificar y videojuego es tiene los datos con los que lo vas a reemplazar
    boolean eliminar(int id);
}
