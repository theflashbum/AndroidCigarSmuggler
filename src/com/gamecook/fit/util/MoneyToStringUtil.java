package com.gamecook.fit.util;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Sep 18, 2010
 * Time: 8:09:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class MoneyToStringUtil {

    /**
     * @param value
     * @param roundUp
     * @return
     */
    public static String convertToString(Double value, Boolean roundUp) {

        String[] split = Double.toString(value).split("\\.");

        return cleanUpString(split, roundUp);
    }

    /**
     * @param value
     * @param roundUp
     * @return
     */
    public static String convertToString(int value, Boolean roundUp) {

        String[] split = Integer.toString(value).split("\\.");

        return cleanUpString(split, roundUp);
    }

    /**
     * @param split
     * @param roundUp
     * @return
     */
    private static String cleanUpString(String[] split, Boolean roundUp) {
        String moneyString = "$" + split[0];
        if (!roundUp) {
            String remainder = split[1];
            if (remainder.length() == 1) {
                remainder.concat("0");
            } else if (remainder.length() < 2) {
                //TODO need to remove any "extra" values after the second number.
            }
            moneyString.concat("." + remainder);
        }

        return moneyString;
    }
}
