package GU.blocks.containers.BlockStructureCube;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMultiCore extends BlockStructureAir {

    public BlockMultiCore(int id, Material material) {
        super(id, material);

        this.registerTile(TileMultiCore.class);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileMultiCore();
    }
}
