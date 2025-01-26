package org.umm.httpserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.umm.httpserver.utils.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {

    private static ConfigurationManager myConfigManager;
    private static Configuration myCurrentConfig;

    private ConfigurationManager() {

    }

    public static ConfigurationManager getInstance() {
        if (myConfigManager == null) {
            myConfigManager = new ConfigurationManager();
        }
        return myConfigManager;
    }

    /**
     * Se utiliza para cargar la configuración por su path
     * @param filePath
     */
    public void loadConfigurationFile(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigException(e);
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i;

        while (true) {
            try {
                if (!((i = fileReader.read()) != -1)) break;
            } catch (IOException e) {
                throw new HttpConfigException(e);
            }
            stringBuffer.append((char) i);
        }
        JsonNode config = null;
        try {
            config = Json.parseJson(stringBuffer.toString());
        } catch (IOException e) {
            throw new HttpConfigException("Error parseando el archivo de configuración", e);
        }
        try {
            myCurrentConfig = Json.fromJson(config, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigException("Error parseando el archivo de configuración interno", e);
        }
    }

    /**
     * Devuelve la configuración cargada actualmente
     */
    public Configuration getCurrentConfiguration() {
        if (myCurrentConfig == null) {
            throw new HttpConfigException("No hay ninguna configuración actualmente");
        }
        return myCurrentConfig;
    }

}
