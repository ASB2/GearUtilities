package GU.blocks.containers.BlockMultiInterface;

import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import GU.blocks.containers.TileMultiBase;

public class TileDataMultiInterface extends TileMultiBase implements IMultiBlockPart {
    
    public TileDataMultiInterface() {
        
        this.setMaxMultiBlocks(1);
    }
    
    @Override
    public void updateEntity() {
        // TODO Auto-generated method stub
        super.updateEntity();
    }
}
