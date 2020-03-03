import java.io.Serializable;

public class Actor implements Serializable{
	String classification = null;
	String name = null;
	String password = null;

	public Actor(String classification, String name, String password) {
		super();
		this.classification = classification;
		this.name = name;
		this.password = password;
	}
	
	public Actor(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Actors [classification=" + classification + ", name=" + name + ", password=" + password + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8370970229682532822L;
	
}
