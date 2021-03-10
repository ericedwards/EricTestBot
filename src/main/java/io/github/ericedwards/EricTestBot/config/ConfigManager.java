package io.github.ericedwards.EricTestBot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static ConfigManager theManager = null;
    private static Logger logger = LoggerFactory.getLogger(ConfigManager.class);

    private static final String PROPERTIES_FILE_NAME = "/local.properties";

    private Properties props;

    private ConfigManager() {
        props = loadProperties();
    }

    public static synchronized ConfigManager instance() {
        if (theManager == null) {
            theManager = new ConfigManager();
            logger.info("new manager");
        }
        return theManager;
    }

    private static Properties loadProperties() {
        Properties classpathProps = new Properties();
        logger.debug("trying classpath");
        try {
            InputStream is = ConfigManager.class.getResourceAsStream(PROPERTIES_FILE_NAME);
            if (is == null) {
                logger.error("input stream is null");
            }
            classpathProps.load(is);
            is.close();
        } catch (IOException ioe) {
            logger.error("cannot load properties: " + ioe.toString());
        }
        logger.debug("loaded");
        return classpathProps;
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }

    public boolean isPropertyTrue(String key) {
        String s = props.getProperty(key);
        if (s == null) {
            return false;
        } else if (s.equalsIgnoreCase("true")) {
            return true;
        } else {
            return false;
        }
    }

    public int getIntegerProperty(String key) {
        int value = 0;
        String s = props.getProperty(key);
        if (s != null) {
            try {
                value = Integer.parseInt(s);
            } catch (NumberFormatException nfe) {
                // ignore and return 0
            }
        }
        return value;
    }

    public long getLongProperty(String key) {
        long value = 0;
        String s = props.getProperty(key);
        if (s != null) {
            try {
                value = Long.parseLong(s);
            } catch (NumberFormatException nfe) {
                // ignore and return 0
            }
        }
        return value;
    }

}
