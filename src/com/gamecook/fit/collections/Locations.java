package com.gamecook.fit.collections;

import java.util.ArrayList;


public class Locations {
    protected int _currentLocation;
    private int lastID;
    private ArrayList<String> locations = new ArrayList<String>();

    /**
     * The map represent a collection of locations.
     * It tracks the current and last location visited.
     */
    public Locations() {

    }

    public int add(String value) {
        if (!locations.contains(value))
            locations.add(value);

        return locations.indexOf(value);
    }

    /**
     * Tells the map what location is currently
     * being visited.
     *
     * @param id
     */
    public String gotoLocationByID(int id) {
        if (_currentLocation >= 0)
            lastID = _currentLocation;

        _currentLocation = id;

        return locations.get(_currentLocation);
    }

    /**
     * Returns the current loctaion.
     *
     * @return
     */
    public String getCurrentLocation() {
        return locations.get(_currentLocation);
    }

    /**
     * Returns the location itself by ID.
     *
     * @param id
     * @return
     */
    public String getLocation(int id) {
        return locations.get(id);
    }

    /**
     * returns the last location visited.
     *
     * @return
     */
    public String getLastLocation() {
        return locations.get(lastID);
    }

    public int getTotal() {
        return locations.size();
    }

    /*public ArrayAdapter<String> getLocationArray() {

        return new ArrayAdapter<String>(locations);
    }*/
}
