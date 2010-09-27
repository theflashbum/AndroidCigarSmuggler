package com.gamecook.fit.collections;

import com.gamecook.fit.items.Item;

import java.util.Arrays;
import java.util.HashMap;


public class Inventory {

    protected HashMap<String, Item> inventory = new HashMap<String, Item>();
    protected int maxTotal = 0;
    protected int currentTotal = 0;

    /**
     * Inventory is a basic collection class which allows
     * you to add an item and specify the total amount of
     * that item. Since this is a departure from the base
     * class AbstractCollection, the default add and removed
     * are not used. In their place is addToInventory and
     * removeFromInventory.
     */
    public Inventory(int value) {
        maxTotal = value;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    /**
     * Max Total represent the total amount of items the inventory
     * can carry. MaxTotal can not be set to a value lower then
     * the current total or it will throw an error.
     *
     * @param value
     */
    public void setMaxTotal(int value) {
        if(maxTotal == value)
            return;
        else if(value < currentTotal && value > -1)
            throw new Error("New MaxTotal is lower then current total");

        this.maxTotal = value;
    }

    /**
     * Gets the inventories total. If the MaxTotal is set to -1 this
     * will always return 0.
     *
     * @return
     */
    public int getCurrentTotal() {
        return currentTotal;
    }

    /**
     *
     * @param value
     */
    protected void subtractFromTotal(int value)
    {
        if(maxTotal == -1)
            return;

        currentTotal -= value;
        if(currentTotal < 0)
            currentTotal = 0;//TODO this should throw an error -> throw new Error("Current total cannon go below 0.");
    }

    /**
     *
     * @param value
     */
    protected void addToTotal(int value)
    {
        if(maxTotal == -1)
            return;

        currentTotal += value;
        if(currentTotal > maxTotal)
            throw new Error("Current total cann't go above the Max Total.");
    }

    /**
     * Adds an item to the inventory along with a amount
     * for that item.
     *
     * @param item
     * @return
     */
    public void add(Item item, int amount) {

        addToTotal(amount);

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

            // Remove item's total from inventory total before removing item
            subtractFromTotal(((Item)inventory.get(name)).getTotal());

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
            subtractFromTotal(amount);

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
        addToTotal(value);

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

    public int getTotalLeft() {
        return maxTotal - currentTotal;
    }
}
