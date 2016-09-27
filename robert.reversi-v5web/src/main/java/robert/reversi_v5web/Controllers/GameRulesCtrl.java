package robert.reversi_v5web.Controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GameRulesCtrl {
	final Logger logger = Logger.getLogger(GameRulesCtrl.class.getName());

	@RequestMapping(value = "/gameRulesForm", method = RequestMethod.GET)
	public String gameRulesPageGET(HttpSession session, Locale locale, Model model) {
		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		return ("/gameRulesForm");
	}

	@RequestMapping(value = "/gameRulesForm", method = RequestMethod.POST, params = { "cancel" })
	public String gameRulesPagePOST(HttpSession session, Locale locale, Model model) {

		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		return "redirect:/LogginPageForm";
	}
}
