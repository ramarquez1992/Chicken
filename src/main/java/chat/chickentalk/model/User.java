package chat.chickentalk.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "CHATUSERS")
public class User implements Serializable{
    private static final long serialVersionUID = 2L;

    @Id
    @Column(name = "userID")
    @SequenceGenerator(name = "CHATUSERID_SEQ", sequenceName = "CHATUSERID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHATUSERID_SEQ")
    int id;

    @Column(name = "email", unique = true, length=100, nullable = false)
    String email;

    @Column(name = "password", length=100, nullable = false)
    String password;

    @Column(name = "firstname", length=100, nullable = false)
    String firstname;

    @Column(name = "lastname", length=100, nullable = false)
    String lastname;

    @Column(name = "avatar")
    String Avatar = "https://s3.amazonaws.com/theresa.d.bucket/c+h+i+c+k+e+n.png"; // user profile image blob.

    @Column(name = "filter", nullable = false)
    int isBaby = 1; // Mature language filter setting. Default 1 for true.

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="status", nullable = false)
    UserStatus status; // User's restriction settings (normal, shadow ban, etc)


    @Column(name = "votes")
    int votesCast = 0; // total number of votes cast from the beginning of time

    @Column(name = "lastloggedin")
    Date lastLoggedIn = new Date(Calendar.getInstance().getTimeInMillis());; // last date time that the user logged in.

    public User() {
    }

    public User(int id, UserStatus status, int votesCast, boolean isBaby, String avatar, String email, String password,
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

    @Override
    public String toString() {
        return "User [id=" + id + ", status=" + status + ", votesCast=" + votesCast + ", isBaby=" + isBaby + ", Avatar="
                + Avatar + ", email=" + email + ", password=" + password + ", firstname=" + firstname + ", lastname="
                + lastname + ", lastLoggedIn=" + lastLoggedIn + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the UserStatus object. NOT IMPLEMENTED: Returning a dummy UserStatus object as a placeholder.
     *
     * @author: Darrin McIntyre
     * @since 2017-06-23
     **/
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Sets the user's status. NOT IMPLEMENTED: Returning -1 as a placeholder.
     *
     * @author: Darrin McIntyre
     * @since 2017-06-23
     **/
    public int setStatus(UserStatus newStatus) {
        status = newStatus;
        return -1;//dao.setUserStatus(id);
    }

    public int getVotesCast() {
        return votesCast;
    }

    public void setVotesCast(int votesCast) {
        this.votesCast = votesCast;
    }

    public boolean isBaby() {
        if (this.isBaby == 1) return true;
        return false;
    }
    
    public int isIntBaby(){
    	return isBaby;
    }

    public void setBaby(boolean isBaby) {
        if (isBaby) this.isBaby = 1;
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

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public Date getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(Date lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

}

