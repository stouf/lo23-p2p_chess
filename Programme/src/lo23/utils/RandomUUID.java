/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.utils;

import java.util.UUID;

/**
 * Class to manage an UUID (Universal Unique Identifier)
 * Use by profile id
 * @author Esteban
 */
public class RandomUUID {
    
    /**
     * Generate an UUID
     * @return the UUID
     */
    public static String RandomStringUUID() {
        // Creating a random UUID (Universally unique identifier).
        //
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }
}
