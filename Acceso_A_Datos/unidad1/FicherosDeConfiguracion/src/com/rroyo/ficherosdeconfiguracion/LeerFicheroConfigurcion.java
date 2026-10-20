package com.rroyo.ficherosdeconfiguracion;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class LeerFicheroConfigurcion {

    private static String path = "acceso_a_datos/unidad1/FicherosDeConfiguracion/src/com/rroyo/ficherosdeconfiguracion/";

    public static void main(String[] args) {

        Properties config = new Properties();

        try {

            config.load(new FileInputStream(path + "configuracion.conf"));

            String user = config.getProperty("user");
            String pass = config.getProperty("password");
            String server = config.getProperty("server");
            int port = Integer.parseInt(config.getProperty("port"));

            System.out.println("User: " + user);
            System.out.println("Pass: " + pass);
            System.out.println("Server: " + server);
            System.out.println("Port: " + port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        };

    }

}
