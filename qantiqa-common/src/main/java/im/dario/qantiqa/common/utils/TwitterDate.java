package im.dario.qantiqa.common.utils;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

/**
 * Helper to centralize how dates are handled by Qantiqa, based on Twitter
 * standard.
 * 
 * @author Dario
 * 
 */
public class TwitterDate implements Serializable, Comparable<TwitterDate> {

	private static final long serialVersionUID = 1360664926408873786L;

	private final static DateTimeFormatter twitter = new DateTimeFormatterBuilder()
			.appendDayOfWeekShortText().appendLiteral(' ')
			.appendMonthOfYearShortText().appendLiteral(' ')
			.appendDayOfMonth(2).appendLiteral(' ').appendHourOfDay(2)
			.appendLiteral(':').appendMinuteOfHour(2).appendLiteral(':')
			.appendSecondOfMinute(2).appendLiteral(" +0000 ").appendYear(4, 4)
			.toFormatter();

	/**
	 * Minimum value possible used internally. Based on UNIX epoch.
	 */
	public static final TwitterDate MIN_VALUE = new TwitterDate(new DateTime(
			1970, 1, 1, 0, 0, 0, 0));
	public static final TwitterDate MAX_VALUE = new TwitterDate(new DateTime(
			2999, 12, 31, 23, 59, 59, 999));

	private DateTime dt;

	public TwitterDate() {
		dt = new DateTime(DateTimeZone.UTC);
	}

	public TwitterDate(DateTime dt) {
		this.dt = dt;
	}

	@Override
	public int compareTo(TwitterDate o) {
		return dt.compareTo(o.dt);
	}

	public String getTimezone() {
		return DateTimeZone.getDefault().getName(dt.getMillis());
	}

	public int getUtcOffset() {
		return DateTimeZone.getDefault().getOffset(dt.getMillis());
	}

	public boolean hasExpired(int timeout) {
		DateTime expire = dt.plusSeconds(timeout);

		return expire.isAfterNow();
	}

	public String toString() {
		return twitter.print(dt);
	}

	public static TwitterDate parse(String data) {
		return new TwitterDate(twitter.parseDateTime(data));
	}
}
