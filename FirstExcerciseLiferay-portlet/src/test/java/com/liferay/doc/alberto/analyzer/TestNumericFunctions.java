package com.liferay.doc.alberto.analyzer;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestNumericFunctions {
	
	@Test public void testRoundOne() {
        assertTrue(1.00 == NumericFunctions.roundTax(1));
    }
	
    @Test public void testRoundZero() {
       assertTrue(0.00 ==  NumericFunctions.roundTax((0)));
    }
    
    @Test public void testRound0dot5() {
       assertTrue(0.50 ==  NumericFunctions.roundTax((0.5)));
           }
    
    @Test public void testRound0dot05() {
       assertTrue(0.05 ==  NumericFunctions.roundTax((0.05)));
    }

    @Test public void testRound0dot04() {
       assertTrue(0.05 ==  NumericFunctions.roundTax((0.04)));
    }
    @Test public void testRound0dot03() {
       assertTrue(0.05 ==  NumericFunctions.roundTax((0.03)));
    }

    @Test public void testRound0dot02() {
       assertTrue(0.00 ==  NumericFunctions.roundTax((0.02)));
    }

    @Test public void testRound0dot025() {
        assertTrue(0.05 ==  NumericFunctions.roundTax((0.025)));
     }

    @Test public void testRound0dot075() {
        assertTrue(0.10 ==  NumericFunctions.roundTax((0.075)));
     }

    @Test public void testRound0dot125() {
        assertTrue(0.15 ==  NumericFunctions.roundTax((0.125)));
     }

    @Test public void testRound0dot175() {
        assertTrue(0.20 ==  NumericFunctions.roundTax((0.175)));
     }

    @Test public void testRound0dot225() {
        assertTrue(0.25 ==  NumericFunctions.roundTax((0.225)));
     }

    @Test public void testRound0dot275() {
        assertTrue(0.30 ==  NumericFunctions.roundTax((0.275)));
     }

    @Test public void testRoundOneSixth() {
       assertTrue(0.15 ==  NumericFunctions.roundTax(1.0/6.0));
    }

    @Test public void testRoundOnethird() {
       assertTrue(0.35 ==  NumericFunctions.roundTax(1.0/3.0));
    }

    @Test public void testRound4525() {
        assertTrue(0.45 ==  NumericFunctions.roundTax(0.4525));
     }

    @Test public void testRoundNearChocolatePrev() {
        assertTrue(0.55 ==  NumericFunctions.roundTax(0.5575));
     }
    
    @Test public void testRoundFamousChocolateN() {
        assertTrue(0.55 ==  NumericFunctions.roundTax(0.5625));
     }
    
    @Test public void testRoundNearChocolatePost() {
        assertTrue(0.55 ==  NumericFunctions.roundTax(0.5675));
     }
    
    @Test public void testRound8525() {
        assertTrue(0.85 ==  NumericFunctions.roundTax(0.8525));
     }

    @Test public void testRoundFamousChocolate() {
        assertTrue(0.55 ==  NumericFunctions.roundTax(11.25*5/100));
     }

    @Test public void testRoundBigNumber() {
       assertTrue(125458668.15 == NumericFunctions.roundTax((125458668.1267888)));
    }
 
    @Test public void testRoundStandarBigNumber() {
        assertTrue(125458668.13 == NumericFunctions.round((125458668.1267888)));
     }
 }
