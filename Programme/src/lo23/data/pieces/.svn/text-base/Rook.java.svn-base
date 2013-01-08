/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lo23.data.pieces;

import java.util.ArrayList;
import java.util.List;
import lo23.data.Game;
import lo23.data.Move;
import lo23.data.Player;
import lo23.data.Position;
import lo23.data.exceptions.IllegalMoveException;

/**
 * Create the rook possible moves
 * @author romain et guilhem
 */
public class Rook extends GamePiece {

    /**
     * Creates a new Rook object with a given position
     * 
     * @param position The position of the created object
     * @param owner The current piece's owner
     * @param game The game containig the current piece
     */

    private Boolean alreadyMoved = false;

    public Rook(Position position, Player owner, Game game) 
    {
        super(position, owner, game);
                firstMove = true;
    }


    private boolean firstMove;

        @Override
    public void movePiece(Position to)throws IllegalMoveException {                       
        position = to;
        firstMove = false;
    }

    @Override
    public List<Position> getPossibleMoves() 
    {
       ArrayList<Position> positions = new ArrayList<Position>();

        
        boolean xp, xm, yp, ym;
        xp = true; //can move x+?
        xm = true; //can move x-  ?
        yp = true; //can move y+ ?
        ym = true; //can move y- ?

        int x = getPosition().getX();
        int y = getPosition().getY();

        Game game = getGame();

        //même fonctionement que le fou (bishop) mais en ligne droite

//        System.out.println(getPosition().getY());

        for (int i = 1; i < 8 && (xp || xm || yp || ym); i++)
        {

            

            if (xp && x + i < 8 && game.getPieceAtXY(x + i, y) == null)
            {
                positions.add(new Position(x + i, y));
            }
            else
            {
                if (xp && thereIsAnEnemyAt(x + i, y))
                {
                    positions.add(new Position(x + i, y));
                }
                xp = false;
            }

            if (xm && x - i >= 0 && game.getPieceAtXY(x - i, y) == null)
            {
                positions.add(new Position(x - i, y));
            }
            else
            {
                if (xm && thereIsAnEnemyAt(x - i, y))
                {
                    positions.add(new Position(x - i, y));
                }
                xm = false;
            }

            if (yp  && y + i < 8 && game.getPieceAtXY(x, y + i) == null)
            {
                positions.add(new Position(x, y + i));
            }
            else
            {
                if (yp  && thereIsAnEnemyAt(x, y + i))
                {
                    positions.add(new Position(x, y + i));
                }
                yp = false;
            }
            
            if (ym && y - i >= 0 && game.getPieceAtXY(x, y - i) == null)
            {
                positions.add(new Position(x, y - i));
            }
            else
            {
                if (ym && thereIsAnEnemyAt(x, y - i))
                {
                    positions.add(new Position(x, y - i));
                }
                ym = false;
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


        boolean xp, xm, yp, ym;
        xp = true; //can move x+?
        xm = true; //can move x-  ?
        yp = true; //can move y+ ?
        ym = true; //can move y- ?

        int x = getPosition().getX();
        int y = getPosition().getY();

        Game game = getGame();

        for (int i = 1; i < 8 && (xp || xm || yp || ym); i++)
        {

            if (xp && x + i < 8 && isThereSomebodyHere(x + i, y, from, to))
            {

            }
            else
            {
                if (xp && isThereAKingHere(x + i, y , from, to, king))
                {
                     return true;
                }
                xp = false;
            }

            if (xm && x - i >= 0 && isThereSomebodyHere(x - i, y, from, to))
            {

            }
            else
            {
                if (xm && isThereAKingHere(x - i, y, from, to, king))
                {
                      return true;
                }
                xm = false;
            }

            if (yp  && y + i < 8 && isThereSomebodyHere(x, y + i, from, to))
            {

            }
            else
            {
                if (yp  && isThereAKingHere(x, y + i, from, to, king))
                {
                    return true;
                }
                yp = false;
            }

            if (ym && y - i >= 0 && isThereSomebodyHere(x, y - i, from, to))
            {

            }
            else
            {
                if (ym && isThereAKingHere(x, y - i, from, to, king))
                {
                      return true;
                }
                ym = false;
            }

        }

        return false;
    
    }
}
