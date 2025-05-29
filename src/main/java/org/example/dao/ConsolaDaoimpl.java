package org.example.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Consola;
import org.example.util.DataBaseUtil;

import java.sql.*;
import java.util.List;

public class ConsolaDaoimpl implements ConsolaDao {
    public static final Logger logger = LogManager.getLogger(ConsolaDaoimpl.class);

    @Override
    public int crear(Consola consola){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int generatedId = -1;

        try {
            conn = DataBaseUtil.getConnection();
            String sql = "INSERT INTO videojuegos(nombre,edad,digital,size,juego_offline) VALUES (?, ?, ?, ?)";
            logger.debug("Preparando sentencia SQL: {}", sql);

            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, consola.getNombre());
            stmt.setDouble(2,consola.getPrecio());
            stmt.setString(3,consola.getMarca());

            int affectedRows = stmt.executeUpdate();
            logger.info("Filas afectadas al crear consola: {}", affectedRows);

            if (affectedRows > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                    logger.info("Consola creado con ID: {}", generatedId);
                }
            }


        }catch (SQLException e) {
            logger.error("Error al crear consola en la base de datos", e);
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
    public Consola buscarPorId(int id_consola){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Consola consola = null;

        try {
            conn = DataBaseUtil.getConnection();
            String sql = "SELECT * FROM consola WHERE id_consola = ?";
            logger.debug("Buscando consola con ID: {}", id_consola);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_consola);
            rs = stmt.executeQuery();

            if (rs.next()){
                consola= new Consola(
                        rs.getInt("id_consola"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getString("marca"));
                logger.info("Videojuego encontrado: {}", consola.getNombre());
            }else {
                logger.warn("No se encontró consola con ID: {}", id_consola);
            }
        }catch (SQLException e) {
            logger.error("Error al buscar consola por ID: {}", id_consola, e);
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.warn("Error al cerrar ResultSet", e);
                }
            }
            DataBaseUtil.closeResources(conn, stmt);
        }
        return consola;
    }
    @Override
    public List<Consola> listarTodo() {
        return List.of();
    }

    @Override
    public boolean actualizar(Consola consola) {
        return false;
    }

    @Override
    public boolean eliminar(int id_consola) {
        return false;
    }
}
