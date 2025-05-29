package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseUtilcon {
    private static final String DB_URL = "jdbc:h2:./Base_de_Datos/alumnos";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    public static void closeResources(Connection conn, Statement stmt) {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void initDatabase() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();

            // SQL completo como una sola cadena
            String sql ="CREATE TABLE IF NOT EXISTS consola ("+
                    "id_consola INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(100) NOT NULL, " +
                    "precio DOUBLE,"+
                    "marca VARCHAR(100) NOT NULL)";
            stmt.execute(sql);
            System.out.println("Base de datos inicializada correctamente");

        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos:");
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
    }
}