package br.ufrn.imd.rankerinformation.engine.filter;

import java.util.ArrayList;
import java.util.List;

public class Filter {
	
	private String trashListChar;
	private List<String> trashListTerms;
	
	public Filter(){
		trashListChar = "! @#$%&*()_-+=,.:;{}[]|/0123456789" + "\"" + "\\" + "\'";
		trashListTerms = new ArrayList<String>();
	}
	
	public Filter(String trashListChar, List<String> trashListTerms){
		this.trashListChar = trashListChar;
		this.trashListTerms = trashListTerms;
	}
	
	public String getTrashListChar() {
		return trashListChar;
	}

	public void setTrashListChar(String trashListChar) {
		this.trashListChar = trashListChar;
	}

	public List<String> getTrashListTerms() {
		return trashListTerms;
	}

	public void setTrashListTerms(List<String> trashListTerms) {
		this.trashListTerms = trashListTerms;
	}
	
	public void addTrashChar(String trashChar) {
		this.trashListChar += trashChar;
	}
	
	public void addTrashChar(char trashChar) {
		this.trashListChar += trashChar;
	}
	
	public void addTrashListTerm(String term) {
		this.trashListTerms.add(term);
	}

}
