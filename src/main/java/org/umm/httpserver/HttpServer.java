package org.umm.httpserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.umm.httpserver.config.Configuration;
import org.umm.httpserver.config.ConfigurationManager;
import org.umm.httpserver.core.ServerListener;

import java.io.IOException;

public class HttpServer {

    private static final Logger log = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) {

        log.info("Ejecutando servidor...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration configuration = ConfigurationManager.getInstance().getCurrentConfiguration();

        log.info("Puerto: " + configuration.getPort());
        log.info("Webroot: " + configuration.getWebroot());

        try {
            ServerListener serverListener = new ServerListener(configuration.getPort(), configuration.getWebroot());
            serverListener.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
