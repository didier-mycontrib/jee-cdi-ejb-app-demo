package tp.myapp.web.cdi.news;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Named;

@Named
@ApplicationScoped
@Default
public class NewsGen implements NewsGenerator {
	public String getLastNews() {
		return "fresh news";
	}
}
