package com.gamecook.cigarsmuggler.enums;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 23, 2010
 * Time: 2:39:44 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Cigars {
    COHIBA("cohiba", 2),
    PARTEGAS("Partegas", 0),
    DIPLOMATICOS("Diplom‡ticos", 1),
    H_UPMANN("H. Upmann", 2),
    PUNCH("Punch", 0),
    RAMON_ALLONES("Ram—n Allones", 2),
    JUAN_LOPEZ("Juan Lopez", 0),
    ROMEO_Y_JULIETA("Romeo Y Julieta", 1),
    MONTECRISTO("Montecristo", 1);

    private final String name;
    private final int cost;

    Cigars(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public String getName()
    {
        return name;
    }

}
