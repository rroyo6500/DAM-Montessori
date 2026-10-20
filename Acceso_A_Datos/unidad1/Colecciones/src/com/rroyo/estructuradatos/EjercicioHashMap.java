package com.rroyo.estructuradatos;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EjercicioHashMap {

    public static void main(String[] args) {

        Map<String, Libro> mapLibros = new HashMap<>();
        Libro libro1 = new Libro();
        libro1.setTitulo("Secuestrado");
        libro1.setAutor("Robert Louis Stevenson");
        mapLibros.put(libro1.getTitulo(), libro1);

        String titulo = "Secuestrado";
        System.out.println(mapLibros.get(titulo));

        Libro libro2 = new Libro("Don quijote de la mancha", "Miguel de cervantes");
        Libro libro3 = new Libro("El amor en tiempos de colera", "Gabriel Garica Marquez");

        mapLibros.put(libro2.getTitulo(), libro2);
        mapLibros.put(libro3.getTitulo(), libro3);

        Collection<Libro> coleccionLibros = mapLibros.values();
        for (Libro libro : coleccionLibros) {
            System.out.println(libro);
        }

    }

}
