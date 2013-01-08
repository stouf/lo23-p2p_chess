package lo23.communication;

import lo23.communication.connection.ConnectionManager;
import lo23.data.ApplicationModel;
import lo23.data.Constant;
import lo23.data.Invitation;
import lo23.data.Message;
import lo23.data.Move;
import lo23.data.PublicProfile;
import lo23.data.managers.Manager;

/**
 * This is the main class of the communication module.
 */
public class ComManager extends Manager implements ISender {

    private ConnectionManager connectionManager;
    private ApplicationModel applicationModel;
    
    /**
     * Constructor of the ComManager.
     * @param profile the profile of the current user
     * @param receiveListener the listener who will be notified
     */
    public ComManager(ApplicationModel applicationModel) throws Exception {
        super(applicationModel);
        this.applicationModel = applicationModel;
        this.connectionManager = new ConnectionManager(this);
    }
    
    public PublicProfile getCurrentUserProfile() {
        PublicProfile profile = null;
        if (applicationModel.getPManager().getCurrentProfile() != null ) {
            profile = applicationModel.getPManager().getCurrentProfile().getPublicProfile();
        }
        return profile;
    }
    
    @Override
    public synchronized void sendMulticast() {
       connectionManager.sendMulticast();
    }
    
    @Override
    public synchronized void sendMulticastDisconnection(){
        connectionManager.sendMulticastDisconnection();
    }

    @Override
    public synchronized void sendInvitation(Invitation invitation) {
        connectionManager.sendInvitation(invitation);
    }

    @Override
    public synchronized void sendInvitationAnswer(Invitation invitation, boolean answer) {
        connectionManager.sendInvitationAnswer(invitation, answer);
    }

    @Override
    public synchronized void sendGameStarted(Invitation invit, boolean started) {
        connectionManager.sendGameStarted(invit, started);
    }

    @Override
    public synchronized void sendChatMessage(Message message) {
        connectionManager.sendChatMessage(message);
    }

    @Override
    public synchronized void sendMovement(Move move) {
        connectionManager.sendMovement(move);
    }

    @Override
    public synchronized void sendConstantMessage(Constant constant) {
        connectionManager.sendConstantMessage(constant);
    }
    
    @Override
    public synchronized void sendGameEnded() {
        connectionManager.sendGameEnded();
    }
    
}
