/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.ui.login.tests;

import java.util.ArrayList;
import javax.swing.JButton;
import lo23.data.ApplicationModel;
import lo23.ui.login.IHMList;
import lo23.ui.login.IhmListGames;
import lo23.ui.login.IhmLoginModel;
import lo23.ui.login.mockManager.CommManagerMock;
import lo23.ui.login.mockManager.GameManagerMock;
import lo23.ui.login.mockManager.ProfileManagerMock;
import org.uispec4j.UISpec4J;
import org.uispec4j.UISpecTestCase;

/**
 *
 * @author LAHLOU Med
 */

public class IhmListPlayersTest extends UISpecTestCase {
    
    static IhmListGames listFrame;
    static IhmLoginModel ihmLoginModel;
    static GameManagerMock gManagerMock;
            
    static {
       UISpec4J.init();
    }
    
    public static void main(String args[]) {
        
        testInitialState();
        
        // Test des joueurs connectés 
        testListPlayersConnected();
        
    }
    
    
    public static void testInitialState() {
        
        //Instantiate DataManager
        ApplicationModel appModel = new ApplicationModel();
        appModel.setComManager(new CommManagerMock(appModel));
        gManagerMock = new GameManagerMock(appModel);
        appModel.setGameManager(gManagerMock);
        appModel.setProfileManager(new ProfileManagerMock(appModel));
        //Instantiate IhmLoginModel
        ihmLoginModel = new IhmLoginModel(appModel);
        IHMList listplayers = new IHMList(ihmLoginModel);

        listFrame = new IhmListGames(ihmLoginModel);
    }
    
    

    private static void testListPlayersConnected() {
        int i=0;
        long idPlayer;
        System.out.println("\n***** TEST LIST PLAYERS CONNECTED *****");
        
        // Récupération des listes de gameID
        ArrayList<Long> ids = ihmLoginModel.idPlayersConnected;
        // Récupération des boutons
        ArrayList<JButton> listLaunchGameBtn = ihmLoginModel.getListLaunchGameBtn();
        
        for (JButton btn : listLaunchGameBtn) {
            idPlayer = ids.get(i);
            System.out.println("id du joueurs = " + idPlayer + " / id btn = " + btn.getClientProperty("id"));
            assertEquals(idPlayer, btn.getClientProperty("id"));
            i++;
        }
     
    }
    
}

