package robert.reversi_v5web.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import robert.reversi_v5web.impl.DBUserDAOImpl;
// import robert.reversi_v5web.impl.DBUserDAOImpl;
import robert.reversi_v5web.impl.FormularzDTO;
import robert.reversi_v5web.impl.HibDBUserDAOImpl;
import robert.reversi_v5web.impl.UserDAOImpl;

@Controller
public class NewUserFormCtrl {
	// dla DBUserDaoImpl - wszystkie zakomentowane linie. Dla celów
	// dydaktycznych chcę mieć zarówno implementację bezpośredni JDBC jak i JPA
	// @Autowired
	private UserDAOImpl userDao;

	// dla DBUserDaoImpl
	// @Autowired
	// private DBUserDAOImpl DBUserDaoImpl;

	// dla HibDBUserDAOImpl
	@Autowired
	private HibDBUserDAOImpl hibDBUserDaoImpl;

	@RequestMapping(value = "/newuserform", method = RequestMethod.GET)
	public String newUserFormGET(Model model) {
		model.addAttribute("message", "Cześć. Wypełnij proszę formularz.");
		return "newuserform";
	}

	@RequestMapping(value = "/newuserform", method = RequestMethod.POST, params = { "submit" })
	public String newUserFormUserData(Model model, @ModelAttribute("form") @Valid FormularzDTO form,
			BindingResult result) {
		if (result.hasErrors()) {
			// formularz nie jest uzupełniony prawidłowo
			model.addAttribute("message", "Bardzo proszę! Popraw blędy!");
			return "/newuserform";
		} else if (hibDBUserDaoImpl.existUserWithEmail(form.getEmail())) {
			model.addAttribute("message", "Użytkownik z takim adresem email już istnieje!");
			return "/newuserform";
		} else {
			// formularz wypełniony prawidłowo
			userDao = new UserDAOImpl();
			userDao.setName(form.getName());
			userDao.setEmail(form.getEmail());
			userDao.setPass(form.getPass());
			userDao.setAge(form.getAge() == null ? 0 : form.getAge());
			// dla DBUserDaoImpl
			// DBUserDaoImpl.addUser(userDao);
			hibDBUserDaoImpl.addUser(userDao);
			return "redirect:/newuserokform";
		}
	}

	@RequestMapping(value = "/newuserform", method = RequestMethod.POST, params = { "cancel" })
	public String newUserFormEsc(Model model) {
		return "redirect:/startform";
	}

	@RequestMapping(value = "/newuserform", method = RequestMethod.POST)
	public String newUsrFormErr(Model model) {
		return "redirect:/errorformu";
	}

	@ModelAttribute("form")
	public FormularzDTO getFormularz() {
		return new FormularzDTO();
	}
}