package lo23.communication.message;
import lo23.data.Move;

/**
 * This message is sent while a chess piece is moved.
 */
public class MoveMsg extends GameMessage {

    private Move move;

    /**
     * Contructor of the MoveMsg.
     */
    public MoveMsg(Move movement) {
        this.move = movement;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
