package GU.blocks.containers.BlockDetachedRelocator;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockDetachedRelocator extends ContainerBase {

    public BlockDetachedRelocator(int id, Material material) {
        super(id, material);
        
        useStandardRendering = false;
        this.registerTile(TileDetachedRelocator.class);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileDetachedRelocator();
    }
}
