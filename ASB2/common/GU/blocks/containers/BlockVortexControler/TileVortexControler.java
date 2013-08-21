package GU.blocks.containers.BlockVortexControler;

import net.minecraftforge.common.ForgeDirection;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerClass;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.blocks.containers.TileBase;
import GU.entity.EntityVortex.EntityVortex;
import GU.power.GUPowerProvider;
import GU.info.*;

public class TileVortexControler extends TileBase implements IPowerMisc {

    EntityVortex vortex;

    public TileVortexControler() {

        powerProvider = new GUPowerProvider(1000, PowerClass.HIGH, State.SINK);
    }

    @Override
    public void updateEntity() {

        if (canCreateVortex()) {

            this.createVortex();
        }
    }

    public void createVortex() {

        if (canCreateVortex()) {

            vortex = new EntityVortex(worldObj, xCoord, yCoord + 1, zCoord);
        }
    }

    public boolean canCreateVortex() {

        return vortex == null || vortex.isDead;
    }

    public boolean sustainVortex() {

        if (this.getPowerProvider().canUsePower(
                Variables.BASIC_VORTEX_COST_PER_TICK, ForgeDirection.UNKNOWN)) {

        }
        return false;
    }

    @Override
    public PowerProvider getPowerProvider() {

        return powerProvider;
    }

}
