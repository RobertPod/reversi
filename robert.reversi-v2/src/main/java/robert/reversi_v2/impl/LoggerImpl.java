package robert.reversi_v2.impl;

import java.util.Date;

import robert.reversi_v2.api.Logger;

public class LoggerImpl implements Logger {

	public void log(String message) {
		System.out.println(new Date() + ": " + message);
	}
}
