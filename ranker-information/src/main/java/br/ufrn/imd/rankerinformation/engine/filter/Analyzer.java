package br.ufrn.imd.rankerinformation.engine.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.ufrn.imd.rankerinformation.model.Information;
import br.ufrn.imd.rankerinformation.user.model.Preferences;
import br.ufrn.imd.rankerinformation.user.model.SourceData;

public class Analyzer{

	public List<Information> analyze(List<Information> informations, Preferences prefferences, ModelAssociation modelAssociation) {
		List<ComparativeInformation> comparativeInformationList = new ArrayList<ComparativeInformation>();
		
		System.out.println("----------------------");
		System.out.println("[ANALYSER] Comparing term by term from informations");
		System.out.println("----------------------");
		
		for (Information information : informations) {
			//Compara cada Informação (cada noticia) com as preferências de acordo com um modelo de associação
			ComparativeInformation comparativeInformation = new ComparativeInformation(information, prefferences);
			comparativeInformation = association(comparativeInformation, modelAssociation);
			comparativeInformationList.add(comparativeInformation);
		}
		
		List<ComparativeInformation> rankInformationList = sortInformations(comparativeInformationList);
		
		List<Information> listInformation = toListInformation(rankInformationList);

		return listInformation;
	}
	
	
	private ComparativeInformation association(ComparativeInformation comparativeInformation, ModelAssociation modelAssociation) {

		Information information = comparativeInformation.getInformation();
		Preferences preferences = comparativeInformation.getPreferences();
		
		Extractor extractor = new Extractor();
		Filter filter = new Filter();
		
		Set<String> termsSetInformation = extractor.extract(information.getContent(), filter, true);
		
		for (SourceData terms : preferences.getListSourceData()) {
			for (String termsInformation : termsSetInformation) {
				double weight = modelAssociation.calculate(terms, termsInformation);
				comparativeInformation.setWeight(comparativeInformation.getWeight()+weight);
			}
		}
		
		return comparativeInformation;
	}

	//TODO Otimizar.
	private List<ComparativeInformation> sortInformations(List<ComparativeInformation> comparativeInformationList) {
		
		int size = comparativeInformationList.size();
		int index = 0, test;
		ComparativeInformation information = comparativeInformationList.get(index);
		System.out.println("----------------------");
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
		System.out.println("----------------------");
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

