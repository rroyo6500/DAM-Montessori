package com.rroyo.ficherosdeconfiguracion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class GenerarFicheroConfigurcion {

    private static String path = "acceso_a_datos/unidad1/FicherosDeConfiguracion/src/com/rroyo/ficherosdeconfiguracion/";

    public static void main(String[] args) {

        Properties config = new Properties();
        String user = "rroyo";
        String pass = "1234";
        String server = "localhost";
        int port = 3304;

        try {

            config.setProperty("user", user);
            config.setProperty("password", pass);
            config.setProperty("server", server);
            config.setProperty("port", String.valueOf(port));

            config.store(new FileOutputStream(path + "configuracion.conf"), "Fichero de configuracion");
        } catch (IOException e) {
            throw new RuntimeException(e);
        };

    }

}
