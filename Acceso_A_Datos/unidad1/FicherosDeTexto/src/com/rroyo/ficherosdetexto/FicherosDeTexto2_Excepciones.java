package com.rroyo.ficherosdetexto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FicherosDeTexto2_Excepciones {

    private  static String path = "acceso_a_datos/unidad1/FicherosDeTexto/src/com/rroyo/ficherosdetexto/";

    public static void main(String[] args) {

        FileWriter fw = null;
        Scanner lector = null;

        try {

            // Escribir

            fw = new FileWriter(path + "fichero2.txt");
            fw.write("Traspaso texto a fichero. \n");
            fw.close();

            // Leer

            File file = new File(path + "fichero2.txt");
            lector = new Scanner(file);
            while (lector.hasNextLine()) {
                System.out.println(lector.nextLine());
            }
            lector.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
