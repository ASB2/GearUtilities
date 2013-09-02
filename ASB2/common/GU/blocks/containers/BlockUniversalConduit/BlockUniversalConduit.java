package GU.blocks.containers.BlockUniversalConduit;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockUniversalConduit extends ContainerBase {

    public BlockUniversalConduit(int id, Material material) {
        super(id, material);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileUniversalConduit();
    }
}
