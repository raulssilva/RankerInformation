package br.ufrn.imd.sise.user.search;

import java.util.List;

import org.json.simple.parser.ParseException;

import br.ufrn.imd.sise.oauth.RequestAuthorization;
import br.ufrn.imd.sise.oauth.exceptions.RequestException;
import br.ufrn.imd.sise.oauth.exceptions.UnauthorizedServiceRequestException;
import br.ufrn.imd.sise.user.model.Course;
import br.ufrn.imd.sise.user.model.CourseClass;
import br.ufrn.imd.sise.user.model.Prefferences;
import br.ufrn.imd.sise.user.model.User;
import br.ufrn.imd.sise.user.search.exceptions.AcessVinculoUserException;
import br.ufrn.imd.sise.user.search.exceptions.ConsultCourseClassException;
import br.ufrn.imd.sise.user.search.exceptions.CourseClassIdException;
import br.ufrn.imd.sise.user.search.exceptions.InvalidIdUserException;
import br.ufrn.imd.sise.user.search.exceptions.ServiceAPIException;

public class PrefferencesSearch {
	
	private RequestAuthorization authorization;
	private ProfileSearch profileSearch;
	private CourseClassSearch courseClassSearch;
	
	public PrefferencesSearch(RequestAuthorization authorization){
		this.authorization = authorization;
		this.profileSearch = new ProfileSearch(authorization);
		this.courseClassSearch = new CourseClassSearch(authorization);
	}
	
	public Prefferences searchPrefferences() throws UnauthorizedServiceRequestException, RequestException, ServiceAPIException, ParseException{

		Prefferences prefferences = null;
		
		try {
			
			User user = profileSearch.consultUser();
			List<CourseClass> coursesClass = courseClassSearch.consultCourseClass(user.getIdStudent());
			Course course = profileSearch.colsultCourse();
			
			prefferences = new Prefferences(course, coursesClass, user);
		
		} catch (InvalidIdUserException | AcessVinculoUserException | ConsultCourseClassException | CourseClassIdException e) {
			throw new ServiceAPIException(e);
		}
		
		return prefferences;
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