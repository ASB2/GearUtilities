package GU.blocks.containers.BlockMultiInterface;

import UC.color.Color4i;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import GU.blocks.containers.BlockMetadataContainerBase;
import GU.render.BlockSimpleRenderer.*;

public class BlockMultiInterface extends BlockMetadataContainerBase implements INoiseBlockRender {
    
    public BlockMultiInterface(Material material) {
        super(material);
        this.registerTile(TileItemMultiInterface.class);
        this.registerTile(TileFluidMultiInterface.class);
        this.registerTile(TilePowerMultiInterface.class);
    }
    
    @Override
    public Color4i getColor(int metadata) {
        
        switch (metadata) {
        
            case 0:
                return Color4i.GREEN;
            case 1:
                return Color4i.BLUE.brighter();
            case 2:
                return Color4i.YELLOW.brighter();
        }
        return null;
    }
    
    @Override
    public Color4i getColor(IBlockAccess world, int x, int y, int z) {
        
        return getColor(world.getBlockMetadata(x, y, z));
    }
}
