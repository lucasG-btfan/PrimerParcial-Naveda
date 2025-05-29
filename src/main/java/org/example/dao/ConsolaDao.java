package org.example.dao;

import org.example.model.Consola;
import java.util.List;

public interface ConsolaDao {
    int crear(Consola consola);
    Consola buscarPorId(int id);
    List<Consola> listarTodo();

    boolean actualizar(Consola consola);
    boolean eliminar(int id_consola);

}
