package robert.reversi_v5web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import robert.reversi_v5web.api.UserDAO;

@Controller
public class StartFormCtrl {
	@Autowired
	private UserDAO userDao;

	private int counter = 1;

	@RequestMapping(value = "/startform", method = RequestMethod.GET)
	public String startForm(Model model) {

		// to tak, żeby sprawdzić czy działa @Autowired
		userDao.setName("Robert");
		userDao.setEmail("mail");
		userDao.setAge(22);

		model.addAttribute("message", "Cześć! Super, że tu jesteś! Już " + counter++ + " raz.");

		return "/startform";
	}

	@RequestMapping(value = "/startform", method = RequestMethod.POST)
	public String startFormPOST(@RequestParam(required = false, defaultValue = "") String error,
			@RequestParam(required = false, defaultValue = "") String newuser,
			@RequestParam(required = false, defaultValue = "") String finduser, Model model) {

		if (!error.isEmpty()) {
			// user clicked "approve"
			return "redirect:/errorform";
		} else if (!newuser.isEmpty()) {
			// user clicked "deny"
			return "redirect:/newuserform";
		} else if (!finduser.isEmpty()) {
			// user clicked "deny"
			return "redirect:/usersearchform";
		} else {
			throw new IllegalArgumentException("Need either approve or deny!");
		}
	}
}
