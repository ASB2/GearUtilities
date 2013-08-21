package GU.blocks.containers.BlockPowerTest;

import GU.blocks.containers.*;
import GU.power.*;
import GU.api.power.*;

public class TilePowerTest extends TileBase implements IPowerMisc {

    public TilePowerTest() {

        this.powerProvider = new GUPowerProvider(1000, PowerClass.LOW,
                State.OTHER);
    }

    @Override
    public void updateEntity() {
        this.getPowerProvider().setPower(this.getPowerProvider().getPowerMax());
        ((GUPowerProvider) this.getPowerProvider()).movePower(worldObj, xCoord,
                yCoord, zCoord, !worldObj.isBlockIndirectlyGettingPowered(
                        xCoord, yCoord, zCoord));
    }

    @Override
    public PowerProvider getPowerProvider() {

        return powerProvider;
    }
}
