package br.ufrn.imd.rankerinformation.engine.filter.modelsassociation;

import br.ufrn.imd.rankerinformation.engine.filter.ModelAssociation;
import br.ufrn.imd.rankerinformation.user.model.SourceData;

public class IntersectionModelAssociation implements ModelAssociation{

	public IntersectionModelAssociation(){
		
	}
	
	/* Comparation method.
	 * return:
	 * 10 - if the two terms are equals.
	 * value - if they are differents. 
	 * Obs.: value means the number of letters are equals.
	 * */
	@Override
	public double calculate(SourceData termPreferences, String termsInformation) {
		try {
			String userTerm = termPreferences.getContent().toLowerCase(); 
			if(userTerm.equals(termsInformation)) {
				return 10.0;
			} else {
				double value = 0, intersec = 0;
				int size = 0;	
				if(userTerm.length() > termsInformation.length()) {
					size = userTerm.length(); 
				} else {
					size = termsInformation.length();
				} 
				
				for(int i=0; i<size;i++) {
					if(userTerm.charAt(i) == termsInformation.charAt(i)) {
						intersec += 1;
					} else {
						value = ((intersec/userTerm.length()) * 10);
						return value;
					}
				}
				return value;
			}	
			
		} catch (Exception e){
			e.getStackTrace();
		}
		return 0;
	}	

}
