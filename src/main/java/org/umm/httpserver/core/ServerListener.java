package org.umm.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.umm.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener extends Thread{

    private int port;
    private String webroot;
    ServerSocket serverSocket;
    private static final Logger log = LoggerFactory.getLogger(HttpServer.class);

    public ServerListener(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {

            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();

                log.info("Conexión aceptada: " + socket.getInetAddress());

                ConnectionWorker worker = new ConnectionWorker(socket);
                worker.start();
            }

            // serverSocket.close(); Que luego no se cierre

        } catch (IOException e) {
            e.printStackTrace();
            log.error("Problemas con el socket: " + e.getMessage());
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
