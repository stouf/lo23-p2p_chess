/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lo23.communication.tests;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lo23.communication.connection.ConnectionParams;
import lo23.communication.handle.HandleSendMessageUDP;
import lo23.communication.message.MulticastInvit;
import lo23.data.PublicProfile;
import lo23.utils.Enums.STATUS;


/**
 *
 * @author Paco
 */

public class MainTestMulticast {

    public static void main(String[] args) {
        PublicProfile profile = new PublicProfile("1", "penotVin", STATUS.INGAME, "172.25.23.139", null, "Vincent", "Penot", 23, 0, 0, 0);
        
        MulticastInvit message = new MulticastInvit(profile);
        MulticastSocket multicastSocket = null;
        DatagramSocket datagramSocket = null;

        try {
            multicastSocket = new MulticastSocket(ConnectionParams.multicastPort);
        } catch (IOException ex) {
            Logger.getLogger(MainTestMulticast.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            multicastSocket.joinGroup(InetAddress.getByName(ConnectionParams.multicastAddress));
        } catch (IOException ex) {
            Logger.getLogger(MainTestMulticast.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            datagramSocket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(MainTestMulticast.class.getName()).log(Level.SEVERE, null, ex);
        }

        HandleSendMessageUDP handler = new HandleSendMessageUDP(datagramSocket);
        handler.sendMulticast(message);
    
    }

}