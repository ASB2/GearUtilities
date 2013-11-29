package GU.blocks.containers.BlockGyro.BlockSolarGyro;

import net.minecraftforge.common.ForgeDirection;
import GU.api.power.IPowerMisc;
import GU.api.power.IPowerProvider;
import GU.api.power.PowerClass;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;

public class TileSolarGyro extends TileBase implements IPowerMisc {

    public TileSolarGyro() {

        this.powerProvider = new PowerProvider(PowerClass.LOW, State.SOURCE);
        waitTimer = new Wait(20, this, 0);
    }

    @Override
    public void updateEntity() {

        waitTimer.update();
    }

    @Override
    public void trigger(int id) {

        if(worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord) && worldObj.isDaytime()) {

            powerProvider.gainPower(.5f, ForgeDirection.UP, true);
        }
    }

    @Override
    public IPowerProvider getPowerProvider() {

        return powerProvider;
    }
}
