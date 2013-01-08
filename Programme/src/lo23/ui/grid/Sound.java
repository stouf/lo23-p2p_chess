package lo23.ui.grid;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
 
/**
*  Read the audio file for the music game
* @author guigou
*/
public class Sound
{
    static SourceDataLine source;
    static boolean boucle = true;
    static boolean lancer = false;
   
    /*
     * read an audio file with string file
     */
    public  void readAudioFile(String fileName) throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {
        URL url = new URL(fileName);
        readAudioFile(url);
    }
    
    /*
     * read an audio file with url file
     */
    public  void readAudioFile(URL url) throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {
     
         lancer = true;
      
         
         /*
         *never stop the read audio file
         */
            while(boucle)
            {
                AudioInputStream ais = AudioSystem.getAudioInputStream(url);
          
        
            AudioFormat format = ais.getFormat();
            Info info = new Info(SourceDataLine.class, format);
            source = (SourceDataLine)AudioSystem.getLine(info);
            source.open(format);
            source.start();
           

            int read = 0;
            byte[] audioData = new byte[16384];

            while(read > -1)
                {
                read = ais.read(audioData, 0 , audioData.length);
                if(read >= 0)
                    source.write(audioData, 0, read);
                }
            source.drain();
            source.close();
            }
      }
    
         /*
         *stop the sound definitely
         */
    public void stop_sound()
    {
        if(lancer == true)
        { 
            System.out.println("entre dans stop sound");
            source.drain();
            boucle = false;
          //  source.close();
          
        }
    }
    
}
