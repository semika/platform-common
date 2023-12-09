package iit.ase.cw.platform.common.util.toolkit;

import iit.ase.cw.platform.common.constant.ThaproGlobalConstant;
import iit.ase.cw.platform.common.util.TimeStamp;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.StringJoiner;
import java.util.function.Function;

@SuppressWarnings("checkstyle:MethodCount")
public final class DateToolkit {

    private static final String INVALID_DATE = "Invalid Date";

    private DateToolkit() {
    }

    /**
     * get Date with time.
     *
     * @return
     */
    public static Date getTimeUnremovedDate() {
        Calendar calendar = Calendar.getInstance();
        Date newDate = calendar.getTime();
        return newDate;
    }

    /**
     * get new date with remove time. set time to 12 NOON
     *
     * @return
     */
    public static Date getNewDate() {
        Calendar calendar = Calendar.getInstance();
        Date newDate = calendar.getTime();
        return removeTime(newDate);
    }

    /**
     * get new date to give year, month and day
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date getNewDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, day);
        Date newDate = calendar.getTime();
        return removeTime(newDate);
    }

    public static Date getNewDate(LocalDate localDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, localDate.getYear());
        calendar.set(Calendar.MONTH, localDate.getMonthValue() - 1);
        calendar.set(Calendar.DATE, localDate.getDayOfMonth());
        Date newDate = calendar.getTime();
        return removeTime(newDate);
    }

    public static Date getNewDate(LocalDateTime localDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, localDate.getYear());
        calendar.set(Calendar.MONTH, localDate.getMonthValue() - 1);
        calendar.set(Calendar.DATE, localDate.getDayOfMonth());
        calendar.set(Calendar.HOUR_OF_DAY, localDate.getHour());
        calendar.set(Calendar.MINUTE, localDate.getMinute());
        calendar.set(Calendar.SECOND, localDate.getSecond());
        return calendar.getTime();
    }

    public static LocalDate getNewLocalDate() {
        Calendar calendar = Calendar.getInstance();
        return getNewLocalDate(calendar);
    }

    public static LocalDate getNewLocalDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getNewLocalDate(calendar);
    }

    public static LocalDate getNewLocalDate(Calendar calendar) {
        int monthValue = calendar.get(Calendar.MONTH) + 1;
        return LocalDate.of(calendar.get(Calendar.YEAR), monthValue, calendar.get(Calendar.DATE));
    }

    public static LocalDateTime getNewLocalDateTime() {
        Calendar calendar = Calendar.getInstance();
        return getNewLocalDateTime(calendar);
    }

    public static LocalDateTime getNewLocalDateTime(LocalDate localDate, LocalTime localTime) {
        LocalTime formatted = localTime == null ? LocalTime.of(12, 0, 0) : localTime;
        return LocalDateTime.of(localDate, formatted);
    }

    public static LocalDateTime getNewLocalDateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getNewLocalDateTime(calendar);
    }

    public static LocalDateTime getNewLocalDateTime(Calendar calendar) {
        LocalDate date = getNewLocalDate(calendar);
        LocalTime time = getNewLocalTime(calendar);
        return LocalDateTime.of(date, time);
    }

    public static LocalTime getNewLocalTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getNewLocalTime(calendar);
    }

    public static LocalTime getNewLocalTime(Calendar calendar) {
        return LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
            calendar.get(Calendar.SECOND));
    }

    public static int getGapInSeconds(Date one, Date two) {
        return getGapInSeconds(getNewLocalDateTime(one), getNewLocalDateTime(two));
    }

    public static int getGapInSeconds(LocalDateTime one, LocalDateTime two) {
        LocalDateTime tempDateTime = LocalDateTime.from(one).truncatedTo(ChronoUnit.SECONDS);
        int gapInYears = getGapInYears(tempDateTime, two);
        tempDateTime = tempDateTime.plusYears(gapInYears);
        int gapInMonths = getGapInMonths(tempDateTime, two);
        tempDateTime = tempDateTime.plusMonths(gapInMonths);
        int gapInDays = getGapInDays(tempDateTime, two);
        tempDateTime = tempDateTime.plusDays(gapInDays);
        int gapInHours = getGapInHours(tempDateTime, two);
        tempDateTime = tempDateTime.plusHours(gapInHours);
        int gapInMintes = getGapInMinutes(tempDateTime, two);
        tempDateTime = tempDateTime.plusMinutes(gapInMintes);
        return Long.valueOf(tempDateTime.until(two.truncatedTo(ChronoUnit.SECONDS), ChronoUnit.SECONDS)).intValue();
    }

    public static int getGapInMinutes(Date one, Date two) {
        return getGapInMinutes(getNewLocalDateTime(one), getNewLocalDateTime(two));
    }

    public static int getGapInMinutes(LocalDateTime one, LocalDateTime two) {
        LocalDateTime tempDateTime = LocalDateTime.from(one).truncatedTo(ChronoUnit.MINUTES);
        int gapInYears = getGapInYears(tempDateTime, two);
        tempDateTime = tempDateTime.plusYears(gapInYears);
        int gapInMonths = getGapInMonths(tempDateTime, two);
        tempDateTime = tempDateTime.plusMonths(gapInMonths);
        int gapInDays = getGapInDays(tempDateTime, two);
        tempDateTime = tempDateTime.plusDays(gapInDays);
        int gapInHours = getGapInHours(tempDateTime, two);
        tempDateTime = tempDateTime.plusHours(gapInHours);
        return Long.valueOf(tempDateTime.until(two.truncatedTo(ChronoUnit.MINUTES), ChronoUnit.MINUTES)).intValue();
    }

    public static int getGapInHours(Date one, Date two) {
        return getGapInHours(getNewLocalDateTime(one), getNewLocalDateTime(two));
    }

    public static int getGapInHours(LocalDateTime one, LocalDateTime two) {
        LocalDateTime tempDateTime = LocalDateTime.from(one).truncatedTo(ChronoUnit.HOURS);
        int gapInYears = getGapInYears(tempDateTime, two);
        tempDateTime = tempDateTime.plusYears(gapInYears);
        int gapInMonths = getGapInMonths(tempDateTime, two);
        tempDateTime = tempDateTime.plusMonths(gapInMonths);
        int gapInDays = getGapInDays(tempDateTime, two);
        tempDateTime = tempDateTime.plusDays(gapInDays);
        return Long.valueOf(tempDateTime.until(two.truncatedTo(ChronoUnit.HOURS), ChronoUnit.HOURS)).intValue();
    }

    public static int getGapInDays(Date one, Date two) {
        return getGapInDays(getNewLocalDateTime(one), getNewLocalDateTime(two));
    }

    public static int getGapInDays(LocalDate one, LocalDate two) {
        LocalDateTime first = LocalDateTime.of(one, LocalTime.of(12, 30));
        LocalDateTime second = LocalDateTime.of(two, LocalTime.of(12, 30));
        return getGapInDays(first, second);
    }

    public static int getGapInDays(LocalDateTime one, LocalDateTime two) {
        LocalDateTime tempDateTime = LocalDateTime.from(one).truncatedTo(ChronoUnit.DAYS);
        int gapInYears = getGapInYears(tempDateTime, two);
        tempDateTime = tempDateTime.plusYears(gapInYears);
        int gapInMonths = getGapInMonths(tempDateTime, two);
        tempDateTime = tempDateTime.plusMonths(gapInMonths);
        return Long.valueOf(tempDateTime.until(two.truncatedTo(ChronoUnit.DAYS), ChronoUnit.DAYS)).intValue();
    }

    public static int getGapInMonths(Date one, Date two) {
        return getGapInMonths(getNewLocalDateTime(one), getNewLocalDateTime(two));
    }

    public static int getGapInMonths(LocalDate one, LocalDate two) {
        LocalDateTime first = LocalDateTime.of(one, LocalTime.of(12, 30));
        LocalDateTime second = LocalDateTime.of(two, LocalTime.of(12, 30));
        return getGapInMonths(first, second);
    }

    public static int getGapInMonths(LocalDateTime one, LocalDateTime two) {
        LocalDateTime tempDateTime = LocalDateTime.from(one);
        int gapInYears = getGapInYears(tempDateTime, two);
        tempDateTime = tempDateTime.plusYears(gapInYears);
        return Long.valueOf(tempDateTime.until(two, ChronoUnit.MONTHS)).intValue();
    }

    public static int getGapInYears(Date one, Date two) {
        return getGapInYears(getNewLocalDateTime(one), getNewLocalDateTime(two));
    }

    public static int getGapInYears(LocalDateTime one, LocalDateTime two) {
        return Long.valueOf(one.until(two, ChronoUnit.YEARS)).intValue();
    }

    public static String localDateToStandardString(LocalDate date) {
        return localDateToString(date, ThaproGlobalConstant.Symbol.COLON);
    }

    public static String localDateToString(LocalDate date, String delimiter) {
        if (null != date) {
            StringJoiner joiner = new StringJoiner(delimiter);
            joiner.add(Integer.toString(date.getYear()));
            joiner.add(Integer.toString(date.getMonthValue()));
            joiner.add(Integer.toString(date.getDayOfMonth()));
            return joiner.toString();
        }
        return ThaproGlobalConstant.Symbol.EMPTY_STR;
    }

    public static LocalDate localDateByStandard(String localDateStr) {
        return localDateByString(localDateStr, ThaproGlobalConstant.Symbol.COLON);
    }

    public static LocalDate localDateByString(String localDateStr, String delimiter) {
        if (StringToolkit.isNotEmpty(localDateStr) && localDateStr.contains(delimiter)) {
            String[] attributes = localDateStr.split(delimiter);
            if (attributes.length == 3) {
                return LocalDate.of(NumberToolkit.parseInt(attributes[0]), NumberToolkit.parseInt(attributes[1]),
                    NumberToolkit.parseInt(attributes[2]));
            }
        }
        return null;
    }

    public static String localDateTimeToStandardString(LocalDateTime dateTime) {
        return localDateTimeToString(dateTime, ThaproGlobalConstant.Symbol.COLON);
    }

    public static String localDateTimeToString(LocalDateTime date, String delimiter) {
        if (null != date) {
            StringJoiner joiner = new StringJoiner(delimiter);
            joiner.add(Integer.toString(date.getYear()));
            joiner.add(Integer.toString(date.getMonthValue()));
            joiner.add(Integer.toString(date.getDayOfMonth()));
            joiner.add(Integer.toString(date.getHour()));
            joiner.add(Integer.toString(date.getMinute()));
            joiner.add(Integer.toString(date.getSecond()));
            return joiner.toString();
        }
        return ThaproGlobalConstant.Symbol.EMPTY_STR;
    }

    public static LocalDateTime localDateTimeByStandard(String localDateStr) {
        return localDateTimeByString(localDateStr, ThaproGlobalConstant.Symbol.COLON);
    }

    public static LocalDateTime localDateTimeByString(String localDateStr, String delimiter) {
        if (StringToolkit.isNotEmpty(localDateStr) && localDateStr.contains(delimiter)) {
            String[] attributes = localDateStr.split(delimiter);
            if (attributes.length == 6) {
                return LocalDateTime.of(
                    NumberToolkit.parseInt(attributes[0]), NumberToolkit.parseInt(attributes[1]),
                    NumberToolkit.parseInt(attributes[2]),
                    NumberToolkit.parseInt(attributes[3]), NumberToolkit.parseInt(attributes[4]),
                    NumberToolkit.parseInt(attributes[5]));
            }
        }
        return null;
    }

    public static String localTimeToStandardString(LocalTime localTime) {
        return localTimeToString(localTime, ThaproGlobalConstant.Symbol.COLON);
    }

    public static String localTimeToString(LocalTime localTime, String delimiter) {
        if (null != localTime) {
            StringJoiner joiner = new StringJoiner(delimiter);
            joiner.add(Integer.toString(localTime.getHour()));
            joiner.add(Integer.toString(localTime.getMinute()));
            joiner.add(Integer.toString(localTime.getSecond()));
            return joiner.toString();
        }
        return ThaproGlobalConstant.Symbol.EMPTY_STR;
    }

    public static LocalTime localTimeByStandard(String localDateStr) {
        return localTimeByString(localDateStr, ThaproGlobalConstant.Symbol.COLON);
    }

    public static LocalTime localTimeByString(String localTimeStr, String delimiter) {
        if (StringToolkit.isNotEmpty(localTimeStr) && localTimeStr.contains(delimiter)) {
            String[] attributes = localTimeStr.split(delimiter);
            if (attributes.length == 3) {
                return LocalTime.of(
                    NumberToolkit.parseInt(attributes[0]), NumberToolkit.parseInt(attributes[1]),
                    NumberToolkit.parseInt(attributes[2]));
            } else if (attributes.length == 2) {
                return LocalTime.of(NumberToolkit.parseInt(attributes[0]), NumberToolkit.parseInt(attributes[1]));
            }
        }
        return null;
    }

    public static Date removeTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * set time to 0.
     *
     * @param date
     * @return
     */
    public static Date setTimeToZero(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Calendar getNow() {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        return instance;
    }

    public static Calendar getFormattedNow() {
        Calendar instance = Calendar.getInstance();
        Date date = new Date();
        instance.setTime(removeTime(date));
        return instance;
    }

    /**
     * get year of the date.
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);

            return calendar.get(Calendar.YEAR);
        }
        return 0;
    }

    /**
     * get month of the date.
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.MONTH);
    }

    /**
     * get current date (today) to given format.
     *
     * @param format
     * @return
     */
    public static String getFormattedToday(String format) {
        return formatDate(new Date(), format);
    }

    /**
     * format give date to default pattern yyyy-MM-dd hh:mm.ss
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date, ThaproGlobalConstant.DateFormat.DEFAULT_PATTERN);
    }

    /**
     * format date to give patern.
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null || StringToolkit.isEmpty(pattern)) {
            return ThaproGlobalConstant.Symbol.EMPTY_STR;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     * get date from given string with default pattern yyyy-MM-dd hh:mm.ss
     *
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        return parseDate(date, ThaproGlobalConstant.DateFormat.DEFAULT_PATTERN);
    }

    /**
     * get date from given string for give pattern.
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date parseDate(String date, String pattern) {
        String varPattern = StringToolkit.isEmpty(pattern) ? ThaproGlobalConstant.DateFormat.DEFAULT_PATTERN : pattern;
        if (StringToolkit.isEmpty(varPattern)) {
            varPattern = ThaproGlobalConstant.DateFormat.DEFAULT_PATTERN;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(varPattern);
        return formatter.parse(date, new ParsePosition(0));
    }

    /**
     * get first date of the given date.
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfMonth(Date date) {

        if (date == null) {
            throw new RuntimeException(INVALID_DATE);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int lastDateofMonth = calendar.getActualMinimum(Calendar.DATE);
        calendar.set(Calendar.DATE, lastDateofMonth);
        return calendar.getTime();
    }

    public static LocalDate getFirstDateOfMonth(LocalDate date) {

        if (date == null) {
            throw new RuntimeException(INVALID_DATE);
        }
        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());
        return yearMonth.atDay(1);
    }

    public static LocalDateTime getFirstDateOfMonth(LocalDateTime date) {

        LocalDate localDate = getFirstDateOfMonth(date.toLocalDate());
        return LocalDateTime.of(localDate, date.toLocalTime());
    }

    /**
     * get last day of month for given date. .
     *
     * @param date
     * @return
     */
    public static Date getLastDateOfMonth(Date date) {

        if (date == null) {
            throw new RuntimeException(INVALID_DATE);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int lastDateofMonth = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DATE, lastDateofMonth);
        return calendar.getTime();
    }

    public static LocalDate getLastDateOfMonth(LocalDate date) {

        if (date == null) {
            throw new RuntimeException(INVALID_DATE);
        }
        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());
        return yearMonth.atEndOfMonth();
    }

    public static LocalDateTime getLastDateOfMonth(LocalDateTime date) {
        LocalDate localDate = getLastDateOfMonth(date.toLocalDate());
        return LocalDateTime.of(localDate, date.toLocalTime());
    }

    /**
     * check given dates are equal.
     *
     * @param firstDateParam
     * @param firstDateParam
     * @return
     */
    public static boolean isDateEqual(Date firstDateParam, Date secDateParam) {
        if (firstDateParam == null || secDateParam == null) {
            return false;
        }
        Date firstDate = parseDate(formatDate(firstDateParam));
        Date secDate = parseDate(formatDate(secDateParam));
        if (firstDate.equals(secDate)) {
            return true;
        }
        return false;
    }

    /**
     * return true if firstDate is before secDate
     *
     * @param firstDate
     * @param secDate
     * @return
     */
    public static boolean isDateBefore(Date firstDate, Date secDate) {
        if (firstDate == null || secDate == null) {
            return false;
        }
        if (firstDate.before(secDate)) {
            return true;
        }
        return false;
    }

    /**
     * return true if firstDate is after secDate
     *
     * @param firstDate
     * @param secDate
     * @return
     */
    public static boolean isDateAfter(Date firstDate, Date secDate) {
        if (firstDate == null || secDate == null) {
            return false;
        }
        if (firstDate.after(secDate)) {
            return true;
        }
        return false;
    }

    /**
     * return true if first date is before or same date for given sec date.
     *
     * @param firstDate
     * @param secDate
     * @return
     */
    public static boolean isBeforeOrEqual(Date firstDate, Date secDate) {
        return isDateBefore(firstDate, secDate) || isDateEqual(firstDate, secDate);
    }

    public static boolean isBeforeOrEqual(LocalDate firstDate, LocalDate secDate) {
        return firstDate.isBefore(secDate) || firstDate.isEqual(secDate);
    }

    public static boolean isBeforeOrEqual(LocalDateTime firstDate, LocalDateTime secDate) {
        return firstDate.isBefore(secDate) || firstDate.isEqual(secDate);
    }

    public static boolean isBeforeOrEqual(LocalTime firstTime, LocalTime secondTime) {
        return firstTime.isBefore(secondTime) || isLocalTimeEqual(firstTime, secondTime);
    }

    public static boolean isLocalTimeEqual(LocalTime firstTime, LocalTime secondTime) {
        return LogicalToolkit.and(
            firstTime.getHour() == secondTime.getHour(),
            firstTime.getMinute() == secondTime.getMinute(),
            firstTime.getSecond() == secondTime.getSecond()
        );
    }

    /**
     * return true if first date is after or same date for given sec date.
     *
     * @param firstDate
     * @param secDate
     * @return
     */
    public static boolean isAfterOrEqual(Date firstDate, Date secDate) {
        return isDateAfter(firstDate, secDate) || isDateEqual(firstDate, secDate);
    }

    public static boolean isAfterOrEqual(LocalDate firstDate, LocalDate secDate) {
        return firstDate.isAfter(secDate) || firstDate.isEqual(secDate);
    }

    public static boolean isAfterOrEqual(LocalDateTime firstDate, LocalDateTime secDate) {
        return firstDate.isAfter(secDate) || firstDate.isEqual(secDate);
    }

    public static boolean isAfterOrEqual(LocalTime firstDate, LocalTime secDate) {
        return firstDate.isAfter(secDate) || isLocalTimeEqual(firstDate, secDate);
    }

    /**
     * get previous date. (yesterday)
     *
     * @param date
     * @return
     */
    public static Date getPreviousDate(Date date) {
        return getPreviousDateWithGap(date, 1);
    }

    /**
     * get next date. (tomorrow)
     *
     * @param date
     * @return
     */
    public static Date getAfterDate(Date date) {
        return getAfterDateWithGap(date, 1);
    }

    /**
     * get previous date with give date.
     *
     * @param date
     * @param gap
     * @return
     */
    public static Date getPreviousDateWithGap(Date date, int gap) {
        return getDateWithGap(date, gap * -1);
    }

    /**
     * get after date with give date.
     *
     * @param date
     * @param gap
     * @return
     */
    public static Date getAfterDateWithGap(Date date, int gap) {
        return getDateWithGap(date, gap);
    }

    /**
     * get date with given gap. for future date gap should be positive and for history date gap should be negative.
     *
     * @param date
     * @param gap
     * @return
     */
    public static Date getDateWithGap(Date date, int gap) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + gap);
        Date result = calendar.getTime();
        return result;
    }

    /**
     * return most recent future date for today.
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Date getHigherDate(Date date1, Date date2) {
        if (isBeforeOrEqual(date1, date2)) {
            return date1;
        }
        return date2;
    }

    /**
     * return most recent history date for today.
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Date getLowerDate(Date date1, Date date2) {
        if (isAfterOrEqual(date1, date2)) {
            return date1;
        }
        return date2;
    }

    public static Function<Date, Date> getDefaultTimeRemovalFunction() {
        return date -> removeTime(date);
    }

    public static Function<Date, Date> getTimeToZeroFunction(int hour) {
        return getTimeRemovalFunction(hour, 0, 0, 0);
    }

    public static Function<Date, Date> getTimeToZeroFunction() {
        return getTimeRemovalFunction(0, 0, 0, 0);
    }

    public static Function<Date, Date> getTimeRemovalFunction(int hour, int minute, int second, int millisecond) {
        return date -> {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.SECOND, second);
            cal.set(Calendar.MILLISECOND, millisecond);
            return cal.getTime();
        };
    }

    /**
     * resolve date values to given collection. set time to 12 NOON
     *
     * @param collection
     */
    public static void resolveDateValues(Collection<? extends Serializable> collection) {
        for (Object obj : collection) {
            resolveDateValues(obj);
        }
    }

    /**
     * resolve date values to given object. set time to 12 NOON
     *
     * @param target
     */
    public static void resolveDateValues(Object target) {
        if (null != target) {
            resolveDateValues(target, target.getClass());
        }
    }

    /**
     * resolve date/ values to given object. set time to 12 NOON can avoid set time to 12 NOON by applying TimeStamp
     * annotation to date field with annotation value "IGNORE_TIME"
     *
     * @param target
     * @param clazz
     */
    @SuppressWarnings("checkstyle:IllegalCatch")
    public static void resolveDateValues(Object target, Class clazz) {
        resolveDateValues(target, clazz, date -> removeTime(date));
    }

    @SuppressWarnings("checkstyle:IllegalCatch")
    public static void resolveDateValues(Object target, Class clazz, Function<Date, Date> formatter) {
        if (null != clazz) {
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {

                if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                    continue;
                }
                if (field.getType() == null) {
                    continue;
                }
                if (!field.getType().getName().equals("java.util.Date")) {
                    continue;
                }
                TimeStamp annotation = field.getAnnotation(TimeStamp.class);
                if (null != annotation && TimeStamp.IGNORE_TIME.equals(annotation.action())) {
                    continue;
                }
                field.setAccessible(true);
                try {
                    Date value = (Date) field.get(target);
                    if (value != null) {
                        field.set(target, formatter.apply(value));
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            resolveDateValues(target, clazz.getSuperclass(), formatter);
        }
    }

}

