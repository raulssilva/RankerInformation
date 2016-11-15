package br.ufrn.imd.rankerinformation.engine.filter;

import br.ufrn.imd.rankerinformation.engine.filter.model.UserPreferences;

public class IntersectionModelAssociation2 implements ModelAssociation{

	public IntersectionModelAssociation2(){
		
	}
	
	@Override
	public double calculate(UserPreferences termPreferences, String termsInformation) {
		try {
			String userTerm = termPreferences.getTermo().toLowerCase(); 
			double weight = 0;
			if(userTerm.equals(termsInformation)) {
				return 10.0;
			} else {
				String userUniqTerm[] = termPreferences.getTermo().toLowerCase().split(" "); 
				
				double total = 0;
				for (int i = 0; i < userUniqTerm.length; i++){
					total+=userUniqTerm[i].length();
					if(termsInformation.equals(userUniqTerm[i])){
						weight += userUniqTerm[i].length();
					}
				}
				return (weight/total)*10;
			}	
			
		} catch (Exception e){
			e.getStackTrace();
		}
		return 0;
	}	

}
