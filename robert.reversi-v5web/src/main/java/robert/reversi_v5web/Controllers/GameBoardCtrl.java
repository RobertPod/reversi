package robert.reversi_v5web.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;

import robert.reversi_v5web.impl.GamePadParDTO;
import robert.reversi_v5web.impl.XYPositionDTO;
import robert.reversi_v5web.services.GameService;
import robert.reversi_v5web.services.LoginLogoutSessionService;

@Controller
@Scope(value = "session")
public class GameBoardCtrl {
	@Autowired
	protected GameService gameService;

	protected LoginLogoutSessionService loginLogoutSessionService = null;

	private int counter = 1;

	@RequestMapping(value = "/gameBoard", method = RequestMethod.GET)
	public String gameBoardPageGET(HttpSession session, Model model, @ModelAttribute("formXY") XYPositionDTO formXY,
			@ModelAttribute("formGamePad") List<Integer> formGamePad,
			@ModelAttribute("formGamePadPar") GamePadParDTO formGamePadPar, SessionStatus status) {

		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}
		if (loginLogoutSessionService == null)
			loginLogoutSessionService = new LoginLogoutSessionService();
		if (!loginLogoutSessionService.loadSession(session)) {
			loginLogoutSessionService.removeSession(session);
			gameService.getvGamePad().clearGamePad();
			return "redirect:/LogginPageForm";
		}

		formGamePad.clear();
		formXY.setCounter(Integer.toString(counter));
		for (int i = 0; i < formGamePadPar.getSizeX(); i++)
			for (int j = 0; j < formGamePadPar.getSizeY(); j++) {
				formGamePad.add(gameService.getCellInt(i, j));
			}
		model.addAttribute("blackCoun", gameService.getBlackCoun());
		model.addAttribute("redCoun", gameService.getRedCoun());
		String gameStat = gameService.getGameStat();
		if (gameStat.length() == 0)
			gameStat = "<p style='color: red; text-align: center;'><strong>Wykonaj pierwszy ruch! POWODZENIA</strong><br /><span style='color: black; text-align: center;'><strong>Jeżeli chcesz, żeby zaczął komputer naciśnij przycisk.</strong></span></p>";
		model.addAttribute("gameStat", gameStat);
		model.addAttribute("playerName", loginLogoutSessionService.getPlayerString());

		return ("/gameBoard");
	}

	@RequestMapping(value = "/gameBoard", method = RequestMethod.POST)
	public String gameBoardPagePOST(HttpSession session, Model model, @ModelAttribute("formXY") XYPositionDTO formXY,
			@ModelAttribute("formGamePad") List<Integer> formGamePad,
			@ModelAttribute("formGamePadPar") GamePadParDTO formGamePadPar, SessionStatus status) {

		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}
		if (!loginLogoutSessionService.checkSession(session)) {
			loginLogoutSessionService.removeSession(session);
			gameService.getvGamePad().clearGamePad();
			return "redirect:/LogginPageForm";
		}

		if ((Integer.parseInt(formXY.getCounter()) == counter)) {
			/*
			 * W ten pzedziwny sposób rozpoznajemy czy w istocie kliknięto w
			 * planszę, czy też zrobiono reload "F5". Inkremetujemy 'counter' i
			 * przepuszczamy go przez formularz na stronie. Jeżeli kliknięto na
			 * planszy to wróci taki jak wyslaliśmy Jeżeli naciśnięto F5 to
			 * wróci poprzedni POST czyli będzie mniejszy od zmiennej counter
			 */
			gameService.processClick(formXY.getX(), formXY.getY());
		}
		formGamePad.clear();
		formXY.setCounter(Integer.toString(++counter));
		for (int i = 0; i < formGamePadPar.getSizeX(); i++) {
			for (int j = 0; j < formGamePadPar.getSizeY(); j++)
				formGamePad.add(gameService.getCellInt(i, j));
		}
		model.addAttribute("blackCoun", gameService.getBlackCoun());
		model.addAttribute("redCoun", gameService.getRedCoun());
		model.addAttribute("gameStat", gameService.getGameStat());
		model.addAttribute("playerName", loginLogoutSessionService.getPlayerString());

		return ("/gameBoard");
	}

	@RequestMapping(value = "/gameBoard", method = RequestMethod.POST, params = { "NewGame" })
	public String gameBoardPagePOSTNewGame(HttpSession session, Model model,
			@ModelAttribute("formXY") XYPositionDTO formXY, @ModelAttribute("formGamePad") List<Integer> formGamePad,
			@ModelAttribute("formGamePadPar") GamePadParDTO formGamePadPar, SessionStatus status) {

		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}
		if (!loginLogoutSessionService.checkSession(session)) {
			loginLogoutSessionService.removeSession(session);
			gameService.getvGamePad().clearGamePad();
			return "redirect:/LogginPageForm";
		}

		gameService.getvGamePad().clearGamePad();

		formGamePad.clear();
		formXY.setCounter(Integer.toString(counter));
		for (int i = 0; i < formGamePadPar.getSizeX(); i++)
			for (int j = 0; j < formGamePadPar.getSizeY(); j++) {
				formGamePad.add(gameService.getCellInt(i, j));
			}
		model.addAttribute("blackCoun", gameService.getBlackCoun());
		model.addAttribute("redCoun", gameService.getRedCoun());
		model.addAttribute("gameStat",
				"<p style='color: red; text-align: center;'><strong>Wykonaj pierwszy ruch! POWODZENIA</strong><br /><span style='color: black; text-align: center;'><strong>Jeżeli chcesz, żeby zaczął komputer naciśnij przycisk.</strong></span></p>");
		model.addAttribute("playerName", loginLogoutSessionService.getPlayerString());

		return ("/gameBoard");
	}

	@RequestMapping(value = "/gameBoard", method = RequestMethod.POST, params = { "ComputerStart" })
	public String gameBoardPagePOSTComputerStart(HttpSession session, Model model,
			@ModelAttribute("formXY") XYPositionDTO formXY, @ModelAttribute("formGamePad") List<Integer> formGamePad,
			@ModelAttribute("formGamePadPar") GamePadParDTO formGamePadPar, SessionStatus status) {
		gameService.getvGamePad().clearGamePad();
		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}
		if (!loginLogoutSessionService.checkSession(session)) {
			loginLogoutSessionService.removeSession(session);
			gameService.getvGamePad().clearGamePad();
			return "redirect:/LogginPageForm";
		}

		if (!gameService.computerMove())
			;
		formGamePad.clear();
		formXY.setCounter(Integer.toString(counter));
		for (int i = 0; i < formGamePadPar.getSizeX(); i++)
			for (int j = 0; j < formGamePadPar.getSizeY(); j++) {
				formGamePad.add(gameService.getCellInt(i, j));
			}
		model.addAttribute("blackCoun", gameService.getBlackCoun());
		model.addAttribute("redCoun", gameService.getRedCoun());
		model.addAttribute("gameStat", gameService.getGameStat());
		model.addAttribute("playerName", loginLogoutSessionService.getPlayerString());

		return ("/gameBoard");

	}

	@RequestMapping(value = "/gameBoard", method = RequestMethod.POST, params = { "StartForm" })
	public String userSearchPOSTStartForm(HttpSession session, Model model) {
		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		return "redirect:/LogginPageForm";
	}

	@RequestMapping(value = "/gameBoard", method = RequestMethod.POST, params = { "refresh" })
	public String userSearchPOSTRefresh(HttpSession session, Model model) {
		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		return "redirect:/LogginPageForm";
	}

	@ModelAttribute("formXY")
	public XYPositionDTO getXYPositionDTO() {
		return new XYPositionDTO();
	}

	@ModelAttribute("formGamePad")
	public List<Integer> getGamePad() {
		List<Integer> formGamePad = new ArrayList<Integer>();
		return formGamePad;
	}

	@ModelAttribute("formGamePadPar")
	public GamePadParDTO getGamePadParDTO() {
		GamePadParDTO formGamePadPar = new GamePadParDTO(gameService);
		return formGamePadPar;
	}

}
