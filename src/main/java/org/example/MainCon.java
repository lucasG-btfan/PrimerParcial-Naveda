package org.example;

import org.example.model.Consola;

import static org.example.dao.ConsolaDaoimpl.logger;

public class MainCon {
    public static void main(String[] args) {
        logger.info("Iniciando aplicación de gestión de Consola");

        try {
            Consola consola1= new Consola(1,"Ps2",200000,"Sony");
            Consola consola3= new Consola(3,"Ps3",600000,"Sony");
            Consola consola2= new Consola("Xbox1",500000,"Microsoft");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}