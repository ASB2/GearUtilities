package GU.blocks.containers.BlockMultiPart;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import ASB2.utils.UtilBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.blocks.containers.TileMultiBase;
import GU.multiblock.MultiBlockBase;
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
        
        public MultiBlockBase toRender;
        
        public TileMultiPartRender() {
            this.setMaxMultiBlocks(1);
        }
        
        @Override
        public boolean addMultiBlock(IMultiBlock multiBlock) {
            
            if (multiBlock instanceof MultiBlockBase) {
                
                if (super.addMultiBlock(multiBlock)) {
                    
                    if (toRender == null) {
                        
                        toRender = (MultiBlockBase) multiBlock;
                    }
                    return true;
                }
            }
            return false;
        }
        
        @Override
        public void removeMultiBlock(IMultiBlock multiBlock) {
            super.removeMultiBlock(multiBlock);
            
            if (toRender == multiBlock) {
                
                toRender = null;
            }
            
            if (multiBlocks.isEmpty()) {
                
                UtilBlock.breakBlockNoDrop(worldObj, xCoord, yCoord, zCoord);
            }
        }
        
        @Override
        public AxisAlignedBB getRenderBoundingBox() {
            
            return INFINITE_EXTENT_AABB;
        }
    }
}
