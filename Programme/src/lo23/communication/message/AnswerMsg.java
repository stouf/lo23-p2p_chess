package lo23.communication.message;

import lo23.data.Invitation;

/**
 * This message is sent by distant user to answer InvitMsg.
 */
public class AnswerMsg extends ConnectionMessage{

    private Invitation invitation;

    private boolean answer;

    /**
     * Constructor of the AnswerMsg.
     * @param invitation the invitation received by the current user which refers to a new game or a loaded game.
     * @param answer the answer provided by the current user. 
     */
    public AnswerMsg(Invitation invitation, boolean answer) {
        this.invitation = invitation;
        this.answer = answer;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public Invitation getInvitation() {
        return invitation;
    }

    public void setInvitation(Invitation invitation) {
        this.invitation = invitation;
    }
}
