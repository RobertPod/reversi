package robert.reversi_v5web.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import robert.reversi_v5web.impl.GamePadParDTO;
import robert.reversi_v5web.impl.XYPositionDTO;
import robert.reversi_v5web.services.GameService;

@Controller
public class GameBoardCtrl {
	@Autowired
	protected GameService gameService;

	@RequestMapping(value = "/gameBoard", method = RequestMethod.GET)
	public String gameBoardPageGET(Model model, @ModelAttribute("formXY") XYPositionDTO formXY,
			@ModelAttribute("formGamePad") List<Integer> formGamePad,
			@ModelAttribute("formGamePadPar") GamePadParDTO formGamePadPar, SessionStatus status) {

		formGamePad.clear();
		formXY.setX("0");
		formXY.setY("0");
		formXY.setPosX("0");
		formXY.setPosY("0");
		for (int j = 0; j < formGamePadPar.getSizeY(); j++)
			for (int i = 0; i < formGamePadPar.getSizeX(); i++) {
				formGamePad.add(gameService.getCellInt(i, j));
			}

		return ("/gameBoard");
	}

	@RequestMapping(value = "/gameBoard", method = RequestMethod.POST)
	public String gameBoardPagePOST(Model model, @ModelAttribute("formXY") XYPositionDTO formXY,
			@ModelAttribute("formGamePad") List<Integer> formGamePad,
			@ModelAttribute("formGamePadPar") GamePadParDTO formGamePadPar, SessionStatus status) {

		if ((Integer.parseInt(formXY.getX()) != 0) && (Integer.parseInt(formXY.getY()) != 0))
			gameService.processClick(formXY.getX(), formXY.getY());
		formGamePad.clear();
		formXY.setX("0");
		formXY.setY("0");
		formXY.setPosX("0");
		formXY.setPosY("0");
		for (int i = 0; i < formGamePadPar.getSizeX(); i++) {
			for (int j = 0; j < formGamePadPar.getSizeY(); j++)
				formGamePad.add(gameService.getCellInt(i, j));
		}

		return ("/gameBoard");
	}

	@ModelAttribute("formXY")
	public XYPositionDTO getXYPositionDTO() {
		return new XYPositionDTO();
	}

	@ModelAttribute("formGamePad")
	public List<Integer> getGamePad() {
		List<Integer> formGamePad = new ArrayList<Integer>();
		formGamePad.add(1);
		formGamePad.add(11);
		formGamePad.add(111);
		return formGamePad;
	}

	@ModelAttribute("formGamePadPar")
	public GamePadParDTO getGamePadParDTO() {
		GamePadParDTO formGamePadPar = new GamePadParDTO();
		return formGamePadPar;
	}

}
