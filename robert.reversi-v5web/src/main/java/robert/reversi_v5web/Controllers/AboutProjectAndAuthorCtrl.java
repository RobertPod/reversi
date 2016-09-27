package robert.reversi_v5web.Controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AboutProjectAndAuthorCtrl {
	final Logger logger = Logger.getLogger(AboutProjectAndAuthorCtrl.class.getName());

	@RequestMapping(value = "/aboutProjectAndAuthorForm", method = RequestMethod.GET)
	public String gameRulesPageGET(HttpSession session, Locale locale, Model model) {
		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		return ("/aboutProjectAndAuthorForm");
	}

	@RequestMapping(value = "/aboutProjectAndAuthorForm", method = RequestMethod.POST, params = { "cancel" })
	public String gameRulesPagePOST(HttpSession session, Locale locale, Model model) {

		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		return "redirect:/LogginPageForm";
	}
}
