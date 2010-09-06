package com.gamecook.fit.items;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Sep 5, 2010
 * Time: 9:21:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class MockItem extends AbstractItem
{
    public MockItem(String name)
    {
        super(name);
    }

    public Item clone(String name) {
        return new MockItem(name);
    }

}