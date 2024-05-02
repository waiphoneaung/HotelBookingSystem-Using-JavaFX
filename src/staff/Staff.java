package staff;

public class Staff {
	private String id, name, password, position;

	public Staff(String id, String name, String password, String position) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.position = position;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", password=" + password + ", position=" + position + "]";
	}
}