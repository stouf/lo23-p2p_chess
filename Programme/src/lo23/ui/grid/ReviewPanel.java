/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.ui.grid;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.text.BadLocationException;
import lo23.data.ApplicationModel;
import lo23.data.Event;
import lo23.data.GridState;
import lo23.data.Message;
import lo23.data.Move;
import lo23.data.Player;
import lo23.data.Position;
import lo23.data.pieces.Bishop;
import lo23.data.pieces.GamePiece;
import lo23.utils.ResourceManager;

/**
 * Create the review panel
 * @author loic
 */
public class ReviewPanel extends javax.swing.JPanel {
    private ApplicationModel myModel;
    private ChatPanel2 myChatPanel;
    private GamePanel myGamePanel;
    private ArrayList<GridState> listGridStateBase;
    private ArrayList<GridState> listGridState;
    private int currentEvent;
    /**
     * Creates new form ReviewPanel
     */
    public ReviewPanel() {
        initComponents();
    }

    public ReviewPanel(ApplicationModel model, ChatPanel2 chat, GamePanel gamePanel) {
        initComponents();
        ImageIcon img;
        myModel = model;
        myChatPanel = chat;
        myGamePanel = gamePanel;
        currentEvent = 0;
        listGridState = new ArrayList<GridState>();    
       //listEvents = myModel.getGManager().getCurrentGame().getEvents();
        img = new ImageIcon(ResourceManager.getInstance().getResource("begin.png"));
        begin.setIcon(new ImageIcon(getScaledImage(img.getImage(), 30,30)));

        img = new ImageIcon(ResourceManager.getInstance().getResource("previous.png"));
        previous.setIcon(new ImageIcon(getScaledImage(img.getImage(), 30,30)));

        img = new ImageIcon(ResourceManager.getInstance().getResource("next.png"));
        next.setIcon(new ImageIcon(getScaledImage(img.getImage(), 30,30)));

        img = new ImageIcon(ResourceManager.getInstance().getResource("end.png"));
        end.setIcon(new ImageIcon(getScaledImage(img.getImage(), 30,30)));
        
       
        // on remplit le tableau avec la pile
        
        Stack<GridState> gridStates = model.getGManager().getGridStates();
                
        while(!gridStates.empty()){
            listGridState.add(gridStates.pop());
        }                
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        previous = new javax.swing.JButton();
        next = new javax.swing.JButton();
        end = new javax.swing.JButton();
        begin = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 204));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Review board", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(0, 0, 255))); // NOI18N

        previous.setToolTipText("Going to the previous move");
        previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousActionPerformed(evt);
            }
        });

        next.setToolTipText("Going to the next move");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        end.setToolTipText("Going to the end of this game");
        end.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endActionPerformed(evt);
            }
        });

        begin.setToolTipText("Going to the beginning of this game");
        begin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(end)
                    .addComponent(previous)
                    .addComponent(next)
                    .addComponent(begin))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(begin)
                .addGap(4, 4, 4)
                .addComponent(previous)
                .addGap(5, 5, 5)
                .addComponent(next)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(end)
                .addContainerGap(123, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents



    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        if(currentEvent < listGridState.size() - 1){
            
            currentEvent++;
            
            // on mets à jour le board
            myGamePanel.updateReviewBoard(listGridState.get(currentEvent));
            
            // on affiche le mouvement
            try {
                myChatPanel.gameMsg(listGridState.get(currentEvent).getMove());
            } catch (BadLocationException ex) {
                Logger.getLogger(ReviewPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        }
    }//GEN-LAST:event_nextActionPerformed

    /**
     * Fonction qui permet de revenir sur le coup précédent
     * @param evt 
     */
    private void previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousActionPerformed
        if(currentEvent > 0){
            
            currentEvent--;
            
            myGamePanel.updateReviewBoard(listGridState.get(currentEvent));
        }

    }//GEN-LAST:event_previousActionPerformed

    private void beginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beginActionPerformed
        // on déroule tous les events à partir d'où on est jusqu'a se retrouver à 0
        while(currentEvent >= 0){ // n'est pas un mouvement
            previousActionPerformed(null);
        }
    }//GEN-LAST:event_beginActionPerformed

    private void endActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endActionPerformed
    // on déroule tous les events à partir d'où on est jusqu'a se retrouver à la fin
        while(currentEvent <= listGridState.size()){ // n'est pas un mouvement
            nextActionPerformed(null);
        }
    }//GEN-LAST:event_endActionPerformed

     private Image getScaledImage(Image srcImg, int w, int h){
    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.BITMASK);
    Graphics2D g2 = resizedImg.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(srcImg, 0, 0, w, h, null);
    g2.dispose();
    return resizedImg;
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton begin;
    private javax.swing.JButton end;
    private javax.swing.JButton next;
    private javax.swing.JButton previous;
    // End of variables declaration//GEN-END:variables
}
