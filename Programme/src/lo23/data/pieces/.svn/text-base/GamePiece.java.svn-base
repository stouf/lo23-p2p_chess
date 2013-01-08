package lo23.data.pieces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lo23.data.Game;
import lo23.data.Move;
import lo23.data.Player;
import lo23.data.Position;
import lo23.data.exceptions.IllegalMoveException;
import lo23.ui.grid.Launch_Sound;
import lo23.ui.grid.Menu;

/**
 * use for all the pieces
 * @author khamidou, romain et guigou
 */
public abstract class GamePiece implements Serializable {

    static final long serialVersionUID = 1L;

    private boolean outOfBorder;
    protected Position position;
    private Player owner;
    private Game game;
    
    
    /**
     * Creates a new GamePiece object with a given position
     * 
     * @param position The position of the created object
     * @param owner The current piece's owner
     * @param game The game containig the current piece
     */
    public GamePiece(Position position, Player owner, Game game)
    {
        this.position = position;
        outOfBorder = false;
        this.owner = owner;
        this.game = game;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public boolean isOutOfBorder() {
        return outOfBorder;
    }

    public boolean setOutOfBorder(boolean val) {
        outOfBorder = val;
        return outOfBorder;
    }

    /**
     * This method simply returns the GamePiece position
     * 
     * @return A Position object
     */
    public Position getPosition() {
        return position;
    }

    public void movePiece(Position to) throws IllegalMoveException {        
        position = to;
    }
    
    /**
     * This method returns the game where the current GamePiece is contained
     * 
     * @return A Game object
     */
    public Game getGame() {
        return game;
    }
    
    /**
     * This method returns the current game piece's owner
     * 
     * @return The current piece's owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * This method return true if at x, y there is a piece and it's an enemy
     * 
     * @return true if enemy, false in oters cases
     * @author Romain ui-gird
     */
    public boolean thereIsAnEnemyAt(int x, int y)
    {
        return getGame().getPieceAtXY(x, y) != null //there is a piece at x, y
                &&
                getGame().getPieceAtXY(x, y).getOwner().getColor() != getOwner().getColor(); //it's an enemy
    }

    /**
     * This method return true if at x, y there no piece or an enemy one's. verify check too
     *
     * @return true if it's an empty case or an enemy piece
     * @author Romain ui-grid
     */
    public boolean isAValidMove(int x, int y)
    {
        if (x >= 0 && y >= 0 && x < 8 && y < 8 && getGame().getPieceAtXY(x, y) == null)
        {

            return (!(isOnCheckWithAMove(getPosition(), new Position(x, y))));
            
        }
        else
        {
            if (thereIsAnEnemyAt(x, y))
            {
                 return (!(isOnCheckWithAMove(getPosition(), new Position(x, y))));
            }
        }
        return false;
    }

    /**
     * This method add in list a new Position if it's a valid move /!\ care to return value
     *
     * @return true if there no obstacle (piece (friend of foe) or end of board)
     * @author Romain ui-grid
     */
    public boolean addIfValid(List<Position> list, int x, int y)
    {
        if (x >= 0 && y >= 0 && x < 8 && y < 8 && getGame().getPieceAtXY(x, y) == null)
        {
             if (!(isOnCheckWithAMove(getPosition(), new Position(x, y))))
             {
              list.add(new Position(x, y));
             }

             return true;
 
        }
        else
        {
            if (thereIsAnEnemyAt(x, y) && (!(isOnCheckWithAMove(getPosition(), new Position(x, y)))))
            {
                list.add(new Position(x, y));
            }
            return false;
        }

    }

     /**
     * This method test si une piece est sur cette case, en prenant en compte un déplacement
     *
     * @return true si il n'y a pas de piece !!!
     * @author Romain ui-grid
     */
    public boolean isThereSomebodyHere(int x, int y, Position from, Position to)
    {
        return (((getGame().getPieceAtXY(x , y) == null) || (from.getX() == x && from.getY() == y)) && (!(to.getX() == x && to.getY() == y)));
    }

      /**
     * This method test si une piece enemis est sur cette case, en prenant en compte un déplacement
     *
     * @return true si y'a une piece enemis
     * @author Romain ui-grid
     */
    public boolean isThereAnEnemyHere(int x, int y, Position from, Position to)
    {
        return ((thereIsAnEnemyAt(x, y)) && (!(from.getX() == x && from.getY() == y))) || (to.getX() == x && to.getY() == y);
    }

    
      /**
     * This method test si le roi est sur la case x, y en prenant en compte un déplacement
     *
     * @return true si y'a le roi sur la case
     * @author Romain ui-grid
     */
    public boolean isThereAKingHere(int x, int y, Position from, Position to, King king)
    {
        return (getGame().getPieceAtXY(x, y) == king) || (to.getX() == x && to.getY() == y  && getGame().getPieceAtXY(from.getX(), from.getY()) == king);
    }


     /**
     * This method return the list of possible move without checking check state
     *
     * @return the list
     * @author Romain ui-grid
     */
    public abstract List<Position> getPossibleMoves();

    public List<Position> getPossibleMovesWithCheck()
    {
        List<Position> list = getPossibleMoves();
        list = removeCheckingMove(list);
        return list;
    }



    public abstract boolean isResponsableOfCheck(King king, Position from, Position to);


    
    public boolean isOnCheckWithAMove(Position from, Position to)
    {
        
        Player player = getOwner();
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
          
                if (!piece.isOutOfBorder() &&  piece.isResponsableOfCheck(getOwner().getKing(), from, to)) //TODO change to king's position
                {
                     
                 //   System.out.println("déplacement en " + to.getX() + " - " + to.getY() +" pas possible à cause du " + piece.getClass().getSimpleName());
                    return true;
                }

        }
        return false;

    }




    public List<Position> removeCheckingMove(List<Position> listPossibleMove)
    {
        List<Position> listFinal = new ArrayList<Position>();
        int i = 0;
        while (i < listPossibleMove.size())
        {
            if (!(isOnCheckWithAMove(getPosition(), listPossibleMove.get(i))))
            {
                listFinal.add(listPossibleMove.get(i));
            }
            i++;
        }

        return listFinal;
    }


public boolean isPawnTop()
{
   // System.out.println("non !!");
    return false;
}


public boolean isDoingARook(Position to)
{
   // System.out.println("non !!");
    return false;
}

//seulement si le dernier mouvement de cette piece est un rock
public boolean haveDoneARook()
{
   System.out.println("non !!");
    return false;
}

}

