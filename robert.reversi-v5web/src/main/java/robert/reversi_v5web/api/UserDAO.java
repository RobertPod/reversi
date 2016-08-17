package robert.reversi_v5web.api;

import java.sql.Timestamp;

public interface UserDAO {
	String getName();

	void setName(String name);

	int getAge();

	void setAge(int age);

	String getEmail();

	void setEmail(String email);

	String getPass();

	void setPass(String pass);

	String getPass2();

	void setPass2(String pass2);

	long getUserID();

	void setUserID(long userID);

	Timestamp getFirst_log();

	void setFirst_log(Timestamp first_log);

	Timestamp getLast_log();

	void setLast_log(Timestamp last_log);

	public void copyUserObj(UserDAO user);
}
