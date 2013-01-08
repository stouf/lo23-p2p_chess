/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.ui.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import lo23.data.Invitation;
import lo23.data.PublicProfile;
import lo23.utils.Enums;
import lo23.utils.JTableButtonMouseListener;
import lo23.utils.ResourceManager;

/**
 * Class IHMList Window 
 * @author Patrick Browne & Mohamed Lahlou
 */
public class IHMList extends javax.swing.JFrame implements PropertyChangeListener, TableModelListener {

    private final IhmLoginModel model;
    private final ImageIcon pawnWhite = new ImageIcon(ResourceManager.getInstance().getResource("PW.png"));
    private final ImageIcon pawnBlack = new ImageIcon(ResourceManager.getInstance().getResource("PB.png"));
    public static String TITLE = "Players list";
    private WaitingDialog waitingDialog;

    /**
     * Creates new form IHMList
     * @param model 
     */
    public IHMList(final IhmLoginModel model) {
        // model.addPropertyChangeListener(this);

        this.model = model;


        initComponents();
        this.setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        this.setTitle(TITLE);

        tablePlayers.setAutoCreateRowSorter(true);
        tablePlayers.removeColumn(tablePlayers.getColumn("id"));
        tablePlayers.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if ((me.getButton() == MouseEvent.BUTTON1) && (me.getClickCount() == 2)) {
                    int num = tablePlayers.rowAtPoint(me.getPoint());
                    String id = (String) tablePlayers.getModel().getValueAt(num, 0);
                    PublicProfile profileSelected = model.getRemoteProfile(id);
                    if (profileSelected != null) {
                        new IhmProfileWindow(model, IhmProfileWindow.READ, profileSelected).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(IHMList.this, "User doesn't exist anymore", "User error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        tablePlayers.getModel().addTableModelListener(this);

        model.addPropertyChangeListener(IhmLoginModel.INVIT_RECEIVE, this);
        model.addPropertyChangeListener(IhmLoginModel.ADD_PLAYER_CONNECTED, this);
        model.addPropertyChangeListener(IhmLoginModel.GAME_ENDED, this);
        model.addPropertyChangeListener(IhmLoginModel.REQUEST_GAME_RESPONSE, this);
        model.addPropertyChangeListener(IhmLoginModel.GAME_ENDED, this);
        model.addPropertyChangeListener(IhmLoginModel.GAME_STARTED, this);


        //TEST
//        try {
//
//            for(PublicProfile p : model.getApplicationModel().getPManager().getLocalPublicProfiles()){
//                PropertyChangeEvent pce = new PropertyChangeEvent("1",IhmLoginModel.ADD_PLAYER_CONNECTED,null,p);
//                model.propertyChange(pce);
//            }
//        } catch (Exception ex){
//            JOptionPane.showMessageDialog(this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
//        }

    }

    /**
     * Action bouton : when a player wants to launch a game  
     * @param evt action event  
     */
    private void launchGameBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String id;
        JButton btn = (JButton) evt.getSource();

        id = (String) btn.getClientProperty("id");
        System.out.println("click launch game btn avec comme id = " + id);
        Enums.COLOR col = chooseColorDialog();
        if (col != null) {
            System.out.println("Send invitation to id = " + id + " with color " + (col == Enums.COLOR.BLACK ? "Black" : "White"));
            try {
                model.sendInvitation(id, col);
                btn.setEnabled(false);
            } catch (Exception ex) {
                 ex.printStackTrace();
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /*
     * Dialog to choose color when a player send an invitation
     */
    private Enums.COLOR chooseColorDialog() {
        Enums.COLOR color = Enums.COLOR.WHITE;
        Object[] colorTab = {pawnWhite, pawnBlack};
        int rang = JOptionPane.showOptionDialog(null,
                "Please choose your color !",
                "Choose Color Dialog",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                colorTab,
                colorTab[0]);
        if (rang == -1) {
            return null;
        } else if (colorTab[rang].equals(pawnBlack)) {
            color = Enums.COLOR.BLACK;
        }
        return color;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTablePlayers = new javax.swing.JScrollPane();
        tablePlayers = new javax.swing.JTable();
        labelTable = new javax.swing.JLabel();
        manageProfileBtn = new javax.swing.JButton();
        javax.swing.JButton disconnectBtn = new javax.swing.JButton();
        reviewGamesBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tablePlayers.setModel(model.getPlayerModel());
        tablePlayers.getColumn("").setCellRenderer(new lo23.utils.JTableButtonRenderer());
        panelTablePlayers.setViewportView(tablePlayers);
        tablePlayers.addMouseListener(new JTableButtonMouseListener(tablePlayers));

        labelTable.setText("Connected Players");

        manageProfileBtn.setText("Manage Profile");
        manageProfileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageProfileBtnActionPerformed(evt);
            }
        });

        disconnectBtn.setText("Disconnect");
        disconnectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectBtnActionPerformed(evt);
            }
        });

        reviewGamesBtn.setText("Review games");
        reviewGamesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reviewGamesBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTablePlayers, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTable)
                        .addGap(0, 409, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(manageProfileBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(reviewGamesBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                        .addComponent(disconnectBtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(labelTable, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTablePlayers, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageProfileBtn)
                    .addComponent(disconnectBtn)
                    .addComponent(reviewGamesBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void disconnectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectBtnActionPerformed
        this.setVisible(false);
        model.disconnect();
        IhmLoginModel ihmLoginModel = new IhmLoginModel(model.getApplicationModel());

        this.dispose();

        new IhmConnexionWindow(ihmLoginModel).setVisible(true);
        ihmLoginModel.refreshProfileList();
    }//GEN-LAST:event_disconnectBtnActionPerformed

    private void manageProfileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageProfileBtnActionPerformed
        new IhmProfileWindow(model, IhmProfileWindow.MODIFY, null).setVisible(true);
    }//GEN-LAST:event_manageProfileBtnActionPerformed

    private void reviewGamesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reviewGamesBtnActionPerformed
        new IhmListGames(model).setVisible(true);
    }//GEN-LAST:event_reviewGamesBtnActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        this.setVisible(false);
        model.disconnect();

        this.dispose();

    }//GEN-LAST:event_formWindowClosing
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelTable;
    private javax.swing.JButton manageProfileBtn;
    private javax.swing.JScrollPane panelTablePlayers;
    private javax.swing.JButton reviewGamesBtn;
    private javax.swing.JTable tablePlayers;
    // End of variables declaration//GEN-END:variables

    @Override
    /*
     * Listener for network events : when a player receives
     * an invitation, or when he receives a response from a 
     * player he invited.
     */
    public void propertyChange(PropertyChangeEvent pce) {
        if (this.isVisible()) {
            if (pce.getPropertyName().equals(IhmLoginModel.INVIT_RECEIVE)) {
                Invitation invitation = (Invitation) pce.getNewValue();
                boolean b = openInvitationDialog(invitation);
                try {
                    model.sendInvitationAnswer(invitation, b);
                    if (b) {
                        waitingDialog = new WaitingDialog(this, true);
                        waitingDialog.setVisible(true);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (pce.getPropertyName().equals(IhmLoginModel.REQUEST_GAME_RESPONSE)) {
                boolean resp = (Boolean) pce.getOldValue();
                Invitation invitation = (Invitation) pce.getNewValue();
                if (resp) {
                    try {
                        model.loadGame(invitation);
                        //System.out.println("Launching Game...");
                        //MainWindow mainWindow = new lo23.ui.grid.MainWindow(model.getApplicationModel());
                        //mainWindow.setVisible(true);
                        this.setVisible(false);
                        model.sendGameStarted(invitation);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                    }
                }

                PublicProfile guest = invitation.getGuest();
                String idGuest = guest.getProfileId();
                for (JButton btn : model.getListLaunchGameBtn()) {
                    if (btn.getClientProperty("id").equals(idGuest)) {
                        btn.setEnabled(true);
                    }
                }
            }
            if (pce.getPropertyName().equals(IhmLoginModel.GAME_STARTED)) {
                Boolean isReady = (Boolean) pce.getOldValue();
                Invitation invit = (Invitation) pce.getNewValue();
                
                waitingDialog.setVisible(false);
                waitingDialog.dispose();
                
                if (isReady) {
                    try {
                        model.loadGame(invit);
                        //System.out.println("Launching Game...");
                        //MainWindow mainWindow = new lo23.ui.grid.MainWindow(model.getApplicationModel());
                        //mainWindow.setVisible(true);
                        this.setVisible(false);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                    }
                }
               

                /*PublicProfile guest = invit.getGuest();
                String idGuest = guest.getProfileId();
                for(JButton btn : model.getListLaunchGameBtn()){
                if(btn.getClientProperty("id").equals(idGuest)){
                btn.setEnabled(true);
                }
                }*/
            }
        }
        if (pce.getPropertyName().equals(IhmLoginModel.GAME_ENDED)) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                this.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(IHMList.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(IHMList.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(IHMList.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(IHMList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /*
     * Dialog to accept or deny an invitation from a remote user 
     * @param invit : the invitation 
     */
    private boolean openInvitationDialog(Invitation invit) {
        int response = -1;
        PublicProfile profile = invit.getHost();
        response = JOptionPane.showConfirmDialog(null, "Accept/deny invitation from " + profile.getPseudo() + " ?", "Accept/deny invitation from " + profile.getPseudo() + " ?", JOptionPane.YES_NO_OPTION);
        System.out.println("Invitation : " + response);
        if (response == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void tableChanged(TableModelEvent tme) {
        if (tme.getType() == TableModelEvent.INSERT) {
            int row = tme.getLastRow();
            TableModel tm = (TableModel) tme.getSource();
            Object o = tm.getValueAt(row, tm.getColumnCount() - 1);
            if (o instanceof JButton) {
                ((JButton) o).addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        launchGameBtnActionPerformed(ae);
                    }
                });
            }
        }
    }
}
