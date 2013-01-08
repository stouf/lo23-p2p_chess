/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.ui.grid;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lo23.data.ApplicationModel;
import lo23.utils.Enums.COLOR;

/**
 * Create 2 differents box with eaten pieces
 * @author Laura BRIZARD
 */
public class PiecesBox extends JPanel{
    
    private COLOR boxDisplayColor;
    ApplicationModel myModel;
    GridBagLayout boxLayout;
    GridBagConstraints c;
    
    
    /** Constructeur de la classe
     * 
     * Ce constructeur permet de créer deux types de boîtes en fonction du paramètre
     * donné.Selon ce paramètre, on choisira les pièces qui doivent être affichées.
     * @param type le type de boîte à créer.
     */
    public PiecesBox(COLOR playerColor, ApplicationModel model) {
        super();
        this.boxDisplayColor = playerColor; // set the playerColor. It specify the color we want to display in our box
        boxLayout = new GridBagLayout(); // set the gridbagLayout
        this.setLayout(boxLayout); // set the created gridbaglayout as layout for the JPanel        
        this.myModel = model ; // set the model
        //this.refPanel = game ; // set the game panel that allow to get the eaten pieces
        c = new GridBagConstraints(); //Create the constraints for the GridBagLayout
        
        // Set the display parameter
        this.setMaximumSize(new Dimension(256, 84));
        this.setMinimumSize(new Dimension(256, 84));
        this.setPreferredSize(new Dimension(256, 84));
        this.setBackground(new java.awt.Color(217,191,143)); //setBackground(new java.awt.Color(255, 255, 204))
        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder("Eaten Pieces"));
      
    }
    
    /** Mise à jour 
     * 
     * Cette fonction permet de mettre à jour le contenu de la box. On parcourt la liste 
     * des pièces composant le jeu, si l'une d'elle est hors de l'échiquier, on regarde si
     * son type correspond à celui de la box. Si oui on l'ajoute, sinon on continue.
     */
    public void updateBox(GamePanel refPanel){
        //On vide l'objet pour le remplir à nouveau ensuite
        this.removeAll();
        // Récupération des Labels ;
        ArrayList<JLabel> pieces ;
        //Iterator ite = pieces.iterator();
        if (boxDisplayColor == COLOR.WHITE){
            pieces = refPanel.getWhiteAtePieces();            
            //vérifie qu'on ajoute le bon nombre de pièces à la liste
            System.out.println("Taille du conteneur de Label : " + pieces.size());
        }
        else{
            pieces = refPanel.getBlackAtePieces();
            //vérifie qu'on ajoute le bon nombre de pièces à la liste
            System.out.println("Taille du conteneur de Label : " + pieces.size());
        }
            
        //For all the pieces
        for (int i=0; i< pieces.size();i++){
            if(i < 8){ // If less than height pieces: add on the first line
                c.gridx = i;
                c.gridy = 0;
            } 
            else { // else, add a second line and add the Label on it
                c.gridx = i-8;
                c.gridy = 1;
            }
            // resize JLabel
            Image img = this.getScaledImage(this.iconToImage(pieces.get(i).getIcon()), 32, 32);
            JLabel imgLabel = new JLabel("", new ImageIcon(img), JLabel.CENTER);
            this.add(imgLabel,c);
        }
    }
    
    /** This function is made to resize Image in order to use them in the box
     * 
     * @param srcImg the image we want to resize
     * @param w the final width
     * @param h the final height
     * @return 
     */
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.BITMASK);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }
    
    /**
     * 
     * @param icon the Icon we want to convert to Image
     * @return 
     */
    private Image iconToImage(Icon icon)    
    {   
       /** On dessine l'icone dans un bufferedImage **/
       BufferedImage image = new BufferedImage( icon.getIconWidth() , icon.getIconHeight() , BufferedImage.BITMASK );
       icon.paintIcon(null, image.getGraphics() , 0 , 0 );
       return image;
    }
}

