package robert.reversi_v2.api;

import robert.reversi_v2.domain.CellCollor;

public interface GamePad {

	void paintGamePad();

	void setField(int x, int y, CellCollor cellColor);
}
