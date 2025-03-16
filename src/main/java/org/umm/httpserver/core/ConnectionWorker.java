package org.umm.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.umm.httpserver.HttpServer;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionWorker extends Thread {
    private Socket socket;
    private static final Logger log = LoggerFactory.getLogger(HttpServer.class);

    public ConnectionWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            int _byte = inputStream.read();

            while ( (_byte = inputStream.read()) >= 0 ) {
                System.out.print((char)_byte);
            }

            String html = "<html><head><title>Servidor HTTP</title></head><body><h1>Hola que tal</h1></body></html>";

            final String CRLF = "\r\n";

            String response = "HTTP/1.1 200 OK" + CRLF +
                    "Tamaño del mensaje" + html.getBytes().length + CRLF +
                    CRLF +
                    html +
                    CRLF + CRLF;

            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();

            log.info("Conexión terminada");
        } catch (Exception e) {
            log.error("Problemas con la comunicación: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {}
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {}
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {}
            }
        }
    }
}
