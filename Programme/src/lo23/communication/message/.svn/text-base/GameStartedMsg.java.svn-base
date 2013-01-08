package lo23.communication.message;

import lo23.data.Invitation;

/**
 * This message is sent to notify the distant client that the game is started.
 */
public class GameStartedMsg extends ConnectionMessage{

    private Invitation invit;
   // private PublicProfile guest;
    private boolean started;

    /**
     * Constructor of the GameStarted.
     * @param guest the distant PublicProfile connected to the current client.
     */
    /*public GameStartedMsg(PublicProfile guest, boolean started) {
        this.guest = guest;
        this.started = started;
    }*/

    public GameStartedMsg(Invitation invitation, boolean started) {
        this.invit = invitation;
        this.started = started;
    }

    /*public void setGuest(PublicProfile guest) {
        this.guest = guest;
    }

    public PublicProfile getGuest() {
        return guest;
    }
*/
    public void setInvit(Invitation invit) {
        this.invit = invit;
    }

    public Invitation getInvit() {
        return this.invit;
    }
    
    public boolean isStarted() {
        return this.started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}
