package br.ufrn.imd.rankerinformation.engine.filter.model;

import java.util.HashSet;
import java.util.Set;
import br.ufrn.imd.rankerinformation.user.model.Preferences;
import br.ufrn.imd.rankerinformation.user.model.SourceData;

public class PreferencesTerms {
	
	private Set<UserPreferences> userPreferences;
	
	//TODO PONTO FRÁGIL (só dados academicos)
	public PreferencesTerms(Preferences preferences){
		Set<UserPreferences> terms = new HashSet<>();
		for (SourceData sourceData : preferences.getListSourceData()) {
			UserPreferences userPreferences = new UserPreferences(sourceData.getContent());
			terms.add(userPreferences);
		}
		setUserPreferences(terms);

	}

	public Set<UserPreferences> getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(Set<UserPreferences> userPreferences) {
		this.userPreferences = userPreferences;
	}
	
}
