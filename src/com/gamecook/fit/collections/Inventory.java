package com.gamecook.fit.collections;

import android.util.Log;
import com.gamecook.fit.items.Item;

import java.util.Arrays;
import java.util.HashMap;


public class Inventory {

    protected HashMap<String, Item> inventory = new HashMap<String, Item>();

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
     * Adds an item to the inventory along with a amount
     * for that item.
     *
     * @param item
     * @return
     */
    public void add(Item item, int amount) {
        if (inventory.containsKey(item.getName())) {
            addToItemTotal(item.getName(), amount);
            return;
        }
        item.setTotal(amount);
        inventory.put(item.getName(), item);
    }

    /**
     * Completely removes and item from the Inventory.
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
     * Removes some amount of an item from the inventory
     * and returns what is left for that item.
     *
     * @param id
     * @param amount
     * @return
     */
    public int removeFromInventory(Item id, int amount) {
        if (!hasItem(id.getName())) {
            return 0;
        } else {
            int remainder = getItemTotal(id.getName()) - amount;
            inventory.get(id.getName()).setTotal(remainder);
            if (remainder <= 0) {
                remove(id.getName());
            }
            return remainder;
        }

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

    public Boolean hasItem(String name) {
        return inventory.containsKey(name);
    }

    public String[] getInventoryAsArray() {
        //TODO this needs to have some sort of invalidation
        return convert(inventory);
    }

    protected String[] convert(HashMap<String, Item> things) {
        String[] tArray = things.keySet().toArray(new String[things.size()]);
        Arrays.sort(tArray);
        return tArray;
    }
}
