package robert.reversi_v5web.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import robert.reversi_v5web.impl.GamePadParDTO;
import robert.reversi_v5web.impl.XYPositionDTO;
import robert.reversi_v5web.services.GameService;

@Controller
@Scope(value = "session")
public class GameBoardCtrl {
	@Autowired
	protected GameService gameService;

	private int counter = 1;

	@RequestMapping(value = "/gameBoard", method = RequestMethod.GET)
	public String gameBoardPageGET(HttpSession session, Model model, @ModelAttribute("formXY") XYPositionDTO formXY,
			@ModelAttribute("formGamePad") List<Integer> formGamePad,
			@ModelAttribute("formGamePadPar") GamePadParDTO formGamePadPar, SessionStatus status) {

		if (session.isNew()) {
			return "redirect:/startForm";
		}
		// session.invalidate();

		formGamePad.clear();
		formXY.setCounter(Integer.toString(counter));
		for (int i = 0; i < formGamePadPar.getSizeX(); i++)
			for (int j = 0; j < formGamePadPar.getSizeY(); j++) {
				formGamePad.add(gameService.getCellInt(i, j));
			}

		return ("/gameBoard");
	}

	@RequestMapping(value = "/gameBoard", method = RequestMethod.POST)
	public String gameBoardPagePOST(HttpSession session, Model model, @ModelAttribute("formXY") XYPositionDTO formXY,
			@ModelAttribute("formGamePad") List<Integer> formGamePad,
			@ModelAttribute("formGamePadPar") GamePadParDTO formGamePadPar, SessionStatus status) {

		if (session.isNew()) {
			return "redirect:/startForm";
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

		return ("/gameBoard");
	}

	@RequestMapping(value = "/gameBoard", method = RequestMethod.POST, params = { "NewGame" })
	public String userSearchPOSTNewGame(HttpSession session, Model model,
			@ModelAttribute("formXY") XYPositionDTO formXY, @ModelAttribute("formGamePad") List<Integer> formGamePad,
			@ModelAttribute("formGamePadPar") GamePadParDTO formGamePadPar, SessionStatus status) {
		gameService.getvGamePad().clearGamePad();
		if (session.isNew()) {
			return "redirect:/startForm";
		}
		// session.invalidate();

		formGamePad.clear();
		formXY.setCounter(Integer.toString(counter));
		for (int i = 0; i < formGamePadPar.getSizeX(); i++)
			for (int j = 0; j < formGamePadPar.getSizeY(); j++) {
				formGamePad.add(gameService.getCellInt(i, j));
			}

		return ("/gameBoard");

	}

	@RequestMapping(value = "/gameBoard", method = RequestMethod.POST, params = { "StartForm" })
	public String userSearchPOSTStartForm(HttpSession session, Model model) {
		if (session.isNew()) {
			return "redirect:/startForm";
		}

		return "redirect:/startForm";
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
		GamePadParDTO formGamePadPar = new GamePadParDTO();
		return formGamePadPar;
	}

}
