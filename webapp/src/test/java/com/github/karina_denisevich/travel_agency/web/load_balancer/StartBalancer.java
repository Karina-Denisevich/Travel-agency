package com.github.karina_denisevich.travel_agency.web.load_balancer;

import com.github.karina_denisevich.travel_agency.web.StartJetty;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class StartBalancer extends Thread {

    private ServerSocket server;
    private static final int LOCAL_PORT = 8080;
    private static final String HOST = "localhost";

    public static void main(String[] args) throws IOException {
        try {
            int remoteport = 8081;
            int localport = 8080;
            // Print a start-up message
            System.out.println("Starting proxy for " + HOST + ":" + remoteport
                    + " on port " + localport);
            ServerSocket server = new ServerSocket(LOCAL_PORT);
            while (true) {
                Socket s = server.accept();
                ProxyThread thread1 = new ProxyThread(s, HOST, 8081);
                thread1.start();
                ProxyThread thread2 = new ProxyThread(s, HOST, 8082);
                thread2.start();
                ProxyThread thread3 = new ProxyThread(s, HOST, 8083);
                thread3.start();
            }
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Usage: java ProxyMultiThread "
                    + "<host> <remoteport> <localport>");
        }
    }
}
