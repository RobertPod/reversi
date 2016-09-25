package robert.reversi_v5web.services;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.persistence.GeneratedValue;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import robert.reversi_v2.domain.CellCollor;
import robert.reversi_v5web.Controllers.LogginPageCtrl;
import robert.reversi_v5web.domain.ReallyStrongSecuredPassword;
import robert.reversi_v5web.impl.LoginsSprDataDAO;
import robert.reversi_v5web.impl.LoginsSprDataImpl;
import robert.reversi_v5web.impl.SprDataUserDAO;
import robert.reversi_v5web.impl.UserSprDataImpl;

/**
 * @author Robert
 *
 */
@Service
@Scope(value = "session")
public class LoginLogoutSessionService {
	final Logger logger = Logger.getLogger(LogginPageCtrl.class.getName());

	@Autowired
	protected SprDataUserDAO userDaoSpr;
	@Autowired
	protected LoginsSprDataDAO loginsSprDataDAO;

	private LoginsSprDataImpl loginsSprDataImpl = new LoginsSprDataImpl();
	private AdditionalUserData additionalUserData = new AdditionalUserData();

	private Boolean newSession = true;
	private Boolean isLogin = false;
	private String session = "";
	private Long playerID = -1l;
	private String player = "niezalogowany";
	private CellCollor myColor = CellCollor.RED;
	private String againstPlayer = "";
	private String ipAdress = "";

	/*
	 * data need to display. It is set from database user record @see
	 * UserSprDataImpl or count during create user session to minimalize access
	 * to database
	 */
	public class AdditionalUserData {
		private String email;
		private Timestamp first_log;
		private int winGames;
		private int lostGames;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Timestamp getFirst_log() {
			return first_log;
		}

		public void setFirst_log(Timestamp first_log) {
			this.first_log = first_log;
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

	public boolean isNewSession() {
		return newSession;
	}

	public void setNewSession(boolean newSession) {
		this.newSession = newSession;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public Long getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Long playerID) {
		this.playerID = playerID;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getAgainstPlayer() {
		return againstPlayer;
	}

	public void setAgainstPlayer(String againstPlayer) {
		this.againstPlayer = againstPlayer;
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public CellCollor getMyColor() {
		return myColor;
	}

	public void setMyColor(CellCollor myColor) {
		this.myColor = myColor;
	}

	public AdditionalUserData getAdditionalUserData() {
		return additionalUserData;
	}

	// localtion.href.replace
	public String getPlayerString() {
		if (getMyColor() == CellCollor.RED)
			return "<span style='font-size: 150%; color: red;'><strong>&nbsp;&nbsp;" + getPlayer()
					+ "&nbsp;&nbsp;</strong></span>";
		else
			return "<span style='font-size: 150%; color: black;'><strong>&nbsp;&nbsp;" + getPlayer()
					+ "&nbsp;&nbsp;</strong></span>";
	}

	public String getLoginHostname() {
		return loginsSprDataImpl.getLoginHostname();
	}

	public int getSessionGameWin() {
		return loginsSprDataImpl.getWinGames();
	}

	public void setSessionGameWin(int winGames) {
		loginsSprDataImpl.setWinGames(winGames);
	}

	public int getSessionGameLost() {
		return loginsSprDataImpl.getLostGames();
	}

	public void setSessionGameLost(int lostGames) {
		loginsSprDataImpl.setLostGames(lostGames);
	}

	/*
	 * true - all is OK - is new sesssion or save session is continued false -
	 * changeed session or ip
	 */
	public boolean checkSession(HttpSession session) {
		try {
			logger.info(player + " " + session.getId() + " " + this.session + " ");
			if (this.newSession) {
				setNewSession(session);
				return true;
			} else { // is not new session
				if (!this.session.equals(session.getId()) || !this.ipAdress.equals(getIP1())) {
					if (this.session.equals(session.getId()))
						session.invalidate();
					setNewSession(session);
					return false;
				}
			}
			// logger.info("getRemoteHost " + request.getRemoteHost());
			// logger.info("getAuthType " + request.getAuthType());
			// logger.info("getLocalAddr " + request.getLocalAddr());
		} catch (IllegalStateException e) {
			return false;
		}
		if (this.isLogin) {
			if ((loginsSprDataImpl.getSession() == null) || !loginsSprDataImpl.getSession().equals(session.getId())) {
				List<LoginsSprDataImpl> currentSession = loginsSprDataDAO.findBySession(session.getId());
				if (!currentSession.isEmpty()) {
					if (currentSession.size() > 1) {
						logger.error("Rekordy z tym samym numere sesji: " + currentSession.size());
					} else {
						loginsSprDataImpl.copyLoginsSprDataImpl(currentSession.get(0));
					}
				}
			}
			loginsSprDataImpl.setLogoutTime(session.getLastAccessedTime());
			loginsSprDataDAO.save(loginsSprDataImpl);
		}
		return true;
	}

	public boolean setNewSession(HttpSession session) {
		try {
			this.session = session.getId();

			this.newSession = false;
			this.isLogin = false;
			this.playerID = -1l;
			this.player = "niezalogowany";
			this.myColor = CellCollor.RED;
			this.againstPlayer = "";
			this.ipAdress = getIP1();
			this.additionalUserData.winGames = 0; // count it in the future
			this.additionalUserData.lostGames = 0; // count it in the future
		} catch (IllegalStateException e) {
			return false;
		}

		return true;
	}

	// public boolean saveSession(HttpSession session) {
	// if (!checkSession(session))
	// return false;
	//
	// try {
	// session.setAttribute("isLogin", this.isLogin);
	// session.setAttribute("session", this.session);
	// session.setAttribute("playerID", this.playerID);
	// session.setAttribute("player", this.player);
	// session.setAttribute("myColor", this.myColor);
	// session.setAttribute("againstPlayer", this.againstPlayer);
	// session.setAttribute("ipAdress", this.ipAdress);
	// } catch (IllegalStateException e) {
	// return false;
	// }
	//
	// return true;
	// }
	//
	// public boolean removeSession(HttpSession session) {
	// if (!checkSession(session))
	// return false;
	//
	// try {
	// session.removeAttribute("isLogin");
	// session.removeAttribute("session");
	// session.removeAttribute("playerID");
	// session.removeAttribute("player");
	// session.removeAttribute("myColor");
	// session.removeAttribute("againstPlayer");
	// session.removeAttribute("ipAdress");
	// } catch (IllegalStateException e) {
	// return false;
	// }
	//
	// return true;
	// }
	//
	// public boolean loadSession(HttpSession session) {
	// try {
	// this.isLogin = (Boolean) session.getAttribute("isLogin");
	// this.session = (String) session.getAttribute("session");
	// this.playerID = (Long) session.getAttribute("playerID");
	// this.player = (String) session.getAttribute("player");
	// this.myColor = (CellCollor) session.getAttribute("myColor");
	// this.againstPlayer = (String) session.getAttribute("againstPlayer");
	// this.ipAdress = (String) session.getAttribute("ipAdress");
	// this.newSession = false;
	// } catch (IllegalStateException e) {
	// return false;
	// }
	//
	// return checkSession(session);
	// }

	public boolean isNewPlayerUserID(HttpSession session) {
		return this.playerID != (Long) session.getAttribute("playerID");

	}

	public boolean loginToGame(String email, String pass) {
		boolean isPassIdentically = false;

		List<UserSprDataImpl> searchResList = userDaoSpr.findByEmail(email);
		if (CollectionUtils.isEmpty(searchResList)) {
			return false;
		} else {
			try {
				isPassIdentically = ReallyStrongSecuredPassword.validatePassword(pass, searchResList.get(0).getPass());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!isPassIdentically) {
				return false;
			}
		}
		this.isLogin = true;
		this.player = searchResList.get(0).getName();
		this.playerID = searchResList.get(0).getUserId();

		additionalUserData.email = searchResList.get(0).getEmail();
		additionalUserData.first_log = searchResList.get(0).getFirst_log();
		additionalUserData.winGames = searchResList.get(0).getWinGames(); // probably
																			// need
																			// to
																			// calculate
																			// the
																			// variable
		additionalUserData.lostGames = searchResList.get(0).getLostGames(); // probably
																			// need
																			// to
																			// calculate
																			// the
																			// variable

		return true;
	}

	public void logoutFromGame(HttpSession session) {

		loginsSprDataImpl.setLogoutTime(session.getLastAccessedTime());
		loginsSprDataDAO.save(loginsSprDataImpl);

		try {
			this.newSession = true;
			this.isLogin = false;
			this.playerID = -1l;
			this.player = "niezalogowany";
			this.myColor = CellCollor.RED;
			this.againstPlayer = "";
			this.ipAdress = getIP1();
			this.session = session.getId();
			session.invalidate();
		} catch (IllegalStateException e) {
			;
		}
	}

	public boolean saveNewSession(HttpSession session, HttpServletRequest request) {

		// private long loginID;
		if (!this.isLogin)
			return false;

		loginsSprDataImpl.setUserId(this.playerID);
		loginsSprDataImpl.setLoginTime(session.getCreationTime());
		loginsSprDataImpl.setLogoutTime(session.getLastAccessedTime());
		loginsSprDataImpl.setLoginHostname(request.getRemoteHost());
		loginsSprDataImpl.setSession(this.session);
		loginsSprDataImpl.setIpAdress(this.ipAdress);
		loginsSprDataImpl.setWinGames(0);
		loginsSprDataImpl.setLostGames(0);

		loginsSprDataDAO.save(loginsSprDataImpl);

		return true;
	}

	/*
	 * all IP metods by
	 * http://stackoverflow.com/questions/8083479/java-getting-my-ip-address
	 */
	private String getIP() {
		String ip = null;

		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface iface = interfaces.nextElement();
				// filters out 127.0.0.1 and inactive interfaces
				if (iface.isLoopback() || !iface.isUp())
					continue;

				Enumeration<InetAddress> addresses = iface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress addr = addresses.nextElement();
					ip = addr.getHostAddress();
					// System.out.println(iface.getDisplayName() + " " + ip);
					logger.info(iface.getDisplayName() + " " + ip);

				}
			}
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
		return ip;
	}

	private String getIP1() {
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface iface = interfaces.nextElement();
				if (iface.isLoopback() || !iface.isUp() || iface.isVirtual() || iface.isPointToPoint())
					continue;

				Enumeration<InetAddress> addresses = iface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress addr = addresses.nextElement();

					final String ip = addr.getHostAddress();
					if (Inet4Address.class == addr.getClass()) {
						logger.info("myIP" + " " + ip);
						return ip;
					}
				}
			}
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	private String getIP2() { // not work
		String ip = null;
		InetAddress[] localaddr;

		try {
			localaddr = InetAddress.getAllByName("host.name");

			for (int i = 0; i < localaddr.length; i++) {

				// System.out.println("\n" + localaddr[i].getHostAddress());
				logger.info(localaddr[i].getHostAddress());

			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}
}
