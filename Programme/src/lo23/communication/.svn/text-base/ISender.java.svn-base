package lo23.communication;

import lo23.data.Constant;
import lo23.data.Invitation;
import lo23.data.Message;
import lo23.data.Move;

/**
 * This interface is used for send messages to distant users,
 * It's implemented by the network module.
 * Check also IReceiveListener to understand the functioning of messages.
 */
public interface ISender {

    /**
     * Send a message to all users in order to receive a response
     * from each users.
     */
    public void sendMulticast();
    
    
    /**
     * Send a message to all users in order to receive a response
     * from each users.
     */
    public void sendMulticastDisconnection();
    
    /**
     * Send a invitation to a user.
     * @param invitation the invitation from a user
     */
    public void sendInvitation(Invitation invitation);
    
    /**
     * Send a response to a invitation.
     * @param invitation the invitation from a user
     * @param answer the answer to the invitation
     */
    public void sendInvitationAnswer(Invitation invitation, boolean answer);
    
    /**
     * Start a game session with a user.
     * @param userProfile the user who will be the opponent
     * @param started indicates is the game have to be started
     */
    public void sendGameStarted(Invitation invit, boolean started);
    
    /**
     * Send a chat message.
     * (available only when a game session is started)
     * @param message the chat message
     */
    public void sendChatMessage(Message message);
    
    /**
     * Send a movement of a piece.
     * (available only when a game session is started)
     * @param move the movement of a piece
     */
    public void sendMovement(Move move);
    
    /**
     * Send a constant message.
     * @param constant the constant
     */
    public void sendConstantMessage(Constant constant);
    
    /**
     * End the current game session.
     * (available only when a game session is started)
     */
    public void sendGameEnded();
    
}
