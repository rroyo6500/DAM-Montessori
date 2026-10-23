package com.rroyo.pspud1;

import java.io.*;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Cliente {

    public static final String serverIp = "10.227.2.56";

    public static void main(String []args) {

        Socket cliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;

        try {
            cliente = new Socket(serverIp, 4242);

            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream())));

            System.out.println("CONECTADO");

            Scanner stdin = new Scanner(System.in);
            MessageDigest m = MessageDigest.getInstance("SHA-256");

            // Ahora a ver que hace mi cliente

            boolean enviando = true;

            while(enviando) {
                System.out.println("Escribe tu mensaje: ");
                String msg = stdin.nextLine();
                String hash = "";
                m.update(msg.getBytes());

                byte[] digest = m.digest();
                for (byte b : digest) {
                    String h = Integer.toHexString(b & 0xFF);
                    if (h.length() == 1) hash += "0";
                    hash += h;
                }
                hash += "asd";

                salida.println(msg);
                salida.println(hash);
                salida.flush();

                String respuesta = entrada.readLine();
                System.out.println("Servidor dice: "+ respuesta);

                enviando = !msg.equals("BYE");
            }
            stdin.close();


        } catch (IOException e) {
            System.err.println("No se puede conectar!!");
            System.exit(-1);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } finally {
            salida.close();

            try {
                entrada.close();
                cliente.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        System.out.println("Conexión finalizada, Saliendo...");
    }

}
