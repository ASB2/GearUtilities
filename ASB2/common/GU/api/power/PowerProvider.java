package GU.api.power;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.utils.UtilDirection;
import GU.utils.UtilPower;

public abstract class PowerProvider {

    NBTTagCompound ntbTag = new NBTTagCompound();

    protected State currentState;
    protected PowerClass powerClass;

    int x;
    int y;
    int z;
    
    TileEntity tile;

    protected int powerStored = 0;
    protected int powerMax;

    public PowerProvider(TileEntity tile, int powerMax, PowerClass pClass) {

        powerClass = pClass;
        this.tile = tile;
        this.powerMax = powerMax;
        PowerManager.getInstance().addPowerProvider(this);
    }

    public void updateProvider() {

        this.movePowerByDirection(ForgeDirection.DOWN);
        this.movePowerByDirection(ForgeDirection.UP);

        this.movePowerByDirection(ForgeDirection.EAST);
        this.movePowerByDirection(ForgeDirection.WEST);

        this.movePowerByDirection(ForgeDirection.NORTH);
        this.movePowerByDirection(ForgeDirection.SOUTH);
    }

    public void movePowerByDirection(ForgeDirection direction) {

        World worldObj = tile.worldObj;

        int[] coords = UtilDirection.translateDirectionToCoords(direction, tile);

        if(worldObj.blockExists(coords[0], coords[1], coords[2])) {

            TileEntity tileToAffect = UtilDirection.translateDirectionToTile(tile, tile.worldObj, direction);

            if(tileToAffect != null) {

                if(tileToAffect instanceof IPowerMisc) {

                    IPowerMisc tileToAffectCasted = ((IPowerMisc)tileToAffect);

                    if(tileToAffectCasted.getPowerProvider() != null) {

                        switch(this.getCurrentState()) {

                            case SINK: {

                                if(tileToAffectCasted.getPowerProvider().getCurrentState() == State.SOURCE) {

                                    UtilPower.transferPower(tileToAffectCasted, (IPowerMisc)tile);
                                }
                            }
                            break;

                            case SOURCE: {

                                if(tileToAffectCasted.getPowerProvider().getCurrentState() == State.SINK) {

                                    UtilPower.transferPower((IPowerMisc)tile, tileToAffectCasted);
                                }
                            }
                            break;

                            case OTHER: break;
                        }
                    }
                }
            }
        }
    }

    public boolean getShouldWork() {
        
        if(tile != null && tile.worldObj != null) {
            
            return !tile.worldObj.isBlockIndirectlyGettingPowered(tile.xCoord, tile.yCoord, tile.zCoord);
        }
        return false;
    }
    /**
     * Get the amount of power stored in the block
     * @return Amount of power stored
     */
    public int getPowerStored() {

        //this.readFromNBT(ntbTag);
        return powerStored;
    }

    /**
     * Get maximum power capacity of the block
     */
    public int getPowerMax() {

        return powerMax;
    }

    public PowerClass getPowerClass() {

        return this.powerClass;
    }

    public State getCurrentState() {

        if(currentState == null) {

            if(this.outputtingPower()) {

                currentState = State.SOURCE;
            }

            else if(this.requestingPower()) {

                currentState = State.SINK;
            }

            else {

                currentState = State.OTHER;
            }
        }

        if(this.outputtingPower()) {

            currentState = State.SOURCE;
        }

        else if(this.requestingPower()) {

            currentState = State.SINK;
        }

        else {

            currentState = State.OTHER;
        }
        return currentState;
    }

    public boolean gainPower(int PowerGained) {

        if(this.getPowerMax() - this.getPowerStored() >= PowerGained) {

            this.setPower(this.getPowerStored() + PowerGained);

            return true;
        }
        return false;
    }

    public boolean usePower(int PowerUsed) {

        if(this.getPowerStored() >= PowerUsed) {

            this.setPower(this.getPowerStored() - PowerUsed);

            return true;
        }
        return false;
    }

    public void setPower(int newPower) {        

        this.powerStored = newPower;
    }

    public boolean requestingPower() {

        if(getPowerStored() < getPowerMax())
            return true;

        return false;
    }

    public boolean outputtingPower() {

        if(getPowerStored() > 0)
            return true;

        return false;
    }

    public boolean canGainPower(int power) {

        if(this.getPowerMax() - this.getPowerStored() >= power) {

            return true;
        }
        return false; 
    }

    public boolean canUsePower(int power) {

        if(this.getPowerStored() >= power) {

            return true;
        }
        return false;
    }

    public void readFromNBT(NBTTagCompound tagCompound) {

        powerStored = tagCompound.getInteger("powerStored");
        powerMax = tagCompound.getInteger("powerMax");
    }

    public void writeToNBT(NBTTagCompound tagCompound) {

        tagCompound.setInteger("powerStored", powerStored);
        tagCompound.setInteger("powerMax", powerMax);
    }
}
