/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.ui.login.tests;

/**
 *
 * @author marcrossi
 */
public class ihmLoginAllTests {
    
    public static void main(String args[]) {
        
        System.out.println("================= TEST de l'IHM LOGIN ====================\n\n");
        
        System.out.println("----------------- TEST de IhmConnexionWindow -------------\n");
        IhmConnexionWindowTest.main(args);
        
        System.out.println("\n----------------- TEST de IhmListGames ----------------\n");
//        IhmListGamesTest.main(args);
        
        System.out.println("\n----------------- TEST de IhmListPlayers ----------------\n");
//        IhmListPlayersTest.main(args);
        
        System.out.println("\n----------------- TEST de IhmProfileWindow ----------------\n");
        IhmProfileWindowTest.main(args);
        
    }
    
    
}
