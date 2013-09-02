package GU.api.power;

import net.minecraftforge.common.ForgeDirection;

public class UtilPower {

    public static boolean transferPower(IPowerMisc powerSource, ForgeDirection direction, IPowerMisc powerSink) {

        ForgeDirection opposite = direction.getOpposite();

        float amountOfPower = powerSource.getPowerProvider().getPowerClass().getPowerValue();

        if (powerSink.getPowerProvider() != null && powerSource.getPowerProvider() != null) {

            if (powerSink.getPowerProvider().gainPower(amountOfPower, direction, false)) {

                if (powerSource.getPowerProvider().usePower(amountOfPower, opposite, false)) {

                    if (powerSink.getPowerProvider().gainPower(amountOfPower, direction, true)) {

                        powerSource.getPowerProvider().usePower(amountOfPower, opposite, true);

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean transferPower(IPowerMisc powerSource, ForgeDirection direction, IPowerMisc powerSink, float amountOfPower) {

        ForgeDirection opposite = direction.getOpposite();

        if (powerSink.getPowerProvider() != null && powerSource.getPowerProvider() != null) {

            if (powerSink.getPowerProvider().gainPower(amountOfPower, direction, false)) {

                if (powerSource.getPowerProvider().usePower(amountOfPower, opposite, false)) {

                    if (powerSink.getPowerProvider().gainPower(amountOfPower, direction, true)) {

                        powerSource.getPowerProvider().usePower(amountOfPower, opposite, true);

                        return true;
                    }
                }
            }
        }
        return false;
    }
}
