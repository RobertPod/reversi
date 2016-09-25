package robert.reversi_v5web.impl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import robert.reversi_v2.domain.CellCollor;

/**
 * @author Robert
 * @version 1.9.4
 * @since 2016-09-25
 * 
 * Game move description
 */
// @Entity
@Table(name = "gameMove")
public class GameMoveSprDataImpl {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long moveId;
	@ManyToOne
	@JoinColumn(name = "gameId")
	private long gameId;
	private CellCollor pawnColor;
	private int x;
	private int y;

	public long getMoveId() {
		return moveId;
	}

	public void setMoveId(long moveId) {
		this.moveId = moveId;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public CellCollor getPawnColor() {
		return pawnColor;
	}

	public void setPawnColor(CellCollor pawnColor) {
		this.pawnColor = pawnColor;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
