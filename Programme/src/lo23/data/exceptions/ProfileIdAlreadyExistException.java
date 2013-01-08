package lo23.data.exceptions;

import lo23.data.Profile;

/**
 * This exception is thrown when a profile whom identifier already exists is about to be registered into the application
 * @author khamidou
 */
public class ProfileIdAlreadyExistException extends Exception
{
    private Profile profile = null;

    /**
     * Default constructor, with no specific message
     */
    public ProfileIdAlreadyExistException()
    {
        super();
    }

    /**
     * Constructor, with a given message
     * @param message The message contained into the thrown exception
     */
    public ProfileIdAlreadyExistException(String message)
    {
        super(message);
    }

    /**
     * Constructor, with a message and also a reference to the concerned Profile object
     * @param message The message contained into the thrown exception
     * @param profile The concerned profile
     */
    public ProfileIdAlreadyExistException(String message, Profile profile)
    {
        super(message);
        this.profile = profile;
    }

    /**
     * This method returns the concerned Profile object
     * @return the profile with the existing profileID or null if not specified
     */
    public Profile getProfileWithExistingID() {
        return profile;
    }
}
