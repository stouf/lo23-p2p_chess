package lo23.communication.handle;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lo23.communication.message.ConnectionMessage;

/**
 * This class handle the reception of a message for Socket. (UDP Socket)
 */
public class HandleReceiveUDPMessage extends HandleRunnable {

    private DatagramSocket socket;
    private ObjectInputStream objectInput;
    private ConnectionListener connListener;

    /**
     * Constructor of HandleReceiveMessage.
     *
     * @param socket the socket concerned
     * @param listener the Listener which will be notified
     */
    public HandleReceiveUDPMessage (DatagramSocket socket, ConnectionListener connListener) {
        this.socket = socket;
        this.connListener = connListener;
    }

    /**
     * This method is launched when the Runnable is started.
     */
    @Override
    public void run() {
        try {
            byte[] b = new byte[65535];
            ByteArrayInputStream b_in = new ByteArrayInputStream(b);
            notifyStart();
            while (getStart()) {
                DatagramPacket dgram = new DatagramPacket(b, b.length);
                socket.receive(dgram); // blocks
                ObjectInputStream o_in = new ObjectInputStream(b_in);
                ConnectionMessage message = (ConnectionMessage) o_in.readObject();
                dgram.setLength(b.length); // must reset length field!
                b_in.reset(); // reset so next read is from start of byte[] again
                
                connListener.receivedUDPMessage(dgram.getAddress(), message);
            }
        } catch (SocketException se) {
            Logger.getLogger(HandleReceiveMessage.class.getName()).log(Level.INFO, "The socket was closed locally", se);
        } catch (EOFException ee) {
            Logger.getLogger(HandleReceiveMessage.class.getName()).log(Level.INFO, "The socket was closed afar", ee);
        } catch (Exception e) {
            Logger.getLogger(HandleReceiveMessage.class.getName()).log(Level.SEVERE, "Error for the reception of a message", e);
        } finally {
            try {
                objectInput.close();
            } catch (IOException e) {
                 Logger.getLogger(HandleReceiveMessage.class.getName()).log(Level.INFO, null, e);
            }
        }
    }
    
}
