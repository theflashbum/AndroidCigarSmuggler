package com.gamecook.fit.items;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 15, 2010
 * Time: 5:43:47 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractItem implements Item {

    protected double minPrice = 0;
    protected double maxPrice = 0;
    protected double price = 0;
    protected String name = "undefined";
    protected String description = "";
    protected int total;

    protected ArrayList<Double> priceHistory;

    /**
     * An Abstract class to represent an Item that can be used
     * with FiT. Items can randomly generate their price based
     * on a min/max or can be explicitly set. Also Items can
     * have a total representing how many of each are in a
     * collection.
     *
     * @param name the name of the Item.
     */
    public AbstractItem(String name) {
        this.name = name;
        priceHistory = new ArrayList<Double>();
    }

    /**
     * @return
     */
    public double getMinPrice() {
        return minPrice;
    }

    /**
     * Sets the Minimum Price for the Item. This is used when
     * randomly generating a new price.
     *
     * @param value
     */
    public void setMinPrice(double value) {
        minPrice = value < 0 ? 0 : value;
    }

    /**
     * @return
     */
    public double getMaxPrice() {
        return maxPrice;
    }

    /**
     * Sets the Maximum price for the Item. This is used when
     * randomly generating a new price.
     *
     * @param value
     */
    public void setMaxPrice(double value) {
        maxPrice = value <= minPrice ? minPrice : value;
    }

    /**
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the price of the item.
     *
     * @param value
     */
    public void setPrice(double value) {
        price = value < 0 ? 0 : value;
        priceHistory.add(price);
    }

    /**
     * Returns the name of the Item. The name is read only and
     * lock in at the time of construction.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the Item.
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets a description for the item.
     *
     * @param value
     */
    public void setDescription(String value) {
        description = value;
    }

    /**
     * This generates a new price for the Item based on it's
     * min/max price. It returns the new price.
     *
     * @return
     */
    public double generateNewPrice() {

        Random randomGenerator = new Random();

        setPrice(Math.round(minPrice + randomGenerator.nextDouble() * maxPrice));
        return price;
    }

    /**
     * Returns total number of items in an instance.
     *
     * @return
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets a new total for the items of an instance. This overrides
     * any previous value.
     *
     * @param value
     */
    public void setTotal(int value) {
        if (value == total)
            return;

        total = value < 0 ? 0 : value;
    }

    /**
     * An abstract clone method for AbstractItem.
     *
     * @param name
     * @return
     */
    abstract public Item clone(String name);

    public ArrayList<Double> getPriceHistory() {
        return priceHistory;
    }


}
