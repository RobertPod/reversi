package robert.reversi_v2.api;

import java.util.List;

public interface ComputerMove {
	public class XYPosition implements Comparable<XYPosition> {
		public int x;
		public int y;
		public int winsPawns;

		public int compareTo(XYPosition o) {
			return o.winsPawns - winsPawns;
		}
	}

	XYPosition bestMove();

	List<XYPosition> getMoveList();

	// List<XYPosition> getMoveListwithEfect(List<XYPosition> moveList);
}
