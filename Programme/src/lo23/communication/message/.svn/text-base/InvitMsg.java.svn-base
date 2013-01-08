package lo23.communication.message;

import lo23.data.Invitation;

/**
 * This message is sent while inviting a distant user to play.
 */
public class InvitMsg extends ConnectionMessage {

    private Invitation invitation;

    /**
     * Constructor of the InvitMsg.
     * @param invitation the invitation sent by the current user which refers to a new game or a loaded game.
     */
    public InvitMsg(Invitation invitation) {
        this.invitation = invitation;
    }
    
    public Invitation getInvitation() {
        return invitation;
    }

    public void setInvitation(Invitation invitation) {
        this.invitation = invitation;
    }

}
