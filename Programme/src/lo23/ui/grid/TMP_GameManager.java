//package lo23.ui.grid;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import lo23.data.exceptions.IllegalMoveException;
//import lo23.data.managers.*;
//import java.util.ArrayList;
//import lo23.communication.ISender;
//import lo23.data.ApplicationModel;
//import lo23.data.Constant;
//import lo23.data.Event;
//import lo23.data.Game;
//import lo23.data.Invitation;
//import lo23.data.Message;
//import lo23.data.Move;
//import lo23.data.Position;
//import lo23.data.PublicProfile;
//import lo23.data.exceptions.FileNotFoundException;
//import lo23.data.exceptions.NoIdException;
//import lo23.data.pieces.GamePiece;
//import lo23.data.serializer.Serializer;
//import lo23.utils.Enums.CONSTANT_TYPE;
//
//
//public class TMP_GameManager extends Manager implements GameManagerInterface {
//
//    private Game currentGame;
//
//    // @khamidou FIXME: use injection dependency
//    // FIXME: use better name
//    private ISender networkSender;
//
//    public TMP_GameManager(ApplicationModel model){
//        super(model);
//    }
//
//    @Override
//    public void save() throws NoIdException {
//        Serializer.saveGame(currentGame);
//    }
//
//
//    @Override
//    public Game load(long gameId) throws FileNotFoundException {
//        return Serializer.readGame(gameId);
//    }
//
//    @Override
//    public Move createMove(Position to, GamePiece piece) {
//       return new Move(piece.getPosition(), to, piece);
//    }
//
//    @Override
//    public void sendMove(Move move) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void playMove(Move move) {
//        try {
//            currentGame.playMove(move);
//        } catch (IllegalMoveException ex) {
//            Logger.getLogger(TMP_GameManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @Override
//    public Message createMessage(String content) {
//        return new Message(content, currentGame.getLocalPlayer(),
//                                    currentGame.getRemotePlayer());
//    }
//
//    @Override
//    public void sendMessage(Message message) {
//        //networkSender.sendChatMessage(message);
//    }
//
//    @Override
//    public void saveMessage(Message message) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public ArrayList<Event> getHistory() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void notifyChatMessage(Message message) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public Game createGame(Invitation invitation) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public Constant createConstant(CONSTANT_TYPE constant) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void sendConstant(Constant constant) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void notifyConstantMessage(Constant constant) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void saveConstant(Constant constant) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void notifyGameEnded() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
///*
//    @Override
//    public Game notifiyGameStarted(Invitation invitation) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//*/
//    @Override
//    public ArrayList<Game> getListStopGames() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public ArrayList<Game> getListStartGames() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public Game getCurrentGame() {
//        return this.currentGame;
//    }
//
//    @Override
//    public void notifyGameStarted(PublicProfile userProfile) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void notifyMovement(Move move) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    public Game notifyGameStarted(Invitation invitation) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    public ArrayList<Game> getListAllGames() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//}
