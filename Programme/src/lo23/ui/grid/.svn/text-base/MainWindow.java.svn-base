/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.ui.grid;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import lo23.data.ApplicationModel;
import lo23.data.Game;
import lo23.data.Player;
import lo23.data.managers.Manager;
import lo23.ui.grid.PlayerPanel;
import lo23.ui.login.IhmLoginModel;
import lo23.utils.Enums;
import lo23.utils.Enums.COLOR;
import lo23.utils.ResourceManager;


/**
 * Create the main window with all the elements
 * @author Karim
 */

public class MainWindow extends JFrame implements ActionListener {

    ApplicationModel myModel;
    Game game;
    PiecesBox remotePlayerLostPieces;
    PiecesBox localPlayerLostPieces;
    public static JLabel chess_king;                //picture when there is chess
    public static JLabel chess_king_crown;          //picture when a person win
    boolean isReviewGame;
    public static final java.awt.Color fond = new java.awt.Color(153, 51, 0); // background color
    //new javax.swing.ImageIcon(getClass().getResource("/lo23/ui/resources/gamer1.png")))
    private Menu menu;

    public MainWindow(ApplicationModel m) {
        super();






        myModel = m;

        build();//On initialise notre fenêtre

    }

    public MainWindow(ApplicationModel m, boolean reviewGame) {
        super();
        isReviewGame = reviewGame;
        myModel = m;
        game = m.getGManager().getCurrentGame();
        build();
    }

    public MainWindow(ApplicationModel m, Game gm) {
        super();

        //Launch the Sound
//       during_party = new Launch_Sound("chess.wav");
//       during_party.play();


        myModel = m;
        game = gm;

        build();//On initialise notre fenêtre

    }

    private void build() {
        setTitle("Chess P2P"); //On donne un titre à l'application

        setSize(GridConstants.WINDOW_WIDTH, GridConstants.WINDOW_HEIGHT); //On donne une taille à notre fenêtre
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix

        setContentPane(buildContentPanel());


        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                   try {
                    if(menu.during_party != null){
                        menu.during_party.pause();
                        menu.during_party.interrupt();
                    }
                    menu.set_noise_on(false);
                    setVisible(false);
                    if(!isReviewGame){
                        Enums.PLAYER_RESULT res = myModel.getGManager().getCurrentGame().isWinner(myModel.getPManager().getCurrentProfile().getProfileId());
                        myModel.getGManager().sendGameEnded();
                        myModel.getGManager().notifyGameEnded(res);
                    }
                    else{
                        myModel.getPManager().getCurrentProfile().setStatus(Enums.STATUS.CONNECTED);
                        ((Manager)myModel.getGManager()).publish(IhmLoginModel.GAME_ENDED, null);
                    }
                    dispose();
                } catch (Exception ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        this.menu = new Menu(this);

    }

    private JPanel buildContentPanel() {
        JPanel panel = new JPanel();



        GridBagLayout gridBagLayout = new GridBagLayout();
        panel.setLayout(gridBagLayout);
//        
//        panel.setBorder(new LineBorder(Color.WHITE, 5));
        //   panel.setBackground(fond);


        //      JLabel image = new JLabel( new ImageIcon(path + "lo23/ui/resources/backgroundLogin.jpg"));

        //     panel.setLayout(new BorderLayout());
        //    panel.setBackground(image);

        //    BackgroundPanel bb = new BackgroundPanel();
        //     panel.add(bb);

        localPlayerLostPieces = new PiecesBox(myModel.getGManager().getCurrentGame().getRemotePlayer().getColor(), myModel);
        remotePlayerLostPieces = new PiecesBox(game.getLocalPlayer().getColor(), myModel);

        GridBagConstraints constraints = new GridBagConstraints();
        final GamePanel gamePanel = new GamePanel(myModel, game, localPlayerLostPieces, remotePlayerLostPieces);

        constraints.fill = GridBagConstraints.HORIZONTAL;

        //The panel is a grid of 6*8 squares


        //remote player
        //      constraints.insets = new Insets(10,10,10,10);
        constraints.fill = GridBagConstraints.NONE;
        //    constraints.anchor = GridBagConstraints.CENTER;

        if (isReviewGame) {
            gamePanel.launchReviewGame();
        } else {
            gamePanel.launchGame();
        }


        //Background
//        constraints.insets = new Insets(50,50,0,0);
        constraints.gridwidth = 10;
        constraints.gridheight = 10;
        constraints.gridx = 0;
        constraints.gridy = 0;


        JLabel image = new JLabel(new ImageIcon(ResourceManager.getInstance().getResource("wood.png")));
        panel.add(image, constraints, -1);



        //Grid
        constraints.insets = new Insets(0, -180, 0, 0);
        constraints.gridwidth = 10;
        constraints.gridheight = 8;
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(gamePanel, constraints, 0);


        //Chat panel
        constraints.insets = new Insets(0, 680, 0, 0);
        constraints.gridwidth = 10;
        constraints.gridheight = 10;
        constraints.gridx = 0;
        constraints.gridy = 0;

        ChatPanel2 chatPanel = new ChatPanel2(myModel,this);
        panel.add(chatPanel, constraints, 1);

        //remote player
        constraints.insets = new Insets(-435, -900, 0, 0);
        constraints.gridwidth = 10;
        constraints.gridheight = 10;
        constraints.gridx = 0;
        constraints.gridy = 0;
        PlayerPanel remotePlayerPanel = new PlayerPanel(myModel, myModel.getGManager().getCurrentGame().getRemotePlayer(), "/lo23/ui/resources/gamer1.png");

        panel.add(remotePlayerPanel, constraints, 0);


        //local player
        constraints.insets = new Insets(435, -900, 0, 0);
        constraints.gridwidth = 10;
        constraints.gridheight = 10;
        constraints.gridx = 0;
        constraints.gridy = 0;

        PlayerPanel localPlayerPanel = new PlayerPanel(myModel, myModel.getGManager().getCurrentGame().getLocalPlayer(), "/lo23/ui/resources/gamer2.png");
        // PlayerPanel localPlayerPanel = new PlayerPanel(myModel);
        panel.add(localPlayerPanel, constraints, 0);

        //Timer remote Player
        constraints.insets = new Insets(-570, -850, 0, 0);
        constraints.gridwidth = 10;
        constraints.gridheight = 10;
        constraints.gridx = 0;
        constraints.gridy = 0;
        TimerPanel timerPanelRemotePlayer = new TimerPanel(myModel, myModel.getGManager().getCurrentGame().getRemotePlayer());
        panel.add(timerPanelRemotePlayer, constraints);


        //Piecebox remote Player
        constraints.insets = new Insets(200, 200, 800, 200);
        constraints.gridwidth = 10;
        constraints.gridheight = 10;
        constraints.gridx = 0;
        constraints.gridy = 0;

        panel.add(remotePlayerLostPieces, constraints, 0);
//

//        

//       Timer player local 
        constraints.insets = new Insets(570, -850, 0, 0);
        constraints.gridwidth = 10;
        constraints.gridheight = 10;
        constraints.gridx = 0;
        constraints.gridy = 0;
        TimerPanel timerPanelLocalPlayer = new TimerPanel(myModel, myModel.getGManager().getCurrentGame().getLocalPlayer());
        panel.add(timerPanelLocalPlayer, constraints);


//       Piecebox local player
        constraints.insets = new Insets(200, 200, -380, 200);
        constraints.gridwidth = 10;
        constraints.gridheight = 10;
        constraints.gridx = 0;
        constraints.gridy = 0;
//        constraints.fill = GridBagConstraints.BOTH ;

        panel.add(localPlayerLostPieces, constraints, 0);


        //Review 
       /*constraints.gridwidth = 6;
         constraints.gridheight = 1;
         constraints.gridx = 1;
         constraints.gridy = 0;
         ReviewPanel reviewPanel = new ReviewPanel(myModel, chatPanel, gamePanel);

         panel.add(reviewPanel, constraints);*/

        //Appels à des fins de test TODO : à retirer
        //       remotePlayerLostPieces.updateBox();
        //       localPlayerLostPieces.updateBox();


        constraints.insets = new Insets(100,-850,100,0);
        constraints.gridwidth = 12;
	constraints.gridheight = 12;
	constraints.gridx = 0;
	constraints.gridy = 0;

    //chess king picture
          chess_king = new JLabel( new ImageIcon(ResourceManager.getInstance().getResource("swords.png")));
          panel.add(chess_king,constraints,3);
          chess_king.setVisible(false);

        //chess king picture
          chess_king_crown = new JLabel( new ImageIcon(ResourceManager.getInstance().getResource("king_crown.png")));
          panel.add(chess_king_crown,constraints,3);
          chess_king_crown.setVisible(false);



        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
