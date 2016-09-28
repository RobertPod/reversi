package robert.reversi_v5web.Controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorFormCtrl {
	@RequestMapping("/ErrorForm")
	public String errorPage(HttpSession session, Locale locale, Model model) {
		final Logger logger = Logger.getLogger(ErrorFormCtrl.class.getName());

		logger.info("Session Id: " + session.getId());
		logger.info("Session CreationTime: " + session.getCreationTime());
		logger.info("Session LastAccessedTime: " + session.getLastAccessedTime());
		logger.info("Session liveTime: " + ((session.getLastAccessedTime() - session.getCreationTime()) / 1000));
		logger.info("Session Locale: " + locale);

		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		// session.invalidate();

		return ("/ErrorForm");
	}
}
