
package lo23.ui.grid;

import java.util.logging.Level;
import java.util.logging.Logger;
import lo23.utils.ResourceManager;

/**
 * This class allows you to start a sound or a noise.
 * @author guigou
 */
public class Launch_Sound extends Thread implements Runnable{
        Thread a;           //thread create when we have a new sound 
        Sound s;            //use for music
        Noise n;            //use for noise
        String sound;       //the path sound
        boolean enpause;    //pause the sound or noise
        
       
        
    /**
    * Constructor to launch the sound
    * 
    */    
        public Launch_Sound(String sound)
        {
          //create the new thread
         a = new Thread(this);
         if(sound.equals("chess.wav") || sound.equals("chess_login.wav"))  // call the launch the sound
         {
               s = new Sound();
         }
         else                       //call launch the noise
         {
               n = new Noise();
         }
         //the difference is that the sound, never stop (while(1))
       
         enpause=false;
         this.sound = sound;
         
        }
      

        
    @Override
    /*
     * launch sound and noise
     */
   
        public void run()
        {    
            
                String path = getClass().getResource("/lo23/ui/resources/").getPath();

                try
                {
                    //to the music game (sound)
                    if(sound.equals("chess.wav") || sound.equals("chess_login.wav"))
                    {
                      
                         this.s.readAudioFile(ResourceManager.getInstance().getResource(sound));
                         
                    }
                    //to the noise 
                    else
                    {
                      
                        this.n.readAudioFile(ResourceManager.getInstance().getResource(sound));
                    }

                } catch (Exception ex) 
                {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
             
        }
        /*
         *play a sound
         */
        public void play()
        {
            if(enpause==false)
            { 
                a.start();  //start the thread
            }
            else
            { 
                a.resume(); //or resume the thread
            }
        }
        
        /*
         *pause a sound
         */
        public void pause()
        {
            enpause = true;
            a.suspend();            //suspend the thread
        }
    
   
        
                
        
}   

