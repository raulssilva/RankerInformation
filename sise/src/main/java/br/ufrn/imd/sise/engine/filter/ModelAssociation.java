package br.ufrn.imd.sise.engine.filter;

import br.ufrn.imd.sise.engine.filter.model.UserPreferences;

public interface ModelAssociation {
	
	public double calculate(UserPreferences termPreferences, String termsInformation);

}
