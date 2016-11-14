package br.ufrn.imd.rankerinformation.user.search;

import java.util.List;

import br.ufrn.imd.rankerinformation.oauth.RequestAuthorization;
import br.ufrn.imd.rankerinformation.user.model.SourceData;
import br.ufrn.imd.rankerinformation.user.model.User;

public interface PreferencesProviderSearch {

	public User consultUser(RequestAuthorization auth);
	
	public List<SourceData> consultDataSource(RequestAuthorization auth, User user);
	
}
