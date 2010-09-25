package com.gamecook.fit;

import com.gamecook.fit.collections.Inventory;
import com.gamecook.fit.collections.Locations;
import com.gamecook.fit.collections.Store;
import com.gamecook.fit.commerce.Bank;
import com.gamecook.fit.commerce.Wallet;
import com.gamecook.fit.player.Player;
import com.gamecook.fit.time.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 18, 2010
 * Time: 8:35:25 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractGame {

    protected Inventory inventory;
    protected Locations locations;
    protected Store store;
    protected Bank bank;
    protected Wallet wallet;
    protected Player player;
    protected Calendar calendar;
    protected Boolean gameStarted = false;

    public abstract void startGame(int days);

    public abstract void endGame();

    public abstract void nextTurn();

    public abstract void reset();

    public Boolean getGameStarted() {
        return gameStarted;
    }

    public Locations getLocations() {
        if (locations == null) locations = new Locations();
        return locations;
    }

    public void setLocations(Locations locations) {
        this.locations = locations;
    }

    public Store getStore() {
        if (store == null) store = new Store();
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Bank getBank() {
        if (bank == null) bank = new Bank(0);
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Wallet getWallet() {
        if (wallet == null) wallet = new Wallet(0);
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Player getPlayer() {
        if (player == null) player = new Player("No Name");
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Calendar getCalendar() {
        if (calendar == null) calendar = new Calendar(0);
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }


    public Inventory getInventory() {
        if (inventory == null) inventory = new Inventory(100);
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
