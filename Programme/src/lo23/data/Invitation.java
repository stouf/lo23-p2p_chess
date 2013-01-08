package lo23.data;

import java.io.Serializable;

public abstract class Invitation implements Serializable {
    static final long serialVersionUID = 1L;
    
    private PublicProfile host;
    private PublicProfile guest;

    public Invitation(PublicProfile host, PublicProfile guest) {
        this.host = host;
        this.guest = guest;
    }

    /**
     * Getter for the host attribute
     *
     * @return The host attribute
     */
    public PublicProfile getHost() {
        return host;
    }

    /**
     * Getter for the guest attribute
     *
     * @return The guest attribute
     */
    public PublicProfile getGuest() {
        return guest;
    }
}
