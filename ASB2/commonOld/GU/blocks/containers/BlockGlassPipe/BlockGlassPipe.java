package GU.blocks.containers.BlockGlassPipe;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockGlassPipe extends ContainerBase {
    
    public BlockGlassPipe(Material material) {
        super(material);
        
        this.registerTile(TileGlassPipe.class);
        this.useStandardRendering = false;
    }
    
    @Override
    public boolean isOpaqueCube() {
        
        return false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        
        return new TileGlassPipe();
    }
}
