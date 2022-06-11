package edu.temple.cis.paystation;

public class ProgressiveRateStrategy implements RateStrategy {
    /*
    Calculate time based on the coin value
    @param coinValue: amount inserted
    @return time bought
    */

    public int calculateTime(int insertedSoFar) {
        int timeBought = 0;
        if (insertedSoFar < 150) { // Less than an hour (60 min).
            timeBought = (insertedSoFar * 2) / 5;
        } else if (insertedSoFar < 350) { // Between 1st hour and 2nd hour .
            timeBought = ((insertedSoFar - 150) * 3) / 10 + 60;
        } else { // Greater than 2 hours.
            timeBought = ((insertedSoFar - 350) / 5) + 120;
        }
        return timeBought;
    }
}
