package GU.api.power;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;

public class PowerHelper {

    public static boolean addEnergyToProviderFromInventory(IPowerProvider thingToAddPowerTo, IInventory inventory, float power, boolean doUse) {

        if(thingToAddPowerTo.gainPower(power, ForgeDirection.UNKNOWN, false) && PowerHelper.useEnergyFromInventory(inventory, power, false)) {

            PowerHelper.useEnergyFromInventory(inventory, power, true);
            thingToAddPowerTo.gainPower(power, ForgeDirection.UNKNOWN, true);
            return true;
        }
        return false;
    }

    public static boolean useEnergyFromInventory(IInventory inventory, float power, boolean doUse) {

        for(int i = 0; i < inventory.getSizeInventory(); i++) {

            ItemStack stack  = inventory.getStackInSlot(i);

            if(stack != null && stack.getItem() instanceof IPowerItem ) {

                if(((IPowerItem)stack.getItem()).getPowerProvider(stack).usePower(power, ForgeDirection.UNKNOWN, false)) {

                    ((IPowerItem)stack.getItem()).getPowerProvider(stack).usePower(power, ForgeDirection.UNKNOWN, doUse);
                    return true;
                }  
            }
        } 
        return false;
    }

    public static boolean moveEnergy(IPowerProvider source, IPowerProvider sink, ForgeDirection sourceDirection, ForgeDirection sinkDirection, boolean doWork) {

        if(source != null && sink != null) {

            float amount = source.getPowerClass().getPowerValue();
            return PowerHelper.moveEnergy(source, sink, sourceDirection, sinkDirection, amount, doWork);
        }
        return false;
    }

    public static boolean moveEnergy(IPowerProvider source, IPowerProvider sink, ForgeDirection sourceDirection, ForgeDirection sinkDirection, float power, boolean doWork) {

        if(source != null && sink != null) {

            if(source != null && sink != null) {

                if(PowerHelper.removeEnergyFromProvider(source, sourceDirection, power, false)) {

                    if(PowerHelper.addEnergyToProvider(sink, sinkDirection, power, false)) {

                        PowerHelper.removeEnergyFromProvider(source, sourceDirection, power, doWork);
                        PowerHelper.addEnergyToProvider(sink, sinkDirection, power, doWork);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean addEnergyToProvider(IPowerProvider powerProvider, ForgeDirection direction, float power, boolean doWork) {

        if(powerProvider != null) {

            if(powerProvider.getState() == State.SINK || powerProvider.getState() == State.OTHER) {

                if(powerProvider.gainPower(power, direction, doWork)) {

                    return true;
                }
            }
        }
        return false;
    }

    public static boolean removeEnergyFromProvider(IPowerProvider powerProvider, ForgeDirection direction, float power, boolean doWork) {

        if(powerProvider != null) {

            if(powerProvider.getState() == State.SOURCE || powerProvider.getState() == State.OTHER) {

                if(powerProvider.usePower(power, direction, doWork)) {

                    return true;
                }
            }
        }
        return false;
    }
}
