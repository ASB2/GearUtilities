package GU.blocks.containers.BlockUniversalConduit;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilDirection;
import GU.api.conduit.IConduitConductor;
import GU.api.conduit.IConduitNetwork;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.conduit.ConduitNetwork;

public class TileUniversalConduit extends TileBase implements IConduitConductor {

    IConduitNetwork network;

    public TileUniversalConduit() {

        this.waitTimer = new Wait(20, this, 0);        
        network = new ConduitNetwork();
    }

    public void updateEntity() {

        //        waitTimer.update();

        if(this.getNetwork() != null) {

            this.getNetwork().updateNetwork(worldObj);

            if(!this.getNetwork().getAvaliableConductors().contains(this)) {

                this.getNetwork().addConductor(worldObj, this);
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
