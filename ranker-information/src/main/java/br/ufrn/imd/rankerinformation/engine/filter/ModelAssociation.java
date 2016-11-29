package br.ufrn.imd.rankerinformation.engine.filter;

import br.ufrn.imd.rankerinformation.user.model.SourceData;

public interface ModelAssociation {
	
	public double calculate(SourceData termPreferences, String termsInformation);

}
