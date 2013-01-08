/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.utils;

import java.net.URL;

/**
 *
 * @author Esteban
 */
public class ResourceManager {
    
    private static ResourceManager instance = null;
    
    private String pathRes = "/lo23/ui/resources/";
    
    
    private ResourceManager(){
       
    }
    
    public static ResourceManager getInstance(){
        if(instance == null)
            instance = new ResourceManager();
        return instance;
    }
    
    public URL getResource(String fileName){
        return getClass().getResource(pathRes+fileName);
    }
    
}
