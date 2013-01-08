package lo23.communication;

import lo23.data.Constant;
import lo23.data.Invitation;
import lo23.data.Message;
import lo23.data.Move;
import lo23.data.PublicProfile;

/**
 * This interface allow to notify a receive message,
 * It needs to be implemented by the model.
 * Check also ISender to understand the functioning of messages.
 */
public interface IReceiveListener {
    
    /**
     * Notify the message receive in response to sendMulticast.
     * @param userProfile the user who responded
     */
    public void notifyAddUser(PublicProfile userProfile);
    
    /**
     * Notify a invitation.
     * @param invitation the invitation from a user
     */
    public void notifyInvitation(Invitation invitation);
    
    /**
     * Notify the response to a invitation.
     * @param invitation the invitation from a user
     * @param answer the answer of the invitation
     */
    public void notifyInvitationAnswer(Invitation invitation, boolean answer);
    
    /**
     * Notify the start of a game session.
     * @param userProfile the user who start the game session
     * @param started indicates is the game have to be started
     */
    public void notifyGameStarted(PublicProfile userProfile, boolean started);
    
    /**
     * Notify a chat message.
     * (only when a game session is started)
     * @param message the chat message
     */
    public void notifyChatMessage(Message message);
    
    /**
     * Notify a movement of a piece.
     * (only when a game session is started)
     * @param move the movement of a piece
     */
    public void notifyMovement(Move move);
    
    
    /**
     * Notify a constant message.
     * (only when a game session is started)
     * @param constantType the constant
     */
    public void notifyConstantMessage(Constant constant);
    
    /**
     * Notify the end of the current game session.
     * (only when a game session is started)
     */
    public void notifyGameEnded();
    
}
