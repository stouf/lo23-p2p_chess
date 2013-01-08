/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lo23.ui.login.mockManager;

import java.util.ArrayList;
import java.util.Stack;
import lo23.data.ApplicationModel;
import lo23.data.Constant;
import lo23.data.Event;
import lo23.data.Game;
import lo23.data.GridState;
import lo23.data.Invitation;
import lo23.data.Message;
import lo23.data.Move;
import lo23.data.Player;
import lo23.data.Position;
import lo23.data.PublicProfile;
import lo23.data.exceptions.FileNotFoundException;
import lo23.data.exceptions.NoIdException;
import lo23.data.managers.GameManagerInterface;
import lo23.data.managers.Manager;
import lo23.data.pieces.GamePiece;
import lo23.utils.Enums.COLOR;
import lo23.utils.Enums.CONSTANT_TYPE;
import lo23.utils.Enums.PLAYER_RESULT;
import lo23.utils.Enums.STATUS;

/**
 *
 * @author lo23a009
 */
public class GameManagerMock extends Manager implements GameManagerInterface{

    // Lists contenant les gameId des parties affichées pour pouvoir tester les boutons dans ihmListGamesTest
    public ArrayList<Long> idStopGames;
    public ArrayList<Long> idStartGames;
    
    
    public GameManagerMock(ApplicationModel model){
        super(model);
    }

    public void save() throws NoIdException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Game load(String gameId) throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Move createMove(Position to, GamePiece piece) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void sendMove(Move move) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void playMove(Move move) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Message createMessage(String content) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void sendMessage(Message message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void saveMessage(Message message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList<Event> getHistory() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyChatMessage(Message message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Game createGame(Invitation invitation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Constant createConstant(CONSTANT_TYPE constant) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void sendConstant(Constant constant) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyConstantMessage(Constant constant) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void saveConstant(Constant constant) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyGameEnded() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Game notifiyGameStarted(Invitation invitation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Game> getListStopGames() {
        ArrayList<Game> listStopGames = new ArrayList<Game>();
        idStopGames = new ArrayList<Long>();

        PublicProfile profile = new PublicProfile("1", "player1", STATUS.INGAME, "127.0.0.1", null, "toto", "toto", 21, 5, 2, 1);
        PublicProfile profile2 = new PublicProfile("2", "player2", STATUS.INGAME, "127.0.0.1", null, "toto", "toto", 21, 5, 2, 2);
        Player playerLocal = new Player(COLOR.BLACK, 10, profile);
        Player remotePlayer = new Player(COLOR.BLACK, 30, profile2);
        Game game1 = new Game(playerLocal, remotePlayer);
        listStopGames.add(game1);
        idStopGames.add(game1.getGameId()); // Ajout de l'id pour les tests
        
//        try {
//            Thread.sleep(987);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(GameManagerMock.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        PublicProfile profile3 = new PublicProfile("3", "player3", STATUS.INGAME, "127.0.0.1", null, "toto", "toto", 21, 5, 2, 2);
        Player remotePlayer2 = new Player(COLOR.BLACK, 3, profile3);
        Game game2 = new Game(playerLocal, remotePlayer2);
        listStopGames.add(game2);
        idStopGames.add(game2.getGameId()); // Ajout de l'id pour les tests
        
        return listStopGames;
    }
    
    @Override
    public ArrayList<Game> getListStartGames() {
        ArrayList<Game> listStartGames = new ArrayList<Game>();
        idStartGames = new ArrayList<Long>();
        
        PublicProfile profile = new PublicProfile("toto", "totopseudo", STATUS.INGAME, "127.0.0.1", null, "toto", "toto", 21, 5, 2, 2);
        PublicProfile profile2 = new PublicProfile("titi", "titipseudo", STATUS.INGAME, "127.0.0.1", null, "toto", "toto", 21, 5, 2, 1);
        Player playerLocal = new Player(COLOR.BLACK, 23, profile);
        Player remotePlayer = new Player(COLOR.BLACK, 10, profile2);
        Game game1 = new Game(playerLocal, remotePlayer);
        listStartGames.add(game1);
        idStartGames.add(game1.getGameId());
        
//        try {
//            Thread.sleep(987);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(GameManagerMock.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        PublicProfile profile3 = new PublicProfile("3", "player3", STATUS.INGAME, "127.0.0.1", null, "toto", "toto", 21, 5, 2, 2);
        Player remotePlayer2 = new Player(COLOR.BLACK, 3, profile3);
        Game game2 = new Game(playerLocal, remotePlayer2);
        listStartGames.add(game2);
        idStartGames.add(game2.getGameId()); // Ajout de l'id pour les tests

        return listStartGames;
    }

    public Game getCurrentGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyGameStarted(PublicProfile userProfile) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyMovement(Move move) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Game notifyGameStarted(Invitation invitation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList<Game> getListAllGames() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Game load(long gameId) throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public ArrayList<Long> getIdStopGames() {
        return idStopGames;
    }
    
    public ArrayList<Long> getIdStartGames() {
        return idStartGames;
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
	public void notifyGameEnded(PLAYER_RESULT gameResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendGameEnded() {
		// TODO Auto-generated method stub
		
	}

    public Stack<GridState> getGridStates() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
