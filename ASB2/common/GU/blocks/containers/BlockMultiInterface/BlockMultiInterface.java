package GU.blocks.containers.BlockMultiInterface;

import net.minecraft.block.material.Material;
import GU.blocks.containers.ContainerBase;

public class BlockMultiInterface extends ContainerBase {
    
    public BlockMultiInterface(int id, Material material) {
        super(id, material);
        this.specialMetadata = true;
        this.registerTile(TileMultiInterfaceInventory.class);
    }
    
}
