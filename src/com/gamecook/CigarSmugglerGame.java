package com.gamecook;

import com.gamecook.fit.AbstractGame;

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
        gameStarted = true;
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
