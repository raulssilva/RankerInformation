package ranker_books_information;

import br.ufrn.imd.rankerinformation.engine.filter.ModelAssociation;
import br.ufrn.imd.rankerinformation.user.model.SourceData;

public class CategoryModelAssociation implements ModelAssociation{

	public double calculate(SourceData termPreferences, String termsInformation) {
		String content = termPreferences.getContent().toLowerCase();		
		double weight = termPreferences.getWeight();
		
//		System.out.println("\n"+content + " x " + termsInformation);
		if(termsInformation.toLowerCase().contains(content)) {
			return weight;
		} else {
			return 0.0;
		}
	}
	
}
