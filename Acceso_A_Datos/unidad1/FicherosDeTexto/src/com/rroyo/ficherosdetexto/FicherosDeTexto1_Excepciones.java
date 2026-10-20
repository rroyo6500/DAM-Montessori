package com.rroyo.ficherosdetexto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FicherosDeTexto1_Excepciones {

    private  static String path = "acceso_a_datos/unidad1/FicherosDeTexto/src/com/rroyo/ficherosdetexto/";

    public static void main(String[] args) {

        PrintWriter escritor = null;
        Scanner lector = null;
        try {

            // Escritura de ficheros de texto plano

            escritor = new PrintWriter(path + "fichero1.txt");
            escritor.println("Esto es una linea para escribir en el fichero.");
            escritor.close();

            // Lectura de ficheros de texto plano

            File file = new File(path + "fichero1.txt");
            lector = new Scanner(file);
            while (lector.hasNextLine()) {
                System.out.println(lector.nextLine());
            }
            lector.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
