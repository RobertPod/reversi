package robert.reversi_v5web.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import robert.reversi_v5web.impl.LoginsSprDataImpl;
import robert.reversi_v5web.impl.SessionDisplayDataDTO;
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
	public String loginPlayersPageGET(HttpSession session, Locale locale, Model model,
			@ModelAttribute("sessionDisplayDataDTO") List<SessionDisplayDataDTO> sessionDisplayDataDTO) {

		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}
		if (!loginLogoutSessionService.isLogin()) {
			return "redirect:/LogginPageForm";
		}

		List<LoginsSprDataImpl> lastSessions = loginLogoutSessionService.getSessionListByLoginDate();
		logger.info(lastSessions.size());

		if (!sessionDisplayDataDTO.isEmpty())
			sessionDisplayDataDTO.clear();

		loginLogoutSessionService.convertSessionsTable2Display(lastSessions, sessionDisplayDataDTO);
		logger.info(sessionDisplayDataDTO.size());

		// for (SessionDisplayDataDTO essionDisplayDataDTO :
		// sessionDisplayDataDTO) {
		// logger.info(essionDisplayDataDTO.getLoginTime() + " - " +
		// essionDisplayDataDTO.getLogoutTime());
		//
		// }

		model.addAttribute("playerName", loginLogoutSessionService.getPlayerString());
		return ("/LoginPlayersForm");
	}

	@RequestMapping(value = "/LoginPlayersForm", method = RequestMethod.POST, params = { "cancel" })
	public String loginPlayersPagePOST(HttpSession session, Locale locale, Model model) {

		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		return "redirect:/LogginPageForm";
	}

	@ModelAttribute("sessionDisplayDataDTO")
	public List<SessionDisplayDataDTO> getSessionDisplayDataDTO() {
		List<SessionDisplayDataDTO> sessionDisplayDataDTO = new ArrayList<SessionDisplayDataDTO>();
		return sessionDisplayDataDTO;
	}
}
