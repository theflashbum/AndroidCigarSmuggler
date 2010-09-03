package com.gamecook.fit;

import com.gamecook.fit.collections.Inventory;
import com.gamecook.fit.collections.Locations;
import com.gamecook.fit.collections.Store;
import com.gamecook.fit.commerce.Bank;
import com.gamecook.fit.commerce.Wallet;
import com.gamecook.fit.items.AbstractItem;
import com.gamecook.fit.items.Item;
import com.gamecook.fit.player.Player;
import com.gamecook.fit.time.Calendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;


/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 18, 2010
 * Time: 8:43:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractGameTest {
    private MockGame game;

    @Before
    public void setUp() throws Exception {
        game = new MockGame();
    }

    @After
    public void tearDown() throws Exception {
        game = null;
    }

    @Test
    public void testGetLocations() throws Exception {
        assertNotNull(game.getLocations());
        assertEquals(game.getLocations().getTotal(), 0);
    }

    @Test
    public void testSetLocations() throws Exception {
        Locations locations = new Locations();
            locations.add("LocationA");
            locations.add("LocationB");
            locations.add("LocationC");
        game.setLocations(locations);
        
        assertEquals(game.getLocations().getTotal(), 3);
    }

    @Test
    public void testGetStore() throws Exception {
        assertNotNull(game.getStore());
        assertEquals(game.getStore().getTotalItems(), 0);
    }

    @Test
    public void testSetStore() throws Exception {
        Store store =new Store();
        store.add(new MockItem("FooBar"), 10);
        store.add(new MockItem("FooBar2"), 5);
        game.setStore(store);
        assertEquals(game.getStore().getTotalItems(), 2);
    }

    @Test
    public void testGetBank() throws Exception {
        assertNotNull(game.getBank());
        assertEquals(game.getBank().getInterest(), 0.0);
    }

    @Test
    public void testSetBank() throws Exception {
        Bank bank = new Bank(4.5);
        game.setBank(bank);
        assertEquals(game.getBank().getInterest(), 4.5);
        
    }

    @Test
    public void testGetWallet() throws Exception {
        assertNotNull(game.getWallet());
        assertEquals(game.getWallet().getTotal(), 0.0);
    }

    @Test
    public void testSetWallet() throws Exception {
        Wallet wallet = new Wallet(100);
        game.setWallet(wallet);
        assertEquals(game.getWallet().getTotal(), 100.0);
    }

    @Test
    public void testGetPlayer() throws Exception {
        assertNotNull(game.getPlayer());
        assertEquals(game.getPlayer().getName(), "No Name");
    }

    @Test
    public void testSetPlayer() throws Exception {
        Player player = new Player("FooBar");
        game.setPlayer(player);
        assertEquals(game.getPlayer().getName(), "FooBar");
    }

    @Test
    public void testGetCalendar() throws Exception {
        assertNotNull(game.getCalendar());
        assertEquals(game.getCalendar().getDays(), 0);
    }

    @Test
    public void testSetCalendar() throws Exception {

        game.setCalendar(new Calendar(30));
        assertEquals(game.getCalendar().getDays(), 30);

    }
}

class MockGame extends AbstractGame
{

    @Override
    public void startGame(int days) {

    }

    @Override
    public void endGame() {

    }

    @Override
    public void nextTurn() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void reset() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

class MockItem extends AbstractItem
{

    public MockItem(String name){
        super(name);
    }
    
    @Override
    public Item clone(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
    
}