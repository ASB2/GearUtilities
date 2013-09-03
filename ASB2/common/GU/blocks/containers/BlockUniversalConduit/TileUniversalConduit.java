package GU.blocks.containers.BlockUniversalConduit;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import ASB2.utils.UtilInventory;
import ASB2.vector.Vector3;
import GU.api.conduit.IConduitConductor;
import GU.api.conduit.IConduitNetwork;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerHelper;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.conduit.ConduitNetwork;
import GU.api.cluster.*;
import GU.entity.EntityCluster.*;

public class TileUniversalConduit extends TileBase implements IConduitConductor, IClusterTrigger {

    IConduitNetwork network;

    public TileUniversalConduit() {

        this.waitTimer = new Wait(20, this, 0);        
        network = new ConduitNetwork();
    }

    public void updateEntity() {

        waitTimer.update();

        if(this.getNetwork() != null) {

            this.getNetwork().updateNetwork(worldObj);

            if(!this.getNetwork().getAvaliableConductors().contains(this)) {

                this.getNetwork().addConductor(worldObj, this);
            }
        }
        
        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);
            
            if(tile != null) {
                
                if(tile instanceof IInventory || tile instanceof IPowerMisc || tile instanceof IFluidHandler) {
                    
                    EntityInfoCluster clustor = new EntityInfoCluster(worldObj, new Vector3(this), direction.getOpposite(), this);
                    worldObj.spawnEntityInWorld(clustor);
                }
            }
        }
    }

    @Override
    public void onClustorCollosion(ForgeDirection side, Vector3 position, IClustor clustor) {
        
        TileEntity sink = position.getTileEntity(worldObj);

        if(sink != null) {

            TileEntity source = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation());

            if(source != null) {

                if(source != sink) {

                    if(source instanceof IInventory) {

                        if(sink instanceof IInventory) {

                            UtilInventory.moveEntireInventory((IInventory)source, (IInventory)sink);
                        }
                    }

                    if(source instanceof IFluidHandler) {

                        if(sink instanceof IFluidHandler) {

                            UtilFluid.moveFluid((IFluidHandler)source, side, (IFluidHandler)sink, true);
                        }
                    }

                    if(source instanceof IPowerMisc) {

                        if(sink instanceof IPowerMisc) {

                            if(PowerHelper.moveEnergy((IPowerMisc)source, (IPowerMisc)sink, side, true)) {
                                
                                System.out.println("Packet Recieved");
                            }
                        }
                    }
                }
            }                 
        }   
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
}
