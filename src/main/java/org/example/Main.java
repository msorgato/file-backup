package org.example;

import org.apache.commons.configuration.*;

import java.io.File;

public class Main {


    public static void main(String[] args) throws ConfigurationException {
        System.out.println("Hello world!");

//        ConfigurationFactory factory = new ConfigurationFactory("config.properties");
//        Configuration config = factory.getConfiguration();

        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new SystemConfiguration());
        config.addConfiguration(new PropertiesConfiguration("config.properties"));

        System.out.println("Percorso iniziale: '" + config.getProperty("backup.source.base-path") + "'");
    }
}