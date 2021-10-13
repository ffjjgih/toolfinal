package Model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the manageruser database table.
 * 
 */
@Entity
@NamedQuery(name="Manageruser.findAll", query="SELECT m FROM Manageruser m")
public class Manageruser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String gmail;

	private String ho_Ten;

	public Manageruser() {
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGmail() {
		return this.gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getHo_Ten() {
		return this.ho_Ten;
	}

	public void setHo_Ten(String ho_Ten) {
		this.ho_Ten = ho_Ten;
	}

}