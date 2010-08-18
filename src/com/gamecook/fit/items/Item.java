package com.gamecook.fit.items;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 15, 2010
 * Time: 5:45:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Item {

    double getMinPrice();

    void setMinPrice(double value);

    double getMaxPrice();

    void setMaxPrice(double value);

    double getPrice();

    void setPrice(double value);

    String getName();

    String getDescription();

    void setDescription(String value);

    double generateNewPrice();

    int getTotal();

    void setTotal(int value);

}
