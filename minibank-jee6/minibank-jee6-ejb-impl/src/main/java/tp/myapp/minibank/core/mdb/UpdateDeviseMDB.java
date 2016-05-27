package tp.myapp.minibank.core.mdb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import tp.myapp.minibank.core.dao.DeviseDao;
import tp.myapp.minibank.core.entity._Devise;

/**
 * Message-Driven Bean implementation class for: MyMDB , simple destination/queue name (ex: "myQueue" without "jms/" )
 */
@MessageDriven(	activationConfig = {
		@ActivationConfigProperty(	propertyName = "destination", propertyValue = "deviseQueue"), 
		@ActivationConfigProperty(	propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		})
public class UpdateDeviseMDB implements MessageListener {
	
	@EJB
	private DeviseDao deviseDao;
	
	@Resource
	private MessageDrivenContext context;

    /**
     * Default constructor. 
     */
    public UpdateDeviseMDB() {
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    
    	System.out.println("in MDB , username = "+context.getCallerPrincipal());
    	
      try {
		  if(message instanceof MapMessage){
			  MapMessage mapMsg = (MapMessage) message;
			  String code = mapMsg.getString("code");
			  double change = mapMsg.getDouble("change");
			  System.out.println("received message to update devise with = code=" + code + " and change=" + change);
			  _Devise devise = deviseDao.getEntityById(code);
			  devise.setDChange(change);
			  deviseDao.updateEntity(devise);
		  }
	} catch (JMSException e) {
		e.printStackTrace();
	}
    }

}
