package lo23.data;

import java.util.Date;
import lo23.utils.Enums.COLOR;

/**
 * Invitation to a new game
 */
public class NewInvitation extends Invitation {

    private COLOR color;
    private long duration;
    private Date date;

    /**
     * Constructor for the NewInvitation class
     * @param color the host's color
     * @param duration the duration of the game
     * @param host the host's PublicProfile
     * @param guest the guest's PublicProfile
     */
    public NewInvitation(COLOR color, long duration, PublicProfile host, PublicProfile guest) {
        super(host, guest);
        this.color = color;
        this.duration = 30*60;
        this.date = new Date();
    }

    /**
     * Getter for the host's color
     * @return the color chosen by the host
     */
    public COLOR getColor() {
        return color;
    }

    /**
     * Getter for the duration of the game
     * @return the duration of the game
     */
    public long getDuration() {
        return duration;
    }

    /**
     * Getter for the date of the invitation
     * @return the date of the invitation
     */
    public Date getDate() {
        return date;
    }
}
