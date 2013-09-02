package GU.blocks.containers.BlockUniversalConduit;

import java.util.ArrayList;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import GU.api.conduit.ConduitNetwork;
import GU.api.conduit.IConduitConductor;
import GU.api.conduit.IConduitNetwork;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerClass;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.blocks.containers.BlockConnectableTank.TileConnectableTank;
import GU.power.GUPowerProvider;

public class TileUniversalConduit extends TileBase implements IConduitConductor, IPowerMisc, IFluidHandler {

    IConduitNetwork network;

    public TileUniversalConduit() {

        this.waitTimer = new Wait(20, this, 0);        
        network = new ConduitNetwork();
        powerProvider = new GUPowerProvider(PowerClass.LOW, State.OTHER);
    }

    public void updateEntity() {

        waitTimer.update();

        if(network != null) {

            network.updateNetwork(worldObj, xCoord, yCoord, zCoord);

            if(!network.getAvaliableConductors().contains(this)) {

                network.addConductor(worldObj, this);
            }

            //            for(ForgeDirection direction  : ForgeDirection.VALID_DIRECTIONS) { }
        }
    }

    public IConduitConductor getNearestValidConductor() {

        IConduitConductor last = this;

        while (true) {

            IConduitConductor below = validConductorAroundTile(last);

            if (below != null) {

                last = below;
            }
            else {

                break;
            }
        }
        return this;
    }

    public ArrayList<IConduitConductor> validConductorAroundTile(IConduitConductor conductor) {

        ArrayList<IConduitConductor> conduitList = new ArrayList<IConduitConductor>();

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {            

            TileEntity possibleConduit = UtilDirection.translateDirectionToTile(this, worldObj, direction);

            if(possibleConduit != null) {

                if(possibleConduit instanceof IConduitConductor && ((IConduitConductor)possibleConduit).getNetwork() != null && ((IConduitConductor)possibleConduit).getNetwork() == this.getNetwork()) {

                    boolean isPossible = false;
                    
                    for(ForgeDirection conduitTileDirection : ForgeDirection.VALID_DIRECTIONS) {     

                        TileEntity adjacent = UtilDirection.translateDirectionToTile(this, worldObj, conduitTileDirection);

                        isPossible = (adjacent instanceof IPowerMisc || adjacent instanceof IInventory || adjacent instanceof IFluidHandler);
                    }
                    if(isPossible) {
                        
                        conduitList.add((IConduitConductor)possibleConduit);
                    }
                }
            }                    
        }
        return conduitList;
    }

    @Override
    public void trigger(int id) {

        if(this.getNetwork() != null) {

            for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                if(tile != null && tile instanceof IConduitConductor) {

                    IConduitConductor conduit = (IConduitConductor)tile;

                    if(conduit.getNetwork() != null) {

                        if(conduit.getNetwork() != this.getNetwork()) {

                            conduit.getNetwork().mergeNetworks(worldObj, this.getNetwork().getAvaliableConductors());
                        }
                        else {

                            conduit.setNetwork(this.getNetwork());
                        }
                    }
                    else {

                        conduit.setNetwork(this.getNetwork());
                    }
                }
            }
        }
    }

    @Override
    public boolean setNetwork(IConduitNetwork network) {

        this.network = network;
        return true;
    }

    @Override
    public IConduitNetwork getNetwork() {

        return network;
    }

    @Override
    public int[] getCoords() {

        return new int[]{xCoord, yCoord, zCoord};
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

    @Override
    public PowerProvider getPowerProvider() {

        return powerProvider;
    }

}
