package br.ufrn.imd.rankerinformation.engine.filter;

import java.util.HashSet;
import java.util.Set;

public class Extractor{
	
	
	public Extractor(){
	}
	
	//TODO Refatorar para extração, filtragem e formatação
	public Set<String> extract(String content, Filter filter, boolean lowerCase){
				
		Set<String> setTerms = new HashSet<String>();
		String term = "";
		for(int i = 0; i < content.length(); i++){
			if(!filter.getTrashListChar().contains(String.valueOf(content.charAt(i)))){
				term += content.charAt(i);
			}
			
			if(content.charAt(i) == ' '){
				if(lowerCase) {
					term = term.trim().toLowerCase();	
				} else {
					term = term.trim();
				}
				
				if(!filter.getTrashListTerms().contains(term)) {
					setTerms.add(term);
				} else {
					System.out.println("[LOG] - Trash word: "+term.toUpperCase());
				}
				
				term = "";
			
			}
		}
		
		setTerms.add(term);
		//TODO retirar o print
		System.out.println("[EXTRACT] ");
		for(String s : setTerms){
			System.out.print(s+" | ");
		}
		
		return setTerms;
	}
	
	public Set<String> semanticFilter(Set<String> setTerms, Set<String> blackListTerms){
		Set<String> keyTerms = new HashSet<String>();
		int flag = 0;
		
		for(String term : setTerms){
			for(String blackTerm : blackListTerms){
				if(term == blackTerm){
					flag = 1;
				}
			}
			
			if(flag == 0){
				keyTerms.add(term);
			}
			
			flag = 0;
		}
		
		return keyTerms;
	}
}
