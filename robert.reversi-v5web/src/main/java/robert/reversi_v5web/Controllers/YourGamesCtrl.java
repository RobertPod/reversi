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
 * @since 2.0.2
 *
 */
@Controller
@Scope(value = "session")
public class YourGamesCtrl {
	final Logger logger = Logger.getLogger(YourGamesCtrl.class.getName());
	private String userString = YourGamesCtrl.class.getName();

	@Autowired
	protected LoginLogoutSessionService loginLogoutSessionService;

	@RequestMapping(value = "/YourGamesForm", method = RequestMethod.GET)
	public String yourGamesPageGET(HttpSession session, Locale locale, Model model) {
		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		model.addAttribute("playerName", loginLogoutSessionService.getPlayerString());
		model.addAttribute("startMessage", userString + " (GET)");

		return ("/YourGamesForm");
	}

	@RequestMapping(value = "/YourGamesForm", method = RequestMethod.POST, params = { "cancel" })
	public String yourGamesPagePOST(HttpSession session, Locale locale, Model model) {

		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		return "redirect:/LogginPageForm";
	}
}
