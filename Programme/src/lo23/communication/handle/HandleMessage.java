package lo23.communication.handle;

import java.net.Socket;
import lo23.communication.message.Message;

/**
 * Class which contains the handle for send and receive a message.
 * see HandleReceiveMessage and HandleSendMessage
 */
public class HandleMessage {
    
    private HandleReceiveMessage handleReceiveMessage;
    private HandleSendMessage handleSendMessage;
    
    /**
     * Constructor of HandleMessage.
     * @param socket the socket
     * @param connListener the connection listener
     */
    public HandleMessage (Socket socket, ConnectionListener connListener) {
        handleReceiveMessage = new HandleReceiveMessage(socket, connListener);
        handleSendMessage = new HandleSendMessage(socket);
    }
    
    /**
     * Start the handle which receives the messages.
     * see HandleReceiveMessage
     */
    public void startHandleReceive() {
        new Thread(handleReceiveMessage).start();
        handleReceiveMessage.waitStarted();
    }
    
    /**
     * Send a message using the handle which sends the messages.
     * see HandleSendMessage
     * @param message 
     */
    public void send(Message message) {
        handleSendMessage.send(message);
    }
    
    /**
     * Close the handleSendMessage and the handleReceiveMessage.
     */
    public void closeHandle() {
        handleSendMessage.closeHandle();
        handleReceiveMessage.closeHandle();
    }
}
