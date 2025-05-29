package org.example.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.exceptions.IdNegativoException;
import org.example.exceptions.NombreInvalidoException;
import org.example.model.Videojuegos;
import org.example.util.DataBaseUtil;

import java.sql.*;
import java.util.List;

public class VideojuegosDAOimpl implements VideojuegosDAO {
    private static final Logger logger = LogManager.getLogger(VideojuegosDAOimpl.class);

    @Override
    public int crear(Videojuegos videojuego) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int generatedId = -1;

        try {
            conn = DataBaseUtil.getConnection();
            String sql = "INSERT INTO videojuegos(nombre,edad,digital,size,juego_offline) VALUES (?, ?, ?, ?)";
            logger.debug("Preparando sentencia SQL: {}", sql);

            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, videojuego.getNombre());
            stmt.setInt(2, videojuego.getEdad_min());
            stmt.setBoolean(3, videojuego.isDigital());
            stmt.setBoolean(4, videojuego.isJuego_offline());
            stmt.setInt(5,videojuego.getId_consola());

            int affectedRows = stmt.executeUpdate();
            logger.info("Filas afectadas al crear videojuego: {}", affectedRows);

            if (affectedRows > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                    logger.info("Videojuego creado con ID: {}", generatedId);
                }
            }
        } catch (SQLException e) {
            logger.error("Error al crear videojuego en la base de datos", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.warn("Error al cerrar ResultSet", e);
                }
            }
            DataBaseUtil.closeResources(conn, stmt);
        }
        return generatedId;
    }

    @Override
    public Videojuegos buscarPorId(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Videojuegos videojuego = null;

        try {
            conn = DataBaseUtil.getConnection();
            String sql = "SELECT * FROM videojuego WHERE id = ?";
            logger.debug("Buscando videojuego con ID: {}", id);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                videojuego = new Videojuegos(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad_min"),
                        rs.getBoolean("digital"),
                        rs.getBoolean("juego_offline"),
                        rs.getInt("id_consola"));
                logger.info("Videojuego encontrado: {}", videojuego.getNombre());
            } else {
                logger.warn("No se encontró videojuego con ID: {}", id);
            }
        } catch (SQLException e) {
            logger.error("Error al buscar videojuego por ID: {}", id, e);
        } catch (IdNegativoException e) {
            throw new RuntimeException(e);
        } catch (NombreInvalidoException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.warn("Error al cerrar ResultSet", e);
                }
            }
            DataBaseUtil.closeResources(conn, stmt);
        }
        return videojuego;
    }

    @Override
    public List<Videojuegos> listarTodo() {
        return List.of();
    }

    @Override
    public boolean actualizar(Videojuegos videojuego) {
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        return false;
    }

}