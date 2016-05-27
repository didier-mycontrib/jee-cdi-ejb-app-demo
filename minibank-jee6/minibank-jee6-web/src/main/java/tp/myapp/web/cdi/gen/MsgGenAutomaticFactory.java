package tp.myapp.web.cdi.gen;

import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import tp.myapp.web.cdi.qualifier.English;
import tp.myapp.web.cdi.qualifier.French;

//@Named
//@ApplicationScoped
public class MsgGenAutomaticFactory {
	
	private Random r = new Random();
	
	@Inject @French
	private MessageGenerator msgGenFr;
	
	@Inject @English
	private MessageGenerator msgGenEn;
	
	@Produces @Default
	public MessageGenerator getMsgGen(){
		MessageGenerator msgGen=null;
		int n = r.nextInt() % 2;
		msgGen = (n==0) ? msgGenEn : msgGenFr;	
		System.out.println("msgGen build by producer: " + msgGen.getClass().getSimpleName());
		return msgGen;
	}

}
