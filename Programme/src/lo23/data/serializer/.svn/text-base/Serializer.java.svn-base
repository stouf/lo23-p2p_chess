package lo23.data.serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import lo23.data.Game;
import lo23.data.Profile;
import lo23.data.exceptions.FileNotFoundException;
import lo23.data.exceptions.NoIdException;

/**
 * This is the util class used for every serialization This class has to contain
 * static methods and attributes only
 */
public class Serializer {

    /**
     * Get all the Profile IDs stored in {@link Constants.PROFILES_PATH} and
     * create any necessary but nonexistent parent directories.
     *
     * @return the profile IDs
     */
    static public ArrayList<String> getProfileIds() {
        // Creates the folder if it doesn't exist
        File folder = new File(Constants.PROFILES_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        ArrayList<String> profileIds = new ArrayList<String>();
        File[] listOfFiles = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File folder, String name) {
                return name.toLowerCase().endsWith(".profile");
            }
        });

        if (listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    profileIds.add(listOfFiles[i].getName().replaceAll(".profile", ""));
                }
            }
        }

        return profileIds;
    }

    /**
     * This method serializes a given Profile object to a given path
     *
     * @param profile The object to serialize
     * @param path The path where the profile is serialized
     * @param profileId The profile ID. Set to "" to permit exportation of
     * profile with personnalised file name
     *
     * @throws NoIdException This exception is thrown if profile argument
     * doesn't have a correct profileId
     */
    static public void saveProfile(Profile profile, String path, String profileId) throws NoIdException, IOException {
        // Checks the profileId attribute validity
        if (profile.getProfileId() == null || profile.getProfileId().equals("")) {
            throw new NoIdException("The object you're trying to serialize handle a null or empty profileId attribute.");
        }

        ObjectOutputStream out;
        out = new ObjectOutputStream(new FileOutputStream(path + profileId + Constants.PROFILE_SUFFIXE));
        out.writeObject(profile);
        out.flush();
        out.close();
    }

    static public void saveProfile(Profile profile, String path) throws NoIdException, IOException {
        saveProfile(profile, path, profile.getProfileId());
    }

    /**
     * This method serializes a given Profile object to the correct path
     *
     * @param profile The object to serialize
     *
     * @throws NoIdException This exception is thrown if profile argument
     * doesn't have a correct profileId
     */
    static public void saveProfile(Profile profile) throws NoIdException, IOException {
        saveProfile(profile, Constants.PROFILES_PATH);
    }

    /**
     * This method tries to read a profile whom id and file paths are given as
     * paramater
     *
     * @param profileId The profileId for the expected profile
     * @param path The path from where the profile is read
     *
     * @return Either a Profil object, either a null value if something went
     * wrong (IOException or file not found)
     *
     * @throws FileNotFoundException This exception is thrown when this method
     * can't have access to an expected file
     */
    static public Profile readProfile2(String filePath) throws FileNotFoundException, IOException, ClassNotFoundException {
        // Creates the folder if it doesn't exist
        File folder = new File(Constants.PROFILES_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        
        // Checks if the profileId associated file exists
        File profileFile = new File(filePath);
        if (profileFile.exists()) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(profileFile));
            Profile profile = (Profile) in.readObject();
            in.close();

            return profile;

        } else {
            throw new FileNotFoundException("Couldn't find the file " + filePath);
        }
    }

    /**
     * This method tries to read a profile whom id is given as a paramater
     *
     * @param profileId The profileId for the expected profile
     *
     * @return Either a Profil object, either a null value if something went
     * wrong (IOException or file not found)
     *
     * @throws FileNotFoundException This exception is thrown when this method
     * can't have access to an expected file
     */
    static public Profile readProfile(String profileId) throws FileNotFoundException, IOException, ClassNotFoundException {
        return readProfile2(Constants.PROFILES_PATH + profileId + Constants.PROFILE_SUFFIXE);
    }

    /**
     * This method serializes a given Game object to the correct path
     *
     * @param game The object to serialize
     *
     * @throws NoIdException This exception is thrown if game argument doesn't
     * have a correct gameId
     */
    static public void saveGame(Game game) throws NoIdException, IOException {
        // Checks the profileId attribute validity
        if (game.getGameId() < 0 || game.getGameId() > (new Date()).getTime()) {
            throw new NoIdException("The object you're trying to serialize handle an invalid gameId attribute.");
        }

        // Creates the folder if it doesn't exist
        File folder = new File(Constants.GAMES_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        ObjectOutputStream out;
        out = new ObjectOutputStream(new FileOutputStream(Constants.GAMES_PATH + game.getGameId() + Constants.GAME_SUFFIXE));
        out.writeObject(game);
        out.close();
    }

    /**
     * This method tries to read a game whom id is given as a paramater
     *
     * @param gameId The gameId for the expected profile
     *
     * @return Either a Game object, either a null value if something went wrong
     * (IOException or class not found)
     *
     * @throws FileNotFoundException This exception is thrown when this method
     * can't have access to an expected file
     */
    static public Game readGame(long gameId) throws FileNotFoundException, IOException, ClassNotFoundException {
        // Checks if the gameId associated file exists

        File gameFile = new File(Constants.GAMES_PATH + gameId + Constants.GAME_SUFFIXE);

        File folder = new File(Constants.GAMES_PATH);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        if (gameFile.exists()) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(gameFile));
            Game game = (Game) in.readObject();
            in.close();

            return game;

        } else {
            throw new FileNotFoundException("Couldn't find the file " + Constants.GAMES_PATH + gameId + Constants.GAME_SUFFIXE);
        }
    }
}
