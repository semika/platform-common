package iit.ase.cw.platform.common.util.toolkit;

import iit.ase.cw.platform.common.constant.ThaproGlobalConstant;
import java.math.BigDecimal;

public final class NumberToolkit {

    private NumberToolkit() {
    }
    // -- Start Integer Operations --

    /**
     * get 0 as result if {@param value} is null.
     *
     * @return value == null ? -1 : value;
     */
    public static int nvlToZero(Integer value) {
        return parseInt(value, 0);
    }

    /**
     * call {@link #parseInt(Integer, int) parseInt}, default value as -1.
     *
     * @return value == null ? -1 : value;
     */
    public static int parseInt(Integer value) {
        return parseInt(value, -1);
    }

    /**
     * check null for {@param value} and if null return {@param defaultValue}
     *
     * @return value == null ? defaultValue : value
     */
    public static int parseInt(Integer value, int defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    /**
     * call {@link #parseInt(String, int)}, default value as 0
     *
     * @return int value of string or 0
     */
    public static int parseInt(String value) {
        return parseInt(value, 0);
    }

    /**
     * call {@link Integer#parseInt(String)}. additionally return default value if value is null.
     *
     * @return int value of value or defaultValue
     */
    public static int parseInt(String value, int defaultValue) {
        int result = defaultValue;
        try {
            result = Integer.parseInt(value);
        } catch (NumberFormatException expected) {
            //ignored
        }
        return result;
    }

    // -- End Integer Operations --

    // -- Start Long Operations --

    /**
     * if parameter is null return 0.
     *
     * @return long value of {@param value}
     */
    public static long parseLong(Long value) {
        if (value == null) {
            return 0;
        }
        return value;
    }

    /**
     * convert string value to long. if parameter is null return 0. call {@link #parseLong(String, long) parseLong}
     *
     * @return long value of {@param value}
     */
    public static long parseLong(String value) {
        return parseLong(value, 0);
    }

    /**
     * convert string value to long. if parameter string value is null return default value.
     *
     * @return long value of string
     */
    public static long parseLong(String value, long defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException expected) {
            //ignored
        }
        return defaultValue;
    }

    /**
     * to get string value of long. null is checking.
     *
     * @return string value of long
     */
    public static String longToString(Long value) {
        if (value == null) {
            return ThaproGlobalConstant.Symbol.EMPTY_STR;
        }
        return value.toString();
    }

    /**
     * get max long value if {@param value} is null
     *
     * @return long value
     */
    public static Long nvlToMaxLong(Long value) {
        if (value == null || value == 0) {
            return Long.MAX_VALUE;
        }
        return value;
    }

    /**
     * check long values are equal.
     */
    public static boolean isEqual(Long firstValue, Long secValue) {
        if (firstValue == null || secValue == null) {
            return false;
        }
        return firstValue.equals(secValue);
    }

    // -- End Long Operations --

    // -- Start Double Operations --

    /**
     * call {@link #parseDouble(String, double)}, {@param defaultValue} as 0.
     *
     * @return double value of {@param value} or 0;
     */
    public static double parseDouble(String value) {
        return parseDouble(value, 0.0);
    }

    /**
     * call {@link #parseDouble(Double, Double) parseDouble}, default value as -1,
     *
     * @return value or return -1 if {@param value} is null.
     */
    public static double parseDouble(Double value) {
        return parseDouble(value, -1.0);
    }

    /**
     * parse double value of {@param value} or if its null return {@param defaultValue}.
     *
     * @return value or defaultValue,
     */
    public static double parseDouble(Double value, Double defaultValue) {
        if (null == value) {
            return defaultValue;
        }
        return value;
    }

    /**
     * @return double value of string {@param value} or, if its null return {@param defaultValue}.
     */
    @SuppressWarnings("checkstyle:IllegalCatch")
    public static double parseDouble(String value, double defaultValue) {
        double result = defaultValue;
        try {
            result = Double.parseDouble(value);
        } catch (Exception expected) {
            //ignored
        }
        return result;
    }

    /**
     * call {@link #parseDouble(Double, Double) parseDouble}, default value as 0.
     *
     * @return value or return 0 if {@param value} is null.
     */
    public static double parseDoubleZero(Double value) {
        return parseDouble(value, 0.0);
    }

    /**
     * call {@link #parseDoubleMaxValue(String)} find double value of string, if its null (not 0.0) return max value.
     *
     * @return double value of {@param value} or max value.
     */
    public static double parseDoubleMaxValue(String value) {
        return parseDoubleMaxValue(value, false);
    }

    /**
     * find double value of string. if string param is null return max double value. if {@param considerZero } true
     * return max value even {@param value} is 0.0.
     *
     * @return double value.
     */
    public static double parseDoubleMaxValue(String value, boolean considerZero) {
        double result = parseDouble(value, ThaproGlobalConstant.DefaultValue.DOUBLE_MAX_VALUE);
        if (considerZero && result == 0.0) {
            return ThaproGlobalConstant.DefaultValue.DOUBLE_MAX_VALUE;
        }
        return result;
    }

    /**
     * return 0 if value is null or less than 0.
     *
     * @return double value.
     */
    public static double nvlMinusToZero(Double value) {
        if (null == value || value < 0) {
            return 0.0;
        }
        return value;
    }

    /**
     * call {@link #parseDoubleZero(Double) parseDoubleZero} and check wheter {@param value} is 0.0.
     *
     * @return true if value is 0;
     */
    public static boolean isZeroDouble(Double value) {
        return parseDoubleZero(value) == 0.0;
    }

    /**
     * to convert double value to string.
     */
    public static String doubleToString(Double value) {
        if (value == null) {
            return Double.toString(0.0);
        }
        return Double.toString(value);
    }

    /**
     * checking number of decimal places is lower than max value.
     */
    public static boolean isDecimalPlacesNotExceed(Double value, int maxDecimalPlaces) {
        if (value == null) {
            return false;
        }
        int decimalPoints = getNumberOfDecimalPlaces(value);
        if (decimalPoints == 0) {
            return true;
        }
        return decimalPoints <= maxDecimalPlaces;
    }

    /**
     * get number of decimal places double value contain.
     */
    public static int getNumberOfDecimalPlaces(Double value) {
        if (null == value || 0.0 == value) {
            return 0;
        }
        BigDecimal bigDecimal = new BigDecimal(value.toString());
        String string = bigDecimal.stripTrailingZeros().toPlainString();
        int index = string.indexOf(ThaproGlobalConstant.Symbol.DOT);
        return index < 0 ? 0 : string.length() - index - 1;
    }

    /**
     * round the double value.
     */
    public static Double round(Double unRounded, int precision, int roundingMode) {
        if (unRounded == null) {
            return unRounded;
        }
        BigDecimal bd = BigDecimal.valueOf(unRounded);
        BigDecimal rounded = bd.setScale(precision, roundingMode);
        return rounded.doubleValue();
    }

    /**
     * to get double value with different #of decimal points. Ex : {@param number} = 1.111 and {@param numDigits} = 2,
     * return 1.11
     *
     * @return #of decimal point modified double.
     */
    public static double truncate(double number, int numDigits) {
        double result = number;
        String arg = ThaproGlobalConstant.Symbol.EMPTY_STR + number;
        int index = arg.indexOf(ThaproGlobalConstant.Symbol.DOT);
        if (index != -1 && (arg.length() > index + numDigits)) {
            arg = arg.substring(0, index + numDigits + 1);
            result = Double.parseDouble(arg);

        }
        return result;
    }

    /**
     * check two double value is equal. in here don't want worry about null param. it handles by method.
     *
     * @return boolean parameter.
     */
    public static boolean isDifferentDouble(Double firstVal, Double secVal) {
        if (firstVal == null) {
            return secVal != null;
        } else {
            if (secVal == null) {
                return true;
            } else {
                return firstVal.doubleValue() != secVal.doubleValue();
            }
        }
    }

    /**
     * check two double values are equal.
     */
    @SuppressWarnings("checkstyle:OverloadMethodsDeclarationOrder")
    public static boolean isEqual(Double firstValue, Double secValue) {
        if (firstValue == null || secValue == null) {
            return false;
        }
        return firstValue.equals(secValue);
    }

    // -- End Double Operations --

    // -- Start Float Operations --

    /**
     * to get float value and if {@param value} is null get 0 as result. call {@link #parseFloat(Float, Float)}
     *
     * @return float value.
     */
    public static float parseFloat(Float value) {
        return parseFloat(value, 0.0f);
    }

    /**
     * to get default value if {@param value} is null.
     *
     * @return float value.
     */
    public static float parseFloat(Float value, Float defaultValue) {
        if (null == value) {
            return defaultValue;
        }
        return value;
    }

    /**
     * parse float value of string {@param value} parameter. if its null return 0. call {@link #parseFloat(String,
     * float)}
     *
     * @return float value
     */
    public static float parseFloat(String value) {
        return parseFloat(value, 0);
    }

    /**
     * get float value of string {@param value} parameter and if parameter null get default value is result.
     *
     * @return float value
     */
    public static float parseFloat(String value, float defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException expected) {
            //ignored
        }
        return defaultValue;
    }

    // -- End Float Operations --

    // -- Start Number Operations --

    /**
     * this is find out minimum value from two numbers. Suppose number1 = 0.0 and number2 = 100.0 if consider0 is true
     * Method will return 0.0. consider 0.0 is valid number. if consider0 is false Method will return 100.0. consider
     * 0.0 is invalid number. Try to always number1 and number2 not be null.
     */
    public static <T extends Number> T getMinimum(T number1, T number2, boolean consider0) {
        if (null != number1 && null == number2) {
            return number1;
        } else if (null != number2 && null == number1) {
            return number2;
        } else if (null != number1 && null != number2) {
            if (!consider0) {
                if (number1.doubleValue() == 0.0 && number2.doubleValue() > 0.0) {
                    return number2;
                } else if (number2.doubleValue() == 0.0 && number1.doubleValue() > 0.0) {
                    return number1;
                } else {
                    return number1.doubleValue() < number2.doubleValue() ? number1 : number2;
                }
            }
            return number1.doubleValue() < number2.doubleValue() ? number1 : number2;
        }
        return null;
    }

    /**
     * calculate total inches when have #of feet and #inches.
     */
    public static Integer getTotalInches(Integer feet, Integer inches) {
        return ((nvlToZero(feet)) * 12) + nvlToZero(inches);
    }

    /**
     * convert inches to feet.
     */
    public static Integer getFeet(Integer totalInches) {
        return (nvlToZero(totalInches)) / 12;
    }

    /**
     * get inches when number of inches divide by 12. Ex : totalInches = 28, result 4
     */
    public static Integer getInches(Integer totalInches) {
        return (nvlToZero(totalInches)) % 12;
    }

    /**
     * convert number to string.
     */
    public static String numberToString(Number number) {
        return String.valueOf(number);
    }

    /**
     * find the number is not null and greater than 0.
     *
     * @param number
     * @return
     */
    public static boolean isValidNumber(Number number) {
        return null != number && number.doubleValue() > 0.0;
    }

    /**
     * find the number have value. if {@Param is0Valid} true consider as 0 as valid number
     *
     * @param number
     * @return
     */
    public static boolean isValidNumber(Number number, boolean is0Valid) {
        return null != number && ((is0Valid && number.doubleValue() == 0.0) || number.doubleValue() > 0.0);
    }

    /**
     * check string is numeric value.
     *
     * @param s
     * @return
     */
    public static boolean isNumeric(String s) {
        if (StringToolkit.isNotEmpty(s)) {
            return s.matches("[-+]?\\d*\\.?\\d+");
        }
        return false;
    }

    // -- End Number Operations --

}
