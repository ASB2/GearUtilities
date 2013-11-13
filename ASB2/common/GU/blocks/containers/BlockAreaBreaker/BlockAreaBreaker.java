package GU.blocks.containers.BlockAreaBreaker;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockAreaBreaker extends ContainerBase {

    public BlockAreaBreaker(int id, Material material) {
        super(id, material);
        
        this.registerTile(TileAreaBreaker.class);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileAreaBreaker();
    }
}
