package GU.blocks.containers.BlockTestTank;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.utils.UtilDirection;
import GU.utils.UtilFluid;

public class TileTestTank extends TileBase {

    private int maxLiquid = FluidContainerRegistry.BUCKET_VOLUME * 100;

    public TileTestTank() {

        fluidTank = new FluidTank(maxLiquid);
        waitTimer = new Wait(10, this, 0);
    }

    @Override
    public void updateEntity() {

        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

            if (!moveFluidBelow())
                this.moveAround();

        }
        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    }

    public int getFluidHandlersAround() {

        int amount = 0;

        for(ForgeDirection direction: ForgeDirection.values()) {

            if(direction != ForgeDirection.DOWN && direction != ForgeDirection.UP) {

                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                if(tile != null && tile instanceof TileTestTank) {

                    amount ++;
                }
            }       
        }
        return amount;
    }

    public boolean moveFluidBelow() {

        if(fluidTank.getFluid() != null) {

            IFluidHandler below = getTankBelow();

            if (below != null) {

                return UtilFluid.moveFluid(this, ForgeDirection.DOWN, below, FluidContainerRegistry.BUCKET_VOLUME);
            }
        }
        return false;
    }

    public boolean moveAround() {

        int amountDivided = FluidContainerRegistry.BUCKET_VOLUME / getFluidHandlersAround();

        for(ForgeDirection direction: ForgeDirection.values()) {

            if(direction != ForgeDirection.UP && direction != ForgeDirection.DOWN) {

                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                if(tile != null) {

                    if(tile instanceof IFluidHandler) {

                        IFluidHandler fTile = (IFluidHandler)tile;

                        for(FluidTankInfo info: fTile.getTankInfo(UtilDirection.translateDirectionToOpposite(direction))) {

                            if(info.fluid != null) {

                                if(info.fluid.isFluidEqual(this.fluidTank.getFluid())) {

                                    if(info.fluid.amount <= this.fluidTank.getFluidAmount()) {

                                        return UtilFluid.moveFluid(this, direction, (IFluidHandler)tile, amountDivided);   
                                    }
                                }
                            }
                            else {

                                return UtilFluid.moveFluid(this, direction, (IFluidHandler)tile, amountDivided);   
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

        if(fluidTank.getCapacity() == fluidTank.getFluidAmount()) {

            if(this.getTankAbove() != null) {

                return this.getTankAbove().fill(from, resource, doFill);
            }
        }

        return fluidTank.fill(resource, doFill);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {

        if(fluid != null) {

            if(fluidTank.getFluid() != null) {

                if(this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 1))) {

                    return true;
                } 
            }
            else {

                return true;
            }
        }

        if(this.getTankAbove() != null) {

            return this.getTankAbove().canFill(from, fluid);
        }
        return false;
    }

    public IFluidHandler getTankBelow() {

        TileEntity below = worldObj.getBlockTileEntity(xCoord, yCoord - 1, zCoord);

        if (below instanceof TileTestTank) {

            return (IFluidHandler) below;
        }
        return null;
    }

    public IFluidHandler getTankAbove() {

        TileEntity below = worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord);

        if (below instanceof TileTestTank) {

            return (IFluidHandler) below;
        }

        return null;
    }

    public void trigger(int id) {
    }
}
