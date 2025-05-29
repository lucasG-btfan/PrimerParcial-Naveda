package org.example.model;

public class Consola {
    private int id_consola;
    private String nombre;
    private double precio;
    private String marca;

    public Consola(String nombre, double precio, String marca) {
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
    }

    public Consola(int id, String nombre, double precio, String marca) {
        this.id_consola = id;
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
    }

    public int getId_consola() {
        return id_consola;
    }
    public void setId_consola(int id_consola) {
        this.id_consola = id_consola;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString(){
        return "consola {"+
                "id_consola="+ id_consola +
                "nombre="+nombre+ '\'' +
                "precio="+precio+ '\'' +
                "marca="+marca+"}";

    }
}
