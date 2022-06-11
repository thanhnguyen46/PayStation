package edu.temple.cis.paystation;

public class LinearRateStrategy2  extends LinearRateStrategy1 {
    /*
    Calculate time based on the coin value
    @param coinValue: amount inserted
    @return time bought
    */

    // 5 cents buys 1 minute
    @Override
    public int calculateTime(int insertedSoFar) {
        return insertedSoFar / 5;
    }
}
