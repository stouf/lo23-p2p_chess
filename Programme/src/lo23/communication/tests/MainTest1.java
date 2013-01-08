package lo23.communication.tests;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import lo23.communication.ComManager;
import lo23.data.ApplicationModel;
import lo23.data.Constant;
import lo23.data.Event;
import lo23.data.Game;
import lo23.data.GridState;
import lo23.data.Invitation;
import lo23.data.Message;
import lo23.data.Move;
import lo23.data.NewInvitation;
import lo23.data.Position;
import lo23.data.Profile;
import lo23.data.PublicProfile;
import lo23.data.exceptions.FileNotFoundException;
import lo23.data.exceptions.NoIdException;
import lo23.data.managers.GameManagerInterface;
import lo23.data.managers.Manager;
import lo23.data.managers.ProfileManagerInterface;
import lo23.data.pieces.GamePiece;
import lo23.utils.Enums;
import lo23.utils.Enums.COLOR;
import lo23.utils.Enums.CONSTANT_TYPE;
import lo23.utils.Enums.PLAYER_RESULT;
import lo23.utils.Enums.STATUS;


public class MainTest1 {
    
    public static void main(String[] args) {
        try {
            MainTest1 main = new MainTest1();
        } catch (Exception ex) {
            Logger.getLogger(MainTest1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Profile hostProfile;
    private ApplicationModel appModel;
    private JFrame frame;
    private JList userJList;
    private DefaultListModel userListModel;
    
    public MainTest1() throws Exception {
        try {            
            String addressIp = null;
            Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); 
            while (e.hasMoreElements()) { 
                Enumeration<InetAddress> i = e.nextElement().getInetAddresses(); 
                while (i.hasMoreElements()){ 
                    InetAddress a = i.nextElement();
                    if (!a.isLoopbackAddress() && !(a instanceof Inet6Address)) {
                        addressIp = a.getHostAddress();
                        System.out.print("\nIP : "+ addressIp);
                    }
                }
            }
            
            hostProfile = new Profile("1", "Luc", "pass".toCharArray(), Enums.STATUS.CONNECTED, addressIp, null, "Nom", "Prénom", 23);
            
            appModel = new ApplicationModel();
            appModel.setComManager(new ComManager(appModel));
            appModel.setGameManager(new MyGameManagerMock(appModel));
            appModel.setProfileManager(new MyProfileManagerMock(appModel));
            
            frame = new JFrame("MainTest1");
            frame.setSize(new Dimension(400, 300));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setLayout(new BorderLayout());
            userListModel = new DefaultListModel();
            userJList = new JList(userListModel);
            frame.getContentPane().add(userJList, BorderLayout.CENTER);
            
            JPanel panelButton = new JPanel(new FlowLayout());
            frame.getContentPane().add(panelButton, BorderLayout.PAGE_END);
            JButton multicastButton = new JButton("1. Multicast");
            JButton invitButton = new JButton("2. Invitation");
            panelButton.add(multicastButton);
            panelButton.add(invitButton);
            
            multicastButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userListModel.clear();
                    appModel.getComManager().sendMulticast();
                }
            });
            
            invitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (userJList.getSelectedValue() != null) {
                        PublicProfile profile = (PublicProfile) userJList.getSelectedValue();
                        NewInvitation invitation = new NewInvitation(COLOR.BLACK, 3600, hostProfile.getPublicProfile(), profile);
                        appModel.getComManager().sendInvitation(invitation);
                    }
                }
            });
            
            frame.setVisible(true);
            frame.toFront();
            
        } catch (SocketException ex) {
            Logger.getLogger(MainTest1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private class MyGameManagerMock extends Manager implements GameManagerInterface {
        
        public MyGameManagerMock(ApplicationModel model) {
            super(model);
        }
        
        @Override
        public void save() throws NoIdException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Game load(long gameId) throws FileNotFoundException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Move createMove(Position to, GamePiece piece) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void sendMove(Move move) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void playMove(Move move) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Message createMessage(String content) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void sendMessage(Message message) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void saveMessage(Message message) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public ArrayList<Event> getHistory() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void notifyChatMessage(Message message) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Game createGame(Invitation invitation) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Constant createConstant(CONSTANT_TYPE constant) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void sendConstant(Constant constant) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void notifyConstantMessage(Constant constant) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void saveConstant(Constant constant) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void notifyGameEnded(PLAYER_RESULT gameResult) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Game notifyGameStarted(Invitation invitation) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public ArrayList<Game> getListStopGames() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public ArrayList<Game> getListStartGames() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public ArrayList<Game> getListAllGames() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Game getCurrentGame() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void notifyMovement(Move move) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void notifyGameStarted(Invitation invit, boolean ans) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void sendGameStarted(Invitation invit, boolean ans) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void sendGameEnded() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

		@Override
		public void notifyGameEnded() {
			// TODO Auto-generated method stub
			
		}

        public Stack<GridState> getGridStates() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
    private class MyProfileManagerMock extends Manager implements ProfileManagerInterface {
        
        public MyProfileManagerMock(ApplicationModel model) {
            super(model);
        }
        
        @Override
        public Profile getCurrentProfile() {
            return MainTest1.this.hostProfile;
        }

        @Override
        public Profile createProfile(String profileId, String pseudo, char[] password, STATUS status, String ipAddress, ImageIcon avatar, String name, String firstName, int age) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void startProfilesDiscovery() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean connection(String profileId, char[] password) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public ArrayList<PublicProfile> getLocalPublicProfiles() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void saveProfile() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Profile loadProfile(String profileId) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void notifyInvitation(Invitation invitation) {
            int n = JOptionPane.showConfirmDialog(frame, "Vous avez reçu une invitation de : " + invitation.getHost().toString() + " ! Voulez-vous acceptez ?");
            if ( n == JOptionPane.OK_OPTION ) {
                //Accept
                getApplicationModel().getComManager().sendInvitationAnswer(invitation, true);
            } else {
                //Deny
                getApplicationModel().getComManager().sendInvitationAnswer(invitation, false);
            }
        }

        @Override
        public Invitation createInvitation(PublicProfile guest, COLOR color, long duration) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void sendInvitation(Invitation invitation) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void notifyAddProfile(PublicProfile publicProfile) {
            userListModel.addElement(publicProfile);
        }

        @Override
        public void notifyInvitAnswer(Invitation invitation, boolean answer) {
            if ( answer ) {
                //invitation.g
                //getApplicationModel().getComManager().sendGameStarted(hostProfile.getPublicProfile(), true);
            }
        }

        @Override
        public void exportProfile(String filePath) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void importProfile(String filePath) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void disconnect() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void notifyProfileDisconnection(PublicProfile publicProfile) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void sendInvitationAnswer(Invitation invitation, boolean answer) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean removeProfile(PublicProfile p, char[] pass) throws FileNotFoundException, IOException, ClassNotFoundException {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
    
}
