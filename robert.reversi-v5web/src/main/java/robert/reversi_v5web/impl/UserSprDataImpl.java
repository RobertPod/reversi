package robert.reversi_v5web.impl;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserSprDataImpl {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public void copyUserObj(UserSprDataImpl user) {
		this.name = user.getName();
		this.age = user.getAge();
		this.email = user.getEmail();
		this.pass = user.getPass();
		this.pass2 = user.getPass2();
		this.userId = user.getUserId();
		this.first_log = user.getFirst_log();
		this.last_log = user.getFirst_log();
	}
}
