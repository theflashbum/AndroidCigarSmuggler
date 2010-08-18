package com.gamecook.fit.player;


public class Player {
    private String _name;

    /**
     * Basic container for player data.
     *
     * @param name
     */
    public Player(String name) {
        _name = name;
    }

    /**
     * Returns the name of the player.
     *
     * @return
     */
    public String getName() {
        return _name;
    }

    /**
     * Sets the name of the player.
     *
     * @param value
     */
    public void setName(String value) {
        _name = value;
    }
}
