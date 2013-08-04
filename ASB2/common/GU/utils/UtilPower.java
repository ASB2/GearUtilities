package GU.utils;

import GU.api.power.IPowerMisc;

public class UtilPower {
    
    public static int TICKSTOPOWER = 10;

    public static boolean transferPower(IPowerMisc powerSource, IPowerMisc powerSink) {

        int amountOfPower = powerSource.getPowerProvider().getPowerClass().getPowerValue();

        if (powerSink.getPowerProvider() != null && powerSource.getPowerProvider() != null) {

            if(powerSink.getPowerProvider().canGainPower(amountOfPower)) {

                if(powerSource.getPowerProvider().canUsePower(amountOfPower)) {
                    
                    if(powerSink.getPowerProvider().gainPower(amountOfPower)) {

                        powerSource.getPowerProvider().usePower(amountOfPower);

                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean transferPower(IPowerMisc powerSource, IPowerMisc powerSink, int amountOfPower) {

        if (powerSink.getPowerProvider() != null && powerSource.getPowerProvider() != null) {

            if(powerSink.getPowerProvider().canGainPower(amountOfPower)) {

                if(powerSource.getPowerProvider().canUsePower(amountOfPower)) {
                    
                    if(powerSink.getPowerProvider().gainPower(amountOfPower)) {

                        powerSource.getPowerProvider().usePower(amountOfPower);

                        return true;
                    }
                }
            }
        }
        return false;
    }
}
