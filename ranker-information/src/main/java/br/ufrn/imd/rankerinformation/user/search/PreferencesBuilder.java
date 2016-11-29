package br.ufrn.imd.rankerinformation.user.search;

import java.util.List;

import br.ufrn.imd.rankerinformation.dao.impl.PreferencesDAO;
import br.ufrn.imd.rankerinformation.oauth.RequestAuthorization;
import br.ufrn.imd.rankerinformation.user.model.Preferences;
import br.ufrn.imd.rankerinformation.user.model.SourceData;
import br.ufrn.imd.rankerinformation.user.model.User;

public class PreferencesBuilder {
	
	public void builder(User user, List<SourceData> listSourceData){
		Preferences preferences = new Preferences(user.getId(), user, listSourceData);
		
		PreferencesDAO preferencesDAO = new PreferencesDAO();
		preferencesDAO.createPrefferences(preferences);
		System.out.println("[BUILDER] Dados Salvos na Base de Dados");
	}
	
	public void buiderFormASearch(RequestAuthorization auth, PreferencesProviderSearch preferencesProviderSearch){
		User user = preferencesProviderSearch.consultUser(auth);
		List<SourceData> listSourceData = preferencesProviderSearch.consultDataSource(auth, user);
		System.out.println("[BUILDER FROM SEARCH] Dados Buscados no ProviderSearch");
		
		builder(user, listSourceData);
	}

}
