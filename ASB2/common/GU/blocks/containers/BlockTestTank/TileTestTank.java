package GU.blocks.containers.BlockTestTank;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import GU.blocks.containers.TileBase;
import GU.utils.UtilDirection;
import GU.utils.UtilFluid;
import GU.api.wait.*;

public class TileTestTank extends TileBase {

    private int maxLiquid = FluidContainerRegistry.BUCKET_VOLUME * 64;
    public int throughtPut = 1000;
    
    public TileTestTank() {

        this.waitTimer = new Wait(20, this, 1);
        fluidTank = new FluidTank(maxLiquid);
    }

    @Override
    public void updateEntity() {

        waitTimer.update();

        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

            if(fluidTank.getFluid() != null && fluidTank.getFluid().getFluid() == FluidRegistry.WATER && fluidTank.getFluid().amount >= 2000) {

                UtilFluid.addFluidToTank(this, ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.WATER, 1000));
            }

            if(!moveFluidBelow())
                this.moveAround();
        }
    }


    public int getFluidHandlersAround() {

        int amount = 0;

        for(ForgeDirection direction: ForgeDirection.values()) {

            if(direction != ForgeDirection.DOWN && direction != ForgeDirection.UP) {

                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                if(tile != null && tile instanceof TileTestTank) {

                    TileTestTank tileC = (TileTestTank)tile;

                    if(!(tileC.fluidTank.getCapacity() == tileC.fluidTank.getFluidAmount())) {

                        amount ++;
                    }                    
                }
            }       
        }
        return amount;
    }

    public boolean moveFluidBelow() {

        if(fluidTank.getFluid() != null) {

            IFluidHandler below = getTankBelow();

            if (below != null) {

                return UtilFluid.moveFluid(this, ForgeDirection.DOWN, below, throughtPut);
            }
        }
        return false;
    }

    public boolean moveAround() {

        boolean itWorked = false;

        int amountDivided = throughtPut;

        if(this.getFluidHandlersAround() != 0) {

            amountDivided = throughtPut / this.getFluidHandlersAround();
        }

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

                                        itWorked = UtilFluid.moveFluid(this, direction, (IFluidHandler)tile, amountDivided);
                                    }
                                }
                            }
                            else {

                                itWorked = UtilFluid.moveFluid(this, direction, (IFluidHandler)tile, amountDivided);
                            }
                        }
                    }
                }
            }
        }

        return itWorked;
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

    public TileTestTank getTankBelow() {

        TileEntity below = worldObj.getBlockTileEntity(xCoord, yCoord - 1, zCoord);

        if (below instanceof TileTestTank) {

            return (TileTestTank) below;
        }
        return null;
    }

    public TileTestTank getTankAbove() {

        TileEntity below = worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord);

        if (below instanceof TileTestTank) {

            return (TileTestTank) below;
        }

        return null;
    }

    @Override
    public void trigger(int id) {

        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    }
}
