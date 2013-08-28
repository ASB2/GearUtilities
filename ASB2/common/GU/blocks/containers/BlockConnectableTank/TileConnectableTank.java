package GU.blocks.containers.BlockConnectableTank;

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

public class TileConnectableTank extends TileBase implements IFluidHandler {

    public static int maxLiquid = FluidContainerRegistry.BUCKET_VOLUME * 64;

    public TileConnectableTank() {

        this.waitTimer = new Wait(20, this, 1);
        fluidTank = new FluidTank(maxLiquid);
    }

    @Override
    public void updateEntity() {

        waitTimer.update();

        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

            if(fluidTank.getFluid() != null && fluidTank.getFluid().getFluid() == FluidRegistry.WATER && fluidTank.getFluidAmount() >= 2000) {

                if(fluidTank.getCapacity() - fluidTank.getFluidAmount() >= 1000) {

                    UtilFluid.addFluidToTank(this, ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.WATER, 1000), true);
                } 
                else {

                    UtilFluid.addFluidToTank(this, ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.WATER, fluidTank.getCapacity() - fluidTank.getFluidAmount()), true);
                }
            }

            if(!this.moveFluidBelow())
                this.moveAround();
        }
    }

    public int getFluidHandlersAround() {

        int amount = 0;

        for (ForgeDirection direction : ForgeDirection.values()) {

            TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

            if(tile != null && tile instanceof TileConnectableTank) {

                TileConnectableTank tileC = (TileConnectableTank) tile;

                if(!(tileC.fluidTank.getCapacity() == tileC.fluidTank.getFluidAmount()) && direction != ForgeDirection.UP) {

                    amount++;
                }
            }
        }
        return amount;
    }

    public boolean moveFluidBelow() {

        if(fluidTank.getFluid() != null) {

            IFluidHandler below = getTankBelow(this);

            if(below != null) {

                if(fluidTank.getFluidAmount() >= 1000) {

                    return UtilFluid.moveFluid(this, ForgeDirection.DOWN, below, 1000, true);
                }
                return UtilFluid.moveFluid(this, ForgeDirection.DOWN, below, fluidTank.getFluidAmount(), true);
            }
        }
        return false;
    }

    public boolean moveAround() {

        boolean itWorked = false;

        if(this.fluidTank.getFluidAmount() >= this.getFluidHandlersAround() * 1000) {

            int amountDivided = 1000;

            for (ForgeDirection direction : ForgeDirection.values()) {

                if(direction != ForgeDirection.UP) {

                    TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                    if(tile != null) {

                        if(tile instanceof IFluidHandler) {

                            IFluidHandler fTile = (IFluidHandler) tile;

                            if(fTile.getTankInfo(direction.getOpposite()) != null) {

                                for (FluidTankInfo info : fTile.getTankInfo(direction.getOpposite())) {

                                    if(info.fluid != null) {

                                        if(info.fluid.isFluidEqual(this.fluidTank.getFluid())) {

                                            if(info.fluid.amount <= this.fluidTank.getFluidAmount()) {

                                                itWorked = UtilFluid.moveFluid(this, direction, (IFluidHandler)tile, amountDivided, true);
                                            }
                                        }
                                    } else {

                                        itWorked = UtilFluid.moveFluid(this, direction, (IFluidHandler) tile, amountDivided, true);
                                    }
                                }
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

        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        return fluidTank.fill(resource, doFill);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {

        if(fluidTank != null) {

            if(fluid != null) {

                if(fluidTank.getFluid() != null) {

                    if(this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 0))) {

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

        if(resource == null || !resource.isFluidEqual(fluidTank.getFluid())) {

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

        if(fluid != null) {
            
            if(this.fluidTank.getFluid() != null) {

                if(fluidTank.getFluidAmount() > 0) {

                    if(this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 1))) {

                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {

        return new FluidTankInfo[] { fluidTank.getInfo() };
    }

    public TileConnectableTank getTankBelow(TileEntity tile) {

        TileEntity below = worldObj.getBlockTileEntity(tile.xCoord, tile.yCoord - 1, tile.zCoord);

        if(below instanceof TileConnectableTank) {

            return (TileConnectableTank) below;
        }
        return null;
    }

    public TileConnectableTank getTankAbove(TileEntity tile) {

        TileEntity below = worldObj.getBlockTileEntity(tile.xCoord,
                tile.yCoord + 1, tile.zCoord);

        if(below instanceof TileConnectableTank) {

            return (TileConnectableTank) below;
        }

        return null;
    }

    @Override
    public void trigger(int id) {

        if(id == 1) {
            if(fluidTank.getFluid() != null) {

                PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 20, worldObj.provider.dimensionId, new ConnectableTankPacket(xCoord, yCoord, zCoord, fluidTank.getFluid().getFluid().getID(), fluidTank.getFluid().amount).makePacket());
            } 
            else {

                PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 20, worldObj.provider.dimensionId, new ConnectableTankPacket(xCoord, yCoord, zCoord, 0, 0).makePacket());
            }
        }
    }
}
