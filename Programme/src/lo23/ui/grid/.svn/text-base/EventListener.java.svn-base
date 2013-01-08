/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.ui.grid;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import lo23.data.ApplicationModel;
import lo23.data.Constant;
import lo23.data.Move;
import lo23.data.Player;
import lo23.data.Position;
import lo23.data.exceptions.IllegalMoveException;
import lo23.data.exceptions.UndefinedGamePieceException;
import lo23.data.managers.Manager;
import lo23.data.pieces.GamePiece;
import lo23.utils.Enums;
import lo23.utils.Enums.CONSTANT_TYPE;

/**
 * Create the event Listener
 * @author Karim
 */
public class EventListener implements PropertyChangeListener {

    private ApplicationModel myModel;
    public static final String NEW_EVENT_ADDED = "new_event_added";
    private GamePanel gamePanel;
    private ChatPanel2 chatPanel;
    private TimerPanel timerPanel;

    private boolean checkPlayerIsDifferent(Player player1, Player player2){

        return !player1.getPublicProfile().getProfileId().equals(player2.getPublicProfile().getProfileId());

    }

    public EventListener(GamePanel panel, ApplicationModel model) {
        gamePanel = panel;
        myModel = model;
        ((Manager) myModel.getGManager()).subscribe(this, NEW_EVENT_ADDED);
    }

    public EventListener(ChatPanel2 panel, ApplicationModel model) {
        chatPanel = panel;
        myModel = model;
        ((Manager) myModel.getGManager()).subscribe(this, NEW_EVENT_ADDED);
    }

    public EventListener(TimerPanel panel, ApplicationModel model) {
        timerPanel = panel;
        myModel = model;
        ((Manager) myModel.getGManager()).subscribe(this, NEW_EVENT_ADDED);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("EVENT DETECTED ; " + evt.getOldValue() + " ; " + evt.getNewValue());

        if (evt.getNewValue() instanceof Move) {
            System.out.println("EVENT DETECTED 3");
            if (gamePanel != null) {
                System.out.println("EVENT DETECTED 4");


                if (gamePanel.getGame().getPieceAtXY(((Move) evt.getNewValue()).getTo().getX(), ((Move) evt.getNewValue()).getTo().getY()).haveDoneARook()) {
                    System.out.println("EVENT DETECTED 4 => rook");
                    // les 4 rocks possibles :
                    if (((Move) evt.getNewValue()).getTo().getX() == 1) {
                        Move move = new Move(new Position(0, ((Move) evt.getNewValue()).getTo().getY()), new Position(2, ((Move) evt.getNewValue()).getTo().getY()), null);
                        try {
                            gamePanel.majDataBoard(move);
                        } catch (IllegalMoveException ex) {
                            Logger.getLogger(EventListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        gamePanel.updateBoardWithoutChangeColor(move);

                    } else if (((Move) evt.getNewValue()).getTo().getX() == 6)
                    {
                        Move move = new Move(new Position(7, ((Move) evt.getNewValue()).getTo().getY()), new Position(5, ((Move) evt.getNewValue()).getTo().getY()), null);
                        try {
                            gamePanel.majDataBoard(move);
                        } catch (IllegalMoveException ex) {
                            Logger.getLogger(EventListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        gamePanel.updateBoardWithoutChangeColor(move);
                    }
                    else if (((Move) evt.getNewValue()).getTo().getX() == 2) {
                        Move move = new Move(new Position(0, ((Move) evt.getNewValue()).getTo().getY()), new Position(3, ((Move) evt.getNewValue()).getTo().getY()), null);
                        try {
                            gamePanel.majDataBoard(move);
                        } catch (IllegalMoveException ex) {
                            Logger.getLogger(EventListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        gamePanel.updateBoardWithoutChangeColor(move);

                    } else if (((Move) evt.getNewValue()).getTo().getX() == 5)
                    {
                        Move move = new Move(new Position(7, ((Move) evt.getNewValue()).getTo().getY()), new Position(4, ((Move) evt.getNewValue()).getTo().getY()), null);
                        try {
                            gamePanel.majDataBoard(move);
                        } catch (IllegalMoveException ex) {
                            Logger.getLogger(EventListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        gamePanel.updateBoardWithoutChangeColor(move);
                    }
                }
                gamePanel.updateBoard((Move) evt.getNewValue());

            } else if (chatPanel != null) {
                try {
                    chatPanel.gameMsg((Move) evt.getNewValue());
                } catch (BadLocationException ex) {
                    Logger.getLogger(EventListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (timerPanel != null) {
                if (timerPanel.playerTimer.isRunning()) {
                    timerPanel.playerTimer.pauseTimer();
                    timerPanel.player.stopTime();
                } else {
                    timerPanel.playerTimer.startTimer();
                    timerPanel.player.startTime();
                }
            }

        } else if (evt.getNewValue() instanceof lo23.data.Message) {
            if (chatPanel != null) {
                try {

                    chatPanel.receivedMsg((lo23.data.Message) evt.getNewValue());
                } catch (BadLocationException ex) {
                    Logger.getLogger(EventListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (evt.getNewValue() instanceof Constant) {
            Constant cst = (Constant) evt.getNewValue();
            CONSTANT_TYPE type = cst.getConstant();

            if (type == CONSTANT_TYPE.OUT_OF_TIME) {
                if (gamePanel != null) {
                    gamePanel.endOfGame(cst.getSender());
                }
            }

            if (type == CONSTANT_TYPE.SURRENDER) {
                System.out.println("------------ SURRENDER -------------");
                System.out.println("mon pseudo : " + myModel.getGManager().getCurrentGame().getLocalPlayer().getPublicProfile().getPseudo());
                System.out.println("pseudo distant : " + myModel.getGManager().getCurrentGame().getRemotePlayer().getPublicProfile().getPseudo());
                System.out.println("cst sender " + cst.getSender().getPublicProfile().getPseudo());
                System.out.println("cst receiver " + cst.getReceiver().getPublicProfile().getPseudo());
                System.out.println("------------ SURRENDER -------------");

                if (gamePanel != null) {

                    if(checkPlayerIsDifferent(cst.getSender(), myModel.getGManager().getCurrentGame().getLocalPlayer())){
                        gamePanel.surrender(cst.getSender());

                    } else { // c'est moi qui est demandé de surrender
                        gamePanel.isPlayPossible = false;
                    }
                }
            }

            if (type == CONSTANT_TYPE.DRAW_ASKED) {

                System.out.println("mon pseudo : " + myModel.getGManager().getCurrentGame().getLocalPlayer().getPublicProfile().getPseudo());
                 System.out.println("pseudo distant : " + myModel.getGManager().getCurrentGame().getRemotePlayer().getPublicProfile().getPseudo());
                System.out.println("cst sender " + cst.getSender().getPublicProfile().getPseudo());
                System.out.println("cst receiver " + cst.getReceiver().getPublicProfile().getPseudo());


                if (gamePanel != null) {
                    if(checkPlayerIsDifferent(cst.getSender(), myModel.getGManager().getCurrentGame().getLocalPlayer())){
                        gamePanel.drawRequest(cst.getSender());
                    } else // c'est moi qui est proposé
                    {
                        gamePanel.isPlayPossible = false;
                    }
                }
            }

            if (type == CONSTANT_TYPE.DRAW_REFUSED) {
                if (gamePanel != null) {
                    if(checkPlayerIsDifferent(cst.getSender(), myModel.getGManager().getCurrentGame().getLocalPlayer())){
                        gamePanel.drawRefused(cst.getSender());
                    }
                }
            }

            if (type == CONSTANT_TYPE.DRAW_ACCEPTED) {
                if (gamePanel != null && checkPlayerIsDifferent(cst.getSender(), myModel.getGManager().getCurrentGame().getLocalPlayer())){
                    gamePanel.drawAccepted(cst.getSender());
                }
            }

            if (type == CONSTANT_TYPE.PROMOTED_TO_BISHOP) {
                if (gamePanel != null && checkPlayerIsDifferent(cst.getSender(), myModel.getGManager().getCurrentGame().getLocalPlayer())){
                    try {
                        gamePanel.updatePromotedPawn(type);
                          gamePanel.currentPawnToPromotePosition = (Position)cst.getArgument();
                    } catch (UndefinedGamePieceException ex) {
                        Logger.getLogger(EventListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (type == CONSTANT_TYPE.PROMOTED_TO_QUEEN) {
            if (gamePanel != null && checkPlayerIsDifferent(cst.getSender(), myModel.getGManager().getCurrentGame().getLocalPlayer())){
             try {
                        gamePanel.currentPawnToPromotePosition = (Position)cst.getArgument();
                        gamePanel.updatePromotedPawn(type);  
                    } catch (UndefinedGamePieceException ex) {
                        Logger.getLogger(EventListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (type == CONSTANT_TYPE.PROMOTED_TO_ROOK) {
                if (gamePanel != null && checkPlayerIsDifferent(cst.getSender(), myModel.getGManager().getCurrentGame().getLocalPlayer())){
              try {
                  gamePanel.currentPawnToPromotePosition = (Position)cst.getArgument();
                  gamePanel.updatePromotedPawn(type);
                          
                    } catch (UndefinedGamePieceException ex) {
                        Logger.getLogger(EventListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (type == CONSTANT_TYPE.PROMOTED_TO_KNIGHT) {
                if (gamePanel != null && checkPlayerIsDifferent(cst.getSender(), myModel.getGManager().getCurrentGame().getLocalPlayer())){
              try {
                 gamePanel.currentPawnToPromotePosition = (Position)cst.getArgument();
                  gamePanel.updatePromotedPawn(type);
                          
                    } catch (UndefinedGamePieceException ex) {
                        Logger.getLogger(EventListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            if(type == CONSTANT_TYPE.GAME_ENDED){
                                System.out.println("------------ SURRENDER -------------");
                System.out.println("mon pseudo : " + myModel.getGManager().getCurrentGame().getLocalPlayer().getPublicProfile().getPseudo());
                 System.out.println("pseudo distant : " + myModel.getGManager().getCurrentGame().getLocalPlayer().getPublicProfile().getPseudo());
                System.out.println("cst sender " + cst.getSender().getPublicProfile().getPseudo());
                System.out.println("cst receiver " + cst.getReceiver().getPublicProfile().getPseudo());

                if (gamePanel != null && checkPlayerIsDifferent(cst.getSender(), myModel.getGManager().getCurrentGame().getLocalPlayer())){
                  gamePanel.gameEndedRemotely(cst.getSender());
                }
            }
            
            /*
             if(gamePanel != null){
             if(evt.getNewValue() == myModel.getGManager().getCurrentGame().getLocalPlayer()){
             gamePanel.endOfGame(myModel.getGManager().getCurrentGame().getRemotePlayer());
             } else {
             gamePanel.endOfGame(myModel.getGManager().getCurrentGame().getLocalPlayer());
             }     
             }*/
        } else {
            System.out.println("EVENT DETECTED BUT INSTANCE FAILED");
        }
    }
}
