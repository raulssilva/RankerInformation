package ranker_movies_information;

import br.ufrn.imd.rankerinformation.engine.filter.ModelAssociation;
import br.ufrn.imd.rankerinformation.user.model.SourceData;

public class GenreModelAssociation implements ModelAssociation{

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
