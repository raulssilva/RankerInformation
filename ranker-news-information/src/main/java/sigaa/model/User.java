package sigaa.model;

public class User {
	
	private String name;
	private int idUser;
	private int idStudent;
	private int idMatriculation;
	
	public User() {
	}

	public User(String name, int idUser, int idStudent, int idMatriculation) {
		this.name = name;
		this.idUser = idUser;
		this.idStudent = idStudent;
		this.idMatriculation = idMatriculation;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return idUser;
	}
	
	public void setId(int id) {
		this.idUser = id;
	}

	public int getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}

	public int getIdMatriculation() {
		return idMatriculation;
	}

	public void setIdMatriculation(int idMatriculation) {
		this.idMatriculation = idMatriculation;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", idUser=" + idUser + ", idStudent=" + idStudent + ", idMatriculation="
				+ idMatriculation + "]";
	}
	
}
