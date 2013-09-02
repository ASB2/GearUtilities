package GU.blocks.containers.BlockPowerTest;

import GU.api.power.IPowerMisc;
import GU.api.power.PowerClass;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.blocks.containers.TileBase;
import GU.power.GUPowerProvider;

public class TilePowerTest extends TileBase implements IPowerMisc {

    public TilePowerTest() {

        this.powerProvider = new GUPowerProvider(PowerClass.LOW, State.OTHER);
    }

    @Override
    public void updateEntity() {

        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

            this.getPowerProvider().setPower(this.getPowerProvider().getPowerMax());         
        }
        else {
            
            this.getPowerProvider().setPower(0);   
        }
        
        ((GUPowerProvider) this.getPowerProvider()).movePower(worldObj, xCoord, yCoord, zCoord, !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord));
    }

    @Override
    public PowerProvider getPowerProvider() {

        return powerProvider;
    }
}
