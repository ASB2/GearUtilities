package GU.blocks.containers.BlockMultiPart;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import GU.blocks.containers.BlockMultiMetadataContainerBase;
import GU.render.BlockSimpleRenderer.INoiseBlockRender;
import UC.color.Color4i;

public class BlockMultiBlockPart extends BlockMultiMetadataContainerBase implements INoiseBlockRender {
    
    public BlockMultiBlockPart(Material material) {
        super(material);
        
        this.registerTile(TileMultiPart.class);
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        
        return new TileMultiPart();
    }
    
    @Override
    public Color4i getColor(int metadata) {
        
        return Color4i.WHITE;
    }
    
    @Override
    public Color4i getColor(IBlockAccess world, int x, int y, int z) {
        
        return getColor(world.getBlockMetadata(x, y, z));
    }
}
