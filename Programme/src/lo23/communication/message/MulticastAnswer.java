package lo23.communication.message;

import lo23.data.PublicProfile;

/**
 * This message is sent by distant client to answer MulticastInvit.
 */
public class MulticastAnswer extends MulticastMessage {

    /**
     * Constructor of the MulticastAnswer.
     * @param guest the distant PublicProfile to add to the local PublicProfile list of the current client.
     */
    public MulticastAnswer(PublicProfile profile) {
        super(profile);
    }

}
