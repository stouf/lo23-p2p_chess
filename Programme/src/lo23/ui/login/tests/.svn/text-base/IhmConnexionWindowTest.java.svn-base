/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.ui.login.tests;

import java.util.logging.Level;
import java.util.logging.Logger;
import lo23.data.ApplicationModel;
import lo23.data.PublicProfile;
import lo23.ui.login.IHMList;
import lo23.ui.login.IhmConnexionWindow;
import lo23.ui.login.IhmLoginModel;
import lo23.ui.login.mockManager.CommManagerMock;
import lo23.ui.login.mockManager.GameManagerMock;
import lo23.ui.login.mockManager.ProfileManagerMock;
import org.uispec4j.Trigger;
import org.uispec4j.UISpec4J;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.interception.WindowHandler;
import org.uispec4j.interception.WindowInterceptor;

/**
 *
 * @author marcrossi
 */
public class IhmConnexionWindowTest extends UISpecTestCase {
    
    static IhmConnexionWindow loginFrame;
    static PublicProfile [] profilesList;
            
    static {
       UISpec4J.init();
    }
    
    public static void main(String args[]) {
        
        testInitialState();
        
        // Test de la connexion avec différentes valeurs erronées
//        testConnectionError();

        // Test de la connexion avec différentes valeurs correctes
//        testConnectionCorrect();
        
    }
    
    
    public static void testInitialState() {
        
        //Instantiate DataManager
        ApplicationModel appModel = new ApplicationModel();
        appModel.setComManager(new CommManagerMock(appModel));
        appModel.setGameManager(new GameManagerMock((appModel)));
        appModel.setProfileManager(new ProfileManagerMock(appModel));
        
        //Instantiate IhmLoginModel
        IhmLoginModel ihmLoginModel = new IhmLoginModel(appModel);
       
        try {
            profilesList = ihmLoginModel.getApplicationModel().getPManager().getLocalPublicProfiles().toArray(new PublicProfile[]{});
        } catch (Exception ex) {
            Logger.getLogger(IhmConnexionWindowTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        loginFrame = new IhmConnexionWindow(ihmLoginModel);
    }
    
    public static void testConnectionCorrect() {
        
        System.out.println("\n***** TEST CONNECTION CORRECT *****");
        
        // 1er test
        PublicProfile profile1 = profilesList[0];
        loginFrame.getLoginCombo().setEditable(true); // on rend la combo editable
        loginFrame.getLoginCombo().setSelectedItem(profile1); // On choisit le premier profile, soit "admin"
        loginFrame.getPasswordField().setText("admin");
        onClickConnectBtnWindowHandlerCorrect("Disconnect");
        
        // 2eme test
        testInitialState();
        PublicProfile profile2 = profilesList[1];
        loginFrame.getLoginCombo().setEditable(true); // on rend la combo editable
        loginFrame.getLoginCombo().setSelectedItem(profile2); // On choisit le deuxieme profile, soit "john"
        loginFrame.getPasswordField().setText("john");
        onClickConnectBtnWindowHandlerCorrect("Disconnect");
        
    }
    
    public static void testConnectionError() {
        
        System.out.println("\n***** TEST CONNECTION AVEC ERREURS *****");
        
        // 1er test - John / null
        PublicProfile profile1 = profilesList[1];
        loginFrame.getLoginCombo().setEditable(true); // on rend la combo editable
        loginFrame.getLoginCombo().setSelectedItem(profile1); // On choisit le deuxieme profile, soit "john"
        loginFrame.getPasswordField().setText(null);
        onClickConnectBtnWindowHandlerError("OK");  
        
        // 2eme Test - Admin / john
        PublicProfile profile2 = profilesList[0];
        loginFrame.getLoginCombo().setEditable(true); // on rend la combo editable
        loginFrame.getLoginCombo().setSelectedItem(profile2); // On choisit le premier profile, soit "admin"
        loginFrame.getPasswordField().setText("john");
        onClickConnectBtnWindowHandlerError("OK");
    }
    
    public static void onClickConnectBtnWindowHandlerError (final String backBtnName) {
        
        // permet d'intercepter une nouvelle fenetre
        WindowInterceptor
            .init(new Trigger() {
              public void run() throws Exception {
                // ... trigger something that will cause the first window to be shown ...
                loginFrame.getConnectBtn().doClick();
              }
            })
            .process(new WindowHandler("first dialog") {
                @Override
                public Trigger process(org.uispec4j.Window window) throws Exception {
                    System.out.println("Result --> " + window.getTitle());
                    assertEquals(window.getTitle(), "Login error");
                    return window.getButton(backBtnName).triggerClick();
                }
                })
            .run();
    }
    
    public static void onClickConnectBtnWindowHandlerCorrect (final String backBtnName) {
        
        // permet d'intercepter une nouvelle fenetre
        WindowInterceptor
            .init(new Trigger() {
              public void run() throws Exception {
                // ... trigger something that will cause the first window to be shown ...
                loginFrame.getConnectBtn().doClick();
              }
            })
            .process(new WindowHandler("first dialog") {
                @Override
                public Trigger process(org.uispec4j.Window window) throws Exception {
                    System.out.println("Result --> " + window.getTitle());
                    assertEquals(window.getTitle(), IHMList.TITLE);
                    window.dispose();
                    return Trigger.DO_NOTHING;
                }
                })
            .run();
    }
    
}
