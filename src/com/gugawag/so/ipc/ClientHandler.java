package com.gugawag.so.ipc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            PrintWriter pout = null;
            try {
                pout = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            pout.println(new Date().toString() + "-Boa noite alunos!");

            BufferedReader bin = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line;
            while ((line = bin.readLine()) != null) {
                System.out.println("O cliente me disse: " + line);
            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
