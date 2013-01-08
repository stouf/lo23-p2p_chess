/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lo23.data;

import java.io.Serializable;

/**
 *
 * @author khamidou
 */
public class Message extends Event implements Serializable {
    static final long serialVersionUID = 1L;
    
    private String contents;
    private Player sender;
    private Player receiver;

    public Message(String contents, Player sender, Player receiver) {
        this.contents = contents;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getContents() {
        return contents;
    }

    public Player getReceiver() {
        return receiver;
    }

    public void setReceiver(Player receiver) {
        this.receiver = receiver;
    }

    public Player getSender() {
        return sender;
    }

    public void setSender(Player sender) {
        this.sender = sender;
    }

}
