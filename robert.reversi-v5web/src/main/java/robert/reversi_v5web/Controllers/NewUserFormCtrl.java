package robert.reversi_v5web.Controllers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import robert.reversi_v5web.impl.FormularzDTO;
import robert.reversi_v5web.impl.SprDataUserDAO;
import robert.reversi_v5web.impl.UserSprDataImpl;

@Controller
public class NewUserFormCtrl {
	private UserSprDataImpl userDao;

	@Autowired
	protected SprDataUserDAO userDaoSpr;
	// @Autowired
	// private HibDBUserDAOImpl hibDBUserDaoImpl;

	@RequestMapping(value = "/newUserForm", method = RequestMethod.GET)
	public String newUserFormGET(Model model) {
		model.addAttribute("message", "Cześć. Wypełnij proszę formularz.");
		return "newUserForm";
	}

	@RequestMapping(value = "/newUserForm", method = RequestMethod.POST, params = { "submit" })
	public String newUserFormUserData(Model model, @ModelAttribute("form") @Valid FormularzDTO form,
			BindingResult result) {
		if (result.hasErrors()) {
			// formularz nie jest uzupełniony prawidłowo
			model.addAttribute("message", "Bardzo proszę! Popraw blędy!");
			return "/newUserForm";
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
			Date date = new Date();
			Timestamp current_log = new Timestamp(date.getTime());
			userDao.setFirst_log(current_log);
			userDao.setLast_log(current_log);
			userDaoSpr.save(userDao);
			return "redirect:/newUserOKForm";
		}
		model.addAttribute("message", "Użytkownik z takim adresem email już istnieje!");
		return "/newUserForm";
	}

	@RequestMapping(value = "/newUserForm", method = RequestMethod.POST, params = { "cancel" })
	public String newUserFormEsc(Model model) {
		return "redirect:/startForm";
	}

	@RequestMapping(value = "/newUserForm", method = RequestMethod.POST)
	public String newUsrFormErr(Model model) {
		return "redirect:/errorForm";
	}

	@ModelAttribute("form")
	public FormularzDTO getFormularz() {
		return new FormularzDTO();
	}
}