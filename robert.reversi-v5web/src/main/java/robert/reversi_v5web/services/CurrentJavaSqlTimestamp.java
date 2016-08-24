package robert.reversi_v5web.services;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

@Service
public class CurrentJavaSqlTimestamp {
	public Timestamp getCurrentJavaSqlTimestamp() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		Date currentDate = calendar.getTime();
		
		return new Timestamp(currentDate.getTime());
	}

}
