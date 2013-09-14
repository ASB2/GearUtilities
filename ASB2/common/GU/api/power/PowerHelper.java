package GU.api.power;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;

public class PowerHelper {

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

    public static boolean addEnergyToProvider(IPowerMisc powerProvider, ForgeDirection direction, float power, boolean doWork) {

        if(powerProvider != null) {

            if(powerProvider.getPowerProvider() != null) {

                if(powerProvider.getPowerProvider().gainPower(power, direction, doWork)) {

                    return true;
                }
            }
        }
        return false;
    }

    public static boolean removeEnergyFromProvider(IPowerMisc powerProvider, ForgeDirection direction, float power, boolean doWork) {

        if(powerProvider != null) {

            if(powerProvider.getPowerProvider() != null) {

                if(powerProvider.getPowerProvider().usePower(power, direction, doWork)) {

                    return true;
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

    public static boolean moveEnergy(IPowerProvider source, IPowerProvider sink, ForgeDirection direction, float power, boolean doWork) {

        if(source != null && sink != null) {

            if(source != null && sink != null) {

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

    public static boolean addEnergyToProvider(IPowerProvider powerProvider, ForgeDirection direction, float power, boolean doWork) {

        if(powerProvider != null) {

            if(powerProvider.gainPower(power, direction, doWork)) {

                return true;
            }
        }
        return false;
    }

    public static boolean removeEnergyFromProvider(IPowerProvider powerProvider, ForgeDirection direction, float power, boolean doWork) {

        if(powerProvider != null) {

            if(powerProvider.usePower(power, direction, doWork)) {

                return true;
            }
        }
        return false;
    }
}
