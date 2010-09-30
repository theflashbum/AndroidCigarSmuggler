package com.gamecook.cigarsmuggler.collections;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Sep 30, 2010
 * Time: 9:22:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class PriceRange {

    private int min;
    private int max;

    public PriceRange(int min, int max) {

        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
