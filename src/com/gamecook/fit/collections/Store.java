package com.gamecook.fit.collections;

import com.gamecook.fit.items.Item;

import java.util.Collection;
import java.util.HashMap;

public class Store {
    protected HashMap<String, Item> inventory = new HashMap<String, Item>();

    /**
     * The Store represents a collection of items
     * with their associated prices. The store also
     * manages the inventory of each item and its
     * fluctuation in price.
     */
    public Store() {

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

    /**
     * Adds an item to the store. It also adds place
     * holder data for the inventory, price and range.
     *
     * @param item
     * @return
     */
    public void add(Item item, int amount) {
        if (inventory.containsKey(item.getName()))
        {
            addToItemTotal(item.getName(), amount);
            return;
        }
        item.setTotal(amount);
        inventory.put(item.getName(), item);
    }

    /**
     * Completely removes and item from the Store.
     *
     * @param name
     * @return
     */
    public Boolean remove(String name) {
        if (inventory.containsKey(name)) {
            inventory.remove(name);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Removes some amount of items from the inventory and returns
     * what is left for that itme.
     *
     * @param id
     * @param amount
     * @return
     */
    public int removeFromInventory(Item id, int amount) {
        return 0;
    }

    public int getTotalItems() {
        return inventory.size();
    }

    public int getItemTotal(String name) {
        return inventory.get(name).getTotal();
    }

    public Item get(String name) {
        return inventory.get(name);
    }

    public int addToItemTotal(String name, int value) {
        Item tmpItem = inventory.get(name);

        tmpItem.setTotal(tmpItem.getTotal() + value);

        return tmpItem.getTotal();
    }

    public Boolean hasItem(String name)
    {
        return inventory.containsKey(name);
    }
}