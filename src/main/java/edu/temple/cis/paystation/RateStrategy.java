package edu.temple.cis.paystation;

public interface RateStrategy {
    /*
    Calculate time based on the coin value
    @param coinValue: amount inserted
    */

    int calculateTime(int insertedSoFar);
}
