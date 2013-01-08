package lo23.communication.message;

import lo23.data.PublicProfile;

/**
 * This message is sent while multicasting.
 */
public class MulticastInvit extends MulticastMessage {
    
    /**
     * Constructor of the MulticastInvit.
     */
    public MulticastInvit(PublicProfile profile) {
       super(profile);
    }
    
}
