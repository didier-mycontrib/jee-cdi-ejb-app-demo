package tp.myapp.minibank.core.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: MyMDB , simple destination/queue name (ex: "myQueue" without "jms/" )
 */
@MessageDriven(	activationConfig = {
		@ActivationConfigProperty(	propertyName = "destination", propertyValue = "myQueue"), 
		@ActivationConfigProperty(	propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		})
public class MyMDB implements MessageListener {

    /**
     * Default constructor. 
     */
    public MyMDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {	
      try {
		  if(message instanceof TextMessage){
			  TextMessage txtMsg = (TextMessage) message;
			  System.out.println("received message = " + txtMsg.getText());
		  }
	} catch (JMSException e) {
		e.printStackTrace();
	}
    }

}
