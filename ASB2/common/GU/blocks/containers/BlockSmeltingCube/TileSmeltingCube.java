package GU.blocks.containers.BlockSmeltingCube;

import ASB2.wait.Wait;
import GU.blocks.containers.*;
import GU.api.power.*;

public class TileSmeltingCube extends TileBase implements IPowerMisc {
    
    public TileSmeltingCube() {
        
        powerProvider = new PowerProvider(PowerClass.LOW, State.SINK);
        waitTimer = new Wait(this, 20, 0);
    }
    
    @Override
    public void updateEntity() {
        
        waitTimer.update();
    }
    
    @Override
    public void trigger(int id) {
        
    }
    
    @Override
    public IPowerProvider getPowerProvider() {
        
        return powerProvider;
    }
}
