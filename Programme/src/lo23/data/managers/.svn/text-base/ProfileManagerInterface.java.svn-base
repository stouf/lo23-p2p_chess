package lo23.data.managers;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import lo23.data.Invitation;
import lo23.data.Profile;
import lo23.data.PublicProfile;
import lo23.utils.Enums.COLOR;
import lo23.utils.Enums.STATUS;
import lo23.data.exceptions.*;

/**
 * Interface for the ProfileManager class. This interface specialize in Profile
 * management operations.
 */
public interface ProfileManagerInterface {

    /**
     * Getter for the current profile
     * @return the current profile
     */
    public Profile getCurrentProfile();

    /**
     * Generates a Profile with all the given information
     *
     * @param profileId the profile's unique identifier
     * {@link Profile#getProfileId}
     * @param pseudo the profile's pseudo
     * @param password the profile's password
     * @param status the profile's status
     * @param ipAddress the profile's IP address
     * @param avatar the profile's avatar
     * @param name the profile's last name
     * @param firstName the profile's first name
     * @param age the profile's age
     * @return the new Profile instance
     */
    public Profile createProfile(String profileId, String pseudo, char[] password, STATUS status, String ipAddress, ImageIcon avatar, String name, String firstName, int age) throws FileNotFoundException, ProfilePseudoAlreadyExistException, IOException, ClassNotFoundException, NoIdException;

    /**
     * Start a periodic timer which discovers Profiles on the network every
     * {@link Configuration#PROFILES_DISCOVERY_REFRESH_RATE} millisecond
     */
    public void startProfilesDiscovery();

    /**
     * Try to log the user with the given profileId/password
     *
     * @param profileId the profile's unique identifier
     * {@link Profile#getProfileId}
     * @param password the profile's password
     * @return true if the connection succeeded, false otherwise
     */
    public boolean connection(String profileId, char[] password) throws FileNotFoundException, IOException, ClassNotFoundException;
    /**
     * Send a disconnection message
     */
    public void disconnect();
    /**
     * Get PublicProfiles for the local Profiles
     *
     * @return the local PublicProfiles list or null if there in no local profile
     */
    public ArrayList<PublicProfile> getLocalPublicProfiles() throws ClassNotFoundException, IOException, FileNotFoundException;

    /**
     * Save the current profile on the local computer
     */
    public void saveProfile() throws NoIdException, IOException;

    /**
     * Load a local profile from the local computer
     *
     * @param profileId
     * @return the local profile or null if there is an error with the loading of the profile
     */
    public Profile loadProfile(String profileId) throws IOException, ClassNotFoundException, FileNotFoundException;
    /**
     * Notify the subscribed Managers about the invitation
     * @param invitation the invitation to be notified
     */
    public void notifyInvitation(Invitation invitation);

    /**
     * Create an invitation using the given information
     * @param guest the public profil for the person being invited to play
     * @param color the color chosen by the host
     * @param duration the duration of the game chosen by the host
     * @return the invitation
     */
    public Invitation createInvitation(PublicProfile guest, COLOR color, long duration);

    /**
     * Send the given invitation via the ComManager
     * @param invitation the invitation to send
     */
    public void sendInvitation(Invitation invitation);

    /**
     * Send the answer to the given invitation via the ComManager
     * @param invitation the invitation to send
     * @param answer wether the invitation has been accepted or not
     */
    public void sendInvitationAnswer(Invitation invitation, boolean answer);

    /**
     * Notify the subscribed Managers to add the given PublicProfile
     * @param publicProfile the new publicProfile to notify
     */
    public void notifyAddProfile(PublicProfile publicProfile);
    
    
    /**
     * Notify the subscribed Managers to remove the given PublicProfile
     * @param publicProfile the new publicProfile to notify
     */
    public void notifyProfileDisconnection(PublicProfile publicProfile);

    /**
     * Notify the answer to an invitation to the subscribed Managers
     * @param invitation the invitation whose answer is to be notified
     * @param answer the answer to the invitation which should be notified to the subscribed Managers
     */
    public void notifyInvitAnswer(Invitation invitation, boolean answer);

    /**
     * Exports the current Profile in a local file
     * @param filePath desired path for the local export file
     */
    public void exportProfile(String filePath) throws NoIdException, IOException ;

    /**
     * Imports a Profile from a local file and sets it as the current Profile
     * This method SHOULD NOT BE called if there is already a current Profile
     * @param filePath path to the local file
     */
    public void importProfile(String filePath) throws FileNotFoundException, ProfileIdAlreadyExistException, ProfilePseudoAlreadyExistException, IOException, ClassNotFoundException ;

    public boolean removeProfile(PublicProfile p, char[] pass)  throws FileNotFoundException, IOException, ClassNotFoundException;
}
