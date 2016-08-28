package robert.reversi_v5web.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import robert.reversi_v2.domain.CellCollor;
import robert.reversi_v5web.impl.VGamePadImpl;

@Service
@Scope(value = "session")
public class GameService {
	private VGamePadImpl vGamePad = new VGamePadImpl();

	public VGamePadImpl getvGamePad() {
		return vGamePad;
	}

	public int getSizeCell() {
		return vGamePad.getSizeCell();
	}

	public int getSizeTable() {
		return vGamePad.getSizeTable();
	}

	public Integer getCellInt(int x, int y) {
		return vGamePad.getCell(x, y).ordinal();
	}

	public void processClick(String x, String y) {
		int ypos = Integer.parseInt(x);
		int xpos = Integer.parseInt(y);

		int xcell = (xpos - xpos % vGamePad.getSizeCell()) / vGamePad.getSizeCell();
		int ycell = (ypos - ypos % vGamePad.getSizeCell()) / vGamePad.getSizeCell();
		if (xcell >= vGamePad.getSizeTable())
			xcell = vGamePad.getSizeTable() - 1;
		if (ycell >= vGamePad.getSizeTable())
			ycell = vGamePad.getSizeTable() - 1;

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
