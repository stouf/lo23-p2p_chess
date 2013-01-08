package lo23.communication.handle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class handle the connection on the server.
 */
public class HandleServerConnection extends HandleRunnable {

    private ServerSocket serverSocket;
    private ConnectionListener listener;

    /**
     * Constructor of HandleServerConnection.
     *
     * @param serverSocket
     * @param listener
     */
    public HandleServerConnection(ServerSocket serverSocket, ConnectionListener listener) {
        this.serverSocket = serverSocket;
        this.listener = listener;
    }

    /**
     * This method is launched when the Thread is started.
     */
    @Override
    public void run() {
        notifyStart();
        Socket clientSocket;
        
        while (getStart()) {
            try {
                clientSocket = serverSocket.accept();
                listener.receivedConnection(clientSocket);
            } catch (IOException ex) {
                Logger.getLogger(HandleServerConnection.class.getName()).log(Level.SEVERE, "Error on the connection (serverSocket.accept)", ex);
            }
        }
    }

}
