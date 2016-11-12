package br.ufrn.imd.sise.user.model;

public class CourseClass {
	
	private int id;
	private Subject subject;
	private String description;
	
	public CourseClass() {
		super();
	}
	
	public CourseClass(int id, Subject subject, String description) {
		super();
		this.id = id;
		this.subject = subject;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "CourseClass [id=" + id + ", subject=" + subject + ", description=" + description + "]";
	}

}