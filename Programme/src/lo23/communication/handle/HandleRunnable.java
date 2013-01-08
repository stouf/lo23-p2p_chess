package lo23.communication.handle;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract class which simplifies the Handle which have to be Runnable.
 * used by HandleReceiveMessage, HandleReceiveUDPMessage and HandleServerConnection
 */
public abstract class HandleRunnable implements Runnable {

    private final AtomicBoolean start;

    /**
     * Constructor of HandleRunnable.
     */
    public HandleRunnable() {
        this.start = new AtomicBoolean(false);
    }

    @Override
    public abstract void run();

    /**
     * Notify that the Thread is ready (started).
     */
    protected void notifyStart() {
        start.set(true);
        synchronized (start) {
            start.notify();
        }
    }

    /**
     * Get the value of start which indicates if the Handle is started.
     * @return start value
     */
    protected boolean getStart() {
        return start.get();
    }

    /**
     * When this method is launched, the current thread is waiting for the start
     * of the Thread.
     */
    public void waitStarted() {
        while (!start.get()) {
            try {
                synchronized (start) {
                    start.wait(1000);
                }
            } catch (InterruptedException e) {
                Logger.getLogger(HandleRunnable.class.getName()).log(Level.SEVERE, "Error waitStarted", e);
            }
        }
    }
    
    /**
     * Stop the runnable handle (or notify the stop).
     * (Don't interrupt the handle if it use blocking method)
     */
    public void stopHandle() {
        start.set(false);
    }
}
