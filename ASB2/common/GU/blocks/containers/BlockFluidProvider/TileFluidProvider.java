package GU.blocks.containers.BlockFluidProvider;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import GU.blocks.containers.TileBase;

public class TileFluidProvider extends TileBase implements IFluidHandler {

    public TileFluidProvider() {

        fluidTank = new FluidTank(1000);
    }

    @Override
    public void updateEntity() {

        if(fluidTank.getFluid() != null) {

            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                if (tile != null) {

                    if (tile instanceof IFluidHandler) {

                        IFluidHandler fTile = (IFluidHandler) tile;

                        if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

                            UtilFluid.addFluidToTank(fTile, direction, fluidTank.getFluid(), true);
                        } 
                        else {

                            UtilFluid.removeFluidFromTank(fTile, direction, fluidTank.getFluid(), true);
                        }
                    }
                }
            }
        }
    }

    public void setFluid(FluidStack fluid) {

        fluidTank.setFluid(fluid);
        fluidTank.setCapacity(fluid.amount);
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

        return 0;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {

        return false;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {

        if (resource == null || !resource.isFluidEqual(fluidTank.getFluid())) {

            return null;
        }

        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        return fluidTank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {

        return fluidTank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {

        if (this.fluidTank.getFluid() != null) {

            if (fluidTank.getFluidAmount() > 0) {

                if (this.fluidTank.getFluid().isFluidEqual(
                        new FluidStack(fluid, 1))) {

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {

        return new FluidTankInfo[] { fluidTank.getInfo() };
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
    }
}
