package com.gamecook.fit.collections;

import java.util.HashMap;

/**
 * @deprecated 
 */
public class Inventory {

    protected HashMap<String, Integer> _items = new HashMap<String, Integer>();

    /**
     * Inventory is a basic collection class which allows
     * you to add an item and specify the total amount of
     * that item. Since this is a departure from the base
     * class AbstractCollection, the default add and removed
     * are not used. In their place is addToInventory and
     * removeFromInventory.
     */
    public Inventory() {

    }

    /**
     * This method allows you to
     * add an item by name and give it a total representing
     * how many of that item is present.
     *
     * @param name
     * @param total
     */
    public void addToInventory(String name, int total) {
        if (_items.containsKey(name)) {
            _items.put(name, _items.get(name) + total);
        } else {
            _items.put(name, total);
        }
    }

    /**
     * This method allows you
     * to remove the amount of an item. When an item's
     * total is zero it is automatically removed from the
     * inventory.
     *
     * @param name
     * @param total
     */
    public void removeFromInventory(String name, int total) {
        if ((_items.get(name) < total) || !_items.containsKey(name)) {
            throw new Error("RangeError");
        } else {
            _items.put(name, _items.get(name) - total);

        }

        if (_items.get(name) == 0) {
            _items.remove(name);
        }
    }

    /**
     * Returns the total amount of a particular item.
     *
     * @param id
     * @return
     */
    public int getTotalOfItem(String id) {
        return _items.get(id);
    }

    /**
     * Returns the total amount of items in the
     * Inventory.
     *
     * @return
     */
    public int getTotal() {
        return _items.size();
    }
}
