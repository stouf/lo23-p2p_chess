package lo23.data.pieces;

import java.util.ArrayList;
import java.util.List;
import lo23.data.Game;
import lo23.data.Move;
import lo23.data.Player;
import lo23.data.Position;
import lo23.data.exceptions.IllegalMoveException;

/**
 * Create the king possible moves
 * @author romain et guilhem
 */
public class King extends GamePiece {
    
    /**
     * Creates a new King object with a given position
     * 
     * @param position The position of the created object
     * @param owner The current piece's owner
     * @param game The game containig the current piece
     */
    public King(Position position, Player owner, Game game) {
        super(position, owner, game);
        firstMove = true;
        justHaveRook = false;
    }


    private boolean firstMove;
    private boolean justHaveRook;

        @Override
    public void movePiece(Position to) throws IllegalMoveException {
        if (isDoingARook(to))
        {
            justHaveRook = true;
            if (to.getX() == 1)
            {
                getGame().getPieceAtXY(0, to.getY()).movePiece(new Position(2, to.getY()));
            }
            else
            {
                getGame().getPieceAtXY(7, to.getY()).movePiece(new Position(5, to.getY()));
            }
        }
        else
        {
            justHaveRook = false;
        }         
    
        position = to;
        firstMove = false;
    }



    @Override
    public List<Position> getPossibleMoves() 
    {
   

        ArrayList<Position> positions = new ArrayList<Position>();

      
//        xpyp = true; //can move x+ and y+ ?
//        xmyp = true; //can move x- and y+ ?
//        xpym = true; //can move x+ and y- ?
//        xmym = true; //can move x- and y- ?
//        xp = true;   //can move x+ ?
//        xm = true;   //can move x- ?
//        yp = true;   //can move y+ ?
//        ym = true;   //can move y- ?

        // on test les cases autours du roi
        
        int x = getPosition().getX();
        int y = getPosition().getY();

        Game game = getGame();

           if(x + 1 < 8 && y + 1 < 8 && game.getPieceAtXY(x + 1, y + 1) == null)
            {
                positions.add(new Position(x + 1, y + 1));
            }
            else
            {
                if (thereIsAnEnemyAt(x + 1, y + 1))
                {
                    positions.add(new Position(x + 1, y + 1));
                }
            }

            if (x - 1 >= 0 && y + 1 < 8 && game.getPieceAtXY(x - 1, y + 1) == null)
            {
                positions.add(new Position(x - 1, y + 1));
            }
            else
            {
                if (thereIsAnEnemyAt(x - 1, y + 1))
                {
                    positions.add(new Position(x - 1, y + 1));
                }
              
            }

            if (x + 1 < 8 && y - 1 >= 0 && game.getPieceAtXY(x + 1, y - 1) == null)
            {
                positions.add(new Position(x + 1, y - 1));
            }
            else
            {
                if (thereIsAnEnemyAt(x + 1, y - 1))
                {
                    positions.add(new Position(x + 1, y - 1));
                }
                
            }

            if (x - 1 >= 0 && y - 1 >= 0 && game.getPieceAtXY(x - 1, y - 1) == null)
            {
                positions.add(new Position(x - 1, y - 1));
            }
            else
            {
                if (thereIsAnEnemyAt(x - 1, y - 1))
                {
                    positions.add(new Position(x - 1, y - 1));
                }
            }

        
            if (x + 1 < 8 && game.getPieceAtXY(x + 1, y) == null)
            {
                positions.add(new Position(x + 1, y));


                if (firstMove) //rook 1
                {
                    boolean isPossible = true;
                    for (int i = x + 1;i < 7;i++)
                    {
                        if (isPossible)
                        {
                            isPossible = game.getPieceAtXY(i, y) == null && !isOnCheckWithAMove(position, new Position(i, y));
                        }
                    }
                    //if (game.getPieceAtXY(7, y).
                    //TODO utiliser firstMove de Rook
                    if (isPossible)
                    {
                        if (getPosition().getX() == 4 )
                        {
                            positions.add(new Position(6, y));
                        }
                        else
                        {
                            positions.add(new Position(5, y));
                        }
                    }

                }


            }
            else
            {
                if (thereIsAnEnemyAt(x + 1, y))
                {
                    positions.add(new Position(x + 1, y));
                }
            }

            if (x - 1 >= 0 && game.getPieceAtXY(x - 1, y) == null)
            {
                positions.add(new Position(x - 1, y));
                //rook 2
                    if (firstMove)
                {
                    boolean isPossible = true;
                    for (int i = x - 1;i > 0;i--)
                    {
                       //  System.out.println(i);
                        if (isPossible)
                        {

                            isPossible = game.getPieceAtXY(i, y) == null && !isOnCheckWithAMove(position, new Position(i, y));
                        }
                    }
                    //if (game.getPieceAtXY(0, y).
                    //TODO utiliser firstMove de Rook
                    if (isPossible)
                    {
                        if (getPosition().getX() == 3 )
                        {
                            positions.add(new Position(1, y));
                        }
                        else
                        {
                            positions.add(new Position(2, y));
                        }
                    }
                }
            }
            else
            {
                if (thereIsAnEnemyAt(x - 1, y))
                {
                    positions.add(new Position(x - 1, y));
                }
            }

            if (y + 1 < 8 && game.getPieceAtXY(x, y + 1) == null)
            {
                positions.add(new Position(x, y + 1));
            }
            else
            {
                if (thereIsAnEnemyAt(x, y + 1))
                {
                    positions.add(new Position(x, y + 1));
                }
            }
            
            if (y - 1 >= 0 && game.getPieceAtXY(x, y - 1) == null)
            {
                positions.add(new Position(x, y - 1));
            }
            else
            {
                if (thereIsAnEnemyAt(x, y - 1))
                {
                    positions.add(new Position(x, y - 1));
                }
            }
        return positions;

    }
   @Override
    public boolean isResponsableOfCheck(King king, Position from, Position to)
    {



               //même fonctionnement que pour getPosibleMove, mais on ne vérifie pas les échecs et on ne regarde que si le roi est en échec.
        // prend en compte la grille + un déplacement (permet de tester un Move sans modifier la grille)


        int x = getPosition().getX();
        int y = getPosition().getY();

        Game game = getGame();

           if(x + 1 < 8 && y + 1 < 8 && isThereSomebodyHere(x + 1, y + 1, from, to))
            {

            }
            else
            {
                if (isThereAKingHere(x + 1, y + 1, from, to, king))
                {
                    return true;
                }
            }

            if (x - 1 >= 0 && y + 1 < 8 && isThereSomebodyHere(x - 1, y + 1, from, to))
            {

            }
            else
            {
                if (isThereAKingHere(x - 1, y + 1, from, to, king))
                {
                    return true;
                }

            }

            if (x + 1 < 8 && y - 1 >= 0 && isThereSomebodyHere(x + 1, y - 1, from, to))
            {

            }
            else
            {
                if (isThereAKingHere(x + 1, y - 1, from, to, king))
                {
                   return true;
                }

            }

            if (x - 1 >= 0 && y - 1 >= 0 && isThereSomebodyHere(x - 1, y - 1, from, to))
            {

            }
            else
            {
                if (isThereAKingHere(x - 1, y - 1, from, to, king))
                {
                 return true;
                }
            }


            if (x + 1 < 8 && isThereSomebodyHere(x + 1, y, from, to))
            {

            }
            else
            {
                if (isThereAKingHere(x + 1, y, from, to, king))
                {
                   return true;
                }
            }

            if (x - 1 >= 0 && isThereSomebodyHere(x - 1, y, from, to))
            {

            }
            else
            {
                if (isThereAKingHere(x - 1, y, from, to, king))
                {
                   return true;
                }
            }

            if (y + 1 < 8 && isThereSomebodyHere(x, y + 1, from, to))
            {

            }
            else
            {
                if (isThereAKingHere(x, y + 1, from, to, king))
                {
                   return true;
                }
            }

            if (y - 1 >= 0 && isThereSomebodyHere(x, y - 1, from, to))
            {

            }
            else
            {
                if (isThereAKingHere(x, y - 1, from, to, king))
                {
                   return true;
                }
            }
        return false;

   }


@Override
public boolean isDoingARook(Position to)
{
    return (to.getX() - position.getX() > 1 || to.getX() - position.getX() < -1);   
}

//seulement si le dernier mouvement de cette piece est un rock
    @Override
public boolean haveDoneARook()
{
    return justHaveRook;
}



}


