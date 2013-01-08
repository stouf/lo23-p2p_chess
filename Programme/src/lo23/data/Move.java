package lo23.data;

import lo23.data.pieces.GamePiece;
import java.io.Serializable;

/**
 *
 * @author khamidou
 */
public class Move extends Event implements Serializable {
    static final long serialVersionUID = 1L;
    Position from;
    Position to;
    GamePiece piece;

    public Move(Position from, Position to, GamePiece piece) {
        this.from = from;
        this.to = to;
        this.piece = piece;
    }

    public Position getFrom() {
        return from;
    }

    public GamePiece getPiece() {
        return piece;
    }

    public Position getTo() {
        return to;
    }

    public String toString() {
        return "Move: " + piece + " from : " + from + " to : " + to;
    }
}
