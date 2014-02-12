package GU.blocks.containers.BlockStructureCube;

import ASB2.wait.Wait;
import GU.api.multiblock.IMultiBlock;
import GU.blocks.containers.TileMultiBase;

public class TileStuctureAir extends TileMultiBase {

    public TileStuctureAir() {
        waitTimer = new Wait(this, 10, 0);
        this.maxMultiBlocks = 1;
    }

    @Override
    public void updateEntity() {
        waitTimer.update();
    }

    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        super.removeMultiBlock(multiBlock);
    }

    @Override
    public void trigger(int id) {

        if (this.getComprisedMultiBlocks().isEmpty() && !worldObj.isRemote) {

            worldObj.setBlockToAir(xCoord, yCoord, zCoord);
        }
    }
}
