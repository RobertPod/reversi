package robert.reversi_v5web.Controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginPlayersCtrl {
	final Logger logger = Logger.getLogger(LoginPlayersCtrl.class.getName());

	@RequestMapping(value = "/loginPlayersForm", method = RequestMethod.GET)
	public String gameRulesPageGET(HttpSession session, Locale locale, Model model) {
		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		return ("/loginPlayersForm");
	}

	@RequestMapping(value = "/loginPlayersForm", method = RequestMethod.POST, params = { "cancel" })
	public String gameRulesPagePOST(HttpSession session, Locale locale, Model model) {

		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		return "redirect:/LogginPageForm";
	}
}
