package chat.chickentalk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class UserStatus {

    @Id
    @Column(name = "user_status_ID")
    @SequenceGenerator(name = "USERSTATUS_SEQ", sequenceName = "USERSTATUS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERSTATUS_SEQ")
    private int id;

    @Column(name = "user_status_name")
    private String name;


    public UserStatus() {
    }

    public UserStatus(String name) {
        super();
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserStatus [id=" + id + ", name=" + name + "]";
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
