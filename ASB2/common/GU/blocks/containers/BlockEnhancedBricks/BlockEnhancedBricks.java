package GU.blocks.containers.BlockEnhancedBricks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockEnhancedBricks extends ContainerBase {

    public BlockEnhancedBricks(int id, Material material) {
        super(id, material);
        this.useStandardRendering = false;
        this.registerTile(TileEnchancedBricks.class);
    }

    public int getRenderBlockPass() {

        return EnchancedBricksRenderer.enchancedBricksID;
    }


    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileEnchancedBricks();
    }
}
