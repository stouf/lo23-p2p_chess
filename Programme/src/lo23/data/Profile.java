package lo23.data;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import lo23.utils.Enums.STATUS;

/**
 * Profile is a player's profile. It contains all the information used to
 * identify a player such as his pseudo and his status.
 */
public class Profile implements Serializable {

    static final long serialVersionUID = 1L;
    
    private String profileId;
    private String pseudo;
    private char[] password;
    private STATUS status;
    private String ipAddress;
    private ImageIcon avatar;
    private String name;
    private String firstName;
    private int age;
    private ArrayList<Invitation> invitations;
    private ArrayList<Player> players;
    private int wonGames = 0;
    private int lostGames = 0;
    private int drawGames = 0;

    /**
     * Constructor for the Profile class. Take all the information needed as
     * arguments.
     *
     * @param profileId the unique profile identifier
     * @param pseudo the player's nickname
     * @param password the player's password
     * @param status the player's status
     * @param ipAddress the player's IP address
     * @param avatar the player's avatar (an image)
     * @param name the player's lastname
     * @param firstName the player's firstname
     * @param age the player's age
     */
    public Profile(String profileId, String pseudo, char[] password, STATUS status, String ipAddress, ImageIcon avatar, String name, String firstName, int age) {
        this.profileId = profileId;
        this.pseudo = pseudo;
        this.password = password;
        this.status = status;
        this.ipAddress = ipAddress;
        this.avatar = avatar;
        this.name = name;
        this.firstName = firstName;
        this.age = age;
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
     * Setter for the player's age
     *
     * @param age the player's new age
     */
    public void setAge(int age) {
        this.age = age;
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
     * Setter for the player's avatar
     *
     * @param avatar the new player's avatar
     */
    public void setAvatar(ImageIcon avatar) {
        this.avatar = avatar;
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
     * Setter for the player's firstname
     *
     * @param firstName the player's new firstname
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
     * Getter for the player's IP address
     *
     * @return the player's IP address
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Setter for the player's IP address
     *
     * @param ipAddress the player's new IP address
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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
     * Setter for the player's lastname
     *
     * @param name the player's new lastname
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the player's password
     *
     * @return the player's password
     */
    public char[] getPassword() {
        return password;
    }

    /**
     * Setter for the player's password
     *
     * @param password the player's new password
     */
    public void setPassword(char[] password) {
        this.password = password;
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
     * Setter for the player's unique profile ID
     *
     * @param profileId the player's new unique profile ID
     */
    public void setProfileId(String profileId) {
        this.profileId = profileId;
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
     * Setter for the player's nickname
     *
     * @param pseudo the player's new nickname
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Getter for the player's status
     *
     * @return the player's status
     */
    public STATUS getStatus() {
        return status;
    }

    /**
     * Setter for the player's status
     *
     * @param status the player's new status
     */
    public void setStatus(STATUS status) {
        this.status = status;
    }

    public int getWonGames() {
        return wonGames;
    }

    public void incrementWonGames() {
        this.wonGames++;
    }

    public int getLostGames() {
        return lostGames;
    }

    public void incrementLostGames() {
        this.lostGames++;
    }

    public int getDrawGames() {
        return drawGames;
    }

    public void incrementDrawGames() {
        this.drawGames++;
    }

    /**
     * Getter for the player's PublicProfile (i.e. Profile without the password
     * attribut)
     *
     * @return the player's PublicProfile
     */
    public PublicProfile getPublicProfile() {
        return new PublicProfile(this.profileId, this.pseudo, this.status, this.ipAddress, this.avatar, this.name, this.firstName, this.age, this.wonGames, this.lostGames, this.drawGames);
    }
}
