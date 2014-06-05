package GU.blocks.containers.BlockCreativeMetadata;

import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import GU.blocks.containers.BlockMetadataContainerBase;
import GU.blocks.containers.TileBase;
import GU.render.BlockSimpleRenderer.INoiseBlockRender;
import UC.color.Color4i;

public class BlockCreativeMetadata extends BlockMetadataContainerBase implements INoiseBlockRender {
    
    public BlockCreativeMetadata(Material material) {
        super(material);
        
        this.registerTile(TileCreativePower.class);
        this.registerTile(TileCreativeFluid.class);
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
        
        TileBase tile = (TileBase) world.getTileEntity(x, y, z);
        int metadata = world.getBlockMetadata(x, y, z);
        return (tile != null && metadata == 1 && tile instanceof TileCreativeFluid && ((TileCreativeFluid) tile).fluidToSave.getFluidAmount() == 0) || metadata == 0;
    }
}
