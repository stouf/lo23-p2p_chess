package lo23.data.pieces;

import java.util.ArrayList;
import java.util.List;
import lo23.data.Game;
import lo23.data.Player;
import lo23.data.Position;

/**
 * Create the bishop possible moves
 * @author romain et guigou
 */
public class Bishop extends GamePiece
{

    /**
     * Creates a new Bishop object with a given position
     * 
     * @param position The position of the created object
     * @param owner The current piece's owner
     * @param game The game containig the current piece
     */
    public Bishop(Position position, Player owner, Game game)
    {
        super(position, owner, game);
    }

    /**
     *
     * @author Romain et Guilhem
     */
    @Override
    public List<Position> getPossibleMoves()
    {

        //le tableau de résultats
        ArrayList<Position> positions = new ArrayList<Position>();

        boolean xpyp, xmyp, xpym, xmym;
        xpyp = true; //can move x+ and y+ ?
        xmyp = true; //can move x- and y+ ?
        xpym = true; //can move x+ and y- ?
        xmym = true; //can move x- and y- ?

        int x = getPosition().getX();
        int y = getPosition().getY();

        Game game = getGame();

        //on parcourt les 4 demi-diagonales en même temps
        //on commence à 1 pour ne pas regarder la position même du cavalier
        for (int i = 1; i < 8 && (xpyp || xmyp || xpym || xmym); i++)
        {

            //si on n'a pas dépassé la grille
            //et si il n'y a pas de piece :
            if (xpyp && x + i < 8 && y + i < 8 && game.getPieceAtXY(x + i, y + i) == null)
            {
                //c'est un coup possible
                positions.add(new Position(x + i, y + i));
            }
            else
            {
                //sinon, si c'est un enemis
                if (xpyp && thereIsAnEnemyAt(x + i, y + i))
                {
                    //c'est aussi un coup possible mais ...
                    positions.add(new Position(x + i, y + i));
                }
                // on arrete de vérifier ce sens
                xpyp = false;
            }


            //on fait pareil pour les 3 autres directions

            if (xmyp && x - i >= 0 && y + i < 8 && game.getPieceAtXY(x - i, y + i) == null)
            {
                positions.add(new Position(x - i, y + i));
            }
            else
            {
                if (xmyp && thereIsAnEnemyAt(x - i, y + i))
                {
                    positions.add(new Position(x - i, y + i));
                }
                xmyp = false;
            }

            if (xpym && x + i < 8 && y - i >= 0 && game.getPieceAtXY(x + i, y - i) == null)
            {
                positions.add(new Position(x + i, y - i));
            }
            else
            {
                if (xpym && thereIsAnEnemyAt(x + i, y - i))
                {
                    positions.add(new Position(x + i, y - i));
                }
                xpym = false;
            }

              if (xmym && x - i >= 0 && y - i >= 0 && game.getPieceAtXY(x - i, y - i) == null)
            {
                positions.add(new Position(x - i, y - i));
            }
            else
            {
                if (xmym && thereIsAnEnemyAt(x - i, y - i))
                {
                    positions.add(new Position(x - i, y - i));
                }
                xmym = false;
            }

        }

        return positions;
    }
 /**
     *
     * @author Romain
     */
    @Override
    public boolean isResponsableOfCheck(King king, Position from, Position to)
    {

        //même fonctionnement que pour getPosibleMove, mais on ne vérifie pas les échecs et on ne regarde que si le roi est en échec.
        // prend en compte la grille + un déplacement (permet de tester un Move sans modifier la grille)

        //System.out.println(to.getX() + " - " + to.getY());
        
        if (getPosition().getX() == to.getX() && getPosition().getY() == to.getY())
        {
            return false; //on est la piece qui vient d'être mangé !
        }


        boolean xpyp, xmyp, xpym, xmym;
        xpyp = true; //can move x+ and y+ ?
        xmyp = true; //can move x- and y+ ?
        xpym = true; //can move x+ and y- ?
        xmym = true; //can move x- and y- ?

        int x = getPosition().getX();
        int y = getPosition().getY();

        Game game = getGame();

        for (int i = 1; i < 8 && (xpyp || xmyp || xpym || xmym); i++)
        {


            if (xpyp && x + i < 8 && y + i < 8 && (isThereSomebodyHere(x + i, y + i, from, to)))
            {

            }
            else
            {
                if (xpyp && isThereAKingHere(x + i, y + i, from, to, king))
                {
                    return true;
                }
                xpyp = false;
            }

            if (xmyp && x - i >= 0 && y + i < 8 && isThereSomebodyHere(x - i, y + i, from, to))
            {

            }
            else
            {
                if (xmyp && isThereAKingHere(x - i, y + i, from, to, king))
                {
                    return true;
                }
                xmyp = false;
            }

            if (xpym && x + i < 8 && y - i >= 0 && isThereSomebodyHere(x + i, y - i, from, to))
            {
 
            }
            else
            {
                if (xpym && isThereAKingHere(x + i, y - i, from, to, king))
                {
                    return true;
                }
                xpym = false;
            }

              if (xmym && x - i >= 0 && y - i >= 0 && isThereSomebodyHere(x - i, y - i, from, to))
            {
   
            }
            else
            {
                if (xmym && isThereAKingHere(x - i, y - i, from, to, king))
                {
                            
                    return true;
                }
                xmym = false;
            }

        }

        return false;
    }
  
}

