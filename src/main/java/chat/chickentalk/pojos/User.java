package chat.chickentalk.pojos;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CHATUSERS")
public class User {
	@Id
	@Column(name = "userID")
	@SequenceGenerator(name = "USERID_SEQ", sequenceName = "USERID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERID_SEQ")
	int id;
	
	@Column(name = "email")
	String email;
	
	@Column(name = "password")
	String password;
	
	@Column(name = "firstname")
	String firstname;
	
	@Column(name = "lastname")
	String lastname;

	@Column(name = "avatar")
	String Avatar; // user profile image blob.
	
	@Column(name = "filter")
	int isBaby; // Mature language filter setting.
	
	@Column(name = "status")
	int status; // User's restriction settings (normal, shadow ban, etc)
	
	@Column(name = "votes")
	int votesCast; // total number of votes cast from the beginning of time
	
	@Column(name = "lastloggedin")
	Date lastLoggedIn; // last date time that the user logged in.
	
	@Override
	public String toString() {
		return "User [id=" + id + ", status=" + status + ", votesCast=" + votesCast + ", isBaby=" + isBaby + ", Avatar="
				+ Avatar + ", email=" + email + ", password=" + password + ", firstname=" + firstname + ", lastname="
				+ lastname + ", lastLoggedIn=" + lastLoggedIn + "]";
	}

	User(){}

	public User(int id, int status, int votesCast, boolean isBaby, String avatar, String email, String password,
			String firstname, String lastname, Date lastLoggedIn) {
		super();
		this.id = id;
		this.status = status;
		this.votesCast = votesCast;
		setBaby(isBaby);
		Avatar = avatar;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.lastLoggedIn = lastLoggedIn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getVotesCast() {
		return votesCast;
	}

	public void setVotesCast(int votesCast) {
		this.votesCast = votesCast;
	}

	public boolean isBaby() {
		if(this.isBaby == 1) return true;
		return false;
	}

	public void setBaby(boolean isBaby) {
		if(isBaby) this.isBaby = 1;
		else this.isBaby = 0;
	}

	public String getAvatar() {
		return Avatar;
	}

	public void setAvatar(String avatar) {
		Avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getLastLoggedIn() {
		return lastLoggedIn;
	}

	public void setLastLoggedIn(Date lastLoggedIn) {
		this.lastLoggedIn = lastLoggedIn;
	}
	
}
