package robert.reversi_v2.api;

import robert.reversi_v2.domain.CellCollor;

public interface VirtualGamePad {
	public int getSizeTable();

	public void setSizeTable(int sizeTable);

	public int getSizeCell();

	public void setSizeCell(int sizeCell);

	public int getSizeDiameter();

	public void setSizeDiameter(int sizeDiameter);

	void setCell(int x, int y, CellCollor cellCollor);

	CellCollor getCell(int x, int y);

	boolean rightMove(int x, int y, CellCollor cellCollor);

	boolean possibleMove(CellCollor cellCollor);

	int amountPawn(CellCollor cellCollor);

	void clearGamePad();
}
