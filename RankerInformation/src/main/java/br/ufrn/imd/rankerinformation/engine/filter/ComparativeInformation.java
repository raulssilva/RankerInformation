package br.ufrn.imd.rankerinformation.engine.filter;

import br.ufrn.imd.rankerinformation.engine.model.Information;
import br.ufrn.imd.rankerinformation.user.model.Preferences;

public class ComparativeInformation {
	Information information;
	Preferences preferences;
	double weight;
	
	public ComparativeInformation() {
	}
	
	public ComparativeInformation(Information information, Preferences preferences) {
		this.information = information;
		this.preferences = preferences;
	}
	
	public Information getInformation() {
		return information;
	}
	public void setInformation(Information information) {
		this.information = information;
	}
	public Preferences getPreferences() {
		return preferences;
	}
	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	

}
