package GU.blocks.containers.BlockMultiInterface;

import net.minecraft.block.material.Material;
import GU.blocks.containers.BlockMetadataContainerBase;

public class BlockMultiInterface extends BlockMetadataContainerBase {
    
    public BlockMultiInterface(Material material) {
        super(material);
        this.registerTile(TileMultiInterface.class);
    }
}
