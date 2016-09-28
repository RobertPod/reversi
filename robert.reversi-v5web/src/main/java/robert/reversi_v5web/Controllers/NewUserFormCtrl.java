package robert.reversi_v5web.Controllers;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import robert.reversi_v5web.services.CurrentJavaSqlTimestamp;
import robert.reversi_v5web.services.EmailService;

@Controller
public class NewUserFormCtrl {
	// String eol = System.getProperty("line.separator");
	private UserSprDataImpl userDao;

	@Autowired
	protected SprDataUserDAO userDaoSpr;
	@Autowired
	protected EmailService emailService;

	@RequestMapping(value = "/NewUserForm", method = RequestMethod.GET)
	public String newUserFormGET(HttpSession session, Model model) {
		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		model.addAttribute("message", "Cześć. Wypełnij proszę formularz.");
		return "/NewUserForm";
	}

	@RequestMapping(value = "/NewUserForm", method = RequestMethod.POST, params = { "submit" })
	public String newUserFormUserData(HttpSession session, Model model,
			@ModelAttribute("form") @Valid FormularzDTO form, BindingResult result) {

		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		if (result.hasErrors()) {
			// formularz nie jest uzupełniony prawidłowo
			model.addAttribute("message", "Bardzo proszę! Popraw blędy!");
			return "/NewUserForm";
		}
		List<UserSprDataImpl> searchResList = userDaoSpr.findByEmail(form.getEmail());
		if (CollectionUtils.isEmpty(searchResList)) {
			// formularz wypełniony prawidłowo
			userDao = new UserSprDataImpl();
			userDao.setName(form.getName());
			userDao.setEmail(form.getEmail());
			userDao.setPass(form.getPass());
			// userDao.setPass2(form.getPass2()); - nie ma sensu wpisywać
			userDao.setAge(form.getAge() == null ? 0 : form.getAge());
			CurrentJavaSqlTimestamp currentJavaSqlTimestamp = new CurrentJavaSqlTimestamp();
			Timestamp current_log = currentJavaSqlTimestamp.getCurrentJavaSqlTimestamp();
			userDao.setFirst_log(current_log);
			userDao.setLast_log(current_log);
			userDaoSpr.save(userDao);
			{
				String emailSubject = "ReversiV5Web - założono konto";
				String emailContent = "Konto założono poprawnie" + ReversiV5Const.EOL + "Username: " + form.getName()
						+ ReversiV5Const.EOL + "User email: " + form.getEmail() + ReversiV5Const.EOL + "User age: "
						+ form.getAge() + ReversiV5Const.EOL + "Data, czas: " + current_log;
				emailService.sendEmail(form.getEmail(), emailSubject, emailContent);
			}
			return "redirect:/NewUserOKForm";
		}
		model.addAttribute("message", "Użytkownik z takim adresem email już istnieje!");
		{
			CurrentJavaSqlTimestamp currentJavaSqlTimestamp = new CurrentJavaSqlTimestamp();
			String emailSubject = "ReversiV5Web - użytkownik już istnieje";
			String emailContent = "Dla podanego adresu Email konto już istnieje" + ReversiV5Const.EOL + "Username: "
					+ form.getName() + ReversiV5Const.EOL + "User email: " + form.getEmail() + ReversiV5Const.EOL
					+ "User age: " + form.getAge() + ReversiV5Const.EOL + "Data, czas: "
					+ currentJavaSqlTimestamp.getCurrentJavaSqlTimestamp();
			emailService.sendEmail(form.getEmail(), emailSubject, emailContent);
		}
		return "/NewUserForm";
	}

	@RequestMapping(value = "/NewUserForm", method = RequestMethod.POST, params = { "cancel" })
	public String newUserFormEsc(HttpSession session, Model model) {

		return "redirect:/LogginPageForm";
	}

	@RequestMapping(value = "/NewUserForm", method = RequestMethod.POST)
	public String newUsrFormErr(HttpSession session, Model model) {
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