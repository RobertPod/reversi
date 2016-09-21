package robert.reversi_v5web.services;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import robert.reversi_v2.api.ComputerMove.XYPosition;
import robert.reversi_v2.domain.CellCollor;
import robert.reversi_v5web.domain.ReversiV5Const;
import robert.reversi_v5web.impl.VGamePadImpl;

@Service
@Scope(value = "session")
public class GameService {
	final static Logger logger = Logger.getLogger(GameService.class.getName());

	private VGamePadImpl vGamePad = new VGamePadImpl();
	private String gameStat = "";
	private String blackCoun = "";
	private String redCoun = "";

	public VGamePadImpl getvGamePad() {
		return vGamePad;
	}

	public String getGameStat() {
		return gameStat;
	}

	public String getBlackCoun() {
		this.blackCoun = "<p style='width: 180px; color: black; text-align: center;'><strong>Czarne:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ getvGamePad().amountPawn(CellCollor.BLACK) + "</p></strong><br />";
		return blackCoun;
	}

	public String getRedCoun() {
		this.redCoun = "<p style='width: 180px; color: red; text-align: center;'><strong>Czerwone:&nbsp;"
				+ getvGamePad().amountPawn(CellCollor.RED) + "</p></strong><br />";
		return redCoun;
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

	public class DelayMove {
		private long currentTime;

		public DelayMove() {
			currentTime = System.currentTimeMillis();
		}

		public void delayM(long delay) {
			long ct = System.currentTimeMillis();
			while (currentTime + delay > ct)
				ct = System.currentTimeMillis();
		}
	}

	public void processClick(String x, String y) {
		int ypos = Integer.parseInt(x);
		int xpos = Integer.parseInt(y);

		int xcell = (xpos - xpos % (vGamePad.getSizeCell() + 1)) / (vGamePad.getSizeCell() + 1);
		int ycell = (ypos - ypos % (vGamePad.getSizeCell() + 1)) / (vGamePad.getSizeCell() + 1);
		if (xcell >= vGamePad.getSizeTable())
			xcell = vGamePad.getSizeTable() - 1;
		if (ycell >= vGamePad.getSizeTable())
			ycell = vGamePad.getSizeTable() - 1;

		switch (vGamePad.getCell(xcell, ycell)) {
		case WHITE:
			clearRedStop();
			if (vGamePad.isCorrectMovement(xcell, ycell, CellCollor.RED, false)) {
				vGamePad.setCell(xcell, ycell, CellCollor.PINK);
				gameStat = "<p style='color: red; text-align: center;'><strong>Zobacz swój ruch.</strong></p>";
			} else {
				gameStat = "<p style='color: red; text-align: center;'><strong>To nie jest poprawny ruch. Próbuj dalej!</strong></p>";
			}
			break;

		case GRAY:
			vGamePad.isCorrectMovement(xcell, ycell, CellCollor.BLACK, true);
			vGamePad.setCell(xcell, ycell, CellCollor.BLACK);
			if (vGamePad.possibleMove(CellCollor.RED)) {
				gameStat = "<p style='color: red; text-align: center;'><strong>Twój ruch! POWODZENIA</strong></p>";
			} else {
				gameStat = getFinalText();
			} {
			DelayMove delay = new DelayMove();
			delay.delayM(ReversiV5Const.getGraydelay());
		}
			break;
		case BLACK:
			// vGamePad.setCell(xcell, ycell, CellCollor.PINK);
			gameStat = "<p style='color: red; text-align: center;'><strong>To nie jest poprawny ruch. Próbuj dalej!</strong></p>";
			break;
		case PINK:
			vGamePad.isCorrectMovement(xcell, ycell, CellCollor.RED, true);
			if (vGamePad.possibleMove(CellCollor.BLACK)) {
				vGamePad.setCell(xcell, ycell, CellCollor.RED_START);
				gameStat = "<p style='color: black; text-align: center;'><strong>Teraz ruch wykonuje komputer.</strong></p>";
			} else {
				vGamePad.setCell(xcell, ycell, CellCollor.RED);
				gameStat = getFinalText();
			} {
			DelayMove delay = new DelayMove();
			delay.delayM(ReversiV5Const.getPinkdelay());
		}
			break;
		case RED_STOP:
			logger.info("RED_STOP: x=" + xcell + " y=" + ycell);
			vGamePad.setCell(xcell, ycell, CellCollor.RED);
		case RED:
			gameStat = "<p style='color: red; text-align: center;'><strong>To nie jest poprawny ruch. Próbuj dalej!</strong></p>";
			break;
		case RED_START:
			logger.info("RED_START: x=" + xcell + " y=" + ycell);
			vGamePad.setCell(xcell, ycell, CellCollor.RED);
			if (computerMove()) {
				gameStat = "<p style='color: red; text-align: center;'><strong>Zobacz ruch komputera.</strong></p>";
			} else {
				logger.error("RED_START: x=" + xcell + " y=" + ycell + "program had no right to be in this place");
				gameStat = "<p style='color: red; text-align: center;'><strong>Koniec gry. (THE END)</strong></p>";
			}
			// vGamePad.setCell(xcell, ycell, CellCollor.WHITE);
		}
	}

	public boolean computerMove() {
		XYPosition xyPosition = vGamePad.bestMove();
		if (xyPosition != null) {
			vGamePad.setCell(xyPosition.x, xyPosition.y, CellCollor.GRAY);
			return true;
		}
		gameStat = "Koniec gry";
		return false;
	}

	private void clearRedStop() {
		int size = vGamePad.getSizeTable();

		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				if ((vGamePad.getCell(i, j) == CellCollor.RED_STOP)
						|| (vGamePad.getCell(i, j) == CellCollor.RED_START)) {
					vGamePad.setCell(i, j, CellCollor.RED);
				}
			}
		}
	}

	private String getFinalText() {
		if (getvGamePad().amountPawn(CellCollor.RED) == getvGamePad().amountPawn(CellCollor.BLACK))
			return "<strong><p style='color: red; text-align: center;'><strong>Koniec gry. (THE END)</strong></p>"
					+ "<p style='color: red; text-align: center;'>Remis: " + getvGamePad().amountPawn(CellCollor.RED)
					+ " do <span style='color: black;'>" + getvGamePad().amountPawn(CellCollor.BLACK)
					+ "</span>!!! Zagraj jeszcze raz!</p></strong>";
		else if (getvGamePad().amountPawn(CellCollor.RED) > getvGamePad().amountPawn(CellCollor.BLACK))
			return "<strong><p style='color: red; text-align: center;'><strong>Koniec gry. (THE END)</strong><br />"
					+ "Wygrałeś: " + getvGamePad().amountPawn(CellCollor.RED) + " do <span style='color: black;'>"
					+ getvGamePad().amountPawn(CellCollor.BLACK) + "</span>!!! Zagraj jeszcze raz!</p></strong>";
		else
			return "<strong><p style='color: red; text-align: center;'><strong>Koniec gry. (THE END)</strong><br />"
					+ "<span style='color: black; text-align: center;'>Wygrał komputer: "
					+ getvGamePad().amountPawn(CellCollor.BLACK) + " do </span><span style='color: red;'>"
					+ getvGamePad().amountPawn(CellCollor.RED)
					+ "</span><span style='color: black; text-align: center;'> Zagraj jeszcze raz!</span></p></strong>";
	}

}
