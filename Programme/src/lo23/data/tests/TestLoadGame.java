/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lo23.data.tests;

import java.io.FileNotFoundException;
import lo23.data.Game;

/**
 *
 * @author khamidou
 */
public class TestLoadGame {
    public static void main(String args[]) throws FileNotFoundException {
        Game g = new Game(null, null);        
        g.loadBoard("board.txt");
        g.dumpBoard();
    }

}
