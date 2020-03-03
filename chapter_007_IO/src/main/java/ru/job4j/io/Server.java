package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String ask = null;
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                if ("hello".equalsIgnoreCase(ask)) {
                    out.println("Hello, dear friend, I'm a oracle.");
                    out.println();
                } else if (!"exit".equalsIgnoreCase(ask)) {
                    out.println("I don't understand");
                }
            } while (!"exit".equalsIgnoreCase(ask));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        try (final Socket socket = new ServerSocket(2000).accept()) {
            new Server(socket);
        }
    }
}
