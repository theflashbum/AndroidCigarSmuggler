package com.gamecook.fit.time;

public class Calendar {
    private int _days;

    private int _totalDays;

    /**
     * This is a simple turn managing class. Each
     * turn is represented by a day, calling the
     * nextDat method advances the turns.
     *
     * @param days
     */
    public Calendar(int days) {
        _days = _totalDays = days;
    }

    /**
     * Returns the number of days left in the
     * calendar.
     *
     * @return
     */
    public int getDays() {
        return _days;
    }

    /**
     * Sets the total number of days in the
     * calender.
     *
     * @param value
     */
    public void setDays(int value) {
        _days = value;
    }

    /**
     * Returns the total number of days in the
     * calender.
     *
     * @return
     */
    public int getTotalDays() {
        return _totalDays;
    }

    /**
     * Checks to see if a day exists.
     *
     * @return
     */
    public Boolean hasNextDay() {
        return _days < 1 ? false : true;
    }

    /**
     * Decreases the number of days by 1
     */
    public void nextDay() {
        _days -= 1;
    }

}