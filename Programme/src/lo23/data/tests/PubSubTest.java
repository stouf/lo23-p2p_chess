/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lo23.data.tests;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import lo23.data.ApplicationModel;
import lo23.data.managers.Manager;

/**
 * This class simply tests the Publish / Subscribe module encapsulated into the Manager class
 *
 * @author doninste
 */
public class PubSubTest implements PropertyChangeListener
{

    static public void main(String[] args)
    {
        // Here we build basic necessary data
        ApplicationModel app = new ApplicationModel();
        Manager manager = new Manager(app);
        PubSubTest testObject = new PubSubTest();

        // Now, we want to listen on the channels "toto" and "hello"
        manager.subscribe(testObject, "toto");
        manager.subscribe(testObject, "hello");

        // Now, let's generate a message sent on channels "toto" and "cars"
        manager.publish("toto", "Hello you");
        manager.publish("cars", "The new BMW is out !");
        // This should output the first message, but not the second
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        System.out.println("[ " + evt.getPropertyName() + " ] " + evt.getNewValue());
    }
}
