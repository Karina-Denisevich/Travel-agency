package com.github.karina_denisevich.travel_agency.web.load_balancer;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class ProxyThread extends Thread {
    private Socket sClient;
    private final String SERVER_URL;
    private final int SERVER_PORT;

    public ProxyThread(Socket sClient, String serverUrl, int serverPort) {
        System.out.println("------------------" + serverPort);
        this.SERVER_URL = serverUrl;
        this.SERVER_PORT = serverPort;
        this.sClient = sClient;
    }

    @Override
    public void run() {
        try (Socket server = new Socket(SERVER_URL, SERVER_PORT)){
            byte[] request = new byte[1024];
            byte[] reply = new byte[4096];
            InputStream inFromClient = sClient.getInputStream();
            OutputStream outToClient = sClient.getOutputStream();
//            Socket server;
//
//            try {
//                server = new Socket(SERVER_URL, SERVER_PORT);
//            } catch (IOException e) {
//                PrintWriter out = new PrintWriter(new OutputStreamWriter(outToClient));
//                out.flush();
//                throw new RuntimeException(e);
//            }
            // a new thread to manage streams from server to client (DOWNLOAD)
            InputStream inFromServer = server.getInputStream();
            OutputStream outToServer = server.getOutputStream();
            // a new thread for uploading to the server

            int p = server.getPort();
            new Thread(() -> {
                int bytes_read;
                try {
                    while ((bytes_read = inFromClient.read(request)) != -1) {
                        outToServer.write(request, 0, bytes_read);
                        outToServer.flush();
                        System.out.println("+++++++++++++++++++++++++++hi  " + p);
                    }
                } catch (IOException e) {
                }
//                try {
//                    outToServer.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }).start();

            // current thread manages streams from server to client (DOWNLOAD)
            int bytes_read;
            try {
                while ((bytes_read = inFromServer.read(reply)) != -1) {
                    outToClient.write(reply, 0, bytes_read);
                    outToClient.flush();
                    System.out.println("+++++++++++++++++++++++++++++++hi2");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
//                try {
//                    if (server != null) {
//                        server.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
//            outToClient.close();
//            sClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}