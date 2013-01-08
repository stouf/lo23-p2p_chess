package lo23.data;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import lo23.utils.Enums.STATUS;

/**
 * PublicProfile is a copy of a Profile without the password attribute.
 * Instances of PublicProfiles SHOULD BE generated using the
 * Profile.getPublicProfile() method.
 */
public class PublicProfile implements Serializable {

    static final long serialVersionUID = 1L;
    
    private String profileId;
    private String pseudo;
    private STATUS status;
    private String ipAddress;
    private ImageIcon avatar;
    private String name;
    private String firstName;
    private int age;
    private ArrayList<Invitation> invitations;
    private ArrayList<Player> players;
    private int wonGames;
    private int lostGames;
    private int drawGames;

    /**
     * Constructor for the PublicProfile class. This constructor SHOULD NOT be
     * called directly. Instead the Profile.getPublicProfile() method SHOULD be
     * used.
     *
     * @param profileId the unique profile identifier
     * @param pseudo the player's nickname
     * @param status the player's status
     * @param ipAddress the player's IP address
     * @param avatar the player's avatar (an image)
     * @param name the player's lastname
     * @param firstName the player's firstname
     * @param age the player's age
     */
    public PublicProfile(String profileId, String pseudo, STATUS status, String ipAddress, ImageIcon avatar, String name, String firstName, int age, int wonGames, int lostGames, int drawGames) {
        this.profileId = profileId;
        this.pseudo = pseudo;
        this.status = status;
        this.ipAddress = ipAddress;
        this.avatar = avatar;
        this.name = name;
        this.firstName = firstName;
        this.age = age;
        this.wonGames = wonGames;
        this.lostGames = lostGames;
        this.drawGames = drawGames;
        this.invitations = new ArrayList<Invitation>();
        this.players = new ArrayList<Player>();
    }

    /**
     * Getter for the player's age
     *
     * @return the player's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Getter for the player's avatar
     *
     * @return the player's avatar
     */
    public ImageIcon getAvatar() {
        return avatar;
    }

    /**
     * Getter for the player's firstname
     *
     * @return the player's firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for the player's invitations
     *
     * @return the player's invitations
     */
    public ArrayList<Invitation> getInvitations() {
        return invitations;
    }

    /**
     * Setter for the player's avatar
     *
     */
    public void setAvatar(ImageIcon avatar) {
        this.avatar = avatar;
    }
    /**
     * Getter for the player's IP address
     *
     * @return the player's IP address
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Getter for the player's lastname
     *
     * @return the player's lastname
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the player's players list
     *
     * @return the player's players list
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Getter for the player's unique profile ID
     *
     * @return the player's unique profile ID
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * Getter for the player's nickname
     *
     * @return the player's nickname
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Getter for the player's status
     *
     * @return the player's status
     */
    public STATUS getStatus() {
        return status;
    }

    public int getWonGames() {
        return wonGames;
    }

    public int getLostGames() {
        return lostGames;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getDrawGames() {
        return drawGames;
    }

    @Override
    public String toString() {
        return this.getPseudo();
    }
}
