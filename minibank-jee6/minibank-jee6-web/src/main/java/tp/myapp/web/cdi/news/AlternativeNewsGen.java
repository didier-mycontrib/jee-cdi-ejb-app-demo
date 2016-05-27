package tp.myapp.web.cdi.news;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Named;

@Named
@ApplicationScoped
@Alternative
public class AlternativeNewsGen implements NewsGenerator {
	public String getLastNews() {
		return "alternative news";
	}
}
