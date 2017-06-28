package chat.chickentalk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/* USER STATUS TABLE VALUES
 *
 * 0	normal
 * 1	shadow ban
 * 2	permanent ban
 * 3	admin
 * 4	Chicken
 */


@Entity
@Table(name = "USERSTATUS")
public class UserStatus implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_status_ID")
    private int id;

    @Column(name = "user_status_name", unique = true, length=100, nullable = false)
    private String name;


    public UserStatus() {
    }

    public UserStatus(String name) {
        super();
        this.name = name;
    }

    public UserStatus(int id, String name) {
        super();
        this.name = name;
        this.id = id;
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

