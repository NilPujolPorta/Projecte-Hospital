package APP_Hospital;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class utils{
    public static Properties loadConfig(){
        Properties prop = new Properties();
        String fileName = System.getProperty("user.dir")+"\\APP_Hospital\\config.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + fileName);
        } catch (IOException e) {
            System.out.println("ERROR");            
        }
        return prop;
    }
}


