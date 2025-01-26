package org.umm.httpserver.config;

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

    }

    /**
     * Devuelve la configuración cargada actualmente
     */
    public void getCurrentConfiguration() {

    }

}
