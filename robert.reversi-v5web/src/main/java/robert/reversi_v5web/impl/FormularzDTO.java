package robert.reversi_v5web.impl;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Robert
 * @version 1.9.4
 * @since 2016-09-25
 * 
 *        Create user data. Is used each time when we contact wth JSP  
 */
public class FormularzDTO {

	// @NotEmpty
	@Size(min = 3)
	private String name;

	@NotEmpty
	@Email
	private String email;

	@Min(18)
	private Integer age;

	// @NotEmpty
	@Size(min = 6)
	private String pass;

	// @NotEmpty
	@Size(min = 6)
	private String pass2;

	private boolean acceptRules;
	private boolean aHuman;

	// gettery i settery
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public boolean isAcceptRules() {
		return acceptRules;
	}

	public void setAcceptRules(boolean acceptRules) {
		this.acceptRules = acceptRules;
	}

	public boolean isaHuman() {
		return aHuman;
	}

	public void setaHuman(boolean aHuman) {
		this.aHuman = aHuman;
	}

}