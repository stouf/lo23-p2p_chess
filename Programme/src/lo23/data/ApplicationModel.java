/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.data;

import lo23.communication.ISender;
import lo23.data.managers.GameManagerInterface;
import lo23.data.managers.ProfileManagerInterface;

/**
 *
 * @author Noé Gaumont
 */
public class ApplicationModel {

    private GameManagerInterface gManager;
    private ProfileManagerInterface pManager;
    private ISender comManager;

    public ApplicationModel() {
    }
    
    public GameManagerInterface getGManager() {
        return gManager;
    }

    public ProfileManagerInterface getPManager() {
        return pManager;
    }

    public void setGameManager(GameManagerInterface gInt) {
        gManager = gInt;
    }

    public void setProfileManager(ProfileManagerInterface pInt) {
        pManager = pInt;
    }

    public ISender getComManager() {
        return comManager;
    }

    public void setComManager(ISender cInt) {
        comManager = cInt;
    }
}
