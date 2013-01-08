package lo23.ui.grid;

/**
 * Create all the position on the board
 * @author all in IHM Grille
 */
public class PositionOnBoard {

	private int x;
	private int y;
	
	public PositionOnBoard(int xPosition, int yPosition) {
		x = xPosition;
		y = yPosition;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object P) {
		if (((PositionOnBoard) P).getX() == x && ((PositionOnBoard) P).getY() == y) return true;
		else return false;
	}
	
	@Override
	public int hashCode() {
		String temp = String.valueOf(x) + String.valueOf(y);
		return Integer.valueOf(temp);
	}
	
}
