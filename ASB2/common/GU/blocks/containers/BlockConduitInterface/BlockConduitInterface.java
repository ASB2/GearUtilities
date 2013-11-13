package GU.blocks.containers.BlockConduitInterface;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockConduitInterface extends ContainerBase {

    public BlockConduitInterface(int id, Material material) {
        super(id, material);

        this.registerTile(TileConduitInterface.class);
        this.useStandardRendering = false;
    }

    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {

        super.breakBlock(world, x, y, z, par5, par6);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileConduitInterface();
    }
}
