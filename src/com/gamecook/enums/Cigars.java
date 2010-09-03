package com.gamecook.enums;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 23, 2010
 * Time: 2:39:44 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Cigars {
    COHIBA("cohiba", 3),
    PARTEGAS("Partegas", 1),
    DIPLOMATICOS("Diplom‡ticos", 1),
    H_UPMANN("H. Upmann", 3),
    PUNCH("Punch", 1),
    RAMON_ALLONES("Ram—n Allones", 1),
    JUAN_LOPEZ("Juan Lopez", 1),
    ROMEO_Y_JULIETA("Romeo Y Julieta", 1),
    MONTECRISTO("Montecristo", 1);

    private final String name;
    private final int cost;

    Cigars(String name, int cost)
    {
        this.name = name;
        this.cost = cost;
    }

    public int cost() {
        return cost;
    }
}
