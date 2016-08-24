package robert.reversi_v5web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import robert.reversi_v5web.impl.GamePadParDTO;
import robert.reversi_v5web.impl.XYPositionDTO;

@Controller
public class GameBoardCtrl {
	@RequestMapping(value = "/gameBoard", method = RequestMethod.GET)
	public String gameBoardPageGET(Model model, @ModelAttribute("formXY") XYPositionDTO formXY,
			@ModelAttribute("formGamePad") XYPositionDTO formGamePad,
			@ModelAttribute("formGamePadPar") GamePadParDTO formGamePadPar, SessionStatus status) {
		return ("/gameBoard");
	}

	@RequestMapping(value = "/gameBoard", method = RequestMethod.POST)
	public String gameBoardPagePOST(Model model, @ModelAttribute("formXY") XYPositionDTO formXY,
			@ModelAttribute("formGamePad") XYPositionDTO formGamePad,
			@ModelAttribute("formGamePadPar") GamePadParDTO formGamePadPar, SessionStatus status) {
		
		formGamePadPar.setX(formXY.getX());
		formGamePadPar.setY(formXY.getY());

		return ("/gameBoard");
	}

	@ModelAttribute("formXY")
	public XYPositionDTO getXYPositionDTO() {
		return new XYPositionDTO();
	}

	@ModelAttribute("formGamePad")
	public XYPositionDTO getXYPositionDTO1() {
		return new XYPositionDTO();
	}

	@ModelAttribute("formGamePadPar")
	public GamePadParDTO getGamePadParDTO() {
		GamePadParDTO formGamePadPar = new GamePadParDTO();
		return formGamePadPar;
	}

}
