/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lo23.ui.grid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import lo23.data.ApplicationModel;
import lo23.data.Player;
import lo23.utils.Enums;

/**
 * Create the timer panel
 * @author mantonel
 */
@SuppressWarnings("serial")
public class TimerPanel extends JPanel {

    public PlayerTimer playerTimer;
    private Border border;
    private ApplicationModel myModel;
    public Player player;
    EventListener eventListener;
    JLabel timer;
    JButton start;
    JButton stop;
    JButton pause;
    JButton recovery;

    public TimerPanel(ApplicationModel model, Player p) {
        myModel = model;
        player = p;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(100,100));
        eventListener = new EventListener(this, myModel);
        //setHorizontalTextPosition(SwingConstants.CENTER);

        timer = new JLabel();
        add(timer, BorderLayout.NORTH);

        
        timer.setFont(new Font("Dialog", Font.BOLD, 15));
         border = new LineBorder(new Color(255));

  //      setBorder(border);

        playerTimer = new PlayerTimer(this, myModel, player);
        //playerTimer.startTimer();
        
           if(p.getColor() == Enums.COLOR.WHITE){
            playerTimer.startTimer();
        } 
        
        addPropertyChangeListener(eventListener);
    }

    public JLabel getLabel(){
        return timer;
    }

}
