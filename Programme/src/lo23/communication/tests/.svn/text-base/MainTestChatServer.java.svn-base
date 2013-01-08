package lo23.communication.tests;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lo23.communication.connection.ConnectionManager;
import lo23.communication.connection.ConnectionParams;
import lo23.communication.handle.ConnectionListener;
import lo23.communication.handle.HandleMessage;
import lo23.communication.handle.HandleServerConnection;
import lo23.communication.message.Message;

public class MainTestChatServer {

    // Test Server pour un Chat =>
    // Le serveur tourne indéfiniment
    // Il envoie un message à tout les clients connectés
    // Il affiche tout les messages reçu de ces clients
    private static ServerSocket serverSocket;
    private static HandleServerConnection serverConnection;
    private static HashMap<Socket, HandleMessage> handleMessageMap;
    private static ConnectionListener connectionListener;

    public static void main(String[] args) {
        connectionListener = new ConnectionListenerImpl();
        handleMessageMap = new HashMap<Socket, HandleMessage>();

        //Initialisation
        try {
            serverSocket = new ServerSocket(ConnectionParams.unicastPort);
            serverConnection = new HandleServerConnection(serverSocket, connectionListener);
            new Thread(serverConnection).start();
            serverConnection.waitStarted();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, "Error for the initialisation of the server sockets", ex);
        }

        //Envoie des messages
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = scanner.nextLine();
            TestMsg testMsg = new TestMsg(text);
            for (HandleMessage handleMessage : handleMessageMap.values()) {
                handleMessage.send(testMsg);
            }
        }
    }

    private static class ConnectionListenerImpl implements ConnectionListener {

        @Override
        public void receivedConnection(Socket socket) {
            HandleMessage handleMessage = new HandleMessage(socket, this);
            handleMessage.startHandleReceive();
            handleMessageMap.put(socket, handleMessage);
        }

        @Override
        public void closedConnection(Socket socket) {
            handleMessageMap.get(socket).closeHandle();

            if (!socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ConnectionManager.class.getName()).log(Level.INFO, "Socket close", ex);
                }
            }

            handleMessageMap.remove(socket);
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
