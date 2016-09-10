package robert.reversi_v5web.Controllers;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import robert.reversi_v5web.domain.ReversiV5Const;
import robert.reversi_v5web.impl.FormularzDTO;
import robert.reversi_v5web.impl.SprDataUserDAO;
import robert.reversi_v5web.impl.UserSprDataImpl;
import robert.reversi_v5web.services.CreateNewUserService;
import robert.reversi_v5web.services.CurrentJavaSqlTimestamp;
import robert.reversi_v5web.services.EmailService;

@Controller
// @Scope(value = "session")
public class CreateNewUserCtrl {
	// String eol = System.getProperty("line.separator");
	// private UserSprDataImpl userDao;

	@Autowired
	protected SprDataUserDAO userDaoSpr;
	@Autowired
	protected CurrentJavaSqlTimestamp currentJavaSqlTimestamp;
	@Autowired
	protected EmailService emailService;
	@Autowired
	protected CreateNewUserService createNewUserService;

	@RequestMapping(value = "/createNewUserForm", method = RequestMethod.GET)
	public String createNnewUserFormGET(HttpSession session, Model model) {
		if (session.isNew()) {
			return "redirect:/startForm";
		}

		model.addAttribute("messageWlk", createNewUserService.getWelcomeStr() + "GET");
		return "createNewUserForm";
	}

	@RequestMapping(value = "/createNewUserForm", method = RequestMethod.POST, params = { "submit" })
	public String createNewUserFormUserData(HttpSession session, Model model,
			@ModelAttribute("form") FormularzDTO form) {

		if (session.isNew()) {
			return "redirect:/startForm";
		}

		// List<UserSprDataImpl> searchResList =
		// userDaoSpr.findByEmail(form.getEmail());
		// if (CollectionUtils.isEmpty(searchResList)) {
		// formularz wypełniony prawidłowo

		createNewUserService.getUserDao().setName(form.getName());
		createNewUserService.getUserDao().setEmail(form.getEmail());
		createNewUserService.getUserDao().setPass(form.getPass());
		createNewUserService.getUserDao().setPass2(form.getPass2());
		createNewUserService.getUserDao().setAge(form.getAge() == null ? 0 : form.getAge());
		createNewUserService.getAncillaryData().setAcceptRules(form.isAcceptRules());
		createNewUserService.getAncillaryData().setaHuman(form.isaHuman());

		Timestamp current_log = currentJavaSqlTimestamp.getCurrentJavaSqlTimestamp();
		// userDao.setFirst_log(current_log);
		// userDao.setLast_log(current_log);

		if (createNewUserService.isDataCorrect()) {
			createNewUserService.saveUserData();
			{
				String emailSubject = "ReversiV5Web - założono konto";
				String emailContent = "Konto założono poprawnie" + ReversiV5Const.EOL + "Username: " + form.getName()
						+ ReversiV5Const.EOL + "User email: " + form.getEmail() + ReversiV5Const.EOL + "User age: "
						+ form.getAge() + ReversiV5Const.EOL + "Data, czas: " + current_log;
				emailService.sendEmail(form.getEmail(), emailSubject, emailContent);
			}
			return "redirect:/newUserOKForm";
		} else {
			model.addAttribute("messageWlk", createNewUserService.getWelcomeStr() + "POST");
			model.addAttribute("errorName", createNewUserService.getErrorName());
			http: // forum.spring.io/forum/spring-projects/web/81471-default-value-for-form-input-element-in-spring
			return "createNewUserForm";
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

	@RequestMapping(value = "/createNewUserForm", method = RequestMethod.POST, params = { "cancel" })
	public String createNewUserFormEsc(HttpSession session, Model model) {

		return "redirect:/startForm";
	}

	@RequestMapping(value = "/createNewUserForm", method = RequestMethod.POST)
	public String createNewUsrFormErr(HttpSession session, Model model) {
		if (session.isNew()) {
			return "redirect:/startForm";
		}
		return "redirect:/errorForm";
	}

	@ModelAttribute("form")
	public FormularzDTO getFormularz() {
		return new FormularzDTO();
	}
}