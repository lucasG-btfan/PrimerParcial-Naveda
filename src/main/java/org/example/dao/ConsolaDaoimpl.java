package org.example.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Consola;
import org.example.util.DataBaseUtil;
import org.example.util.DataBaseUtilcon;

import java.sql.*;
import java.util.ArrayList;
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
            conn = DataBaseUtilcon.getConnection();
            String sql = "INSERT INTO consola(nombre,precio,marca) VALUES (?, ?, ?)";
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
            DataBaseUtilcon.closeResources(conn, stmt);
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
            conn = DataBaseUtilcon.getConnection();
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
                logger.info("Consola encontrado: {}", consola.getNombre());
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
            DataBaseUtilcon.closeResources(conn, stmt);
        }
        return consola;
    }
    @Override
    public List<Consola> listarTodo() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<Consola> consolas=new ArrayList<>();

        try {

            conn = DataBaseUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM consola";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Consola consola= new Consola(
                        rs.getInt("id_consola"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getString("marca")
                );
                consolas.add(consola);
            }
    }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DataBaseUtilcon.closeResources(conn, stmt);
        }
        return consolas;
    }

    @Override
    public boolean actualizar(Consola consola) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean actualizado = false;
        try {
            conn = DataBaseUtilcon.getConnection();
            String sql = "UPDATE consolaSET nombre = ?,precio = ?" +
                    "marca = ?  WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,consola.getNombre());
            stmt.setDouble(2, consola.getPrecio());
            stmt.setString(3,consola.getMarca());

            int affectedRows = stmt.executeUpdate();
            actualizado = (affectedRows > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseUtilcon.closeResources(conn, stmt);
        }
        return actualizado;
    }

    @Override
    public boolean eliminar(int id_consola) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean eliminado = false;

        try {
            conn = DataBaseUtilcon.getConnection();
            String sql = "DELETE FROM consola WHERE id_consola = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_consola);
            int affectedRows = stmt.executeUpdate();
            eliminado = (affectedRows > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseUtilcon.closeResources(conn, stmt);
        }
        return eliminado;
    }
}
