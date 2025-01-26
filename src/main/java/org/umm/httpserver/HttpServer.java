package org.umm.httpserver;

import org.umm.httpserver.config.Configuration;
import org.umm.httpserver.config.ConfigurationManager;

public class HttpServer {

    public static void main(String[] args) {

        System.out.println("Ejecutando servidor...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration configuration = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Puerto: " + configuration.getPort());
        System.out.println("Webroot: " + configuration.getWebroot());

    }

}
