/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lo23.data;

import java.io.Serializable;
import java.util.ArrayList;
import lo23.data.pieces.GamePiece;
import lo23.data.pieces.King;
import lo23.utils.Enums.COLOR;

/**
 *
 * @author khamidou
 */
public class Player implements Serializable {

    static final long serialVersionUID = 1L;
    
    private COLOR playerColor;
    private long time = 0; // Temps de jeu déjà utilisé par le joueur
    private long startTime = 0; // Temps début du tour
    private long endTime = 0; // Temps fin du tou
    private long remainingTime = 0; // Temps total aloué pour jouer
    private ArrayList<GamePiece> pieces;
    private PublicProfile publicProfile;
    
    private King kingHandle = null; // getKing() is used a lot so we save a reference to it.

    public Player(COLOR playerColor, long remainingTime, PublicProfile publicProfile ) {
        this.playerColor = playerColor;
        this.remainingTime = remainingTime;
        this.pieces = new ArrayList<GamePiece>();
        this.publicProfile=publicProfile;
    }
   
    public void startTime() {
        startTime = System.currentTimeMillis();
    }

    public void stopTime() {
        endTime = System.currentTimeMillis();
        time += endTime - startTime;
    }

    public long getRemainingTime() {
        return remainingTime - time;
    }

    public King getKing() {
        return kingHandle;
    }

    /**
     * This method gives the current player's color
     * 
     * @return The current player's color
     */
    public COLOR getColor() {
        return playerColor;
    }

    /**
     * Used by the Game constructor to provide to the Player
     * a list of the gamepieces.
     * @param pc piece to add
     */
    public void addPiece(GamePiece pc) {
        pieces.add(pc);
        if(pc instanceof King) {
            kingHandle = (King) pc;
        }
    }



      /**
     * getter of pieces
     *
     * @autor : romain ui grid
     */
    public ArrayList<GamePiece> getPieces()
    {
        return pieces;
    }

    public boolean isOncheck()
    {


        Player player = this;
        if (player == getGame().getLocalPlayer())
        {
            player = getGame().getRemotePlayer();
        }
        else
        {
            player = getGame().getLocalPlayer();
        }

        ArrayList<GamePiece> gamePieces = player.getPieces();


        for (int i = 0; i < gamePieces.size(); ++i)
        {
            GamePiece piece = gamePieces.get(i);

                //List<Position> pos = piece.getPossibleMoves();
             //   System.out.println(piece.getPosition().getX() + " - " + piece.getPosition().getY() + " -> " + piece.getClass().getSimpleName());
                if (!piece.isOutOfBorder() && piece.isResponsableOfCheck(getKing(), new Position(10, 10), new Position(10, 10)))
                {

                    return true;
                }


        }
        return false;

    }

    public boolean isCheckAndMat()
    {
        Player player = this;

        ArrayList<GamePiece> gamePieces = player.getPieces();

       
        for (int i = 0; i < gamePieces.size(); ++i)
        {
            GamePiece piece = gamePieces.get(i);

                //List<Position> pos = piece.getPossibleMoves();
             //   System.out.println(piece.getPosition().getX() + " - " + piece.getPosition().getY() + " -> " + piece.getClass().getSimpleName());
                if (!piece.isOutOfBorder() && !piece.getPossibleMovesWithCheck().isEmpty())
                {
                  System.out.println("TAILLE DES COUPS " + piece.getClass() + " position : (" + piece.getPosition().getX() + ")("+ piece.getPosition().getX()+")  coups : " + piece.getPossibleMovesWithCheck().size());
                    return false;
                }
        }
       
        return isOncheck();
    }

    
    public PublicProfile getPublicProfile() {
        return publicProfile;
    }

    // joseph, fouette moi
    public Game getGame() {
        return getKing().getGame();
    }

       //vérifie si le player peu jouer un coup. Si aucun coup possible, c'est un pat
    // il faut d'abord vérifier si ce n'est pas un échec et mat en premier :
    // si le jeu est en echec et mat, la méthode va retourner vrai !
    //author : romain
    public boolean isOnPat()
    {


        ArrayList<GamePiece> gamePieces = getPieces();


        for (int i = 0; i < gamePieces.size(); ++i) // pour toute les pièces du joueur
        {
             GamePiece piece = gamePieces.get(i);
                if (!piece.isOutOfBorder() && !piece.getPossibleMovesWithCheck().isEmpty()) // si la piece est toujours en jeu et qu'elle peut faire des déplacements
                {
                    return false; // ce n'est pas un pat
                }


        }


        //sinon on ne peut pas jouer.
        return true;

    }
}
