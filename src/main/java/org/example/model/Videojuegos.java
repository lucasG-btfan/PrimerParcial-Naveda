package org.example.model;

import org.example.exceptions.NombreInvalidoException;
import org.example.exceptions.IdNegativoException;

public class Videojuegos {
    private int id;
    private String nombre;
    private int edad_min;
    private boolean digital;
    private boolean juego_offline;
    private int id_consola;

    public Videojuegos(String nombre, int edad_min, boolean digital, boolean juego_offline, int id_consola)
            throws NombreInvalidoException {

        // Validar nombre
        if (nombre == null || nombre.isEmpty() || (nombre.length() == 1 && nombre.equals(" "))) {
            throw new NombreInvalidoException("El nombre debe tener al menos 1 carácter válido (no espacio)");
        }

        this.nombre = nombre;
        this.edad_min = edad_min;
        this.digital = digital;
        this.juego_offline = juego_offline;
        this.id_consola = id_consola;
    }

    public Videojuegos(int id, String nombre, int edad_min, boolean digital, boolean juego_offline, int id_consola)
            throws IdNegativoException, NombreInvalidoException {

        // Validar ID negativo
        if (id < 0) {
            throw new IdNegativoException("El ID no puede ser negativo: " + id);
        }

        // Validar nombre
        if (nombre == null || nombre.isEmpty() || (nombre.length() == 1 && nombre.equals(" "))) {
            throw new NombreInvalidoException("El nombre debe tener al menos 1 carácter válido (no espacio)");
        }

        this.id = id;
        this.nombre = nombre;
        this.edad_min = edad_min;
        this.digital = digital;
        this.juego_offline = juego_offline;
        this.id_consola = id_consola;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) throws IdNegativoException {
        if (id < 0) {
            throw new IdNegativoException("El ID no puede ser negativo: " + id);
        }
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) throws NombreInvalidoException {
        if (nombre == null || nombre.isEmpty() || (nombre.length() == 1 && nombre.equals(" "))) {
            throw new NombreInvalidoException("El nombre debe tener al menos 1 carácter válido (no espacio)");
        }
        this.nombre = nombre;
    }

    public int getEdad_min() {
        return edad_min;
    }
    public void setEdad_min(int edad_min) {
        this.edad_min = edad_min;
    }

    public boolean isDigital() {
        return digital;
    }
    public void setDigital(boolean digital) {
        this.digital = digital;
    }

    public boolean isJuego_offline() {
        return juego_offline;
    }
    public void setJuego_offline(boolean juego_offline) {
        this.juego_offline = juego_offline;
    }

    public int getId_consola() {
        return id_consola;
    }
    public void setId_consola(int id_consola) {
        this.id_consola = id_consola;
    }

    @Override
    public String toString() {
        return "Videojuegos {" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad_min=" + edad_min +
                ", digital=" + digital +
                ", juego_offline=" + juego_offline +
                ", id_consola=" + id_consola +
                '}';
    }
}
