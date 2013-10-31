package GU.blocks.containers.BlockUniversalConduit;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilDirection;
import ASB2.vector.Vector3;
import GU.api.network.IConductor;
import GU.api.network.INetwork;
import GU.api.network.UniversalConduitNetwork;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;

public class TileUniversalConduit extends TileBase implements IConductor {

    INetwork network;

    public TileUniversalConduit() {

        this.waitTimer = new Wait(20, this, 0);        
        network = new UniversalConduitNetwork();
    }

    public void updateEntity() {

        waitTimer.update();
    }

    @Override
    public void trigger(int id) {

        if(this.getNetwork() != null) {

            for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                if(tile != null ) {

                    if(tile instanceof IConductor) {

                        IConductor conduit = (IConductor)tile;

                        if(conduit.getNetwork() != null) {

                            if(conduit.getNetwork() != this.getNetwork()) {

                                conduit.getNetwork().mergeNetworks(worldObj, this.getNetwork().getConductors());
                            
                                for(Vector3 vector : conduit.getNetwork().getConductors()) {

                                    if(vector != null && vector.getTileEntity(worldObj) != null && vector.getTileEntity(worldObj) instanceof IConductor) {

                                        ((IConductor)vector.getTileEntity(worldObj)).setNetwork(this.getNetwork());
                                    }
                                } 
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
    }

    @Override
    public boolean setNetwork(INetwork network) {

        this.network = network;
        
        return true;
    }

    @Override
    public INetwork getNetwork() {

        return network;
    }
}
