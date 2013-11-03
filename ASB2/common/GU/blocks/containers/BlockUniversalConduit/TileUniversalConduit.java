package GU.blocks.containers.BlockUniversalConduit;

import ASB2.vector.Vector3;
import GU.api.MiscHelpers;
import GU.api.network.IConductor;
import GU.api.network.INetwork;
import GU.api.network.UniversalConduitNetwork;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;

public class TileUniversalConduit extends TileBase implements IConductor {

    INetwork network;

    public TileUniversalConduit() {

        this.waitTimer = new Wait(20, this, 0);        
        this.setNetwork(new UniversalConduitNetwork());
    }

    public void updateEntity() {

        waitTimer.update();
    }

    @Override
    public void trigger(int id) {

        if(this.getNetwork() != null) {

            MiscHelpers.addConductorsAround(this, worldObj, this.getNetwork());
        }
    }

    @Override
    public boolean setNetwork(INetwork network) {

        this.network = network;  

        if(network != null) {            
            
            if(!network.getConductors().contains(new Vector3(this))) {
                
                network.addConductor(new Vector3(this));
            }
        }
        return true;
    }

    @Override
    public INetwork getNetwork() {

        return network;
    }
}
