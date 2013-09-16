package GU.blocks.containers.BlockUniversalConduit;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilDirection;
import ASB2.vector.Vector3;
import GU.api.network.IConductor;
import GU.api.network.INetwork;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.network.UniversalConduitNetwork;

public class TileUniversalConduit extends TileBase implements IConductor {

    UniversalConduitNetwork network;

    public TileUniversalConduit() {

        this.waitTimer = new Wait(20, this, 0);        
        network = new UniversalConduitNetwork();
    }

    public void updateEntity() {

        waitTimer.update();

        if(this.getNetwork() != null) {

            if(!this.getNetwork().getAvaliableConductors().contains(this)) {

                this.getNetwork().addConductor(worldObj, new Vector3(this));
            }
        }
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
    }

    @Override
    public boolean setNetwork(INetwork network) {

        if(network instanceof UniversalConduitNetwork) {

            this.network = (UniversalConduitNetwork)network;
            return true;
        }
        return false;
    }

    @Override
    public INetwork getNetwork() {

        return network;
    }

    @Override
    public int[] getCoords() {

        return new int[]{xCoord, yCoord, zCoord};
    }
}
