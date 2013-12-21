package GU.blocks.containers.BlockQuantaSender;

import net.minecraft.tileentity.TileEntity;
import ASB2.vector.Vector3;
import ASB2.wait.Wait;
import GU.api.power.IPowerHandler;
import GU.api.power.IPowerProvider;
import GU.api.power.PowerClass;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.blocks.containers.TileBase;

public class TileQuantaSender extends TileBase implements IPowerHandler {
    
    public TileQuantaSender() {
        
        powerProvider = new PowerProvider(PowerClass.LOW, State.SINK);
        waitTimer = new Wait(this, 20, 0);
    }
    
    @Override
    public void updateEntity() {
        
        waitTimer.update();
    }
    
    @Override
    public void trigger(int id) {
        
        Vector3 position = new Vector3(this);
        
        for (int i = 1; i <= 16; i++) {
            
            position.add(this.getOrientation(), i);
            TileEntity tile = position.getTileEntity(worldObj);
            
            if (tile != null) {
                
                if (tile instanceof IPowerHandler) {
                    
                    ((IPowerHandler) tile).getPowerProvider().gainPower(10, this.getOrientation(), true);
                } else {
                    return;
                }
            }
        }
    }
    
    @Override
    public IPowerProvider getPowerProvider() {
        
        return powerProvider;
    }
}
