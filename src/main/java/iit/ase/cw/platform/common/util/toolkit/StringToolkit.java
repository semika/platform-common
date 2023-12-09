package iit.ase.cw.platform.common.util.toolkit;;

import iit.ase.cw.platform.common.constant.ThaproGlobalConstant;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringJoiner;
import java.util.function.Function;

public final class StringToolkit {

    private StringToolkit() {
    }

    /**
     * check {@param value} is not empty. call {@link #isEmpty(String)} isEmpty
     */
    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    /**
     * check {@param value} is empty. (null or empty)
     */
    public static boolean isEmpty(String value) {
        if (value == null) {
            return true;
        }
        return value.trim().isEmpty();
    }

    /**
     * convert param to lower case.
     */
    public static String toLowerCase(String value) {
        if (isEmpty(value)) {
            return value;
        }
        return value.toLowerCase();
    }

    /**
     * convert param to upper case.
     */
    public static String toUpperCase(String value) {
        if (isEmpty(value)) {
            return value;
        }
        return value.toUpperCase();
    }

    /**
     * remove last character from param. KalerisX -> Kaleris
     */
    public static String removeFirstElement(String value) {
        return removeFirstElement(value, 1);
    }

    /**
     * remove last offset char set. param = Kaleris, 2 -> Kaler
     */
    public static String removeFirstElement(String value, int offset) {
        if (isEmpty(value) || value.length() < offset) {
            return ThaproGlobalConstant.Symbol.EMPTY_STR;
        }
        return subsString(value, offset, value.length());
    }

    /**
     * remove last character from param. KalerisS -> Kaleris
     */
    public static String removeLastElement(String value) {
        return removeLastElement(value, 1);
    }

    /**
     * remove last offset char set. param = Kaleris, 2 -> Kaler
     */
    public static String removeLastElement(String value, int offset) {
        if (isEmpty(value)) {
            return ThaproGlobalConstant.Symbol.EMPTY_STR;
        }
        if (value.length() > offset) {
            return value.substring(0, value.length() - offset);
        }
        return value;
    }

    /**
     * concat string with delimiter. if params are (Kale, ',', ris) -> Kale,ris
     */
    public static String concat(String source, String delimiter, String value) {
        if (!isEmpty(source)) {
            if (!isEmpty(value)) {
                return source + (null == delimiter ? ThaproGlobalConstant.Symbol.EMPTY_STR : delimiter) + value;
            }
            return source;
        } else {
            if (!isEmpty(value)) {
                return value;
            }
        }
        return ThaproGlobalConstant.Symbol.EMPTY_STR;
    }

    public static String concat(String delimiter, String... dataList) {
        return concat(delimiter, false, Arrays.asList(dataList), s -> s);
    }

    public static String concat(String delimiter, Collection<String> dataList) {
        return concat(delimiter, false, dataList, s -> s);
    }

    public static String concat(String delimiter, boolean ignoreEmpty, Collection<String> dataList) {
        return concat(delimiter, ignoreEmpty, dataList, s -> s);
    }

    public static <S> String concat(String delimiter, boolean ignoreEmpty, Collection<S> dataList,
                                    Function<S, String> stringConverter) {
        StringJoiner joiner = new StringJoiner(delimiter);
        for (S data : dataList) {
            String value = null;
            if (null != data) {
                value = stringConverter.apply(data);
            }
            if (!ignoreEmpty || (ignoreEmpty && !isEmpty(value))) {
                joiner.add(value);
            }
        }
        return joiner.toString();
    }

    public static String concatIgnoreEmpty(String delimiter, String... dataList) {
        return concat(delimiter, true, Arrays.asList(dataList), s -> s);
    }

    /**
     * get substring of param string. if params( yyXXXyy, 1, 2) -> yXX
     */
    public static String subsString(String value, int fromIndex, int toIndex) {
        if (isEmpty(value)) {
            return ThaproGlobalConstant.Symbol.EMPTY_STR;
        }
        if (fromIndex <= toIndex && value.length() >= toIndex) {
            return value.substring(fromIndex, toIndex);
        }
        return value;
    }

    /**
     * check two params are equal. can avoid null check )
     */
    public static boolean isMatch(String value, String match) {
        return isNotEmpty(value) && value.equalsIgnoreCase(match);
    }

    /**
     * same logic as {@link #isMatch(String, String)}. but here if both param empty consider as its match.
     */
    public static boolean isEqual(String s1, String s2) {
        boolean value;

        if (isEmpty(s1) && isEmpty(s2)) {
            value = true;
        } else if (isEmpty(s1) && !isEmpty(s2)) {
            value = false;
        } else if (!isEmpty(s1) && isEmpty(s2)) {
            value = false;
        } else {
            value = s1.equals(s2);
        }
        return value;
    }

    /**
     * trim string value. can avoid null handling.
     */
    public static String trim(String value) {
        if (value == null) {
            return null;
        }
        return value.trim();
    }

    /**
     * check for contain some string in param string.
     */
    public static boolean contain(String value, String regX) {
        if (!isEmpty(value) && !isEmpty(regX)) {
            return trim(value).contains(trim(regX));
        }
        return false;
    }

    /**
     * if param is null can get empty string. null handling.
     */
    public static String nvl(String string) {
        return nvl(string, ThaproGlobalConstant.Symbol.EMPTY_STR, true);
    }

    /**
     * if param is null can get empty string. return value will trim according to {@param shouldTrim}. null handling.
     */
    public static String nvl(String string, boolean shouldTrim) {
        return nvl(string, ThaproGlobalConstant.Symbol.EMPTY_STR, shouldTrim);
    }

    /**
     * if param is null can get default value and return value will trim. null handled.
     */
    public static String nvl(String string, String defaultValue) {
        return nvl(string, defaultValue, true);
    }

    /**
     * if param is null can get default value. return value will trim according to {@param shouldTrim}. null handled.
     */
    public static String nvl(String string, String defaultValue, boolean shouldTrim) {
        if (!isEmpty(string)) {
            return shouldTrim ? string.trim() : string;
        } else {
            return isEmpty(defaultValue) ? ThaproGlobalConstant.Symbol.EMPTY_STR : defaultValue;
        }
    }

    /**
     * add #of param characters to left side of source till source length equal to size.
     */
    public static String leftPad(String paramSource, String parChar) {
        return leftPad(paramSource, parChar);
    }

    /**
     * add #of param characters to left side of source till source length equal to size.
     */
    public static String leftPad(String paramSource, String parChar, int size) {
        String source = nvl(paramSource);
        int sourceSize = source.length();
        StringBuilder dest = new StringBuilder(source);
        for (int i = 0; i < size - sourceSize; i++) {
            dest.insert(0, parChar);
        }
        return dest.toString();
    }

    /**
     * add #of param characters to right side of source till source length equal to size.
     */
    public static String rightPad(String paramSource, String parChar) {
        return rightPad(paramSource, parChar, 1);
    }

    /**
     * add #of param characters to right side of source till source length equal to size.
     */
    public static String rightPad(String paramSource, String parChar, int size) {
        String source = nvl(paramSource);
        int sourceSize = source.length();
        StringBuilder dest = new StringBuilder(source);
        for (int i = 0; i < size - sourceSize; i++) {
            dest.append(parChar);
        }
        return dest.toString();
    }

    /**
     * return first character. return null if {@param value} is null.
     */
    public static String extractFirstChar(String value) {
        if (value == null) {
            return value;
        }
        if (value.length() <= 1) {
            return value;
        }
        return value.substring(0, 1);
    }

    /**
     *
     * @param input
     *          Accepts a camel case text.
     *          Ex : customerFirstName
     *
     * @return
     *          Sentence format
     *          Ex: Customer First Name
     */
    public static String fromCamelCase(String input) {
        String[] arr = input.split("(?=\\p{Upper})");
        String rv = "";
        for (String s : arr) {
            rv += s + " ";
        }
        rv = rv.trim();
        if (Character.isLowerCase(rv.charAt(0))) {
            rv = Character.toUpperCase(rv.charAt(0)) + rv.substring(1, rv.length());
        }
        return rv;
    }
}
