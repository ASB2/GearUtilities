package GU.blocks.containers.BlockConnectableTank;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.packets.TankPacket;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TileConnectableTank extends TileBase implements IFluidHandler {

    public static int maxLiquid = FluidContainerRegistry.BUCKET_VOLUME * 64;

    public TileConnectableTank() {

        this.waitTimer = new Wait(20 * 2, this, 0);
        fluidTank = new FluidTank(maxLiquid);
    }

    @Override
    public void updateEntity() {

        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

            if((fluidTank.getFluid() != null)) {

                if(fluidTank.getFluid().getFluid() == FluidRegistry.WATER && fluidTank.getFluidAmount() >= 2000) {

                    if(fluidTank.getCapacity() - fluidTank.getFluidAmount() >= 1000) {

                        UtilFluid.addFluidToTank(this, ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.WATER, 1000), true);
                    } 
                    else {

                        UtilFluid.addFluidToTank(this, ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.WATER, fluidTank.getCapacity() - fluidTank.getFluidAmount()), true);
                    }
                }

                if(!this.moveFluidBelow()) {

                    this.moveAround();
                }                
            }
        }
    }

    public int getFluidHandlersAround() {

        int amount = 0;

        for (ForgeDirection direction : ForgeDirection.values()) {

            if(direction != ForgeDirection.UP && direction != ForgeDirection.DOWN) {

                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                if(tile != null && tile instanceof IFluidHandler) {

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

                    return UtilFluid.moveFluid(this, ForgeDirection.DOWN, below, true);
                }
                return UtilFluid.moveFluid(this, ForgeDirection.DOWN, below, fluidTank.getFluidAmount(), true);
            }
        }
        return false;
    }

    public boolean moveAround() {

        boolean itWorked = false;

        if(this.fluidTank.getFluidAmount() >= this.getFluidHandlersAround() * 1000) {

            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

                if(direction != ForgeDirection.UP && direction != ForgeDirection.DOWN) {

                    TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                    if(tile != null) {

                        if(tile instanceof IFluidHandler) {

                            if(tile instanceof TileConnectableTank) {

                                if(((TileConnectableTank)tile).fluidTank.getFluidAmount() < this.fluidTank.getFluidAmount()) {

                                    itWorked = UtilFluid.moveFluid(this, direction, (IFluidHandler)tile, 1000, true);
                                }
                            }
                            else {

                                itWorked = UtilFluid.moveFluid(this, direction, (IFluidHandler)tile, 1000, true);
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

        int fill = fluidTank.fill(resource, doFill);

        if(((this.fluidTank.getFluid() != null && this.fluidTank.getFluid().isFluidEqual(resource)) || this.fluidTank.getCapacity() == this.fluidTank.getFluidAmount())) {            

            if(fill == 0) {

                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, ForgeDirection.UP);
                
                if(tile != null && tile instanceof TileConnectableTank) {
                    
                    return ((TileConnectableTank)tile).fill(ForgeDirection.DOWN, resource, doFill);
                }
            }

            if(doFill) {

                worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);   
                this.trigger(0);
            }
        }
        return fill;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {

        if(fluid != null) {

            if(fluidTank.getFluid() != null) {

                if(this.fluidTank.getFluid().getFluid() == fluid) {

                    return true;
                }
            } 
            else {

                return true;
            }
        }
        return true;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {

        if(resource == null || !resource.isFluidEqual(fluidTank.getFluid())) {

            return null;
        }

        if(doDrain) {

            worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
            this.trigger(0);   
        }     
        return fluidTank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {

        if(fluidTank.getFluidAmount() < maxDrain) {

            return null;
        }

        if(doDrain) {

            worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
            this.trigger(0);   
        }  
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

    public IFluidHandler getTankBelow(TileEntity tile) {

        TileEntity below = worldObj.getBlockTileEntity(tile.xCoord, tile.yCoord - 1, tile.zCoord);

        if(below instanceof IFluidHandler) {

            return (IFluidHandler) below;
        }
        return null;
    }

    public IFluidHandler getTankAbove(TileEntity tile) {

        TileEntity below = worldObj.getBlockTileEntity(tile.xCoord, tile.yCoord + 1, tile.zCoord);

        if(below instanceof IFluidHandler) {

            return (IFluidHandler) below;
        }

        return null;
    }


    @Override
    public void trigger(int id) {

        if(!worldObj.isRemote) {

            if(fluidTank.getFluid() != null) {

                PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 20, worldObj.provider.dimensionId, new TankPacket(xCoord, yCoord, zCoord, fluidTank.getFluid().getFluid().getID(), fluidTank.getFluid().amount).makePacket());
            } 
            else {

                PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 20, worldObj.provider.dimensionId, new TankPacket(xCoord, yCoord, zCoord, 0, 0).makePacket());
            }
        }
    }
}
