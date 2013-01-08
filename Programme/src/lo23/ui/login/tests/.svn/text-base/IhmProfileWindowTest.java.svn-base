/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.ui.login.tests;

import lo23.data.ApplicationModel;
import lo23.data.PublicProfile;
import lo23.ui.login.IHMList;
import lo23.ui.login.IhmListGames;
import lo23.ui.login.IhmLoginModel;
import lo23.ui.login.IhmProfileWindow;
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
 * @author Gaëtan Grégoire
 */
public class IhmProfileWindowTest extends UISpecTestCase {
    
    static IhmProfileWindowTest loginFrame;
    static PublicProfile [] profilesList;
    static IhmListGames listFrame;
            
    static {
       UISpec4J.init();
    }
    
    public static void main(String args[]) {  
        //Test modify
        testModifyProfile();
        
        //Test read
        testReadProfile();
        
        //Test create
        testCreateProfile();
        
        
    }
    
    private static void testModifyProfile() {
        
        //Instantiate DataManager
        ApplicationModel appModel = new ApplicationModel();
        appModel.setComManager(new CommManagerMock(appModel));
        appModel.setGameManager(new GameManagerMock((appModel)));
        appModel.setProfileManager(new ProfileManagerMock(appModel));
        
        //Instantiate IhmLoginModel
        IhmLoginModel ihmLoginModel = new IhmLoginModel(appModel);
        IHMList listplayers = new IHMList(ihmLoginModel);
        listFrame = new IhmListGames(ihmLoginModel);

        new IhmProfileWindow(ihmLoginModel,IhmProfileWindow.MODIFY,null).setVisible(true);
    }
    
    private static void testReadProfile() {
        
        //Instantiate DataManager
        ApplicationModel appModel = new ApplicationModel();
        appModel.setComManager(new CommManagerMock(appModel));
        appModel.setGameManager(new GameManagerMock((appModel)));
        appModel.setProfileManager(new ProfileManagerMock(appModel));
        
        //Instantiate IhmLoginModel
        IhmLoginModel ihmLoginModel = new IhmLoginModel(appModel);
        //new IhmProfileWindow(ihmLoginModel,IhmProfileWindow.READ,ihmLoginModel.getApplicationModel().g).setVisible(true);
    }
        
    private static void testCreateProfile() {
        
        //Instantiate DataManager
        ApplicationModel appModel = new ApplicationModel();
        appModel.setComManager(new CommManagerMock(appModel));
        appModel.setGameManager(new GameManagerMock((appModel)));
        appModel.setProfileManager(new ProfileManagerMock(appModel));
        
        //Instantiate IhmLoginModel
        IhmLoginModel ihmLoginModel = new IhmLoginModel(appModel);
        new IhmProfileWindow(ihmLoginModel,IhmProfileWindow.CREATE,null).setVisible(true);
    }
    
    public static void onClickmanageProfileBtnHandlerCorrect (final String backBtnName) {
        //TODO....
        // permet d'intercepter une nouvelle fenetre
        WindowInterceptor
            .init(new Trigger() {
              public void run() throws Exception {
                // ... trigger something that will cause the first window to be shown ...
                
                //loginFrame.getConnectBtn().doClick();
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
