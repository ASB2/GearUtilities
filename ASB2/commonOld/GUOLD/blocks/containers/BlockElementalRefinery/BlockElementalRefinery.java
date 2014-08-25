package GUOLD.blocks.containers.BlockElementalRefinery;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GUOLD.blocks.containers.ContainerBase;

public class BlockElementalRefinery extends ContainerBase {

    public BlockElementalRefinery( Material material) {
        super( material);

        this.registerTile(TileElementalRefinery.class);
        this.useStandardRendering = false;
        this.setLightOpacity(0);
    }

    @Override
    public boolean isOpaqueCube() {

        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {

        return new TileElementalRefinery();
    }
}
