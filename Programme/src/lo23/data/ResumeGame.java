package lo23.data;

/**
 * Invitation to resume an existing unfinished game
 */
public class ResumeGame extends Invitation {

    private Game game;

    /**
     * Getter for the unfinished game
     * @return the unfinished game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Setter for the unfinished game
     * @param game the unfinished game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Constructor for the ResumeGame class
     * @param host the host of the game
     * @param guest the guest of the game
     * @param game the unfinished game
     */
    public ResumeGame(PublicProfile host, PublicProfile guest, Game game) {
        super(host, guest);
        this.game = game;
    }
}
