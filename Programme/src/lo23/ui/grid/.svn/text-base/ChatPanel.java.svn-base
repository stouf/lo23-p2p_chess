
package lo23.ui.grid;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class create the Layout for the chat module
 * @author lopicave
 */
public class ChatPanel extends JPanel
{
    JButton saveBtn;
    JButton drawBtn;
    JButton giveUpBtn;

    public ChatPanel() 
    {
        super();
        build();
    }

    private void build()
    {
        setSize(250, 760);
        setBackground(Color.red);
        GridBagLayout chatBag = new GridBagLayout();
        setLayout(chatBag);

        // Buttons
        saveBtn = new JButton("Sauvegarder la partie");
        drawBtn = new JButton("Match nul");
        giveUpBtn = new JButton("Abandonner");

        

        saveBtn.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                //connectBtnActionPerformed(ae);
            }
        });


        chatBag.setConstraints(saveBtn, modifyConstraint(GridBagConstraints.REMAINDER, 1, 1, 1));
        chatBag.setConstraints(drawBtn, modifyConstraint(GridBagConstraints.BOTH, 1, 2, 1));
        chatBag.setConstraints(giveUpBtn, modifyConstraint(GridBagConstraints.REMAINDER, 1, 2, 1));

        add(saveBtn);
        add(drawBtn);
        add(giveUpBtn);
    }

    private GridBagConstraints modifyConstraint(int width, float weightx, float weighty, int height) 
    {
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = width;
        c.weightx = weightx;
        c.weighty = weighty;
        c.gridheight = height;
        c.fill = GridBagConstraints.HORIZONTAL;

        return c;
    }
}
