package org.maniargh;

import org.apache.commons.configuration.*;
import org.maniargh.logging.CustomLogger;

import java.io.File;
import java.util.Arrays;
import java.util.logging.Logger;

public class Launcher {

    private static final Logger LOG = Logger.getLogger("launcher");
    private static final String FS_SEPARATOR = System.getProperty("file.separator");

    public static void main(String[] args) throws ConfigurationException {
        System.out.println("Hello world!");

//        ConfigurationFactory factory = new ConfigurationFactory("config.properties");
//        Configuration config = factory.getConfiguration();

        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new SystemConfiguration());
        config.addConfiguration(new PropertiesConfiguration("config.properties"));

        LOG.info("Percorso iniziale: '" + config.getProperty("backup.source.base-path") + "'");

        // Scan files nella directory
        File sourcePath = new File((String) config.getProperty("backup.source.base-path"));

        LOG.info("Contenuto della sorgente: " + Arrays.toString(sourcePath.list()));

        // mi ciclo i vari file ricorsivamente
        String[] childrenFiles = sourcePath.list();
        if (childrenFiles != null && childrenFiles.length > 0) {
            for (int i = 0; i < childrenFiles.length; i++) {
                String logPrefix = "[" + (i + 1) + "/" + childrenFiles.length + "] ";
                String child = childrenFiles[i];
                LOG.info(logPrefix + "File in processamento: '" + child + "'");

                File childFile = new File(sourcePath.getAbsolutePath() + FS_SEPARATOR + child);
                LOG.info(logPrefix + "isFile = " + childFile.isFile());
            }
        }
    }
}