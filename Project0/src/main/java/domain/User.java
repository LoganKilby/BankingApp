package domain;

public class User {

	private int id;
	private String first_name;
	private String last_name;
	private String email;
	private String username;
	private String password;
	private int salary;
	private String title;
	
	public User() {
		super();
	}
	
	public User(int id, String first_name, String last_name, String username, String password, String email, int salary, String title) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.salary = salary;
		this.title = title;
	}
	
	public User(int id, String first_name, String last_name, String email, String username, String password) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", email=" + email + ", username=" + username + ", password=" + password;
	}

	public int getId() {
		return id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getSalary() {
		return salary;
	}

	public String getTitle() {
		return title;
	}
	
	

}
