package GU.blocks.containers.BlockEnergyCube;

import net.minecraft.tileentity.TileEntity;
import ASB2.utils.UtilDirection;
import GU.api.power.IPowerMisc;
import GU.api.power.IPowerProvider;
import GU.api.power.PowerClass;
import GU.api.power.PowerHelper;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;

public class TileEnergyCube extends TileBase implements IPowerMisc {

    public TileEnergyCube() {

        this.powerProvider = new PowerProvider(4000, PowerClass.LOW, State.OTHER);
        this.waitTimer = new Wait(10, this, 0);
    }

    public void updateEntity() {    

        waitTimer.update();
    }

    public void trigger(int id) {

        TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation());

        if(tile != null && tile instanceof IPowerMisc) {

            PowerHelper.moveEnergy(this, (IPowerMisc)tile, this.getOrientation(), true);
        }
    }

    @Override
    public IPowerProvider getPowerProvider() {

        return powerProvider;
    }
}
