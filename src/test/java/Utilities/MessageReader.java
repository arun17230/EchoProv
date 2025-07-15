package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MessageReader {

    static Properties prop = new Properties();

    static //Used only once when class is loaded automatically 
    {
        try 
        {
        	String path = System.getProperty("user.dir") + "/src/test/resources/message.properties";
            FileInputStream file = new FileInputStream(path);
            prop.load(file);
        } 
        catch (IOException e) 
        {
            System.out.println("Failed to load messages.properties");
            e.printStackTrace();
        }
    }

    public static String get(String key) 
    {
        return prop.getProperty(key);
    }
}
