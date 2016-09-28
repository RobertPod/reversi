package robert.reversi_v5web.Controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Robert
 * @since 2.0.0
 *
 */
@Controller
public class GameRulesCtrl {
	final Logger logger = Logger.getLogger(GameRulesCtrl.class.getName());

	@RequestMapping(value = "/GameRulesForm", method = RequestMethod.GET)
	public String gameRulesPageGET(HttpSession session, Locale locale, Model model) {
		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		return ("/GameRulesForm");
	}

	@RequestMapping(value = "/GameRulesForm", method = RequestMethod.POST, params = { "cancel" })
	public String gameRulesPagePOST(HttpSession session, Locale locale, Model model) {

		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		return "redirect:/LogginPageForm";
	}
}
