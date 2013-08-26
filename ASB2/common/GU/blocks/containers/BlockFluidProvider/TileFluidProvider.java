package GU.blocks.containers.BlockFluidProvider;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import GU.blocks.containers.TileBase;
import GU.utils.UtilDirection;
import GU.utils.UtilFluid;

public class TileFluidProvider extends TileBase implements IFluidHandler {

    public FluidStack fluidStack;

    public TileFluidProvider() {

        fluidTank = new FluidTank(1000);
    }

    @Override
    public void updateEntity() {

        if (fluidStack != null) {

            fluidTank.setFluid(fluidStack);

            for (ForgeDirection direction : ForgeDirection.values()) {

                TileEntity tile = UtilDirection.translateDirectionToTile(this,
                        worldObj, direction);

                if (tile != null) {

                    if (tile instanceof IFluidHandler) {

                        IFluidHandler fTile = (IFluidHandler) tile;

                        if (!worldObj.isBlockIndirectlyGettingPowered(xCoord,
                                yCoord, zCoord)) {

                            UtilFluid.addFluidToTank(fTile, direction, fluidStack, true);
                        } else {

                            UtilFluid.removeFluidFromTank(fTile, direction, fluidStack, true);
                        }
                    }
                }
            }
        }
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        return fluidTank.fill(resource, doFill);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {

        if (fluidTank != null) {

            if (fluid != null) {

                if (fluidTank.getFluid() != null) {

                    if (this.fluidTank.getFluid().isFluidEqual(
                            new FluidStack(fluid, 0))) {

                        return true;
                    }
                } else {

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource,
            boolean doDrain) {

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
        this.fluidStack = fluidTank.getFluid();
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
    }
}
