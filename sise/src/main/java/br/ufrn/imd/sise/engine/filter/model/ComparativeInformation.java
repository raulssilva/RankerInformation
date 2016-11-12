package br.ufrn.imd.sise.engine.filter.model;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.sise.engine.model.Information;

public class ComparativeInformation {
	
	private Information information;
	private List<ComparativeTerm> comparativeTerms;
	private double weight;

	public ComparativeInformation(Information information) {
		this.information = information;
		this.comparativeTerms = new ArrayList<ComparativeTerm>();
	}

	public void add(ComparativeTerm compTerm) {
		this.comparativeTerms.add(compTerm);
		//TODO ADICIONAR NA ORDEM DECRESCENTE
	}

	public Information getInformation() {
		return information;
	}

	public void setInformation(Information information) {
		this.information = information;
	}

	public List<ComparativeTerm> getComparativeTerms() {
		return comparativeTerms;
	}

	public void setComparativeTerms(List<ComparativeTerm> comparativeTerms) {
		this.comparativeTerms = comparativeTerms;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	

}
