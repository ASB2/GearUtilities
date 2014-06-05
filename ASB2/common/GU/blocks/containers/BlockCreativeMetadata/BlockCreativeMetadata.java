package GU.blocks.containers.BlockCreativeMetadata;

import UC.color.Color4i;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import GU.blocks.containers.BlockMetadataContainerBase;
import GU.render.BlockSimpleRenderer.INoiseBlockRender;

public class BlockCreativeMetadata extends BlockMetadataContainerBase implements INoiseBlockRender {
    
    public BlockCreativeMetadata(Material material) {
        super(material);
        
        this.registerTile(TileCreativePower.class);
    }
    
    @Override
    public Color4i getColor(int metadata) {
        
        return Color4i.WHITE;
    }
    
    @Override
    public Color4i getColor(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        
        return Color4i.WHITE;
    }
    
    @Override
    public boolean canRender(int metadata) {
        
        return true;
    }
    
    @Override
    public boolean canRender(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        
        return false;
    }
}
