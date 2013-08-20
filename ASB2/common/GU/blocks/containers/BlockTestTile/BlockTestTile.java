package GU.blocks.containers.BlockTestTile;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockTestTile extends ContainerBase {

    public BlockTestTile(int id, Material material) {
        super(id, material);

        this.registerTile(TileTestTile.class);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileTestTile();
    }

}
