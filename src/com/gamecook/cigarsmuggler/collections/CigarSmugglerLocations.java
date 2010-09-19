package com.gamecook.cigarsmuggler.collections;

import com.gamecook.fit.collections.Locations;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Sep 16, 2010
 * Time: 1:22:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class CigarSmugglerLocations extends Locations {

    public int getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(int homeLocation) {
        this.homeLocation = homeLocation;
    }

    private int homeLocation = 0;


    public CigarSmugglerLocations() {
        super();
        createLocations();
    }

    private void createLocations() {
        add("Ft. Lauderdale");
        add("Miami");
        add("Hialeah");
        add("Hollywood");
        add("Boca Raton");
    }

    public String[] getActiveLocations() {

        int total = locations.size() - 1;

        String excludeLocation = locations.get(currentLocation);

        if (getCurrentLocation() == locations.get(0))
            total++;

        String[] locationList = new String[total];

        int id = 0;

        for (String location : locations) {
            if (excludeLocation != location) {
                locationList[id] = location;
                id++;
            } else if (excludeLocation == locations.get(0)) {
                locationList[id] = "Bank";
                id++;
            }
        }

        return locationList;
    }
}
