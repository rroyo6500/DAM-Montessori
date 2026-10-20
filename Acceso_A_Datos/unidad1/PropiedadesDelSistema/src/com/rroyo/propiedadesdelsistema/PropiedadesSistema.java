package com.rroyo.propiedadesdelsistema;

import java.io.File;

public class PropiedadesSistema {

    public static void main(String[] args) {
        System.out.println(String.format("Caracter separacion rutas: '%s'",
                File.separator));
        System.out.println(String.format("Carpeta personal del usuario: '%s'",
                System.getProperty("user.home")));
        System.out.println(String.format("Ruta en la que se encuentra el usuario: '%s'",
                System.getProperty("user.dir")));

    }

}
