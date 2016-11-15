package br.ufrn.imd.rankerinformation.engine.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.ufrn.imd.rankerinformation.engine.filter.model.ComparativeInformation;
import br.ufrn.imd.rankerinformation.engine.filter.model.ComparativeTerm;
import br.ufrn.imd.rankerinformation.engine.filter.model.PreferencesTerms;
import br.ufrn.imd.rankerinformation.engine.filter.model.UserPreferences;
import br.ufrn.imd.rankerinformation.engine.model.Information;
import br.ufrn.imd.rankerinformation.user.model.Preferences;

public class Analyzer{

	public List<Information> analyze(List<Information> informations, Preferences prefferences, ModelAssociation modelAssociation) {
		List<ComparativeInformation> comparativeInformationList = new ArrayList<ComparativeInformation>();
		
		//TODO Rever essa parte
		PreferencesTerms preferencesTerms = new PreferencesTerms(prefferences);
		
		System.out.println("----------------------");
		System.out.println("[ANALYSER] Comparing term by term from informations");
		System.out.println("----------------------");
		
		for (Information information : informations) {
			
			//Compara cada Informação (cada noticia) com as preferências de acordo com um modelo de associação
			ComparativeInformation comparativeInformation = association(information, preferencesTerms, modelAssociation);
			
			//Adiciona na lista
			comparativeInformationList.add(comparativeInformation);
		}
		System.out.println("\n ----------------------");
		
		//TODO RETIRAR
		for (ComparativeInformation information : comparativeInformationList) {System.out.println("[NOT_ORDER] "+information.getInformation().getTitle() +", "+ information.getWeight());}
		System.out.println("----------------------");
		
		List<ComparativeInformation> rankInformationList = sortInformations(comparativeInformationList);
		
		//TODO RETIRAR
		for (ComparativeInformation information : rankInformationList) {System.out.println("[ORDER] "+information.getInformation().getTitle() +", "+ information.getWeight());}
		System.out.println("----------------------");
		
		List<Information> listInformation = toListInformation(rankInformationList);

		return listInformation;
	}
	
	
	private ComparativeInformation association(Information information, PreferencesTerms preferencesTerms, ModelAssociation modelAssociation) {
		ComparativeInformation compInformation = new ComparativeInformation(information);
		
		Extractor extractor = new Extractor();
		Filter filter = new Filter();
		
		Set<String> termsSetInformation = extractor.extract(information.getContent(), filter, true);
		
		
		for (UserPreferences termPreferences : preferencesTerms.getUserPreferences()) {
			for (String termsInformation : termsSetInformation) {
				double weight = modelAssociation.calculate(termPreferences, termsInformation);
				ComparativeTerm compTerm = new ComparativeTerm(termPreferences, termsInformation, weight);
				compInformation.add(compTerm);

				//TODO (TEMPORARIO) RETIRAR, ISSO NÃO DEVERIA ESTAR AQUI...
				compInformation.setWeight(compInformation.getWeight()+weight);
			}
		}
		
		return compInformation;
	}

	//TODO Otimizar.
	private List<ComparativeInformation> sortInformations(List<ComparativeInformation> comparativeInformationList) {
		
		int size = comparativeInformationList.size();
		int index = 0, test;
		ComparativeInformation information = comparativeInformationList.get(index);
		System.out.println("[ANALYSER] Ordering informations");
		for(int i=0; i < size;i++){
			information = comparativeInformationList.get(i);
			index = i;
			for(int j=i; j < size;j++){
				test = Double.compare(comparativeInformationList.get(j).getWeight() , information.getWeight());
				if(test == 1) {
					index = j;
					information = comparativeInformationList.get(j);
				}
			}
			
			comparativeInformationList.set(index, comparativeInformationList.get(i));
			comparativeInformationList.set(i, information);
			test = 0;
		} 
		System.out.println("[SORT] ");
		return comparativeInformationList;
	}

	private List<Information> toListInformation(List<ComparativeInformation> comparativeInformationList) {
		
		List<Information> listInformation = new ArrayList<Information>(); 
		for (ComparativeInformation element : comparativeInformationList) {
			listInformation.add(element.getInformation());
		}
		
		return listInformation;
	}
	
}

