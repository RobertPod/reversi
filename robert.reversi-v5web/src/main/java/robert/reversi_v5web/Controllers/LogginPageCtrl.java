package robert.reversi_v5web.Controllers;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import robert.reversi_v5web.impl.FormularzDTO;
import robert.reversi_v5web.services.LoginLogoutSessionService;

@Controller
@Scope(value = "session")
public class LogginPageCtrl {
	final Logger logger = Logger.getLogger(LogginPageCtrl.class.getName());
	private String logString = "Graj bez logowania, zaloguj się, załóż konto lub poczytaj o projekcie";

	@Autowired
	protected LoginLogoutSessionService loginLogoutSessionService;

	@RequestMapping(value = { "/LogginPageForm", "/", "" }, method = RequestMethod.GET)
	public String logginPageGet(HttpSession session, HttpServletRequest request, Locale locale, Model model) {

		// logger.info("Session Id: " + session.getId());
		// logger.info("Session CreationTime: " + session.getCreationTime());
		// logger.info("Session LastAccessedTime: " +
		// session.getLastAccessedTime());
		logger.info(loginLogoutSessionService.getPlayer() + " "
				+ ((session.getLastAccessedTime() - session.getCreationTime()) / 1000) + "s " + session.getId());
		// logger.info("Session Locale: " + locale);
		loginLogoutSessionService.checkSession(session);

		// if (session.isNew()) {
		// return "redirect:/LogginPageForm";
		// }
		model.addAttribute("playerName", loginLogoutSessionService.getPlayerString());
		model.addAttribute("startMessage", logString + " (GET)");

		// session.invalidate();

		return ("/LogginPageForm");
	}

	@RequestMapping(value = "/LogginPageForm", method = RequestMethod.POST, params = { "login" })
	public String logginPageLoginPost(@ModelAttribute("email") String playerEmail,
			@ModelAttribute("pass") String playerPass, HttpSession session, HttpServletRequest request, Locale locale,
			Model model) {

		if (!loginLogoutSessionService.checkSession(session))
			return ("/LogginPageForm");

		if ((playerEmail.length() > 0) && (playerPass.length() > 0)) {
			loginLogoutSessionService.loginToGame(playerEmail, playerPass);
		}

		model.addAttribute("playerName", loginLogoutSessionService.getPlayerString());
		model.addAttribute("startMessage",
				logString + " (POST)" + " " + "<br />Logowanie nieudane - spróbuj jeszcze raz!");

		return ("/LogginPageForm");
	}

	@RequestMapping(value = "/LogginPageForm", method = RequestMethod.POST, params = { "gameboard" })
	public String logginPageGamePost(HttpSession session, HttpServletRequest request, Locale locale, Model model) {
		if (!loginLogoutSessionService.saveSession(session)) {
			loginLogoutSessionService.removeSession(session);
			return ("/LogginPageForm");
		}
		return "redirect:/gameBoard";
	}

}
