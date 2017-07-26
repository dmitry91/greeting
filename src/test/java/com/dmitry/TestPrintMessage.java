package com.dmitry;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class TestPrintMessage {


    @Test
    public void testEnLocale(){
        testMessage(new Locale("en", "US"));
    }

    @Test
    public void testRuLocale(){
        testMessage(new Locale("ru", "RU"));
    }

    /**
     * method for test messages output
     * @param locale - set locale 'RU' or 'EN' for testing
     */
    private void testMessage(Locale locale){
        Locale.setDefault(locale);
        PrintMessage printMessage = new PrintMessage(locale);
        ResourceBundle resources = ResourceBundle.getBundle("messages/message", new UTF8Control());
        for (int i = 0; i < 24; i++) {
            //set time
            printMessage.setCurrentHour(i);

            if (i >= 5 && i < 12)
                Assert.assertEquals(printMessage.sayHello(), resources.getString("morning"));
            else if (i >= 12 && i < 19)
                Assert.assertEquals(printMessage.sayHello(), resources.getString("day"));
            else if (i >= 19 && i < 23)
                Assert.assertEquals(printMessage.sayHello(), resources.getString("evening"));
            else
                Assert.assertEquals(printMessage.sayHello(), resources.getString("night"));
        }
    }

}
