package com.gamecook.fit.commerce;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 13, 2010
 * Time: 10:33:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankTest extends Bank {

        public BankTest()
        {
            super(0.1299);
            takeOutLoan(2500);
            depositIntoSavings(30);
            
        }

        @Test
        public void testCalculateInterest()
        {
            assertEquals(calculateInterest(_interest, 12, _loan, 3), 81.0);
        }

        @Test
        public void testInterest()
        {
            nextDay(3,12);
            assertEquals(getLoan(), 2581.0);

        }

        @Test
        public void testGetLoad()
        {
            assertEquals(getLoan(), 2500.0);
        }

        @Test
        public void testGetSavings()
        {
            assertEquals(getSavings(), 30.0);
        }

        @Test
        public void testGetInterest()
        {
            assertEquals(getInterest(), .1299);
        }

        @Test
        public void testPayOffLoan()
        {
            payOffLoan(1000);

            assertEquals(getLoan(), 1500.0);
        }
        @Test
        public void testTakeOutLoan()
        {
            takeOutLoan(500);
            assertEquals(getLoan(), 3000.0);
        }

        @Test
        public void testRemainderPayOffLoan()
        {
            double leftOver = payOffLoan(3000);
            assertEquals(leftOver, 500.0);
        }

        @Test
        public void testBalanceOverPayOffLoan()
        {
            double leftOver = payOffLoan(3000);
            assertEquals(getLoan(), 0.0);
        }

        @Test
        public void testBalanceOverPayOffLoanReturn()
        {
            double leftOver = payOffLoan(3000);
            assertEquals(leftOver, 500.0);
        }

        @Test
        public void testDepositIntoSavings()
        {
            depositIntoSavings(1000);
            assertEquals("test", getSavings(), 1030.0);
        }
}
