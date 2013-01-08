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
 * @author marcrossi
 */
public class IhmListGamesTest extends UISpecTestCase {
    
    static IhmListGames listFrame;
    static IhmLoginModel ihmLoginModel;
    static GameManagerMock gManagerMock;
            
    static {
       UISpec4J.init();
    }
    
    public static void main(String args[]) {
        
        testInitialState();
        
        // Test des parties terminées
        testListEndGames();
        
        // Test des parties "en cours"
        testListStopGames();
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
    
    public static void testListEndGames() {
        
        int i=0;
        long idGame;
        System.out.println("\n***** TEST LIST END GAME *****");
        
        // Récupération des listes de gameID
        ArrayList<Long> ids = ihmLoginModel.idEndGames;
        // Récupération des boutons
        ArrayList<JButton> listReviewGameBtn = ihmLoginModel.getListReviewGameBtn();
        
        for (JButton btn : listReviewGameBtn) {
            idGame = ids.get(i);
            System.out.println("id du jeu = " + idGame + " / id btn = " + btn.getClientProperty("id"));
            assertEquals(idGame, btn.getClientProperty("id"));
            i++;
        }
    }
    
    public static void testListStopGames() {
        
        int i=0;
        long idGame;
        System.out.println("\n***** TEST LIST STOP GAME *****");
        
        // Récupération des listes de gameID
        ArrayList<Long> ids = ihmLoginModel.idStartGames;
        // Récupération des boutons
        ArrayList<JButton> listContinueGameBtn = ihmLoginModel.getListContinueGameBtn();
        
        for (JButton btn : listContinueGameBtn) {
            idGame = ids.get(i);
            System.out.println("id du jeu = " + idGame + " / id btn = " + btn.getClientProperty("id"));
            assertEquals(idGame, btn.getClientProperty("id"));
            i++;
        }
    }
    
    
}
