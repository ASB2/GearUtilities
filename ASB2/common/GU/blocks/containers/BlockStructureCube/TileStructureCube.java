package GU.blocks.containers.BlockStructureCube;

import GU.api.multiblock.IMultiBlock;
import GU.blocks.containers.TileMultiBase;

public class TileStructureCube extends TileMultiBase {
    
    public TileStructureCube() {
        useSidesRendering = false;
    }
    
    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        super.removeMultiBlock(multiBlock);
        if (!this.isInMultiBlock) {
            worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
        }
    }
}
