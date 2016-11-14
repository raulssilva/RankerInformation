package br.ufrn.imd.rankerinformation.user.model;

import java.util.ArrayList;
import java.util.List;

public class Preferences {
	
	private int id;
	private User user;
	private List<SourceData> listSourceData;
	
	public Preferences() {
		listSourceData = new ArrayList<SourceData>();
	}

	public Preferences(int id, User user, List<SourceData> listSourceData) {
		super();
		this.id = id;
		this.user = user;
		this.listSourceData = listSourceData;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<SourceData> getListSourceData() {
		return listSourceData;
	}

	public void setListSourceData(List<SourceData> listSourceData) {
		this.listSourceData = listSourceData;
	}
	
	public void addSourceData(SourceData sourceData){
		listSourceData.add(sourceData);
	}

}