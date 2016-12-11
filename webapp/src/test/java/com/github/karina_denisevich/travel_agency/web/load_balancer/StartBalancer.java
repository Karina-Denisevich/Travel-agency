package com.github.karina_denisevich.travel_agency.web.load_balancer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class StartBalancer {

    public static void main(String[] args) throws IOException {
        try {
            // and the local port that we listen for connections on
            String host = "localhost";
            int remoteport = 8081;
            int localport = 8080;
            // Print a start-up message
            System.out.println("Starting proxy for " + host + ":" + remoteport
                    + " on port " + localport);
            ServerSocket server = new ServerSocket(localport);
            while (true) {
                new ProxyThread(server.accept(), host, remoteport);
            }
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Usage: java ProxyMultiThread "
                    + "<host> <remoteport> <localport>");
        }
    }

    public static void runServer(String host, int remoteport, int localport)
            throws IOException {
        // Create a ServerSocket to listen for connections with
        ServerSocket ss = new ServerSocket(localport);

        final byte[] request = new byte[1024];
        byte[] reply = new byte[4096];

        while(true) {
            // Variables to hold the sockets to the client and to the server.
            Socket client = null;
            Socket server = null;

            try {
                // Wait for a connection on the local port
                client = ss.accept();
                // Get client streams.  Make them final so they can
                // be used in the anonymous thread below.
                final InputStream from_client = client.getInputStream();
                final OutputStream to_client= client.getOutputStream();

                try {
                    server = new Socket(host, remoteport);
                }
                catch (IOException e) {
                    e.printStackTrace();
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(to_client));
                    out.println("Proxy server cannot connect to " + host + ":" +
                            remoteport + ":\n" + e);
                    out.flush();
                    client.close();
                    continue;
                }
                // Get server streams.
                final InputStream from_server = server.getInputStream();
                final OutputStream to_server = server.getOutputStream();
                // Make a thread to read the client's requests and pass them to the
                // server.  We have to use a separate thread because requests and
                // responses may be asynchronous.
                new Thread() {
                    public void run() {
                        int bytes_read;
                        try {
                            while((bytes_read = from_client.read(request)) != -1) {
                                to_server.write(request, 0, bytes_read);
                                System.out.println(bytes_read+"to_server--->"+ new String(request, "UTF-8")+"<---");
                                to_server.flush();
                            }
                        }
                        catch (IOException e) {}
                        // the client closed the connection to us, so  close our
                        // connection to the server.  This will also cause the
                        // server-to-client loop in the main thread exit.
                        try {to_server.close();} catch (IOException e) {}
                    }
                }.start();
                // Meanwhile, in the main thread, read the server's responses
                // and pass them back to the client.  This will be done in
                // parallel with the client-to-server request thread above.
                int bytes_read;
                try {
                    while((bytes_read = from_server.read(reply)) != -1) {
                        try {
                            Thread.sleep(1);
                            System.out.println(bytes_read+"to_client--->"+ new String(request, "UTF-8")+"<---");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        to_client.write(reply, 0, bytes_read);
                        to_client.flush();
                    }
                }
                catch(IOException e) {}
                // The server closed its connection to us, so close our
                // connection to our client.  This will make the other thread exit.
                to_client.close();
            }
            catch (IOException e) { System.err.println(e); }
            // Close the sockets no matter what happens each time through the loop.
            finally {
                try {
                    if (server != null) server.close();
                    if (client != null) client.close();
                }
                catch(IOException e) {}
            }
        }//while(true)
    }
}
