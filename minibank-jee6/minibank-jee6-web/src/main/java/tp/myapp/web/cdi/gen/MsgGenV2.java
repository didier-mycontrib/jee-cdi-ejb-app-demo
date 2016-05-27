package tp.myapp.web.cdi.gen;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.inject.Named;

import tp.myapp.web.cdi.qualifier.French;


@Named //javax.inject
@ApplicationScoped //javax.enterprise.context
@French
//@Any //(by default)
public class MsgGenV2 implements MessageGenerator {

	@Override
	public String messageFromInput(String input) {
		return "bonjour " + input;
	}

}
