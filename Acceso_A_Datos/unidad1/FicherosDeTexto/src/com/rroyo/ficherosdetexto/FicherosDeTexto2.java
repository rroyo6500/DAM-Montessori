package com.rroyo.ficherosdetexto;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FicherosDeTexto2 {

    private  static String path = "acceso_a_datos/unidad1/FicherosDeTexto/src/com/rroyo/ficherosdetexto/";

    public static void main(String[] args) throws IOException {

        // Escribir

        FileWriter fw = new FileWriter(path + "fichero2.txt");
        fw.write("Traspaso texto a fichero. \n");
        fw.close();

        // Leer

        File file = new File(path + "fichero2.txt");
        Scanner lector = null;
        lector = new Scanner(file);
        while (lector.hasNextLine()) {
            System.out.println(lector.nextLine());
        }
        lector.close();

    }

}
