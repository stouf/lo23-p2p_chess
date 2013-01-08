package lo23.communication.message;
import lo23.data.Constant;

/**
 * This message is sent while players agree with start or stop a party.
 */
public class ConstantMsg extends GameMessage {

    private Constant constant;

    /**
     * Constructor of the Constant message.
     */
    public ConstantMsg(Constant constant){
        this.constant = constant;
    }
    
    public void setConstant(Constant constant) {
        this.constant = constant;
    }

    public Constant getConstant() {
        return constant;
    }

}
