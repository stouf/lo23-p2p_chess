package lo23.communication.handle;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import lo23.communication.connection.ConnectionManager;
import lo23.communication.connection.ConnectionParams;
import lo23.communication.message.Message;

/**
 * Handle to send a message on a TCP Socket.
 */
public class HandleSendMessageUDP {

    private DatagramSocket socket;

    /**
     * Constructor of HandleSendMessage.
     * @param socket the socket
     */
    public HandleSendMessageUDP(DatagramSocket socket) {
        this.socket = socket;
    }

     /**
     * This function manage messages serialization.
     * @param message to serialize
     * @return : byte[] of the message
     */
    private byte[] serialize(Message message){
       ByteArrayOutputStream b_out = new ByteArrayOutputStream();
       ObjectOutputStream o_out = null;
       try {
           o_out = new ObjectOutputStream(b_out);
       } catch (IOException ex) {
           Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
       }

       try {
           // Message serialization
           o_out.writeObject(message);
       } catch (IOException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, "Error during the message serialization", ex);
       }
       byte[] b = b_out.toByteArray();

       return b;
    }
    /**
     * Send a message.
     * @param message the message
     */
    public void send(Message message, String ipAddress) {
       byte b[] = this.serialize(message);
       DatagramPacket dgram = null;
       try {
           dgram = new DatagramPacket(b, b.length, InetAddress.getByName(ipAddress), ConnectionParams.multicastPort); // multicast
           socket.send(dgram);
       } catch (Exception ex) {
       Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, "Error during the datagramme creation", ex);
       }
    }
    
    /**
     * Send a message in Multicast.
     * @param message the message.
     */
    public void sendMulticast(Message message) {
        send(message, ConnectionParams.multicastAddress);
    }
    
}
