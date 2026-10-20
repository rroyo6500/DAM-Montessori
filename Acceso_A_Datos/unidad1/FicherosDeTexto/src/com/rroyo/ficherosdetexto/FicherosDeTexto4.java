package com.rroyo.ficherosdetexto;

import java.io.*;

public class FicherosDeTexto4 {

    private  static String path = "acceso_a_datos/unidad1/FicherosDeTexto/src/com/rroyo/ficherosdetexto/";

    public static void main(String[] args) throws IOException {

        // Escribir

        BufferedWriter bw = new BufferedWriter(new FileWriter(path + "fichero4.txt"));
        bw.write("Texto a escribir. \n");
        bw.close();

        // Leer

        BufferedReader br = new BufferedReader(new FileReader(path + "fichero4.txt"));
        String linea;
        while ((linea = br.readLine()) != null) {
            System.out.println(linea);
        }
        br.close();

    }

}
