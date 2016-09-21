package robert.reversi_v5web.services;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;

@Service
public class CurrentJavaSqlTimestamp {
	public Timestamp getCurrentJavaSqlTimestamp() {
//		final Logger logger = Logger.getLogger(CurrentJavaSqlTimestamp.class.getName());
//
//		TimeZone tz = TimeZone.getDefault();
//		logger.info("Identyfer: " + tz.getID());
//		logger.info("useDST: " + tz.useDaylightTime());
//		logger.info("dstSav: " + tz.getDSTSavings());
//		logger.info("defName: " + tz.getDisplayName());
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		Date currentDate = calendar.getTime();

		return new Timestamp(currentDate.getTime());
	}

}
