package robert.reversi_v2.api;

import robert.reversi_v2.domain.CellCollor;

public interface VirtualGamePad {

	void setCell(int x, int y, CellCollor cellCollor);

	CellCollor getCell(int x, int y);

	boolean rightMove(int x, int y, CellCollor cellCollor);

	boolean possibleMove(CellCollor cellCollor);

	int amountPawn(CellCollor cellCollor);

	void clearGamePad();
}
