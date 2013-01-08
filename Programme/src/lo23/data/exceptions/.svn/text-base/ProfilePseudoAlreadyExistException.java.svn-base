package lo23.data.exceptions;

import lo23.data.Profile;

/**
 * @author khamidou
 */
public class ProfilePseudoAlreadyExistException extends Exception
{
    private Profile profile = null;

    /**
     * Default constructor, with no specific message
     */
    public ProfilePseudoAlreadyExistException()
    {
        super();
    }

    /**
     * Constructor, with a given message
     * @param message The message contained into the thrown exception
     */
    public ProfilePseudoAlreadyExistException(String message)
    {
        super(message);
    }

    /**
     * Constructor, with a message and also a reference to the concerned Profile object
     * @param message The message contained into the thrown exception
     * @param profile The concerned profile
     */
    public ProfilePseudoAlreadyExistException(String message, Profile profile)
    {
        super(message);
        this.profile = profile;
    }

    /**
     * This method returns the concerned Profile object
     * @return the profile with the existing pseudo or null if not specified
     */
    public Profile getProfileWithExistingPseudo() {
        return profile;
    }
}
