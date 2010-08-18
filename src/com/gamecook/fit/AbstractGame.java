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

    public Locations getLocations() {
        return locations != null ? locations : new Locations();
    }

    public void setLocations(Locations locations) {
        this.locations = locations;
    }

    public Store getStore() {
        return store != null ? store : new Store();
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Bank getBank() {
        return bank != null ? bank : new Bank(0);
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Wallet getWallet() {
        return wallet != null ? wallet : new Wallet(0);
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Player getPlayer() {
        return player != null ? player : new Player("No Name");
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Calendar getCalendar() {
        return calendar != null ? calendar : new Calendar(0) ;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
