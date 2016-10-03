package robert.reversi_v5web.impl;

/**
 * @author Robert
 * @since 2.0.2
 * 
 *        Display last session. Will be modyfy.
 */
public class SessionDisplayDataDTO {
	// private long loginID;
	// private long userId;
	private String userName;
	// private long loginTime;
	private String loginTime;
	// private long logoutTime;
	private String logoutTime;
	private String loginHostname;
	// private String session;
	private String ipAdress;
	private int winGames;
	private int lostGames;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getLoginHostname() {
		return loginHostname;
	}

	public void setLoginHostname(String loginHostname) {
		this.loginHostname = loginHostname;
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

}
