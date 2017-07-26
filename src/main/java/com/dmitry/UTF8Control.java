package com.dmitry;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * ResourceBundle by default read as ISO-8859-1
 * We read data from UTF-8
 * because ResourceBundle on ISO-8859-1 return Russian-language text is unreadable
 */
public class UTF8Control extends ResourceBundle.Control {
    private final Logger logger = Logger.getLogger(UTF8Control.class);

    public ResourceBundle newBundle
            (String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
            throws IllegalAccessException, InstantiationException, IOException {
        // The below is a copy of the default implementation.
        String bundleName = toBundleName(baseName, locale);
        String resourceName = toResourceName(bundleName, "properties");
        ResourceBundle bundle = null;
        InputStream stream = null;
        if (reload) {
            URL url = loader.getResource(resourceName);
            if (url != null) {
                URLConnection connection = url.openConnection();
                if (connection != null) {
                    connection.setUseCaches(false);
                    stream = connection.getInputStream();
                    logger.debug("connection getInputStream");
                }
            }
        } else {
            stream = loader.getResourceAsStream(resourceName);
            logger.debug("stream getResourceAsStream");
        }
        if (stream != null) {
            try {
                // Only this line is changed to make it to read properties files as UTF-8.
                bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
            } finally {
                stream.close();
                logger.debug("InputStream close");
            }
        }
        return bundle;
    }
}
