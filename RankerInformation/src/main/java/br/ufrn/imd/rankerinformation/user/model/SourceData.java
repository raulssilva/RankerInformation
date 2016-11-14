package br.ufrn.imd.rankerinformation.user.model;

public class SourceData {
	private int id;
	private String content;
	private double weight;
	
	public SourceData() {
		
	}

	public SourceData(int id, String content, double weight) {
		this.id = id;
		this.content = content;
		this.weight = weight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
