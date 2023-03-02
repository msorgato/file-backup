package org.maniargh;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.maniargh.backup.filesystem.FileSyncManager;
import org.maniargh.exception.InvalidArgumentException;

import java.io.IOException;
import java.util.logging.Logger;

public class Launcher {

    private static final Logger LOG = Logger.getLogger("launcher");

    public static void main(String[] args) throws ConfigurationException {
        System.out.println("Hello world!");

//        ConfigurationFactory factory = new ConfigurationFactory("config.properties");
//        Configuration config = factory.getConfiguration();

        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new SystemConfiguration());
        config.addConfiguration(new PropertiesConfiguration("config.properties"));

        LOG.info("Percorso iniziale: '" + config.getProperty("backup.source.base-path") + "'");

        // Scan files nella directory
        String sourcePath = (String) config.getProperty("backup.source.base-path");
        String destinationPath = (String) config.getProperty("backup.destination.base-path");

        try {
            FileSyncManager.getInstance().syncDirectory(sourcePath, destinationPath, true);
        } catch (InvalidArgumentException e) {
            LOG.warning("Configuration error");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}