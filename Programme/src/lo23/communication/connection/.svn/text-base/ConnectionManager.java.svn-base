package lo23.communication.connection;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import lo23.communication.ComManager;
import lo23.communication.handle.ConnectionListener;
import lo23.communication.handle.HandleMessage;
import lo23.communication.handle.HandleReceiveUDPMessage;
import lo23.communication.handle.HandleSendMessageUDP;
import lo23.communication.handle.HandleServerConnection;
import lo23.communication.message.AnswerMsg;
import lo23.communication.message.ChatMsg;
import lo23.communication.message.ConnectionMessage;
import lo23.communication.message.ConstantMsg;
import lo23.communication.message.GameEndedMsg;
import lo23.communication.message.GameStartedMsg;
import lo23.communication.message.InvitMsg;
import lo23.communication.message.Message;
import lo23.communication.message.MoveMsg;
import lo23.communication.message.MulticastAnswer;
import lo23.communication.message.MulticastDisconnection;
import lo23.communication.message.MulticastInvit;
import lo23.communication.message.MulticastMessage;
import lo23.data.ApplicationModel;
import lo23.data.Constant;
import lo23.data.Invitation;
import lo23.data.Move;
import lo23.data.PublicProfile;

/**
 * This class manage the socket connections.
 */
public class ConnectionManager implements ConnectionListener {

    private final ComManager comManager;
    // Multicast Socket
    private final MulticastSocket multicastSocket; //server
    private final HandleReceiveUDPMessage handleMulticast;
    private final DatagramSocket datagramSocket; //client
    // TCP
    private final ServerSocket serverSocket;
    private final HandleServerConnection serverConnection;
    private final HashMap<Socket, HandleMessage> handleMessageMap;
    // Other
    private final HashMap<InetAddress, Socket> socketDirectory;
    private final HashMap<Socket, Invitation> invitationMap;
    private final List<String> myIpAddressList;
    private Socket socketSession;

    /**
     * Constructor of ConnectionManager.
     *
     * @param comManager the comManager
     */
    public ConnectionManager(ComManager comManager) throws Exception {
        this.comManager = comManager;
        handleMessageMap = new HashMap<Socket, HandleMessage>();
        socketDirectory = new HashMap<InetAddress, Socket>();
        invitationMap = new HashMap<Socket, Invitation>();
        myIpAddressList = new ArrayList<String>();
        socketSession = null;

        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            Enumeration<InetAddress> inetAddresses = interfaces.nextElement().getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress aInetAddress = inetAddresses.nextElement();
                if (!aInetAddress.isLoopbackAddress() && !(aInetAddress instanceof Inet6Address)) {
                    myIpAddressList.add(aInetAddress.getHostAddress());
                }
            }
        }

        try {
            multicastSocket = new MulticastSocket(ConnectionParams.multicastPort);
            multicastSocket.joinGroup(InetAddress.getByName(ConnectionParams.multicastAddress));
            handleMulticast = new HandleReceiveUDPMessage(multicastSocket, this);
            new Thread(handleMulticast).start();

            datagramSocket = new DatagramSocket();
            serverSocket = new ServerSocket(ConnectionParams.unicastPort);

            serverConnection = new HandleServerConnection(serverSocket, this);
            new Thread(serverConnection).start();
            serverConnection.waitStarted();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, "Error for the initialisation of the server sockets", ex);
            throw new Exception("Error for the initialisation of the server sockets");
        }
    }

    /**
     * Function which allow us to warn every players on the network that there
     * is a new connection.
     */
    public void sendMulticast() {
        if (comManager.getCurrentUserProfile() != null) {
            PublicProfile profile = comManager.getCurrentUserProfile();
            MulticastInvit message = new MulticastInvit(profile);
            HandleSendMessageUDP handler = new HandleSendMessageUDP(datagramSocket);
            handler.sendMulticast(message);
        } else {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, "Send multicast without publicProfile");
        }
    }

    /**
     * Send a multicast message which indicates the disconnection.
     */
    public void sendMulticastDisconnection() {
        if (comManager.getCurrentUserProfile() != null) {
            PublicProfile profile = comManager.getCurrentUserProfile();
            MulticastDisconnection message = new MulticastDisconnection(profile);
            HandleSendMessageUDP handler = new HandleSendMessageUDP(datagramSocket);
            handler.sendMulticast(message);
        } else {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, "Send multicast without publicProfile");
        }
    }

    /**
     * Function which answer to sendMultiCast().
     */
    private void replyMulticast(String ipAddress) {
        if (comManager.getCurrentUserProfile() != null) {
            PublicProfile profile = comManager.getCurrentUserProfile();
            MulticastAnswer message = new MulticastAnswer(profile);
            HandleSendMessageUDP handler = new HandleSendMessageUDP(datagramSocket);
            handler.send(message, ipAddress);
        }
    }

    /**
     * Send a invitation to a user.
     * @param invitation the invitation from a user
     */
    public void sendInvitation(Invitation invitation) {
        PublicProfile guestProfile = invitation.getGuest();
        InvitMsg invitMsg = new InvitMsg(invitation);
        try {
            InetAddress address = InetAddress.getByName(guestProfile.getIpAddress());
            connect(address);
            Socket socket = socketDirectory.get(address);
            HandleMessage handleMessage = handleMessageMap.get(socket);
            handleMessage.send(invitMsg);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Send a response to a invitation.
     * @param invitation the invitation from a user
     * @param answer the answer to the invitation
     */
    public void sendInvitationAnswer(Invitation invitation, boolean answer) {
        PublicProfile hostProfile = invitation.getHost();
        AnswerMsg answerMsg = new AnswerMsg(invitation, answer);
        InetAddress address;
        try {
            address = InetAddress.getByName(hostProfile.getIpAddress());
            Socket socket = socketDirectory.get(address);
            HandleMessage handleMessage = handleMessageMap.get(socket);
            handleMessage.send(answerMsg);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Start a game session with a user.
     * @param invitation the invitation
     * @param started indicates is the game have to be started
     */
    public void sendGameStarted(Invitation invitation, boolean started) {
        try {
            GameStartedMsg message = new GameStartedMsg(invitation, started);
            InetAddress distantIpAddr = InetAddress.getByName(invitation.getGuest().getIpAddress());
            HandleMessage handleMessage = handleMessageMap.get(socketDirectory.get(distantIpAddr));
            handleMessage.send(message);

            //Ne pas oublier d'annuler les invitations sur l'app locale.
            if (started) {
                socketSession = socketDirectory.get(distantIpAddr);
                cancelInvitations();
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Send a negative answer to each invitations excepted the one which has
     * been accepted.
     */
    private void cancelInvitations() {
        //Envoie d'une reponse négative à toute les demandes précédement reçues
        invitationMap.remove(socketSession);
        HashMap<Socket, Invitation> copyInvitMap = new HashMap<Socket, Invitation>(invitationMap);
        for (Socket invitSock : invitationMap.keySet()) {
            AnswerMsg answerNot = new AnswerMsg(copyInvitMap.get(invitSock), false);
            HandleMessage handleMessageReceivedInvit = handleMessageMap.get(invitSock);
            handleMessageReceivedInvit.send(answerNot);
        }
    }

    /**
     * Send a chat message. (available only when a game session is started)
     * @param message the chat message
     */
    public void sendChatMessage(lo23.data.Message message) {
        ChatMsg chatMsg = new ChatMsg(message);
        HandleMessage handleMessage = handleMessageMap.get(socketSession);
        handleMessage.send(chatMsg);
    }

    /**
     * Send a movement of a piece. (available only when a game session is
     * started)
     * @param move the movement of a piece
     */
    public void sendMovement(Move move) {
        MoveMsg moveMsg = new MoveMsg(move);
        HandleMessage handleMessage = handleMessageMap.get(socketSession);
        handleMessage.send(moveMsg);
    }

    /**
     * Send a constant message.
     * @param constant the constant
     */
    public void sendConstantMessage(Constant constant) {
        ConstantMsg constantMsg = new ConstantMsg(constant);
        HandleMessage handleMessage = handleMessageMap.get(socketSession);
        handleMessage.send(constantMsg);
    }

    /**
     * End the current game session. (available only when a game session is
     * started)
     */
    public void sendGameEnded() {
        if(socketSession != null){//Socket already closed by remote user
            GameEndedMsg message = new GameEndedMsg();
            HandleMessage handleMessage = handleMessageMap.get(socketSession);
            handleMessage.send(message);
            socketSession = null;
        }
    }

    /**
     * Method which notifies the connection on the server socket. (distant)
     * @param socket the new client socket
     */
    @Override
    public void receivedConnection(Socket socket) {
        synchronized (comManager) {
            HandleMessage handleMessage = new HandleMessage(socket, this);
            handleMessage.startHandleReceive();
            socketDirectory.put(socket.getInetAddress(), socket);
            handleMessageMap.put(socket, handleMessage);
        }
    }

    /**
     * Allow to connect to a distant user. (local) (Update socketDirectory and
     * handleMessageMap)
     * @param inetAddress
     */
    private void connect(InetAddress inetAddress) {
        try {
            Socket socket = new Socket(inetAddress, ConnectionParams.unicastPort);
            HandleMessage handleMessage = new HandleMessage(socket, this);
            handleMessage.startHandleReceive();

            socketDirectory.put(socket.getInetAddress(), socket);
            handleMessageMap.put(socket, handleMessage);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, "Error on connection", ex);
        }
    }

    /**
     * Method which notifies the closure of a socket. (local or distant)
     * @param socket the closed socket
     */
    @Override
    public void closedConnection(Socket socket) {
        synchronized (comManager) {
            handleMessageMap.get(socket).closeHandle();
            if (!socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ConnectionManager.class.getName()).log(Level.INFO, "Socket close", ex);
                }
            }

            socketDirectory.remove(socket.getInetAddress());
            handleMessageMap.remove(socket);

            //Si le joueur est en partie
            if (socketSession != null && socket.equals(socketSession)) {
                socketSession = null;
                notifyMessage(new GameEndedMsg());
            } else { // Si le joueur n'est pas en partie (lit les invitations)
                Invitation invitation = invitationMap.get(socket);
                if (invitation != null) {
                    invitationMap.remove(socket);
                    AnswerMsg invitAnswerMsg = new AnswerMsg(invitation, false);
                    notifyMessage(invitAnswerMsg);
                }
            }
        }
    }

    /**
     * Allow to disconnect from a socket.
     * @param socket the socket will be closed
     */
    private void disconnect(Socket socket) {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.WARNING, "Error on disconnection", ex);
        }
    }

    /**
     * Method which be notified when we receive a message
     * @param socket the socket which sent the message
     * @param message the received message
     */
    @Override
    public void receivedMessage(Socket socket, Message message) {
        synchronized (comManager) {
            System.out.println("Message TCP Received : " + message);

            correctIpAddress(socket.getInetAddress(), message);

            if (message instanceof InvitMsg) {
                InvitMsg invitMsg = (InvitMsg) message;
                if (socketSession == null) {
                    invitationMap.put(socket, invitMsg.getInvitation());
                    notifyMessage(message);
                } else {
                    AnswerMsg answerMsg = new AnswerMsg(invitMsg.getInvitation(), false);
                    HandleMessage handleMessage = handleMessageMap.get(socket);
                    handleMessage.send(answerMsg);
                }

            } else if (message instanceof AnswerMsg) {
                if (!((AnswerMsg) message).isAnswer()) {
                    disconnect(socket);
                } else {
                    if (socketSession != null) {
                        sendGameStarted(((AnswerMsg) message).getInvitation(), false);
                    }
                }
                System.out.println("Notify from Com");
                notifyMessage(message);

            } else if (message instanceof GameStartedMsg) {
                GameStartedMsg gameStartedMsg = (GameStartedMsg) message;
                if (gameStartedMsg.isStarted()) {
                    socketSession = socket;
                    cancelInvitations();
                }
                notifyMessage(message);

            } else if (message instanceof ChatMsg) {
                notifyMessage(message);

            } else if (message instanceof MoveMsg) {
                notifyMessage(message);

            } else if (message instanceof ConstantMsg) {
                notifyMessage(message);

            } else if (message instanceof GameEndedMsg) {
                disconnect(socketSession);
            }
        }
    }

    /**
     * Function which manage received message.
     * @param message message received
     */
    @Override
    public void receivedUDPMessage(InetAddress remoteAddress, Message message) {
        synchronized (comManager) {
            System.out.println("Message UDP Received : " + message);
             
            correctIpAddress(remoteAddress, message);

            if (message instanceof MulticastInvit) {
                String ipAddress = ((MulticastInvit) message).getProfile().getIpAddress();
                if (!myIpAddressList.contains(ipAddress)) {
                    replyMulticast(ipAddress);
                    notifyMessage(message);
                }
            } else if (message instanceof MulticastAnswer || message instanceof MulticastDisconnection) {
                notifyMessage(message);
            }
        }
    }

    /**
     * Correct the IP Address in the message which contains the remote public profile.
     * @param remoteAddress the remoteAddress
     * @param message  the message
     */
    private void correctIpAddress(InetAddress remoteAddress, Message message) {
        if (message instanceof MulticastMessage) {
            ((MulticastMessage) message).getProfile().setIpAddress(remoteAddress.getHostAddress());
        } else if (message instanceof ConnectionMessage) {
            if (message instanceof InvitMsg) {
                ((InvitMsg) message).getInvitation().getHost().setIpAddress(remoteAddress.getHostAddress());
            } else if (message instanceof AnswerMsg) {
                ((AnswerMsg) message).getInvitation().getGuest().setIpAddress(remoteAddress.getHostAddress());
            } else if (message instanceof GameStartedMsg) {
                ((GameStartedMsg) message).getInvit().getHost().setIpAddress(remoteAddress.getHostAddress());
            }
        }
    }

    /**
     * Notify messages to the Model in the swing thread.
     * SwingUtilities.invokeLater
     * @param message message received
     */
    private void notifyMessage(Message message) {
        SwingUtilities.invokeLater(new NotifyMessageLater(message));
    }

    /**
     * Class which allow to notify a message later.
     */
    private class NotifyMessageLater implements Runnable {

        private Message message;

        public NotifyMessageLater(Message message) {
            this.message = message;
        }

        @Override
        public void run() {
            ApplicationModel model = comManager.getApplicationModel();
            if (message instanceof InvitMsg) {
                model.getPManager().notifyInvitation(((InvitMsg) message).getInvitation());
            } else if (message instanceof AnswerMsg) {
                model.getPManager().notifyInvitAnswer(((AnswerMsg) message).getInvitation(), ((AnswerMsg) message).isAnswer());
            } else if (message instanceof GameStartedMsg) {
                model.getGManager().notifyGameStarted(((GameStartedMsg) message).getInvit(), ((GameStartedMsg) message).isStarted());
            } else if (message instanceof ChatMsg) {
                model.getGManager().notifyChatMessage(((ChatMsg) message).getMessage());
            } else if (message instanceof MoveMsg) {
                model.getGManager().notifyMovement(((MoveMsg) message).getMove());
            } else if (message instanceof ConstantMsg) {
                model.getGManager().notifyConstantMessage(((ConstantMsg) message).getConstant());
            } else if (message instanceof GameEndedMsg) {
                model.getGManager().notifyGameEnded();
            } else if (message instanceof MulticastAnswer || message instanceof MulticastInvit) {
                model.getPManager().notifyAddProfile(((MulticastMessage) message).getProfile());
            } else if (message instanceof MulticastDisconnection) {
                model.getPManager().notifyProfileDisconnection(((MulticastDisconnection) message).getProfile());
            }
        }
    }
}
