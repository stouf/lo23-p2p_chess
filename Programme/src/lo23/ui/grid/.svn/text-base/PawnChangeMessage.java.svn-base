

package lo23.ui.grid;
import javax.swing.JOptionPane;
import lo23.data.pieces.GamePiece;
import lo23.data.pieces.Pawn;
import lo23.utils.Enums.PROMOTED_PIECES_TYPES;

/** Message Box for paw tranformation
 * this class provides a personnal managment of the paw tranformation.
 * 
 * @author Laura, guigou
 */

public class PawnChangeMessage{

    
    Pawn pawn;
    public PawnChangeMessage(Pawn p) {
        this.pawn = p ;
    }
    
    /**
     * Display the window with the different pieces
     * @return GamePiece the piece that will replace our pawn
     */
    
    
    static PROMOTED_PIECES_TYPES display(GamePiece currentPiece) 
    {
        String[] pieces = {"Knight", "Bishop", "Rook", "Queen"};
      
    // Message creation
    JOptionPane jop = new JOptionPane();
    int rang = jop.showOptionDialog(null, "Transform the pawn into : ", "Pawn", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
      null, pieces, pieces[3]);
    
        switch(rang)
        {
            //return an Enum value
            case 0 : return PROMOTED_PIECES_TYPES.KNIGHT;
            case 1 : return PROMOTED_PIECES_TYPES.BISHOP;
            case 2 : return PROMOTED_PIECES_TYPES.ROOK;
            case 3 : return PROMOTED_PIECES_TYPES.QUEEN;
            default: return PROMOTED_PIECES_TYPES.QUEEN; // There's no reason to pass here, but it could be safer to do this
        }
    }
    
  
}
