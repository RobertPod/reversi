package robert.reversi_v5web.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import static robert.reversi_v2.domain.DeclareConstants.SIZETABLE;
import static robert.reversi_v2.domain.DeclareConstants.CELLSIZE;
import robert.reversi_v2.domain.CellCollor;
import robert.reversi_v5web.impl.VGamePadImpl;

@Service
@Scope(value = "session")
public class GameService {
	private VGamePadImpl vGamePad = new VGamePadImpl();

	public VGamePadImpl getvGamePad() {
		return vGamePad;
	}

	public Integer getCellInt(int x, int y) {
		return vGamePad.getCell(x, y).ordinal();
	}

	public void processClick(String x, String y) {
		int ypos = Integer.parseInt(x);
		int xpos = Integer.parseInt(y);

		int xcell = (xpos - xpos % CELLSIZE) / CELLSIZE;
		int ycell = (ypos - ypos % CELLSIZE) / CELLSIZE;
		if (xcell >= SIZETABLE)
			xcell = SIZETABLE - 1;
		if (ycell >= SIZETABLE)
			ycell = SIZETABLE - 1;

		switch (vGamePad.getCell(xcell, ycell)) {
		case WHITE:
			vGamePad.setCell(xcell, ycell, CellCollor.BLACK);
			break;

		case BLACK:
			vGamePad.setCell(xcell, ycell, CellCollor.RED);
			break;

		case RED:
			vGamePad.setCell(xcell, ycell, CellCollor.WHITE);

		}

		// TODO Auto-generated method stub

	}

}
