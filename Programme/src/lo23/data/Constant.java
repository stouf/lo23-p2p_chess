package lo23.data;

import lo23.utils.Enums;
import lo23.utils.Enums.CONSTANT_TYPE;

/**
 * This class is used for communication between two players.
 * Messages in this class are all messages that aren't Message or Move objects
 */
public class Constant extends Event
{
    private Enums.CONSTANT_TYPE constant;
    private Player receiver;
    private Player sender;
    private Object argument;
    
    /**
     * Default constructor
     * 
     * @param constant The concerned constant
     * @param receiver The receiver
     * @param sender The sender (simply...)
     */
    public Constant(Enums.CONSTANT_TYPE constant, Player receiver, Player sender)
    {
        this.constant = constant;
        this.receiver = receiver;
        this.sender = sender;
    }

        public Constant(Enums.CONSTANT_TYPE constant, Player receiver, Player sender, Object arg)
    {
        this.constant = constant;
        this.receiver = receiver;
        this.sender = sender;
        this.argument = arg;
    }

    public CONSTANT_TYPE getConstant() {
        return constant;
    }

    public Player getReceiver() {
        return receiver;
    }

    public Player getSender() {
        return sender;
    }

    public Object getArgument() {
        return argument;
    }

    public void setReceiver(Player receiver) {
        this.receiver = receiver;
    }

    public void setSender(Player sender) {
        this.sender = sender;
    }
}
