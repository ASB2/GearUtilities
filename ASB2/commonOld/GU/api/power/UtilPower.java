package GU.api.power;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public class UtilPower {

    public static boolean useEnergyFromInventory(IInventory inventory, float power, boolean doUse) {

        for(int i = 0; i < inventory.getSizeInventory(); i++) {

            ItemStack stack = inventory.getStackInSlot(i);

            if(stack != null) {

                if(stack.getItem() instanceof IPowerItem) {

                    if(((IPowerItem) stack.getItem()).getPowerProvider(stack) != null) {

                        if(((IPowerItem) stack.getItem()).getPowerProvider(stack).usePower(power, ForgeDirection.UNKNOWN, false)) {

                            return ((IPowerItem) stack.getItem()).getPowerProvider(stack).usePower(power, ForgeDirection.UNKNOWN, doUse);
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean addEnergyToInventory(IInventory inventory, float power, boolean doUse) {

        for(int i = 0; i < inventory.getSizeInventory(); i++) {

            ItemStack stack = inventory.getStackInSlot(i);

            if(stack != null && stack.getItem() instanceof IPowerItem) {

                if(((IPowerItem) stack.getItem()).getPowerProvider(stack).gainPower(power, ForgeDirection.UNKNOWN, false)) {

                    return ((IPowerItem) stack.getItem()).getPowerProvider(stack).gainPower(power, ForgeDirection.UNKNOWN, doUse);
                }
            }
        }
        return false;
    }

    public static boolean moveEnergy(IPowerProvider source, IPowerProvider sink, ForgeDirection sourceDirection, ForgeDirection sinkDirection, boolean doWork) {

        if(source != null && sink != null) {

            float amount = source.getPowerClass().getPowerValue();
            return UtilPower.moveEnergy(source, sink, sourceDirection, sinkDirection, amount, doWork);
        }
        return false;
    }

    public static boolean moveEnergy(IPowerProvider source, IPowerProvider sink, ForgeDirection sourceDirection, ForgeDirection sinkDirection, float power, boolean doWork) {

        if(source != null && sink != null) {

            if(UtilPower.removeEnergyFromProvider(source, sourceDirection, power, false)) {

                if(UtilPower.addEnergyToProvider(sink, sinkDirection, power, false)) {

                    UtilPower.removeEnergyFromProvider(source, sourceDirection, power, doWork);
                    UtilPower.addEnergyToProvider(sink, sinkDirection, power, doWork);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean addEnergyToProvider(IPowerProvider powerProvider, ForgeDirection direction, float power, boolean doWork) {

        if(powerProvider != null) {

            return UtilPower.addEnergyToProvider(powerProvider, direction, power, doWork, false);
        }
        return false;
    }

    public static boolean addEnergyToProvider(IPowerProvider powerProvider, ForgeDirection direction, float power, boolean doWork, boolean ignoreState) {

        if(powerProvider != null) {

            if(ignoreState) {

                if(powerProvider.gainPower(power, direction, doWork)) {

                    return true;
                }
            }
            else {

                if(powerProvider.getState() == State.SINK || powerProvider.getState() == State.OTHER) {

                    if(powerProvider.gainPower(power, direction, doWork)) {

                        return true;
                    }
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
