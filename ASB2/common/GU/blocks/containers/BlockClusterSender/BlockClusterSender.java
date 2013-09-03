package GU.blocks.containers.BlockClusterSender;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockClusterSender extends ContainerBase {

    public BlockClusterSender(int id, Material material) {
        super(id, material);
        
        this.useStandardRendering = false;
        this.registerTile(TileClusterSender.class);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileClusterSender();
    }
}
