package GU.blocks.containers.BlockBasicElemental;

import ASB2.wait.Wait;
import GU.blocks.containers.TileBase;

public class TileBasicElemental extends TileBase {
    
    public TileBasicElemental() {
        
        waitTimer = new Wait(this, 0, 0);
    }
    
    @Override
    public void trigger(int id) {
        // TODO Auto-generated method stub
        super.trigger(id);
    }
}
