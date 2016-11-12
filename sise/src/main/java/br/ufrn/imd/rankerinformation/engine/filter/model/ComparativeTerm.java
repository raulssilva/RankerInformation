package br.ufrn.imd.rankerinformation.engine.filter.model;

public class ComparativeTerm {

	private UserPreferences termPreferences;
	private String termsInformation;
	private double weight;
	
	public ComparativeTerm(UserPreferences termPreferences, String termsInformation, double weight) {
		this.termPreferences = termPreferences;
		this.termsInformation = termsInformation;
		this.weight = weight;
	}

	public ComparativeTerm() {
		
	}

	public UserPreferences getTermPreferences() {
		return termPreferences;
	}

	public void setTermPreferences(UserPreferences termPreferences) {
		this.termPreferences = termPreferences;
	}

	public String getTermsInformation() {
		return termsInformation;
	}

	public void setTermsInformation(String termsInformation) {
		this.termsInformation = termsInformation;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	
}
