/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.ui.grid;

//import ui.grid.TMP_GameManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import lo23.data.ApplicationModel;
import lo23.data.Game;
import lo23.data.NewInvitation;
import lo23.data.Profile;
import lo23.data.exceptions.ProfilePseudoAlreadyExistException;
import lo23.data.exceptions.WrongInvitation;
import lo23.data.managers.GameManager;
import lo23.data.managers.ProfileManager;
import lo23.data.tests.GameManagerTest;
import lo23.utils.Enums;

/**
 * Grid main local, without connection, just for local use
 * @author Karim
 */
public class GridMain
{

    static ApplicationModel myModel;

    private static ApplicationModel getModel()
    {
        return myModel;
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {

            public void run()
            {
                /*
                 * Important : All of yours interactions with managers will be develop like that :
                 *
                 * You have to create a constructor which takes parameters ApplicationModel
                 *
                 * And all your interactions will work like that :
                 *
                 * myModel.getManageYouWant().callFunctionYouWant()
                 *
                 * You can find an example in ChatPanel2 or GamePanel
                 *
                 */


                //du code tout moche de data pour faire les tests !
                //ApplicationModel myModel;
                Profile pGuest;
                NewInvitation inv;
                Game gm;

                myModel = new ApplicationModel();
                myModel.setGameManager(new GameManager(myModel));
                myModel.setProfileManager(new ProfileManager(myModel));
         //       myModel.setComManager(new ComManager(myModel));

                char[] fakePassword =
                {
                };
                String profileId = "M23hel";
                Profile p;
                try
                {
                    try {
                        p = myModel.getPManager().createProfile(profileId, "toto2", fakePassword, Enums.STATUS.CONNECTED, "", null, "michel", "titi", 22);
                    } catch (ProfilePseudoAlreadyExistException ex) {
                        Logger.getLogger(GridMain.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (myModel.getPManager().connection(profileId, fakePassword))
                    {

                        pGuest = new Profile("idprofile", "host", fakePassword, Enums.STATUS.INGAME, "", null, "", "", 21);
                        Profile phost = new Profile("idple", "host", fakePassword, Enums.STATUS.INGAME, "", null, "", "", 21);
                        inv = new NewInvitation(Enums.COLOR.WHITE, 300, myModel.getPManager().getCurrentProfile().getPublicProfile(), pGuest.getPublicProfile());
                        try
                        {
                            gm = myModel.getGManager().createGame(inv);
                            long gid = gm.getGameId();

                           gm.dumpBoard();
                           
                            //             myModel.setGameManager(new TMP_GameManager(myModel));
                            //  myModel.setProfileManager(new TMP_ProfileManager(myModel));
                            //TODO : comment a cause d'un problème de compil

                            //myModel.setComManager(new TMP_ComManager(null, myModel))
                            MainWindow fenetre = new MainWindow(getModel(), gm);
                            fenetre.setVisible(true);



                        } catch (WrongInvitation expt)
                        {
                            System.out.println(expt.getMessage());
                            System.out.println(expt.getStackTrace());
                        }
                    }
                    else
                    {
                        System.out.println("Probleme lors de la connection.");
                    }
                } catch (lo23.data.exceptions.NoIdException expt)
                {
                    System.out.println(expt.getMessage());
                    System.out.println(expt.getStackTrace());
                } catch (IOException ex)
                {
                    Logger.getLogger(GameManagerTest.class.getName()).log(Level.SEVERE, null, ex);

                } catch (ClassNotFoundException ex)
                {
                    Logger.getLogger(GameManagerTest.class.getName()).log(Level.SEVERE, null, ex);
                } catch (lo23.data.exceptions.FileNotFoundException ex)
                {
                    Logger.getLogger(GameManagerTest.class.getName()).log(Level.SEVERE, null, ex);
                }



            }
        });
    }
}
