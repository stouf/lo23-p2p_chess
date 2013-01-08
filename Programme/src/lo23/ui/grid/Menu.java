
package lo23.ui.grid;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import lo23.data.managers.Manager;
import lo23.ui.login.IhmLoginModel;
import lo23.utils.ResourceManager;
import lo23.utils.Enums;

/**
 *Create the menu bar with some options
 * @author guigou
 */
public class Menu {

    public static Launch_Sound during_party;    //the game music
    boolean is_full_screen;      //control if the screen is in full screen
    static boolean noise_on;       //sound effect

    public Menu(MainWindow mw) {
        //launch sound
        during_party = new Launch_Sound("chess.wav");   //game music
        during_party.play();                            //play the music

        is_full_screen = false;
        noise_on = true;



        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu options = new JMenu("Options");
        JMenu son = new JMenu("Sound");
        JMenu other = new JMenu("?");
        JMenuItem full_screen = new JMenuItem("Full screen");
        JMenuItem rules = new JMenuItem("Rules of chess");
        JMenuItem about = new JMenuItem("About");
        JRadioButtonMenuItem stop_music = new JRadioButtonMenuItem("Stop music");
        JRadioButtonMenuItem play_music = new JRadioButtonMenuItem("Play music");
        JRadioButtonMenuItem stop_noise = new JRadioButtonMenuItem("Stop sound effects");
        JRadioButtonMenuItem play_noise = new JRadioButtonMenuItem("Make some noises");
  

        
        JMenuItem close = new JMenuItem("Quit");
        
        
        //file
       
        file.add(close);
        //options
        options.add(rules);
        options.addSeparator();
        options.add(full_screen);


        //Radio buttons sound
        ButtonGroup bg = new ButtonGroup();
        ButtonGroup bg2 = new ButtonGroup();
        bg.add(play_music);
        bg.add(stop_music);
        play_music.setSelected(true);
        bg2.add(play_noise);
        bg2.add(stop_noise);
        play_noise.setSelected(true);

        son.add(play_music);
        son.add(stop_music);
        son.addSeparator();
        son.add(play_noise);
        son.add(stop_noise);

        //other
        other.add(about);
        
        //accelerator to simplify the use
        file.setMnemonic('F');
        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
        options.setMnemonic('O');
        full_screen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));
        son.setMnemonic('S');


        //add in the Menu
        menu.add(file);
        menu.add(options);
        menu.add(son);
        menu.add(other);
        mw.setJMenuBar(menu);



        //Listeners
        Stop_music stopm = new Stop_music();
        stop_music.addActionListener(stopm);

        Play_music playm = new Play_music();
        play_music.addActionListener(playm);

        Stop_noise stopn = new Stop_noise();
        stop_noise.addActionListener(stopn);

        Play_noise playn = new Play_noise();
        play_noise.addActionListener(playn);



        Quit quit = new Quit(mw);
        close.addActionListener(quit);

        Full screen = new Full(mw);
        full_screen.addActionListener(screen);


        Rules rul = new Rules();
        rules.addActionListener(rul);

        About ab = new About();
        about.addActionListener(ab);


    }
    /*
     * Quit the menu
     */
    private class Quit implements ActionListener {

        MainWindow mw;

        public Quit(MainWindow mw) {
            this.mw = mw;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
  JOptionPane d = new JOptionPane();
        String[] choice = {"Yes", "No"};
        int retour = d.showOptionDialog(mw,
        "Do you really want to quit the game ?",
        "Exit",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        new ImageIcon(ResourceManager.getInstance().getResource("chess_icon.png")),
        choice,
        choice[1]);


        if(retour == 0){ // oui je veux enregistrer pour reprendre


            try {
                    if(Menu.during_party != null){
                        Menu.during_party.pause();
                        Menu.during_party.interrupt();
                    }
                    Menu.noise_on = false;

                    mw.setVisible(false);
                    if(!mw.isReviewGame){
                        Enums.PLAYER_RESULT res = mw.myModel.getGManager().getCurrentGame().isWinner(mw.myModel.getPManager().getCurrentProfile().getProfileId());
                        mw.myModel.getGManager().sendGameEnded();
                        mw.myModel.getGManager().notifyGameEnded(res);

                    }
                    else{
                        mw.myModel.getPManager().getCurrentProfile().setStatus(Enums.STATUS.CONNECTED);
                        ((Manager)mw.myModel.getGManager()).publish(IhmLoginModel.GAME_ENDED, null);
                    }
                    mw.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        else{ // ici on abandonne complétement
 
        }

        // on envoie à l'autre player qu'on abandonne




      //  Constant cst = myModel.getGManager().createConstant(Enums.CONSTANT_TYPE.SURRENDER);
        //new Constant(Enums.CONSTANT_TYPE.SURRENDER, myModel.getGManager().getCurrentGame().getRemotePlayer(),myModel.getGManager().getCurrentGame().getLocalPlayer());
      //  myModel.getGManager().sendConstant(cst);

     
        }
    }
/*
 * Stop the music
 */
    private class Stop_music implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            during_party.pause();
        }
    }
/*
 * Play the music
 */
    private class Play_music implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            during_party.play();
        }
    }
/*
 * Play the noise 
 */
    private class Play_noise implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            set_noise_on(true);
        }
    }
/*
 * Stop the noise
 */
    private class Stop_noise implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            set_noise_on(false);
        }
    }
/*
 * Full screen mode, start or stop
 */
    private class Full implements ActionListener {

        MainWindow mw;

        public Full(MainWindow mw) {
            super();
            this.mw = mw;
        }

        public void actionPerformed(ActionEvent e) {
            if (is_full_screen == false) {
                Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                mw.setLocation(0, 0);
                mw.setSize(tailleEcran);
                is_full_screen = true;
            } else {
                mw.setSize(GridConstants.WINDOW_WIDTH, GridConstants.WINDOW_HEIGHT); //On donne une taille à notre fenêtre
                mw.setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
                is_full_screen = false;
            }
        }
    }
/*
 * Display the chess rules
 */
    private class Rules extends JFrame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(this,
                    "Chess is played on a square board of eight rows (called ranks and denoted with numbers 1 to 8) and eight columns (called files and denoted with letters a to h) of squares."
                    + "\nThe colors of the sixty-four squares alternate and are referred to as \"light squares\" and \"dark squares\". The chessboard is placed with a light square at the right-hand"
                    + "\nend of the rank nearest to each player, and the pieces are set out as shown in the diagram, with each queen on its own color. The pieces are divided, by convention,"
                    + "\ninto white and black sets. The players are referred to as \"White\" and \"Black\", and each begins the game with sixteen pieces of the specified color. "
                    + "\nThese consist of one king, one queen, two rooks, two bishops, two knights, and eight pawns."
                    + "\n\nMovement\n"
                    + "\nWhite always moves first. After the initial move, the players alternately move one piece at a time (with the exception of castling, when two pieces are moved)."
                    + "\nPieces are moved to either an unoccupied square or one occupied by an opponent's piece, which is captured and removed from play. With the sole exception of en passant,"
                    + "\nall pieces capture opponent's pieces by moving to the square that the opponent's piece occupies."
                    + "\nA player may not make any move that would put or leave his king under attack. If the player to move has no legal moves, the game is over; "
                    + "\nit is either a checkmate (a loss for the player with no legal moves)"
                    + "\n—if the king is under attack—or a stalemate (a draw)—if the king is not."
                    + "\nEach chess piece has its own style of moving. In the diagrams, the dots mark the squares where the piece can move if no other pieces "
                    + "\n(including one's own piece) are on the squares between the piece's initial position and its destination."
                    + "\nThe king moves one square in any direction. The king has also a special move which is called castling and involves also moving a rook."
                    + "\nThe rook can move any number of squares along any rank or file, but may not leap over other pieces. Along with the king, the rook is involved during the king's castling move."
                    + "\n The bishop can move any number of squares diagonally, but may not leap over other pieces."
                    + "\nThe queen combines the power of the rook and bishop and can move any number of squares along rank, file, or diagonal, but it may not leap over other pieces."
                    + "\nThe knight moves to any of the closest squares that are not on the same rank, file, or diagonal, thus the move forms an \"L\""
                    + "\n-shape: two squares vertically and one square horizontally, or two squares horizontally and one square vertically."
                    + "\nThe knight is the only piece that can leap over other pieces. The pawn may move forward to the unoccupied square immediately in front of it on the same file;"
                    + "\nor on its first move it may advance two squares along the same file provided both squares are unoccupied; "
                    + "\nor it may move to a square occupied by an opponent's piece which is diagonally in front of it on an adjacent file, capturing that piece."
                    + "\nThe pawn has two special moves: the en passant capture and pawn promotion."
                    + "\n\n\nFor more informations, please follow this link : http://en.wikipedia.org/wiki/Rules_of_chess"
                    + "\n   \n", "Rules of Chess",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(ResourceManager.getInstance().getResource("rules.png")));
        }
    }

    /*
     * Display the about
     */
    private class About extends JFrame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(this,
                    "Game developed by UTC Students"
                    + "\n" + "        LO23 Project - 2012", "About",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(ResourceManager.getInstance().getResource("logo_utc.png")));
        }
    }

    /*
     * Change the style windows
     */
    private static void setWindowsLook() {
        LookAndFeel lf = UIManager.getLookAndFeel();

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    /*
     * get the noise
     */
    public static boolean get_noise_on() {
        return noise_on;
    }
    /*
     * set the noise
     */
    public void set_noise_on(boolean t) {
        noise_on = t;

    }
}