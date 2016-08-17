package robert.reversi_v5web.impl;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

import robert.reversi_v5web.api.UserDAO;

@Repository
@Entity
@Table(name="user")
public class UserDAOImpl implements UserDAO {
	@Id
	@GeneratedValue
	private long userID;
	private String name;
	private String email;
	private int age;
	private String pass;
	@GeneratedValue
	private String pass2;
	@GeneratedValue
	private Timestamp first_log;
	private Timestamp last_log;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass2() {
		return pass2;
	}

	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}
	public Timestamp getFirst_log() {
		return first_log;
	}

	public void setFirst_log(Timestamp first_log) {
		this.first_log = first_log;
	}

	public Timestamp getLast_log() {
		return last_log;
	}

	public void setLast_log(Timestamp last_log) {
		this.last_log = last_log;
	}

	public void copyUserObj(UserDAO user) {
		this.name = user.getName();
		this.age = user.getAge();
		this.email = user.getEmail();
		this.pass = user.getPass();
		this.pass2 = user.getPass2();
		this.userID = user.getUserID();
		this.first_log = user.getFirst_log();
		this.last_log = user.getFirst_log();		
	}
}
