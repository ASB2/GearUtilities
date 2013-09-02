package GU.api.power;

import net.minecraftforge.common.ForgeDirection;

public class PowerHelper {

    public static boolean addEnergyToProvider(IPowerMisc powerProvider, ForgeDirection direction, float power, boolean doWork) {

        if(powerProvider != null) {

            if(powerProvider.getPowerProvider() != null) {

                if(powerProvider.getPowerProvider().getCurrentState() == State.SINK || powerProvider.getPowerProvider().getCurrentState() == State.OTHER) {

                    if(powerProvider.getPowerProvider().gainPower(power, direction, doWork)) {

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean removeEnergyFromProvider(IPowerMisc powerProvider, ForgeDirection direction, float power, boolean doWork) {

        if(powerProvider != null) {

            if(powerProvider.getPowerProvider() != null) {

                if(powerProvider.getPowerProvider().getCurrentState() == State.SOURCE || powerProvider.getPowerProvider().getCurrentState() == State.OTHER) {

                    if(powerProvider.getPowerProvider().usePower(power, direction, doWork)) {

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean moveEnergy(IPowerMisc source, IPowerMisc sink, ForgeDirection direction, float power, boolean doWork) {

        if(source != null && sink != null) {

            if(source.getPowerProvider() != null && sink.getPowerProvider() != null) {

                if(PowerHelper.removeEnergyFromProvider(source, direction, power, false)) {

                    if(PowerHelper.addEnergyToProvider(sink, direction, power, false)) {

                        PowerHelper.removeEnergyFromProvider(source, direction, power, doWork);
                        PowerHelper.addEnergyToProvider(sink, direction.getOpposite(), power, doWork);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean moveEnergy(IPowerMisc source, IPowerMisc sink, ForgeDirection direction, boolean doWork) {

        if(source != null && sink != null) {

            if(source.getPowerProvider() != null && sink.getPowerProvider() != null) {

                float amount = source.getPowerProvider().getPowerClass().getPowerValue();
                return PowerHelper.moveEnergy(source, sink, direction, amount, doWork);
            }
        }

        return false;
    }
}
