package com.gamecook.fit.commerce;

public class Bank {
    protected double _savings = 0;

    protected double _interest;

    protected double _loan = 0;
    private Boolean _round = true;

    /**
     * The bank manges a single loan and savings value.
     * It is capable of adding interest to the load each
     * day.
     *
     * @param interest
     */
    public Bank(double interest) {
        _interest = interest;
    }

    /**
     * Returns the total amount of savings in the bank.
     *
     * @return
     */
    public double getSavings() {
        return _savings;
    }

    /**
     * Returns the total amount of the load in the bank.
     *
     * @return
     */
    public double getLoan() {
        return _loan;
    }

    /**
     * Returns the interest used for the load.
     *
     * @return
     */
    public double getInterest() {
        return _interest;
    }


    /**
     * Calculates the interest on the loan.
     *
     * @param currentDay
     * @param totalDays
     */
    public void nextDay(double currentDay, double totalDays) {
        if (_loan != 0) {
            double interest = calculateInterest(_interest, totalDays, _loan, currentDay);
            _loan += interest;
        }
    }

    /**
     * Pays off the loan. Returns the remainder if over.
     *
     * @param value
     * @return
     */
    public double payOffLoan(double value) {
        _loan -= value;

        double remainder = 0;

        if (_loan < 0) {
            remainder = _loan * -1;
            _loan = 0;
        }

        return remainder;

    }

    /**
     * takes money out of savings.
     *
     * @param value
     * @return
     */
    public double withdrawFromSavings(double value) {
        if (value > _savings) {
            value = _savings;
            _savings = 0;
        } else {
            _savings -= value;
        }

        return value;
    }

    /**
     * Adds to the loan.
     *
     * @param value
     */
    public void takeOutLoan(double value) {
        _loan += value;
    }

    /**
     * Adds money to your savings account.
     *
     * @param value
     */
    public void depositIntoSavings(double value) {
        _savings += value;
    }


    protected double calculateInterest(double interest, double totalTime, double balance, double timeElapsed) {
        double value = ((interest / totalTime) * balance) * timeElapsed;

        return _round ? Math.round(value) : value;
    }

    /**
     * Allows you to trigger if the bank will round all interest transactions.
     *
     * @param value
     */
    public void setRoundInterestCalculation(Boolean value) {
        _round = value;
    }

    public double get_interest() {
        return _interest;
    }

    public void set_interest(double _interest) {
        this._interest = _interest;
    }
}
