package robert.reversi_v2.impl;

import static robert.reversi_v2.domain.DeclareConstants.SIZETABLE;

import robert.reversi_v2.api.VirtualGamePad;
import robert.reversi_v2.domain.CellCollor;

public class VirtualGamePadImpl implements VirtualGamePad {
	private CellCollor[][] gameTable;

	public VirtualGamePadImpl() {
		gameTable = new CellCollor[SIZETABLE][SIZETABLE];
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
		for (int i = 0; i < SIZETABLE; ++i)
			for (int j = 0; j < SIZETABLE; ++j)
				if (gameTable[i][j] == cellCollor)
					amount++;
		return amount;
	}

	public void clearGamePad() {
		for (int i = 0; i < SIZETABLE; ++i)
			for (int j = 0; j < SIZETABLE; ++j)
				gameTable[i][j] = CellCollor.WHITE;
		gameTable[SIZETABLE / 2][SIZETABLE / 2] = CellCollor.BLACK;
		gameTable[SIZETABLE / 2 - 1][SIZETABLE / 2 - 1] = CellCollor.BLACK;
		gameTable[SIZETABLE / 2][SIZETABLE / 2 - 1] = CellCollor.RED;
		gameTable[SIZETABLE / 2 - 1][SIZETABLE / 2] = CellCollor.RED;
	}

}
