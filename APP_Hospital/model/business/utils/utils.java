package APP_Hospital.model.business.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;



public class utils {

    /**
     * Returns the Properties of the config file.
     * 
     * @return the {@code Properties} value of the config file,
     *         or {@code null} if there is none.
     */
    public static Properties loadConfig() {
        Properties prop = new Properties();
        String fileName = System.getProperty("user.dir") + "\\APP_Hospital\\model\\business\\config\\config.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + fileName);
        } catch (IOException e) {
            System.out.println("ERROR");
        }
        return prop;
    }

    /**
     * Cercar si existeix un valor enter en concret dins un array
     * 
     * @param arr
     * @param key
     * @return
     */
    public static boolean contains(final int[] arr, final int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }
    public static String getSHA256(String input){

        String toReturn = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }

    public static String getSHA512(String input){

        String toReturn = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }

}
