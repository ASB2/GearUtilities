package GU.api.power;

import net.minecraftforge.common.ForgeDirection;

public class PowerHelper {

    public static boolean addEnergyToProvider(IPowerMisc powerProvider, ForgeDirection direction, float power) {

        if(powerProvider != null) {

            if(powerProvider.getPowerProvider() != null) {

                if(powerProvider.getPowerProvider().getCurrentState() == State.SINK || powerProvider.getPowerProvider().getCurrentState() == State.OTHER) {
                    
                    if(powerProvider.getPowerProvider().gainPower(power, direction, true)) {

                        return true;
                    }
                }
            }
        }
        return false;
    }
}
