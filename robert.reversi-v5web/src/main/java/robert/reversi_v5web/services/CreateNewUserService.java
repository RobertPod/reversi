package robert.reversi_v5web.services;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import robert.reversi_v5web.domain.ReversiV5Const;
import robert.reversi_v5web.impl.UserSprDataImpl;

@Service
// @Scope(value = "session")
public class CreateNewUserService {
	final static Logger logger = Logger.getLogger(CreateNewUserService.class.getName());
	private UserSprDataImpl userDao;
	private AncillaryData ancillaryData;

	private String welcomeStr = "<p style=''>Podaj dane rejestracyjne.</p>"
			+ "<p style=''>Rejestracja i logowanie umożliwi zapisywanie Twoich wyników i grę z innymi</p>"
			+ "<p style=''>Po uruchomieniu wersji mobilnej będzie ten sam login i hasło</p>";
	private String errorName = "";
	private String errorEmail = "";
	private String errorPass = "";
	private String errorAccept = "";
	private String errorHuman = "";

	public class AncillaryData {
		private boolean acceptRules;
		private boolean aHuman;

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

	public CreateNewUserService() {
		super();
		this.userDao = new UserSprDataImpl();
		this.ancillaryData = new AncillaryData();
	}

	public String getWelcomeStr() {
		return welcomeStr;
	}

	public void setWelcomeStr(String welcomeStr) {
		this.welcomeStr = welcomeStr;
	}

	public UserSprDataImpl getUserDao() {
		return userDao;
	}

	public AncillaryData getAncillaryData() {
		return ancillaryData;
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	public String getErrorEmail() {
		return errorEmail;
	}

	public void setErrorEmail(String errorEmail) {
		this.errorEmail = errorEmail;
	}

	public String getErrorPass() {
		return errorPass;
	}

	public void setErrorPass(String errorPass) {
		this.errorPass = errorPass;
	}

	public String getErrorAccept() {
		return errorAccept;
	}

	public void setErrorAccept(String errorAccept) {
		this.errorAccept = errorAccept;
	}

	public String getErrorHuman() {
		return errorHuman;
	}

	public void setErrorHuman(String errorHuman) {
		this.errorHuman = errorHuman;
	}

	public boolean isDataCorrect() {
		logger.info("Name: " + userDao.getName());
		if (checkName(userDao.getName())) {

		}
		logger.info("Email: " + userDao.getEmail());
		logger.info("Pass: " + userDao.getPass());
		logger.info("Pass2: " + userDao.getPass2());
		logger.info("isAcceptRules: " + ancillaryData.isAcceptRules());
		logger.info("isHuman: " + ancillaryData.isaHuman());
		return false;
	}

	private boolean checkName(String name) {
		if (name.length() < 3 || name.length() > 20) {
			errorName = "<span style='color: red;'>Nazwa użytkownika musi mieć nie mniej niż 3 znaki.</span>";
			return false;
		} else {
			errorName = "<span style='color: green;'>OK</span>";
		}
		return true;
	}

	public void saveUserData() {
		// TODO Auto-generated method stub

	}
}
