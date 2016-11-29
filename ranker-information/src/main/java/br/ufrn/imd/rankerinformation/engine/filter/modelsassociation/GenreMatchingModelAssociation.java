package br.ufrn.imd.rankerinformation.engine.filter.modelsassociation;

import br.ufrn.imd.rankerinformation.engine.filter.ModelAssociation;
import br.ufrn.imd.rankerinformation.user.model.SourceData;

public class GenreMatchingModelAssociation implements ModelAssociation{

	@Override
	public double calculate(SourceData termPreferences, String termsInformation) {
		String content = termPreferences.getContent().toLowerCase();		
		double weight = termPreferences.getWeight();
	
		if(content.equals(termsInformation)) {
			return weight;
		} else {
			return 0.0;
		}
	}
}
