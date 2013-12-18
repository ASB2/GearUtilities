package GU.blocks.containers.BlockSmeltingCube;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.*;

public class BlockSmeltingCube extends ContainerBase {
    
    public BlockSmeltingCube(int id, Material material) {
        super(id, material);
        // TODO Auto-generated constructor stub
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
