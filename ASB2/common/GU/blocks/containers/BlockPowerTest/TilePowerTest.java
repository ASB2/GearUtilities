package GU.blocks.containers.BlockPowerTest;

import GU.blocks.containers.*;
import GU.power.*;
import GU.api.power.*;

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
