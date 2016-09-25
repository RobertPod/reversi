package robert.reversi_v5web.impl;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Robert
 * @version 1.9.4
 * @since 2016-09-25
 * 
 *        Login data. Each login is saving in database
 */
@Entity
@Table(name = "login")
public class LoginsSprDataImpl {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long loginID;
	private long userId;
	private long loginTime;
	private long logoutTime;
	private String loginHostname;
	private String session;
	private String ipAdress;
	private int winGames;
	private int lostGames;

	public long getLoginID() {
		return loginID;
	}

	public void setLoginID(long loginID) {
		this.loginID = loginID;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}

	public long getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(long logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getLoginHostname() {
		return loginHostname;
	}

	public void setLoginHostname(String loginHostname) {
		this.loginHostname = loginHostname;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public int getWinGames() {
		return winGames;
	}

	public void setWinGames(int winGames) {
		this.winGames = winGames;
	}

	public int getLostGames() {
		return lostGames;
	}

	public void setLostGames(int lostGames) {
		this.lostGames = lostGames;
	}

	public void copyLoginsSprDataImpl(LoginsSprDataImpl loginsSprDataImpl) {
		this.loginID = loginsSprDataImpl.loginID;
		this.userId = loginsSprDataImpl.userId;
		this.loginTime = loginsSprDataImpl.loginTime;
		this.logoutTime = loginsSprDataImpl.logoutTime;
		this.loginHostname = loginsSprDataImpl.loginHostname;
		this.session = loginsSprDataImpl.session;
		this.ipAdress = loginsSprDataImpl.ipAdress;
		this.winGames = loginsSprDataImpl.winGames;
		this.lostGames = loginsSprDataImpl.lostGames;
	}
}
