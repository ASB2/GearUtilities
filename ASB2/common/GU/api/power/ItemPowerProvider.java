package GU.api.power;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilItemStack;

public class ItemPowerProvider implements IPowerProvider {

    ItemStack item;
    State currentState = State.OTHER;

    public ItemPowerProvider(ItemStack stack, float powerMax, State state) {

        this(stack, powerMax);
        currentState = state;
    }

    public ItemPowerProvider(ItemStack stack, float powerMax) {

        this.item = stack;
        this.setPowerMax(powerMax);
    }

    @Override
    public float getPowerStored() {

        return UtilItemStack.getNBTTagFloat(item, "powerStored");
    }

    @Override
    public float getPowerMax() {

        return UtilItemStack.getNBTTagFloat(item, "powerMax");
    }

    public boolean usePower(float power, ForgeDirection direction, boolean doUse) {

        if(currentState == State.SINK) {

            return false;
        }

        if(this.getMaxOutput() != -1) {

            if(power > this.getMaxOutput()) {

                return false;
            }
        }

        if(this.getMinOutput() != -1) {

            if(power < this.getMinOutput()) {

                return false;
            }
        }

        if(this.getPowerStored() >= power) {

            if(doUse)
                this.setPowerStored(this.getPowerStored() - power);

            return true;
        }
        return false;
    }

    public boolean gainPower(float power, ForgeDirection direction, boolean doUse) {

        if(currentState == State.SOURCE) {

            return false;
        }

        if(this.getMaxInput() != -1) {

            if(power > this.getMaxInput()) {

                return false;
            }
        }

        if(this.getMinInput() != -1) {

            if(power < this.getMinInput()) {

                return false;
            }
        }

        if(this.getPowerMax() - this.getPowerStored() >= power) {

            if(doUse)
                this.setPowerStored(this.getPowerStored() + power);

            return true;
        }
        return false;
    }

    @Override
    public void setPowerStored(float newPower) {

        UtilItemStack.setNBTTagFloat(item, "powerStored", newPower);
    }

    @Override
    public void setPowerMax(float newMaxPower) {

        UtilItemStack.setNBTTagFloat(item, "powerMax", newMaxPower);
    }

    @Override
    public PowerClass getPowerClass() {

        return PowerClass.OTHER;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        // TODO Auto-generated method stub

    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        // TODO Auto-generated method stub

    }

    @Override
    public IPowerProvider copy() {

        ItemPowerProvider provider = new ItemPowerProvider(item, this.getPowerMax());
        provider.setPowerStored(getPowerStored());
        return provider;
    }

    @Override
    public float getMinInput() {

        return -1;
    }

    @Override
    public float getMinOutput() {

        return -1;
    }

    @Override
    public float getMaxInput() {

        return -1;
    }

    @Override
    public float getMaxOutput() {

        return -1;
    }

    @Override
    public State getState() {

        return State.OTHER;
    }
}
