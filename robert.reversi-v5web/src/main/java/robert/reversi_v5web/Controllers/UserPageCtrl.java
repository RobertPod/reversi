package robert.reversi_v5web.Controllers;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import robert.reversi_v5web.services.LoginLogoutSessionService;

@Controller
@Scope(value = "session")
public class UserPageCtrl {
	final Logger logger = Logger.getLogger(UserPageCtrl.class.getName());
	private String userString = "";

	@Autowired
	protected LoginLogoutSessionService loginLogoutSessionService;

	@RequestMapping(value = { "/UserPageForm" }, method = RequestMethod.GET)
	public String userPageGet(HttpSession session, HttpServletRequest request, Locale locale, Model model) {

		logger.info(loginLogoutSessionService.getPlayer() + " "
				+ ((session.getLastAccessedTime() - session.getCreationTime()) / 1000) + "s " + session.getId());

		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}
		if (!loginLogoutSessionService.checkSession(session))
			return ("/LogginPageForm");
		if (!loginLogoutSessionService.isLogin())
			return ("redirect:/LogginPageForm");

		model.addAttribute("playerName", loginLogoutSessionService.getPlayerString());
		model.addAttribute("startMessage", userString + " (GET)");

		model.addAttribute("adresEmail", "<span style='font-style: italic;'>Adres email: </span>"
				+ loginLogoutSessionService.getAdditionalUserData().getEmail());
		model.addAttribute("ipAdress", "<span style='font-style: italic;'>IP / HOST: </span>"
				+ loginLogoutSessionService.getIpAdress() + " / " + loginLogoutSessionService.getLoginHostname());
		model.addAttribute("accountCreate", "<span style='font-style: italic;'>Założenie konta: </span>"
				+ loginLogoutSessionService.getAdditionalUserData().getFirst_log());
		
		Date data = new Date(session.getCreationTime());
		model.addAttribute("lastLoginDate",
				"<span style='font-style: italic;'>Czas logowania: </span>" + data);
		model.addAttribute("gamesWinLost",
				"<span style='font-style: italic;'>Wygrane / przegrane teraz i (od początku): </span>"
						+ loginLogoutSessionService.getSessionGameWin() + " / "
						+ loginLogoutSessionService.getSessionGameLost() + " ("
						+ loginLogoutSessionService.getAdditionalUserData().getWinGames() + " / "
						+ loginLogoutSessionService.getAdditionalUserData().getLostGames() + ")");

		return ("/UserPageForm");
	}

	@RequestMapping(value = "/UserPageForm", method = RequestMethod.POST, params = { "logout" })
	public String userPageFormLogoutPost(HttpSession session, HttpServletRequest request, Locale locale, Model model) {

		loginLogoutSessionService.logoutFromGame(session);

		return ("redirect:/LogginPageForm");
	}

	@RequestMapping(value = "/UserPageForm", method = RequestMethod.POST, params = { "gameboard" })
	public String userPageFormGamePost(HttpSession session, HttpServletRequest request, Locale locale, Model model) {

		return "redirect:/gameBoard";
	}

}
