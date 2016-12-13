package com.github.karina_denisevich.travel_agency.web.load_balancer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

public class StartBalancer extends Thread {

    private static final int LOCAL_PORT = 8080;
    private static final String HOST = "localhost";


    public static void main(String[] args) throws IOException {
        try {
            System.out.println("Starting proxy for " + HOST + " on port " + LOCAL_PORT);
            runServer(HOST, args, LOCAL_PORT);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Usage: java ProxyMultiThread " + "<host> <remoteport> <localport>");
        }
    }


    public static void runServer(String host, String[] remotePorts, int localPort)
            throws IOException {
        ServerSocket serverSocket = new ServerSocket(localPort);

        byte[] request = new byte[1024];
        byte[] reply = new byte[4096];

        while (true) {
            int remotePort = getRandomPort(remotePorts);

            try (Socket client = serverSocket.accept();
                 Socket server = new Socket(host, remotePort)) {

                InputStream fromClient = client.getInputStream();

                new Thread(() -> {
                    int bytesRead;
                    try (OutputStream toServer = server.getOutputStream()) {
                        while ((bytesRead = fromClient.read(request)) != -1) {
                            toServer.write(request, 0, bytesRead);
                            toServer.flush();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

                InputStream fromServer = server.getInputStream();
                int bytesRead;
                try (OutputStream toClient = client.getOutputStream()) {
                    while ((bytesRead = fromServer.read(reply)) != -1) {
                        toClient.write(reply, 0, bytesRead);
                        toClient.flush();
                    }
                } catch (SocketException e) {
                    //always will: socket closed exception
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int getRandomPort(String[] args) {
        int size = new Random().nextInt(args.length);
        return Integer.valueOf(args[size]);
    }
}
