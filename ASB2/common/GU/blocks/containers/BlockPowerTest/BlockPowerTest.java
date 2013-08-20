package GU.blocks.containers.BlockPowerTest;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.*;

public class BlockPowerTest extends ContainerBase {

    public BlockPowerTest(int id, Material material) {
        super(id, material);
        this.registerTile(TilePowerTest.class);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TilePowerTest();
    }
}
