package robert.reversi_v5web.domain;

import java.sql.Timestamp;
import java.util.Date;

public class CurrentJavaSqlTimestamp {
	public Timestamp getCurrentJavaSqlTimestamp() {
		Date date = new Date();
		return new Timestamp(date.getTime());
	}

}
