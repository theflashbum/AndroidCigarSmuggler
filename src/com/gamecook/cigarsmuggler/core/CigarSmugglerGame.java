package com.gamecook.cigarsmuggler.core;

import android.content.Context;
import com.gamecook.cigarsmuggler.R;
import com.gamecook.cigarsmuggler.collections.CigarSmugglerLocations;
import com.gamecook.cigarsmuggler.collections.PriceRange;
import com.gamecook.cigarsmuggler.enums.Cigars;
import com.gamecook.cigarsmuggler.items.Cigar;
import com.gamecook.fit.AbstractGame;
import com.gamecook.fit.collections.Store;
import com.gamecook.fit.items.Item;
import com.gamecook.fit.time.Calendar;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 18, 2010
 * Time: 8:34:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class CigarSmugglerGame extends AbstractGame {

    private int difficultyLevel;
    private Context context;
    private static final int BASE_DAYS = 30;
    private PriceRange[] costTable;
    private String[] descriptions;

    public String getCurrentLocation() {
        return locations.getCurrentLocation();
    }

    public void setCurrentLocation(String name) {
        locations.gotoLocationByName(name);
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }


    @Override
    public void startGame(int days) {
        startGame(days, null);
    }

    public void startGame(int difficultyLevel, Context context) {
        restart();
        if(context == null)
            throw new Error("This game instance needs a reference to a context to work.");

        this.difficultyLevel = difficultyLevel;
        this.context = context;

        int days = ((3 - (difficultyLevel + 1)) + 1) * BASE_DAYS;

        reset();
        setCalendar(new Calendar(days));
        getWallet().setTotal(100);
        getBank().takeOutLoan(100);
        getBank().set_interest(0.1299);
        createCigarInventory();
        setLocations(new CigarSmugglerLocations());

        gameStarted = true;
    }

    private void restart() {
        inventory = null;
        locations = null;
        store = null;
        bank = null;
        wallet = null;
        player = null;
        calendar = null;

    }

    private void createCigarInventory() {

        Store store = getStore();

        costTable = new PriceRange[]{ new PriceRange(1,5),
                                      new PriceRange(15,25),
                                      new PriceRange(35,40)};

        descriptions = new String[]{context.getString(R.string.cigar_description_low),
                                    context.getString(R.string.cigar_description_medium),
                                    context.getString(R.string.cigar_description_high)};

        
        store.add(createCigar(Cigars.COHIBA), -1);
        store.add(createCigar(Cigars.DIPLOMATICOS), -1);
        store.add(createCigar(Cigars.H_UPMANN), -1);
        store.add(createCigar(Cigars.JUAN_LOPEZ), -1);
        store.add(createCigar(Cigars.MONTECRISTO), -1);
        store.add(createCigar(Cigars.PARTEGAS), -1);
        store.add(createCigar(Cigars.PUNCH), -1);
        store.add(createCigar(Cigars.RAMON_ALLONES), -1);
        store.add(createCigar(Cigars.ROMEO_Y_JULIETA), -1);

        store.refresh();
    }

    private Item createCigar(Cigars cigars) {

        Item item = new Cigar(cigars.getName());

        PriceRange range = costTable[cigars.getCost()];

        item.setMinPrice(range.getMin());
        item.setMaxPrice(range.getMax());
        item.setDescription(descriptions[cigars.getCost()]);

        //PPopulate price history
        int history = 16;

        for (int i = 0; i < history; i ++)
        {
            item.generateNewPrice();
        }

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

    public String difficultyToString() {
        switch (difficultyLevel)
        {
        case 0:
            return context.getString(R.string.difficulty_1);
        case 1:
            return context.getString(R.string.difficulty_2);
        case 2:
            return context.getString(R.string.difficulty_3);
        }

        return "None";
    }
}
