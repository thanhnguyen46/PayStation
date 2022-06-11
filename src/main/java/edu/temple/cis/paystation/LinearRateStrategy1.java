package edu.temple.cis.paystation;

public class LinearRateStrategy1 implements RateStrategy{
    /*
    Calculate time based on the coin value
    @param coinValue: amount inserted
    @return time bought
    */

    // 5 cents buys 2 minutes
    public int calculateTime(int insertedSoFar) {
        return (insertedSoFar * 2) / 5;
    }
}
