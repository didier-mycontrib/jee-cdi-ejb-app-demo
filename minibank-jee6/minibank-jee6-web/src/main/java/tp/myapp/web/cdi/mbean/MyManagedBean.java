package tp.myapp.web.cdi.mbean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import tp.myapp.web.cdi.gen.MessageGenerator;
import tp.myapp.web.cdi.news.NewsGenerator;



@Named
@SessionScoped
//@RequestScoped
public class MyManagedBean implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String message;
	
	@Inject 
	//@English
	//@French
	//@Default by default
	private MessageGenerator msgGen;
	
	@Inject
	private NewsGenerator newsGen;
	
	public String doAction(){		
		message = msgGen.messageFromInput(name) + " [last news: " + newsGen.getLastNews() + "]";
		System.out.println("message: "+message);
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
