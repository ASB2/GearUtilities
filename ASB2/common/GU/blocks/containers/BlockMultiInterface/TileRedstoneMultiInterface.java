package GU.blocks.containers.BlockMultiInterface;

import GU.api.multiblock.MultiBlockAbstract.EnumMultiBlockPartPosition;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import GU.blocks.containers.TileMultiBase;

public class TileRedstoneMultiInterface extends TileMultiBase implements IMultiBlockPart {
    
    public TileRedstoneMultiInterface() {
        
        this.setMaxMultiBlocks(1);
    }
    
    @Override
    public boolean isPositionValid(EnumMultiBlockPartPosition position) {
        
        return position == EnumMultiBlockPartPosition.FACE;
    }
}
