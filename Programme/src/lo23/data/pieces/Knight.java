package lo23.data.pieces;

import java.util.ArrayList;
import java.util.List;
import lo23.data.Game;
import lo23.data.Player;
import lo23.data.Position;

/**
 * Create the knight possible moves
 * @author romain et guilhem
 */
public class Knight extends GamePiece {
    
    /**
     * Creates a new Knight object with a given position
     * 
     * @param position The position of the created object
     * @param owner The current piece's owner
     * @param game The game containig the current piece
     */

    private Boolean alreadyMoved = false;
    private Boolean alreadyThreatened = false;

    public Knight(Position position, Player owner, Game game) {
        super(position, owner, game);
    }
    
    @Override
    public List<Position> getPossibleMoves() {
  

        ArrayList<Position> positions = new ArrayList<Position>();

    

        int x = getPosition().getX();
        int y = getPosition().getY();

        Game game = getGame();

        //test des 6 cases
        
            if (x + 1 < 8 && y + 2 < 8 && game.getPieceAtXY(x + 1, y + 2) == null)
            {
                positions.add(new Position(x + 1, y + 2));
            }
            else
            {
                if (thereIsAnEnemyAt(x + 1, y + 2))
                {
                    positions.add(new Position(x + 1, y + 2));
                }
            }

            if (x - 1 >= 0 && y + 2 < 8 && game.getPieceAtXY(x - 1, y + 2) == null)
            {
                positions.add(new Position(x - 1, y + 2));
            }
            else
            {
                if (thereIsAnEnemyAt(x - 1, y + 2))
                {
                    positions.add(new Position(x - 1, y + 2));
                }
            }
            
            if (x - 2 >=0 && y + 1 < 8 && game.getPieceAtXY(x - 2, y + 1) == null)
            {
                positions.add(new Position(x - 2, y + 1));
            }
            else
            {
                if (thereIsAnEnemyAt(x - 2, y + 1))
                {
                    positions.add(new Position(x - 2, y + 1));
                }
            }
            
            if (x + 2 < 8 && y + 1 < 8 && game.getPieceAtXY(x + 2, y + 1) == null)
            {
                positions.add(new Position(x + 2, y + 1));
            }
            else
            {
                if (thereIsAnEnemyAt(x + 2, y + 1))
                {
                    positions.add(new Position(x + 2, y + 1));
                }
            }
            
            if (x - 2 >=0 && y - 1 >=0 && game.getPieceAtXY(x - 2, y - 1) == null)
            {
                positions.add(new Position(x - 2, y - 1));
            }
            else
            {
                if (thereIsAnEnemyAt(x - 2, y - 1))
                {
                    positions.add(new Position(x - 2, y - 1));
                }
            }
            
            if (x + 2 < 8 && y - 1 >=0 && game.getPieceAtXY(x + 2, y - 1) == null)
            {
                positions.add(new Position(x + 2, y - 1));
            }
            else
            {
                if (thereIsAnEnemyAt(x + 2, y - 1))
                {
                    positions.add(new Position(x + 2, y - 1));
                }
            }
            
              if (x - 1  >= 0 && y - 2 >=0 && game.getPieceAtXY(x - 1, y - 2) == null)
            {
                positions.add(new Position(x - 1, y - 2));
            }
            else
            {
                if (thereIsAnEnemyAt(x - 1, y - 2))
                {
                    positions.add(new Position(x - 1, y - 2));
                }
            }
              
            if (x + 1 < 8 && y - 2 >=0 && game.getPieceAtXY(x + 1, y - 2) == null)
            {
                positions.add(new Position(x + 1, y - 2));
            }
            else
            {
                if (thereIsAnEnemyAt(x + 1, y - 2))
                {
                    positions.add(new Position(x + 1, y - 2));
                }
            }

        return positions;    
    }
   @Override
    public boolean isResponsableOfCheck(King king, Position from, Position to)
    {

               //même fonctionnement que pour getPosibleMove, mais on ne vérifie pas les échecs et on ne regarde que si le roi est en échec.
        // prend en compte la grille + un déplacement (permet de tester un Move sans modifier la grille)

         if (getPosition().getX() == to.getX() && getPosition().getY() == to.getY())
        {
            return false; //on est la piece qui vient d'être mangé !
        }

        int x = getPosition().getX();
        int y = getPosition().getY();

        Game game = getGame();

        //6 cases

            if (x + 1 < 8 && y + 2 < 8 && isThereSomebodyHere(x + 1, y + 2, from, to))
            {

            }
            else
            {
                if (isThereAKingHere(x + 1, y + 2, from, to, king))
                {
                    return true;
                }
            }

            if (x - 1 >= 0 && y + 2 < 8 && isThereSomebodyHere(x - 1, y + 2, from, to))
            {

            }
            else
            {
                if (isThereAKingHere(x - 1, y + 2, from, to, king))
                {
                    return true;
                }
            }

            if (x - 2 >=0 && y + 1 < 8 && isThereSomebodyHere(x - 2, y + 1, from, to))
            {

            }
            else
            {
                if (isThereAKingHere(x - 2, y + 1, from, to, king))
                {
                    return true;
                }
            }

            if (x + 2 < 8 && y + 1 < 8 && isThereSomebodyHere(x + 2, y + 1, from, to))
            {

            }
            else
            {
                if (isThereAKingHere(x + 2, y + 1, from, to, king))
                {
                    return true;
                }
            }

            if (x - 2 >=0 && y - 1 >=0 && isThereSomebodyHere(x - 2, y - 1, from, to))
            {

            }
            else
            {
                if (isThereAKingHere(x - 2, y - 1, from, to, king))
                {
                    return true;
                }
            }

            if (x + 2 < 8 && y - 1 >=0 && isThereSomebodyHere(x + 2, y - 1, from, to))
            {

            }
            else
            {
                if (isThereAKingHere(x + 2, y - 1, from, to, king))
                {
                    return true;
                }
            }
            
           
            if (x - 1  >= 0 && y - 2 >=0 && isThereSomebodyHere(x - 1, y - 2, from, to))
            {
              
            }
            else
            {
                if (isThereAKingHere(x - 1, y - 2, from, to, king))
                {
                    return true;
                }
            }
              
            if (x + 1 < 8 && y - 2 >=0 && isThereSomebodyHere(x + 1, y - 2, from, to))
            {
               
            }
            else
            {
                if (isThereAKingHere(x + 1, y - 2, from, to, king))
                {
                   return true;
                }
            }
            

        return false;
    }
}
