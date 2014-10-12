package GU.blocks.containers.BlockCreativeMetadata;

import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import GU.api.color.VanillaColor;
import GU.blocks.containers.BlockMetadataContainerBase;
import GU.blocks.containers.TileBase;
import GU.render.BlockSimpleRenderer.INoiseBlockRender;
import UC.color.Color4i;

public class BlockCreativeMetadata extends BlockMetadataContainerBase implements INoiseBlockRender {
    
    public BlockCreativeMetadata(Material material) {
        super(material);
        
        this.registerTile(TileCreativePower.class);
        this.registerTile(TileCreativeFluid.class);
        this.registerTile(TileCreativeItem.class);
    }
    
    @Override
    public Color4i getNoiseColor(int metadata) {
        
        switch (metadata) {
        
            case 0:
                return Color4i.WHITE;
            case 1:
                return VanillaColor.LIGHT_GREY.getRGBValue();
            case 2:
                return VanillaColor.GREY.getRGBValue();
        }
        return Color4i.WHITE;
    }
    
    @Override
    public Color4i getNoiseColor(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        
        return getNoiseColor(world.getBlockMetadata(x, y, z));
    }
    
    @Override
    public boolean canRenderNoise(int metadata) {
        
        return true;
    }
    
    @Override
    public boolean canRenderNoise(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        
        TileBase tile = (TileBase) world.getTileEntity(x, y, z);
        int metadata = world.getBlockMetadata(x, y, z);
        return metadata != 1 || (tile != null && tile instanceof TileCreativeFluid && ((TileCreativeFluid) tile).fluidTank.getFluidAmount() == 0);
    }
}
