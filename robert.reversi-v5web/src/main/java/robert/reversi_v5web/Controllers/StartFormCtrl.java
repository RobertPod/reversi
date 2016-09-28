package robert.reversi_v5web.Controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Robert
 * @since 1.2.0
 *
 */
@Controller
@Scope(value = "session")
public class StartFormCtrl {

	private int counter = 1;
	private String headString = "<p style=''>Cześć! Nieznany użytkowniku.</p>"
			+ "<p style=''>Możesz grać anonmowo lub się zalogować / <a href='./CreateNewUserForm'>założyć konto.</a></p>"
			+ "<p style=''>Po zalogowaniu będą zapisywane Twoje gry. Zyskkasz też możliwość gry z innym graczem.</p>";

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(HttpSession session, Locale locale, Model model) {
//		model.addAttribute("message", headString + "GET");
//		return "/StartForm";
//	}

	@RequestMapping(value = "/StartForm", method = RequestMethod.GET)
	public String startForm(HttpSession session, Model model) {
		model.addAttribute("message", headString + "POST");
		return "/StartForm";
	}

	@RequestMapping(value = "/StartForm", method = RequestMethod.POST)
	public String startFormPOST(HttpSession session, @RequestParam(required = false, defaultValue = "") String error,
			@RequestParam(required = false, defaultValue = "") String newuser,
			@RequestParam(required = false, defaultValue = "") String gameboard,
			@RequestParam(required = false, defaultValue = "") String finduser, Model model) {

		if (!error.isEmpty()) {
			return "redirect:/ErrorForm";
		} else if (!newuser.isEmpty()) {
			return "redirect:/CreateNewUserForm";
		} else if (!finduser.isEmpty()) {
			return "redirect:/UserSearchForm";
		} else if (!gameboard.isEmpty()) {
			return "redirect:/GameBoard";
		} else {
			throw new IllegalArgumentException("Need either newuser or finduser!");
		}
	}
}
