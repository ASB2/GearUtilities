package GU.blocks.containers.BlockUniversalConduit;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.*;
import ASB2.vector.Vector3;
import GU.api.conduit.ConduitNetwork;
import GU.api.conduit.IConduitConductor;
import GU.api.conduit.IConduitNetwork;
import GU.api.conduit.packet.ConduitPacket;
import GU.api.conduit.packet.IConduitPacket;
import GU.api.conduit.packet.IPacketReciever;
import GU.api.power.IPowerMisc;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.api.power.*;

public class TileUniversalConduit extends TileBase implements IConduitConductor, IPacketReciever {

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

            TileEntity source = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());

            if(source != null && (source instanceof IInventory || source instanceof IPowerMisc || source instanceof IFluidHandler)) {

                this.getNetwork().addConduitPacketToQuene(new ConduitPacket(this, this.getOrientation(), null, new Vector3(this), this.getNetwork()));
            }
        }
    }

    @Override
    public void onPacketRecieved(int x, int y, int z, IConduitPacket packet) {

        TileEntity sink = worldObj.getBlockTileEntity(x, y, z);

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

                            UtilFluid.moveFluid((IFluidHandler)source, ((ConduitPacket)packet).getDirection(), (IFluidHandler)sink, true);
                        }
                    }

                    if(source instanceof IPowerMisc) {

                        if(sink instanceof IPowerMisc) {

                            if(PowerHelper.moveEnergy((IPowerMisc)source, (IPowerMisc)sink, ((ConduitPacket)packet).getDirection(), true)) {
                                
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
