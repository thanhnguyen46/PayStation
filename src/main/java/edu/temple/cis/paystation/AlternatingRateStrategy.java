package edu.temple.cis.paystation;

import java.util.Calendar;

// GammaTown - Progressive/Linear1
public class AlternatingRateStrategy implements RateStrategy{

    private LinearRateStrategy1 linearRate1 = new LinearRateStrategy1();
    private ProgressiveRateStrategy progressiveRate = new ProgressiveRateStrategy();

    // Alternating Calculate Rate
    @Override
    public int calculateTime(int insertedSoFar) {
        int timeBought = 0;
        int day = Main.calendar.get(Calendar.DAY_OF_WEEK);

        // For testing weekendRate (Linear1), uncomment line below
        // day = Calendar.SATURDAY;

        if (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
            timeBought = linearRate1.calculateTime(insertedSoFar);
            return timeBought;
        } else {
            timeBought = progressiveRate.calculateTime(insertedSoFar);
            return timeBought;
        }
    }
}
