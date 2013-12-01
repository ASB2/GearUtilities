package GU.blocks.containers.BlockDetachedRelocator;

import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;

public class TileDetachedRelocator extends TileBase {

    public TileDetachedRelocator() {

        waitTimer = new Wait(20, this, 0);
    }

    @Override
    public void updateEntity() {

    }

    @Override
    public void trigger(int id) {

    }
}
