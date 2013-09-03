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

            for(ForgeDirection packetDirection : ForgeDirection.VALID_DIRECTIONS) {

                TileEntity packetDestination = UtilDirection.translateDirectionToTile(this, worldObj, packetDirection);

                if(packetDestination != null && packetDestination instanceof IConduitConductor) {

                    for(ForgeDirection tileDirection : ForgeDirection.VALID_DIRECTIONS) {

                        TileEntity tileTakingOutOf = UtilDirection.translateDirectionToTile(this, worldObj, tileDirection);

                        if(tileTakingOutOf != null && (tileTakingOutOf instanceof IInventory || tileTakingOutOf instanceof IPowerMisc || tileTakingOutOf instanceof IFluidHandler)) {

                            this.getNetwork().addConduitPacketToQuene(new ConduitPacket(this, packetDirection, null, new Vector3(this), this.getNetwork()));
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onPacketRecieved(int x, int y, int z, IConduitPacket packet) {

        TileEntity tile = worldObj.getBlockTileEntity(x, y, z);

        if(tile != null) {

            for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

                TileEntity adjacent = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                if(adjacent != null) {

                    if(adjacent instanceof IInventory) {

                        if(tile instanceof IInventory) {

                            UtilInventory.moveEntireInventory((IInventory)tile, (IInventory)adjacent);
                        }
                    }

                    if(adjacent instanceof IFluidHandler) {

                        if(tile instanceof IFluidHandler) {

                            UtilFluid.moveFluid((IFluidHandler)tile, ((ConduitPacket)packet).getDirection().getOpposite(), (IFluidHandler)adjacent, true);
                        }
                    }
                    
                    if(adjacent instanceof IPowerMisc) {

                        if(tile instanceof IPowerMisc) {

                            PowerHelper.moveEnergy((IPowerMisc)tile, (IPowerMisc)adjacent, direction.getOpposite(), true);
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
