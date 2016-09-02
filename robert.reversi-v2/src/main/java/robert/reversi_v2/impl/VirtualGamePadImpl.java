package robert.reversi_v2.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import robert.reversi_v2.api.ComputerMove;
import robert.reversi_v2.api.VirtualGamePad;
import robert.reversi_v2.domain.CellCollor;
import robert.reversi_v2.domain.DeclareConstants;

public class VirtualGamePadImpl implements VirtualGamePad, ComputerMove {
	final static Logger logger = Logger.getLogger(robert.reversi_v2.impl.VirtualGamePadImpl.class);

	private CellCollor cColour = CellCollor.BLACK; // computer pawn colour
	private CellCollor[][] gameTable;
	private int sizeTable = DeclareConstants.getSizetable();
	private int sizeCell = DeclareConstants.getCellsize();
	private int sizeDiameter = DeclareConstants.getDiameter();

	public VirtualGamePadImpl() {
		gameTable = new CellCollor[sizeTable][sizeTable];
		clearGamePad();
	}

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

	public void setCell(int x, int y, CellCollor cellCollor) {
		gameTable[x][y] = cellCollor;
	}

	public CellCollor getCell(int x, int y) {
		return gameTable[x][y];
	}

	public boolean isCorrectMovement(int x, int y, CellCollor cellCollor, boolean makeMove) {
		if (isCorrectMovementWinsCount(x, y, cellCollor, makeMove) > 0)
			return true;
		return false;

		/*
		 * // nie możemy ustawić białego / pustego if (cellCollor ==
		 * CellCollor.WHITE) return false; // 1. czy jest puste pole if
		 * (getCell(x, y) != CellCollor.WHITE) return false;
		 * 
		 * // 2. czy jest w otoczeniu pon przeciwnika CellCollor opositeCollor =
		 * cellCollor == CellCollor.BLACK ? CellCollor.RED : CellCollor.BLACK;
		 * // 2.1 northCell boolean northCell = (getCell(x, y - 1 >= 0 ? y - 1 :
		 * 0) == opositeCollor) ? true : false; // 2.2 southCell boolean
		 * southCell = (getCell(x, y + 1 < this.sizeTable ? y + 1 :
		 * this.sizeTable - 1) == opositeCollor) ? true : false; // 2.3 eastCell
		 * boolean eastCell = (getCell(x - 1 >= 0 ? x - 1 : 0, y) ==
		 * opositeCollor) ? true : false; // 2.4 westCell boolean westCell =
		 * (getCell(x + 1 < this.sizeTable ? x + 1 : this.sizeTable - 1, y) ==
		 * opositeCollor) ? true : false;
		 * 
		 * // 2.5 northEastCell boolean northEastCell = (getCell(x - 1 >= 0 ? x
		 * - 1 : 0, y - 1 >= 0 ? y - 1 : 0) == opositeCollor) ? true : false; //
		 * 2.6 southEastCell boolean southEastCell = (getCell(x - 1 >= 0 ? x - 1
		 * : 0, y + 1 < this.sizeTable ? y + 1 : this.sizeTable - 1) ==
		 * opositeCollor) ? true : false; // 2.7 nortWesthCell boolean
		 * nortWesthCell = (getCell(x + 1 < this.sizeTable ? x + 1 :
		 * this.sizeTable - 1, y - 1 >= 0 ? y - 1 : 0) == opositeCollor) ? true
		 * : false; // 2.8 southWestCell boolean southWestCell = (getCell(x + 1
		 * < this.sizeTable ? x + 1 : this.sizeTable - 1, y + 1 < this.sizeTable
		 * ? y + 1 : this.sizeTable - 1) == opositeCollor) ? true : false;
		 * 
		 * if ((northCell || southCell || eastCell || westCell || northEastCell
		 * || southEastCell || nortWesthCell || southWestCell) == false) return
		 * false;
		 * 
		 * // 3. czy jest zamknięta linia // 3.1 northCell if (northCell) {
		 * northCell = false; for (int iy = y; iy >= 0; --iy) { if (getCell(x,
		 * iy) == cellCollor) { if (makeMove) { setCell(x, y, cellCollor); for
		 * (int iiy = y - 1; iiy >= 0; --iiy) { if (getCell(x, iiy) ==
		 * opositeCollor) { setCell(x, iiy, cellCollor);
		 * 
		 * } else { break; } } } northCell = true; break; } } }
		 * 
		 * // 3.2 southCell if (southCell) { southCell = false; for (int iy = y;
		 * iy < this.sizeTable; ++iy) { if (getCell(x, iy) == cellCollor) { if
		 * (makeMove) { setCell(x, y, cellCollor); for (int iiy = y + 1; iiy <
		 * this.sizeTable; ++iiy) { if (getCell(x, iiy) == opositeCollor) {
		 * setCell(x, iiy, cellCollor);
		 * 
		 * } else { break; } } } southCell = true; break; } } }
		 * 
		 * // 3.3 eastCell if (eastCell) { eastCell = false; for (int ix = x; ix
		 * >= 0; --ix) { if (getCell(ix, y) == cellCollor) { if (makeMove) {
		 * setCell(x, y, cellCollor); for (int iix = x - 1; iix >= 0; --iix) {
		 * if (getCell(iix, y) == opositeCollor) { setCell(iix, y, cellCollor);
		 * 
		 * } else { break; } } } eastCell = true; break; } } }
		 * 
		 * // 3.4 westCell if (westCell) { westCell = false; for (int ix = x; ix
		 * < this.sizeTable; ++ix) { if (getCell(ix, y) == cellCollor) { if
		 * (makeMove) { setCell(x, y, cellCollor); for (int iix = x + 1; iix <
		 * this.sizeTable; ++iix) { if (getCell(iix, y) == opositeCollor) {
		 * setCell(iix, y, cellCollor);
		 * 
		 * } else { break; } } } westCell = true; break; } } }
		 * 
		 * // 3.5 northEastCell if (northEastCell) { northEastCell = false; for
		 * (int ix = x, iy = y; ix >= 0 && iy >= 0; --ix, --iy) { if
		 * (getCell(ix, iy) == cellCollor) { if (makeMove) { setCell(x, y,
		 * cellCollor); for (int iix = x - 1, iiy = y - 1; iix >= 0 && iiy >= 0;
		 * --iix, --iiy) { if (getCell(iix, iiy) == opositeCollor) {
		 * setCell(iix, iiy, cellCollor);
		 * 
		 * } else { break; } } } northEastCell = true; break; } } }
		 * 
		 * // 3.6 southEastCell if (southEastCell) { southEastCell = false; for
		 * (int ix = x, iy = y; ix >= 0 && iy < this.sizeTable; --ix, ++iy) { if
		 * (getCell(ix, iy) == cellCollor) { if (makeMove) { setCell(x, y,
		 * cellCollor); for (int iix = x - 1, iiy = y + 1; iix >= 0 && iiy <
		 * this.sizeTable; --iix, ++iiy) { if (getCell(iix, iiy) ==
		 * opositeCollor) { setCell(iix, iiy, cellCollor);
		 * 
		 * } else { break; } } } southEastCell = true; break; } } }
		 * 
		 * // 3.7 nortWesthCell if (nortWesthCell) { nortWesthCell = false; for
		 * (int ix = x, iy = y; ix < this.sizeTable && iy >= 0; ++ix, --iy) { if
		 * (getCell(ix, iy) == cellCollor) { if (makeMove) { setCell(x, y,
		 * cellCollor); for (int iix = x + 1, iiy = y - 1; iix < this.sizeTable
		 * && iiy >= 0; ++iix, --iiy) { if (getCell(iix, iiy) == opositeCollor)
		 * { setCell(iix, iiy, cellCollor);
		 * 
		 * } else { break; } } } nortWesthCell = true; break; } } }
		 * 
		 * // 3.8 southWestCell if (southWestCell) { southWestCell = false; for
		 * (int ix = x, iy = y; ix < this.sizeTable && iy < this.sizeTable;
		 * ++ix, ++iy) { if (getCell(ix, iy) == cellCollor) { if (makeMove) {
		 * setCell(x, y, cellCollor); for (int iix = x + 1, iiy = y + 1; iix <
		 * this.sizeTable && iiy < this.sizeTable; ++iix, ++iiy) { if
		 * (getCell(iix, iiy) == opositeCollor) { setCell(iix, iiy, cellCollor);
		 * 
		 * } else { break; } } } southWestCell = true; break; } } }
		 * 
		 * // TODO Auto-generated method stub // return true; return northCell
		 * || southCell || eastCell || westCell || northEastCell ||
		 * southEastCell || nortWesthCell || southWestCell;
		 */

	}

	public boolean possibleMove(CellCollor cellCollor) {
		for (int i = 0; i < this.sizeTable; i++) {
			for (int j = 0; j < this.sizeTable; j++) {
				if (isCorrectMovement(i, j, cellCollor, false))
					return true;
			}
		}
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

	private XYPosition firsMove() {
		// TODO Auto-generated method stub
		return null;
	}

	public XYPosition bestMove() {
		int maxWin;
		int maxWinCount;
		XYPosition xyPosition = new XYPosition();
		Random r = new Random();

		List<XYPosition> moveList = getMoveList();
		if (moveList == null)
			return null;

		Collections.sort(moveList);
		xyPosition = moveList.get(0);
		maxWin = xyPosition.winsPawns + xyPosition.subjectiveWeight;
		maxWinCount = 0;
		for (XYPosition xyPosition2 : moveList) {
			if (xyPosition2.winsPawns + xyPosition2.subjectiveWeight < maxWin) {
				break;
			}
			++maxWinCount;
		}
		int myShot = r.nextInt(maxWinCount);

		xyPosition = moveList.get(myShot);
		return xyPosition;
	}

	public boolean makeBestConputerMove() {
		XYPosition xyPosition = bestMove();
		if (xyPosition.winsPawns > 0) {
			isCorrectMovement(xyPosition.x, xyPosition.y, cColour, true);
			// setCell(xyPosition.x, xyPosition.y, cColour);
			return true;
		}
		return false;
	}

	public List<XYPosition> getMoveList() {
		List<XYPosition> moveList = new ArrayList<ComputerMove.XYPosition>();
		int xyWin;

		for (int i = 0; i < this.sizeTable; i++) {
			for (int j = 0; j < this.sizeTable; j++) {
				if ((xyWin = isCorrectMovementWinsCount(i, j, cColour, false)) > 0) {
					XYPosition xyPosition = new XYPosition();
					xyPosition.winsPawns = xyWin;
					xyPosition.x = i;
					xyPosition.y = j;
					xyPosition.subjectiveWeight = i == 0 || j == 0 || i == sizeTable - 1 || j == sizeTable - 1 ? 1 : 0;
					if (xyPosition.subjectiveWeight != 0)
						logger.debug("Ustawiony xyPosition.subjectiveWeight: " + i + " " + j);
					moveList.add(xyPosition);
				}
			}
		}
		if (!moveList.isEmpty())
			return moveList;
		return null;
	}

	private int isCorrectMovementWinsCount(int x, int y, CellCollor cellCollor, boolean makeMove) {
		// nie możemy ustawić białego / pustego
		if (cellCollor == CellCollor.WHITE)
			return 0;
		// 1. czy jest puste pole
		if (getCell(x, y) != CellCollor.WHITE && getCell(x, y) != CellCollor.PINK && getCell(x, y) != CellCollor.GRAY)
			return 0;

		// 2. czy jest w otoczeniu pon przeciwnika
		CellCollor opositeCollor = cellCollor == CellCollor.BLACK ? CellCollor.RED : CellCollor.BLACK;
		// 2.1 northCell
		boolean northCell = (getCell(x, y - 1 >= 0 ? y - 1 : 0) == opositeCollor) ? true : false;
		// 2.2 southCell
		boolean southCell = (getCell(x, y + 1 < this.sizeTable ? y + 1 : this.sizeTable - 1) == opositeCollor) ? true
				: false;
		// 2.3 eastCell
		boolean eastCell = (getCell(x - 1 >= 0 ? x - 1 : 0, y) == opositeCollor) ? true : false;
		// 2.4 westCell
		boolean westCell = (getCell(x + 1 < this.sizeTable ? x + 1 : this.sizeTable - 1, y) == opositeCollor) ? true
				: false;

		// 2.5 northEastCell
		boolean northEastCell = (getCell(x - 1 >= 0 ? x - 1 : 0, y - 1 >= 0 ? y - 1 : 0) == opositeCollor) ? true
				: false;
		// 2.6 southEastCell
		boolean southEastCell = (getCell(x - 1 >= 0 ? x - 1 : 0,
				y + 1 < this.sizeTable ? y + 1 : this.sizeTable - 1) == opositeCollor) ? true : false;
		// 2.7 nortWesthCell
		boolean nortWesthCell = (getCell(x + 1 < this.sizeTable ? x + 1 : this.sizeTable - 1,
				y - 1 >= 0 ? y - 1 : 0) == opositeCollor) ? true : false;
		// 2.8 southWestCell
		boolean southWestCell = (getCell(x + 1 < this.sizeTable ? x + 1 : this.sizeTable - 1,
				y + 1 < this.sizeTable ? y + 1 : this.sizeTable - 1) == opositeCollor) ? true : false;

		if ((northCell || southCell || eastCell || westCell || northEastCell || southEastCell || nortWesthCell
				|| southWestCell) == false)
			return 0;

		int winsPawn = 0;

		// 3. czy jest zamknięta linia
		// 3.1 northCell
		if (northCell) {
			northCell = false;
			for (int iy = y; iy >= 0; --iy) {
				if (iy != y && (getCell(x, iy) == CellCollor.WHITE))
					break;
				if (getCell(x, iy) == cellCollor) {
					for (int iiy = y - 1; iiy >= 0; --iiy) {
						if (getCell(x, iiy) == opositeCollor) {
							if (makeMove) {
								setCell(x, iiy, cellCollor);
							}
							winsPawn++;
						} else {
							break;
						}
					}
					northCell = true;
					break;
				}
			}
		}

		// 3.2 southCell
		if (southCell) {
			southCell = false;
			for (int iy = y; iy < this.sizeTable; ++iy) {
				if (iy != y && (getCell(x, iy) == CellCollor.WHITE))
					break;
				if (getCell(x, iy) == cellCollor) {
					for (int iiy = y + 1; iiy < this.sizeTable; ++iiy) {
						if (getCell(x, iiy) == opositeCollor) {
							if (makeMove) {
								setCell(x, iiy, cellCollor);
							}
							winsPawn++;
						} else {
							break;
						}
					}
					southCell = true;
					break;
				}
			}
		}

		// 3.3 eastCell
		if (eastCell) {
			eastCell = false;
			for (int ix = x; ix >= 0; --ix) {
				if (ix != x && (getCell(ix, y) == CellCollor.WHITE))
					break;
				if (getCell(ix, y) == cellCollor) {
					for (int iix = x - 1; iix >= 0; --iix) {
						if (getCell(iix, y) == opositeCollor) {
							if (makeMove) {
								setCell(iix, y, cellCollor);
							}
							winsPawn++;
						} else {
							break;
						}
					}
					eastCell = true;
					break;
				}
			}
		}

		// 3.4 westCell
		if (westCell)

		{
			westCell = false;
			for (int ix = x; ix < this.sizeTable; ++ix) {
				if (ix != x && (getCell(ix, y) == CellCollor.WHITE))
					break;
				if (getCell(ix, y) == cellCollor) {
					for (int iix = x + 1; iix < this.sizeTable; ++iix) {
						if (getCell(iix, y) == opositeCollor) {
							if (makeMove) {
								setCell(iix, y, cellCollor);
							}
							winsPawn++;
						} else {
							break;
						}
					}
					westCell = true;
					break;
				}
			}
		}

		// 3.5 northEastCell
		if (northEastCell) {
			northEastCell = false;
			for (int ix = x, iy = y; ix >= 0 && iy >= 0; --ix, --iy) {
				if (ix != x && (getCell(ix, iy) == CellCollor.WHITE))
					break;
				if (getCell(ix, iy) == cellCollor) {
					for (int iix = x - 1, iiy = y - 1; iix >= 0 && iiy >= 0; --iix, --iiy) {
						if (getCell(iix, iiy) == opositeCollor) {
							if (makeMove) {
								setCell(iix, iiy, cellCollor);
							}
							winsPawn++;
						} else {
							break;
						}
					}
					northEastCell = true;
					break;
				}
			}
		}

		// 3.6 southEastCell
		if (southEastCell) {
			southEastCell = false;
			for (int ix = x, iy = y; ix >= 0 && iy < this.sizeTable; --ix, ++iy) {
				if (ix != x && (getCell(ix, iy) == CellCollor.WHITE))
					break;
				if (getCell(ix, iy) == cellCollor) {
					for (int iix = x - 1, iiy = y + 1; iix >= 0 && iiy < this.sizeTable; --iix, ++iiy) {
						if (getCell(iix, iiy) == opositeCollor) {
							if (makeMove) {
								setCell(iix, iiy, cellCollor);
							}
							winsPawn++;
						} else {
							break;
						}
					}
					southEastCell = true;
					break;
				}
			}
		}

		// 3.7 nortWesthCell
		if (nortWesthCell) {
			nortWesthCell = false;
			for (int ix = x, iy = y; ix < this.sizeTable && iy >= 0; ++ix, --iy) {
				if (ix != x && (getCell(ix, iy) == CellCollor.WHITE))
					break;
				if (getCell(ix, iy) == cellCollor) {
					for (int iix = x + 1, iiy = y - 1; iix < this.sizeTable && iiy >= 0; ++iix, --iiy) {
						if (getCell(iix, iiy) == opositeCollor) {
							if (makeMove) {
								setCell(iix, iiy, cellCollor);
							}
							winsPawn++;
						} else {
							break;
						}
					}
					nortWesthCell = true;
					break;
				}
			}
		}

		// 3.8 southWestCell
		if (southWestCell) {
			southWestCell = false;
			for (int ix = x, iy = y; ix < this.sizeTable && iy < this.sizeTable; ++ix, ++iy) {
				if (ix != x && (getCell(ix, iy) == CellCollor.WHITE))
					break;
				if (getCell(ix, iy) == cellCollor) {
					for (int iix = x + 1, iiy = y + 1; iix < this.sizeTable && iiy < this.sizeTable; ++iix, ++iiy) {
						if (getCell(iix, iiy) == opositeCollor) {
							if (makeMove) {
								setCell(iix, iiy, cellCollor);
							}
							winsPawn++;
						} else {
							break;
						}
					}
					southWestCell = true;
					break;
				}
			}
		}
		if (makeMove && winsPawn > 0) {
			setCell(x, y, cellCollor);
		}
		return winsPawn;
	}

}
