package robert.reversi_v2.domain;

public final class DeclareConstants {
	private static final int CELLSIZE = 48; // parzysta - PROSZĘ, one cell
											// dimension
	private final static int SIZETABLE = 8; // game pad (chessboard) size
	// dokładnie margines pomiędzy granicą pola a okręgiem ;). Parzysta - PROSZĘ
	private static final int DIAMETER = 4;

	public static int getSizetable() {
		return SIZETABLE;
	}

	public static int getCellsize() {
		return CELLSIZE;
	}

	public static int getDiameter() {
		return DIAMETER;
	}

}