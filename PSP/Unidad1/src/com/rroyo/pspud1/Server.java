package com.rroyo.pspud1;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private static final int PORT = 4242;
    private final ServerSocket server;

    public Server() throws IOException {
        server = new ServerSocket(PORT, 1000, InetAddress.getByName("0.0.0.0"));
    }

    public void init() throws IOException {
        System.out.println("Server initialized");

        Socket connection = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            connection = server.accept();
            System.out.println("CONNECTION ACCEPTED: " + connection);

            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(connection.getOutputStream())));

            Scanner stdin = new Scanner(System.in);

            boolean send = true;
            while (send) {
                String str = in.readLine();
                System.out.println("CLIENT: " + str);

                System.out.print("-> ");
                String serverMsg = stdin.nextLine();
                out.println(serverMsg);
                out.flush();

            }
            stdin.close();
        } finally {
            assert connection != null;
            assert in != null;
            assert out != null;
            connection.close();
            in.close();
            out.close();
        }



    }

}
