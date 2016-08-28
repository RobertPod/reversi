package robert.reversi_v2.impl;

import robert.reversi_v2.api.VirtualGamePad;
import robert.reversi_v2.domain.CellCollor;
import robert.reversi_v2.domain.DeclareConstants;

public class VirtualGamePadImpl implements VirtualGamePad {
	private CellCollor[][] gameTable;
	private int sizeTable = DeclareConstants.getSizetable();
	private int sizeCell = DeclareConstants.getCellsize();
	private int sizeDiameter = DeclareConstants.getDiameter();

	public int getSizeTable() {
		return sizeTable;
	}

	public void setSizeTable(int sizeTable) {
		this.sizeTable = sizeTable;
	}

	public int getSizeCell() {
		return sizeCell;
	}

	public void setSizeCell(int sizeCell) {
		this.sizeCell = sizeCell;
	}

	public int getSizeDiameter() {
		return sizeDiameter;
	}

	public void setSizeDiameter(int sizeDiameter) {
		this.sizeDiameter = sizeDiameter;
	}

	public VirtualGamePadImpl() {
		gameTable = new CellCollor[sizeTable][sizeTable];
		clearGamePad();
	}

	public void setCell(int x, int y, CellCollor cellCollor) {
		gameTable[x][y] = cellCollor;
	}

	public CellCollor getCell(int x, int y) {
		return gameTable[x][y];
	}

	public boolean rightMove(int x, int y, CellCollor cellCollor) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean possibleMove(CellCollor cellCollor) {
		// TODO Auto-generated method stub
		return false;
	}

	public int amountPawn(CellCollor cellCollor) {
		int amount = 0;
		for (int i = 0; i < sizeTable; ++i)
			for (int j = 0; j < sizeTable; ++j)
				if (gameTable[i][j] == cellCollor)
					amount++;
		return amount;
	}

	public void clearGamePad() {
		for (int i = 0; i < sizeTable; ++i)
			for (int j = 0; j < sizeTable; ++j)
				gameTable[i][j] = CellCollor.WHITE;
		gameTable[sizeTable / 2][sizeTable / 2] = CellCollor.BLACK;
		gameTable[sizeTable / 2 - 1][sizeTable / 2 - 1] = CellCollor.BLACK;
		gameTable[sizeTable / 2][sizeTable / 2 - 1] = CellCollor.RED;
		gameTable[sizeTable / 2 - 1][sizeTable / 2] = CellCollor.RED;
	}

}
