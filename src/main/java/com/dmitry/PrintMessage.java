package com.dmitry;

import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * class for greetings
 */
public class PrintMessage {

    private final Logger logger = Logger.getLogger(PrintMessage.class);
    private int currentHour;
    ResourceBundle myResources;
    Locale locale;

    public PrintMessage() {
        locale = Locale.getDefault();
        logger.info("current locale " + locale);
        setLocale(locale);
        Calendar currentTime = Calendar.getInstance();
        currentHour = currentTime.get(Calendar.HOUR_OF_DAY);
        logger.info("current currentHour- " + currentHour);
    }

    /**
     * If the current computer locale is not supported, set local "EN"
     * @param locale set new locale
     */
    public void setLocale(Locale locale) {
        if (!locale.getLanguage().equals("en") && !locale.getLanguage().equals("ru")) {
            this.locale = new Locale("en", "US");
        } else {
            this.locale = locale;
        }
        logger.info("set locale " + locale);
        myResources = ResourceBundle.getBundle("messages/message", this.locale, new UTF8Control());
    }

    /**
     * Determine the time of day
     * Call greetings
     */
    public String sayHello() {
        logger.info("call say hello");
        //use messages
        if (currentHour >= 6 && currentHour < 9)
            return morning();
        else if (currentHour >= 9 && currentHour < 19)
            return day();
        else if (currentHour >= 19 && currentHour < 23)
            return evening();
        else
            return night();
    }

    //print "Good morning, World!"
    private String morning() {
        logger.info(myResources.getString("morning"));
        return myResources.getString("morning");
    }

    //print "Good day, World!"
    private String day() {
        logger.info(myResources.getString("day"));
        return myResources.getString("day");
    }

    //print "Good evening, World!"
    private String evening() {
        logger.info(myResources.getString("evening"));
        return myResources.getString("evening");
    }

    //print "Good night, World!"
    private String night() {
        logger.info(myResources.getString("night"));
        return myResources.getString("night");
    }

    public void setCurrentHour(int currentHour) {
        logger.info("set current hour- " + currentHour);
        this.currentHour = currentHour;
    }
}
