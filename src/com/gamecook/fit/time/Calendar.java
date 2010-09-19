package com.gamecook.fit.time;

public class Calendar {
    private int days;

    private int totalDays;

    /**
     * This is a simple turn managing class. Each
     * turn is represented by a day, calling the
     * nextDat method advances the turns.
     *
     * @param days
     */
    public Calendar(int days) {
        this.days = totalDays = days;
    }

    /**
     * Returns the number of days left in the
     * calendar.
     *
     * @return
     */
    public int getDays() {
        return days;
    }

    /**
     * Sets the total number of days in the
     * calender.
     *
     * @param value
     */
    public void setDays(int value) {
        days = value;
    }

    /**
     * Returns the total number of days in the
     * calender.
     *
     * @return
     */
    public int getTotalDays() {
        return totalDays;
    }

    /**
     * Sets total Days
     *
     * @param value
     * @return
     */
    public int setTotalDays(int value) {
        return totalDays;
    }

    /**
     * Checks to see if a day exists.
     *
     * @return
     */
    public Boolean hasNextDay() {
        return days < 1 ? false : true;
    }

    /**
     * Decreases the number of days by 1
     */
    public void nextDay() {
        days -= 1;
    }

}