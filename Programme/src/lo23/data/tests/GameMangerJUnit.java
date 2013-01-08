/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lo23.data.tests;

import java.util.logging.Level;
import java.util.logging.Logger;
import lo23.data.ApplicationModel;
import lo23.data.Game;
import lo23.data.NewInvitation;
import lo23.data.Player;
import lo23.data.Profile;
import lo23.data.exceptions.WrongInvitation;
import lo23.data.managers.GameManager;
import lo23.data.managers.ProfileManager;
import lo23.utils.Enums;
import lo23.utils.Enums.COLOR;
import lo23.utils.Enums.STATUS;

/**
 *
 * @author khamidou
 */
public class GameMangerJUnit {

    GameManager gManager;
    ApplicationModel app;
    Player p1, p2;
    Profile pHost, pGuest;
    NewInvitation inv;
    Game gm;

    public GameMangerJUnit() {        
    }

    public void setUp() {
        app = new ApplicationModel();
        app.setGameManager(new GameManager(app));
        app.setProfileManager(new ProfileManager(app));
        p1 = new Player(COLOR.WHITE, 0, null);
        p2 = new Player(COLOR.BLACK, 0, null);

        char[] fakePassword = {};

        Profile pHost = new Profile("", "host", fakePassword, STATUS.INGAME, "", null, "", "", 21);
        Profile pGuest = new Profile("", "host", fakePassword, STATUS.INGAME, "", null, "", "", 21);
        inv = new NewInvitation(Enums.COLOR.WHITE,300, pHost.getPublicProfile(), pGuest.getPublicProfile());
        try {
            gm = app.getGManager().createGame(inv);
        } catch (WrongInvitation ex) {
            Logger.getLogger(GameMangerJUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testSave() {

    }
    
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

}