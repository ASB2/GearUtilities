package GU.blocks.containers.BlockTestRender;

import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;

public class TileTestRender extends TileBase {

    public TileTestRender() {

        waitTimer = new Wait(20, this, 0);
    }

    @Override
    public void updateEntity() {
    }

    @Override
    public void trigger(int id) {

        
    }
}
