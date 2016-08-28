package robert.reversi_v5web.Controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Scope(value = "session")
public class StartFormCtrl {
	// @Autowired
	// private UserDAO userDao;

	private int counter = 1;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, Locale locale, Model model) {
		model.addAttribute("message", "Cześć! Super, że tu jesteś! Już " + counter++ + " raz.");
		return "/startForm";
	}

	@RequestMapping(value = "/startForm", method = RequestMethod.GET)
	public String startForm(HttpSession session, Model model) {
		model.addAttribute("message", "Cześć! Super, że tu jesteś! Już " + counter++ + " raz.");
		return "/startForm";
	}

	@RequestMapping(value = "/startForm", method = RequestMethod.POST)
	public String startFormPOST(HttpSession session, @RequestParam(required = false, defaultValue = "") String error,
			@RequestParam(required = false, defaultValue = "") String newuser,
			@RequestParam(required = false, defaultValue = "") String gameboard,
			@RequestParam(required = false, defaultValue = "") String finduser, Model model) {

		if (!error.isEmpty()) {
			return "redirect:/errorForm";
		} else if (!newuser.isEmpty()) {
			return "redirect:/newUserForm";
		} else if (!finduser.isEmpty()) {
			return "redirect:/userSearchForm";
		} else if (!gameboard.isEmpty()) {
			return "redirect:/gameBoard";
		} else {
			throw new IllegalArgumentException("Need either newuser or finduser!");
		}
	}
}
