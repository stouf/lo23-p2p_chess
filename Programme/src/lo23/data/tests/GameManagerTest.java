/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.data.tests;

import junit.framework.TestCase;
import lo23.data.Game;
import lo23.data.Player;
import lo23.data.PublicProfile;
import lo23.utils.Enums;
import lo23.utils.Enums.COLOR;

/**
 *
 * @author ksadorf
 */
public class GameManagerTest extends TestCase {

    private Game game;


    public GameManagerTest() {
        super();

        initializeAttributes();
    }

    public GameManagerTest(String str) {
        super(str);

        initializeAttributes();
    }

    private void initializeAttributes() {
        Player localPlayer = new Player(COLOR.WHITE, 600, new PublicProfile("001", "local", Enums.STATUS.INGAME, "192.168.1.10", null, "Donin", "Stephan", 23, 5, 9, 1));
        Player remotePlayer = new Player(COLOR.BLACK, 600, new PublicProfile("002", "remote", Enums.STATUS.INGAME, "192.168.1.11", null, "Hamidou", "Karim", 23, 5, 9, 1));
        game = new Game(localPlayer, remotePlayer);
    }

    public void simpleTest() {
        System.out.println("Erreur ?");
        assertTrue(false);
    }


    static public void main(String[] args) throws Throwable {
        (new GameManagerTest("rzedcz")).runTest();
    }
}
