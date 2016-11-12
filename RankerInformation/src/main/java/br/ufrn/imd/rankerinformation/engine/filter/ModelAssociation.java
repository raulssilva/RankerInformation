package br.ufrn.imd.rankerinformation.engine.filter;

import br.ufrn.imd.rankerinformation.engine.filter.model.UserPreferences;

public interface ModelAssociation {
	
	public double calculate(UserPreferences termPreferences, String termsInformation);

}
