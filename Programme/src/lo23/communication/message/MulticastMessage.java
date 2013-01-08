package lo23.communication.message;

import lo23.data.PublicProfile;

/**
 * Abstract class used for multicast messages
 */
public abstract class MulticastMessage extends ConnectionMessage {
    
    private PublicProfile profile;
    
    /**
     * Constructor of the MulticastConnection.
     */
    public MulticastMessage(PublicProfile Profile){
        this.profile = Profile;
    }

    public void setProfile(PublicProfile Profile) {
        this.profile = Profile;
    }
    
    public PublicProfile getProfile() {
        return profile;
    }
    
}
