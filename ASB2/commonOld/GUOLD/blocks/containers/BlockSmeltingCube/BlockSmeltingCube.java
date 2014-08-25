package GUOLD.blocks.containers.BlockSmeltingCube;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GUOLD.blocks.containers.*;

public class BlockSmeltingCube extends ContainerBase {
    
    public BlockSmeltingCube(Material material) {
        super(material);
        this.registerTile(TileSmeltingCube.class);
    }
    
    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        
        return super.createTileEntity(world, metadata);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        
        return new TileSmeltingCube();
    }
}
