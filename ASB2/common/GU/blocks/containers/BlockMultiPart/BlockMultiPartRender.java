package GU.blocks.containers.BlockMultiPart;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import ASB2.utils.UtilBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.blocks.containers.TileMultiBase;
import GU.multiblock.MultiBlockClientState;
import cpw.mods.fml.client.registry.ClientRegistry;

public class BlockMultiPartRender extends BlockMultiBlockPartAir {
    
    public BlockMultiPartRender(Material material) {
        super(material);
        this.registerTile(TileMultiPartRender.class);
    }
    
    @Override
    public void postInit() {
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileMultiPartRender.class, MultiPartRenderer.instance);
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        
        return new TileMultiPartRender();
    }
    
    public static class TileMultiPartRender extends TileMultiBase {
        
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
        
        @Override
        public AxisAlignedBB getRenderBoundingBox() {
            
            return INFINITE_EXTENT_AABB;
        }
    }
}
