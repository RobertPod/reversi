package robert.reversi_v5web.impl;

import robert.reversi_v5web.services.GameService;

public class GamePadParDTO {
	private static Integer width;
	private static Integer height;
	private static Integer cellWidth;
	private static Integer cellHeight;
	private static Integer sizeX;
	private static Integer sizeY;
	private Integer x;
	private Integer y;

	public GamePadParDTO(GameService gameService) {
		GamePadParDTO.width = gameService.getSizeTable() * gameService.getSizeCell() + gameService.getSizeTable(); // SIZETABLE
																													// *
																													// CELLSIZE
																													// +
																													// SIZETABLE
		GamePadParDTO.height = gameService.getSizeTable() * gameService.getSizeCell() + gameService.getSizeTable(); // SIZETABLE
																													// *
																													// CELLSIZE
																													// +
																													// SIZETABLE
		GamePadParDTO.cellWidth = gameService.getSizeCell(); // CELLSIZE;
		GamePadParDTO.cellHeight = gameService.getSizeCell(); // CELLSIZE;
		GamePadParDTO.sizeX = gameService.getSizeTable(); // SIZETABLE;
		GamePadParDTO.sizeY = gameService.getSizeTable(); // SIZETABLE;
	}

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
