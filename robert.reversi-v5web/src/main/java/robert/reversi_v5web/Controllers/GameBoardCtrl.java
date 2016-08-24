package robert.reversi_v5web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import robert.reversi_v5web.impl.XYPositionDTO;

@Controller
public class GameBoardCtrl {
	@RequestMapping(value = "/gameBoard", method = RequestMethod.GET)
	public String gameBoardPageGET(Model model) {
		return ("/gameBoard");
	}

	@RequestMapping(value = "/gameBoard", method = RequestMethod.POST)
	public String gameBoardPagePOST(Model model, @ModelAttribute("formXY") XYPositionDTO formXY, SessionStatus status) {

		return ("/gameBoard");
	}

	@ModelAttribute("formXY")
	public XYPositionDTO getXYPositionDTO() {
		return new XYPositionDTO();
	}

}
