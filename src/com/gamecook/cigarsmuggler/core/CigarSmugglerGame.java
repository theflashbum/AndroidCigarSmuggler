package com.gamecook.cigarsmuggler.core;

import com.gamecook.cigarsmuggler.collections.CigarSmugglerLocations;
import com.gamecook.cigarsmuggler.enums.Cigars;
import com.gamecook.cigarsmuggler.items.Cigar;
import com.gamecook.fit.AbstractGame;
import com.gamecook.fit.collections.Store;
import com.gamecook.fit.items.Item;
import com.gamecook.fit.time.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 18, 2010
 * Time: 8:34:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class CigarSmugglerGame extends AbstractGame {

    public String getCurrentLocation() {
        return locations.getCurrentLocation();
    }

    public void setCurrentLocation(String name) {
        locations.gotoLocationByName(name);
    }

    @Override
    public void startGame(int days) {
        reset();
        setCalendar(new Calendar(days));
        getWallet().setTotal(100);
        getBank().takeOutLoan(100);
        getBank().set_interest(0.1299);
        createCigarInventory();
        setLocations(new CigarSmugglerLocations());

        gameStarted = true;
    }

    private void createCigarInventory() {
        Store store = getStore();

        //TODO this could be optimized

        String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer id sapien sollicitudin purus facilisis dapibus. Duis enim dolor, sodales sit.";
        store.add(createCigar(Cigars.COHIBA, 1, 10, loremIpsum), -1);
        store.add(createCigar(Cigars.DIPLOMATICOS, 1, 10, loremIpsum), -1);
        store.add(createCigar(Cigars.H_UPMANN, 1, 10, loremIpsum), -1);
        store.add(createCigar(Cigars.JUAN_LOPEZ, 1, 10, loremIpsum), -1);
        store.add(createCigar(Cigars.MONTECRISTO, 1, 10, loremIpsum), -1);
        store.add(createCigar(Cigars.PARTEGAS, 1, 10, loremIpsum), -1);
        store.add(createCigar(Cigars.PUNCH, 1, 10, loremIpsum), -1);
        store.add(createCigar(Cigars.RAMON_ALLONES, 1, 10, loremIpsum), -1);
        store.add(createCigar(Cigars.ROMEO_Y_JULIETA, 1, 10, loremIpsum), -1);

        store.refresh();
    }

    private Item createCigar(Cigars cigars, double min, double max, String description) {

        Item item = new Cigar(cigars.name());
        item.setMinPrice(min);
        item.setMaxPrice(max);
        item.setDescription(description);

        return item;
    }

    @Override
    public void endGame() {
        gameStarted = false;
        //TODO change activity to game over
    }

    @Override
    public void nextTurn() {
        calendar.nextDay();
        bank.nextDay(1, 2);
        store.refresh();
    }

    @Override
    public void reset() {
        //TODO configure game instances here
    }

    public int getScore() {
        return (int) ((int) (wallet.getTotal() + bank.getSavings()) - bank.getLoan());
    }
}
