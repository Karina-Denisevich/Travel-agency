package com.github.karina_denisevich.travel_agency.web.load_balancer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class StartBalancer extends Thread {

    private static final int LOCAL_PORT = 8080;
    private static final String HOST = "localhost";
    private static final String[] ports = {"8081", "8082", "8083"};

    private static int getRandomPort(String[] args) {
        int size = new Random().nextInt(args.length);
        return Integer.valueOf(args[size]);
    }


    public static void main(String[] args) throws IOException {
        // try (ServerSocket server = new ServerSocket(LOCAL_PORT)) {
        try {
            System.out.println("Starting proxy for " + HOST + " on port " + LOCAL_PORT);

//            while (true) {
//                ProxyThread proxyThread = new ProxyThread(server.accept(), HOST, getRandomPort(args));
//                new Thread(proxyThread).start();
//            }

            runServer(HOST, args, LOCAL_PORT);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Usage: java ProxyMultiThread "
                    + "<host> <remoteport> <localport>");
        }
    }


    public static void runServer(String host, String[] remoteports, int localport)
            throws IOException {
        ServerSocket ss = new ServerSocket(localport);

        final byte[] request = new byte[1024];
        byte[] reply = new byte[4096];

        while (true) {
            int remoteport = getRandomPort(remoteports);

           // Socket client = null;
            try (Socket server = new Socket(host, remoteport); Socket client = ss.accept();) {
//                client = ss.accept();

                InputStream from_client = client.getInputStream();

                new Thread() {
                    public void run() {
                        int bytes_read;
                        try (OutputStream to_server = server.getOutputStream()) {
                            while ((bytes_read = from_client.read(request)) != -1) {
                                to_server.write(request, 0, bytes_read);
                                System.out.println("+++++++++++++++++++++++++++hi  " + server.getPort());
                                to_server.flush();
                            }
                        } catch (IOException e) {
                        }
                    }
                }.start();
                InputStream from_server = server.getInputStream();
                int bytes_read;
                try (OutputStream to_client = client.getOutputStream()){
                    while ((bytes_read = from_server.read(reply)) != -1) {
                        to_client.write(reply, 0, bytes_read);
                        to_client.flush();
                        System.out.println("+++++++++++++++++++++++++++++++hi2");
                    }
                } catch (IOException e) {
                }

            } catch (IOException e) {
                System.err.println(e);
            } finally {
//                try {
//                    //if (server != null) server.close();
//                    if (client != null) client.close();
//                } catch (IOException e) {
//                }
            }
        }//while(true)
    }
}
