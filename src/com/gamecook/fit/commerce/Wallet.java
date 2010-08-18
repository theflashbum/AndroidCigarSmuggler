package com.gamecook.fit.commerce;

public class Wallet {
    private double _total = 0;

    /**
     * The wallet is a basic container for currency.
     * It simply allows you to store and retrieve
     * the value inside of it.
     *
     * @param value
     */
    public Wallet(double value) {
        _total = value;
    }

    /**
     * Total in wallet.
     *
     * @return
     */
    public double getTotal() {
        return _total;
    }

    /**
     * Change the value of the total.
     *
     * @param value
     */
    public void setTotal(double value) {
        _total = value;
    }

    /**
     * Adds to the wallet's value.
     *
     * @param value
     */
    public double add(double value) {
        return _total += value;
    }

    /**
     * Adds to the wallet's value.
     *
     * @param value
     */
    public double subtract(double value) {
        return _total -= value;
    }
}