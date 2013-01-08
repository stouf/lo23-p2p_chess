package lo23.data.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import lo23.data.ApplicationModel;
import lo23.data.Constant;
import lo23.data.Event;
import lo23.data.Game;
import lo23.data.GridState;
import lo23.data.Invitation;
import lo23.data.Message;
import lo23.data.Move;

import lo23.data.NewInvitation;

import lo23.data.Player;

import lo23.data.Position;
import lo23.data.Profile;
import lo23.data.ResumeGame;
import lo23.data.exceptions.FileNotFoundException;
import lo23.data.exceptions.IllegalMoveException;
import lo23.data.exceptions.NoIdException;
import lo23.data.exceptions.WrongInvitation;
import lo23.data.pieces.GamePiece;
import lo23.data.serializer.Constants;
import lo23.data.serializer.Serializer;
import lo23.ui.grid.GridConstants;
import lo23.ui.grid.MainWindow;
import lo23.ui.login.IhmLoginModel;
import lo23.utils.Enums;
import lo23.utils.Enums.COLOR;
import lo23.utils.Enums.CONSTANT_TYPE;
import lo23.utils.Enums.PLAYER_RESULT;

public class GameManager extends Manager implements GameManagerInterface {

    private Game currentGame;
    private Stack<GridState> gridStates;

    public GameManager(ApplicationModel app) {
	super(app);
        gridStates = new Stack<GridState>();
    }

    @Override
    public void save() throws NoIdException, IOException {
	Serializer.saveGame(currentGame);
    }

    @Override
    public Game load(long gameId) throws FileNotFoundException, IOException, ClassNotFoundException {
	return Serializer.readGame(gameId);
    }

    @Override
    public Move createMove(Position to, GamePiece piece) {
	return new Move(piece.getPosition(), to, piece);
    }

    @Override
    public void sendMove(Move move) {
	getApplicationModel().getComManager().sendMovement(move);
    }

    @Override
    public void playMove(Move move) {
	try {
            saveGridState(move);
	    currentGame.playMove(move);
	    currentGame.swapCurrentPlayerColor();
	} catch (IllegalMoveException ex) {
	    Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
	}

	pushEvent(move);
    }

    
    /**
     * Saves the current grid's state and the move that will update this state.
     * @param move The move
     */
    private void saveGridState(Move move) {
        gridStates.push(new GridState(currentGame.getBoard(), move));
    }

    /**
     * This method simply returns the last recorded state of the grid.
     * Be careful, it pops the state, so it will be deleted after calling this method
     * @return The last recorded grid's state
     */
    public GridState popLastGridState()
    {
        return gridStates.pop();
    }
    
    public Stack<GridState> getGridStates(){
        return gridStates;
    }

    @Override
    public Message createMessage(String content) {
	return new Message(content, currentGame.getLocalPlayer(),
		currentGame.getRemotePlayer());
    }

    @Override
    public void sendMessage(Message message) {
	saveMessage(message);
	getApplicationModel().getComManager().sendChatMessage(message);
    }

    public void pushEvent(Event e) {
	currentGame.pushEvent(e);
	this.publish(GridConstants.NEW_EVENT_ADDED, e);
    }

    @Override
    public void saveMessage(Message message) {
	pushEvent(message);
    }

    @Override
    public ArrayList<Event> getHistory() {
	return currentGame.getEvents();
    }

    @Override
    public void notifyChatMessage(Message message) {
	pushEvent(message);
    }

    @Override
    public Game createGame(Invitation invitation) throws WrongInvitation {

	if (getApplicationModel().getPManager().getCurrentProfile() == null) {
	    throw new WrongInvitation("Personne n'est connecté.");
	}

	String curr_pofileId = getApplicationModel().getPManager().getCurrentProfile().getProfileId(),
		guestProfileId = invitation.getGuest().getProfileId(),
		hostprofileId = invitation.getHost().getProfileId();
	if (curr_pofileId.equals(guestProfileId)
		|| curr_pofileId.equals(hostprofileId)) {
	    getApplicationModel().getPManager().getCurrentProfile().setStatus(Enums.STATUS.INGAME);
	    if (invitation instanceof NewInvitation) {
		NewInvitation I = (NewInvitation) invitation;
		COLOR guestColor;
		if (I.getColor() == COLOR.BLACK) {
		    guestColor = COLOR.WHITE;
		} else {
		    guestColor = COLOR.BLACK;
		}
		if (guestProfileId.equals(curr_pofileId)) {
		    // guest=local
		    Player local = new Player(guestColor, I.getDuration(), invitation.getGuest());
		    Player remote = new Player(I.getColor(), I.getDuration(), invitation.getHost());
		    currentGame = new Game(local, remote);
		    currentGame.buildPieces();
		} else {
		    // guest = remote
		    Player local = new Player(I.getColor(), I.getDuration(), invitation.getHost());
		    Player remote = new Player(guestColor, I.getDuration(), invitation.getGuest());
		    currentGame = new Game(local, remote);
		    currentGame.buildPieces();
		}

                currentGame.setCurrentPlayerColor(COLOR.WHITE);
                 MainWindow fenetre = new MainWindow(this.getApplicationModel(), false);
                 fenetre.setVisible(true);
		
	    } else {
		//Il s'agit d'un resume game
		ResumeGame I = (ResumeGame) invitation;
		currentGame = I.getGame();
                currentGame.swapCurrentPlayerColor();
                String idLocalPlayer = currentGame.getLocalPlayer().getPublicProfile().getProfileId();
                if(!idLocalPlayer.equals(curr_pofileId))
                    currentGame.swapPlayer(); // Il faut inverser local et remote player
		if (currentGame.getEndDate()==null){
		    // Partie non finie
                    MainWindow fenetre = new MainWindow(this.getApplicationModel(), false);
                    fenetre.setVisible(true);
		}else{
		    //Partie finie -> visionnage
                    MainWindow fenetre = new MainWindow(this.getApplicationModel(), true);
                    fenetre.setVisible(true);
		}
	    }
	} else {
	    throw new WrongInvitation("L'invitation n'est pas pour le profil connecté.");
	}
	//MainWindow fenetre = new MainWindow(this.getApplicationModel(), currentGame);
	//fenetre.setVisible(true);
	return currentGame;
    }

    @Override
    public Constant createConstant(CONSTANT_TYPE constant) {
	return new Constant(constant, currentGame.getRemotePlayer(), currentGame.getLocalPlayer());
    }

    @Override
    public void sendConstant(Constant constant) {
	saveConstant(constant);
	getApplicationModel().getComManager().sendConstantMessage(constant);
    }

    @Override
    public void notifyConstantMessage(Constant constant) {
	pushEvent(constant);
	CONSTANT_TYPE c = constant.getConstant();
	if (c == CONSTANT_TYPE.DRAW_ACCEPTED || c == CONSTANT_TYPE.OUT_OF_TIME || c == CONSTANT_TYPE.SURRENDER) {
	    currentGame.setEnd();
	}
    }

    @Override
    public void saveConstant(Constant constant) {
	pushEvent(constant);
	CONSTANT_TYPE c = constant.getConstant();
	if (c == CONSTANT_TYPE.DRAW_ACCEPTED || c == CONSTANT_TYPE.OUT_OF_TIME || c == CONSTANT_TYPE.SURRENDER) {
	    currentGame.setEnd();
	}
    }
    
    /**
     * Notification use when local user quit the game
     * Set Status to Connected
     * Update Profile Stats
     * Notify to IHM Login the game is ended
     * @param gameResult 
     */

    @Override
    public void notifyGameEnded(PLAYER_RESULT gameResult) {
        if(gameResult != Enums.PLAYER_RESULT.NOT_FINISH) {
            currentGame.setRes(gameResult);
        }
        this.getApplicationModel().getPManager().getCurrentProfile().setStatus(Enums.STATUS.CONNECTED);
        this.getApplicationModel().getComManager().sendMulticast();
        updateProfileStatistics(gameResult);
        publish(IhmLoginModel.GAME_ENDED, null);
    }
    
    
    
    /**
     * Notify local user when the remote user quit the game
     * Notification send to Grid
     */
    
    @Override
    public void notifyGameEnded() {
        Constant constant = this.createConstant(CONSTANT_TYPE.GAME_ENDED);
        
        // Switch receiver and sender because this constant was created locally
        Player receiver = constant.getSender();
        Player sender = constant.getReceiver();
        constant.setReceiver(receiver);
        constant.setSender(sender);

        publish(GridConstants.NEW_EVENT_ADDED, constant);
    }
    
    
    
    /**
     * Updates the local player's profile's statistics considering a game's result
     * @param gameResult The concerned game's result
     */
    private void updateProfileStatistics(PLAYER_RESULT gameResult)
    {
        try {
            switch(gameResult)
            {
            case DRAW:
                    this.getApplicationModel().getPManager().getCurrentProfile().incrementDrawGames();
                    this.save();
                    break;

            case WIN:
                    this.getApplicationModel().getPManager().getCurrentProfile().incrementWonGames();
                    this.save();
                    break;

            case LOST:
                    this.getApplicationModel().getPManager().getCurrentProfile().incrementLostGames();
                    this.save();
                    break;

            default:
                    break;
            }
            this.getApplicationModel().getPManager().saveProfile();
        }
        catch (Exception ex) {
            Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void sendGameEnded(){
        getApplicationModel().getComManager().sendGameEnded();
    }
    @Override
    @Deprecated
    public Game notifyGameStarted(Invitation invitation) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Game> getListStopGames() throws IOException, ClassNotFoundException {
	ArrayList<Game> gameList = getListAllGames();
	int i = 0;
	while (i < gameList.size()) {
	    if (gameList.get(i).getEndDate() == null) {
		gameList.remove(i);
	    } else {
		i++;
	    }
	}
	return gameList;
    }

    @Override
    public ArrayList<Game> getListStartGames() throws IOException, ClassNotFoundException {
	ArrayList<Game> gameList = getListAllGames();
	//EndGames have to be remove.
	int i = 0;
	while (i < gameList.size()) {
	    if (gameList.get(i).getEndDate() != null) { //EndGame have an end Date.
		gameList.remove(i);
	    } else {
		i++;
	    }
	}
	return gameList;
    }

    @Override
    public Game getCurrentGame() {
	return this.currentGame;
    }

    @Override
    public void notifyGameStarted(Invitation invit,boolean ans) {
	publish(IhmLoginModel.GAME_STARTED, invit,ans);
    }
    @Override
    public void sendGameStarted(Invitation invit, boolean ans){
	getApplicationModel().getComManager().sendGameStarted(invit, ans);
    }

    @Override
    public void notifyMovement(Move move) {
	if (move == null) {
	    return;
	} else {
	    int xfrom = move.getFrom().getX();
	    int yfrom = move.getFrom().getY();
	    GamePiece p = getPieceAtXY(xfrom, yfrom);
            playMove(move);
            currentGame.swapCurrentPlayerColor();

	    publish("move", move);
	}
    }

    @Override
    public ArrayList<Game> getListAllGames() throws IOException, ClassNotFoundException {
        // Creates the folder if it doesn't exist
	File games = new File(Constants.GAMES_PATH);
        if (!games.exists()) {
            games.mkdirs();
        }

	String[] fileList = games.list();

	ArrayList<Game> gameList = new ArrayList<Game>();
	
	if (fileList == null) {
	    return new ArrayList<Game>();
	}

	for (int i = 0; i < fileList.length; i++) {
	    if (fileList[i].startsWith(".")) {
		continue;
	    }

	    try {
		//fileList[i] format is "gameId.game"
		//So the string is split in order to have the gameId.
		Profile cur = getApplicationModel().getPManager().getCurrentProfile();

		long tmp_long = Long.parseLong(fileList[i].split("\\.")[0]);
		Game tmp = load(tmp_long);
		if (cur.getProfileId().equals(tmp.getLocalPlayer().getPublicProfile().getProfileId())
			|| cur.getProfileId().equals(tmp.getRemotePlayer().getPublicProfile().getProfileId())) { //ajout de tmp si le localPLayer ou le distant est le profile connecte
		    gameList.add(tmp);
		}
	    } catch (FileNotFoundException expt) {
		System.out.println(expt.getMessage());
		System.out.println(expt.getStackTrace());
	    }
	}
	return gameList;
    }

    public GamePiece getPieceAtXY(int x, int y) {
	return currentGame.getPieceAtXY(x, y);
    }
}
