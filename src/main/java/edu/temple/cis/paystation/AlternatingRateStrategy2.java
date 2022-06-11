package edu.temple.cis.paystation;

import java.util.Calendar;

// OmegaTown - Linear1/Free
public class AlternatingRateStrategy2 extends AlternatingRateStrategy {
    private LinearRateStrategy1 linearRate1 = new LinearRateStrategy1();

    // Alternating Calculate Rate
    @Override
    public int calculateTime(int insertedSoFar) {
        int day = Main.calendar.get(Calendar.DAY_OF_WEEK);

        // For testing weekendRate (FREE), uncomment line below
        // day = Calendar.SATURDAY;

        if (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
            return 0;
        } else {
            return linearRate1.calculateTime(insertedSoFar);
        }
    }
}
