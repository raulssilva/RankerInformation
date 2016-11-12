package br.ufrn.imd.sise.engine.filter.model;

public class UserPreferences {
	
	private String termo;
	private int repeticoes;
	private String matUsuario; //REFERENCES Usuario(matricula) 

	public UserPreferences(){
		this.termo = "";
		this.repeticoes = 0;
		this.matUsuario = "";
	}
	
	public UserPreferences(String n_termo, String n_matUsuario){
		this.termo = n_termo;
		this.repeticoes = 1;
		this.matUsuario = n_matUsuario;
	}
	
	public void setTermo(String n_termo){
		this.termo = n_termo;
	}
	
	public void setRepeticoes(int n_repeticoes){
		this.repeticoes = n_repeticoes;
	}
	
	public void setMatUsuario(String n_matUsuario){
		this.matUsuario = n_matUsuario;
	}
	
	public String getTermo(){
		return this.termo;
	}
	
	public int getRepeticoes(){
		return this.repeticoes;
	}
	
	public String getMatUsuario(){
		return this.matUsuario;
	}
	
	public void addQuantidade() {
		setRepeticoes(getRepeticoes() + 1);
	}
	
}
