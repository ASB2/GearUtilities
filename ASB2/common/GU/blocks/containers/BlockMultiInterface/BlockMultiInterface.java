package GU.blocks.containers.BlockMultiInterface;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockMultiInterface extends ContainerBase {
    
    public BlockMultiInterface(int id, Material material) {
        super(id, material);
        this.specialMetadata = true;
        this.registerTile(TileMultiInterfaceInventory.class);
    }
    
    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        // TODO Auto-generated method stub
        return super.createTileEntity(world, metadata);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        // TODO Auto-generated method stub
        return null;
    }
}
