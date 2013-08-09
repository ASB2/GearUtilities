package GU.blocks.containers.BlockConduit;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockConduit extends ContainerBase {

    public BlockConduit(int id, Material material) {
        super(id, material);

        this.registerTile(TileConduit.class);
        useStandardRendering = false;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileConduit();
    }
}
