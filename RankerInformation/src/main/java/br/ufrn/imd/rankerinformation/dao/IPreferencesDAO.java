package br.ufrn.imd.rankerinformation.dao;

import br.ufrn.imd.rankerinformation.user.model.Preferences;

public interface IPreferencesDAO {
	
	public boolean createPrefferences(Preferences preferences);
	
	public Preferences readPrefferencesByIdUser(int ID_USER);
    
    public Preferences readPrefferences(int ID_PREFERENCES);
    
    public void updatePrefferences(int ID_PREFERENCES, Preferences prefferences);
    
    public void deletePrefferences(int ID_PREFERENCES);

}
