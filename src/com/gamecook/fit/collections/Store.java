package com.gamecook.fit.collections;

import com.gamecook.fit.items.Item;

import java.util.Collection;

public class Store extends Inventory {



    /**
     * The Store represents a collection of items
     * with their associated prices. The store also
     * manages the inventory of each item and its
     * fluctuation in price.
     */
    public Store() {
        super(-1);
    }

    /**
     * This refreshes the prices of each of the
     * items. It uses the range values to calculate
     * the change in price.
     */
    public void refresh() {
        Collection<Item> items = inventory.values();
        for (Item item : items) {
            item.generateNewPrice();
        }
    }

    public Object getItemByID(int i) {
        return get(getInventoryAsArray()[i]);
    }
}