package GU.blocks.containers.BlockMultiPart;

import net.minecraft.util.AxisAlignedBB;
import ASB2.utils.UtilBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.blocks.containers.TileMultiBase;
import GU.multiblock.clientState.MultiBlockClientState;

public class TileMultiPartRender extends TileMultiBase {
    
    MultiBlockClientState state;
    
    public TileMultiPartRender() {
        
        this.setMaxMultiBlocks(1);
    }
    
    @Override
    public void updateEntity() {
        
        if (worldObj.isRemote) {
            multiBlocks.size();
            // UtilEntity.sendClientChat(worldObj.isRemote + "");
        }
        super.updateEntity();
    }
    
    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        super.removeMultiBlock(multiBlock);
        
        if (multiBlocks.isEmpty()) {
            
            UtilBlock.breakBlockNoDrop(worldObj, xCoord, yCoord, zCoord);
        }
    }
    
    public TileMultiPartRender setClientState(MultiBlockClientState state) {
        
        this.state = state;
        return this;
    }
    
    public MultiBlockClientState getClientState() {
        
        return state;
    }
    
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        
        return INFINITE_EXTENT_AABB;
    }
    
    @Override
    public double getMaxRenderDistanceSquared() {
        
        return super.getMaxRenderDistanceSquared() * 2;
    }
}