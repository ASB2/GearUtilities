package GU.utils;

import GU.api.power.IPowerMisc;

public class UtilPower {

    public static int LOW_POWER = 1;
    public static int MID_POWER = 10;
    public static int HIGH_POWER = 100;
    
    public static int TICKSTOPOWER = 10;

    public static boolean transferPower(IPowerMisc powerSource, IPowerMisc powerSink) {

        int amountOfPower = 0;

        switch(powerSource.getPowerProvider().getPowerClass()) {

            case LOW: amountOfPower = LOW_POWER; break;
            case MID: amountOfPower = MID_POWER; break;
            case HIGH: amountOfPower = HIGH_POWER; break;
            default: amountOfPower = 1;
        }

        if (powerSink.getPowerProvider() != null && powerSource.getPowerProvider() != null) {

            if(powerSink.getPowerProvider().canGainPower(amountOfPower)) {

                if(powerSource.getPowerProvider().canUsePower(amountOfPower)) {
                    
                    if(powerSink.getPowerProvider().gainPower(amountOfPower, UtilDirection.translateDirectionToOpposite(powerSource.getOrientation()))) {

                        powerSource.getPowerProvider().usePower(amountOfPower, powerSource.getOrientation());

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
                    
                    if(powerSink.getPowerProvider().gainPower(amountOfPower, UtilDirection.translateDirectionToOpposite(powerSource.getOrientation()))) {

                        powerSource.getPowerProvider().usePower(amountOfPower, powerSource.getOrientation());

                        return true;
                    }
                }
            }
        }
        return false;
    }
}
