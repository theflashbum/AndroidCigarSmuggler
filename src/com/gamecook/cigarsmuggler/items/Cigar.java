package com.gamecook.cigarsmuggler.items;

import android.util.Log;
import com.gamecook.fit.items.AbstractItem;
import com.gamecook.fit.items.Item;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 31, 2010
 * Time: 10:00:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Cigar extends AbstractItem {

    private int saleChance = 10;

    public Cigar(String name) {
        super(name);
    }

    @Override
    public Item clone(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public double generateNewPrice() {

        double tmpPrice;
         Random random = new Random();
        int sale = random.nextInt(saleChance);

        if(sale == 0)
        {
            tmpPrice = minPrice * .5;
            if(tmpPrice <= 0) tmpPrice = 1;
        }
        else
        {
            tmpPrice = super.generateNewPrice();
        }


        return tmpPrice;
    }
}
