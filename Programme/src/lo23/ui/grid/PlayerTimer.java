

package lo23.ui.grid;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import lo23.data.ApplicationModel;
import lo23.data.Constant;
import lo23.data.Player;
import lo23.data.managers.Manager;
import lo23.utils.Enums;
import lo23.utils.Enums.CONSTANT_TYPE;


/**
 * Create the timer for the both players
 * @author mantonel
 */
public class PlayerTimer {

    TimerPanel localTimerPanel;
    private int delay = 1000; //milliseconds
    private Timer timer;
    private int currentTimer;
    private int endOfTimer;
    private ApplicationModel myModel;
    Player p;
    boolean isTimerEnd = false;
    
    public static final String NEW_EVENT_ADDED = "new_event_added";


    public PlayerTimer(final TimerPanel timerPanel, ApplicationModel am, final Player player) {
        myModel = am;
        localTimerPanel = timerPanel;
        p = player;
        endOfTimer = (int) p.getRemainingTime();
      
        
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                currentTimer--;
                timerPanel.getLabel().setText(getText());
                
                if(currentTimer == 0 && isTimerEnd == false){
                    if(myModel.getGManager().getCurrentGame().getLocalPlayer() == p){
                         Constant out_of_time = new Constant(CONSTANT_TYPE.OUT_OF_TIME, myModel.getGManager().getCurrentGame().getLocalPlayer(), p);
                   ((Manager)myModel.getGManager()).publish(NEW_EVENT_ADDED, out_of_time);
                    } else {
                         Constant out_of_time = new Constant(CONSTANT_TYPE.OUT_OF_TIME, p, myModel.getGManager().getCurrentGame().getRemotePlayer());
                   ((Manager)myModel.getGManager()).publish(NEW_EVENT_ADDED, out_of_time);
                    }
                   
                   
                }
            }
        };
        
        currentTimer = endOfTimer;
        timerPanel.getLabel().setText(getText());
        timer = new Timer(delay, taskPerformer);
        //startTimer();
    }

	public void startTimer(){
    	p.startTime();
        timer.start();
        
    }

    public void stopTimer(){
    	p.stopTime();
        currentTimer = 0;
        timer.stop();
        localTimerPanel.getLabel().setText(getText());
    }

    public void pauseTimer(){
        timer.stop();
    }

    public void recoveryTimer(){
        timer.start();
    }
    
    public void startAt(int s){
    	currentTimer = (int) p.getRemainingTime();
//        currentTimer = s;
        timer.start();
    }

    public String getText(){

    	if (currentTimer < 0)
        {
            isTimerEnd = true;
            return "00:00:00";
        }

//        if ((int) p.getRemainingTime() <= 0) {
//            this.stopTimer();
//            return "No more time!";
//        }
        
        int hour = 0;
        int minute = 0;
        int second = 0;

        if (currentTimer >= 3600){
            minute = currentTimer%3600;
            hour = (currentTimer - minute)/3600;
            second = minute%60;
            if (minute >= 60){
                minute = (minute - second)/60;
            } else {
                minute = 0;
            }
        } else if (currentTimer >= 60) {
            second = currentTimer%60;
            minute = (currentTimer - second)/60;
        } else second = currentTimer;

        String hoursDisplayed = String.valueOf(hour);
        String minutesDisplayed = String.valueOf(minute);
        String secondsDisplayed = String.valueOf(second);
        
        if (hour < 10) {hoursDisplayed = "0" + hour;}
        
        if (minute < 10) {minutesDisplayed = "0" + minute;}
        
        if (second < 10) {secondsDisplayed = "0" + second;}
        
        return hoursDisplayed + " : " + minutesDisplayed  + " : " +  secondsDisplayed;
    }

    public boolean isRunning(){
        return timer.isRunning();
    }


}
