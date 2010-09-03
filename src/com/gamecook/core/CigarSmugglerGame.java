package com.gamecook.core;

import com.gamecook.enums.Cigars;
import com.gamecook.fit.AbstractGame;
import com.gamecook.fit.collections.Store;
import com.gamecook.fit.items.Item;
import com.gamecook.items.Cigar;

import java.util.Enumeration;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 18, 2010
 * Time: 8:34:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class CigarSmugglerGame extends AbstractGame {


    @Override
    public void startGame(int days) {
        reset();
        getCalendar().setDays(days);
        getWallet().setTotal(100);
        getBank().takeOutLoan(100);
        createCigarInventory();
        gameStarted = true;
    }

    private void createCigarInventory() {
        Store store = getStore();

        //TODO this could be optimized

        store.add(createCigar(Cigars.COHIBA), -1);
        store.add(createCigar(Cigars.DIPLOMATICOS), -1);
        store.add(createCigar(Cigars.H_UPMANN), -1);
        store.add(createCigar(Cigars.JUAN_LOPEZ), -1);
        store.add(createCigar(Cigars.MONTECRISTO), -1);
        store.add(createCigar(Cigars.PARTEGAS), -1);
        store.add(createCigar(Cigars.PUNCH), -1);
        store.add(createCigar(Cigars.RAMON_ALLONES), -1);
        store.add(createCigar(Cigars.ROMEO_Y_JULIETA), -1);
    }

    private Item createCigar(Cigars cigars) {
        Item item = new Cigar(cigars.name());
       /* <int>[] priceRange = range[cigars.cost()];
        item.setMinPrice();*/
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
        bank.nextDay(calendar.getDays(), calendar.getTotalDays());

    }

    @Override
    public void reset() {
        //TODO configure game instances here
    }
}
