package robert.reversi_v5web.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.GeneratedValue;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import robert.reversi_v5web.impl.SprDataUserDAO;
import robert.reversi_v5web.impl.UserSprDataImpl;
import robert.reversi_v5web.domain.ReallyStrongSecuredPassword;

@Service
// @Scope(value = "session")
public class CreateNewUserService {
	final static Logger logger = Logger.getLogger(CreateNewUserService.class.getName());
	@Autowired
	protected SprDataUserDAO userDaoSpr;

	private UserSprDataImpl userDao;
	private AncillaryData ancillaryData;

	private String welcomeStr = "Podaj dane rejestracyjne. "
			+ "Rejestracja i logowanie umożliwi zapisywanie Twoich wyników<br />i grę z innymi. "
			+ "Po uruchomieniu wersji mobilnej będzie ten sam login i hasło";
	private String errorName = "";
	private String errorEmail = "";
	private String errorPass = "";
	private String errorPass2 = "";
	private String errorAccept = "";
	private String errorHuman = "";
	private String hashPass;

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

	public class CaptchaResponsePOJO {
		public boolean success;
		public String challenge_ts;
		public String hostname;
		public String[] error_codes;

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
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

	public String getErrorPass2() {
		return errorPass2;
	}

	public void setErrorPass2(String errorPass2) {
		this.errorPass2 = errorPass2;
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

	public boolean isDataCorrect(String gRecaptchaResponse, HttpServletRequest request) {
		// logger.info("Name: " + userDao.getName());
		boolean returnV = checkName(userDao.getName());

		// logger.info("Email: " + userDao.getEmail());
		returnV = checkEmail(userDao.getEmail()) ? returnV : false;

		logger.info("Pass: " + userDao.getPass());
		logger.info("Pass2: " + userDao.getPass2());
		try {
			hashPass = ReallyStrongSecuredPassword.generateStorngPasswordHash(userDao.getPass());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("hashPass: " + hashPass);
		boolean isPassIdentically = false;
		try {
			isPassIdentically = ReallyStrongSecuredPassword.validatePassword(userDao.getPass2(), hashPass);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Pass == Pass2?: " + isPassIdentically);
		returnV = checkPass(userDao.getPass(), userDao.getPass2()) ? returnV : false;

		// logger.info("isAcceptRules: " + ancillaryData.isAcceptRules());
		returnV = checkAcceptRules(ancillaryData.isAcceptRules()) ? returnV : false;

		// logger.info("isHuman: " + ancillaryData.isaHuman());
		returnV = checkHuman(ancillaryData.isaHuman(), gRecaptchaResponse, request) ? returnV : false;

		if (returnV) {
			userDao.setPass(hashPass);
			userDao.setPass2("");
			CurrentJavaSqlTimestamp currentJavaSqlTimestamp = new CurrentJavaSqlTimestamp();
			Timestamp current_log = currentJavaSqlTimestamp.getCurrentJavaSqlTimestamp();
			logger.info("Timestamp: " + current_log);
			userDao.setFirst_log(current_log);
			userDao.setLast_log(current_log);
		}
		return returnV;

	}

	private boolean checkHuman(boolean isaHuman, String gRecaptchaResponse, HttpServletRequest request) {
		String secretParameter = "6Lda5ikTAAAAAOB53l102dF3h0rX-6HzSHfeJ-TT";

		URL url = null;
		try {
			url = new URL("https://www.google.com/recaptcha/api/siteverify?secret=" + secretParameter + "&response="
					+ gRecaptchaResponse + "&remoteip=" + request.getRemoteAddr());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.setRequestMethod("GET");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line, outputString = "";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while ((line = reader.readLine()) != null) {
				outputString += line;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(outputString);
		logger.info("reCaptcha: " + outputString);

		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		CaptchaResponsePOJO capRes = gson.fromJson(outputString, CaptchaResponsePOJO.class);

		// Verify whether the input from Human or Robot
		if (capRes.isSuccess()) {
			// Input by Human
			userDao.setFirstHostname(capRes.hostname);
			errorHuman = "<span style='color: green;'>OK</span>";
			logger.info("hostname: " + capRes.hostname);

			return true;
		} else {
			errorHuman = "<span style='color: red;'>Musisz być człowiekiem!</span>";
		}
		return false;
	}

	private boolean checkAcceptRules(boolean acceptRules) {
		if (acceptRules) {
			errorAccept = "<span style='color: green;'>OK</span>";
			return true;
		}
		errorAccept = "<span style='color: red;'>Musisz zaakceptować regulamin!</span>";
		return false;
	}

	private boolean checkPass(String pass, String pass2) {
		String regex = "^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!&]).{6,20})$";
		// ( # Start of group
		// (?=.*\d) # must contains one digit from 0-9
		// (?=.*[a-z]) # must contains one lowercase characters
		// (?=.*[A-Z]) # must contains one uppercase characters
		// (?=.*[@#$%]) # must contains one special symbols in the list "@#$%"
		// . # match anything with previous condition checking
		// {6,20} # length at least 6 characters and maximum of 20
		// ) # End of group
		// by
		// https://www.mkyong.com/regular-expressions/how-to-validate-password-with-regular-expression/
		String regex1 = "^((?=.*\\d).{6,20})$";
		String regex2 = "^((?=.*[a-z]).{6,20})$";
		String regex3 = "^((?=.*[A-Z]).{6,20})$";
		String regex4 = "^((?=.*[@#$%!&]).{6,20})$";

		if (Pattern.matches(regex, pass)) {
			errorPass = "<span style='color: green;'>OK</span>";
			if (pass.equals(pass2)) {
				errorPass2 = "<span style='color: green;'>OK</span>";
				return true;
			} else {
				errorPass2 = "<span style='color: red;'>Oba hasła muszą być identyczne.</span>";
			}
		} else {
			errorPass2 = "";
			if (pass.length() < 6) {
				errorPass = "<span style='color: red;'>Hasło musi mieć przynajmniej 6 znaków.</span>";
			} else if (pass.length() > 20) {
				errorPass = "<span style='color: red;'>Hasło musi mieć nie więcej niż 20 znaków.</span>";
			} else if (!Pattern.matches(regex1, pass)) {
				errorPass = "<span style='color: red;'>Hasło musi mieć przynajmniej jedną cyfrę.</span>";
			} else if (!Pattern.matches(regex2, pass)) {
				errorPass = "<span style='color: red;'>Hasło musi mieć przynajmniej jedną małą literę.</span>";
			} else if (!Pattern.matches(regex3, pass)) {
				errorPass = "<span style='color: red;'>Hasło musi mieć przynajmniej jedną wielką literę.</span>";
			} else if (!Pattern.matches(regex4, pass)) {
				errorPass = "<span style='color: red;'>Hasło musi mieć przynajmniej jeden ze znaków '@#$%!&'.</span>";
			}
		}
		return false;
	}

	private boolean checkEmail(String email) {
		// I know that use regular expressions to check email is not recomended
		// but is sexy
		String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		// Pattern pattern;
		// Matcher matcher;
		//
		// pattern = Pattern.compile(regex);
		// matcher = pattern.matcher(email);
		//
		// if (matcher.matches()) {
		// or more simple
		if (Pattern.matches(regex, email)) {
			List<UserSprDataImpl> searchResList = userDaoSpr.findByEmail(email);
			if (CollectionUtils.isEmpty(searchResList)) {
				errorEmail = "<span style='color: green;'>OK</span>";
				return true;
			} else {
				errorEmail = "<span style='color: red;'>Jest już użytkownik o takim adresie email.</span>";
			}
		} else {
			if (email.length() == 0) {
				errorEmail = "<span style='color: red;'>Musisz podać adres email, formalnie poprawny, może być fikcyjny.</span>";
			} else {
				errorEmail = "<span style='color: red;'>To nie jest adres email. Wpisz ciąg o strukturze abc@def.ghi.</span>";
			}
		}
		return false;
	}

	private boolean checkName(String name) {
		// if (name.length() < 3 || name.length() > 20) {
		// or
		// Pattern pattern = Pattern.compile("^.{3,20}");
		// Matcher matcher = pattern.matcher(name.trim());
		// if (!matcher.matches()) {
		// or
		if (Pattern.matches("^.{3,20}$", name.trim())) {
			errorName = "<span style='color: green;'>OK</span>";
			return true;
		} else {
			if (name.length() == 0)
				errorName = "<span style='color: red;'>Musisz podać swój identyfikator.</span>";
			else
				errorName = "<span style='color: red;'>Nazwa użytkownika musi mieć nie mniej niż 3 znaki.</span>";
		}
		return false;
	}

	public void saveUserData() {
		userDaoSpr.save(userDao);
	}
}
