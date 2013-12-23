package GU.blocks.containers.BlockRemoteInventory;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.*;

public class BlockRemoteInventory extends ContainerBase {
    
    public BlockRemoteInventory(int id, Material material) {
        super(id, material);
        
        this.registerTile(TileRemoteInventory.class);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        
        return new TileRemoteInventory();
    }
}
