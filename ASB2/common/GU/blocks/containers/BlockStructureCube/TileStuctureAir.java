package GU.blocks.containers.BlockStructureCube;

import GU.api.multiblock.IMultiBlock;
import GU.blocks.containers.TileMultiBase;

public class TileStuctureAir extends TileMultiBase {

    public TileStuctureAir() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void updateEntity() {

        if (this.getComprisedMultiBlocks().size() == 0) {

            worldObj.setBlockToAir(xCoord, yCoord, zCoord);
        }
        super.updateEntity();
    }

    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        super.removeMultiBlock(multiBlock);
        worldObj.setBlockToAir(xCoord, yCoord, zCoord);
    }
}
