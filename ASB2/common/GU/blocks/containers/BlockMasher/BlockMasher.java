package GU.blocks.containers.BlockMasher;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.*;

public class BlockMasher extends ContainerBase {

    public BlockMasher(int id, Material material) {
        super(id, material);
        
        this.registerTile(TileMasher.class);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileMasher();
    }
}
