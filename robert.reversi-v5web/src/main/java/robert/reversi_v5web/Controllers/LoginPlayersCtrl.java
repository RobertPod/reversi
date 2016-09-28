package robert.reversi_v5web.Controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import robert.reversi_v5web.services.LoginLogoutSessionService;

/**
 * @author Robert
 * @since 2.0.0
 *
 */
@Controller
@Scope(value = "session")
public class LoginPlayersCtrl {
	final Logger logger = Logger.getLogger(LoginPlayersCtrl.class.getName());

	@Autowired
	protected LoginLogoutSessionService loginLogoutSessionService;

	@RequestMapping(value = "/LoginPlayersForm", method = RequestMethod.GET)
	public String gameRulesPageGET(HttpSession session, Locale locale, Model model) {
		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		model.addAttribute("playerName", loginLogoutSessionService.getPlayerString());
		return ("/LoginPlayersForm");
	}

	@RequestMapping(value = "/LoginPlayersForm", method = RequestMethod.POST, params = { "cancel" })
	public String gameRulesPagePOST(HttpSession session, Locale locale, Model model) {

		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		return "redirect:/LogginPageForm";
	}
}
