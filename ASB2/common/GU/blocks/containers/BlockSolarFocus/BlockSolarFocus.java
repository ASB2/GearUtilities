package GU.blocks.containers.BlockSolarFocus;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockSolarFocus extends ContainerBase {

    public BlockSolarFocus(int id, Material material) {
        super(id, material);

    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileSolarFocus();
    }

}
