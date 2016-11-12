package br.ufrn.imd.sise.engine.filter.model;

import java.util.HashSet;
import java.util.Set;

import br.ufrn.imd.sise.user.model.CourseClass;
import br.ufrn.imd.sise.user.model.Prefferences;

public class PreferencesTerms {
	
	private Set<UserPreferences> userPreferences;
	
	//TODO PONTO FRÁGIL (só dados academicos)
	public PreferencesTerms(Prefferences preferences){
		Set<UserPreferences> terms = new HashSet<>();
		for (CourseClass course : preferences.getCoursesClass()) {
			String nameDisciplina = course.getSubject().getName();
			
			String matricula = String.valueOf(preferences.getUser().getIdMatriculation());
			UserPreferences userPreferences = new UserPreferences(nameDisciplina, matricula);
			terms.add(userPreferences);
			//System.out.println(nameDisciplina);
		}
		setUserPreferences(terms);

	}
	
//	private abstract void extractPreferences(Prefferences preferences){
//		
//	}

	public Set<UserPreferences> getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(Set<UserPreferences> userPreferences) {
		this.userPreferences = userPreferences;
	}
	
}
