package lo23.communication.handle;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import lo23.communication.message.Message;

/**
 * Handle to send a message on a TCP Socket.
 */
public class HandleSendMessage {

    private ObjectOutputStream objectOutput;

    /**
     * Constructor of HandleSendMessage.
     * @param socket the socket
     */
    public HandleSendMessage(Socket socket) {
        try {
            objectOutput = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            Logger.getLogger(HandleSendMessage.class.getName()).log(Level.SEVERE, "Error for the initialisation", e);
        }
    }

    /**
     * Send a message.
     * @param message the message
     */
    public void send(Message message) {
        try {
            System.out.println("Send Message : " + message);
            objectOutput.writeObject(message);
        } catch (IOException e) {
            Logger.getLogger(HandleSendMessage.class.getName()).log(Level.SEVERE, "Error for sending a message", e);
        }
    }

    /**
     * Close the handle.
     * ObjectOutputStream
     */
    public void closeHandle() {
        try {
            objectOutput.close();
        } catch (IOException ex) {
            Logger.getLogger(HandleReceiveMessage.class.getName()).log(Level.SEVERE, "Error for closing the Handle", ex);
        }
    }
}
