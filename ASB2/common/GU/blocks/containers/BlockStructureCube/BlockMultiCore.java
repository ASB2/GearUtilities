package GU.blocks.containers.BlockStructureCube;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockMultiCore extends ContainerBase {

    public BlockMultiCore(int id, Material material) {
        super(id, material);
        // TODO Auto-generated constructor stub
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileMultiCore();
    }
}
