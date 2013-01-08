/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.ui.login;

import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author Esteban
 */
public class WaitingDialog extends JDialog {
    

    public WaitingDialog(Frame frame,boolean b) {
        super(frame,b);
        
        setLayout(new BorderLayout());
        add(new JLabel("Waiting for response ...",JLabel.CENTER),BorderLayout.CENTER);
        
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.setSize(200,100);
    }
    
    
    
}
