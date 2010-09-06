package com.gamecook.cigarsmuggler.items;

import com.gamecook.fit.items.AbstractItem;
import com.gamecook.fit.items.Item;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 31, 2010
 * Time: 10:00:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Cigar extends AbstractItem{

    public Cigar(String name) {
        super(name);
    }

    @Override
    public Item clone(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
