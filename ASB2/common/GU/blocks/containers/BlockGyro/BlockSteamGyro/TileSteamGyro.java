package GU.blocks.containers.BlockGyro.BlockSteamGyro;

import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import ASB2.utils.UtilFluid;
import GU.api.power.IPowerMisc;
import GU.api.power.IPowerProvider;
import GU.api.power.PowerClass;
import GU.api.power.*;
import GU.api.power.State;
import GU.api.wait.Wait;
import GU.blocks.containers.TileFluidBase;

public class TileSteamGyro extends TileFluidBase implements IPowerMisc {

    // The amount of energy 100mb of steam is worth
    public static float STEAM_WORTH = 10;

    public TileSteamGyro() {

        waitTimer = new Wait(20, this, 0);
        this.fluidTank = new FluidTank(1000);
        this.powerProvider = new PowerProvider(PowerClass.LOW, State.SOURCE);
    }

    @Override
    public void updateEntity() {

        waitTimer.update();
    }

    @Override
    public void trigger(int id) {

        if(this.fluidTank.getFluidAmount() > 0) {

            FluidStack stack = this.fluidTank.getFluid().copy();
            stack.amount = 100;

            if(UtilFluid.removeFluidFromTank(this, ForgeDirection.UNKNOWN, stack, false)) {

                if(PowerHelper.addEnergyToProvider(this.getPowerProvider(), ForgeDirection.UNKNOWN, STEAM_WORTH, false, true)) {

                    PowerHelper.addEnergyToProvider(this.getPowerProvider(), ForgeDirection.UNKNOWN, STEAM_WORTH, true, true);
                    UtilFluid.removeFluidFromTank(this, ForgeDirection.UNKNOWN, stack, true);
                }
            }
        }
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

        if(resource.getFluid() == FluidRegistry.getFluid("steam")) {

            return fluidTank.fill(resource, doFill);
        }
        return 0;
    }

    @Override
    public IPowerProvider getPowerProvider() {

        return powerProvider;
    }
}
