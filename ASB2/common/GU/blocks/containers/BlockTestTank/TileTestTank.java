package GU.blocks.containers.BlockTestTank;

import cpw.mods.fml.common.network.PacketDispatcher;
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
import GU.packets.*;

public class TileTestTank extends TileBase {

    private int maxLiquid = FluidContainerRegistry.BUCKET_VOLUME * 64;

    public TileTestTank() {

        this.waitTimer = new Wait(20, this, 1);
        fluidTank = new FluidTank(maxLiquid);
    }

    @Override
    public void updateEntity() {

        waitTimer.update();

        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

            if(fluidTank.getFluid() != null && fluidTank.getFluid().getFluid() == FluidRegistry.WATER && fluidTank.getFluidAmount() >= 2000) {

                if(fluidTank.getCapacity() - fluidTank.getFluidAmount() >= 1000) {
                    
                    UtilFluid.addFluidToTank(this, ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.WATER, 1000));
                }
                else {
                 
                    UtilFluid.addFluidToTank(this, ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.WATER, fluidTank.getCapacity() - fluidTank.getFluidAmount()));
                }
            }

            if(!this.moveFluidBelow())
                this.moveAround();

            worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        }
    }


    public int getFluidHandlersAround() {

        int amount = 0;

        for(ForgeDirection direction: ForgeDirection.values()) {

            TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

            if(tile != null && tile instanceof TileTestTank) {

                TileTestTank tileC = (TileTestTank)tile;

                if(!(tileC.fluidTank.getCapacity() == tileC.fluidTank.getFluidAmount())) {

                    amount ++;
                }                    
            }   
        }
        return amount;
    }

    public boolean moveFluidBelow() {

        if(fluidTank.getFluid() != null) {

            IFluidHandler below = getTankBelow(this);

            if (below != null) {

                if(fluidTank.getFluidAmount() >= 5000) 
                    return UtilFluid.moveFluid(this, ForgeDirection.DOWN, below, 5000, true);

                if(fluidTank.getFluidAmount() >= 4000) 
                    return UtilFluid.moveFluid(this, ForgeDirection.DOWN, below, 4000, true);

                if(fluidTank.getFluidAmount() >= 3000) 
                    return UtilFluid.moveFluid(this, ForgeDirection.DOWN, below, 3000, true);

                if(fluidTank.getFluidAmount() >= 2000) 
                    return UtilFluid.moveFluid(this, ForgeDirection.DOWN, below, 2000, true);

                if(fluidTank.getFluidAmount() >= 1000) 
                    return UtilFluid.moveFluid(this, ForgeDirection.DOWN, below, 1000, true);

                return UtilFluid.moveFluid(this, ForgeDirection.DOWN, below, fluidTank.getFluidAmount(), true);
            }
        }
        return false;
    }

    public boolean moveAround() {

        boolean itWorked = false;

        int amountDivided = 1000;

        if(fluidTank.getFluidAmount() >= 5000) 
            amountDivided = 5000;

        if(fluidTank.getFluidAmount() >= 4000) 
            amountDivided = 4000;

        if(fluidTank.getFluidAmount() >= 3000) 
            amountDivided = 3000;

        if(fluidTank.getFluidAmount() >= 2000) 
            amountDivided = 2000;
        
        if(this.getFluidHandlersAround() != 0) {

            amountDivided = 1000 / this.getFluidHandlersAround();
        }
        
        if(fluidTank.getFluidAmount() < 1000) 
            amountDivided = fluidTank.getFluidAmount();

        for(ForgeDirection direction: ForgeDirection.values()) {

            if(direction != ForgeDirection.UP) {

                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                if(tile != null) {

                    if(tile instanceof IFluidHandler) {

                        IFluidHandler fTile = (IFluidHandler)tile;

                        for(FluidTankInfo info: fTile.getTankInfo(UtilDirection.translateDirectionToOpposite(direction))) {

                            if(info.fluid != null) {

                                if(info.fluid.isFluidEqual(this.fluidTank.getFluid())) {

                                    if(info.fluid.amount <= this.fluidTank.getFluidAmount()) {

                                        itWorked = UtilFluid.moveFluid(this, direction, (IFluidHandler)tile, amountDivided, true);
                                    }
                                }
                            }
                            else {

                                itWorked = UtilFluid.moveFluid(this, direction, (IFluidHandler)tile, amountDivided, true);
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

        return fluidTank.fill(resource, doFill);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {

        if(fluid != null) {

            if(fluidTank.getFluid() != null) {

                if(this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 0))) {

                    return true;
                } 
            }
            else {

                return true;
            }
        }
        return false;
    }

    public TileTestTank getTankBelow(TileEntity tile) {

        TileEntity below = worldObj.getBlockTileEntity(tile.xCoord, tile.yCoord - 1, tile.zCoord);

        if (below instanceof TileTestTank) {

            return (TileTestTank) below;
        }
        return null;
    }

    public TileTestTank getTankAbove(TileEntity tile) {

        TileEntity below = worldObj.getBlockTileEntity(tile.xCoord, tile.yCoord + 1, tile.zCoord);

        if (below instanceof TileTestTank) {

            return (TileTestTank) below;
        }

        return null;
    }

    @Override
    public void trigger(int id) {

        if(id == 1) {
            if(fluidTank.getFluid() != null) {

                PacketDispatcher.sendPacketToAllAround(xCoord,yCoord, zCoord, 20, worldObj.provider.dimensionId, new TestTankPacket(xCoord, yCoord, zCoord, fluidTank.getFluid().getFluid().getID(), fluidTank.getFluid().amount).makePacket());
            }
            else {

                PacketDispatcher.sendPacketToAllAround(xCoord,yCoord, zCoord, 20, worldObj.provider.dimensionId, new TestTankPacket(xCoord, yCoord, zCoord, 0, 0).makePacket());
            }
        }
    }
}
