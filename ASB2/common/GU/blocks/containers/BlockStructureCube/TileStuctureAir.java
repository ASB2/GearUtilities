package GU.blocks.containers.BlockStructureCube;

import ASB2.wait.Wait;
import GU.api.multiblock.IMultiBlock;
import GU.blocks.containers.TileMultiBase;

public class TileStuctureAir extends TileMultiBase {

    public TileStuctureAir() {
        waitTimer = new Wait(this, 5, 0);
        this.maxMultiBlocks = 1;
    }

    @Override
    public void updateEntity() {
        waitTimer.update();
    }

    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        super.removeMultiBlock(multiBlock);

        if (this.multiBlocks.isEmpty()) {

            worldObj.setBlockToAir(xCoord, yCoord, zCoord);
        }
    }

    @Override
    public void trigger(int id) {

        if (multiBlocks.isEmpty()) {

            worldObj.setBlockToAir(xCoord, yCoord, zCoord);
        }
    }
}
