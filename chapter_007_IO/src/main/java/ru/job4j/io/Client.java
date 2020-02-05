package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void run() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        String request = null;
        do {
            request = console.nextLine();
            out.println(request);
            in.lines().forEach(System.out::println);
        } while (!"exit".equalsIgnoreCase(request));
    }

    public static void main(String[] args) throws IOException {
        try(final Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 46535)) {
            new Client(socket);
        }
    }
}
