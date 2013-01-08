package lo23.communication.tests;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lo23.communication.connection.ConnectionManager;
import lo23.communication.connection.ConnectionParams;
import lo23.communication.handle.ConnectionListener;
import lo23.communication.handle.HandleMessage;
import lo23.communication.message.Message;

public class MainTestChatClient {

    // Test Client pour un Chat =>
    // le client envoie 3 message puis se termine
    // Il envoie un message seulement au serveur
    // Il recoit également les messages du serveur
    private static Socket socket;
    private static HandleMessage handleMessage;
    private static ConnectionListener connectionListener;

    public static void main(String[] args) {
        connectionListener = new ConnectionListenerImpl();

        //Initialisation
        try {
            socket = new Socket("localhost", ConnectionParams.unicastPort);
            handleMessage = new HandleMessage(socket, connectionListener);
            handleMessage.startHandleReceive();

        } catch (UnknownHostException ex) {
            Logger.getLogger(MainTestChatClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainTestChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Envoie des messages
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            String text = scanner.nextLine();
            TestMsg testMsg = new TestMsg(text);
            handleMessage.send(testMsg);
        }

        // Fermeture du socket
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(MainTestChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static class ConnectionListenerImpl implements ConnectionListener {

        @Override
        public void receivedConnection(Socket socket) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void closedConnection(Socket socket) {
            handleMessage.closeHandle();

            if (!socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ConnectionManager.class.getName()).log(Level.INFO, "Socket close", ex);
                }
            }
        }

        @Override
        public void receivedMessage(Socket socket, Message message) {
            if (message instanceof TestMsg) {
                System.out.println("receive: " + ((TestMsg) message).getContent());
            }
        }

        @Override
        public void receivedUDPMessage(InetAddress remoteAddress, Message message) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }
}
