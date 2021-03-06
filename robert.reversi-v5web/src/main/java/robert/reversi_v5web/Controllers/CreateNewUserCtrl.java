package robert.reversi_v5web.Controllers;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import robert.reversi_v5web.domain.ReversiV5Const;
import robert.reversi_v5web.impl.FormularzDTO;
import robert.reversi_v5web.impl.SprDataUserDAO;
import robert.reversi_v5web.services.CreateNewUserService;
import robert.reversi_v5web.services.CurrentJavaSqlTimestamp;
import robert.reversi_v5web.services.EmailService;
// import robert.reversi_v5web.services.RecaptchaServiceImpl;
import robert.reversi_v5web.services.LoginLogoutSessionService;

/**
 * @author Robert
 * @since 1.9.1
 * 
 *        Create new user page
 *
 */
@Controller
@Scope(value = "session")
public class CreateNewUserCtrl {
	final static Logger logger = Logger.getLogger(CreateNewUserCtrl.class.getName());

	@Autowired
	protected SprDataUserDAO userDaoSpr;
	@Autowired
	protected EmailService emailService;
	@Autowired
	protected CreateNewUserService createNewUserService;
	@Autowired
	protected LoginLogoutSessionService loginLogoutSessionService;

	@RequestMapping(value = "/CreateNewUserForm", method = RequestMethod.GET)
	public String createNewUserFormGET(HttpSession session, Model model) {
		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		model.addAttribute("playerName", loginLogoutSessionService.getPlayerString());
		model.addAttribute("messageWlk", createNewUserService.getWelcomeStr() + " (GET)");
		return "/CreateNewUserForm";
	}

	@RequestMapping(value = "/CreateNewUserForm", method = RequestMethod.POST, params = { "submit" })
	public String createNewUserFormUserData(@RequestParam("g-recaptcha-response") String gRecaptchaResponse,
			HttpSession session, HttpServletRequest request, Model model, @ModelAttribute("form") FormularzDTO form) {

		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}
		createNewUserService.getUserDao().setName(form.getName());
		createNewUserService.getUserDao().setEmail(form.getEmail());
		createNewUserService.getUserDao().setPass(form.getPass());
		createNewUserService.getUserDao().setPass2(form.getPass2());
		createNewUserService.getUserDao().setAge(form.getAge() == null ? 0 : form.getAge());
		createNewUserService.getAncillaryData().setAcceptRules(form.isAcceptRules());

		CurrentJavaSqlTimestamp currentJavaSqlTimestamp = new CurrentJavaSqlTimestamp();
		Timestamp current_log = currentJavaSqlTimestamp.getCurrentJavaSqlTimestamp();
		if (createNewUserService.isDataCorrect(gRecaptchaResponse, request)) {
			createNewUserService.saveUserData();
			{
				String emailSubject = "ReversiV5Web - założono konto";
				String emailContent = "Konto założono poprawnie" + ReversiV5Const.EOL + "Username: " + form.getName()
						+ ReversiV5Const.EOL + "User email: " + form.getEmail() + ReversiV5Const.EOL + "User age: "
						+ form.getAge() + ReversiV5Const.EOL + "Data, czas: " + current_log;
				emailService.sendEmail(form.getEmail(), emailSubject, emailContent);
			}
			return "redirect:/NewUserOKForm";
		} else {
			model.addAttribute("messageWlk", createNewUserService.getWelcomeStr() + " (POST)");
			model.addAttribute("playerName", loginLogoutSessionService.getPlayerString());

			model.addAttribute("errorName", createNewUserService.getErrorName());
			model.addAttribute("errorEmail", createNewUserService.getErrorEmail());
			model.addAttribute("errorPass", createNewUserService.getErrorPass());
			model.addAttribute("errorPass2", createNewUserService.getErrorPass2());
			model.addAttribute("errorAccept", createNewUserService.getErrorAccept());
			model.addAttribute("errorHuman", createNewUserService.getErrorHuman());
			// http: //
			// forum.spring.io/forum/spring-projects/web/81471-default-value-for-form-input-element-in-spring
			return "/CreateNewUserForm";
		}

		// userDaoSpr.save(userDao);

		/*
		 * model.addAttribute("message",
		 * "Użytkownik z takim adresem email już istnieje!");
		 * 
		 * { String emailSubject = "ReversiV5Web - użytkownik już istnieje";
		 * String emailContent = "Dla podanego adresu Email konto już istnieje"
		 * + ReversiV5Const.EOL + "Username: " + form.getName() +
		 * ReversiV5Const.EOL + "User email: " + form.getEmail() +
		 * ReversiV5Const.EOL + "User age: " + form.getAge() +
		 * ReversiV5Const.EOL + "Data, czas: " +
		 * currentJavaSqlTimestamp.getCurrentJavaSqlTimestamp();
		 * emailService.sendEmail(form.getEmail(), emailSubject, emailContent);
		 * }
		 */
	}

	@RequestMapping(value = "/CreateNewUserForm", method = RequestMethod.POST, params = { "cancel" })
	public String createNewUserFormEsc(HttpSession session, Model model) {

		return "redirect:/LogginPageForm";
	}

	@RequestMapping(value = "/CreateNewUserForm", method = RequestMethod.POST)
	public String createNewUsrFormErr(HttpSession session, Model model) {
		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}
		return "redirect:/ErrorForm";
	}

	@ModelAttribute("form")
	public FormularzDTO getFormularz() {
		return new FormularzDTO();
	}
}