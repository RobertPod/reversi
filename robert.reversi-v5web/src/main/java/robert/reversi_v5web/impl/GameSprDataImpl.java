package robert.reversi_v5web.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import robert.reversi_v2.domain.CellCollor;

/**
 * @author Robert
 * @version 1.9.4
 * @since 2016-09-25
 * 
 *        Game description
 */
// @Entity
@Table(name = "game")
public class GameSprDataImpl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long gameId;
	private CellCollor firstMoveColor;
	@OneToMany
	// http://viralpatel.net/blogs/hibernate-one-to-many-annotation-tutorial/
	// https://www.mkyong.com/hibernate/hibernate-one-to-many-relationship-example-annotation/
	// https://en.wikibooks.org/wiki/Java_Persistence/OneToMany
	private List<GameMoveSprDataImpl> gameMoves;

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public CellCollor getFirstMoveColor() {
		return firstMoveColor;
	}

	public void setFirstMoveColor(CellCollor firstMoveColor) {
		this.firstMoveColor = firstMoveColor;
	}

	public List<GameMoveSprDataImpl> getGameMoves() {
		return gameMoves;
	}

	public void setGameMoves(List<GameMoveSprDataImpl> gameMoves) {
		this.gameMoves = gameMoves;
	}

}
