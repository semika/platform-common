package iit.ase.cw.platform.common.util.toolkit;

import java.util.Arrays;

public final class LogicalToolkit {

    private LogicalToolkit() {
    }

    /**
     * check particular parameter exists in javaarg paramter.
     */
    @SafeVarargs
    public static <T> boolean in(T parameter, T... values) {
        if (null != values) {
            return Arrays.asList(values).contains(parameter);
        }
        return false;
    }

    /**
     * perform logical or operation for javaargs params.
     */
    public static boolean or(boolean... values) {
        if (null != values) {
            for (boolean value : values) {
                if (value) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * perform logical and operation for javaargs params.
     */
    public static boolean and(boolean... values) {
        if (null != values) {
            for (boolean value : values) {
                if (!value) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
