package robert.reversi_v2.api;

import java.util.List;

public interface ComputerMove {
	public class XYPosition implements Comparable<XYPosition> {
		public int x;
		public int y;
		public int winsPawns;
		public int subjectiveWeight;

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			XYPosition other = (XYPosition) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		public int compareTo(XYPosition o) {
			return (o.winsPawns + o.subjectiveWeight) - (this.winsPawns + this.subjectiveWeight);
		}
	}

	XYPosition bestMove();

	List<XYPosition> getMoveList();

	// List<XYPosition> getMoveListwithEfect(List<XYPosition> moveList);
}
