package lo23.communication.tests;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import lo23.communication.ComManager;
import lo23.data.ApplicationModel;
import lo23.data.Game;
import lo23.data.Invitation;
import lo23.data.Move;
import lo23.data.NewInvitation;
import lo23.data.Player;
import lo23.data.Position;
import lo23.data.PublicProfile;
import lo23.data.exceptions.WrongInvitation;
import lo23.data.managers.GameManager;
import lo23.data.managers.Manager;
import lo23.data.managers.ProfileManager;
import lo23.data.pieces.King;
import lo23.data.tests.TestInit;
import lo23.ui.login.IhmLoginModel;
import lo23.utils.Enums;
import lo23.utils.Enums.COLOR;

public class SuperMain implements PropertyChangeListener {

    public static void main(String[] args) {
        try {
            SuperMain main = new SuperMain();
        } catch (Exception ex) {
            Logger.getLogger(SuperMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private PublicProfile hostProfile;
    private ApplicationModel appModel;
    private JFrame frame;
    private JList userJList;
    private DefaultListModel userListModel;

    public SuperMain() throws Exception {
        try {
            String addressIp = null;
            Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                Enumeration<InetAddress> i = e.nextElement().getInetAddresses();
                while (i.hasMoreElements()) {
                    InetAddress a = i.nextElement();
                    if (!a.isLoopbackAddress() && !(a instanceof Inet6Address)) {
                        addressIp = a.getHostAddress();
                        System.out.print("\nIP : " + addressIp);
                    }
                }
            }

            hostProfile = new PublicProfile("1", "Nous", Enums.STATUS.CONNECTED, addressIp, null , "Nom", "Prénom", 23, 0, 0, 0);

            appModel = new ApplicationModel();
            appModel.setComManager(new ComManager(appModel));
            appModel.setGameManager(new GameManager(appModel));
            appModel.setProfileManager(new ProfileManager(appModel));

            frame = new JFrame("SuperMain");
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
            JButton moveButton = new JButton("3. Move");
            panelButton.add(multicastButton);
            panelButton.add(invitButton);
            panelButton.add(moveButton);

            multicastButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    userListModel.clear();
                    appModel.getPManager().startProfilesDiscovery();
                    ((Manager) appModel.getPManager()).subscribe(SuperMain.this, IhmLoginModel.ADD_PLAYER_CONNECTED);
                }
            });

            invitButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (userJList.getSelectedValue() != null) {
                        PublicProfile profile = (PublicProfile) userJList.getSelectedValue();
                        NewInvitation invitation = new NewInvitation(COLOR.BLACK, 3600, hostProfile, profile);
                        appModel.getPManager().sendInvitation(invitation);
                        ((Manager) appModel.getPManager()).subscribe(SuperMain.this, IhmLoginModel.INVIT_RECEIVE);
                    }
                }
            });

            moveButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (userJList.getSelectedValue() != null) {
                        PublicProfile profile = (PublicProfile) userJList.getSelectedValue();
                        NewInvitation invitation = new NewInvitation(COLOR.BLACK, 3600, hostProfile, profile);

                        appModel.getComManager().sendInvitation(invitation);
                        //appModel.getComManager().sendGameStarted(invitation.getGuest(), true);

                        Player p1 = new Player(COLOR.WHITE, 0, null);
                        Player p2 = new Player(COLOR.BLACK, 0, null);

                        Game gm = null;
                        try {
                            gm = appModel.getGManager().createGame(invitation);
                        } catch (WrongInvitation ex) {
                            Logger.getLogger(TestInit.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        Move m = new Move(new Position(0, 0), new Position(0, 2), new King(new Position(0, 0), p1, gm));
                        appModel.getGManager().sendMove(m);

                        ((Manager) appModel.getPManager()).subscribe(SuperMain.this, "move");
                    }
                }
            });

            frame.setVisible(true);
            frame.toFront();

        } catch (SocketException ex) {
            Logger.getLogger(MainTest1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IhmLoginModel.ADD_PLAYER_CONNECTED)) {
            userListModel.addElement(evt.getNewValue());
            System.out.println(evt.getNewValue());
        } else if (evt.getPropertyName().equals(IhmLoginModel.INVIT_RECEIVE)) {
            Invitation invit = (Invitation) evt.getNewValue();
            System.out.println("Invitation reçu de " + invit.getHost());
        } else if (evt.getPropertyName().equals("move")) {
            System.out.println("Coup reçu");
        }
    }
}
