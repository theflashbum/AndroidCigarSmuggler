package com.gamecook.fit.player;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 13, 2010
 * Time: 10:30:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerTest {

    private Player player;


    @Before
    public void createPlayer()
    {
        player = new Player("John Doe");
    }

    @Test
    public void testPlayerName()
    {
        assertEquals(player.getName(), "John Doe");
    }
}
