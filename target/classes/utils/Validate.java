package utils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);
	public static boolean isEmail(String email) {
		Matcher m = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return m.matches();
	}

	public static Date convertDates(String date) {
		String[] dateArray = date.split("/");
		if (dateArray.length != 3) {
			return null;
		}
		int day, month, year;
		year = Integer.parseInt(dateArray[2]);
		month = Integer.parseInt(dateArray[1]);
		day = Integer.parseInt(dateArray[0]);
		try {
			return Date.valueOf(LocalDate.of(year, month, day));
		} catch (Exception e) {
			return null;
		}
	}
}
