package com.gamecook.fit.commerce;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 13, 2010
 * Time: 11:13:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class WalletTest {
    
    @Test
    public void testNewWallet()
    {
        Wallet wallet = new Wallet(100);
        assertEquals(wallet.getTotal(), 100.0);
    }

    @Test
    public void testSetMoneyInWallet()
    {
        Wallet wallet = new Wallet(100);
        wallet.add(200);
        assertEquals(wallet.getTotal(), 300.0);
    }
}
