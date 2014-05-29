package GU.blocks.containers.BlockMultiInterface;

import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import GU.blocks.containers.BlockMultiMetadataContainerBase;
import GU.render.BlockSimpleRenderer.INoiseBlockRender;
import UC.color.Color4i;

public class BlockMultiInterface extends BlockMultiMetadataContainerBase implements INoiseBlockRender {
    
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
            case 3:
                return new Color4i(50, 90, 0).darker();
        }
        return null;
    }
    
    @Override
    public Color4i getColor(IBlockAccess world, int x, int y, int z) {
        
        return getColor(world.getBlockMetadata(x, y, z));
    }
}
