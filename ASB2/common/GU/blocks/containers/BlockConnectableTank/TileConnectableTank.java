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
import ASB2.vector.Vector3;
import GU.api.network.INetwork;
import GU.api.network.INetworkInterface;
import GU.api.network.UniversalConduitNetwork;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.packets.TankPacket;
import cpw.mods.fml.common.network.PacketDispatcher;
import GU.api.*;

public class TileConnectableTank extends TileBase implements IFluidHandler, INetworkInterface {

    INetwork network;
    public static int maxLiquid = FluidContainerRegistry.BUCKET_VOLUME * 64;
    
    public TileConnectableTank() {

        this.waitTimer = new Wait(20, this, 1);
        fluidTank = new FluidTank(maxLiquid);
        network = new UniversalConduitNetwork();
    }

    @Override
    public void updateEntity() {

        waitTimer.update();

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

        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

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

                    return UtilFluid.moveFluid(this, ForgeDirection.DOWN, below, ForgeDirection.UP, true);
                }
                return UtilFluid.moveFluid(this, ForgeDirection.DOWN, below, ForgeDirection.UP, fluidTank.getFluidAmount(), true);
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

                                    itWorked = UtilFluid.moveFluid(this, direction, (IFluidHandler)tile, direction.getOpposite(), 1000, true);
                                }
                            }
                            else {

                                itWorked = UtilFluid.moveFluid(this, direction, (IFluidHandler)tile, direction.getOpposite(), 1000, true);
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

            //            if(fill == 0) {
            //
            //                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, ForgeDirection.UP);
            //                
            //                if(tile != null && tile instanceof TileConnectableTank) {
            //                    
            //                    return ((TileConnectableTank)tile).fill(ForgeDirection.DOWN, resource, doFill);
            //                }
            //            }

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

        if(resource == null || !resource.isFluidEqual(fluidTank.getFluid()) || worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

            return null;
        }

        if(doDrain) {

            worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
            this.trigger(0);   
        }     
        return drain(from, resource.amount, doDrain);
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

        if(this.getNetwork() != null) {

            MiscHelpers.addConductorsAround(this, worldObj, this.getNetwork());
        }        

        if(id == 0) {

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

    @Override
    public boolean setNetwork(INetwork network) {

        this.network = network;

        if(network != null) {            

            if(!network.getConductors().contains(new Vector3(this))) {

                network.addConductor(new Vector3(this));
            }
            
            if(!network.getFluidInterfaces().contains(new Vector3(this))) {

                network.addFluidInterface(new Vector3(this));
            }
        }
        return true;
    }

    @Override
    public INetwork getNetwork() {

        return network;
    }

    @Override
    public TileEntity[] getAvaliableTileEntities(ForgeDirection direction) {

        return new TileEntity[]{this};
    }
}
