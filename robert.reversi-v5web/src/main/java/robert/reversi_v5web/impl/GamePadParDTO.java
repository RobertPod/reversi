package robert.reversi_v5web.impl;

import static robert.reversi_v2.domain.DeclareConstants.SIZETABLE;
import static robert.reversi_v2.domain.DeclareConstants.CELLSIZE;

public class GamePadParDTO {
	private static final Integer width = SIZETABLE * CELLSIZE + SIZETABLE;
	private static final Integer height = SIZETABLE * CELLSIZE + SIZETABLE;
	private static final Integer cellWidth = CELLSIZE;
	private static final Integer cellHeight = CELLSIZE;
	private static final Integer sizeX = SIZETABLE;
	private static final Integer sizeY = SIZETABLE;
	private Integer x;
	private Integer y;

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}

	public Integer getCellWidth() {
		return cellWidth;
	}

	public Integer getCellHeight() {
		return cellHeight;
	}

	public Integer getSizeX() {
		return sizeX;
	}

	public Integer getSizeY() {
		return sizeY;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public void setX(String x) {
		this.x = Integer.valueOf(x);
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public void setY(String y) {
		this.y = Integer.valueOf(y);
	}
}
