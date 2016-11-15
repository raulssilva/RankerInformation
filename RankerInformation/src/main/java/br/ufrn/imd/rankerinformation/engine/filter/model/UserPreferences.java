package br.ufrn.imd.rankerinformation.engine.filter.model;

public class UserPreferences {
	
	private String termo;
	private int repeticoes;

	public UserPreferences(){
		this.termo = "";
		this.repeticoes = 0;
	}
	
	public UserPreferences(String n_termo){
		this.termo = n_termo;
		this.repeticoes = 1;
	}
	
	public void setTermo(String n_termo){
		this.termo = n_termo;
	}
	
	public void setRepeticoes(int n_repeticoes){
		this.repeticoes = n_repeticoes;
	}
	
	public String getTermo(){
		return this.termo;
	}
	
	public int getRepeticoes(){
		return this.repeticoes;
	}
	
	public void addQuantidade() {
		setRepeticoes(getRepeticoes() + 1);
	}

	@Override
	public String toString() {
		return "UserPreferences [termo=" + termo + ", repeticoes=" + repeticoes + "]";
	}
	
	
	
	
}
