package tp.myapp.web.cdi.gen;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import tp.myapp.web.cdi.qualifier.English;


@Named //javax.inject
@ApplicationScoped //javax.enterprise.context
@English
//@Default //javax.enterprise.inject
public class MsgGenV1 implements MessageGenerator {

	@Override
	public String messageFromInput(String input) {
		return "hello " + input;
	}

}
