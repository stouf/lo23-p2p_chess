/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.data.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lo23.data.ApplicationModel;
import lo23.data.Constant;
import lo23.data.Game;
import lo23.data.NewInvitation;
import lo23.data.Position;
import lo23.data.Profile;
import lo23.data.exceptions.NoIdException;
import lo23.data.exceptions.ProfilePseudoAlreadyExistException;
import lo23.data.exceptions.WrongInvitation;
import lo23.data.managers.GameManager;
import lo23.data.managers.ProfileManager;
import lo23.data.pieces.GamePiece;
import lo23.utils.Enums;
import lo23.utils.Enums.PLAYER_RESULT;

/**
 *
 * @author Romain
 */
public class TestCoupPossible {

    public static void main(String[] args) {
        ApplicationModel app;
        Profile pGuest;
        NewInvitation inv;
        Game gm;

        app = new ApplicationModel();
        app.setGameManager(new GameManager(app));
        app.setProfileManager(new ProfileManager(app));

        char[] fakePassword = {};
        String profileId = "MIchel";
        Profile p;
        try {
            try {
                try {
                    p = app.getPManager().createProfile(profileId, "toto", fakePassword, Enums.STATUS.CONNECTED, "", null, "michel", "titi", 22);
                } catch (ProfilePseudoAlreadyExistException ex) {
                    Logger.getLogger(TestCoupPossible.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NoIdException ex) {
                Logger.getLogger(TestCoupPossible.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (app.getPManager().connection(profileId, fakePassword)) {

                pGuest = new Profile("idprofile", "host", fakePassword, Enums.STATUS.INGAME, "", null, "", "", 21);
                Profile phost = new Profile("idple", "host", fakePassword, Enums.STATUS.INGAME, "", null, "", "", 21);
                inv = new NewInvitation(Enums.COLOR.WHITE, 300, app.getPManager().getCurrentProfile().getPublicProfile(), pGuest.getPublicProfile());
                try {
                    gm = app.getGManager().createGame(inv);
                    long gid = gm.getGameId();

                    gm.buildPieces();
                    // gm.dumpBoard();


                    //maintenant, testons les coups possibles !


                    //GamePiece roi = gm.getPieceAtXY(4, 0);
                    //roi.movePiece(new Position(5, 3));
                    //System.out.println(piece.getOwner().getColor());
                    //GamePiece fou = gm.getPieceAtXY(5, 7);
                    //fou.movePiece(new Position(5, 5));
                    GamePiece piece = gm.getPieceAtXY(7, 7);
                    //piece.movePiece(new Position(5, 4));

//System.out.println(roi.isOncheck());


                    List<Position> possibleMoves = piece.getPossibleMovesWithCheck();
//        possibleMoves = piece.removeCheckingMove(possibleMoves);

                    TestCoupPossible test = new TestCoupPossible();
                    test.dumpBoardAndList(possibleMoves, gm);

                    Constant c = app.getGManager().createConstant(Enums.CONSTANT_TYPE.SURRENDER);
                    app.getGManager().saveConstant(c);
                    try {
                        PLAYER_RESULT reponse = gm.isWinner("idprofile");
                        System.out.println(reponse + " " + PLAYER_RESULT.WIN + " " + PLAYER_RESULT.LOST);
                    } catch (Exception ex) {
                        Logger.getLogger(GameManagerTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    app.getGManager().save();

                    ArrayList<Game> list = app.getGManager().getListAllGames();
                    for (Game g : list) {
                        g.dumpBoard();
                    }
                    System.out.println(list.size());

                } catch (NoIdException expt) {
                    System.out.println(expt.getMessage());
                    System.out.println(expt.getStackTrace());
                } catch (WrongInvitation expt) {
                    System.out.println(expt.getMessage());
                    System.out.println(expt.getStackTrace());
                }
            } else {
                System.out.println("Probleme lors de la connection.");
            }
        } catch (lo23.data.exceptions.FileNotFoundException ex) {
            Logger.getLogger(GameManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GameManagerTest.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //méthode temporaire pour afficher le board et en mettant des croix quand la position est dans la liste
    // et je sais, l'algo est bien bien moche mais c'est juste pour les tests !
    public void dumpBoardAndList(List<Position> list, Game gm) {

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                GamePiece p = gm.getPieceAtXY(x, y);
                if (p != null) {
                    boolean test = false;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getX() == x && list.get(i).getY() == y) {
                            test = true;
                        }
                    }
                    if (test) {
                        System.out.print("x");
                    } else {
                        System.out.print(p.getClass().getSimpleName().charAt(0));
                    }
                } else {
                    boolean test = false;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getX() == x && list.get(i).getY() == y) {
                            test = true;
                        }
                    }
                    if (test) {
                        System.out.print("x");
                    } else {
                        System.out.print("-");
                    }
                }

            }

            System.out.println();

        }
    }
}
