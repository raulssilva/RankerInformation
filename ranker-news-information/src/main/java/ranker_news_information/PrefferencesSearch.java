package ranker_news_information;

import java.util.List;

import org.json.simple.parser.ParseException;

import br.ufrn.imd.rankerinformation.oauth.RequestAuthorization;
import br.ufrn.imd.rankerinformation.oauth.exceptions.InvalidServiceRequestException;
import br.ufrn.imd.rankerinformation.oauth.exceptions.RequestException;
import br.ufrn.imd.rankerinformation.oauth.exceptions.UnauthorizedServiceRequestException;
import br.ufrn.imd.rankerinformation.user.model.Preferences;
import sigaa.model.Course;
import sigaa.model.CourseClass;
import sigaa.model.User;

public class PrefferencesSearch {
	
	private RequestAuthorization authorization;
	private ProfileSearch profileSearch;
	private CourseClassSearch courseClassSearch;
	
	public PrefferencesSearch(RequestAuthorization authorization){
		this.authorization = authorization;
		this.profileSearch = new ProfileSearch(authorization);
		this.courseClassSearch = new CourseClassSearch(authorization);
	}
	
	public Preferences searchPrefferences() throws InvalidServiceRequestException, UnauthorizedServiceRequestException, RequestException, ParseException{

		Preferences preferences = null;
		
		User user = profileSearch.consultUser();
		List<CourseClass> coursesClass = courseClassSearch.consultCourseClass(user.getIdStudent());
		Course course = profileSearch.colsultCourse();
		
//		preferences = new Preferences(course, coursesClass, user);
		
		return preferences;
	}
	
	public RequestAuthorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(RequestAuthorization authorization) {
		this.authorization = authorization;
		this.profileSearch = new ProfileSearch(authorization);
		this.courseClassSearch = new CourseClassSearch(authorization);
	}

}