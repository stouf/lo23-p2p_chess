/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.ui.login;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import lo23.data.ApplicationModel;
import lo23.data.Game;
import lo23.data.Invitation;
import lo23.data.NewInvitation;
import lo23.data.PublicProfile;
import lo23.data.ResumeGame;
import lo23.data.exceptions.FileNotFoundException;
import lo23.data.exceptions.WrongInvitation;
import lo23.data.managers.GameManager;
import lo23.data.managers.GameManagerInterface;
import lo23.data.managers.Manager;
import lo23.data.managers.ProfileManager;
import lo23.data.managers.ProfileManagerInterface;
import lo23.ui.grid.MainWindow;
import lo23.ui.login.mockManager.ProfileManagerMock;
import lo23.utils.Enums;
import lo23.utils.Enums.STATUS;
import lo23.utils.ResourceManager;

/**
 * IHMLogin model
 * @author Esteban
 */
public class IhmLoginModel implements PropertyChangeListener{
   
    
    public static final String ADD_PLAYER_CONNECTED = "add-player-connected";
    public static final String DELETE_PLAYER_DISCONNECTED = "delete-player-disconnected";
    public static final String REQUEST_GAME_RESPONSE = "request-game-response";
    public static final String GAME_STARTED = "game_started";
    public static final String INVIT_RECEIVE = "invit-receive";
    public static final String INVIT_EXPIRED = "invit-expired";
    public static final String GAME_ENDED = "game-ended";

    private final ImageIcon ONLINEICON = new ImageIcon(ResourceManager.getInstance().getResource("status_online.png.png"));
    private final ImageIcon OFFLINEICON = new ImageIcon(ResourceManager.getInstance().getResource("status_offline.png"));
    
    public PropertyChangeSupport pcs;
    public ArrayList<Long> idEndGames;
    public ArrayList<Long> idStartGames;
    public ArrayList<Long> idPlayersConnected;
    private ApplicationModel appModel;

    private PlayerModel listPlayers;
    private HashMap<PublicProfile,Date> listProfileDate;
    private HashMap<Long,Game> listIdGame;
    ArrayList<JButton> listPlayersLaunchBtn;
    private  EndGameModel listEndGames;
    private  StopGameModel listStartGames;
    ArrayList<JButton> listContinueGameBtn;
    ArrayList<JButton> listReviewGameBtn;
    ArrayList<JButton> listPlayGameBtn;
    
    
    public IhmLoginModel(ApplicationModel appModel){
        this.appModel = appModel;

        // Liste des joueurs présents
        Object[][] donnees = {};
        String[] entetes = {"id", "Pseudo", "FistName", "Status",""};
//        ProfileManager profileManager = new ProfileManager(appModel);
        listPlayers = new PlayerModel();
        listPlayers.setDataVector(donnees, entetes);
        listPlayGameBtn = new ArrayList<JButton>();
        listPlayersLaunchBtn = new ArrayList<JButton>();
       
//        idPlayersConnected = profileManager.getIdPlayersConnected(); // pour le test
         
        // Liste des parties terminées
        listIdGame = new HashMap<Long, Game>();
        
        String[] entetesEndGames = {"Date","Adversary", "Result", ""};
        listEndGames = new EndGameModel();
        listEndGames.setDataVector(donnees, entetesEndGames);
        listReviewGameBtn = new ArrayList<JButton>();

//        idEndGames = gameManager.getIdStopGames(); // Pour les tests
//        ArrayList<Game> endGames = new ArrayList<Game>();
//        try {
//            endGames = gameManager.getListStopGames();
//        } catch (Exception ex) {
//              JOptionPane.showMessageDialog(null, ex.getMessage(), "Exception EndGame", JOptionPane.ERROR_MESSAGE);
//        }
//        for (Game game : endGames ) {
//            listEndGames.addGame(game.getEndDate(), game.getRemotePlayer().getPublicProfile().toString(),"result", game.getGameId());
//        }
        
        // Liste des parties en cours
        String[] entetesStopGames = {"Date","Adversary", ""};
        listStartGames = new StopGameModel();
        listStartGames.setDataVector(donnees, entetesStopGames);
        listContinueGameBtn = new ArrayList<JButton>();
//        ArrayList<Game> stopGames = new ArrayList<Game>();
//        try {
//            stopGames = gameManager.getListStartGames();
//        } catch (Exception ex) {
//              JOptionPane.showMessageDialog(null, ex.getMessage(), "Exception StopGame", JOptionPane.ERROR_MESSAGE);
//        }
//        
////        idStartGames = gameManager.getIdStartGames(); // Pour les tests
//        for (Game game : stopGames ) {
//            listStartGames.addGame(game.getEndDate(), game.getRemotePlayer().getPublicProfile().toString(), game.getGameId(),game.getRemotePlayer().getPublicProfile().getStatus());
//        }
        
        listProfileDate = new HashMap<PublicProfile,Date>();

        pcs = new PropertyChangeSupport(this);
        

    }

    /**
     * subscribe a PropertyChangeListener l to the model on the channel evt
     * @param evt the channel
     * @param l the listener
     */
    public void addPropertyChangeListener(String evt,PropertyChangeListener l){
        if(pcs != null)
            pcs.addPropertyChangeListener(evt,l);
    }
    
    
    /**
     * Accept an Invitation, create the Game and load it
     * @param invit Invitation
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws WrongInvitation 
     */
    public void sendInvitationAnswer(Invitation invit,boolean answer) throws FileNotFoundException, IOException, ClassNotFoundException, WrongInvitation{
        appModel.getPManager().sendInvitationAnswer(invit,answer);
    }

    
    
    /**
     * Send an Invitation to a remote user identified by idUser with a color col
     * @param idUser
     * @param col
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws FileNotFoundException 
     */
    public void sendInvitation(String idUser,Enums.COLOR col) throws IOException, ClassNotFoundException, FileNotFoundException{
        long time = System.currentTimeMillis();
        //Instantiate DataManager
        ProfileManagerInterface profileManager = appModel.getPManager();
        //Instantiate profile and invitation
        PublicProfile p = this.getRemoteProfile(idUser);
        //Profile profile = profileManager.loadProfile(idUser);
        Invitation invit = profileManager.createInvitation(p, col, time);
        //Send invitation
        profileManager.sendInvitation(invit);  
    }

    public ApplicationModel getApplicationModel() {
        return appModel;
    }

    public PlayerModel getPlayerModel(){
        return listPlayers;
    }

    public EndGameModel getEndGameModel() {
        return listEndGames;
    }
 
    public StopGameModel getStopGameModel() {
        return listStartGames;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(ADD_PLAYER_CONNECTED)){
            PublicProfile profile = (PublicProfile)evt.getNewValue();

            PublicProfile p = getRemoteProfile(profile.getProfileId());
            
            if(p == null){
                //Add remote profile to Jtable model
                listPlayers.addPlayer(profile.getProfileId(),profile.getPseudo(),profile.getFirstName(),getIconStatus(profile));
                
                System.out.println("Player : "+profile.getPseudo()+" added");
                
                //Get all games stopped which have the id of the current remote profile
                ArrayList<Game> gamesContinue = getGamesContinueFromId(profile.getProfileId());
                for(Game g : gamesContinue){
                    listIdGame.put(g.getGameId(), g);
                    listStartGames.addGame(g.getStart(), g.getRemotePlayer().getPublicProfile().toString(), g.getGameId(),profile.getStatus());
                }
                
                //Set profile to p
                p = profile;
                pcs.firePropertyChange(ADD_PLAYER_CONNECTED, null, p);
            }
            else{
                //update info of p
                listPlayers.updatePlayer(profile.getProfileId(), profile.getPseudo(), profile.getFirstName(), getIconStatus(profile));
                listProfileDate.remove(p);
                p = profile;
                System.out.println("Update Player ");
            }
            //put p to list
            listProfileDate.put(p,new Date());
     
            //removeDisconnectedProfilesAndGames();
            
        }
        else if(evt.getPropertyName().equals(DELETE_PLAYER_DISCONNECTED)){
            PublicProfile profile = (PublicProfile) evt.getNewValue();
            if(profile != null){
                PublicProfile p = this.getRemoteProfile(profile.getProfileId());
                if(p != null){
                    listPlayers.removePlayer(p.getProfileId());
                    listProfileDate.remove(p);
                    System.out.println("Player Removed : "+p.getProfileId());
                    //Remove games associated
                    ArrayList<Game> gamesContinue = this.getGamesContinueFromId(p.getProfileId());
                    for(Game g : gamesContinue){
                        listIdGame.remove(g.getGameId());
                        this.listStartGames.removeGame(g.getGameId());
                    }
                }
            }
        }
        else if(evt.getPropertyName().equals(INVIT_RECEIVE)){
            pcs.firePropertyChange(INVIT_RECEIVE, evt.getOldValue(), evt.getNewValue());
        }
        else if(evt.getPropertyName().equals(REQUEST_GAME_RESPONSE)){
            pcs.firePropertyChange(REQUEST_GAME_RESPONSE, evt.getOldValue(), evt.getNewValue());
        }
        else if(evt.getPropertyName().equals(INVIT_EXPIRED)){
            pcs.firePropertyChange(INVIT_EXPIRED,evt.getOldValue(),evt.getNewValue());
        }
        else if(evt.getPropertyName().equals(GAME_STARTED)){
            pcs.firePropertyChange(GAME_STARTED,evt.getOldValue(),evt.getNewValue());
        }
        else if(evt.getPropertyName().equals(GAME_ENDED)){
            pcs.firePropertyChange(GAME_ENDED,evt.getOldValue(),evt.getNewValue());
        }
        else
            System.out.println("Event Received : "+evt.getPropertyName());
    }
    
    
    /**
     * return the ImageIcon according to profile status
     * @param profile
     * @return 
     */
    private ImageIcon getIconStatus(PublicProfile profile){
        if(profile.getStatus().equals(STATUS.CONNECTED)){
            return ONLINEICON;
        }
        else
            return OFFLINEICON;
    }

    /**
     * Remove disconnect Profiles and Games from the JTable models
     * if the profile wasn't modified since 3 sec, it will be deleted
     */
    private void removeDisconnectedProfilesAndGames(){
        Date now = new Date();
        Set<PublicProfile> col = listProfileDate.keySet();
        for(PublicProfile p : col){
            Date currDate = listProfileDate.get(p);
            if(now.getTime() - currDate.getTime() >= 3*1000){
                //Remove profile from two list
                listProfileDate.remove(p);
                System.out.println("Player Removed : "+p.getPseudo());
                listPlayers.removePlayer(p.getProfileId());
                
                //Remove games associated
                ArrayList<Game> gamesContinue = this.getGamesContinueFromId(p.getProfileId());
                for(Game g : gamesContinue){
                    listIdGame.remove(g.getGameId());
                    this.listStartGames.removeGame(g.getGameId());
                }
            }
        }
    }

    /**
     * Get the remote profile identified by id from remote profile list
     * if it doesn't exist return null
     * @param id id
     * @return Remote Profile
     */
    PublicProfile getRemoteProfile(String id) {
        for(PublicProfile p : listProfileDate.keySet()){
            if(p.getProfileId().equals(id))
                return p;
        }
        return null;
    }

    /**
     * Create a game from the Invitation and load it
     * @param invitation 
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws WrongInvitation 
     */
    public void loadGame(Invitation invitation) throws FileNotFoundException, IOException, ClassNotFoundException, WrongInvitation {
        appModel.getGManager().createGame(invitation);
    }
    
    /**
     * Fire REFRESH_LIST evt to refresh local profile list
     */
    void refreshProfileList() {
        pcs.firePropertyChange(IhmConnexionWindow.REFRESH_LIST,null,null);
    }

    /**
     * Return stopped games according to the remote user id
     * @param profileId
     * @return 
     */
    private ArrayList<Game> getGamesContinueFromId(String profileId) {
        ArrayList<Game> res = new ArrayList<Game>();
        try {
            ArrayList<Game> gamesContinue = appModel.getGManager().getListStartGames();
            for(Game g : gamesContinue){
                if(g.getRemotePlayer().getPublicProfile().getProfileId().equals(profileId)){
                    res.add(g);
                }
                else if(g.getLocalPlayer().getPublicProfile().getProfileId().equals(profileId)){
                    res.add(g);
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(IhmLoginModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IhmLoginModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    
    /**
     * Connect the profile with the password
     * true if connection succeed, otherwise return false
     * @param selectedProfile
     * @param password
     * @return 
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    boolean connect(PublicProfile profile,char[] password) throws FileNotFoundException, IOException, ClassNotFoundException {
        ProfileManagerInterface pmi = appModel.getPManager();
        GameManagerInterface gmi = appModel.getGManager();
        boolean ret = pmi.connection(profile.getProfileId(),password);
        if(ret){
            ((Manager)pmi).subscribe(this, INVIT_RECEIVE);
            ((Manager)pmi).subscribe(this, INVIT_EXPIRED);
            ((Manager)pmi).subscribe(this, ADD_PLAYER_CONNECTED);
            ((Manager)pmi).subscribe(this, REQUEST_GAME_RESPONSE);
            ((Manager)pmi).subscribe(this, DELETE_PLAYER_DISCONNECTED);
            ((Manager)gmi).subscribe(this, GAME_STARTED);
            ((Manager)gmi).subscribe(this, GAME_ENDED);
            initEndGames();
            pmi.startProfilesDiscovery();            
        }
        return ret;
    }
    
    private void initEndGames(){
        ArrayList<Game> endGames = new ArrayList<Game>();
        try {
            endGames = appModel.getGManager().getListStopGames();
        } catch (Exception ex) {
              JOptionPane.showMessageDialog(null, ex.getMessage(), "Exception EndGame", JOptionPane.ERROR_MESSAGE);
        }
        for (Game game : endGames ) {
            listIdGame.put(game.getGameId(), game);
            listEndGames.addGame(game.getEndDate(), game.getRemotePlayer().getPublicProfile().toString(),"result", game.getGameId());
        }
    }

    
    /**
     * Unsubscribe events
     */
    void disconnect() {
        ProfileManagerInterface pmi = appModel.getPManager();
        GameManagerInterface gmi = appModel.getGManager();
        ((Manager)pmi).unsubscribe(this, INVIT_RECEIVE);
        ((Manager)pmi).unsubscribe(this, INVIT_EXPIRED);
        ((Manager)pmi).unsubscribe(this, ADD_PLAYER_CONNECTED);
        ((Manager)pmi).unsubscribe(this, REQUEST_GAME_RESPONSE);
        ((Manager)pmi).unsubscribe(this, DELETE_PLAYER_DISCONNECTED);
        ((Manager)gmi).unsubscribe(this, GAME_STARTED);
        ((Manager)gmi).unsubscribe(this, GAME_ENDED);
        pmi.disconnect();
    }

    void sendGameStarted(Invitation invitation) {
        appModel.getGManager().sendGameStarted(invitation, true);
    }

    void loadEndedGame(long idGame) throws WrongInvitation {
        Game game = listIdGame.get(idGame);
        PublicProfile localP = appModel.getPManager().getCurrentProfile().getPublicProfile();
        ResumeGame rg = null;
        if(game.getLocalPlayer().getPublicProfile().getProfileId().equals(localP.getProfileId()))
            rg= new ResumeGame(localP,game.getRemotePlayer().getPublicProfile(),game);
        else
            rg = new ResumeGame(game.getRemotePlayer().getPublicProfile(),localP,game);
        appModel.getGManager().createGame(rg);
    }
 

    private class PlayerModel extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int r, int c) {
            return false;
        }

        @Override
        public Class getColumnClass(int columnIndex) {
            Object o = getValueAt(0, columnIndex);

            if (o == null) {
                return Object.class;
            } else {
                return o.getClass();
            }
        }
        
        public void updatePlayer(String id,String name,String firstname,ImageIcon ico){
            for (int i = 0; i < this.getRowCount(); i++) {
                if (this.getValueAt(i, 0).equals(id)) {
                    this.setValueAt(name, i, 1);
                    this.setValueAt(firstname,i,2);
                    
                    this.setValueAt(ico, i, 3);
                    if(ico.equals(IhmLoginModel.this.ONLINEICON)){
                        ((JButton)this.getValueAt(i, 4)).setEnabled(true);
                    }
                    else{
                        ((JButton)this.getValueAt(i, 4)).setEnabled(false);
                    }
                    System.out.println("Player found and updated : "+name);
                    return;
                }
            }
        }

        public void addPlayer(String id,String name, String firstname, ImageIcon ico) {
            
            JButton btn = new JButton("Send Invitation");
            btn.putClientProperty("id", id);
            listPlayersLaunchBtn.add(btn);
            if(ico == ONLINEICON)
                btn.setEnabled(true);
            else 
                btn.setEnabled(false);
            this.addRow(new Object[]{id,name, firstname, ico,btn});
        }

        public void removePlayer(String id) {
            for (int i = 0; i < this.getRowCount(); i++) {
                if (this.getValueAt(i, 0).equals(id)){
                    System.out.println("Player found and removed : "+this.getValueAt(i,1));
                    this.removeRow(i);
                    return;
                }
            }
        }
    }

    private class GameModel extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int r, int c) {
            return false;
        }

        @Override
        public Class getColumnClass(int columnIndex) {
            Object o = getValueAt(0, columnIndex);

            if (o == null) {
                return Object.class;
            } else {
                return o.getClass();
            }
        }
        
        
        
    }
    
    private class EndGameModel extends GameModel {
        public void addGame(Date date, String adversary, String result, Long id) {
            JButton btn = new JButton("Review");
            btn.putClientProperty("id", id);
            listReviewGameBtn.add(btn);
            this.addRow(new Object[]{date, adversary, result, btn});
        }
        
        public void removeGame(Long id) {
            for (int i = 0; i < this.getRowCount(); i++) {
                if(this.getValueAt(i, 3) instanceof JButton){
                    JButton button = (JButton) this.getValueAt(i, 3);
                    if (button.getClientProperty("id") == id) {
                        this.removeRow(i);
                        return;
                    }
                }
            }
        }
        
    } 
    private class StopGameModel extends GameModel {
        public void addGame(Date date, String adversary, Long id,Enums.STATUS status) {
            JButton btn = new JButton("Continue");
            btn.putClientProperty("id", id);
            if(status.equals(Enums.STATUS.CONNECTED))
                btn.setEnabled(true);
            else
                btn.setEnabled(false);
            listContinueGameBtn.add(btn);
            this.addRow(new Object[]{date, adversary, btn});
        }
        
        public void removeGame(Long id) {
            for (int i = 0; i < this.getRowCount(); i++) {
                if(this.getValueAt(i, 2) instanceof JButton){
                    JButton button = (JButton) this.getValueAt(i, 2);
                    if (button.getClientProperty("id") == id) {
                        this.removeRow(i);
                        return;
                    }
                }
            }
        }
    } 
  

    /*
     * Permet à la classe ihmListGame d'acceder aux boutons review
     */
    public ArrayList<JButton> getListReviewGameBtn() {
        return listReviewGameBtn;
    }
    
    /*
     * Permet à la classe ihmListGame d'acceder aux boutons continue
     */
    public ArrayList<JButton> getListContinueGameBtn() {
        return listContinueGameBtn;
    }
    
    public ArrayList<JButton> getListLaunchGameBtn() {
        return  listPlayersLaunchBtn;
    }

    /**
     * Send Invitation to resume a game identified by idGame
     * @param idGame 
     */
    public void sendInvitationResumeGame(Long idGame) {
        Game game = listIdGame.get(idGame);
        PublicProfile guest = game.getRemotePlayer().getPublicProfile();
        PublicProfile host = game.getLocalPlayer().getPublicProfile();
        ResumeGame invit = new ResumeGame(host, guest, game);

        //Send invitation
        appModel.getPManager().sendInvitation(invit);
    }

}
